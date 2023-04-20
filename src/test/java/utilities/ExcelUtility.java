package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	String path = "./src/test/resources/RecipeExcelData/test.xlsx";
	XSSFWorkbook workbook;
	FileOutputStream fo;
	FileInputStream fi;
	Sheet sheet;
	Row row;
	Cell cell;

	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}

		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if (workbook.getSheetIndex(sheetName) == -1)
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rownum) == null)
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void setCellDataHeaders() throws IOException {
		this.setCellData("recipelist", 0, 0, "Recipe ID");
		this.setCellData("recipelist", 0, 1, "Recipe Name");
		this.setCellData("recipelist", 0, 2, "Recipe Category");
		this.setCellData("recipelist", 0, 3, "Ingredients");
		this.setCellData("recipelist", 0, 4, "Preparation Time");
		this.setCellData("recipelist", 0, 5, "Cooking Time");
		this.setCellData("recipelist", 0, 6, "Food Category");
		this.setCellData("recipelist", 0, 7, "Preparation Method");
		this.setCellData("recipelist", 0, 8, "Nutrient values");
		this.setCellData("recipelist", 0, 9, "Targetted morbid conditions");
		this.setCellData("recipelist", 0, 10, "Recipe URL");

	}
}
