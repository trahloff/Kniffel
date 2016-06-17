package kniff;

import java.util.*;

public class Player implements Comparable<Player>
{
	private static int objCount;
	
	public String name = "Spieler " + Player.objCount;
	public KniffSheet KniffSheet = new KniffSheet();
	
	public Player(String name) throws Exception
	{
		Player.objCount++;
		if (name.length() == 0)
			throw new Exception("Der Name des Spielers darf nicht leer sein!");
		if (name.length() > 20)
			throw new Exception("Der Name des Spielers darf nicht länger als 20 Zeichen sein!");
		this.name = name;
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
