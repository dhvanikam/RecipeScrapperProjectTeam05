package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityWriter {
	String path = "./src/test/resources/RecipeExcelData/test.xlsx";
	XSSFWorkbook workbook;
	FileOutputStream fo;
	FileInputStream fi;
	Sheet sheet;
	Row row;
	Cell cell;

	public void saveDataToExcel(List<LinkedHashMap<String, String>> allData, String sheetName, String path) throws IOException {
		//String path = "./src/test/resources/RecipeExcelData/RecipeData.xlsx";
		
		Sheet sheet = null;
		Workbook workbook = null;
		if (new File(path).isFile()) {
			FileInputStream inputStream = new FileInputStream(new File(path));
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
		}
		
		if (workbook == null) {
			workbook = new XSSFWorkbook();
		}
		
		if (sheet == null) {
			sheet = workbook.createSheet(sheetName);
		}

		int indexRow = 0;
		int headerCol = 0;
		// Write header
		Row headerRow = sheet.createRow(indexRow++);
		for (Map.Entry<String, String> eachMapItem : allData.get(0).entrySet()) {
			Cell cell_text = headerRow.createCell(headerCol++);
			cell_text.setCellValue(eachMapItem.getKey());
		}

		for (LinkedHashMap<String, String> map : allData) {
			Row row = sheet.createRow(indexRow++);
			int indexCol = 0;
			for (Map.Entry<String, String> eachMapItem : map.entrySet()) {
				Cell cell_text = row.createCell(indexCol++);
				cell_text.setCellValue(eachMapItem.getValue());
			}
		}
		FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
		workbook.write(fileOutputStream);
	}

}
