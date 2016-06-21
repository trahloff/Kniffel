package kniff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class ScOption extends Screen
{
	private JLabel lbTitle = new JLabel("Spieleinstellungen");
	private JPanel pnPlayers;
	KniffButton btnStart = new KniffButton("Start");
	KniffButton btnAdd, btnRmv;
	private static TreeSet<Player> players = new TreeSet<Player>();
	
	public ScOption()
	{
		this.setLayout(null);
		this.setName("option");
		
		pnPlayers = new JPanel();
		pnPlayers.setBackground(Color.GRAY);
		pnPlayers.setBounds(249, 167, 286, 328);
		add(pnPlayers);
		pnPlayers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 11, 724, 100);
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		this.add(lbTitle);
		
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
						players.clear();
						players.add(new Player("Anna", "AnA"));
//						players.add(new Player("Barbara", "B$L"));
//						players.add(new Player("Charlie", "Cha"));
//						players.add(new Player("Dennis", "God"));
//						players.add(new Player("Eduard", "Edu"));
//						players.add(new Player("Frederike", "Frd"));
//						players.add(new Player("Galadriel", "Gal"));
//						players.add(new Player("Henrik", "Hrk"));
					
					Controller.startGame(players);
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart.setBounds(343, 512, 100, 100);
		btnStart.bdt = ButtonDesignType.startButton;
		this.add(btnStart);
		
		btnAdd = new KniffButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addPlayerButton("Spielername");
			}
		});
		btnAdd.setBounds(453, 532, 60, 60);
		btnAdd.bdt = ButtonDesignType.startButton;
		this.add(btnAdd);
		
		btnRmv = new KniffButton("-");
		btnRmv.setBounds(273, 532, 60, 60);
		btnRmv.bdt = ButtonDesignType.startButton;
		this.add(btnRmv);
		
		KniffButton btnback = new KniffButton("Start");
		btnback.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.show(Controller.scStart);   // Leonard Text
				
			}
			
		});
		btnback.setText("zur\u00FCck");
		btnback.setBounds(52, 619, 100, 41);
		add(btnback);
	}
	private int i = 0;
	private void addPlayerButton(String player)
	{
		i++;
		final KniffButton b = new KniffButton(player + i);
		b.bdt = ButtonDesignType.menuButton;
		b.setPreferredSize(new Dimension(pnPlayers.getWidth(), 50));
		pnPlayers.add(b);
		
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				pnPlayers.remove(b);
			}
		});
	}
}
