package kniff;

import java.awt.Color;
import java.util.*;

public class Design
{
	private static Dictionary<Colors, Color> colorDictionary = new Hashtable<Colors, Color>();
	
	public static void setColorScheme(int i)
	{
		switch (i)
		{
		case 0:
			setDefault();
			break;
		case 1:
			setBlueGreen();
			break;
		case 2:
			setBlueOrange();
			break;
		default:
			setDefault();
			break;
		}
	}
	
	public static void setRandom()
	{
		setColorScheme((int) (Math.random() * 10));
	}
	
	public static Color getColor(Colors c)
	{
		return colorDictionary.get(c);
	}
	
	private static void setBlueOrange()
	{

	}
	
	private static void setBlueGreen()
	{

	}
	
	private static void setDefault()
	{
		colorDictionary = new Hashtable<Colors, Color>();
		colorDictionary.put(Colors.bg_dark, 		Color.decode("#AAAAAA"));
		colorDictionary.put(Colors.bg_light, 		Color.decode("#BBBBBB"));
		colorDictionary.put(Colors.fg_dark, 		Color.decode("#DDDDDD"));
		colorDictionary.put(Colors.fg_light, 		Color.decode("#EEEEEE"));
		colorDictionary.put(Colors.accent_a_dark, 	Color.decode("#55AA55"));
		colorDictionary.put(Colors.accent_a_light, 	Color.decode("#BBDD99"));
		colorDictionary.put(Colors.accent_b_dark, 	Color.decode("#AAAA55"));
		colorDictionary.put(Colors.accent_b_light,  Color.decode("#FFDD99"));
	}
}
