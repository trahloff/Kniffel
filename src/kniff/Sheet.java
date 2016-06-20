package kniff;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

public class Sheet extends JPanel
{
	private CombiButton btn3oA, btn4oA, btn5oA, btnFul, btnCnc, btnSml, btnBig, btnOne, btnTwo, btnThr, btnFou, btnFiv, btnSix;
	private JLabel lbSumUp, lbBonus, lbSumUpAll, lbSumDown, lbSumUpAllValue, lbSumAll;
	private JPanel content;
	private JLabel playerName;
	
	public Sheet(boolean cleared)
	{
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		content = new JPanel();
		content.setSize(311, 600);
		content.setLocation(0, 50);
		
		add(content);
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnOne = new CombiButton(DiceCombination.One);
		btnOne.setToolTipText("1er Augen");
		btnOne.addMouseListener(CombiButtonListener);
		content.add(btnOne);
		
		btnTwo = new CombiButton(DiceCombination.Two);
		btnTwo.setToolTipText("2er Augen");
		btnTwo.setBounds(114, 129, 100, 30);
		btnTwo.addMouseListener(CombiButtonListener);
		content.add(btnTwo);
		
		btnThr = new CombiButton(DiceCombination.Thr);
		btnThr.setToolTipText("3er Augen");
		btnThr.setBounds(114, 170, 100, 30);
		btnThr.addMouseListener(CombiButtonListener);
		content.add(btnThr);
		
		btnFou = new CombiButton(DiceCombination.Fou);
		btnFou.setToolTipText("4er Augen");
		btnFou.setBounds(223, 88, 100, 30);
		btnFou.addMouseListener(CombiButtonListener);
		content.add(btnFou);
		
		btnFiv = new CombiButton(DiceCombination.Fiv);
		btnFiv.setToolTipText("5er Augen");
		btnFiv.setBounds(224, 129, 100, 30);
		btnFiv.addMouseListener(CombiButtonListener);
		content.add(btnFiv);
		
		btnSix = new CombiButton(DiceCombination.Six);
		btnSix.setToolTipText("6er Augen");
		btnSix.setBounds(224, 170, 100, 30);
		btnSix.addMouseListener(CombiButtonListener);
		content.add(btnSix);
		
		lbSumUp = new JLabel("oben");
		lbSumUp.setHorizontalAlignment(SwingConstants.CENTER);
		lbSumUp.setToolTipText("Summe des oberen Teils (ohne Bonus)");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbSumUp);
		
