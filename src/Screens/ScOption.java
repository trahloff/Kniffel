package Screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.poi.sl.draw.geom.IfElseExpression;

import helper.EColor;
import helper.EComponentDesign;
import kniff.Controller;
import kniff.Design;
import kniff.KniffButton;
import kniff.KniffPanel;
import kniff.Player;

import java.awt.FlowLayout;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

public class ScOption extends Screen
{
	private static final long serialVersionUID = 1L;
	
	private JLabel lbTitle = new JLabel("Spieleinstellungen");
	private KniffPanel pnPlayers;
	private JLabel lbInfoMessage;
	private KniffButton btnStart;
	private KniffButton btnBack;
	private KniffButton btnAdd;
	private static Dictionary<KniffButton, Player> players = new Hashtable<KniffButton, Player>();
	private JPanel pnInput;
	private static JTextField nameValue;
	private static JTextField kurzValue;
	
	public ScOption()
	{
		this.setLayout(null);
		this.setName("option");
		
		pnPlayers = new KniffPanel();
		pnPlayers.setBackground(Color.GRAY);
		pnPlayers.setBounds(230, 220, 280, 365);
		add(pnPlayers);
		pnPlayers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		pnInput = new KniffPanel();
		pnInput.setBounds(235, 115, 270, 95);
		JLabel nameLabel = new JLabel ("Spielername");
		nameLabel.setFont(Design.getFont());
		nameLabel.setBounds(5, 0, 200, 30);
		nameValue = new JTextField();
		nameValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					if (btnAdd.isEnabled())
						addPlayer(ScOption.nameValue.getText());
			}
		});
		nameValue.setHorizontalAlignment(SwingConstants.CENTER);
		nameValue.setBounds(5, 30, 210, 40);
		nameValue.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		pnInput.setLayout(null);
		
		pnInput.add(nameLabel);
		pnInput.add(nameValue);
		add(pnInput);
		
		btnAdd = new KniffButton("+");
		btnAdd.setBounds(220, 30, 55, 40);
		pnInput.add(btnAdd);
		btnAdd.setFont(btnAdd.getFont().deriveFont(12f));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (btnAdd.isEnabled())
					addPlayer(ScOption.nameValue.getText());
			}
		});
		btnAdd.setComponentDesign(EComponentDesign.menuButton);

		
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 11, 760, 100);
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		this.add(lbTitle);
		
		lbInfoMessage = new JLabel("Info");
		lbInfoMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfoMessage.setBounds(10, 647, 760, 32);
		lbInfoMessage.setFont(Design.getFont());
		this.add(lbInfoMessage);
		
		btnStart = new KniffButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					if (!btnStart.isEnabled())
						return;
					
					ArrayList<Player> plrs = new ArrayList<Player>();
					Enumeration<Player> i = players.elements();
					while(i.hasMoreElements())
						plrs.add(i.nextElement());
					Controller.startGame(plrs);
				} catch (Exception e2)
				{
					
					Controller.show(Controller.scOption);
				}
			}
		});
		//btnStart.setBounds(216, 603, 100, 100);
		btnStart.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnStart);
		
		btnBack = new KniffButton("zurück");
		btnBack.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.show(Controller.scStart);   // Leonard Text	
			}
			
		});
		btnBack.setBounds(230, 596, 100, 40);
		add(btnBack);
		
		btnStart.setBounds(410, 596, 100, 40);
		btnStart.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnStart);		
		
		updateAfterInputChanged();
	}

	//
	private boolean addPlayer(String playerName)
	{
		try
		{
			Player p = createPlayer(playerName);		
			
			KniffButton b = p.getPlayerButton();
			b.setFont(Design.getFont().deriveFont(0, 15));
			b.setPreferredSize(new Dimension(this.pnPlayers.getWidth() - 10, 40));
			
			b.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e)
				{
					KniffButton b = ((KniffButton) e.getSource());
					b.setText("löschen");
				}
				
				public void mouseExited(MouseEvent e)
				{
					KniffButton b = ((KniffButton) e.getSource());
					Player p = players.get(b);
					b.setText(p.getFullName());
				}
				
				public void mouseClicked(MouseEvent e)
				{
					KniffButton b = ((KniffButton) e.getSource());
					players.remove(b);
					pnPlayers.remove(b);
					
					updateAfterInputChanged();
					pnPlayers.setVisible(false);
					pnPlayers.setVisible(true);
				}
			});
			
			pnPlayers.add(b);
			
			this.lbInfoMessage.setForeground(Design.getColor(EColor.accent_a_light));
			
			updateAfterInputChanged();
			pnPlayers.setVisible(false);
			pnPlayers.setVisible(true);
		}
		catch (Exception e)
		{
			this.err(e.getMessage());
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	//
	public static Player createPlayer(String name) throws Exception{
		
		if (ScOption.nameValue.getText().trim().equals(""))
			throw new Exception ("Bitte einen Spielernamen eingeben!");

			Enumeration<Player> i = players.elements();
			
			while(i.hasMoreElements())
			{
				Player p = i.nextElement();
				if (ScOption.nameValue.getText().trim().toUpperCase().equals(p.getName().trim().toUpperCase()))
					throw new Exception ("Der Spielername " + nameValue.getText().trim() + " ist bereits vergeben");
			}
			
			String nameShort = "";
			if (ScOption.nameValue.getText().trim().length() < 3)
				nameShort = ScOption.nameValue.getText().trim().substring(0, ScOption.nameValue.getText().trim().length());
			else
				nameShort = ScOption.nameValue.getText().trim().substring(0, 3);
				
			Player p = new Player(ScOption.nameValue.getText().trim(), nameShort);
			
			ScOption.players.put(p.getPlayerButton(), p);
			ScOption.nameValue.setText("");
			
			return p;
	}
	
	private Player nameExists(String n)
	{
		Enumeration<Player> i = players.elements();
		while(i.hasMoreElements())
		{
			Player p = i.nextElement();
			if (n.toUpperCase().equals(p.getName().toUpperCase()))
				return p;
		}
		return null;
	}
	
	private Player shortExists(String s)
	{
		Enumeration<Player> i = players.elements();
		while(i.hasMoreElements())
		{
			Player p = i.nextElement();
			if (s.toUpperCase().equals(p.getShortName().toUpperCase()))
				return p;
		}
		return null;
	}
	
	private void updateAfterInputChanged()
	{
		this.btnStart.setEnabled(false);
		if (players.size() < 1)
			this.err("Kein Spiel ohne Spieler!");
		else if (players.size() > 8)
			this.err("Mehr als 8 passen leider nicht an einen Tisch.");
		else
		{
			this.info("Jetzt kann es los gehen...");
			this.btnStart.setEnabled(true);
		}
		
		this.btnAdd.setEnabled(players.size() < 8);
	}
	
	private void warn(String s)
	{
		this.lbInfoMessage.setForeground(Design.getColor(EColor.warn_text));
		this.lbInfoMessage.setText(s);
	}
	
	private void err(String s)
	{
		this.lbInfoMessage.setForeground(Design.getColor(EColor.error_text));
		this.lbInfoMessage.setText(s);
	}
	
	private void info(String s)
	{
		this.lbInfoMessage.setForeground(Design.getColor(EColor.accent_a_light));
		this.lbInfoMessage.setText(s);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// repositioning in the center of the parent
		if (this.getParent() == null)
			return;
		
		int halfWidth = this.getParent().getWidth() / 2;
		
		this.lbTitle.setBounds(0, 20, this.getParent().getWidth(), 100);
		
		this.pnInput.setBounds(halfWidth - 140, 120, 280, 75);
		this.pnPlayers.setBounds(halfWidth - 140, 220, 280, 365);
		this.btnStart.setBounds(halfWidth + 140 - this.btnStart.getWidth(), 600, 100, 40);
		this.btnBack.setBounds(halfWidth - 140, 600, 100, 40);
		
		this.lbInfoMessage.setBounds(0, 700, this.getParent().getWidth(), 30);
	}
}
