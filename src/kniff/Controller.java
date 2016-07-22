package kniff;

import java.awt.CardLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import helper.POI;
import screens.ScEnd;
import screens.ScGame;
import screens.ScHelp;
import screens.ScOption;
import screens.ScPromt;
import screens.ScRanking;
import screens.ScSettings;
import screens.ScStart;
import screens.ScWhoIsFirst;
import screens.Screen;

public class Controller
{
	public static Screen scContainer 		= new Screen(new CardLayout());
	public static CardLayout clController 	= (CardLayout)(scContainer.getLayout());

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player currentPlayer;
	public static Iterator<Player> ip;
	public static int remainingRolls = 3;
	public static int remainingRounds = 13;

	public static ScStart scStart;
	public static ScGame scGame;
	public static ScOption scOption;
	public static ScSettings scSettings;
	public static ScHelp scHelp;
	public static ScPromt scPromt;
	public static ScRanking scRanking;
	public static ScEnd scEnd;
	public static ScWhoIsFirst scWhoIsFirst;

	public static Dice[] kniffDice;

	public static void main(String[] args)
	{
		Design.setRandom();
		Design.setFont(new Font("OCR A Extended", Font.PLAIN, 12));

		kniffDice = Dice.initDiceCollection();
		initScreens();
		MainWindow.main(args);
		show(scStart);
		// Test
		// show(scEnd);
		// Test
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
		scWhoIsFirst.startPlayerOrder(players);
	}

	public static void startGameScreen()
	{
		Controller.show(scGame);
		scGame.init();
		ip = players.iterator();
		nextPlayer();
		scGame.writeMessage(currentPlayer.getFullName() + " macht den ersten Wurf");
	}

	public static void nextPlayer()
	{
		vanishSheetValues(currentPlayer);
		if (!ip.hasNext())
		{
			if (remainingRounds <= 0)
			{
				saveScores();
				Controller.show(scEnd);
				return;
			}
			ip = players.iterator();
			remainingRounds--;
		}

		currentPlayer = ip.next();

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
				ScRanking.newScores=true;
			} catch (IOException e)
			{
				System.err.println("Der Punktestand von " + p.getName() + " konnte aufgrund eines Schreib-Lesefehlers nicht gespeichert werden!");
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
		scHelp = new ScHelp();
		scPromt = new ScPromt();
		scRanking = new ScRanking();
		scEnd = new ScEnd();
		scWhoIsFirst = new ScWhoIsFirst();

		scContainer.add(scStart, scStart.getName());
		scContainer.add(scOption, scOption.getName());
		scContainer.add(scGame, scGame.getName());
		scContainer.add(scSettings, scSettings.getName());
		scContainer.add(scHelp, scHelp.getName());
		scContainer.add(scPromt, scPromt.getName());
		scContainer.add(scRanking, scRanking.getName());
		scContainer.add(scEnd, scEnd.getName());
		scContainer.add(scWhoIsFirst, scWhoIsFirst.getName());
	}

	public static boolean addPlayer(Player player)
	{
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