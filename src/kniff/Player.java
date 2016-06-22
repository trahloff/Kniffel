 package kniff;

import java.util.*;

public class Player implements Comparable<Player>
{
	private static int objCount;
	
	public String name;
	public String shortName;
	public Sheet sheet = new Sheet();
	public KniffSheet KniffSheet = new KniffSheet();
	
	public Player(String name, String shortName) throws Exception
	{
		Player.objCount++;
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
		this.sheet.setTitle(this.shortName);
	}
	
	public void finalize()
	{
		Player.objCount--;
	}
	
	public int compareTo(Player p)
	{
		return p.name.compareTo(this.name);
	}
	
	public static boolean NameIsUsed(String name)
	{
		for (Player player : KniffEngine.Players)
			if (player.name.compareTo(name) == 0)
				return true;
		return false;
	}
}
