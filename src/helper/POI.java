package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/*
 * 'POI' ist eine Library von Apache und steht für 'Poor Obfuscation Implementation'. Ja, ernsthaft.
 * Diese Library wird genutzt um Highscores permanent auf dem Rechner des Anwenders zu speichern.
 *
 * Da eine instanzierbare Klasse im Projektkontext nicht weiterhelfen würde (man benötigt ausschließlich die Funktionen, Klassenattribute werden nicht gebraucht)
 * Ist diese Klasse eine Ansaammlung ausschließlich statischer Methoden. Sie ist als Hilfsklasse anzusehen und ist vom Design grob an die 'Math' Klasse angelehnt.
 *
 * Das erstellte .xlsx File ist absichtlich nicht durch simples Öffnen mit Excel lesbar. Daher hier der Aufbau:
 * Jedem Spieler wird ein eigenes Sheet zugewiesen. Der name des Sheets ist gleich nem Spielernamen. In der ersten (nullten) Reihe des Sheets werden die Scores in Zellen gespeichert.
 * Man könnte sich demnach im folgenden Coding sämtliche Iteratoren, die die Rows durchgehen sparen und nur mit 'getRow(0) arbeiten. Die Iteratoren sind jedoch trotzdem drin um eine möglichst hohe Fehlertoleranz zu gewährleisten.
 * Bei Userinput kann man nie sicher sein, dass nicht irgendetwas unvorhergesehenes passiert. Der selbe Grundgedanke wurde bei vielen Funktionen angewendet um möglichst viele unerwartete Ergebnisse/Inputs abzufangen
 *
 */

@SuppressWarnings({"unused" })
public class POI { //

	// Verzeichnis und saveFile bekommen bzw erstellen falls nicht vorhanden. getAppPath() gibt den OS-spezifischen AppData Ordner aus (Unter Windows zbsp %appdata% -> AppData/Roaming)
	// Man muss Verzeichnis UND Dateinamen definieren weil sonst bei fehlendem Verzeichnis mit ausschließlich dem absolutem Dateipfad ein Verzeichnis nur umständlich erstellt werden kann
	private static final File directory = new File(SystemUtil.getAppPath()+"/Kniffel");
	private static final File saveFile = new File(SystemUtil.getAppPath()+"/Kniffel/save.xlsx");


	/*
	 * PRIVATE FUNKTIONEN; fassen Funktionalitäten für public functions zusammen, verhindert, dass Teilcode doppelt geschrieben werden muss
	 */

	// Läd über simplen FileInputStream die Datei und erstellt ein HSSFWorkbook, das es zurückgibt. 'HSSF' steht für 'Horrible Spreadsheet Format' (ganz ernsthaft)
	private static Workbook getSave() throws IOException {
		checkSave();
		FileInputStream input = new FileInputStream(saveFile);
		Workbook wb = new HSSFWorkbook(input);
		input.close();
		return wb;
	}

	// nimmt Playernamen als String input und gibt eine geordnete Liste der Scores des Spielers wieder
	private static List<Integer> getScoreByPlayer(String player) {

		List<Integer> scores = new ArrayList<Integer>();

		try {
			Workbook wb = getSave();
			try {
				Iterator<Row> rowIterator = wb.getSheetAt(wb.getSheetIndex(player)).iterator(); // erstellt im Sheet des Spielers einen Iterator, der die verschiedenen Rows durchiteriert
				while (rowIterator.hasNext()) {
					Iterator<Cell> cellIterator = rowIterator.next().cellIterator(); // erstellt in der aktuellen Row einen Iterator, der die verschiedenen Cells durchiteriert
					while (cellIterator.hasNext()) {
						scores.add((int) cellIterator.next().getNumericCellValue()); // fügt den Integer Wert der aktuellen Zelle in die 'scores' Liste hinzu. 'getNumericCellValue' fängt möglicherweise auftretende nicht-integer WErte ab
					}
				}
			} catch (IllegalArgumentException e) { // falls kein Spieler mit dem gegebenen Namen existiert soll eine leere Liste zurückgegeben werden, da so das Handling in der GUI Logik am einfachsten stattfinden kann.
				System.err.println("No player with name: "+player+"\n");
				return scores;
			}
		} catch (IOException e) { // Tritt auf wenn die Datei nicht lesbar ist. Siehe Kommentar Zeile 71
			System.err.println(e);
			return scores; // man könnte hier auch sich code sparen und in einem finally block scores zurückgeben, ist aber bad practice. return gehört nicht in finally blocks
		}

		Collections.sort(scores, Collections.reverseOrder()); // so stehen die besten Ergebnisse an erster Stelle
		return scores;

	}

