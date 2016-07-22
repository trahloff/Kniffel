package Screens;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.*;
import kniff.Controller;
import kniff.Design;
import kniff.KniffButton;
import kniff.KniffPanel;
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
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import kniff.Dice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScWhoIsFirst extends Screen
{
	private static final long serialVersionUID = 1L;

	
	private static Player currentPlayer;
	private static ArrayList<Player> playerOrder = new ArrayList<Player>();
	private static Dice dice1, dice2;
	private static Iterator<Player> ip;
	private static KniffButton btnRoll;
	private static KniffButton btnBack, btnStart;
	
	private static JLabel lbTitle, lbSubTitle;
	private static KniffPanel pnPositioning;
	
	public ScWhoIsFirst()
	{
		setLayout(null);
		this.setName("whoIsFirst");
		
		// Title-Label
		lbTitle = new JLabel("Festlegung der Spielerreihenfolge");
		lbTitle.setBounds(0, 11, 650, 150);
		lbTitle.setForeground(Color.decode("#666666"));
		lbTitle.setFont(Design.getFont().deriveFont(0, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbTitle);
		
		// Subtitel
		lbSubTitle = new JLabel("Jetzt würfelt " + currentPlayer);
		lbSubTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lbSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbSubTitle.setBounds(280, 130, 400, 50);
		add(lbSubTitle);	
		
		// Positionstabelle
		pnPositioning = new KniffPanel();
		pnPositioning.setBounds(339, 162, 280, 279);
		add(pnPositioning);
		pnPositioning.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// zurück Button
		btnBack = new KniffButton("Beenden");
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e){
				Controller.scPromt.setBackToScreen(Controller.scWhoIsFirst);
				Controller.show(Controller.scPromt);
			}
		});
		btnBack.setBounds(24, 700, 100, 40);
		btnBack.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnBack);
		
		// Start Button
		btnStart = new KniffButton("Los");
		btnStart.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e){
				if (!btnStart.isEnabled())
					return;
				Controller.startGameScreen();
			}
		});
		btnStart.setBounds(24, 700, 100, 40);
		btnStart.setComponentDesign(EComponentDesign.menuButton);
		btnStart.setEnabled(false);
		this.add(btnStart);
		
		// Dice Roll Button
		btnRoll = new KniffButton((String) null);
		btnRoll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{	
				if (!btnRoll.isEnabled()) 
					return;
				
				// Füge den aktuellen Spieler zur Positionsliste hinzu.
				// Die Sortierung ist durch die Augenzahl definiert
				currentPlayer.setPosition(dice1.roll() + dice2.roll());
				playerOrder.add(currentPlayer);
				orderPlayer();
				fillTable(playerOrder);
				
				if (ip.hasNext())			
					nextPlayer();
				else
				{
					
					btnRoll.setEnabled(false);
					btnStart.setEnabled(true);
					
					lbSubTitle.setText("Die Reihenfolge steht fest!");
					
					// Sendet die fertige Reihenfolge an den Controller
					Controller.players.clear();
					Controller.players.addAll(playerOrder);
				}
					
			}
		});
		btnRoll.setText("W\u00FCrfel rollen");
		btnRoll.setBounds(355, 452, 250, 80);
		add(btnRoll);
		
		// Würfel links
		dice1 = new Dice();
		dice1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				dice1.setEnabled(true);
			}
		});
		dice1.setInitial(true);
		dice1.setBounds(636, 274, 150, 150);
		add(dice1);
		
		// Würfel rechts
		dice2 = new Dice();
		dice2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				dice2.setEnabled(true);
			}
		});
		dice2.setInitial(true);
		dice2.setBounds(636, 274, 150, 150);
		add(dice2);
	}
	
	// intialisiert alle relevanten Felder und Elemente
	public void init()
	{
		dice1.setEnabled(true);
		dice2.setEnabled(true);
		btnRoll.setEnabled(true);
		btnStart.setEnabled(false);
		
		lbSubTitle.setText("Jetzt würfelt " + currentPlayer.getName());
		pnPositioning.removeAll();
	}
	
	// sortiert die Spieler nach ihrer Position
	private static void orderPlayer()
	{
		boolean swapped;
		int end = playerOrder.size() - 1;
		do
		{
			swapped = false;
			for (int i = 0; i < end; i++)
			{
				if (playerOrder.get(i).getPosition() < playerOrder.get(i+1).getPosition())
				{
					Player temp = playerOrder.get(i);
					playerOrder.remove(i);
					playerOrder.add(i+1, temp);
					swapped = true;
				}
			}
			end--;
		} while (swapped);
	}
	
	
	private static void nextPlayer()
	{
		if (ip.hasNext())
		{
			dice1.setEnabled(true);
			dice2.setEnabled(true);
			btnRoll.setEnabled(true);
			btnStart.setEnabled(false);
			currentPlayer = ip.next();
			lbSubTitle.setText("Jetzt würfelt " + currentPlayer.getName());
		}
		else
			lbSubTitle.setText("Alle Spieler haben gewürfelt.");
	}

	public void startPlayerOrder(ArrayList<Player> players) throws Exception
	{
		Controller.show(Controller.scWhoIsFirst);
		if (players.size() == 0)
			throw new Exception("Es gibt keine Spieler deren Startreihenfolge bestimmt werden könnte!");
		if (players.size() == 1)
			Controller.startGameScreen();
		playerOrder.clear();

		try
		{
			ip = players.iterator();
			if (!ip.hasNext())
				Controller.startGameScreen();
			currentPlayer = ip.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.init();
	}
	
	private static void fillTable(ArrayList<Player> order)
	{
		pnPositioning.removeAll();
		
		for (Player player : order)
		{
			KniffButton playerEntry = new KniffButton(player.getName() + " hat eine " + player.getPosition() + " gewürfelt.");
			playerEntry.setEnabled(true);
			playerEntry.setPreferredSize(new Dimension(pnPositioning.getWidth() - 10, 35));

			pnPositioning.add(playerEntry);
		}
		
		pnPositioning.setVisible(false);
		pnPositioning.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// repositioning in the center of the parent
		if (this.getParent() == null)
			return;
		int halfWidth = this.getParent().getWidth() / 2;
		
		this.lbTitle.setBounds(0, 20, this.getParent().getWidth(), 100);
		this.lbSubTitle.setBounds(0, 150, this.getParent().getWidth(), 30);
		this.dice1.setBounds(halfWidth - 350, 250, 150, 150);
		this.dice2.setBounds(halfWidth + 200, 250, 150, 150);
		this.btnRoll.setBounds(halfWidth - 150, 540, 300, 50);
		this.btnBack.setBounds(halfWidth - 150, 600, 100, 50);
		this.btnStart.setBounds(halfWidth + 50, 600, 100, 50);
		
		this.pnPositioning.setBounds(halfWidth - 150, 190, 300, 325);
	}
}
