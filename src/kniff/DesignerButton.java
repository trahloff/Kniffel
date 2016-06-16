package kniff;

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
	}

	public DesignerButton(String string, String type)
	{
		super(string);
		this.setButtonType(type);
	}
	
	public void SetButtonType(String type)
	{
		this.setButtonType(type);
	}
	
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
//        switch (this.buttonType)
//		{
//        	case "gameStartButton":
//        		paintGameStartButton(g);
//        		break;
//        	case "menuButton":
//        		paintNormalButton(g);
//        		break;
//			default:
//				//super.paintComponent(g);
//				break;
//		}
    }
	
//	private void paintGameStartButton(Graphics g)
//	{	
//		g.setColor(Color.decode("#555555"));
//		g.fillOval(0, 0, 150, 150);
//		g.setColor(Color.decode("#55DDAA"));
//		g.drawOval(15, 15, 120, 120);
//	}
//	private void paintNormalButton(Graphics g)
//	{
//		g.setColor(Color.decode("#ABACAD"));
//		g.drawRoundRect(0, 0, 150, 50, 40, 40);
//	}

	public String getButtonType()
	{
		return buttonType;
	}

	public void setButtonType(String buttonType)
	{
		this.buttonType = buttonType;
	}
}
