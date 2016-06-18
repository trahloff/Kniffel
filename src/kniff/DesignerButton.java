package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class DesignerButton extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonType;
	
	//
	
	public DesignerButton(String string)
	{
		super(string);
		this.setButtonType("menuButton");
		initDesign();
	}

	public DesignerButton(String string, String type)
	{
		super(string);
		this.setButtonType(type);
		initDesign();
	}
	
	private void initDesign()
	{
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
	}
	
	public void SetButtonType(String type)
	{
		this.setButtonType(type);
	}
	
	public void paintComponent(Graphics g)
    {
        
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        switch (this.buttonType)
		{
        	case "gameStartButton":
        		paintGameStartButton(g);
        		break;
        	case "menuButton":
        		paintMenuButton(g);
        		break;
			default:
				super.paintComponent(g);
				break;
		}
        
        this.setForeground(Design.getColor(Colors.accent_a_dark));
        super.paintComponent(g);
    }
	
	private void paintGameStartButton(Graphics g)
	{	
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillOval(0, 0, 150, 150);
		g.setColor(Design.getColor(Colors.accent_a_light));
		g.fillOval(15, 15, 120, 120);
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillOval(20, 20, 110, 110);
	}
	private void paintMenuButton(Graphics g)
	{
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillRoundRect(0, 0, 150, 50, 40, 40);
		g.setColor(Design.getColor(Colors.accent_a_light));
		g.fillRoundRect(3, 3, 144, 44, 35, 35);
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillRoundRect(5, 5, 140, 40, 35, 35);
	}

	public String getButtonType()
	{
		return buttonType;
	}

	public void setButtonType(String buttonType)
	{
		this.buttonType = buttonType;
	}
}
