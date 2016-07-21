package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EColor;
import helper.EComponentDesign;
import kniff.Controller;
import kniff.Design;
import kniff.KniffButton;
import poi.POI;

public class ScStart extends Screen
{	
	private static final long serialVersionUID = 1L;
	public KniffButton btnStart, btnEnd, btnSettings, btnRanking;
	JLabel lbTitle, lbMessage;
	
	public ScStart()
	{
		this.setLayout(null);
		
		this.setName("start");
		
		// Title-Label
		lbTitle = new JLabel("Kniffelig");
		lbTitle.setBounds(0, 100, 650, 150);
		lbTitle.setForeground(Color.decode("#666666"));
		lbTitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 90));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbTitle);
		
		// Message-Label
		lbMessage = new JLabel(getRandomMessage());	
		lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbMessage.setBounds(-20, 708, 724, 71);
		lbMessage.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		this.add(lbMessage);
		
		// Start-Button
		btnStart = new KniffButton("Spielen");
		btnStart.setFont(Design.getFont().deriveFont(0, 25));
		btnStart.setBounds(240, 274, 200, 200);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controller.show(Controller.scOption);
			}
		});
		btnStart.setComponentDesign(EComponentDesign.startButton);
		this.add(btnStart);
		
		// End-Button
		btnEnd = new KniffButton("Beenden");
		btnEnd.setBounds(250, 630, 180, 50);
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnEnd.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnEnd);
		
		btnSettings = new KniffButton("Einstellungen");
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.show(Controller.scSettings);
			}
		});
		btnSettings.setComponentDesign(EComponentDesign.menuButton);
		btnSettings.setBounds(250, 570, 180, 50);
		add(btnSettings);
		
		// Ranking-Button
		btnRanking = new KniffButton("Punktetabelle");
		btnRanking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.show(Controller.scRanking);
			}
		});
		btnRanking.setComponentDesign(EComponentDesign.menuButton);
		btnRanking.setBounds(250, 509, 180, 50);
		add(btnRanking);
	}
		
	
	private String getRandomMessage()
	{
		switch ((int) (Math.random() * 10))
		{
		case 0:
			return "Hallo Du da!";
		case 1:
			return "die Würfel sind gefallen";
		case 2:
			return "wir sind nicht allein";
		case 3:
			return "gute Arbeit, gutes Spiel";
		case 4:
			return "es wird Zeit für ein Spiel";
		case 5:
			return "6er Pasch!!!";
		case 6:
			return "los jetzt!";
		case 7:
			return "Sonnenschein und am PC";
		case 8:
			return "Hier könnte Ihre Werbung stehen!";
		case 9:
			return "Es war ein mal ein Algorithmus.";
		default:
			return "[...]";
		}
	}
	
	public void writeMessage(String s)
	{
		lbMessage.setText(s);
	}
	
	public void writeRandomMessage()
	{
		lbMessage.setText(getRandomMessage());
	}
	
	// Übersteuerung um RandomText auszugeben
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		this.lbMessage.setText(this.getRandomMessage());
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// repositioning in the center of the parent
		if (this.getParent() == null)
			return;
		
		int halfWidth = this.getParent().getWidth() / 2;
		
		this.lbTitle.setBounds(0, 100, this.getParent().getWidth(), 150);
		
		this.btnStart.setBounds(halfWidth - 200 / 2, 250, 200, 200);
		this.btnRanking.setBounds(halfWidth - 180 / 2, 510, 180, 50);
		this.btnSettings.setBounds(halfWidth - 180 / 2, 570, 180, 50);
		this.btnEnd.setBounds(halfWidth - 180 / 2, 630, 180, 50);
		
		this.lbMessage.setBounds(0, 700, this.getParent().getWidth(), 30);
	}
}
