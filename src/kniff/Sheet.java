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
	private CombiButton btnThroA;
	private JPanel content;
	private JLabel playerName;
	
	public Sheet()
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
		
		JLabel lbSumUp = new JLabel("oben");
		lbSumUp.setToolTipText("Summe des oberen Teils (ohne Bonus)");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbSumUp);
		
		JLabel lbBonus = new JLabel("bonus");
		lbBonus.setToolTipText("35 Bonuspunkte wenn +75 Punkte im oberen Teil");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbBonus);
		
		JLabel lbSumUpAll = new JLabel("oben gesamt");
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
		
		JLabel lbSumDown = new JLabel("unterer Teil");
		lbSumDown.setToolTipText("Summe des unteren Teils");
		lbSumUp.setPreferredSize(new Dimension(100, 20));
		content.add(lbSumDown);
		
		JLabel lbSumUpAllValue = new JLabel("oberer Teil Gesamt");
		lbSumUpAllValue.setBounds(224, 170, 100, 20);
		content.add(lbSumUpAllValue);
		
		JLabel lbSumAll = new JLabel("gesamt");
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
	
	private MouseAdapter CombiButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof CombiButton)
		             {
		                 CombiButton button = (CombiButton) e.getSource();
		                 if (button.isEnabled())
		                 {
		                	 Controller.nextPlayer();
		                	 button.setEnabled(false);
		                 }
		             }
		     }
	};

	public void setEnabled(boolean b)
	{
		for (Component c : content.getComponents())
			c.setEnabled(b);
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
}
