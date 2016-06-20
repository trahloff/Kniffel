package kniff;

import java.util.*;

public class KniffEngine
{
	private static int round = 0;
	public static TreeSet<Player> Players = new TreeSet<Player>();
	private static Iterator<Player> IPlayer;
	
	public static int getRound()
	{
		return round;
	}
	
	public static void initPlayers()
	{
		try
		{
			Players.add(new Player("Test Player A", "TPA"));
			Players.add(new Player("Test Player B", "TPB"));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IPlayer = Players.iterator();
	}
	
	public static Player[] getRanking()
	{
		return null;
	}
	
	public static Player nextPlayer()
	{
		if (IPlayer.hasNext())
			return IPlayer.next();
		IPlayer = Players.iterator();
		return IPlayer.next();
	}
	
	public static void initEngine()
	{
		round = 0;	
		initPlayers();
		Dice.setAllInitial(true);
	}
}
