package screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EComponentDesign;
import kniff.Controller;
import kniff.KniffButton;
import kniff.KniffPanel;
import kniff.Player;

import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JTextField;

public class ScEnd extends Screen{

	private KniffButton btnEnd;
	private KniffButton btnNewGame;
	private KniffButton btnNextGame;
	
	private JLabel lbTitle;
	private KniffPanel pnRanking;
	
	
	public ScEnd()	
	{
		this.setLayout(null);
		this.setName("end");
		
		lbTitle = new JLabel("Spielinfo");
		lbTitle.setText("Spiel Beendet - Herzlichen Glückwunsch");
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lbTitle.setBounds(20, 11, 908, 48);
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);		
		this.add(lbTitle);
		
		pnRanking = new KniffPanel();
		pnRanking.setBounds(371, 104, 174, 201);
		pnRanking.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(pnRanking);
		

		
		btnEnd = new KniffButton("Beenden");
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Controller.stopGame(0);
				Controller.show(Controller.scStart);
			}
		});

		btnEnd.setComponentDesign(EComponentDesign.menuButton);
		btnEnd.setBounds(20, 701, 175, 70);
		this.add(btnEnd);
		
		
		btnNextGame = new KniffButton("Spiel wiederholen");
		btnNextGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					ArrayList<Player> p = new ArrayList<Player>();
					p.addAll(Controller.players);
					
					Controller.stopGame(0);
					Controller.startGame(p);
				} catch (Exception e1) {
					System.err.println("Fehler beim Starten des Spiel mit gleichen Einstellungen!\n> " + e1.getMessage());
					Controller.show(Controller.scStart);
				}
			}
		});
		btnNextGame.setBounds(204, 701, 175, 70);
		btnNextGame.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnNextGame);

		
		btnNewGame = new KniffButton("neues Spiel");
		btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{				
				Controller.stopGame(0);
				Controller.show(Controller.scOption);
			}
		});
		btnNewGame.setComponentDesign(EComponentDesign.menuButton);
		btnNewGame.setBounds(388, 701, 175, 70);
		this.add(btnNewGame);	
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// repositioning in the center of the parent
		if (this.getParent() == null)
			return;
		
		this.lbTitle.setBounds(0, 80, this.getParent().getWidth(), 100);
		
		int halfWidth = this.getParent().getWidth() / 2;
		
		this.pnRanking.setBounds(halfWidth - 200, 160, 400, 320);		
		this.btnNextGame.setBounds(halfWidth - 180 / 2, 510, 180, 50);
		this.btnNewGame.setBounds(halfWidth - 180 / 2, 570, 180, 50);
		this.btnEnd.setBounds(halfWidth - 180 / 2, 630, 180, 50);
		
		fillRanking();
		
	}
	
	private void fillRanking()
	{
		Player[] ranking = Controller.getRanking();
		this.lbTitle.setText("Platz 1. - "+ ranking[0].getName() + " mit " + ranking[0].getPoints() + " Punkten!");
		
		this.pnRanking.removeAll();
		
		for (int i = 1; i < ranking.length; i++)
		{
			KniffButton playerEntry = new KniffButton("Platz " + (i+1) + " - " + ranking[i].getName() + " | " + ranking[i].getPoints());
			playerEntry.setEnabled(true);
			playerEntry.setPreferredSize(new Dimension(this.pnRanking.getWidth() - 10, 40));
			
			pnRanking.add(playerEntry);
		}

		pnRanking.setVisible(false);
		pnRanking.setVisible(true);
	}
}




