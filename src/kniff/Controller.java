package kniff;

import java.awt.CardLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import poi.POI;

public class Controller
{
	public static Screen scContainer 		= new Screen(new CardLayout());
	public static CardLayout clController 	= (CardLayout)(scContainer.getLayout());

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player currentPlayer;
	public static Iterator<Player> ip;
	public static int remainingRolls = 3;
	private static int remainingRounds = 13;

	public static ScStart scStart;
	public static ScGame scGame;
	public static ScOption scOption;
	public static ScSettings scSettings;
	public static Dice[] kniffDice;

	public static void main(String[] args)
	{
		Design.setRandom();
		Design.setFont(new Font("OCR A Extended", Font.PLAIN, 12));

		kniffDice = Dice.initDiceCollection();
		initScreens();
		MainWindow.main(args);
		show(scStart);
	}

	public static void show(Screen sc)
	{
		clController.show(scContainer, sc.getName());
	}

	public static void startGame(ArrayList<Player> p) throws Exception
	{
		players.addAll(p);
		ip = players.iterator();
		remainingRounds = 12;

		if (ip.hasNext()) {
			currentPlayer = ip.next();
		} else {
			throw new Exception("Es scheint keine Spieler zu geben.");
		}

		Controller.show(scGame);
		scGame.init();
		scGame.writeMessage(currentPlayer.getFullName() + " macht den ersten Wurf");
	}

	public static void nextPlayer()
	{
		vanishSheetValues(currentPlayer);
		if (!ip.hasNext())
		{
			ip = players.iterator();
			if (remainingRounds <= 0)
			{
				stopGame(0);
				return;
			}
			remainingRounds--;
		}
		if (ip.hasNext()) {
			currentPlayer = ip.next();
		}

		scGame.writeMessage(currentPlayer.getFullName() + " ist an der Reihe");
		remainingRolls = 3;

		scGame.setRanking(getRanking());

		scGame.getBtnRoll().setEnabled(true);
		scGame.setEnableSheets(false);
		Dice.setAllEnabled(true);
		Dice.setAllInitial(true);
	}

	private static void vanishSheetValues(Player p)
	{
		p.getSheet().vanish();
	}

	public static void stopGame(int i)
	{
		switch (i)
		{
		case 0:
			System.out.println("Spielende");
			saveScores();
			break;
		case 1:
			System.out.println("Spielabbruch durch Spieler");
			break;
		default:
			System.out.println("Spielabbruch undefiniert");
			break;
		}

		for (Player p : players) {
			p.resetSheet();
		}

		currentPlayer = null;
		players.clear();
		show(scStart);

	}

	private static void saveScores()
	{
		for (Player p : players) {
			try
			{
				POI.savePlayerScore(p.getName(), p.getPoints());
			} catch (IOException e)
			{
				System.err.println("Der Punktestand von " + p.getName() + " konnte aufgrund eines Schreib-Lesefehlers nicht gespeichert werden!");
				e.printStackTrace();
			}
		}
	}

	public static void rollDice()
	{
		if (!currentPlayer.getSheet().isEnabled()) {
			currentPlayer.getSheet().setEnabled(true);
		}
		if (remainingRolls > 0)
		{
			Dice.rollAll();
			updateSheetValue(currentPlayer, kniffDice);
			remainingRolls--;
		}
		if (remainingRolls < 1) {
			scGame.getBtnRoll().setEnabled(false);
		}
		scGame.enableSheetForPlayer(currentPlayer);
	}

	private static void updateSheetValue(Player p, Dice[] kniffDice)
	{
		p.getSheet().updateSheetValues(kniffDice);
	}

	public static void updateBtnRoll()
	{
		scGame.getBtnRoll().setEnabled(!Dice.allDeactivated());
		if (remainingRolls <= 0) {
			scGame.getBtnRoll().setEnabled(false);
		}
	}

	private static void initScreens()
	{
		scContainer.setName("container");

		scStart	= new ScStart();
		scOption = new ScOption();
		scGame = new ScGame();
		scSettings = new ScSettings();

		scContainer.add(scStart, scStart.getName());
		scContainer.add(scOption, scOption.getName());
		scContainer.add(scGame, scGame.getName());
		scContainer.add(scSettings, scSettings.getName());
	}

	public static boolean addPlayer(Player player) {
		if(players.size() <= 8) {
			players.add(player);
			return true;
		}
		return false;

	}

	public static Player[] getRanking()
	{
		Player[] ranking = new Player[players.size()];
		int j = 0;
		for (Player player : players)
		{
			ranking[j] = player;
			j++;
		}
		boolean swapped;
		int end = ranking.length - 1;
		do
		{
			swapped = false;
			for (int i = 0; i < end; i++)
			{
				if (ranking[i].getPoints() < ranking[i+1].getPoints())
				{
					Player temp = ranking[i];
					ranking[i] = ranking[i+1];
					ranking[i+1] = temp;
					swapped = true;
				}
			}
			end--;
		} while (swapped);

		return ranking;
	}
}