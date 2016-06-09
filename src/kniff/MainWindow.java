package kniff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class MainWindow
{
	private JFrame frmKniffelig;
	private JButton btn3oA, btn4oA, btn5oA, btnFul, btnCnc, btnSml, btnBig, btnOne, btnTwo, btnThr, btnFou, btnFiv, btnSix;
	private JButton btnRoll;
	private Die die1, die2, die3, die4, die5;
	private Player p = new Player("");
	
	private boolean phase1 = false;
	
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
		     }
	};
	private JPanel gameScreen;
	private JLabel label;
	private JLabel lblKniffelig;
	private JPanel menuScreen;
	private JButton button_1;
	
	private void initDice()
	{
		die1 = new Die();
		die1.addMouseListener(DieButtonListener);
		die1.setBounds(83, 481, 100, 100);
		die1.setText("1");
		die1.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		
		gameScreen.add(die1);
		
		die2 = new Die();
		die2.addMouseListener(DieButtonListener);
		die2.setBounds(193, 481, 100, 100);
		die2.setText("2");
		die2.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		gameScreen.add(die2);
		
		die3 = new Die();
		die3.addMouseListener(DieButtonListener);
		die3.setBounds(303, 481, 100, 100);
		die3.setText("3");
		die3.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		gameScreen.add(die3);
		
		die4 = new Die();
		die4.addMouseListener(DieButtonListener);
		die4.setBounds(413, 481, 100, 100);
		die4.setText("4");
		die4.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		gameScreen.add(die4);
		
		die5 = new Die();
		die5.addMouseListener(DieButtonListener);
		die5.setBounds(523, 481, 100, 100);
		die5.setText("5");
		die5.setFont(new Font("Gill Sans MT", Font.BOLD, 25));
		gameScreen.add(die5);
		
		Die.KniffDice[0] = die1;
		Die.KniffDice[1] = die2;
		Die.KniffDice[2] = die3;
		Die.KniffDice[3] = die4;
		Die.KniffDice[4] = die5;
		
		JButton btnBeenden = new JButton("x");
		btnBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setScreen(menuScreen);
			}
		});
		btnBeenden.setBounds(671, 660, 63, 40);
		gameScreen.add(btnBeenden);
		
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
		btn3oA.setText(0 + "");
		btn4oA.setText(0 + "");
		btn5oA.setText(0 + "");
		btnSml.setText(0 + "");
		btnBig.setText(0 + "");
		btnFul.setText(0 + "");
		btnCnc.setText(0 + "");
		btnOne.setText(0 + "");
		btnTwo.setText(0 + "");
		btnThr.setText(0 + "");
		btnFou.setText(0 + "");
		btnFiv.setText(0 + "");
		btnSix.setText(0 + "");
	}

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
		frmKniffelig = new JFrame();
		frmKniffelig.setFont(new Font("Agency FB", Font.PLAIN, 20));
		frmKniffelig.setTitle("Kniffelig");
		frmKniffelig.setBounds(100, 100, 760, 750);
		frmKniffelig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frmKniffelig.getContentPane().setLayout(new CardLayout(0, 0));
		
		gameScreen = new JPanel();
		frmKniffelig.getContentPane().add(gameScreen, "gameScreen");
		gameScreen.setLayout(null);
		
		btnOne = new JButton("einer");
		btnOne.setBounds(304, 125, 100, 30);
		gameScreen.add(btnOne);
		
		btnTwo = new JButton("zweier");
		btnTwo.setBounds(304, 166, 100, 30);
		gameScreen.add(btnTwo);
		
		btnThr = new JButton("dreier");
		btnThr.setBounds(304, 207, 100, 30);
		gameScreen.add(btnThr);
		
		btnFou = new JButton("vierer");
		btnFou.setBounds(414, 125, 100, 30);
		gameScreen.add(btnFou);
		
		btnFiv = new JButton("f\u00FCnfer");
		btnFiv.setBounds(414, 166, 100, 30);
		gameScreen.add(btnFiv);
		
		btnSix = new JButton("sechser");
		btnSix.setBounds(414, 207, 100, 30);
		gameScreen.add(btnSix);
		
		btnFul = new JButton("Full House");
		btnFul.setBounds(524, 125, 100, 30);
		gameScreen.add(btnFul);
		
		btn3oA = new JButton("3er Pasch");
		btn3oA.setBounds(524, 166, 100, 30);
		gameScreen.add(btn3oA);
		
		btn4oA = new JButton("4er Pasch");
		btn4oA.setBounds(524, 207, 100, 30);
		gameScreen.add(btn4oA);
		
		btnSml = new JButton("kleine Stra\u00DFe");
		btnSml.setBounds(634, 166, 100, 30);
		gameScreen.add(btnSml);
		
		btnBig = new JButton("gro\u00DFe Stra\u00DFe");
		btnBig.setBounds(634, 207, 100, 30);
		gameScreen.add(btnBig);
		
		btn5oA = new JButton("Kniffel");
		btn5oA.setBounds(634, 125, 100, 30);
		gameScreen.add(btn5oA);
		
		btnCnc = new JButton("chance");
		btnCnc.setBounds(524, 248, 210, 30);
		gameScreen.add(btnCnc);
		
		btnRoll = new JButton("W\u00FCrfel rollen");
		btnRoll.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0)
			{
				initButtons();		
				Die.rollAll();
				
				updateButtonForPlayer(p);
			}
		});
		btnRoll.setBounds(83, 592, 540, 60);
		gameScreen.add(btnRoll);
		
		label = new JLabel("Mr. Snuggels ist an der Reihe");
		label.setBounds(0, 663, 744, 48);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		gameScreen.add(label);
		
		menuScreen = new JPanel();
		frmKniffelig.getContentPane().add(menuScreen, "menuScreen");
		menuScreen.setLayout(null);
		
		lblKniffelig = new JLabel("Kniffelig");
		lblKniffelig.setBounds(10, 11, 724, 75);
		lblKniffelig.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		lblKniffelig.setHorizontalAlignment(SwingConstants.CENTER);
		menuScreen.add(lblKniffelig);
		
		button_1 = new JButton(">");
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				setScreen(gameScreen);
			}
		});
		button_1.setBounds(671, 660, 63, 40);
		menuScreen.add(button_1);
		frmKniffelig.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{menuScreen, gameScreen}));
		
		initDice();
		initButtons();
	}
	
	private void updateButtonForPlayer(Player p)
	{
		ArrayList<DiceCombination> combis = p.currentKniffSheet.getFixedCombinations();
		
		btn3oA.setText(0 + "");
		btn4oA.setText(0 + "");
		btn5oA.setText(0 + "");
		btnSml.setText(0 + "");
		btnBig.setText(0 + "");
		btnFul.setText(0 + "");
		btnCnc.setText(0 + "");
		btnOne.setText(0 + "");
		btnTwo.setText(0 + "");
		btnThr.setText(0 + "");
		btnFou.setText(0 + "");
		btnFiv.setText(0 + "");
		btnSix.setText(0 + "");
		
		if (combis.contains(DiceCombination.ThroA))
		{
			btn3oA.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.ThroA) + "");
		}
		if (combis.contains(DiceCombination.FouoA))
		{
			btn4oA.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.FouoA) + "");
		}
		if (combis.contains(DiceCombination.FivoA))
		{
			btn5oA.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.FivoA) + "");
	}
		if (combis.contains(DiceCombination.SmlStr))
		{
			btnSml.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.SmlStr) + "");
		}
		if (combis.contains(DiceCombination.BigStr))
		{
			btnBig.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.BigStr) + "");
		}
		if (combis.contains(DiceCombination.FullHouse))
		{
			btnFul.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.FullHouse) + "");
		}
		if (combis.contains(DiceCombination.Cnc))
		{
			btnCnc.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.Cnc) + "");
		}
		if (combis.contains(DiceCombination.One))
		{
			btnOne.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.One) + "");
		}
		if (combis.contains(DiceCombination.Two))
		{
			btnTwo.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.Two) + "");
		}
		if (combis.contains(DiceCombination.Thr))
		{
			btnThr.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.ThroA) + "");
		}
		if (combis.contains(DiceCombination.Fou))
		{
			btnFou.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.Fou) + "");
		}
		if (combis.contains(DiceCombination.Fiv))
		{
			btnFiv.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.Fiv) + "");
		}
		if (combis.contains(DiceCombination.Six))
		{
			btnSix.setEnabled(false);
			btn3oA.setText(p.currentKniffSheet.getPoints(DiceCombination.Six) + "");
		}
		
	}
}