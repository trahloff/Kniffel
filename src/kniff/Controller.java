package kniff;

import java.util.ArrayList;
import javax.swing.JPanel;

public class Controller
{
	public static Screen scStart = new StartScreen();
	public static Screen scOption, scGame;
	
	private static ArrayList<Screen> screens = new ArrayList<Screen>();
	
	public static void main(String[] args)
	{
		Design.setColorScheme(0);	
		MainWindow.main(args);
	}
	
	public static void Show(Screen sc)
	{
		for (Screen screen : screens)
			screen.hide();
		sc.show();
	}
}
