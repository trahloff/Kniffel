package poi;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

//@SuppressWarnings({"unused" })
public class POI_Service {

	private static File saveFile = new File("save.xls");


	public static ArrayList<String> getPlayerList() {

		ArrayList<String> players = new ArrayList<String>();

		try {
			//Get first sheet from the workbook
			HSSFSheet sheet = (HSSFSheet) getWorkbook().getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
						players.add(cell.getStringCellValue());
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "The savefile \"save.xls\" can't be opened. Please close the file and try again.");
		}

		return players;
	}


	private static void  writeToWorkbook(Workbook wb) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("save.xls");
		wb.write(fileOut);
		fileOut.close();
	}

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

	public static void createPlayer(String playerName) throws IOException {

		HSSFWorkbook workbook = (HSSFWorkbook) getWorkbook();

		try {

			HSSFSheet sheet = workbook.getSheetAt(0);
			workbook.createSheet(playerName);
			System.out.println("created " + playerName);

			if (sheet.getRow(0)==null) {
				sheet.createRow(0);
			}

			sheet.getRow(0).createCell(Math.abs(sheet.getRow(0).getLastCellNum()), Cell.CELL_TYPE_STRING).setCellValue(playerName);

		} catch (IllegalArgumentException e) {
			System.out.println("there is no player \""+playerName+"\" who could be created");
		}


		FileOutputStream output = new FileOutputStream(saveFile);
		workbook.write(output);

		// better safe than sorry...
		output.close();
		workbook.close();


	}

	public static void deletePlayer(String playerName) throws IOException {
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

	private static Workbook getWorkbook() throws IOException {

		// much safer check than .exist(), because with .isFile() return==true ONLY when it exists & valid file
		if(saveFile.isFile()) { 
			return getSave();
		}else {
			createSave("overview");
			return getSave();
		}

	}

	public static void resetSaveFile() throws IOException {
		createSave("overview");
	}




	/* general flow: 
	 * 
	 * 1.: open f, get scores, close f
	 * 		- works
	 * 
	 * 2.: do some stuff, recalculate proper scores
	 * 
	 * 3.: open f, write scores, close f
	 * 		- works
	 * 
	 * 4.: don't mess up the closing
	 * 		- works probably
	 * 
	 */





}
