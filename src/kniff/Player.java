package kniff;

import java.util.*;

public class Player implements Comparable<Player>
{
	private static int objCount;
	
	public String name = "Spieler " + Player.objCount;
	private List<KniffSheet> KniffSheets = new ArrayList<KniffSheet>();
	public KniffSheet currentKniffSheet = new KniffSheet();
	
	public Player(String name)
	{
		Player.objCount++;
		
		//if (name.length() == 0)
			//throw new Exception("Der Spielername darf nicht leer sein!");
		//if (name.trim().length() == 0)
			//throw new IllegalArgumentException("Der Spielername darf nicht nur aus Leerzeichen bestehen!");
		//if (NameIsUsed(name))
			//throw new Exception("Der Spielername " + name + " ist bereits vergeben!");
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
	
	public void fixCurrentKniffSheet()
	{
		KniffSheets.add(currentKniffSheet);
		currentKniffSheet = new KniffSheet();
	}
}
