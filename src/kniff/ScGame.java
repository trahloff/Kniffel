package kniff;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ScGame extends Screen
{
	JButton btnEnd;
	
	private Dice die1, die2, die3, die4, die5;
	private KniffButton btnRoll;
	private JLabel lblInfolabel;
	
	private MouseAdapter DieButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof Dice)
		             {
		                 Dice button = (Dice) e.getSource();
		                 if (button.isEnabled())
		                	 button.setEnabled(false);
		                 else
		                	 button.setEnabled(true);
		             }
		     }
	};
	private JPanel pnSheets;
	
	public ScGame()	
	{
		this.setBackground(Design.getColor(Colors.bg_dark));
		this.setLayout(null);
		this.setName("game");
		
		
		btnEnd = new JButton("Pause");
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controller.show(Controller.scStart);
			}
		});
		btnEnd.setBounds(32, 768, 79, 71);
		this.add(btnEnd);
		
		btnRoll = new KniffButton("W\u00FCrfel rollen");
		btnRoll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.rollDice();
			}
		});
		btnRoll.setBounds(121, 767, 540, 71);
		btnRoll.bdt = ButtonDesignType.menuButton;
		this.add(btnRoll);
		
		lblInfolabel = new JLabel("");
		lblInfolabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblInfolabel.setBounds(10, 11, 734, 48);
		lblInfolabel.setHorizontalAlignment(SwingConstants.CENTER);		
		this.add(lblInfolabel);
		
		initDice();
	}
	
	private void initDice()
	{
		die1 = new Dice();
		die1.addMouseListener(DieButtonListener);
		die1.setBounds(120, 656, 100, 100);
		die1.setText("1");
		die1.setFont(new Font("OCR A Extended", Font.BOLD, 25));		
		this.add(die1);
		
		die2 = new Dice();
		die2.addMouseListener(DieButtonListener);
		die2.setBounds(230, 656, 100, 100);
		die2.setText("2");
		die2.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		this.add(die2);
		
		die3 = new Dice();
		die3.addMouseListener(DieButtonListener);
		die3.setBounds(340, 656, 100, 100);
		die3.setText("3");
		die3.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		this.add(die3);
		
		die4 = new Dice();
		die4.addMouseListener(DieButtonListener);
		die4.setBounds(450, 656, 100, 100);
		die4.setText("4");
		die4.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		this.add(die4);
		
		die5 = new Dice();
		die5.addMouseListener(DieButtonListener);
		die5.setBounds(560, 656, 100, 100);
		die5.setText("5");
		die5.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		this.add(die5);
		
		Dice.KniffDice[0] = die1;
		Dice.KniffDice[1] = die2;
		Dice.KniffDice[2] = die3;
		Dice.KniffDice[3] = die4;
		Dice.KniffDice[4] = die5;
		
		Dice.rollAll();
		
		initSheets();
		
		pnSheets = new JPanel();
		pnSheets.setBounds(121, 70, 540, 575);
		add(pnSheets);
		pnSheets.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Sheet res = new Sheet();
		res.setPreferredSize(new Dimension(150, pnSheets.getHeight()));
		pnSheets.add(res);
		res.setEnabled(false);
	}

	private void initSheets()
	{
		for (Player p : KniffEngine.Players)
		{
			p.sheet.setPreferredSize(new Dimension((pnSheets.getWidth() / KniffEngine.Players.size()) - 5, pnSheets.getHeight()));
			pnSheets.add(p.sheet);
		}
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
}
