package kniff;

import java.awt.CardLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import helper.POI;
import screens.ScEnd;
import screens.ScGame;
import screens.ScHelp;
import screens.ScOption;
import screens.ScPromt;
import screens.ScRanking;
import screens.ScSettings;
import screens.ScStart;
import screens.ScWhoIsFirst;
import screens.Screen;

public class Main
{
	public static Screen scContainer 		= new Screen(new CardLayout());
	public static CardLayout clController 	= (CardLayout)(scContainer.getLayout());

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player currentPlayer;
	public static Iterator<Player> ip;
	public static int remainingRolls = 3;
	public static int remainingRounds = 13;

	public static ScStart scStart;
	public static ScGame scGame;
	public static ScOption scOption;
	public static ScSettings scSettings;
	public static ScHelp scHelp;
	public static ScPromt scPromt;
	public static ScRanking scRanking;
	public static ScEnd scEnd;
	public static ScWhoIsFirst scWhoIsFirst;

	public static Dice[] kniffDice;

	public static void main(String[] args)
	{
		// legt zufällig ein Designschema fest
		Design.setRandom();
		// legt die globale Schriftart des Programms fest
		Design.setFont(new Font("OCR A Extended", Font.PLAIN, 12));

		// die Würfel werden initialisiert
		kniffDice = Dice.initDiceCollection();
		// alle Bildschirme werden initialisiert
		initScreens();
		// die fenster Logik wird ausgeführt
		MainWindow.main(args);
		// der Startbildschirm wird aufgerufen
		show(scStart);
	}

	// legt den angegebenen Screen in den Vordergrund
	public static void show(Screen sc)
	{
		clController.show(scContainer, sc.getName());
	}

	// Logik bei Spielstart
	public static void startGame(ArrayList<Player> p) throws Exception
	{
		// Spieler werden der globalen Spielerliste hinzugefügt
		players.addAll(p);
		ip = players.iterator();
		remainingRounds = 12;

		if (ip.hasNext()) {
			currentPlayer = ip.next();
		} else {
			throw new Exception("Es scheint keine Spieler zu geben.");
		}
		// startet Subroutine für die Bestimmung der Spielerreihenfolge 
		scWhoIsFirst.startPlayerOrder(players);
	}

	// Logik zum Aufruf des Spielfeldes
	public static void startGameScreen()
	{
		// anzeige des Spielfeldes
		Main.show(scGame);
		// initialisierung des Spielfeldes
		scGame.init();
		// initialisierung des Iterators für die Spieler nach Festlegung der Spielerreihenfolge
		ip = players.iterator();
		nextPlayer();
		scGame.writeMessage(currentPlayer.getFullName() + " macht den ersten Wurf");
	}

	// setzt den nächsten Spieler zum globalen currentPlayer sofern noch runden zu spielen sind
	public static void nextPlayer()
	{
		vanishSheetValues(currentPlayer);
		if (!ip.hasNext())
		{
			// wenn das Spiel zu Ende ist
			if (remainingRounds <= 0)
			{
				// speichert die Ergebnisse für alle Spieler
				saveScores();
				Main.show(scEnd);
				// bricht die Spiellogik ab
				return;
			}
			//wenn es noch mehr runden gibt, dann setze den Iterator zurück
			ip = players.iterator();
			remainingRounds--;
		}

		currentPlayer = ip.next();

		scGame.writeMessage(currentPlayer.getFullName() + " ist an der Reihe");
		remainingRolls = 3;

		
		scGame.setRanking(getRanking());

		// Steuerelemente Aktivieren
		scGame.getBtnRoll().setEnabled(true);
		scGame.setEnableSheets(false);
		Dice.setAllEnabled(true);
		Dice.setAllInitial(true);
	}

	// löscht die nicht festgelegten Einträge von den Spielersheets
	private static void vanishSheetValues(Player p)
	{
		p.getSheet().vanish();
	}

