package kniff;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ScGame extends Screen
{
	KniffButton btnEnd;
	
	private KniffButton btnRoll;
	private JLabel lblInfolabel;
	private JPanel pnSheets;
	private JPanel pnDiceContainer;
	
	public ScGame()	
	{
		this.setLayout(null);
		this.setName("game");
		
		
		btnEnd = new KniffButton("Pause");
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controller.show(Controller.scStart);
			}
		});
		btnEnd.bdt = ButtonDesignType.menuButton;
		btnEnd.setBounds(32, 768, 162, 71);
		this.add(btnEnd);
		
		btnRoll = new KniffButton("W\u00FCrfel rollen");
		btnRoll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.rollDice();
			}
		});
		btnRoll.setBounds(204, 767, 540, 71);
		btnRoll.bdt = ButtonDesignType.menuButton;
		this.add(btnRoll);
		
		lblInfolabel = new JLabel("Spielinfo");
		lblInfolabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblInfolabel.setBounds(10, 11, 734, 48);
		lblInfolabel.setHorizontalAlignment(SwingConstants.CENTER);		
		this.add(lblInfolabel);
		
		pnSheets = new JPanel();
		pnSheets.setBounds(204, 70, 540, 576);
		this.add(pnSheets);
		pnSheets.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Sheet sheet = new Sheet();
		sheet.setPreferredSize(new Dimension(150, 677));
		sheet.setEnabled(false);
		sheet.setBounds(20, 70, 174, 687);
		this.add(sheet);
		
		pnDiceContainer = new JPanel();
		pnDiceContainer.setBounds(204, 657, 540, 99);
		this.add(pnDiceContainer);
		pnDiceContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		initSheets();	
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
		for (Player p : Controller.players)
		{
			p.sheet.setPreferredSize(new Dimension((pnSheets.getWidth() / Controller.players.size()) - 5, pnSheets.getHeight()));
			pnSheets.add(p.sheet);
		}
		this.repaint();
	}
	
	public void writeMessage(String string)
	{
		this.lblInfolabel.setText(string);
	}

	public void enableSheetForPlayer(Player currentPlayer)
	{
		for (Component s : pnSheets.getComponents())
			((Sheet) s).setEnabled(false);
		currentPlayer.sheet.setEnabled(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		initSheets();
	}
}