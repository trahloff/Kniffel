package kniff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import helper.EComponentDesign;

import java.awt.FlowLayout;

public class ScOption extends Screen
{
	private JLabel lbTitle = new JLabel("Spieleinstellungen");
	private JPanel pnPlayers;
	KniffButton btnStart = new KniffButton("Start");
	KniffButton btnAdd, btnRmv;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private JPanel pnInput;
	static JTextField nameValue;
	static JTextField kurzValue;
	
	public ScOption()
	{
		this.setLayout(null);
		this.setName("option");
		
		pnPlayers = new JPanel();
		pnPlayers.setBackground(Color.GRAY);
		pnPlayers.setBounds(249, 225, 286, 270);
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
						Controller.startGame(players);
						players.clear();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart.setBounds(343, 512, 100, 100);
		btnStart.setComponentDesign(EComponentDesign.startButton);
		this.add(btnStart);
		
		btnAdd = new KniffButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addPlayer(ScOption.nameValue.getText(), ScOption.kurzValue.getText());
			}
		});
		btnAdd.setBounds(453, 532, 60, 60);
		btnAdd.setComponentDesign(EComponentDesign.startButton);
		this.add(btnAdd);
		
		btnRmv = new KniffButton("-");
		btnRmv.setBounds(273, 532, 60, 60);
		btnRmv.setComponentDesign(EComponentDesign.startButton);
		this.add(btnRmv);
		
		KniffButton btnback = new KniffButton("Start");
		btnback.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.show(Controller.scStart);   // Leonard Text	
			}
			
		});
		btnback.setText("Zur\u00FCck");
		btnback.setBounds(52, 619, 100, 41);
		add(btnback);
		
		pnInput = new JPanel();
		pnInput.setBackground(Color.WHITE);
		pnInput.setBounds(249, 147, 286, 67);
		pnInput.setLayout(new GridLayout(2,2));
		JLabel nameLabel = new JLabel ("Name");
		JLabel kurzLabel = new JLabel ("Kürzel");
		nameValue = new JTextField();
		kurzValue = new JTextField();
		
		pnInput.add(nameLabel);
		pnInput.add(kurzLabel);
		pnInput.add(nameValue);
		pnInput.add(kurzValue);
		add(pnInput);

		btnStart.setBounds(329, 637, 100, 100);
		btnStart.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnStart);
	}

	//
	private void addPlayer(String playerName, String shortName)
	{
		try
		{
			createPlayer(playerName, shortName);
			final KniffButton b = new KniffButton(playerName + " [" + shortName + "]");
			b.setComponentDesign(EComponentDesign.menuButton);
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
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	public static void createPlayer(String name, String kurz) throws Exception{
		
		if (ScOption.nameValue.getText() == null || ScOption.kurzValue.getText() == null)
			throw new Exception ("Bitte einen Spielernamen und ein Kürzel eingeben!");

			Iterator<Player> i = players.iterator();
			
			while(i.hasNext())
			{
				Player tmp = i.next();
				if (ScOption.nameValue.getText().equals(tmp.getName()))
					throw new Exception ("Der Spielername ist bereits vergeben");
				
				if (ScOption.kurzValue.getText().equals(tmp.getShortName()))
					throw new Exception ("Das Kürzel ist bereits vergeben");
				
			}
			
			ScOption.players.add(new Player(ScOption.nameValue.getText(), ScOption.kurzValue.getText()));
			ScOption.nameValue.setText("");
			ScOption.kurzValue.setText("");	
	}
}
