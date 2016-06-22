package poi;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

@SuppressWarnings({"unused" })
public class POI {

	private static File saveFile = new File("save.xls");
	private static void createSave(String sheetName) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet(sheetName).createRow(0);

		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		// better safe than sorry...
		output.close();
		workbook.close();

	}
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
			createSave("overview");
			return getSave();
		}

	}
	public static void createPlayer(String playerName) throws IOException {

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

		// better safe than sorry...
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


	public static ArrayList<String> getPlayerList() {

		ArrayList<String> players = new ArrayList<String>();

		try {

			Iterator<Sheet> sheetIterator= getWorkbook().iterator();

			while (sheetIterator.hasNext()) {
				players.add(sheetIterator.next().getSheetName());		
			}


		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "The savefile \"save.xls\" can't be opened. Please close the file and try again.");
		}

		return players;
	}
	public static void savePlayerScores(String player, Integer score) throws IOException {

		try {
			saveScore(player, score);
		} catch (Exception e) { // Spieler gibt es noch nicht
			createPlayer(player);
			saveScore(player, score);
		}


	}
	public static void resetSaveFile() throws IOException {
		createSave("overview");
	}

	public static Map<String, Integer>getAllScores() throws IOException{
		Map<String, Integer> map = new TreeMap<String, Integer>();

		Iterator<Sheet> sheetIterator= getWorkbook().iterator();

		while (sheetIterator.hasNext()) {
			Sheet tmp = sheetIterator.next();
			Iterator<Row> rowIterator = tmp.iterator();
			while(rowIterator.hasNext()) {
				Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
				while(cellIterator.hasNext()) {
					map.put(tmp.getSheetName(), (int) cellIterator.next().getNumericCellValue());
				}
			}
		}



		return map;
	}








}
