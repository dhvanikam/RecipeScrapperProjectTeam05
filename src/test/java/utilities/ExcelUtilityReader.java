package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityReader {
	
	public List<String> getmorbidityElimination(String morbidity) throws IOException {
		String excelFilePath = ConfigReader.getExcelPath();
		List<String> eleminated_list = new ArrayList<String>();
		FileInputStream iputstream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(iputstream);

		XSSFSheet sheet = workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows = sheet.getLastRowNum();

		for (int r = 2; r <= rows; r++) {

			XSSFRow row = sheet.getRow(r);

			switch (morbidity) {
			case "Diabetic":

				XSSFCell cell = row.getCell(0);
				String rowvalue = cell.getStringCellValue();
				if (rowvalue.equals("")) {
					r = rows + 1;
				} else {
					eleminated_list.add(rowvalue);
				}
				break;

			case "Hypothyroidism":

				XSSFCell cell1 = row.getCell(2);
				String rowvalue1 = cell1.getStringCellValue();
				if (rowvalue1.equals("")) {
					r = rows + 1;
				} else {
					eleminated_list.add(rowvalue1);
				}
				break;

			case "High Blood Pressure":

				XSSFCell cell2 = row.getCell(4);
				String rowvalue2 = cell2.getStringCellValue();
				if (rowvalue2.equals("")) {
					r = rows + 1;
				} else {
					eleminated_list.add(rowvalue2);
				}
				break;

			case "PCOS":

				XSSFCell cell3 = row.getCell(6);
				String rowvalue3 = cell3.getStringCellValue();
				if (rowvalue3.equals("")) {
					r = rows + 1;
				} else {
					eleminated_list.add(rowvalue3);
				}
				break;

			}

		}

		workbook.close();
		return eleminated_list;

	}

	/******************************************************************/

	public List<String> getmorbidityTOADD(String morbidity) throws IOException {
		String excelFilePath = ConfigReader.getExcelPath();
		List<String> TOadd_list = new ArrayList<String>();
		FileInputStream iputstream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(iputstream);

		XSSFSheet sheet = workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows = sheet.getLastRowNum();

		for (int r = 2; r <= rows; r++) {

			XSSFRow row = sheet.getRow(r);

			switch (morbidity) {
			case "Diabetic":

				XSSFCell cell = row.getCell(1);
				String rowvalue = cell.getStringCellValue();
				if (rowvalue.equals("")) {
					r = rows + 1;
				} else {
					TOadd_list.add(rowvalue);
				}
				break;

			case "Hypothyroidism":

				XSSFCell cell1 = row.getCell(3);
				String rowvalue1 = cell1.getStringCellValue();
				if (rowvalue1.equals("")) {
					r = rows + 1;
				} else {
					TOadd_list.add(rowvalue1);
				}
				break;

			case "High Blood Pressure":

				XSSFCell cell2 = row.getCell(5);
				String rowvalue2 = cell2.getStringCellValue();
				if (rowvalue2.equals("")) {
					r = rows + 1;
				} else {
					TOadd_list.add(rowvalue2);
				}
				break;

			case "PCOS":

				XSSFCell cell3 = row.getCell(7);
				String rowvalue3 = cell3.getStringCellValue();
				if (rowvalue3.equals("")) {
					r = rows + 1;
				} else {
					TOadd_list.add(rowvalue3);
				}
				break;

			}

		}

		workbook.close();
		return TOadd_list;

	}

	public List<String> getAllergy() throws IOException {
		String excelFilePath = ConfigReader.getExcelPath();
		List<String> Allergy_list = new ArrayList<String>();
		FileInputStream iputstream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(iputstream);

		XSSFSheet sheet = workbook.getSheet("Allergy");
		int rows = sheet.getLastRowNum();

		for (int r = 0; r <= rows; r++) {

			XSSFRow row = sheet.getRow(r);

			XSSFCell cell = row.getCell(0);
			String rowvalue = cell.getStringCellValue();
			if (rowvalue.equals("")) {
				r = rows + 1;
			} else {
				Allergy_list.add(rowvalue);
			}
		}

		return Allergy_list;
	}

}
