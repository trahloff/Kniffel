package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import helper.EColor;
import helper.EColorScheme;
import helper.EDesign;

public class Design
{
	private static Dictionary<EColor, Color> colorDictionary = new Hashtable<EColor, Color>();
	private static EColorScheme globalColorScheme = EColorScheme.Standard;
	private static Font globalFont = new Font("OCR A Extended", Font.PLAIN, 12);
	private static EDesign globalDesignScheme = EDesign.Standard;
	
	public static void setColorScheme(EColorScheme s)
	{
		globalColorScheme = s;
		switch (s)
		{
		case Standard:
			setDefault();
			break;
		case Feuer:
			setFire();
			break;
		case Wasser:
			setWater();
			break;
		case Erde:
			setEarth();
			break;
		default:
			break;
		}
	}
	
	public static void setRandom()
	{
		EColorScheme c;
		EDesign d;
		
		switch ((int) (Math.random() * 10))
		{
		case 0:
		case 1:
		case 2:
			c = EColorScheme.Erde;
		case 3:
			c = EColorScheme.Wasser;
			break;
		case 4:
			c = EColorScheme.Feuer;
			break;
		default:
			c = EColorScheme.Standard;
			break;
		}
		
		switch ((int) (Math.random() * 10))
		{
		case 0:
		case 1:
		case 2:
		case 3:
			d = EDesign.Sap;
			break;
		case 4:
			d = EDesign.Episch;
			break;
		default:
			d = EDesign.Standard;
			break;
		}
		
		setColorScheme(c);
		setDesignScheme(d);
	}
	
	public static Color getColor(EColor c)
	{
		return colorDictionary.get(c);
	}
	
	private static void setFire()
	{
		colorDictionary = new Hashtable<EColor, Color>();
		colorDictionary.put(EColor.bg_dark, 		Color.decode("#665555")); // Rahmen von MenüButton und Hintergrund von allen Button bei Maus über Button
		colorDictionary.put(EColor.bg_light, 		Color.decode("#DDDDDD")); // Rahmen vom StartButton und Hintergrund von Menübutton
		colorDictionary.put(EColor.fg_dark, 		Color.decode("#111111")); // Schrift der Menübutton
		colorDictionary.put(EColor.fg_light, 		Color.decode("#FFFFFF")); // Schrift der Startbutton
		colorDictionary.put(EColor.accent_a_dark, 	Color.decode("#DDAA33"));
		colorDictionary.put(EColor.accent_a_light, 	Color.decode("#F47755")); // Schrift der Menübutton bei Maus über Button
		colorDictionary.put(EColor.accent_b_dark, 	Color.decode("#00FFAA")); // Rahmen von Startbutton bei Maus über Button
		colorDictionary.put(EColor.accent_b_light,  Color.decode("#00AADD"));
		colorDictionary.put(EColor.light_glow_a,  	Color.decode("#DDEEFF"));
		colorDictionary.put(EColor.disabled_dice_a, Color.decode("#EFEFEF")); //  HintergrundButton Kniffel-Tabelle 
		colorDictionary.put(EColor.disabled_dice_b, Color.decode("#E0E0E0")); //  Rahmen von HintergrundButton Kniffel-Tabelle
		colorDictionary.put(EColor.error_text, 		Color.decode("#DD0066")); //  Farbe für Fehler
		colorDictionary.put(EColor.warn_text, 		Color.decode("#DDAA00")); //  Farbe für Warnungen
	}
	
