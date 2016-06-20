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
		frmKniffelig.setBounds(100, 100, 800, 900);
		frmKniffelig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frmKniffelig.getContentPane().setLayout(new CardLayout(0, 0));	
		frmKniffelig.getContentPane().add(Controller.scContainer, "container");
	}
}