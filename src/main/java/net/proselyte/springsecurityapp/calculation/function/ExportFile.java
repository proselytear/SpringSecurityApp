package net.proselyte.springsecurityapp.calculation.function;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class ExportFile {
	public static void writeToXls(String fileName, String[][] twoDimensionalArray) throws Exception {
		// Create a EXCEL
		Workbook wb = new Workbook();

		// Get the first worksheet
		Worksheet sheet = wb.getWorksheets().get(0);

		// Write the array to the worksheet from the specified cell
		sheet.insertArray(twoDimensionalArray, 1, 1);

		// Save the file
		wb.saveToFile(fileName + ".xlsx", ExcelVersion.Version2016);
	}
}
