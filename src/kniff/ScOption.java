package kniff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
					Controller.startGame();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart.setBounds(329, 537, 100, 100);
		btnStart.bdt = ButtonDesignType.startButton;
		this.add(btnStart);
		
		btnAdd = new KniffButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addPlayerButton("Spielername");
			}
		});
		btnAdd.setBounds(576, 346, 60, 60);
		btnAdd.bdt = ButtonDesignType.startButton;
		this.add(btnAdd);
		
		btnRmv = new KniffButton("-");
		btnRmv.setBounds(152, 346, 60, 60);
		btnRmv.bdt = ButtonDesignType.startButton;
		this.add(btnRmv);
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
