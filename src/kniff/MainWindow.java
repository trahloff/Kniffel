package kniff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;

public class MainWindow
{
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow();
					window.frmKniffelig.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		initGameScreen();
		initMenuScreen();
		initGameOptionScreen();
		
		initDice();
		initButtons();
		initEngine();
		setScreen(gameScreen);
	}
	
	//
	//
	// ---------------------------------------------------------------------------------------------------------------------
	//
	//
	
	
	private JFrame frmKniffelig;
	private CombiButton btn3oA, btn4oA, btn5oA, btnFul, btnCnc, btnSml, btnBig, btnOne, btnTwo, btnThr, btnFou, btnFiv, btnSix;
	private JButton btnRoll;
	private Player p;
	private Die die1, die2, die3, die4, die5;
	private final int maxRollAmount = 3;
	private int rolled = 1;
	
	private MouseAdapter DieButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof Die)
		             {
		                 Die button = (Die) e.getSource();
		                 if (button.isEnabled())
		                	 button.setEnabled(false);
		                 else
		                	 button.setEnabled(true);
		             }
		         	if (rolled < maxRollAmount)
		         		btnRoll.setEnabled(!Die.allDeactivated());
		         	
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
		                	 p.currentKniffSheet.fixCombination(button.getLinkedCombination(), Die.KniffDice);
		                	 nextPlayer();	 
		                 }
		             }
		     }
	};
	
	private JPanel gameScreen, menuScreen, gameOptionScreen;
	private JLabel lblInfolabel;
	private JLabel lblKniffelig;
	private JLabel lblSpieleinstellungen;
	
	private void initDice()
	{
		die1 = new Die();
		die1.addMouseListener(DieButtonListener);
		die1.setBounds(113, 528, 100, 100);
		die1.setText("1");
		die1.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		
		gameScreen.add(die1);
		
		die2 = new Die();
		die2.addMouseListener(DieButtonListener);
		die2.setBounds(223, 528, 100, 100);
		die2.setText("2");
		die2.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		gameScreen.add(die2);
		
		die3 = new Die();
		die3.addMouseListener(DieButtonListener);
		die3.setBounds(333, 528, 100, 100);
		die3.setText("3");
		die3.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		gameScreen.add(die3);
		
		die4 = new Die();
		die4.addMouseListener(DieButtonListener);
		die4.setBounds(443, 528, 100, 100);
		die4.setText("4");
		die4.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		gameScreen.add(die4);
		
		die5 = new Die();
		die5.addMouseListener(DieButtonListener);
		die5.setBounds(553, 528, 100, 100);
		die5.setText("5");
		die5.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		gameScreen.add(die5);
		
		Die.KniffDice[0] = die1;
		Die.KniffDice[1] = die2;
		Die.KniffDice[2] = die3;
		Die.KniffDice[3] = die4;
		Die.KniffDice[4] = die5;
		
		Die.rollAll();
	}

	protected void setScreen(JPanel screen)
	{
		gameScreen.setVisible(false);
		menuScreen.setVisible(false);
		
		screen.setVisible(true);
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

	private void initEngine()
	{
		KniffEngine.initEngine();
		p = KniffEngine.nextPlayer();
		lblInfolabel.setText(p.name + " beginnt...");
	}
	
	private void initGameScreen()
	{
		frmKniffelig = new JFrame();
		frmKniffelig.setResizable(false);
		frmKniffelig.setFont(new Font("Agency FB", Font.PLAIN, 20));
		frmKniffelig.setTitle("Kniffelig");
		frmKniffelig.setBounds(100, 100, 760, 750);
		frmKniffelig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frmKniffelig.getContentPane().setLayout(new CardLayout(0, 0));
		
		gameScreen = new JPanel();
		gameScreen.setBackground(Color.decode("#EEEEEE"));
		frmKniffelig.getContentPane().add(gameScreen, "gameScreen");
		gameScreen.setLayout(null);
		
		
		JButton btnBeenden = new JButton("Pause");
		btnBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setScreen(menuScreen);
			}
		});
		btnBeenden.setBounds(553, 83, 110, 40);
		gameScreen.add(btnBeenden);
		
		btnOne = new CombiButton(DiceCombination.One);
		btnOne.setToolTipText("1er Augen");
		btnOne.setBounds(114, 88, 100, 30);
		btnOne.addMouseListener(CombiButtonListener);
		gameScreen.add(btnOne);
		
		btnTwo = new CombiButton(DiceCombination.Two);
		btnTwo.setToolTipText("2er Augen");
		btnTwo.setBounds(114, 129, 100, 30);
		btnTwo.addMouseListener(CombiButtonListener);
		gameScreen.add(btnTwo);
		
		btnThr = new CombiButton(DiceCombination.Thr);
		btnThr.setToolTipText("3er Augen");
		btnThr.setBounds(114, 170, 100, 30);
		btnThr.addMouseListener(CombiButtonListener);
		gameScreen.add(btnThr);
		
		btnFou = new CombiButton(DiceCombination.Fou);
		btnFou.setToolTipText("4er Augen");
		btnFou.setBounds(223, 88, 100, 30);
		btnFou.addMouseListener(CombiButtonListener);
		gameScreen.add(btnFou);
		
		btnFiv = new CombiButton(DiceCombination.Fiv);
		btnFiv.setToolTipText("5er Augen");
		btnFiv.setBounds(224, 129, 100, 30);
		btnFiv.addMouseListener(CombiButtonListener);
		gameScreen.add(btnFiv);
		
		btnSix = new CombiButton(DiceCombination.Six);
		btnSix.setToolTipText("6er Augen");
		btnSix.setBounds(224, 170, 100, 30);
		btnSix.addMouseListener(CombiButtonListener);
		gameScreen.add(btnSix);
		
		btnFul = new CombiButton(DiceCombination.FullHouse);
		btnFul.setToolTipText("Full House");
		btnFul.setBounds(114, 289, 100, 30);
		btnFul.addMouseListener(CombiButtonListener);
		gameScreen.add(btnFul);
		
		btn3oA = new CombiButton(DiceCombination.ThroA);
		btn3oA.setToolTipText("3er Pasch");
		btn3oA.setBounds(114, 321, 100, 30);
		btn3oA.addMouseListener(CombiButtonListener);
		gameScreen.add(btn3oA);
		
		btn4oA = new CombiButton(DiceCombination.FouoA);
		btn4oA.setToolTipText("4er Pasch");
		btn4oA.setBounds(114, 353, 100, 30);
		btn4oA.addMouseListener(CombiButtonListener);
		gameScreen.add(btn4oA);
		
		btnSml = new CombiButton(DiceCombination.SmlStr);
		btnSml.setToolTipText("kleine Stra\u00DFe");
		btnSml.setBounds(114, 385, 100, 30);
		btnSml.addMouseListener(CombiButtonListener);
		gameScreen.add(btnSml);
		
		btnBig = new CombiButton(DiceCombination.BigStr);
		btnBig.setToolTipText("gro\u00DFe Stra\u00DFe");
		btnBig.setBounds(114, 417, 100, 30);
		btnBig.addMouseListener(CombiButtonListener);
		gameScreen.add(btnBig);
		
		btn5oA = new CombiButton(DiceCombination.FivoA);
		btn5oA.setToolTipText("Kniffel");
		btn5oA.setBounds(114, 449, 100, 30);
		btn5oA.addMouseListener(CombiButtonListener);
		gameScreen.add(btn5oA);
		
		btnCnc = new CombiButton(DiceCombination.Cnc);
		btnCnc.setToolTipText("Chance");
		btnCnc.setBounds(114, 481, 100, 30);
		btnCnc.addMouseListener(CombiButtonListener);
		gameScreen.add(btnCnc);
		
		btnRoll = new JButton("W\u00FCrfel rollen");
		btnRoll.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0)
			{
				if (btnRoll.isEnabled())
				{
					Die.rollAll();
					updateButtonForPlayer(p);
					rolled++;
					if (rolled > maxRollAmount)
						btnRoll.setEnabled(false);
				}
			}
		});
		btnRoll.setBounds(114, 639, 540, 71);
		gameScreen.add(btnRoll);
		
		lblInfolabel = new JLabel("");
		lblInfolabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblInfolabel.setBounds(10, 11, 734, 48);
		lblInfolabel.setHorizontalAlignment(SwingConstants.CENTER);		
		gameScreen.add(lblInfolabel);
	}
	
	private void initMenuScreen()
	{
		menuScreen = new JPanel();
		menuScreen.setBackground(Color.WHITE);
		frmKniffelig.getContentPane().add(menuScreen, "menuScreen");
		menuScreen.setLayout(null);
		
		lblKniffelig = new JLabel("Kniffelig");
		lblKniffelig.setBounds(10, 156, 724, 157);
		lblKniffelig.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 90));
		lblKniffelig.setHorizontalAlignment(SwingConstants.CENTER);
		menuScreen.add(lblKniffelig);
		
		DesignerButton btnNewButton = new DesignerButton("Spielen", "gameStartButton");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setScreen(gameOptionScreen);
			}
		});
		btnNewButton.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnNewButton.setBounds(295, 332, 150, 150);
		menuScreen.add(btnNewButton);
		
		DesignerButton btnBeenden_1 = new DesignerButton("Beenden", "menuButton");
		btnBeenden_1.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnBeenden_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnBeenden_1.setBounds(295, 555, 150, 50);
		menuScreen.add(btnBeenden_1);
		
		JLabel lblHalloText = new JLabel("hallo Text");
		lblHalloText.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblHalloText.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalloText.setBounds(10, 629, 724, 71);
		menuScreen.add(lblHalloText);
	}
	
	private void initGameOptionScreen()
	{
		gameOptionScreen = new JPanel();
		gameOptionScreen.setBackground(Color.WHITE);
		frmKniffelig.getContentPane().add(gameOptionScreen, "gameOptionScreen");
		gameOptionScreen.setLayout(null);
		
		lblSpieleinstellungen = new JLabel("Spieleinstellungen");
		lblSpieleinstellungen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpieleinstellungen.setBounds(10, 11, 724, 100);
		lblSpieleinstellungen.setFont(new Font("Ebrima", Font.PLAIN, 40));
		gameOptionScreen.add(lblSpieleinstellungen);
		
		JButton btnStarten = new JButton("Starten");
		btnStarten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KniffEngine.initEngine();
				setScreen(gameScreen);
			}
		});
		btnStarten.setBounds(292, 594, 150, 50);
		gameOptionScreen.add(btnStarten);
		
		JButton btnSpielerHinzufgen = new JButton("Spieler hinzuf\u00FCgen");
		btnSpielerHinzufgen.setBounds(422, 346, 120, 30);
		gameOptionScreen.add(btnSpielerHinzufgen);
		
		JButton btnSpielerEntfernen = new JButton("Spieler entfernen");
		btnSpielerEntfernen.setBounds(292, 346, 120, 30);
		gameOptionScreen.add(btnSpielerEntfernen);
	}
	
	private Player nextPlayer()
	{
		rolled = 1;
		btnRoll.setEnabled(true);
		p = KniffEngine.nextPlayer();
		updateButtonForPlayer(p);
		
		lblInfolabel.setText(p.name + " ist an der Reihe.");
		
		return p;
	}
	
	private void updateButtonForPlayer(Player p)
	{
		ArrayList<DiceCombination> combis = p.currentKniffSheet.getFixedCombinations();
		Enumeration<CombiButton> iCombiButton = CombiButton.combiButtons.elements();
		CombiButton button;
		
		
		while(iCombiButton.hasMoreElements())
		{
			button = iCombiButton.nextElement();
			button.setEnabled(true);
			button.setText("" + KniffSheet.calcPoints(button.getLinkedCombination(), Die.getSortedValues(Die.KniffDice)));
		}
		
		for (DiceCombination combi : combis)
		{
			button = CombiButton.combiButtons.get(combi);
			button.setEnabled(false);
			button.setText(p.currentKniffSheet.getPoints(combi) + "");
		}
		
		
	}
}