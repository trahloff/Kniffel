package kniff;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import helper.EColor;
import helper.EComponentDesign;
import helper.EDiceCombination;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;

public class Sheet extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JPanel content;
	private JLabel title;
	private int componentHeight = 30;
	private int fivOACount = 0;
	
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
		
		//
		//
		this.componentHeight = 25;
		//
		//
		
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
			((JLabel)this.content.getComponent(6)).setText("0"); 	// 6:"gesamt"
			((JLabel)this.content.getComponent(7)).setText("0"); 	// 7:"Bonus bei 63 oder mehr"
			((JLabel)this.content.getComponent(8)).setText("0"); 	// 8:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(16)).setText("0"); 	// 16:"gesamt unterer Teil"
			((JLabel)this.content.getComponent(17)).setText("0"); 	// 17:"gesamt oberer Teil"
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
		                	 // tötet den Button entgültig um gewählte Kombination fest zu setzen
		                	 button.kill();
		                	 // aktualisiere alle Zwischenergebnisse
		                	 updateSheetValues(Controller.kniffDice);
		                	 // der nächste Spieler ist an der Reihe
		                	 Controller.nextPlayer();
		                 }
		             }
		     }
	};
	
	// initialisiert alle CombiButtons im Sheet
	
	//
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
			b.setBounds(0, 0, 20, 15);
			b.setPreferredSize(new Dimension(20, 50));
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
			l.setFont(Design.getFont());
			l.setHorizontalAlignment(SwingConstants.CENTER);
			if (index[i] == 18)
				l.setFont(l.getFont().deriveFont(1, 20));
			
			content.add(l, index[i]);
		}
	}
	
	// Gibt den CombiButton des Sheets zurück,
	// der der entsprechenden Kombination zugeordnet ist
	@SuppressWarnings("unused")
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
	
	//
	public void updateSheetValues(Dice[] combination)
	{
		// für jede Kombination, die noch nicht gewählt wurde, wird der neue Wert gesetzt.
		for (CombiButton b : this.combinations)
			if (!b.isKilled())
				b.setValue(Dice.getSortedValues(Controller.kniffDice));		
		
		// Kniffel Kombinationsbutton ermitteln
		CombiButton btnFivOA = this.getCombiButton(EDiceCombination.FivoA);
		
		// wenn die aktuelle Kombination ein Kniffel ist und die Kniffelkombination schon genutzt...
		if (Sheet.isFivOA(combination) && btnFivOA.isKilled())
		{
			// Entsprechenden Kombinationsbutton der oberen Hälfte ermitteln...
			CombiButton btnUpCombi = getCombiButton(this.getUpperCombinationByNumber(combination));
			if (!btnUpCombi.isKilled())
			{
				// wenn die obere Kombination noch nicht genutzt ist, dann gebe +50 Bonus
				int value = btnUpCombi.getValue();
				btnUpCombi.setValue(btnUpCombi.getValue() + 50);
				btnUpCombi.setText(value + " +50");
			}
			else
			{
				// wenn obere Kombination genutzt ist, dann gebe Bonus für beliebige untere Kombination
				CombiButton btnSmlStr 		= this.getCombiButton(EDiceCombination.SmlStr);
				CombiButton btnBigStr 		= this.getCombiButton(EDiceCombination.BigStr);
				CombiButton btnFullHouse 	= this.getCombiButton(EDiceCombination.FullHouse);
				
				if (!btnSmlStr.isKilled())
					btnSmlStr.setValue(30);
				if (!btnBigStr.isKilled())
					btnBigStr.setValue(40);
				if (!btnFullHouse.isKilled())
					btnFullHouse.setValue(25);
			}
		}
		
		// Berechnung zur Ausgabe auf den Zwischenergebnis Labels		
		int b = 0;	// bonus
		
		// gesamt & gesamt oberer Teil
		int a = getPointsOfUpperPart();
		
		// Bonus wenn mehr als 63 im oberen Teil
		if (a >= 63)
			b = 35;
		
		// gesamt unterer Teil
		int c = getPointsOfLowerPart();
		
		try
		{
			// Positionen der Labels: 6, 7, 8, 16, 17, 18
			// ...und Inhalt: "gesamt", "Bonus bei 63 oder mehr", "gesamt oberer Teil", "gesamt unterer Teil", "gesamt oberer Teil", "Endsumme"
			
			((JLabel)this.content.getComponent(6)).setText("" + a); 			// 6:"gesamt"
			((JLabel)this.content.getComponent(7)).setText("" + b); 			// 7:"Bonus bei 63 oder mehr"
			((JLabel)this.content.getComponent(8)).setText("" + (a + b)); 		// 8:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(16)).setText("" + c); 			// 16:"gesamt unterer Teil"
			((JLabel)this.content.getComponent(17)).setText("" + (a + b)); 		// 17:"gesamt oberer Teil"
			((JLabel)this.content.getComponent(18)).setText("" + (a + b + c));	// 18:"Endsumme"
		} catch (Exception e)
		{
			System.err.println("Fehlerhafte Indexzuweisung verhindert korrekte Berrechnung der Ergebnisse!");
		}
	}

	// oberer Teil
	public int getPointsOfUpperPart()
	{
		int a = 0;
		for (int i = 0; i < 6; i++)
			if (this.combinations.get(i).isKilled())
				a += this.combinations.get(i).getValue();
		return a;
	}
	
	// gibt es einen Bonus?
	public boolean hasBonus()
	{
		return getPointsOfUpperPart() >= 63;
	}
	
	// unterer Teil
	public int getPointsOfLowerPart()
	{
		int a = 0;
		for (int i = 6; i < combinations.size(); i++)
			if (this.combinations.get(i).isKilled())
				a += this.combinations.get(i).getValue();
		return a;
	}
	
	public int getEndresult()
	{
		int a = getPointsOfUpperPart();
		if (a >= 63)
			a += 35;
		return  a + getPointsOfLowerPart();
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
	
	//
	public int getButtonHeight()
	{
		return componentHeight;
	}

	//
	public void setButtonHeight(int buttonHeight)
	{
		this.componentHeight = buttonHeight;
	}
	
	// Übersteuerung um Darstellung an gegebene Größen anzupassen
	public void paintComponent(Graphics g)
	{
		title.setBounds(10, 10, this.getWidth(), 30);
		content.setBounds(0, 40, this.getWidth(), this.getHeight() - 50);
		
		for (Component c : content.getComponents())
			c.setPreferredSize(new Dimension(content.getWidth(), this.componentHeight));
		
		
		
		super.paintComponent(g);
	}

	
	//-----------------------------------------------------------------
	//
	//	Logischer Teil
	//
	//-----------------------------------------------------------------
	
	// ermittelt die entsprechende Kombination des oberen Teils für eine bestimmte Zahl im Dice-Array
	private EDiceCombination getUpperCombinationByNumber(Dice[] dice)
	{
		return getUpperCombinationByNumber(Dice.getSortedValues(dice)[0]);
	}
	
	// ermittelt die entsprechende Kombination des oberen Teils für eine bestimmte Zahl
	private EDiceCombination getUpperCombinationByNumber(int i)
	{
		switch (i) {
		case 1:
			return EDiceCombination.One;
		case 2:
			return EDiceCombination.Two;
		case 3:
			return EDiceCombination.Thr;
		case 4:
			return EDiceCombination.Fou;
		case 5:
			return EDiceCombination.Fiv;
		case 6:
			return EDiceCombination.Six;
		default:
			return null;
		}
	}
	
	/**
	 * calculates the points for a specific dice combination
	 * @param combi - the combination for which the points should be calculated
	 * @param values - the dice values
	 * @return returns the points for the combination with specific dice values
	 */
	public static int calcPoints(EDiceCombination combi, int[] values)
	{
		// über die switch-Anweisung wird die Berechnung an die entsprechende
		// Methode weiterdelegiert.
		switch (combi)
		{
		case BigStr:
			return isBigStr(values) ? 40 : 0;
		case FivoA:
			return isFivOA(values) ? 50 : 0;
		case FouoA:
			return isFouOA(values) ? countAny(values) : 0;
		case FullHouse:
			return isFullHouse(values) ? 25 : 0;
		case SmlStr:
			return isSmlStr(values) ? 30 : 0;
		case ThroA:
			return isThrOA(values) ? countAny(values) : 0;
		case One:
			return countAny(1, values);
		case Two:
			return countAny(2, values);
		case Thr:
			return countAny(3, values);
		case Fou:
			return countAny(4, values);
		case Fiv:
			return countAny(5, values);
		case Six:
			return countAny(6, values);
		case Cnc:
			return countAny(values);
		default:
			return 0;
		}
	}
	
	public int getPoints(EDiceCombination combi)
	{
		return this.getCombiButton(combi).getValue();
	}
	
	/**
	 * Calculates the sum of occurrence of a specific dice value
	 * @param value - specific value
	 * @param values - the dice values
	 * @return returns the sum
	 */
	private static int countAny(int value, int[] values)
	{
		int j = 0;
		for (int i = 0; i < values.length; i++)
				if (values[i] == value)
					j += values[i];
		return j;
	}

	/**
	 * Calculates the sum of all dice values
	 * @param values - the dice values
	 * @return returns the sum
	 */
	private static int countAny(int[] values)
	{
		int result = 0;
		for (int i = 0; i < values.length; i++)
			if (!Dice.isInitialValue(values[i]))
				result += values[i];
		return result;
	}
	
	private static boolean isSmlStr(Dice[] combination)
	{
		return isSmlStr(Dice.getSortedValues(combination));
	}
	
	private static boolean isBigStr(Dice[] combination)
	{
		return isBigStr(Dice.getSortedValues(combination));
	}
	
	private static boolean isFullHouse(Dice[] combination)
	{
		return isFullHouse(Dice.getSortedValues(combination));
	}
	
	private static boolean isThrOA(Dice[] combination)
	{
		return isThrOA(Dice.getSortedValues(combination));
	}
	
	private static boolean isFouOA(Dice[] combination)
	{
		return isFouOA(Dice.getSortedValues(combination));
	}
	private static boolean isFivOA(Dice[] combination)
	{
		return isFivOA(Dice.getSortedValues(combination));
	}
	
	/**
	 * returns true if the dice value combination is a BIG STRAIGHT
	 * @param values - the dice values
	 * @return returns true if BIG STRAIGHT else false
	 */
	private static boolean isBigStr(int[] values)
	{
		if (Arrays.equals(values, new int[]{1,2,3,4,5}))
			return true;
		if (Arrays.equals(values, new int[]{2,3,4,5,6}))
			return true;
		return false;
	}

	/**
	 * returns true if the dice value combination is a SMALL STRAIGHT
	 * @param values - the dice values
	 * @return returns true if SMALL STRAIGHT else false
	 */
	private static boolean isSmlStr(int[] values)
	{
		int j = 0;
		for (int i = 1; i < values.length; i++)
		{
			if (values[i - 1]+1 == values[i])
				j++;
			if (values[i - 1] == values[i])
				continue;
			else if (values[i - 1]+1 > values[i])
				j = 0;
			
			if (j == 3)
				return true;
		}
		return false;
	}

	/**
	 * returns true if all (five) dice values are equal (KNIFFEL)
	 * @param values - the dice values
	 * @return returns true if all values are equal else false
	 */
	private static boolean isFivOA(int[] values)
	{
		for (int i = 0; i < values.length-1; i++)
		{
			if (Dice.isInitialValue(values[i]))
				return false;
			if (values[i] != values[i+1])
				return false;
		}
		return true;
	}

	/**
	 * returns true if the combination consists of
	 * two equal values and three more values which are equal too [FULL-HOUSE]
	 * Example:
	 * [2] [2] [2] [3] [3] returns true
	 * [2] [2] [3] [3] [3] returns true
	 * [2] [2] [2] [2] [3] returns false
	 * @param values - the dice values
	 * @return returns true if FULL-HOUSE else false
	 */
	private static boolean isFullHouse(int[] values)
	{
		for (int i = 0; i < values.length - 1; i++)
		{
			if (values[i] != values[i+1])
			{
				if (Dice.isInitialValue(values[i]) || Dice.isInitialValue(values[i+1]))		// Sicherheitsabfrage falls Buttons mit Initialwerten
					return false;
				int a = getOcc(values[i], values);
				int b = getOcc(values[i+1], values);
				if (a == 3 && b == 2 || a == 2 && b == 3)
					return true;
			}
		}
		return false;
	}

	/**
	 * returns true if three dice values are equal (3er PASCH)
	 * @param values - the dice values
	 * @return returns true if 3er PASCH else false
	 */
	private static boolean isThrOA(int[] values)
	{
		for (int i = 0; i < values.length - 1; i++)
			if (!Dice.isInitialValue(values[i]))				// Sicherheitsabfrage falls Buttons mit Initialwerten
				if (getOcc(values[i], values) >= 3)
					return true;
		return false;
	}

	/**
	 * returns true if four dice values are equal (3er PASCH)
	 * @param values - the dice values
	 * @return returns true if 4er PASCH else false
	 */
	private static boolean isFouOA(int[] values)
	{
		for (int i = 0; i < values.length - 1; i++)
			if (!Dice.isInitialValue(values[i]))				// Sicherheitsabfrage falls Buttons mit Initialwerten
				if (getOcc(values[i], values) >= 4)
					return true;
		return false;
	}

	/**
	 * counts the amount of occurrences of a specific value 
	 * @param value - value of which occurrence should determined
	 * @param values - the dice values
	 * @return returns the amount of occurrences
	 */
	private static int getOcc(int value, int[] values)
	{
		int j = 0;
		for (int i = 0; i < values.length; i++)
			if (values[i] == value)
				j++;
		return j;
	}
}
