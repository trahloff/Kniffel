 package kniff;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import helper.EComponentDesign;

public class Player implements Comparable<Player>
{
	private String name;
	private String shortName;
	private Sheet sheet;
	private KniffButton button;

	public Player(String name, String shortName) throws Exception
	{
		if (name.length() == 0)
			throw new Exception("Der Name des Spielers darf nicht leer sein!");
		if (name.length() > 20)
			throw new Exception("Der Name des Spielers darf nicht länger als 20 Zeichen sein!");
		if (shortName.length() == 0)
			throw new Exception("Das Kürzel des Spielers darf nicht leer sein!");
		if (shortName.length() > 3)
			throw new Exception("Das Kürzel darf nicht länger als 3 Zeichen sein!");
		
		this.name = name;
		this.shortName = shortName;
		
		this.sheet = new Sheet(true);
		this.sheet.setTitle(this.shortName);
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public Sheet getSheet()
	{
		return sheet;
	}
	
	public int compareTo(Player p)
	{
		return p.name.compareTo(this.name);
	}
	
	public KniffButton getPlayerButton()
	{
		if (this.button == null)
		{
			this.button = new KniffButton(this.getName() + " : " + this.getShortName());
			this.button.setComponentDesign(EComponentDesign.menuButton);
		}
		return this.button;
	}
}