	// Achtung extrem hässlich!!! :D
	private static void setEarth()
	{
		colorDictionary = new Hashtable<EColor, Color>();
		colorDictionary.put(EColor.bg_dark, 		Color.decode("#666655")); // Rahmen von MenüButton und Hintergrund von allen Button bei Maus über Button
		colorDictionary.put(EColor.bg_light, 		Color.decode("#EEE9EE")); // Rahmen vom StartButton und Hintergrund von Menübutton
		colorDictionary.put(EColor.fg_dark, 		Color.decode("#111111")); // Schrift der Menübutton
		colorDictionary.put(EColor.fg_light, 		Color.decode("#FFFFFF")); // Schrift der Startbutton
		colorDictionary.put(EColor.accent_a_dark, 	Color.decode("#AA3399"));
		colorDictionary.put(EColor.accent_a_light, 	Color.decode("#DDCC99")); // Schrift der Menübutton bei Maus über Button
		colorDictionary.put(EColor.accent_b_dark, 	Color.decode("#00FFAA")); // Rahmen von Startbutton bei Maus über Button
		colorDictionary.put(EColor.accent_b_light,  Color.decode("#00AADD"));
		colorDictionary.put(EColor.light_glow_a,  	Color.decode("#DDEEFF"));
		colorDictionary.put(EColor.disabled_dice_a, Color.decode("#ECECE0")); //  HintergrundButton Kniffel-Tabelle 
		colorDictionary.put(EColor.disabled_dice_b, Color.decode("#887755")); //  Rahmen von HintergrundButton Kniffel-Tabelle
		colorDictionary.put(EColor.error_text, 		Color.decode("#DD0066")); //  Farbe für Fehler
		colorDictionary.put(EColor.warn_text, 		Color.decode("#DDAA00")); //  Farbe für Warnungen
	}
	
	private static void setDefault()
	{
		colorDictionary = new Hashtable<EColor, Color>();
		colorDictionary.put(EColor.bg_dark, 		Color.decode("#888888"));
		colorDictionary.put(EColor.bg_light, 		Color.decode("#DDDDDD"));
		colorDictionary.put(EColor.fg_dark, 		Color.decode("#444444"));
		colorDictionary.put(EColor.fg_light, 		Color.decode("#EEEEEE"));
		colorDictionary.put(EColor.accent_a_dark, 	Color.decode("#55AA55"));
		colorDictionary.put(EColor.accent_a_light, 	Color.decode("#88CCBB"));
		colorDictionary.put(EColor.accent_b_dark, 	Color.decode("#AAAA55"));
		colorDictionary.put(EColor.accent_b_light,  Color.decode("#FFDD99"));
		colorDictionary.put(EColor.light_glow_a,  	Color.decode("#DDDDDD"));
		colorDictionary.put(EColor.disabled_dice_a, Color.decode("#EFEFEF"));
		colorDictionary.put(EColor.disabled_dice_b, Color.decode("#E0E0E0"));
		colorDictionary.put(EColor.error_text, 		Color.decode("#DD0066")); //  Farbe für Fehler
		colorDictionary.put(EColor.warn_text, 		Color.decode("#DDAA00")); //  Farbe für Warnungen
	}

	private static void setWater()
	{
		colorDictionary = new Hashtable<EColor, Color>();
		colorDictionary.put(EColor.bg_dark, 		Color.decode("#666666"));
		colorDictionary.put(EColor.bg_light, 		Color.decode("#DDDDDD"));
		colorDictionary.put(EColor.fg_dark, 		Color.decode("#222222"));
		colorDictionary.put(EColor.fg_light, 		Color.decode("#DDDDDD"));
		colorDictionary.put(EColor.accent_a_dark, 	Color.decode("#55AA55"));
		colorDictionary.put(EColor.accent_a_light, 	Color.decode("#6666FF"));
		colorDictionary.put(EColor.accent_b_dark, 	Color.decode("#AAAA55"));
		colorDictionary.put(EColor.accent_b_light,  Color.decode("#FFDD99"));
		colorDictionary.put(EColor.light_glow_a,  	Color.decode("#DDDDDD"));
		colorDictionary.put(EColor.disabled_dice_a, Color.decode("#EFEFEF"));
		colorDictionary.put(EColor.disabled_dice_b, Color.decode("#E0E0E0"));
		colorDictionary.put(EColor.error_text, 		Color.decode("#DD0066")); //  Farbe für Fehlertexte
		colorDictionary.put(EColor.warn_text, 		Color.decode("#DDAA00")); //  Farbe für Warnungen
	}
	
	public static Font getFont()
	{
		return globalFont;
	}
	
	public static void drawButton(KniffButton b, Graphics g) 
	{
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
		
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch (b.getComponentDesign())
		{
		case menuButton:
			paintMenuButton(b, g);
			break;
		case startButton:
			paintStartButton(b, g);
			break;
		default:
			break;

		}
	}
	
