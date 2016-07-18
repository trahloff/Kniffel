package Screens;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.*;
import kniff.Controller;
import kniff.KniffButton;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

public class ScHelp extends Screen
{
	private static final long serialVersionUID = 1L;

	public ScHelp()
	{
		setLayout(null);
		
		this.setName("Spielregeln");
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(ScHelp.class.getResource("/Bilder/Anleitung.JPG")));
		lblNewLabel.setBounds(24, -52, 950, 820);
		add(lblNewLabel);
		
//		KniffButton knfbtnzuruck = new KniffButton("Zurück");
//		knfbtnZuruck.setText("Zurück");
//		knfbtnZuruck.setComponentDesign(EComponentDesign.menuButton);
//		knfbtnZuruck.setBounds(753, 701, 175, 71);
//		knfbtnZuruck.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e)
//			{
//				Controller.show(Controller.scGame);
//			}
//		});
//		btnZurck.setBounds(801, 753, 89, 23);
//		add(btnZurck);
//		
		
		KniffButton btnStart = new KniffButton("Zurück");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Controller.show(Controller.scGame);
				
//				try
//				{
//					if (!btnStart.isEnabled())
//						return;
//					
//					ArrayList<Player> plrs = new ArrayList<Player>();
//					Enumeration<Player> i = players.elements();
//					while(i.hasMoreElements())
//						plrs.add(i.nextElement());
//					Controller.startGame(plrs);
//				} catch (Exception e2)
//				{
//					
//					Controller.show(Controller.scOption);
//				}
			}
		});
		btnStart.setBounds(24, 700, 100, 40);
		btnStart.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnStart);
		
//		setLayout(null);
//		
//		this.setName("settings");
//		
//		JLabel lblNewLabel = new JLabel("Einstellungen");
//		lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setBounds(10, 10, 750, 50);
//		add(lblNewLabel);
//		
//		final JComboBox<EColorScheme> cBoxColor = new JComboBox<EColorScheme>();
//		cBoxColor.setModel(new DefaultComboBoxModel<EColorScheme>(EColorScheme.values()));
//		cBoxColor.setSelectedIndex(0);
//		cBoxColor.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
//		cBoxColor.setBounds(290, 151, 200, 35);
//		add(cBoxColor);
//		
//		JLabel lbInfoA = new JLabel("Auswahl des Farbschemas");
//		lbInfoA.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
//		lbInfoA.setBounds(240, 110, 250, 30);
//		add(lbInfoA);
//		
//		KniffButton btnBack = new KniffButton("zur\u00FCck");
//		btnBack.setComponentDesign(EComponentDesign.menuButton);
//		btnBack.addMouseListener(new MouseAdapter()
//		{
//			@Override
//			public void mouseClicked(MouseEvent e)
//			{
//				Controller.show(Controller.scStart);
//			}
//		});
//		btnBack.setBounds(240, 450, 120, 40);
//		add(btnBack);
//		
//		KniffButton btnAnwenden = new KniffButton("anwenden");
//		btnAnwenden.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e)
//			{
//				Design.setColorScheme((EColorScheme)cBoxColor.getSelectedItem());
//			}
//		});
//		btnAnwenden.setComponentDesign(EComponentDesign.menuButton);
//		btnAnwenden.setBounds(370, 450, 120, 40);
//		add(btnAnwenden);
//		
//		JComboBox<EDesign> cBoxDesign = new JComboBox<EDesign>();
//		cBoxDesign.setModel(new DefaultComboBoxModel<EDesign>(EDesign.values()));
//		cBoxDesign.setSelectedIndex(0);
//		cBoxDesign.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
//		cBoxDesign.setBounds(290, 238, 200, 35);
//		add(cBoxDesign);
//		
//		JLabel lblAuswahlDesDesignschemas = new JLabel("Auswahl des Designschemas");
//		lblAuswahlDesDesignschemas.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
//		lblAuswahlDesDesignschemas.setBounds(240, 197, 250, 30);
//		add(lblAuswahlDesDesignschemas);
//		
////		JLabel lblAuswahlDerSchrift = new JLabel("Auswahl der Schriftart");
////		lblAuswahlDerSchrift.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
////		lblAuswahlDerSchrift.setBounds(240, 284, 250, 30);
////		add(lblAuswahlDerSchrift);
////		
////		JComboBox<EDesign> comboBox = new JComboBox<EDesign>();
////		comboBox.setSelectedIndex(0);
////		comboBox.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
////		comboBox.setBounds(290, 325, 200, 35);
////		add(comboBox);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
