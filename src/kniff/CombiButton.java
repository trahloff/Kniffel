package kniff;

import java.awt.Graphics;
import helper.*;

/*	Der CombiButton
 * 	Erweiterter KniffButton zum speichern eines Wertes
 * 	einer bestimmten W�rfelkombination, die diesem
 * 	Button zugeordnet werden muss.
 * 	Er kann dauerhaft deaktiviert werden um ein mehrfaches
 * 	w�hlen gleicher Kombinationen zu verhindern.
 * 	Die Zeichenlogik ist in der PaintComponent(*) Methode
 * 	�bersteuert, um den CombiButton entsprechend darzustellen.	
 */

public class CombiButton extends KniffButton
{
	private static final long serialVersionUID = 1L;

	private EDiceCombination combination;	// die dem Button zugeordnete Kombination
	private boolean isKilled;				// �bersteuert setEnabled(true) mit false
	private String customText;				//
	private int value;
	
	public CombiButton(String text, EDiceCombination combi)
	{
		super(text);
		this.customText = text;
		this.combination = combi;	
		this.value = 0;
		this.isKilled = false;
	}
	
	public CombiButton(EDiceCombination combi)
	{
		this(getDefaultText(combi), combi);
	}
	
	public void setCombination(EDiceCombination combi)
	{
		this.combination = combi;
	}
	
	// gibt die dem CombiButton zugeordnete Kombination zur�ck
	public EDiceCombination getCombination()
	{
		return this.combination;
	}
	
	// erweitert um abfrage isKilled einzuf�hren.
	public void setEnabled(boolean b)
	{
		if (this.isKilled)
			super.setEnabled(false);
		else
			super.setEnabled(b);
	}
	
	// legt die Textausgabe auf den Wert fest
	public void setTextToValue()
	{
		this.setText(this.value + "");
	}
	
	// legt den Wert f�r die Kombination fest
	public void setValue(int[] dice)
	{
		this.value = Sheet.calcPoints(this.combination, dice);
		this.setTextToValue();
	}
	
	// gibt den Wert der Kombination an
	public int getValue()
	{
		return this.value;
	}
	
	// legt die Textausgabe auf die Bezeichnung der Kombination fest
	public void setTextToDefault()
	{
		this.setText(getDefaultText());
	}
	
	// 
	public String getDefaultText()
	{
		return getDefaultText(this.combination);
	}
	
	// �bersteuert die setEnabled(*) Methode mit false f�r diesen Button entg�ltig
	public void kill()
	{
		try
		{
			this.value = Integer.parseInt(this.getText());
		} catch (Exception e)
		{
			this.value = 0;
		}
		this.isKilled = true;
		this.setEnabled(false);
	}

	// gibt an, ob der Button tot ist
	public boolean isKilled()
	{
		return this.isKilled;
	}
	
	// gibt den String zur�ck, der eine Kombination darstellt
	public static String getDefaultText(EDiceCombination combi)
	{
		switch (combi)
		{
		case BigStr:
			return "gro�e Stra�e";
		case Cnc:
			return "Chance";
		case Fiv:
			return "5er Augen";
		case FivoA:
			return "Kniffel";
		case Fou:
			return "4er Augen";
		case FouoA:
			return "4er Pasch";
		case FullHouse:
			return "Full House";
		case One:
			return "1er Augen";
		case Six:
			return "6er Augen";
		case SmlStr:
			return "kleine Stra�e";
		case Thr:
			return "3er Augen";
		case ThroA:
			return "3er Pasch";
		case Two:
			return "2er Augen";
		default:
			return "...";
		}
	}
	
	// �bersteuerrung um CombiButton nach bestimmten Schema zu Zeichen
	public void paintComponent(Graphics g)
	{
		//Design.drawButton(this, g);
		super.paintComponent(g);
	}
	
}