	private static void paintStartButton(KniffButton b, Graphics g) // Startbutton
	{
		g.setColor(Design.getColor(EColor.bg_dark));
		
		if (b.isEnabled())
		{	
			g.setColor(Design.getColor(EColor.bg_light));
			g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
			g.setColor(Design.getColor(EColor.bg_dark));
			g.fillOval(5, 5, b.getWidth() - 11, b.getHeight() - 11);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(EColor.fg_light));
			
			if (b.isClicked())
			{

			}
			else if(b.isEntered())
			{
				g.setColor(Design.getColor(EColor.accent_a_light));
				g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillOval(5, 5, b.getWidth() - 11, b.getHeight() - 11);
				
//				g.setColor(Design.getColor(Colors.accent_a_light));					// Leonard Test
//				g.fillRect(b.getWidth()/ 2 -50, b.getHeight()/ 2 -10, 100, 20);  // Leonard Test
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.fg_light));
			}
			else if(b.isExited())
			{
				g.setColor(Design.getColor(EColor.bg_light));
				g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillOval(5, 5, b.getWidth() - 11, b.getHeight() - 11);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.fg_light));
			}
			else if(b.isPressed())
			{
				g.setColor(Design.getColor(EColor.accent_a_light));
				g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillOval(10, 10, b.getWidth() - 21, b.getHeight() - 21);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.fg_light));
			}
			else if(b.isReleased())
			{
				
			}
		}
		else
		{
			g.setColor(Design.getColor(EColor.bg_light));
			g.fillRoundRect(3, 3, b.getWidth() - 6, b.getHeight() - 6, 15, 15);
			g.setColor(Design.getColor(EColor.bg_dark));
			g.fillRoundRect(5, 5, b.getWidth() - 10, b.getHeight() - 10, 15, 15);
			g.setColor(Design.getColor(EColor.bg_dark));
			g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, 15, 15);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(EColor.fg_dark));
		}
		
	}

	// MenuButton
	private static void paintMenuButton(KniffButton b, Graphics g)
	{		
		switch (globalDesignScheme)
		{
		case Episch:
			// break;
		case Sap:
			// break;
		case Standard:
		default:
			paintMenuButtonDefault(b, g);
			break;
		}
	}

	// MenuButton : Default
	private static void paintMenuButtonDefault(KniffButton b, Graphics g)
	{
		int r = 5;
		
		if (b.isEnabled())
		{	
			g.setColor(Design.getColor(EColor.bg_light));
			g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
			g.setColor(Design.getColor(EColor.bg_dark));
			g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(EColor.fg_dark));
			
			if (b.isClicked())
			{
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.fg_light));
			}
			else if(b.isEntered())
			{
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.accent_a_light));
			}
			else if(b.isExited())
			{
				g.setColor(Design.getColor(EColor.bg_light));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.fg_dark));
			}
			else if(b.isPressed())
			{
				g.setColor(Design.getColor(EColor.accent_a_light));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillRoundRect(0, 5, b.getWidth() - 1, b.getHeight() - 5, r, r);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.accent_a_light));
			}
			else if(b.isReleased())
			{
				g.setColor(Design.getColor(EColor.bg_dark));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(EColor.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(EColor.fg_light));
			}
		}
		else
		{
			g.setColor(Design.getColor(EColor.disabled_dice_a));
			g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
			g.setColor(Design.getColor(EColor.disabled_dice_b));
			g.drawRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(EColor.fg_light));
		}
	}
	
	public static void drawPanel(KniffPanel p, Graphics g) 
	{
		p.setFont(getFont());		
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		int r = 5;
        
		g.setColor(Design.getColor(EColor.disabled_dice_a));
		g.fillRoundRect(0, 0, p.getWidth() - 1, p.getHeight() - 1, r, r);
		g.setColor(Design.getColor(EColor.disabled_dice_b));
        g.drawRoundRect(0, 0, p.getWidth() - 1, p.getHeight() - 1, r, r);
	}
	
	public static void setFont(Font font)
	{
		globalFont = font;
	}

	public static EColorScheme getColorScheme()
	{
		return globalColorScheme;
	}
	
	public static void setDesignScheme(EDesign d)
	{
		globalDesignScheme = d;
	}
	
	public static EDesign getDesignScheme()
	{
		return globalDesignScheme;
	}
}
