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

public class Design
{
	private static Dictionary<Colors, Color> colorDictionary = new Hashtable<Colors, Color>();
	private static Font globalFont;
	
	public static void setColorScheme(int i)
	{
		switch (i)
		{
		case 0:
			setDefault();
			break;
		case 1:
			setFire();
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
	
	private static void setFire()
	{
		colorDictionary = new Hashtable<Colors, Color>();
		colorDictionary.put(Colors.bg_dark, 		Color.decode("#555555"));
		colorDictionary.put(Colors.bg_light, 		Color.decode("#DDDDDD"));
		colorDictionary.put(Colors.fg_dark, 		Color.decode("#111111"));
		colorDictionary.put(Colors.fg_light, 		Color.decode("#FFFFFF"));
		colorDictionary.put(Colors.accent_a_dark, 	Color.decode("#DDAA33"));
		colorDictionary.put(Colors.accent_a_light, 	Color.decode("#F47755"));
		colorDictionary.put(Colors.accent_b_dark, 	Color.decode("#00FFAA"));
		colorDictionary.put(Colors.accent_b_light,  Color.decode("#00AADD"));
		colorDictionary.put(Colors.light_glow_a,  	Color.decode("#DDEEFF"));
		colorDictionary.put(Colors.disabled_dice_a, Color.decode("#EFEFEF"));
		colorDictionary.put(Colors.disabled_dice_b, Color.decode("#E0E0E0"));
	}
	
	private static void setDefault()
	{
		colorDictionary = new Hashtable<Colors, Color>();
		colorDictionary.put(Colors.bg_dark, 		Color.decode("#888888"));
		colorDictionary.put(Colors.bg_light, 		Color.decode("#DDDDDD"));
		colorDictionary.put(Colors.fg_dark, 		Color.decode("#444444"));
		colorDictionary.put(Colors.fg_light, 		Color.decode("#EEEEEE"));
		colorDictionary.put(Colors.accent_a_dark, 	Color.decode("#55AA55"));
		colorDictionary.put(Colors.accent_a_light, 	Color.decode("#88CCBB"));
		colorDictionary.put(Colors.accent_b_dark, 	Color.decode("#AAAA55"));
		colorDictionary.put(Colors.accent_b_light,  Color.decode("#FFDD99"));
		colorDictionary.put(Colors.light_glow_a,  	Color.decode("#DDDDDD"));
		colorDictionary.put(Colors.disabled_dice_a, Color.decode("#EFEFEF"));
		colorDictionary.put(Colors.disabled_dice_b, Color.decode("#E0E0E0"));
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
		b.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch (b.bdt)
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
	
	private static void paintStartButton(KniffButton b, Graphics g)
	{
		int r = 5;
		g.setColor(Design.getColor(Colors.bg_dark));
		
		if (b.isEnabled())
		{	
			g.setColor(Design.getColor(Colors.bg_light));
			g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
			g.setColor(Design.getColor(Colors.bg_dark));
			g.fillOval(5, 5, b.getWidth() - 11, b.getHeight() - 11);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(Colors.fg_light));
			
			if (b.isClicked)
			{
				
			}
			else if(b.isEntered)
			{
				g.setColor(Design.getColor(Colors.accent_a_light));
				g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillOval(5, 5, b.getWidth() - 11, b.getHeight() - 11);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.fg_light));
			}
			else if(b.isExited)
			{
				g.setColor(Design.getColor(Colors.bg_light));
				g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillOval(5, 5, b.getWidth() - 11, b.getHeight() - 11);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.fg_light));
			}
			else if(b.isPressed)
			{
				g.setColor(Design.getColor(Colors.accent_a_light));
				g.fillOval(0, 0, b.getWidth() - 1, b.getHeight() - 1);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillOval(10, 10, b.getWidth() - 21, b.getHeight() - 21);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.fg_light));
			}
			else if(b.isReleased)
			{
				
			}
		}
		else
		{
			g.setColor(Design.getColor(Colors.bg_light));
			g.fillRoundRect(3, 3, b.getWidth() - 6, b.getHeight() - 6, 15, 15);
			g.setColor(Design.getColor(Colors.bg_dark));
			g.fillRoundRect(5, 5, b.getWidth() - 10, b.getHeight() - 10, 15, 15);
			g.setColor(Design.getColor(Colors.bg_dark));
			g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, 15, 15);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(Colors.fg_dark));
		}
		g.drawOval(0, 0, b.getWidth()-1, b.getHeight()-1);
		
	}

	private static void paintMenuButton(KniffButton b, Graphics g)
	{		
		int r = 5;
		
		if (b.isEnabled())
		{	
			g.setColor(Design.getColor(Colors.bg_light));
			g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
			g.setColor(Design.getColor(Colors.bg_dark));
			g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(Colors.fg_dark));
			
			if (b.isClicked)
			{
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.fg_light));
			}
			else if(b.isEntered)
			{
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.accent_a_light));
			}
			else if(b.isExited)
			{
				g.setColor(Design.getColor(Colors.bg_light));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.fg_dark));
			}
			else if(b.isPressed)
			{
				g.setColor(Design.getColor(Colors.accent_a_light));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillRoundRect(0, 5, b.getWidth() - 1, b.getHeight() - 5, r, r);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.accent_a_light));
			}
			else if(b.isReleased)
			{
				g.setColor(Design.getColor(Colors.bg_dark));
				g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
				g.setColor(Design.getColor(Colors.bg_dark));
				g.drawRoundRect(0, 0, b.getWidth()-1, b.getHeight()-1, r, r);
				
				// final Color setting for Text
				b.setForeground(Design.getColor(Colors.fg_light));
			}
		}
		else
		{
			g.setColor(Design.getColor(Colors.disabled_dice_a));
			g.fillRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
			g.setColor(Design.getColor(Colors.disabled_dice_b));
			g.drawRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, r, r);
			
			// final Color setting for Text
			b.setForeground(Design.getColor(Colors.fg_light));
		}
	}

	public static void setFont(Font font)
	{
		globalFont = font;
	}
}
