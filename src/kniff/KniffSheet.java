package kniff;

import java.util.*;

import helper.EDiceCombination;

public class KniffSheet
{
	public static ArrayList<EDiceCombination> possibleCombinations = new ArrayList<EDiceCombination>();
	private Dictionary<EDiceCombination, int[]> results = new Hashtable<EDiceCombination, int[]>();
	
	public KniffSheet()
	{
		
	}
	
	public static ArrayList<EDiceCombination> getPossibleCombinations(Dice[] kniffDice)
	{
		int[] sortedValues = Dice.getSortedValues(kniffDice);
		return getPossibleCombinations(sortedValues);
	}
	
	public static ArrayList<EDiceCombination> getPossibleCombinations(int[] values)
	{
		possibleCombinations.clear();
		int[] sortedValues = Dice.sortKniffelDiceValues(values);
		if(isThroA(sortedValues))
			possibleCombinations.add(EDiceCombination.ThroA);
		if(isFouoA(sortedValues))
			possibleCombinations.add(EDiceCombination.FouoA);
		if(isFullHouse(sortedValues))
			possibleCombinations.add(EDiceCombination.FullHouse);
		if(isFivoA(sortedValues))
			possibleCombinations.add(EDiceCombination.FivoA);
		if(isSmlStr(sortedValues))
			possibleCombinations.add(EDiceCombination.SmlStr);
		if(isBigStr(sortedValues))
			possibleCombinations.add(EDiceCombination.BigStr);	
		return possibleCombinations;
	}

	public boolean fixCombination(EDiceCombination combi, Dice[] kniffDice)
	{	
		int[] sortedValues = Dice.getSortedValues(kniffDice);
		if (results.put(combi, sortedValues) == null)
			return true;
		return false;
	}	
	
	public int getPoints(EDiceCombination combi)
	{
		int[] diceValues = results.get(combi);
		if (diceValues == null)
			return 0;
		
		return calcPoints(combi, diceValues);
	}
	
	public static int calcPoints(EDiceCombination combi, int[] diceValues)
	{
		switch (combi)
		{
		case BigStr:
			return isBigStr(diceValues) ? 40 : 0;
		case FivoA:
			return isFivoA(diceValues) ? 40 : 0;
		case FouoA:
			return isFouoA(diceValues) ? countAnyValue(diceValues) : 0;
		case FullHouse:
			return isFullHouse(diceValues) ? 25 : 0;
		case SmlStr:
			return isSmlStr(diceValues) ? 30 : 0;
		case ThroA:
			return isThroA(diceValues) ? countAnyValue(diceValues) : 0;
		case One:
			return countAny(1, diceValues);
		case Two:
			return countAny(2, diceValues);
		case Thr:
			return countAny(3, diceValues);
		case Fou:
			return countAny(4, diceValues);
		case Fiv:
			return countAny(5, diceValues);
		case Six:
			return countAny(6, diceValues);
		case Cnc:
			return countAnyValue(diceValues);
		default:
			return 0;
		}
	}
	
	private static int countAny(int value, int[] diceValues)
	{
		int j = 0;
		for (int i = 0; i < diceValues.length; i++)
			if (diceValues[i] == value)
				j += diceValues[i];
		return j;
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

	public ArrayList<EDiceCombination> getFixedCombinations()
	{
		ArrayList<EDiceCombination> combis = new ArrayList<EDiceCombination>();
		Enumeration<EDiceCombination> enumer = this.results.keys();
		while(enumer.hasMoreElements())
			combis.add(enumer.nextElement());
		
		return combis;
	}

}