	// Beendet das Spiel mit entsprechendem Endcode
	public static void stopGame(int i)
	{
		switch (i)
		{
		case 0:
			System.out.println("Spielende");
			break;
		case 1:
			System.out.println("Spielabbruch durch Spieler");
			break;
		default:
			System.out.println("Spielabbruch undefiniert");
			break;
		}

		// alle spielersheets werden zurückgesetzt
		for (Player p : players) {
			p.resetSheet();
		}

		// aufräumen...
		currentPlayer = null;
		players.clear();
		show(scStart);

	}

	// speichert die Ergebnisse für alle spieler
	private static void saveScores()
	{
		for (Player p : players) {
			try
			{
				POI.savePlayerScore(p.getName(), p.getPoints());
				ScRanking.newScores=true;
			} catch (IOException e)
			{
				System.err.println("Der Punktestand von " + p.getName() + " konnte aufgrund eines Schreib-Lesefehlers nicht gespeichert werden!");
			}
		}
	}

	// 
	public static void rollDice()
	{
		if (!currentPlayer.getSheet().isEnabled()) {
			currentPlayer.getSheet().setEnabled(true);
		}
		if (remainingRolls > 0)
		{
			Dice.rollAll();
			updateSheetValue(currentPlayer, kniffDice);
			remainingRolls--;
		}
		if (remainingRolls < 1) {
			scGame.getBtnRoll().setEnabled(false);
		}
		scGame.enableSheetForPlayer(currentPlayer);
	}

	// setzt die Werte in dem Playersheet fest auf basis der Würfelergebnisse
	private static void updateSheetValue(Player p, Dice[] kniffDice)
	{
		p.getSheet().updateSheetValues(kniffDice);
	}

	// legt fest, ob der BtnRoll des scGame screens aktiv ist oder nicht
	public static void updateBtnRoll()
	{
		scGame.getBtnRoll().setEnabled(!Dice.allDeactivated());
		if (remainingRolls <= 0) {
			scGame.getBtnRoll().setEnabled(false);
		}
	}

	// initialisiert alle screens und fügt sie in den globalen Container ein
	private static void initScreens()
	{
		scContainer.setName("container");

		scStart	= new ScStart();
		scOption = new ScOption();
		scGame = new ScGame();
		scSettings = new ScSettings();
		scHelp = new ScHelp();
		scPromt = new ScPromt();
		scRanking = new ScRanking();
		scEnd = new ScEnd();
		scWhoIsFirst = new ScWhoIsFirst();

		scContainer.add(scStart, scStart.getName());
		scContainer.add(scOption, scOption.getName());
		scContainer.add(scGame, scGame.getName());
		scContainer.add(scSettings, scSettings.getName());
		scContainer.add(scHelp, scHelp.getName());
		scContainer.add(scPromt, scPromt.getName());
		scContainer.add(scRanking, scRanking.getName());
		scContainer.add(scEnd, scEnd.getName());
		scContainer.add(scWhoIsFirst, scWhoIsFirst.getName());
	}

	// fügt einen Spieler der globalen Spieler liste hinzu
	public static boolean addPlayer(Player player)
	{
		if(players.size() <= 8) {
			players.add(player);
			return true;
		}
		return false;

	}

	// gibt eine nach Punkten des aktuellen Spiels sortierte Spielerliste zurück
	public static Player[] getRanking()
	{

		Player[] ranking = new Player[players.size()];
		int j = 0;
		for (Player player : players)
		{
			ranking[j] = player;
			j++;
		}
		boolean swapped;
		int end = ranking.length - 1;
		do
		{
			swapped = false;
			for (int i = 0; i < end; i++)
			{
				if (ranking[i].getPoints() < ranking[i+1].getPoints())
				{
					Player temp = ranking[i];
					ranking[i] = ranking[i+1];
					ranking[i+1] = temp;
					swapped = true;
				}
			}
			end--;
		} while (swapped);

		return ranking;
	}
}