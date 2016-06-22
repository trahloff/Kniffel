package kniff;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import helper.EColor;

public class Dice extends JButton
{
	private static final long serialVersionUID = 1L;
	
	private int value;
	private boolean isInitial;
	
	public Dice()
	{
		super();
	}
	
	public int roll()
	{
		if (this.isEnabled())
		{
			this.isInitial = false;
			this.value = (int) Math.round((Math.random()*5) + 1);
		}
		this.setText(value + "");
		this.repaint();
		return this.value;
	}
	
	public void setInitial(boolean v)
	{
		this.isInitial = v;
		this.value = 0;
	}
	
	public static void setAllEnabled(boolean v)
	{
		for (Dice d : Controller.kniffDice)
			d.setEnabled(v);
	}
	
	public static void setAllInitial(boolean v)
	{
		for (Dice d : Controller.kniffDice)
			d.setInitial(v);
	}
	
	public static Dice[] rollAll()
	{
		for (Dice d : Controller.kniffDice)
			d.roll();
		return Controller.kniffDice;
	} 
	
	public static boolean allDeactivated()
	{
        for (int i = 0; i < Controller.kniffDice.length; i++)
       	 if (Controller.kniffDice[i].isEnabled())
       		 return false;
        return true;
	}
	
	public static int[] getSortedValues(Dice[] Dice)
	{
		int[] values = new int[5];
		int i = 0;
		for (Dice d : Dice)
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
		
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
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
        	g.setColor(Design.getColor(EColor.disabled_dice_a));
            g.fillRoundRect(12, 12, 76, 76, 15, 15);
            g.setColor(Color.decode("#E0E0E0"));
        	g.drawRoundRect(12, 12, 76, 76, 15, 15);
			g.setColor(Color.decode("#444444"));
        }
        if (this.isInitial)
		{
			g.drawLine(25, 25, this.getWidth() - 25, this.getHeight() - 25);
			g.drawLine(25, 75, this.getWidth() - 25, this.getHeight() - 75);
		}
        else
        {
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
    }
	
	public static Dice[] initDiceCollection()
	{
		Dice die1 = new Dice();
		die1.addMouseListener(DieButtonListener);
		die1.setPreferredSize(new Dimension(100, 100));
		die1.setText("1");
		die1.setFont(Design.getFont());
		
		Dice die2 = new Dice();
		die2.addMouseListener(DieButtonListener);
		die2.setPreferredSize(new Dimension(100, 100));
		die2.setText("2");
		die2.setFont(Design.getFont());
		
		Dice die3 = new Dice();
		die3.addMouseListener(DieButtonListener);
		die3.setPreferredSize(new Dimension(100, 100));
		die3.setText("3");
		die3.setFont(Design.getFont());
		
		Dice die4 = new Dice();
		die4.addMouseListener(DieButtonListener);
		die4.setPreferredSize(new Dimension(100, 100));
		die4.setText("4");
		die4.setFont(Design.getFont());
		
		Dice die5 = new Dice();
		die5.addMouseListener(DieButtonListener);
		die5.setPreferredSize(new Dimension(100, 100));
		die5.setText("5");
		die5.setFont(Design.getFont());
		
		return new Dice[]{die1, die2, die3, die4, die5};
	}
	
	private static MouseAdapter DieButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof Dice)
		             {
		                 Dice button = (Dice) e.getSource();
		                 if (button.isEnabled())
		                	 button.setEnabled(false);
		                 else
		                	 button.setEnabled(true);
		                 Controller.updateBtnRoll();
		             }
		     }
	};
}