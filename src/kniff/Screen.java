package kniff;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel
{	
	public Screen(CardLayout cardLayout)
	{
		super(cardLayout);
	}

	public Screen()
	{
		super();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(this.getBackground());
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		super.paintComponent(g);
	}
}
