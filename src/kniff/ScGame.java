package kniff;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EComponentDesign;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;

public class ScGame extends Screen
{
	private static final long serialVersionUID = 1L;

	private KniffButton btnEnd;
	
	private KniffButton btnRoll;
	private JLabel lblInfolabel;
	private KniffPanel pnSheets;
	private KniffPanel pnDiceContainer;
	private JLabel lblSollDasSpiel;
	
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
			public void mouseClicked(MouseEvent arg0) {
				Controller.stopGame(1);
			}
		});

		btnEnd.setComponentDesign(EComponentDesign.menuButton);
		btnEnd.setBounds(20, 742, 175, 30);
		this.add(btnEnd);
		
		btnRoll = new KniffButton("W\u00FCrfel rollen");
		btnRoll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (((KniffButton)e.getSource()).isEnabled())
					Controller.rollDice();
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
		pnDiceContainer.setBounds(754, 575, 175, 115);
		this.add(pnDiceContainer);
		pnDiceContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		KniffButton knfbtnHilfe = new KniffButton("Ende");
		knfbtnHilfe.setText("Hilfe");
		knfbtnHilfe.setComponentDesign(EComponentDesign.menuButton);
		knfbtnHilfe.setBounds(20, 701, 175, 30);
		add(knfbtnHilfe);
		
		KniffButton knfbtnRangliste = new KniffButton("Ende");
		knfbtnRangliste.setText("Rangliste");
		knfbtnRangliste.setComponentDesign(EComponentDesign.menuButton);
		knfbtnRangliste.setBounds(754, 701, 175, 30);
		add(knfbtnRangliste);
		
		KniffPanel kniffPanel = new KniffPanel();
		kniffPanel.setBounds(754, 66, 174, 498);
		add(kniffPanel);
		kniffPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	
		initDice();
	}
	
	private void initDice()
	{
		for (Dice d : Controller.kniffDice)
			pnDiceContainer.add(d);	
		Dice.rollAll();
	}
	
	private void initSheets()
	{
		pnSheets.removeAll();
		for (Player p : Controller.players)
		{
			p.getSheet().setPreferredSize(new Dimension((pnSheets.getWidth() / Controller.players.size()) - 5, pnSheets.getHeight() - 10));
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
		
		Dice.setAllEnabled(true);
		Dice.setAllInitial(true);
		
		btnRoll.setEnabled(true);
		setEnableSheets(false);
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
}
