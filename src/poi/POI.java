package poi;

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

import helper.MapUtil;
import helper.SystemUtil;

/*
 * 'POI' ist eine Library von Apache und steht für 'Poor Obfuscation Implementation'. Ja, ernsthaft.
 * Diese Library wird genutzt um Highscores permanent auf dem Rechner des Anwenders zu speichern.
 *
 * Da eine instanzierbare Klasse im Projektkontext nicht weiterhelfen würde (man benötigt ausschließlich die Funktionen, Klassenattribute werden nicht gebraucht)
 * Ist diese Klasse eine Ansaammlung ausschließlich statischer Methoden. Sie ist als Hilfsklasse anzusehen
 *
 */

@SuppressWarnings({"unused" })
public class POI { //

	// create/retrieve saveFile from the os specific AppData directory
	private static final File directory = new File(SystemUtil.getAppPath()+"/Kniffel");
	private static final File saveFile = new File(SystemUtil.getAppPath()+"/Kniffel/save.xlsx");

	// private functions. provide logic for the publicly exposed stuff
	private static Workbook getSave() throws IOException {
		FileInputStream input = new FileInputStream(saveFile);
		Workbook wb = new HSSFWorkbook(input);
		input.close();
		return wb;
	}
	private static Workbook getWorkbook() throws IOException {

		// much safer check than .exist(), because with .isFile() return==true ONLY when it exists & valid file
		if(saveFile.isFile()) {
			return getSave();
		}else {
			createSave();
			return getSave();
		}

	}
	private static List<Integer> getScoreByPlayer(String player) {

		List<Integer> scores = new ArrayList<Integer>();
		try {

			Workbook wb = getWorkbook();

			try {

				Iterator<Row> rowIterator = wb.getSheetAt(wb.getSheetIndex(player)).iterator();
				while (rowIterator.hasNext()) {
					Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
					while (cellIterator.hasNext()) {
						scores.add((int) cellIterator.next().getNumericCellValue());
					}
				}

			} catch (IllegalArgumentException e) {
				System.err.println("No player with name: "+player+"\n");
				return scores;
			}


		} catch (IOException e) {
			System.err.println(e);
			return scores; // man könnte hier auch sich code sparen und in einem finally block scores zurückgeben, ist aber bad practice. return gehört nicht in finally blocks
		}

		Collections.sort(scores, Collections.reverseOrder()); // so stehen die besten Ergebnisse an erster Stelle
		return scores;




	}
	private static Map<String, Integer>getAllScores() {

		Map<String, Integer> map = new TreeMap<String, Integer>();

		try {

			Iterator<Sheet> sheetIterator= getWorkbook().iterator();

			while (sheetIterator.hasNext()) {
				Sheet tmp = sheetIterator.next();
				String sheetName = tmp.getSheetName();
				Iterator<Row> rowIterator = tmp.iterator();
				while(rowIterator.hasNext()) {
					Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
					while(cellIterator.hasNext()) {
						int value = (int) cellIterator.next().getNumericCellValue();
						if (map.get(sheetName)==null || map.get(sheetName) < value) {
							map.put(sheetName, value);
						}
					}
				}
			}

		} catch (IOException e) {
			return map; // siehe +getScoreByPlayer(String)
		}

		return MapUtil.sortByValue(map);


	}
	private static void createSave() throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet("placeholder").createRow(0);

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		output.close();
		workbook.close();

	}
	private static void createPlayer(String playerName) throws IOException {

		HSSFWorkbook workbook = (HSSFWorkbook) getWorkbook();

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

		HSSFWorkbook workbook = (HSSFWorkbook) getWorkbook();

		try {
			workbook.removeSheetAt(workbook.getSheetIndex(	workbook.getSheet(playerName)));
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

		HSSFWorkbook workbook = (HSSFWorkbook) getWorkbook();

		HSSFSheet sheet = workbook.getSheet(player);

		if (sheet.getRow(0)==null) {
			sheet.createRow(0);
		}

		sheet.getRow(0).createCell(Math.abs(sheet.getRow(0).getLastCellNum()), Cell.CELL_TYPE_STRING).setCellValue(score);

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		output.close();
		workbook.close();


	}
	private static void showReadError() {
		JOptionPane.showMessageDialog(null, "The savefile \"save.xls\" can't be opened. Please close the file and try again.");
	}

	// publicly exposed stuff
	public static ArrayList<String> getPlayerList() {

		ArrayList<String> players = new ArrayList<String>();

		try {

			Iterator<Sheet> sheetIterator= getWorkbook().iterator();

			while (sheetIterator.hasNext()) {
				players.add(sheetIterator.next().getSheetName());
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
	public static void checkSave() {

		if (! directory.exists()){
			directory.mkdirs(); // 'mkdir()' is unsafe
		}

		if(!saveFile.isFile()) {
			try {
				createSave();
			} catch (IOException e) {
				showReadError();
			}
		}

	}
	public static void highscoreAll() {
		Scores.highscoreAll(getAllScores());
	}
	public static void highscoreByPlayer(String player) {
		Scores.highscorePlayer(getScoreByPlayer(player));
	}

}
