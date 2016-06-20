package kniff;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Controller
{
	public static Screen scContainer 		= new Screen(new CardLayout());
	public static CardLayout clController 	= (CardLayout)(scContainer.getLayout());
	public static Player currentPlayer;
	public static Screen scStart 			= new ScStart();
	public static ScGame scGame 			= new ScGame();
	public static Screen scOption 			= new ScOption();
	
	public static void main(String[] args)
	{
		Design.setColorScheme(1);
		Design.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		initScreens();
		initEngine();
		MainWindow.main(args);
		show(scStart);
	}
	
	public static void show(Screen sc)
	{
	    clController.show(scContainer, sc.getName());
	}
	
	public static void startGame()
	{
		Controller.show(scGame);
		scGame.writeMessage("jetzt wird gekniffelt");
	}
	
	public static void nextPlayer()
	{
		currentPlayer = KniffEngine.nextPlayer();
		scGame.writeMessage(currentPlayer.name + " ist an der Reihe");
		scGame.enableSheetForPlayer(currentPlayer);
	}
	
	public static void rollDice()
	{
		Dice.rollAll();
	}
	
	private static void initScreens()
	{
		scContainer.setName("container");
		
		scContainer.add(scStart, scStart.getName());
		scContainer.add(scOption, scOption.getName());
		scContainer.add(scGame, scGame.getName());
	}
	
	private static void initEngine()
	{
		KniffEngine.initEngine();
	}
}