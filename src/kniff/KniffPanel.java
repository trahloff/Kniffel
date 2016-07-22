package kniff;

import java.awt.Graphics;

import javax.swing.JPanel;

public class KniffPanel extends JPanel
{

	public KniffPanel()
	{
		super();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Design.drawPanel(this, g);
	}
}
