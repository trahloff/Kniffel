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
import kniff.Main;
import kniff.Dice;
import kniff.KniffButton;
import kniff.KniffPanel;
import kniff.Player;
import kniff.Sheet;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextField;

public class ScGame extends Screen
{
	private static final long serialVersionUID = 1L;

	private KniffButton btnEnd;
	
	private KniffButton btnRoll;
	private JLabel lblInfolabel, lblRanking;
	private KniffPanel pnSheets, pnDiceContainer;
	private JPanel pnRanking;
	
	public KniffButton getBtnRoll()
	{
		return this.btnRoll;
	}
	
	public ScGame()	
	{
		this.setLayout(null);
		this.setName("game");

		btnEnd = new KniffButton("Ende");
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				Main.scPromt.setBackToScreen(Main.scGame);
				Main.show(Main.scPromt);
			}
		});

		btnEnd.setComponentDesign(EComponentDesign.menuButton);
		btnEnd.setBounds(20, 701, 175, 71);
		this.add(btnEnd);
		
		btnRoll = new KniffButton("W\u00FCrfel rollen");
		btnRoll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (((KniffButton)e.getSource()).isEnabled())
					Main.rollDice();
			}
		});
		btnRoll.setBounds(204, 701, 540, 71);
		btnRoll.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnRoll);
		
		lblInfolabel = new JLabel("Spielinfo");
		lblInfolabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblInfolabel.setBounds(20, 11, 908, 48);
		lblInfolabel.setHorizontalAlignment(SwingConstants.CENTER);		
		this.add(lblInfolabel);
		
		pnSheets = new KniffPanel();
		pnSheets.setBounds(204, 66, 540, 624);
		this.add(pnSheets);
		pnSheets.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Sheet sheet = new Sheet(false);
		sheet.setTitle("Kombinationen");
		sheet.setPreferredSize(new Dimension(150, 677));
		sheet.setEnabled(false);
		sheet.setBounds(19, 70, 175, 620);
		this.add(sheet);
		
		pnDiceContainer = new KniffPanel();
		pnDiceContainer.setBounds(801, 325, 80, 365);
		this.add(pnDiceContainer);
		pnDiceContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 6));
		
		KniffButton knfbtnHilfe = new KniffButton("Ende");
		knfbtnHilfe.setText("Regeln");
		knfbtnHilfe.setComponentDesign(EComponentDesign.menuButton);
		knfbtnHilfe.setBounds(753, 701, 175, 71);
		knfbtnHilfe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Main.show(Main.scHelp);
			}
		});
		add(knfbtnHilfe);
		
		pnRanking = new JPanel();
		pnRanking.setBounds(754, 114, 174, 201);
		add(pnRanking);
		pnRanking.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblRanking = new JLabel();
		lblRanking.setText("Rangliste");
		lblRanking.setBounds(754, 70, 174, 39);
		lblRanking.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setVerticalAlignment(SwingConstants.CENTER);
		add(lblRanking);

	
		initDice();
	}
	
	private void initDice()
	{
		for (Dice d : Main.kniffDice)
			pnDiceContainer.add(d);	
		Dice.rollAll();
	}
	
	private void initSheets()
	{
		pnSheets.removeAll();
		for (Player p : Main.players)
		{
			p.getSheet().setPreferredSize(new Dimension((pnSheets.getWidth() / Main.players.size()) - 5, pnSheets.getHeight() - 10));
			pnSheets.add(p.getSheet());
		}
		this.repaint();
	}
	
	public void writeMessage(String string)
	{
		this.lblInfolabel.setText(string);
	}

	public void init()
	{		
		initSheets();
		initDice();
		initRanking();
		
		Dice.setAllEnabled(true);
		Dice.setAllInitial(true);
		
		btnRoll.setEnabled(true);
		setEnableSheets(false);
	}
	
	public void initRanking()
	{
		clearRanking();
	}
	
	public void enableSheetForPlayer(Player player)
	{
		setEnableSheets(false);
		player.getSheet().setEnabled(true);
	}
	
	public void setEnableSheets(boolean b)
	{
		for (Component s : pnSheets.getComponents())
			s.setEnabled(b);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.repaint();
	}
	
	public void exitPromt()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 0, this.getParent().getHeight(), this.getParent().getWidth());
		add(panel);
		
		
	}

	public void clearRanking()
	{
		pnRanking.removeAll();
	}
	
	public void setRanking(Player[] ranking)
	{
		pnRanking.removeAll();
		for (int i = 0; i < ranking.length; i++)
		{
			Player p = ranking[i];
			KniffButton pPoints = new KniffButton(i+1 + ". - " + p.getName() + " | " + p.getPoints());
			pPoints.setEnabled(false);
			pPoints.setPreferredSize(new Dimension(this.pnRanking.getWidth(), 20));
			pnRanking.add(pPoints);
		}
	}
}
