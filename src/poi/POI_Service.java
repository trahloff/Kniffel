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


public class POI_Service {

	private static Workbook getWorkbook(String file) throws IOException {
		FileInputStream input = new FileInputStream(file);
		Workbook wb = new HSSFWorkbook(input);
		input.close();
		return wb;
	}

	private static void  writeToWorkbook(String file, Workbook wb) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();
	}
	
	public static String test() throws IOException{
		return getWorkbook("save.xls").toString();
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

		Workbook wb = new HSSFWorkbook();
		Sheet sheet1 = wb.createSheet("new sheet");

		// replaces invalid characters with spaces
		String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
		Sheet sheet3 = wb.createSheet(safeName);



		// better than .exist(), cuz .exist() also returns true of f is a valid directory
		File f = new File("save.xls");
		if(!f.isFile()) { 
			System.out.println("exists");
		}else {
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



		}


		System.out.println(getWorkbook("save.xls"));
		






	}

}
