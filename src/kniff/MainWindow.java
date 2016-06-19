package kniff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;

public class MainWindow
{	
	private JFrame frmKniffelig;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow();
					window.frmKniffelig.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainWindow()
	{
		initialize();
	}

	private void initialize()
	{
		frmKniffelig = new JFrame();
		frmKniffelig.setResizable(false);
		frmKniffelig.setTitle("Kniffelig");
		frmKniffelig.setBounds(100, 100, 760, 750);
		frmKniffelig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frmKniffelig.getContentPane().setLayout(new CardLayout(0, 0));	
		frmKniffelig.getContentPane().add(Controller.scContainer, "container");
	}
	
	private void updateButtonForPlayer(Player p)
	{
		ArrayList<DiceCombination> combis = p.KniffSheet.getFixedCombinations();
		Enumeration<CombiButton> iCombiButton = CombiButton.combiButtons.elements();
		CombiButton button;
		
		while(iCombiButton.hasMoreElements())
		{
			button = iCombiButton.nextElement();
			button.setEnabled(true);
			button.setText("" + KniffSheet.calcPoints(button.getLinkedCombination(), Dice.getSortedValues(Dice.KniffDice)));
		}
		
		for (DiceCombination combi : combis)
		{
			button = CombiButton.combiButtons.get(combi);
			button.setEnabled(false);
			button.setText(p.KniffSheet.getPoints(combi) + "");
		}	
	}
}