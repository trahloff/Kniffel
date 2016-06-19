package kniff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class DesignerButton extends JButton
{
	private static final long serialVersionUID = 1L;
	private ButtonDesignType buttonType;
	
	public DesignerButton(String string)
	{
		super(string);
		this.setButtonType(ButtonDesignType.menuButton);
		initDesign();
	}

	public DesignerButton(String string, ButtonDesignType type)
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
	
	public void SetButtonType(ButtonDesignType type)
	{
		this.setButtonType(type);
	}
	
	public void paintComponent(Graphics g)
    {
        
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        switch (this.buttonType)
		{
        	case startButton:
        		paintGameStartButton(g);
        		break;
        	case menuButton:
        		paintMenuButton(g);
        		break;
			default:
				super.paintComponent(g);
				break;
		}
        
        
        super.paintComponent(g);
    }
	
	private void paintGameStartButton(Graphics g)
	{	
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillOval(0, 0, this.getHeight(), this.getWidth());
		g.setColor(Design.getColor(Colors.accent_a_light));
		g.fillOval(15, 15, this.getHeight() - 30, this.getWidth() - 30);
		g.setColor(Design.getColor(Colors.bg_dark));
		g.fillOval(20, 20, this.getHeight() - 40, this.getWidth() - 40);
		
		// final Color setting for Text
		this.setForeground(Design.getColor(Colors.fg_dark));
	}
	private void paintMenuButton(Graphics g)
	{
		g.setColor(Design.getColor(Colors.light_glow_a));
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 40, 40);
		g.setColor(Design.getColor(Colors.accent_a_light));
		g.fillRoundRect(3, 3, this.getWidth() - 6, this.getHeight() - 6, 35, 35);
		g.setColor(Design.getColor(Colors.bg_light));
		g.fillRoundRect(5, 5, this.getWidth() - 10, this.getHeight() - 10, 35, 35);
		
		// final Color setting for Text
		this.setForeground(Design.getColor(Colors.fg_light));
	}

	public ButtonDesignType getButtonType()
	{
		return buttonType;
	}

	public void setButtonType(ButtonDesignType menubutton)
	{
		this.buttonType = menubutton;
	}
}
