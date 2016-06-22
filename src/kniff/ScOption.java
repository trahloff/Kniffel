package kniff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.FlowLayout;

public class ScOption extends Screen
{
	private JLabel lbTitle = new JLabel("Spieleinstellungen");
	private JPanel pnPlayers;
	private JPanel addPlayer;
	KniffButton btnStart = new KniffButton("Start");
	KniffButton btnAdd, btnRmv;
	static JTextField nameValue;
	static JTextField kurzValue;
	
	
	
	public static ArrayList<Player> spielerListe = new ArrayList<Player>();

	public ScOption()
	{
		this.setLayout(null);
		this.setName("option");
		
		pnPlayers = new JPanel();
		pnPlayers.setBackground(Color.GRAY);
		pnPlayers.setBounds(249, 267, 286, 328);
		add(pnPlayers);
		pnPlayers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 11, 724, 100);
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		this.add(lbTitle);
		
		
		addPlayer = new JPanel();
		addPlayer.setBackground(Color.WHITE);
		addPlayer.setBounds(249, 147, 286, 100);
		addPlayer.setLayout(new GridLayout(2,2));
		JLabel nameLabel = new JLabel ("Name");
		JLabel kurzLabel = new JLabel ("Kürzel");
		nameValue = new JTextField();
		kurzValue = new JTextField();
		
		addPlayer.add(nameLabel);
		addPlayer.add(kurzLabel);
		addPlayer.add(nameValue);
		addPlayer.add(kurzValue);
		add(addPlayer);
		
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				Controller.startGame();
			}
		});
		btnStart.setBounds(329, 637, 100, 100);
		btnStart.bdt = ButtonDesignType.startButton;
		this.add(btnStart);
				
		btnAdd = new KniffButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
				try{
				ScOption.createPlayer(ScOption.nameValue.getText(), ScOption.kurzValue.getText());
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			//	addPlayerButton("Spielername");
				
			
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
	
	public static void createPlayer(String name, String kurz) throws Exception{
		
		if (ScOption.nameValue.getText() == null || ScOption.kurzValue.getText() == null){
			throw new Exception ("Bitte einen Spielernamen und ein Kürzel eingeben!");
		} else{
			Iterator<Player> i = spielerListe.iterator();
			
			while(i.hasNext()){
				Player tmp = i.next();
				if (ScOption.nameValue.getText() == tmp.name){
					
				}
			}
			
			ScOption.spielerListe.add(new Player(ScOption.nameValue.getText(), ScOption.kurzValue.getText()));
			ScOption.nameValue.setText("");
			ScOption.kurzValue.setText("");
		}
	}
	
}
