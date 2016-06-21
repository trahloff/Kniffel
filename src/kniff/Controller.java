package kniff;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JPanel;

public class Controller
{
	public static Screen scContainer 		= new Screen(new CardLayout());
	public static CardLayout clController 	= (CardLayout)(scContainer.getLayout());
	
	public static TreeSet<Player> players = new TreeSet<Player>();
	public static Player currentPlayer;
	public static Iterator<Player> ip;
	public static Dice[] kniffDice = new Dice[5];
	public static int remainingRolls = 3;
	private static int remainingRounds = 13;
	
	public static ScStart scStart;
	public static ScGame scGame;
	public static ScOption scOption;
	public static ScSettings scSettings;
	
	public static void main(String[] args)
	{
		Design.setRandom();
		Design.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		kniffDice = Dice.initDiceCollection();
		initScreens();		
		MainWindow.main(args);
		show(scStart);
	}
	
	public static void show(Screen sc)
	{
	    clController.show(scContainer, sc.getName());
	}
	
	public static void startGame(TreeSet<Player> p) throws Exception
	{
		Controller.show(scGame);
		players.addAll(p);
		ip = players.iterator();
		remainingRounds = 12;
		
		if (ip.hasNext())
			currentPlayer = ip.next();
		else
			throw new Exception("Es scheint keine Spieler zu geben.");
	
		scGame.init();
		scGame.getBtnRoll().setEnabled(true);
		scGame.setEnableSheets(false);
		Dice.setAllEnabled(true);
		Dice.setAllInitial(true);
		scGame.writeMessage("jetzt wird gekniffelt und " + currentPlayer.name + " fängt an");
	}
	
	public static void nextPlayer()
	{
		vanishSheetValues(currentPlayer);
		if (!ip.hasNext())
		{
			ip = players.iterator();
			if (remainingRounds <= 0)
			{
				stopGame();
				return;
			}
			remainingRounds--;
		}
		if (ip.hasNext())
			currentPlayer = ip.next();	
		
		scGame.writeMessage(currentPlayer.name + " ist an der Reihe");
		remainingRolls = 3;
		
		scGame.getBtnRoll().setEnabled(true);
		scGame.setEnableSheets(false);
		Dice.setAllEnabled(true);
		Dice.setAllInitial(true);
	}
	
	private static void vanishSheetValues(Player p)
	{
		p.sheet.vanish();
	}

	public static void stopGame()
	{
		show(scStart);
		currentPlayer = null;
		players.clear();
	}
	
	public static void rollDice()
	{
		if (!currentPlayer.sheet.isEnabled())
			currentPlayer.sheet.setEnabled(true);
		if (remainingRolls > 0)
		{
			Dice.rollAll();
			updateSheetValue(currentPlayer, kniffDice);
			remainingRolls--;
		}
		if (remainingRolls < 1)
			scGame.getBtnRoll().setEnabled(false);
		scGame.enableSheetForPlayer(currentPlayer);
	}
	
	private static void updateSheetValue(Player p, Dice[] kniffDice)
	{
		p.sheet.updateSheetValues(kniffDice);
	}

	public static void updateBtnRoll()
	{
		scGame.getBtnRoll().setEnabled(!Dice.allDeactivated());
		if (remainingRolls <= 0)
			scGame.getBtnRoll().setEnabled(false);
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
}