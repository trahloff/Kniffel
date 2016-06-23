package kniff;

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
	private KniffButton btnAdd, btnRmv;
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
		pnPlayers.setBounds(229, 220, 280, 365);
		add(pnPlayers);
		pnPlayers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 11, 724, 100);
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		this.add(lbTitle);
		
		lbInfoMessage = new JLabel("Info");
		lbInfoMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfoMessage.setBounds(10, 188, 724, 32);
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
					//players = new Hashtable<KniffButton, Player>();
				} catch (Exception e2)
				{
					
					Controller.show(Controller.scOption);
				}
			}
		});
		//btnStart.setBounds(216, 603, 100, 100);
		btnStart.setComponentDesign(EComponentDesign.startButton);
		this.add(btnStart);
		
		btnAdd = new KniffButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				addPlayer(ScOption.nameValue.getText(), ScOption.kurzValue.getText());
			}
		});
		btnAdd.setBounds(455, 122, 60, 60);
		btnAdd.setComponentDesign(EComponentDesign.startButton);
		this.add(btnAdd);
		
		btnRmv = new KniffButton("-");
		btnRmv.setBounds(159, 353, 60, 60);
		btnRmv.setComponentDesign(EComponentDesign.startButton);
		this.add(btnRmv);
		
		KniffButton btnback = new KniffButton("zurück");
		btnback.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.show(Controller.scStart);   // Leonard Text	
			}
			
		});
		btnback.setBounds(229, 609, 100, 41);
		add(btnback);
		
		pnInput = new JPanel();
		pnInput.setBounds(229, 122, 200, 60);
		JLabel nameLabel = new JLabel ("Name");
		nameLabel.setBounds(0, 0, 0, 0);
		JLabel kurzLabel = new JLabel ("Kürzel");
		kurzLabel.setBounds(0, 0, 0, 0);
		nameLabel.setFont(Design.getFont());
		kurzLabel.setFont(Design.getFont());
		nameValue = new JTextField();
		nameValue.setBounds(10, 25, 130, 30);
		nameValue.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		kurzValue = new JTextField();
		
		kurzValue.setBounds(150, 25, 45, 30);
		kurzValue.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		pnInput.setLayout(null);
		
		pnInput.add(nameLabel);
		pnInput.add(kurzLabel);
		pnInput.add(nameValue);
		pnInput.add(kurzValue);
		add(pnInput);

		btnStart.setBounds(414, 609, 100, 41);
		btnStart.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnStart);
	
		
		
		updateAfterInputChanged();
	}

	//
	private boolean addPlayer(String playerName, String shortName)
	{
		try
		{
			Player p = createPlayer(playerName, shortName);		
			
			KniffButton b = p.getPlayerButton();
			b.setFont(Design.getFont().deriveFont(0, 15));
			b.setPreferredSize(new Dimension(this.pnPlayers.getWidth() - 10, 40));
			
			b.addMouseListener(new MouseAdapter() {
				@Override
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
	public static Player createPlayer(String name, String kurz) throws Exception{
		
		if (ScOption.nameValue.getText() == null || ScOption.kurzValue.getText() == null)
			throw new Exception ("Bitte einen Spielernamen und ein Kürzel eingeben!");

			Enumeration<Player> i = players.elements();
			
			while(i.hasMoreElements())
			{
				Player p = i.nextElement();
				if (ScOption.nameValue.getText().toUpperCase().equals(p.getName().toUpperCase()))
					throw new Exception ("Der Spielername " + nameValue.getText() + " ist bereits vergeben");
				
				if (ScOption.kurzValue.getText().toUpperCase().equals(p.getShortName().toUpperCase()))
					throw new Exception ("Das Kürzel " + kurzValue.getText().toUpperCase() + " ist bereits vergeben");
			}
			
			Player p = new Player(ScOption.nameValue.getText(), ScOption.kurzValue.getText());
			
			ScOption.players.put(p.getPlayerButton(), p);
			ScOption.nameValue.setText("");
			ScOption.kurzValue.setText("");
			
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
		if (players.size() < 1)
		{
			this.err("Kein Spiel ohne Spieler!");
			this.btnStart.setEnabled(false);
		}
		else if (players.size() > 8)
		{
			this.err("Mehr als 8 passen leider nicht an einen Tisch.");
			this.btnStart.setEnabled(false);
		}
		else
			this.btnStart.setEnabled(true);
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
}