		lbBonus = new JLabel("bonus");
		lbBonus.setHorizontalAlignment(SwingConstants.CENTER);
		lbBonus.setToolTipText("35 Bonuspunkte wenn +75 Punkte im oberen Teil");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbBonus);
		
		lbSumUpAll = new JLabel("oben gesamt");
		lbSumUpAll.setHorizontalAlignment(SwingConstants.CENTER);
		lbSumUpAll.setToolTipText("Summe des oberen Teils");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbSumUpAll);
		
		btnFul = new CombiButton(DiceCombination.FullHouse);
		btnFul.setToolTipText("Full House");
		btnFul.setBounds(114, 289, 100, 30);
		btnFul.addMouseListener(CombiButtonListener);
		content.add(btnFul);
		
		btn3oA = new CombiButton(DiceCombination.ThroA);
		btn3oA.setToolTipText("3er Pasch");
		btn3oA.setBounds(114, 321, 100, 30);
		btn3oA.addMouseListener(CombiButtonListener);
		content.add(btn3oA);
		
		btn4oA = new CombiButton(DiceCombination.FouoA);
		btn4oA.setToolTipText("4er Pasch");
		btn4oA.setBounds(114, 353, 100, 30);
		btn4oA.addMouseListener(CombiButtonListener);
		content.add(btn4oA);
		
		btnSml = new CombiButton(DiceCombination.SmlStr);
		btnSml.setToolTipText("kleine Stra\u00DFe");
		btnSml.setBounds(114, 385, 100, 30);
		btnSml.addMouseListener(CombiButtonListener);
		content.add(btnSml);
		
		btnBig = new CombiButton(DiceCombination.BigStr);
		btnBig.setToolTipText("gro\u00DFe Stra\u00DFe");
		btnBig.setBounds(114, 417, 100, 30);
		btnBig.addMouseListener(CombiButtonListener);
		content.add(btnBig);
		
		btn5oA = new CombiButton(DiceCombination.FivoA);
		btn5oA.setToolTipText("Kniffel");
		btn5oA.setBounds(114, 449, 100, 30);
		btn5oA.addMouseListener(CombiButtonListener);
		content.add(btn5oA);
		
		btnCnc = new CombiButton(DiceCombination.Cnc);
		btnCnc.setToolTipText("Chance");
		btnCnc.setBounds(114, 481, 100, 30);
		btnCnc.addMouseListener(CombiButtonListener);
		content.add(btnCnc);
		
		lbSumDown = new JLabel("unterer Teil");
		lbSumDown.setHorizontalAlignment(SwingConstants.CENTER);
		lbSumDown.setToolTipText("Summe des unteren Teils");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbSumDown);
		
		lbSumUpAllValue = new JLabel("oberer Teil Gesamt");
		lbSumUpAllValue.setHorizontalAlignment(SwingConstants.CENTER);
		lbSumUpAllValue.setBounds(224, 170, 100, 20);
		content.add(lbSumUpAllValue);
		
		lbSumAll = new JLabel("gesamt");
		lbSumAll.setHorizontalAlignment(SwingConstants.CENTER);
		lbSumAll.setToolTipText("Summe des gesamten Spiels");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbSumAll);
		
		playerName = new JLabel("...");
		playerName.setBounds(10, 10, 290, 30);
		playerName.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setVerticalAlignment(SwingConstants.CENTER);
		
		add(playerName);
		initButtons();
		if (cleared)
			vanish();
	}
	
	private void initButtons()
	{
		btn3oA.setText("3er Pasch");
		btn4oA.setText("4er Pasch");
		btn5oA.setText("Kniffel");
		btnSml.setText("kleine Straße");
		btnBig.setText("große Straße");
		btnFul.setText("Full House");
		btnCnc.setText("Chance");
		btnOne.setText("1er Augen");
		btnTwo.setText("2er Augen");
		btnThr.setText("3er Augen");
		btnFou.setText("4er Augen");
		btnFiv.setText("5er Augen");
		btnSix.setText("6er Augen");
	}
	
	private MouseAdapter CombiButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof CombiButton)
		             {
		                 CombiButton button = (CombiButton) e.getSource();
		                 if (button.isEnabled())
		                 {
		                	 button.kill();
		                	 updateSheetValues(Controller.kniffDice);
		                	 Controller.nextPlayer();
		                 }
		             }
		     }
	};

	public void setEnabled(boolean b)
	{
		for (Component c : content.getComponents())
			c.setEnabled(b);
	}
	
	// lbSumUp, lbBonus, lbSumUpAll, lbSumDown, lbSumUpAllValue, lbSumAll
	// btn3oA, btn4oA, btn5oA, btnFul, btnCnc, btnSml, btnBig, btnOne, btnTwo, btnThr, btnFou, btnFiv, btnSix
	public void updateSheetValues(Dice[] combination)
	{
		for (Component c : content.getComponents())
			if (c.getClass().equals(CombiButton.class))
			{
				CombiButton b = (CombiButton) c;
				if(!b.isKilled())
					b.setText("" + KniffSheet.calcPoints(b.getLinkedCombination(), Dice.getSortedValues(combination)));
			}
		
		// oberer Teil
		int sumUp = btnOne.value + btnTwo.value + btnThr.value + btnFou.value + btnFiv.value + btnSix.value;
		int bonus = 0;
		int sumDown = btn3oA.value + btn4oA.value + btn5oA.value + btnFul.value + btnCnc.value + btnSml.value + btnBig.value;
		
		this.lbSumUp.setText(sumUp + "");
		if (sumUp >= 63)
			bonus = 35;
		this.lbBonus.setText(bonus + "");
		this.lbSumUpAll.setText(sumUp + bonus + "");
		
		// unterer Teil
		this.lbSumDown.setText(sumDown + "");
		this.lbSumUpAllValue.setText(this.lbSumUpAll.getText());
		this.lbSumAll.setText(sumDown + sumUp + bonus + "");
	}
	
	public void paintComponent(Graphics g)
	{
		playerName.setBounds(10, 10, this.getWidth(), 30);
		content.setBounds(0, 40, this.getWidth(), this.getHeight() - 50);

		for (Component c : content.getComponents())
			c.setPreferredSize(new Dimension(content.getWidth(), 30));
	}

	public void setTitle(String shortName)
	{
		this.playerName.setText(shortName);
	}

	public void vanish()
	{
		for (Component c : content.getComponents())
			if (c.getClass().equals(CombiButton.class))
			{
				CombiButton b = (CombiButton) c;
				if (!b.isKilled())
					b.setText("");
			}
	}
}
