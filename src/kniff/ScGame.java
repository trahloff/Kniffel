package kniff;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ScGame extends Screen
{
	JButton btnEnd;
	
	private CombiButton btn3oA, btn4oA, btn5oA, btnFul, btnCnc, btnSml, btnBig, btnOne, btnTwo, btnThr, btnFou, btnFiv, btnSix;
	private Dice die1, die2, die3, die4, die5;
	private DesignerButton btnRoll;
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
	
	private MouseAdapter CombiButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof CombiButton)
		             {
		                 CombiButton button = (CombiButton) e.getSource();
		                 if (button.isEnabled())
		                 {
		                	 button.setEnabled(false);  
		                 }
		             }
		     }
	};
	
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
		btnEnd.setBounds(553, 83, 110, 40);
		this.add(btnEnd);
		
		btnOne = new CombiButton(DiceCombination.One);
		btnOne.setToolTipText("1er Augen");
		btnOne.setBounds(114, 88, 100, 30);
		btnOne.addMouseListener(CombiButtonListener);
		this.add(btnOne);
		
		btnTwo = new CombiButton(DiceCombination.Two);
		btnTwo.setToolTipText("2er Augen");
		btnTwo.setBounds(114, 129, 100, 30);
		btnTwo.addMouseListener(CombiButtonListener);
		this.add(btnTwo);
		
		btnThr = new CombiButton(DiceCombination.Thr);
		btnThr.setToolTipText("3er Augen");
		btnThr.setBounds(114, 170, 100, 30);
		btnThr.addMouseListener(CombiButtonListener);
		this.add(btnThr);
		
		btnFou = new CombiButton(DiceCombination.Fou);
		btnFou.setToolTipText("4er Augen");
		btnFou.setBounds(223, 88, 100, 30);
		btnFou.addMouseListener(CombiButtonListener);
		this.add(btnFou);
		
		btnFiv = new CombiButton(DiceCombination.Fiv);
		btnFiv.setToolTipText("5er Augen");
		btnFiv.setBounds(224, 129, 100, 30);
		btnFiv.addMouseListener(CombiButtonListener);
		this.add(btnFiv);
		
		btnSix = new CombiButton(DiceCombination.Six);
		btnSix.setToolTipText("6er Augen");
		btnSix.setBounds(224, 170, 100, 30);
		btnSix.addMouseListener(CombiButtonListener);
		this.add(btnSix);
		
		btnFul = new CombiButton(DiceCombination.FullHouse);
		btnFul.setToolTipText("Full House");
		btnFul.setBounds(114, 289, 100, 30);
		btnFul.addMouseListener(CombiButtonListener);
		this.add(btnFul);
		
		btn3oA = new CombiButton(DiceCombination.ThroA);
		btn3oA.setToolTipText("3er Pasch");
		btn3oA.setBounds(114, 321, 100, 30);
		btn3oA.addMouseListener(CombiButtonListener);
		this.add(btn3oA);
		
		btn4oA = new CombiButton(DiceCombination.FouoA);
		btn4oA.setToolTipText("4er Pasch");
		btn4oA.setBounds(114, 353, 100, 30);
		btn4oA.addMouseListener(CombiButtonListener);
		this.add(btn4oA);
		
		btnSml = new CombiButton(DiceCombination.SmlStr);
		btnSml.setToolTipText("kleine Stra\u00DFe");
		btnSml.setBounds(114, 385, 100, 30);
		btnSml.addMouseListener(CombiButtonListener);
		this.add(btnSml);
		
		btnBig = new CombiButton(DiceCombination.BigStr);
		btnBig.setToolTipText("gro\u00DFe Stra\u00DFe");
		btnBig.setBounds(114, 417, 100, 30);
		btnBig.addMouseListener(CombiButtonListener);
		this.add(btnBig);
		
		btn5oA = new CombiButton(DiceCombination.FivoA);
		btn5oA.setToolTipText("Kniffel");
		btn5oA.setBounds(114, 449, 100, 30);
		btn5oA.addMouseListener(CombiButtonListener);
		this.add(btn5oA);
		
		btnCnc = new CombiButton(DiceCombination.Cnc);
		btnCnc.setToolTipText("Chance");
		btnCnc.setBounds(114, 481, 100, 30);
		btnCnc.addMouseListener(CombiButtonListener);
		this.add(btnCnc);
		
		btnRoll = new DesignerButton("W\u00FCrfel rollen");
		btnRoll.setBounds(114, 639, 540, 71);
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
		die1.setBounds(113, 528, 100, 100);
		die1.setText("1");
		die1.setFont(new Font("OCR A Extended", Font.BOLD, 25));		
		this.add(die1);
		
		die2 = new Dice();
		die2.addMouseListener(DieButtonListener);
		die2.setBounds(223, 528, 100, 100);
		die2.setText("2");
		die2.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		this.add(die2);
		
		die3 = new Dice();
		die3.addMouseListener(DieButtonListener);
		die3.setBounds(333, 528, 100, 100);
		die3.setText("3");
		die3.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		this.add(die3);
		
		die4 = new Dice();
		die4.addMouseListener(DieButtonListener);
		die4.setBounds(443, 528, 100, 100);
		die4.setText("4");
		die4.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		this.add(die4);
		
		die5 = new Dice();
		die5.addMouseListener(DieButtonListener);
		die5.setBounds(553, 528, 100, 100);
		die5.setText("5");
		die5.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		this.add(die5);
		
		Dice.KniffDice[0] = die1;
		Dice.KniffDice[1] = die2;
		Dice.KniffDice[2] = die3;
		Dice.KniffDice[3] = die4;
		Dice.KniffDice[4] = die5;
		
		Dice.rollAll();
	}

	private void initButtons()
	{
		btn3oA.setText("3er Pasch");
		btn4oA.setText("4er Pasch");
		btn5oA.setText("Kniffel");
		btnSml.setText("kleine Straﬂe");
		btnBig.setText("groﬂe Straﬂe");
		btnFul.setText("Full House");
		btnCnc.setText("Chance");
		btnOne.setText("1er Augen");
		btnTwo.setText("2er Augen");
		btnThr.setText("3er Augen");
		btnFou.setText("4er Augen");
		btnFiv.setText("5er Augen");
		btnSix.setText("6er Augen");
	}

	public void writeMessage(String string)
	{
		this.lblInfolabel.setText(string);
	}
}