	// sehr ähnlich aufgebaut wie 'getScoreByPlayer'; gibt die Scores aller Spieler zurück
	public static Map<String, Integer>getAllScores() {

		Map<String, Integer> map = new TreeMap<String, Integer>();

		try {

			Iterator<Sheet> sheetIterator= getSave().iterator();

			while (sheetIterator.hasNext()) { // diese Schleife iteriert durch die Sheets, speichert den Sheetnamen in tmp und erstellt einen Iterator für die Rows des aktuellen Sheets
				Sheet tmp = sheetIterator.next(); // man brauch den Namen zwingend in einer tmp Variable, da es sonst nicht möglich ist später über den Iterator auf diese Eigenschaft zuzugreifen
				String sheetName = tmp.getSheetName();
				Iterator<Row> rowIterator = tmp.iterator();
				while(rowIterator.hasNext()) { // erstellt Iterator für die Cells der aktuellen Row
					Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
					while(cellIterator.hasNext()) {
						int value = (int) cellIterator.next().getNumericCellValue();
						if (map.get(sheetName)==null || map.get(sheetName) < value) { // wenn es den Eintrag für den Spieler noch nicht gibt oder gerade ein höherer Score als der in der Map gespeicherte gefunden wurde soll der neu gefundene score (value) eingetragen werden
							map.put(sheetName, value);
						}
					}
				}
			}

		} catch (IOException e) {
			return map; // siehe getScoreByPlayer
		}

		return MapUtil.sortByValue(map); // noch in die richtige Reihenfolge bringen


	}

	private static void createSave() throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet("placeholder").createRow(0); // ein Workbook braucht mindestens ein Sheet mit einer Row, da es ansonsten nach .xls Definition korrupt ist und alle Operationen auf dieses Workbook crashen

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		// Beides nicht unbedingt nötig, ist aber sauberer
		output.close();
		workbook.close();

	}

	private static void createPlayer(String playerName) throws IOException {

		HSSFWorkbook workbook = (HSSFWorkbook) getSave();

		try {
			workbook.createSheet(playerName);
		} catch (IllegalArgumentException e) { // Spieler schon vorhanden
			System.out.println("could not create player \""+playerName+"\"");
		}

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		output.close();
		workbook.close();

	}

	private static void deletePlayer(String playerName) throws IOException {

		HSSFWorkbook workbook = (HSSFWorkbook) getSave();

		try {
			workbook.removeSheetAt(workbook.getSheetIndex(workbook.getSheet(playerName)));
			System.out.println("deleted " + playerName);
		} catch (IllegalArgumentException e) {
			System.out.println("there is no player \""+playerName+"\" who could be deleted");
		}

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		output.close();
		workbook.close();

	}

	private static void saveScore(String player, Integer score) throws IOException {

		HSSFWorkbook workbook = (HSSFWorkbook) getSave();

		HSSFSheet sheet = workbook.getSheet(player); // erstellt eine Sheet Variable aus dem derzeitigen Sheet des Spielers

		if (sheet.getRow(0)==null) { // falls der Spieler noch nicht angelegt wurde wird eine leere Row angelegt. Braucht POI zum befüllen
			sheet.createRow(0);
		}

		sheet.getRow(0).createCell(Math.abs(sheet.getRow(0).getLastCellNum()), Cell.CELL_TYPE_NUMERIC).setCellValue(score); // erstellt neue Cell an der letzten Stelle der Row mit Wert=value

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		output.close();
		workbook.close();


	}

	//TODO obsolete?
	private static void showReadError() {
		JOptionPane.showMessageDialog(null, "Ist die Datei \"save.xlsx\" eventuell gerade in Benutzung? Sie existiert, kann aber leider nicht geöffnet werden. Bitte schließe sie und versuche es erneut");
	}

	// Überprüft ob das Verzeichnis für das saveFile und das saveFile an sich existiert
	private static void checkSave() {

		if (! directory.exists()){
			directory.mkdirs(); // 'mkdir()' ist unsicherer
		}

		if(!saveFile.isFile()) { // hier besser als 'exists', da 'exists' auch true zurückgeben würde wenn es ein Verzeichnis gibt, das wie das saveFile heißt
			try {
				createSave();
			} catch (IOException e) {
				showReadError();
			}
		}

	}



	// PUBLIC FUNKTIONEN

	public static ArrayList<String> getPlayerList() { // gibt eine Arraylist mit allen Spielernahmen zurück

		ArrayList<String> players = new ArrayList<String>();

		try {

			Iterator<Sheet> sheetIterator= getSave().iterator();

			while (sheetIterator.hasNext()) {
				players.add(sheetIterator.next().getSheetName()); // befüllt Arraylist mit Sheetnamen=Playernamen
			}

		} catch (Exception e) {
			System.out.println(e);
			showReadError();
		}

		return players;

	}

	public static void savePlayerScore(String player, Integer score) throws IOException { // exception handling sollte hier nicht im service sondern auf controller ebene stattfinden

		try {
			saveScore(player, score);
		} catch (Exception e) { // Spieler gibt es noch nicht
			createPlayer(player);
			saveScore(player, score);
		}

	}

	public static void resetSaveFile() {

		try {
			createSave();
		} catch (IOException e) {
			showReadError();
		}

	}


}
