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
	
	public static ScStart scStart;
	public static ScGame scGame;
	public static ScOption scOption;
	public static ScSettings scSettings;
	
	public static void main(String[] args)
	{
		Design.setColorScheme(ColorScheme.Fire);
		Design.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		kniffDice = Dice.initDiceCollection();
		initScreens();
		
		try
		{
			addPlayer(new Player("Anna", "AnA"));
			//addPlayer(new Player("Barbara", "B$L"));
			//addPlayer(new Player("Charlie", "Cha"));
			//addPlayer(new Player("Dennis", "God"));
			//addPlayer(new Player("Eduard", "Edu"));
			//addPlayer(new Player("Frederike", "Frd"));
			//addPlayer(new Player("Galadriel", "Gal"));
			//addPlayer(new Player("Henrik", "Hrk"));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainWindow.main(args);
		show(scStart);
	}
	
	public static void show(Screen sc)
	{
	    clController.show(scContainer, sc.getName());
	}
	
	public static void startGame() throws Exception
	{
		Controller.show(scGame);
		if (ip.hasNext())
			currentPlayer = ip.next();
		else
			throw new Exception("Es scheint keine Spieler zu geben.");
		
		scGame.enableSheetForPlayer(currentPlayer);
		scGame.writeMessage("jetzt wird gekniffelt und " + currentPlayer.name + " fängt an");
	}
	
	public static void nextPlayer()
	{
		if (!ip.hasNext())
			ip = players.iterator();
		if (ip.hasNext())
			currentPlayer = ip.next();	
		
		scGame.writeMessage(currentPlayer.name + " ist an der Reihe");
		scGame.enableSheetForPlayer(currentPlayer);
	}
	
	public static void rollDice()
	{
		Dice.rollAll();
	}
	
	private static void addPlayer(Player p)
	{
		players.add(p);
		ip = players.iterator();
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
}