package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EColor;
import helper.EComponentDesign;
import kniff.Main;
import kniff.Design;
import kniff.KniffButton;

public class ScPromt extends Screen
{	
	private static final long serialVersionUID = 1L;
	
	private Screen backTo = Main.scStart;
	private KniffButton btnYes, btnNo;
	private JLabel lbTitle;	
	
	
	public ScPromt()
	{
		this.setLayout(null);		
		this.setName("promt");
		
		// Title-Label
		lbTitle = new JLabel("Soll das Spiel wirklich beendet werden?");
		lbTitle.setBounds(0, 150, 650, 150);
		lbTitle.setForeground(Color.decode("#666666"));
		lbTitle.setFont(Design.getFont().deriveFont(0, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbTitle);
		
		btnYes = new KniffButton("Ja");
		btnYes.setBounds(250, 600, 180, 50);
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.stopGame(1);
			}
		});
		btnYes.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnYes);
		
		
		btnNo = new KniffButton("Nein");
		btnNo.setBounds(450, 600, 180, 50);
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.show(backTo);
			}
		});
		btnNo.setComponentDesign(EComponentDesign.menuButton);
		this.add(btnNo);	
	}

	public Screen getBackToScreen()
	{
		return this.backTo;
	}
	
	public void setBackToScreen(Screen backTo)
	{
		this.backTo = backTo;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// repositioning in the center of the parent
		if (this.getParent() == null)
			return;
		
		this.lbTitle.setBounds(0, 100, this.getParent().getWidth(), 100);
		
		int halfWidth = this.getParent().getWidth() / 2;
		
		this.btnYes.setBounds(halfWidth - 220 , 400, 120, 50);	
		this.btnNo.setBounds(halfWidth + 120, 400, 120, 50);
	}
}
