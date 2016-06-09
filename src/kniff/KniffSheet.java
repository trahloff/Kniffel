package kniff;

import java.util.*;

public class KniffSheet
{
	public static ArrayList<DiceCombination> possibleCombinations = new ArrayList<DiceCombination>();
	private Dictionary<DiceCombination, int[]> results = new Hashtable<DiceCombination, int[]>();
	
	public KniffSheet()
	{
		
	}
	
	public static ArrayList<DiceCombination> getPossibleCombinations(Die[] kniffDice)
	{
		int[] sortedValues = Die.getSortedValues(kniffDice);
		return getPossibleCombinations(sortedValues);
	}
	
	public static ArrayList<DiceCombination> getPossibleCombinations(int[] values)
	{
		possibleCombinations.clear();
		int[] sortedValues = Die.sortKniffelDiceValues(values);
		if(isThroA(sortedValues))
			possibleCombinations.add(DiceCombination.ThroA);
		if(isFouoA(sortedValues))
			possibleCombinations.add(DiceCombination.FouoA);
		if(isFullHouse(sortedValues))
			possibleCombinations.add(DiceCombination.FullHouse);
		if(isFivoA(sortedValues))
			possibleCombinations.add(DiceCombination.FivoA);
		if(isSmlStr(sortedValues))
			possibleCombinations.add(DiceCombination.SmlStr);
		if(isBigStr(sortedValues))
			possibleCombinations.add(DiceCombination.BigStr);	
		return possibleCombinations;
	}

	public boolean fixCombination(DiceCombination combi, Die[] kniffDice)
	{
		
		int[] sortedValues = Die.getSortedValues(kniffDice);
		if (getPossibleCombinations(kniffDice).contains(combi))
			if (results.put(combi, sortedValues) == null)
				return true;
		return false;
	}	
	
	public int getPoints(DiceCombination combi)
	{
		int[] diceValues = results.get(combi);
		if (diceValues == null)
			return 0;
		
		return calcPoints(combi, diceValues);
	}
	
	public static int calcPoints(DiceCombination combi, int[] diceValues)
	{
		switch (combi)
		{
		case BigStr:
			return 40;
		case FivoA:
			return 50;
		case FouoA:
			return countAnyValue(diceValues);
		case FullHouse:
			return 25;
		case SmlStr:
			return 30;
		case ThroA:
			return countAnyValue(diceValues);
		default:
			return 0;
		}
	}
	
	private static int countAnyValue(int[] values)
	{
		int result = 0;
		for (int i = 0; i < values.length; i++)
			result += values[i];
		return result;
	}
	
	private static boolean isBigStr(int[] values)
	{
		if (Arrays.equals(values, new int[]{1,2,3,4,5}))
			return true;
		if (Arrays.equals(values, new int[]{2,3,4,5,6}))
			return true;
		return false;
	}

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

	private static boolean isFivoA(int[] values)
	{
		for (int i = 0; i < values.length-1; i++)
			if (values[i] != values[i+1])
				return false;
		return true;
	}

	private static boolean isFullHouse(int[] values)
	{
		for (int i = 0; i < values.length - 1; i++)
		{
			if (values[i] != values[i+1])
			{
				int a = checkOcc(values[i], values);
				int b = checkOcc(values[i+1], values);
				if (a == 3 && b == 2 || a == 2 && b == 3)
					return true;
			}
		}
		return false;
	}

	private static boolean isThroA(int[] values)
	{
		for (int i = 0; i < values.length - 1; i++)
			if (checkOcc(values[i], values) >= 3)
				return true;
		return false;
	}

	private static int checkOcc(int value, int[] values)
	{
		int j = 0;
		for (int i = 0; i < values.length; i++)
			if (values[i] == value)
				j++;
		return j;
	}

	private static boolean isFouoA(int[] values)
	{
		for (int i = 0; i < values.length - 1; i++)
			if (checkOcc(values[i], values) >= 4)
				return true;
		return false;
	}

	public ArrayList<DiceCombination> getFixedCombinations()
	{
		
		return null;
	}

}
