package kniff;

import java.awt.Graphics;
import java.util.*;

import javax.swing.JButton;

public class CombiButton extends KniffButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isKilled = false;
	//
	
	public static Dictionary<DiceCombination, CombiButton> combiButtons = new Hashtable<DiceCombination, CombiButton>();
	private DiceCombination linkedCombination;
	private String customText = "";
	
	public CombiButton(String text, DiceCombination combi)
	{
		super(text);
		this.linkedCombination = combi;
		this.customText = text;
		CombiButton.combiButtons.put(combi, this);
	}
	
	public CombiButton(DiceCombination combi)
	{
		super(getDefaultText(combi));
		this.linkedCombination = combi;
		this.customText = "";
		CombiButton.combiButtons.put(combi, this);
	}
	
	public void setCombination(DiceCombination combi)
	{
		this.linkedCombination = combi;
	}
	
	public DiceCombination getLinkedCombination()
	{
		return this.linkedCombination;
	}
	
	public String getDefaultText()
	{
		return getDefaultText(this.linkedCombination);
	}
	
	public static String getDefaultText(DiceCombination combi)
	{
		switch (combi)
		{
		case BigStr:
			return "groﬂe Straﬂe";
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
			return "kleine Straﬂe";
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
	
	public void setDefaultText()
	{
		this.setText(getDefaultText());
	}
	
	public void setCustomText()
	{
		this.setText(this.customText);
	}
	
	public void finalize()
	{
		CombiButton.combiButtons.remove(this);
	}
	
	public void paintComponent(Graphics g)
	{
		Design.drawButton(this, g);
		super.paintComponent(g);
	}
	
	public void kill()
	{
		this.isKilled = true;
		this.setEnabled(false);
	}
	
	public void setEnabled(boolean b)
	{
		if (this.isKilled)
			super.setEnabled(false);
		else
			super.setEnabled(b);
	}

	public boolean isKilled()
	{
		return this.isKilled;
	}
}
