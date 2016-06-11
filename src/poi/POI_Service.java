package poi;

import java.io.*;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

@SuppressWarnings({"unused" })
public class POI_Service {
	
	private static void  writeToWorkbook(Workbook wb) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("save.xls");
		wb.write(fileOut);
		fileOut.close();
	}
	
	private static void createSave(File file) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("overview");
		
		FileOutputStream output = new FileOutputStream(file);
		workbook.write(output);
		output.close();
		workbook.close();
		
	}
	
	private static Workbook getSave(File file) throws IOException {
		FileInputStream input = new FileInputStream(file);
		Workbook wb = new HSSFWorkbook(input);
		input.close();
		return wb;
		
	}

	private static Workbook getWorkbook() throws IOException {
		
		File f = new File("save.xls");
		
		// much safer check than .exist(), because with .isFile() return==true ONLY when it exists & valid file
		if(f.isFile()) { 
			System.out.println("exists");
			return getSave(f);
		}else {
			System.out.println("created new save");
			createSave(f);
			return getSave(f);
		}
		
	}



	
	public static String test() throws IOException{
		return getWorkbook().toString();
	}


	public static void main(String[] args) throws Exception {

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

			while (true) {
				try {
					//					writeToWorkbook("save.xls", wb);

					FileInputStream file = new FileInputStream(new File("save.xls"));

					//Get the workbook instance for XLS file 
					HSSFWorkbook workbook = new HSSFWorkbook(file);
					//Get first sheet from the workbook
					HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

					//Iterate through each rows from first sheet
					Iterator<Row> rowIterator = sheet.iterator();
					while(rowIterator.hasNext()) {
						Row row = rowIterator.next();

						//For each row, iterate through each columns
						Iterator<Cell> cellIterator = row.cellIterator();
						while(cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							switch(cell.getCellType()) {
							case Cell.CELL_TYPE_BOOLEAN:
								System.out.print(cell.getBooleanCellValue() + "\t\t");
								break;
							case Cell.CELL_TYPE_NUMERIC:
								System.out.print(cell.getNumericCellValue() + "\t\t");
								break;
							case Cell.CELL_TYPE_STRING:
								System.out.print(cell.getStringCellValue() + "\t\t");
								break;
							}
						}
						System.out.print("\n");
					}

					workbook.close();
				} catch (Exception e) {
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "The savefile \"save.xls\" can't be opened. Please close the file and try again.");
					continue;
				}
				break;
			}



		


		System.out.println(getWorkbook());






	}

}
