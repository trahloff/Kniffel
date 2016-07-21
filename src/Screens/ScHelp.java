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
		
		// Überschrift des Fensters
		this.setName("Spielregeln");
		
		// Einfügen des Bilds mit der Spielanleitung
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(ScHelp.class.getResource("/Bilder/Anleitung.JPG"))); // Bild File
		lblNewLabel.setBounds(24, -52, 950, 820); // Position des Bildes
		add(lblNewLabel);	
		
		// Initialisierung des Button um auf das Spielfeld zurück zu kommen
		KniffButton btnZuruck = new KniffButton("Zurück");
		btnZuruck.addMouseListener(new MouseAdapter() // Funktion bei einem Maus Klick
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Controller.show(Controller.scGame);
				
			}
		});
		btnZuruck.setBounds(24, 700, 100, 40); // Position des Buttons
		btnZuruck.setComponentDesign(EComponentDesign.menuButton);  // Aussehen des Buttons
		this.add(btnZuruck);	// Hinzufügen des Buttons

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
