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

public class ScGame extends Screen
{
	private static final long serialVersionUID = 1L;

	private KniffButton btnEnd;
	
	private KniffButton btnRoll;
	private JLabel lblInfolabel;
	private KniffPanel pnSheets;
	private JPanel pnDiceContainer;
	
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
		btnEnd.setBounds(20, 742, 174, 30);
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
		lblInfolabel.setBounds(20, 11, 734, 48);
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
		sheet.setBounds(20, 70, 174, 620);
		this.add(sheet);
		
		pnDiceContainer = new JPanel();
		pnDiceContainer.setBounds(758, 144, 100, 576);
		this.add(pnDiceContainer);
		pnDiceContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		KniffButton knfbtnHilfe = new KniffButton("Ende");
		knfbtnHilfe.setText("Hilfe");
		knfbtnHilfe.setComponentDesign(EComponentDesign.menuButton);
		knfbtnHilfe.setBounds(20, 701, 174, 30);
		add(knfbtnHilfe);
	
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
}
