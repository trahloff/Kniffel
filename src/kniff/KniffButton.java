package kniff;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import helper.EComponentDesign;

public class KniffButton extends JButton
{
	private EComponentDesign design = EComponentDesign.menuButton;
	
	public KniffButton(String string)
	{
		super(string);
		this.addMouseListener(mouseListener);
		this.setFont(Design.getFont());
	}
	
	public EComponentDesign getDesign()
	{
		return design;
	}

	public void setDesign(EComponentDesign design)
	{
		this.design = design;
	}

	public boolean isClicked()
	{
		return isClicked;
	}

	public boolean isEntered()
	{
		return isEntered;
	}

	public boolean isExited()
	{
		return isExited;
	}

	public boolean isPressed()
	{
		return isPressed;
	}

	public boolean isReleased()
	{
		return isReleased;
	}

	private boolean isClicked, isEntered, isExited, isPressed, isReleased;
	
	public void setComponentDesign(EComponentDesign d)
	{
		this.design = d;
	}
	
	public EComponentDesign getComponentDesign()
	{
		return this.design;
	}

	private MouseListener mouseListener = new MouseListener()
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

	public void paintComponent(Graphics g)
	{
		Design.drawButton(this, g);
		super.paintComponent(g);
	}
}
