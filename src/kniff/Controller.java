package kniff;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Controller
{
	public static Screen scContainer 		= new Screen(new CardLayout());
	public static CardLayout clController 	= (CardLayout)(scContainer.getLayout());
	public static Screen scStart 			= new ScStart();
	public static ScGame scGame 			= new ScGame();
	public static Screen scOption 			= new ScOption();
	
	private static ArrayList<Screen> screens = new ArrayList<Screen>();
	
	public static void main(String[] args)
	{
		Design.setColorScheme(0);
		initScreens();
		initEngine();
		MainWindow.main(args);
		show(scStart);
	}
	
	public static void show(Screen sc)
	{
	    clController.show(scContainer, sc.getName());
	}
	
	public static void startGame(ArrayList<Player> players)
	{
		
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