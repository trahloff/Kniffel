package Screens;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.*;
import kniff.Controller;
import kniff.Design;
import kniff.KniffButton;
import kniff.Player;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import kniff.Dice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScWhoIsFirst extends Screen
{
	private static final long serialVersionUID = 1L;

	
	private static Player currentPlayer;
	private static ArrayList<Player> playerOrder;
	private static Dice dice;
	private static Iterator<Player> ip;
	private static KniffButton knfbtnWrfelRollen;
	
	private JLabel lbTitle, lbSubTitle;
	
	public ScWhoIsFirst()
	{
		setLayout(null);
		
		// Überschrift des Fensters
		this.setName("whoIsFirst");
		
		// Titel
		lbTitle = new JLabel("Wer darf wann?");
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(300, 144, 350, 50);
		add(lbTitle);
		
		// Subtitel
		lbSubTitle = new JLabel("Jetzt würfelt " + currentPlayer);
		lbSubTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lbSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbSubTitle.setBounds(279, 192, 400, 50);
		add(lbSubTitle);	
		
		// Initialisierung des Button um auf das Spielfeld zurück zu kommen
		KniffButton btnZuruck = new KniffButton("Beenden");
		btnZuruck.addMouseListener(new MouseAdapter() // Funktion bei einem Maus Klick
		{
			@Override
			public void mouseClicked(MouseEvent e){
				Controller.show(Controller.scPromt);
			}
		});
		btnZuruck.setBounds(24, 700, 100, 40); // Position des Buttons
		btnZuruck.setComponentDesign(EComponentDesign.menuButton);  // Aussehen des Buttons
		this.add(btnZuruck);	// Hinzufügen des Buttons
		
		knfbtnWrfelRollen = new KniffButton((String) null);
		knfbtnWrfelRollen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				if (ip.hasNext())
				{
					Player p = ip.next();
					p.setPosition(dice.roll());
					playerOrder.add(p);
					nextPlayer();
				}
				else
				{
					orderPlayer();
				}
			}
		});
		knfbtnWrfelRollen.setText("W\u00FCrfel rollen");
		knfbtnWrfelRollen.setBounds(355, 452, 250, 80);
		add(knfbtnWrfelRollen);
		
		init();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	public void init()
	{
		dice = new Dice();
		dice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				dice.setEnabled(true);
			}
		});
		dice.setInitial(true);
		dice.setBounds(408, 276, 150, 150);
		add(dice);	
	}

	private static ArrayList<Player> orderPlayer()
	{
		boolean swapped;
		int end = playerOrder.size() - 1;
		do
		{
			swapped = false;
			for (int i = 0; i < end; i++)
			{
				if (playerOrder.get(i).getPoints() < playerOrder.get(i+1).getPoints())
				{
					Player temp = playerOrder.get(i);
					playerOrder.add(i, playerOrder.get(i+1));
					playerOrder.add(i+1, temp);
					swapped = true;
				}
			}
			end--;
		} while (swapped);

		
//		if (containsEqualPositions)
//		{
//			
//		}
			Controller.startGameScreen();
		
		return playerOrder;
	}
	
	private boolean containsEqualPositions(ArrayList<Player> order)
	{
		ArrayList<Integer> positions = new ArrayList<Integer>();
		
		
		
		return false;
	}
	
	private static void nextPlayer()
	{
		if (ip.hasNext())
		{
			currentPlayer = ip.next();
			dice.setEnabled(true);
			dice.setInitial(true);
			
			knfbtnWrfelRollen.setEnabled(true);
		}
		else
		{
			dice.setInitial(true);
			dice.setEnabled(false);
			
			knfbtnWrfelRollen.setEnabled(false);
		}
	}

	public void startPlayerOrder(ArrayList<Player> players) throws Exception
	{
		if (players.size() == 0)
			throw new Exception("Es gibt keine Spieler deren Startreihenfolge bestimmt werden könnte!");
		if (players.size() == 1)
			return;
		ip = players.iterator();
	}
}
