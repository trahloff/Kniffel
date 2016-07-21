package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EColor;
import helper.EComponentDesign;
import kniff.Controller;
import kniff.Design;
import kniff.KniffButton;
import kniff.KniffPanel;
import kniff.Player;
import poi.POI;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class ScRanking extends Screen
{	
	private static final long serialVersionUID = 1L;
	
	private KniffButton btnBack, btnSearch;
	private KniffPanel pnRanking, pnInput;
	private JTextField nameValue;
	
	private JLabel lbTitle, lbInfoMessage;
	
	public ScRanking()
	{
		this.setLayout(null);		
		this.setName("ranking");
		
		// Title-Label
		lbTitle = new JLabel("Punktetabelle");
		lbTitle.setBounds(0, 11, 650, 150);
		lbTitle.setForeground(Color.decode("#666666"));
		lbTitle.setFont(Design.getFont().deriveFont(0, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbTitle);
		
		pnInput = new KniffPanel();
		pnInput.setBounds(235, 115, 270, 95);
		JLabel nameLabel = new JLabel ("Suche nach Spielername");
		nameLabel.setFont(Design.getFont());
		nameLabel.setBounds(5, 0, 200, 30);
		nameValue = new JTextField();
		nameValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					listPlayerByName(nameValue.getText());
			}
			public void keyReleased(KeyEvent arg0)
			{
				listPlayerByName(nameValue.getText());
			}
		});
		nameValue.setHorizontalAlignment(SwingConstants.CENTER);
		nameValue.setBounds(5, 30, pnInput.getWidth() - 10, 40);
		nameValue.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		pnInput.setLayout(null);
		
		pnInput.add(nameLabel);
		pnInput.add(nameValue);
		add(pnInput);

		
		btnBack = new KniffButton("zurück");
		btnBack.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.show(Controller.scStart);   // Leonard Text	
			}
			
		});
		btnBack.setBounds(230, 596, 100, 40);
		add(btnBack);
		
		pnRanking = new KniffPanel();
		pnRanking.setBounds(153, 221, 280, 365);
		add(pnRanking);
		pnRanking.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lbInfoMessage = new JLabel("Info");
		lbInfoMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfoMessage.setBounds(10, 647, 760, 32);
		lbInfoMessage.setFont(Design.getFont());
		this.add(lbInfoMessage);
		
		listPlayerByName("");
	}

	private void listPlayerByName(String name)
	{
		Map<String, Integer> ranking = POI.getAllScores();
		
		Iterator<String> iter = ranking.keySet().iterator();
		if (!iter.hasNext())
		{
			err("Keine Spieler Einträge gefunden! (Es hat wohl noch nie jemand gespielt...)");
			return;
		}
		
		while (iter.hasNext())
		{
			String player = (String) iter.next().toLowerCase();
			if (!player.contains(name.trim().toLowerCase()))
				iter.remove();
		}		

		if (ranking.size() == 0)
			err("Für die Eingabe \"" + nameValue.getText().trim() + "\" wurde kein Spieler gefunden!");
		else
			info("Hier die Spieler");
		fillTable(ranking);
		
	}
	
	private void fillTable(Map<String, Integer> ranking)
	{
		this.pnRanking.removeAll();
		
		Iterator<String> iter = ranking.keySet().iterator();
		while (iter.hasNext())
		{
			String player = (String) iter.next();
			KniffButton playerEntry = new KniffButton(player + " | " + ranking.get(player));
			playerEntry.setEnabled(true);
			playerEntry.setPreferredSize(new Dimension(this.pnRanking.getWidth() - 10, 40));
			playerEntry.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e)
				{
					KniffButton b = ((KniffButton) e.getSource());
					b.setText(POI.highscoreByPlayer(b.getText()); + " Punkte");
				}
				
				public void mouseExited(MouseEvent e)
				{
					KniffButton b = ((KniffButton) e.getSource());
					b.setText();
				}
			});
			
			pnRanking.add(playerEntry);
			pnRanking.add(playerEntry, null, 0);
		}

		pnRanking.setVisible(false);
		pnRanking.setVisible(true);
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
		nameValue.setBounds(5, 30, pnInput.getWidth() - 10, 40);
		this.pnRanking.setBounds(halfWidth - 140, 220, 280, 365);
		this.btnBack.setBounds(halfWidth - 140, 600, 100, 40);
		
		this.lbInfoMessage.setBounds(0, 700, this.getParent().getWidth(), 30);
	}
}
