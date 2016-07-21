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
		this.setName(name);
		this.setShortName(shortName);
		
		this.sheet = new Sheet(true);
		this.sheet.setTitle(this.shortName);
	}
	
	public void resetSheet()
	{
		this.sheet = new Sheet(true);
		this.sheet.setTitle(shortName);
	}
	
	public int getPoints()
	{
		return this.sheet.getEndresult();
	}
	
	public String getFullName()
	{
		return this.getName() + " [" + this.getShortName() + "]";
	}
	
	public void setName(String name) throws Exception
	{
		name = name.trim();
		
		if (name.length() == 0)
			throw new Exception("Der Name des Spielers darf nicht leer sein!");
		if (name.length() > 10)
			throw new Exception("Der Name des Spielers darf nicht länger als 10 Zeichen sein!");
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public void setShortName(String shortName) throws Exception
	{
		shortName = shortName.trim();
		
		if (shortName.length() == 0)
			throw new Exception("Das Kürzel des Spielers darf nicht leer sein!");
		if (shortName.length() > 3)
			throw new Exception("Das Kürzel darf nicht länger als 3 Zeichen sein!");
		this.shortName = shortName;
	}
	
	public String getShortName()
	{
		return shortName;
	}

	public KniffButton getPlayerButton()
	{
		if (this.button == null)
			this.button = new KniffButton(this.getFullName());
		return this.button;
	}
	
	public Sheet getSheet()
	{
		return sheet;
	}
	
	public int compareTo(Player p)
	{
		return p.name.compareTo(this.name);
	}
}
