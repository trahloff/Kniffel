package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JButton;

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
		colorDictionary = new Hashtable<Colors, Color>();
		colorDictionary.put(Colors.bg_dark, 		Color.decode("#555555"));
		colorDictionary.put(Colors.bg_light, 		Color.decode("#F0F0F0"));
		colorDictionary.put(Colors.fg_dark, 		Color.decode("#F0F0F0"));
		colorDictionary.put(Colors.fg_light, 		Color.decode("#222222"));
		colorDictionary.put(Colors.accent_a_dark, 	Color.decode("#00AADD"));
		colorDictionary.put(Colors.accent_a_light, 	Color.decode("#00DDFF"));
		colorDictionary.put(Colors.accent_b_dark, 	Color.decode("#00FFAA"));
		colorDictionary.put(Colors.accent_b_light,  Color.decode("#00AADD"));
		colorDictionary.put(Colors.light_glow_a,  	Color.decode("#DDEEFF"));
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
		colorDictionary.put(Colors.light_glow_a,  	Color.decode("#DDDDDD"));
	}

	public static void drawButton(JButton b, Graphics g)
	{
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
		b.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		
		g.setColor(Design.getColor(Colors.light_glow_a));
		g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 40, 40);
		g.setColor(Design.getColor(Colors.accent_a_light));
		g.fillRoundRect(3, 3, b.getWidth() - 6, b.getHeight() - 6, 35, 35);
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillRoundRect(5, 5, b.getWidth() - 10, b.getHeight() - 10, 35, 35);
		
		// final Color setting for Text
		b.setForeground(Design.getColor(Colors.fg_light));
		b.paintComponents(g);
	}
}
