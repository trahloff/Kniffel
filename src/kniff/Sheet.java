package kniff;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EComponentDesign;
import helper.EDiceCombination;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

public class Sheet extends JPanel
{
	private JPanel content;
	private JLabel title;
	private ArrayList<CombiButton> combinations = new ArrayList<CombiButton>();
	
	public Sheet(boolean cleared)
	{
		this.setLayout(null);
		
		content = new JPanel();
		content.setSize(300, 600);
		content.setLocation(0, 50);
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		add(content);
		
		title = new JLabel();
		title.setBounds(10, 10, 290, 30);
		title.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		add(title);
		
		initCombiButtons();		// Hinzufügen aller CombiButtons zum content JPanel	
		insertLabels();			// Einfügen der JLabel für Punkteausgabe

		// wenn cleared == true : das Sheet wird für einen Spieler genutzt
		if (cleared)
		{
			vanish();
			setLabelTextToZero();
		}
	}
	
	// setzt in jedes Label eine 0 um Sheet für Spieler vorzubereiten.
	private void setLabelTextToZero()
	{
		try
		{
			// Positionen der Labels: 6, 7, 8, 16, 17, 18
			((JLabel)this.content.getComponent(6)).setText("0"); 		// 6:"gesamt"
			((JLabel)this.content.getComponent(7)).setText("0"); 		// 7:"Bonus bei 63 oder mehr"
			((JLabel)this.content.getComponent(8)).setText("0"); 	// 8:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(16)).setText("0"); 		// 16:"gesamt unterer Teil"
			((JLabel)this.content.getComponent(17)).setText("0"); 		// 17:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(18)).setText("0");	// 18:"Endsumme"
		} catch (Exception e)
		{
			System.err.println("Fehlerhafte Indexzuweisung verhindert korrekte Darstellungsaktualisierung");
		}
	}
	
	//
	private MouseAdapter CombiButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		         if(e.getSource() instanceof CombiButton)
		             {
		                 CombiButton button = (CombiButton) e.getSource();
		                 if (button.isEnabled())
		                 {
		                	 button.kill();
		                	 updateSheetValues(Controller.kniffDice);
		                	 Controller.nextPlayer();
		                 }
		             }
		     }
	};
	
	// initialisiert alle CombiButtons im Sheet
	private void initCombiButtons()
	{
		// für jede Kombination wird ein Button angelegt
		// und die entsprechende Kombination zugewiesen.
		// Jeder Button wird dem Ausgabepanel (content) hinzugefügt
		for (EDiceCombination c : EDiceCombination.values())
		{
			CombiButton b = new CombiButton(c);
			b.setComponentDesign(EComponentDesign.menuButton);
			b.addMouseListener(CombiButtonListener);
			b.setBounds(0, 0, 100, 25);
			this.combinations.add(b);
			this.content.add(b);
		}
	}
	
	// fügt Labels zur Darstellung der ZwischenErgebnisse dem content JPanel hinzu
	private void insertLabels()
	{
		// Es gilt 6 JLabel dem content JPanel hinzuzufügen.
		// index : speichert 6 Positionen für alle einzufügenden Labels
		// value : speichert die den Labeln zuzuordnenden Texte
		
		int[] index = new int[]{ 6, 7, 8, 16, 17, 18 };
		String[] value = new String[]{ "gesamt", "Bonus bei 63 oder mehr", "gesamt oberer Teil", "gesamt unterer Teil", "gesamt oberer Teil", "Endsumme" };
		
		for (int i = 0; i < index.length; i++)
		{
			JLabel l = new JLabel(value[i]);
			l.setHorizontalAlignment(SwingConstants.CENTER);
			l.setPreferredSize(new Dimension(100, 20));
			content.add(l, index[i]);
		}
	}
	
	// Gibt den CombiButton des Sheets zurück,
	// der der entsprechenden Kombination zugeordnet ist
	private CombiButton getCombiButton(EDiceCombination c)
	{
		// für jeden CombiButton wird geprüft,
		// ob er die entsprechende Kombination besitzt
		// wenn der Button gefunden ist, wird er zurückgegeben
		for (CombiButton b : combinations)
			if (c.equals(b.getCombination()))
				return b;
		return null;
	}

	public void updateSheetValues(Dice[] combination)
	{
		// für jede Kombination, die noch nicht gewählt wurde, wird der neue Wert gesetzt.
		for (CombiButton b : this.combinations)
			if (!b.isKilled())
				b.setValue(Dice.getSortedValues(Controller.kniffDice));		
		
		// Berechnung zur Ausgabe auf den Zwischenergebnis Labels		
		int a = 0,	// gesamt & gesamt oberer Teil
			b = 0,	// bonus
			c = 0;	// gesamt unterer Teil
		
		// oberer Teil
		for (int i = 0; i < 6; i++)
			if (this.combinations.get(i).isKilled())
				a += this.combinations.get(i).getValue();
		
		// unterer Teil
		for (int i = 6; i < combinations.size(); i++)
			if (this.combinations.get(i).isKilled())
				c += this.combinations.get(i).getValue();
		
		// Bonus wenn mehr als 63 im oberen Teil
		if (a >= 63)
			b = 35;
		
		try
		{
			// Positionen der Labels: 6, 7, 8, 16, 17, 18
			// ...und Inhalt: "gesamt", "Bonus bei 63 oder mehr", "gesamt oberer Teil", "gesamt unterer Teil", "gesamt oberer Teil", "Endsumme"
			
			((JLabel)this.content.getComponent(6)).setText("" + a); 		// 6:"gesamt"
			((JLabel)this.content.getComponent(7)).setText("" + b); 		// 7:"Bonus bei 63 oder mehr"
			((JLabel)this.content.getComponent(8)).setText("" + (a + b)); 	// 8:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(16)).setText("" + c); 		// 16:"gesamt unterer Teil"
			((JLabel)this.content.getComponent(17)).setText("" + a); 		// 17:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(18)).setText("" + a + c);	// 18:"Endsumme"
		} catch (Exception e)
		{
			System.err.println("Fehlerhafte Indexzuweisung verhindert korrekte Berrechnung der Ergebnisse!");
		}
	}

	// legt den Titel des Sheets fest
	public void setTitle(String shortName)
	{
		this.title.setText(shortName);
	}

	// leert den Inhalt aller nicht toten CombiButtons
	public void vanish()
	{
		for (Component c : content.getComponents())
			if (c.getClass().equals(CombiButton.class))
			{
				CombiButton b = (CombiButton) c;
				if (!b.isKilled())
					b.setText("");
			}
	}
	
	// Übersteuerung um wirklich alle componenten zu deaktivieren
	public void setEnabled(boolean b)
	{
		super.setEnabled(b);
		for (Component c : content.getComponents())
			c.setEnabled(b);
	}
	
	// Übersteuerung um Darstellung an gegebene Größen anzupassen
	public void paintComponent(Graphics g)
	{
		title.setBounds(10, 10, this.getWidth(), 30);
		content.setBounds(0, 40, this.getWidth(), this.getHeight() - 50);

		for (Component c : content.getComponents())
			c.setPreferredSize(new Dimension(content.getWidth(), 30));
	}
}
