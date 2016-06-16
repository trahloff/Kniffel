package kniff;

import java.awt.*;
import javax.swing.JButton;

public class Die extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2847346976374468132L;

	public static Die[] KniffDice = new Die[5];
	
	private int value;
	
	public Die()
	{
		super();
		initDesign();
	}
	
	public int roll()
	{
		if (this.isEnabled())
			this.value = (int) Math.round((Math.random()*5) + 1);
		this.setText(value + "");
		
		return this.value;
	}
	
	public static Die[] rollAll()
	{
		for (Die d : KniffDice)
			d.roll();
		return KniffDice;
	} 
	
	public static boolean allDeactivated()
	{
        for (int i = 0; i < KniffDice.length; i++)
       	 if (KniffDice[i].isEnabled())
       		 return false;
        return true;
	}
	
	public static int[] getSortedValues(Die[] Dice)
	{
		int[] values = new int[5];
		int i = 0;
		for (Die d : Dice)
		{
			values[i] = d.value;
			if (i > 0)
				sortNext(values, i);
			i++;
		}
		
		return values;
	}
	
	public static int[] sortKniffelDiceValues(int[] values)
	{
		if (values.length != 5)
			throw new ArrayIndexOutOfBoundsException("Das Array muss genau 5 Elemente enthalten!");
		
		int[] sortedValues = new int[5];
		int j = 0;
		for (int i = 0; i < values.length; i++)
		{
			sortedValues[j] = values[i];
			if (j > 0)
				sortNext(values, j);
			j++;
		}
		
		return values;
	}
	
	private static int[] sortNext(int[] values, int i)
	{
		if (i > 0)
			if (values[i-1] > values[i])
			{
				int j = values[i-1];
				values[i-1] = values[i];
				values[i] = j;
				sortNext(values, i-1);
			}
		return values;
	}
	
	public void paintComponent(Graphics g)
    {
        //super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (this.isEnabled())
		{
        	g.setColor(Color.decode("#FDFDFD"));
        	g.fillRoundRect(15, 15, 70, 70, 15, 15);
        	g.setColor(Color.decode("#222222"));
		}
        else
        {
        	g.setColor(Color.decode("#EFEFEF"));
            g.fillRoundRect(12, 12, 76, 76, 15, 15);
			g.setColor(Color.decode("#444444"));
        }
        
        switch (this.value)
		{
		case 1:
			g.fillOval(45, 45, 10, 10);
			break;
		case 2:
			g.fillOval(25, 25, 10, 10);
			g.fillOval(65, 65, 10, 10);
			break;
		case 3:
			g.fillOval(25, 25, 10, 10);
			g.fillOval(45, 45, 10, 10);
			g.fillOval(65, 65, 10, 10);
			break;
		case 4:
			g.fillOval(25, 25, 10, 10);
			g.fillOval(65, 65, 10, 10);	
			g.fillOval(65, 25, 10, 10);
			g.fillOval(25, 65, 10, 10);
			break;
		case 5:
			g.fillOval(25, 25, 10, 10);
			g.fillOval(45, 45, 10, 10);
			g.fillOval(65, 65, 10, 10);
			g.fillOval(65, 25, 10, 10);
			g.fillOval(25, 65, 10, 10);
			break;
		case 6:
			g.fillOval(25, 25, 10, 10);
			g.fillOval(25, 45, 10, 10);
			g.fillOval(25, 65, 10, 10);
			g.fillOval(65, 25, 10, 10);
			g.fillOval(65, 45, 10, 10);
			g.fillOval(65, 65, 10, 10);
			break;
			
		default:
			break;
		}
    }
	
	private void initDesign()
	{
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
}