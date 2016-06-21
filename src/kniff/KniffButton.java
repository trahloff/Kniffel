package kniff;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import helper.EComponentDesign;

public class KniffButton extends JButton
{
	private EComponentDesign design = EComponentDesign.menuButton;
	
	public void setComponentDesign(EComponentDesign d)
	{
		this.design = d;
	}
	
	public EComponentDesign getComponentDesign()
	{
		return this.design;
	}
	
	public boolean isClicked, isEntered, isExited, isPressed, isReleased;
	private MouseListener la = new MouseListener()
			{
				public void reset()
				{
					isClicked = false;
					isEntered = false;
					isExited = false;
					isPressed = false;
					isReleased = false;
					
				}
				@Override
				public void mouseClicked(MouseEvent e)
				{
					reset();
					isClicked = true;
				}

				@Override
				public void mouseEntered(MouseEvent e)
				{
					reset();
					isEntered = true;
				}

				@Override
				public void mouseExited(MouseEvent e)
				{
					reset();
					isExited = true;
				}

				@Override
				public void mousePressed(MouseEvent e)
				{
					reset();
					isPressed = true;
				}

				@Override
				public void mouseReleased(MouseEvent e)
				{
					reset();
					isReleased = true;
				}
		
			};
	
	public KniffButton(String string)
	{
		super(string);
		this.addMouseListener(la);
	}

	public void paintComponent(Graphics g)
	{
		Design.drawButton(this, g);
		super.paintComponent(g);
	}
}
