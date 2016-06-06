package main;

import java.io.*;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;


public class POI_Service {

	public static void main(String[] args) throws Exception {

		Workbook wb = new HSSFWorkbook();
		Sheet sheet1 = wb.createSheet("new sheet");

		// You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
		// for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
		String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
		Sheet sheet3 = wb.createSheet(safeName);


		while (true) {
			try {
				FileOutputStream fileOut = new FileOutputStream("save.xls");
				wb.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "The savefile \"save.xls\" can't be opened. Please close the file and try again.");
				continue;
			}
			break;
		}





	}

}
