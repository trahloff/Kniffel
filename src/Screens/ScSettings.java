package Screens;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.*;
import kniff.Controller;
import kniff.Design;
import kniff.KniffButton;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScSettings extends Screen
{
	private static final long serialVersionUID = 1L;

	private JLabel lbTitle, lbInfoA;
	private final JComboBox<EColorScheme> cBoxColor;
	private KniffButton btnApply, btnBack;
	
	public ScSettings()
	{
		setLayout(null);
		
		this.setName("settings");
		
		lbTitle = new JLabel("Einstellungen");
		lbTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(300, 120, 350, 50);
		add(lbTitle);
		
		cBoxColor = new JComboBox<EColorScheme>();
		cBoxColor.setModel(new DefaultComboBoxModel<EColorScheme>(EColorScheme.values()));
		cBoxColor.setSelectedIndex(0);
		cBoxColor.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		cBoxColor.setBounds(350, 340, 200, 35);
		add(cBoxColor);
		
		for (int i = 0; i < this.cBoxColor.getItemCount(); i++) {
			if (this.cBoxColor.getItemAt(i) == Design.getColorScheme()){
				this.cBoxColor.setSelectedIndex(i);
				break;
			}
		}
		
		lbInfoA = new JLabel("Auswahl des Farbschemas");
		lbInfoA.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lbInfoA.setBounds(350, 300, 250, 30);
		add(lbInfoA);
		
		btnBack = new KniffButton("zur\u00FCck");
		btnBack.setComponentDesign(EComponentDesign.menuButton);
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Controller.show(Controller.scStart);
			}
		});
		btnBack.setBounds(350, 600, 120, 40);
		add(btnBack);
		
		btnApply = new KniffButton("anwenden");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Design.setColorScheme((EColorScheme)cBoxColor.getSelectedItem());
			}
		});
		btnApply.setComponentDesign(EComponentDesign.menuButton);
		btnApply.setBounds(490, 600, 120, 40);
		add(btnApply);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// repositioning in the center of the parent
		if (this.getParent() == null)
			return;
		
		int halfWidth = this.getParent().getWidth() / 2;
		
		this.lbTitle.setBounds(0, 130, this.getParent().getWidth(), 150);
		this.lbInfoA.setBounds(halfWidth - 260 / 2, 360, 260, 20);
		this.cBoxColor.setBounds(halfWidth - 260 / 2, 400, 260, 50);
		this.btnApply.setBounds(halfWidth - 180 / 2, 570, 180, 50);
		this.btnBack.setBounds(halfWidth - 180 / 2, 630, 180, 50);		
	}
}
