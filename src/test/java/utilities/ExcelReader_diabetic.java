package utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader_diabetic {
	
	public List<String> getDiabeticElimination() throws IOException
	{
		List<String>  eleminated_list = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
		//int cols=sheet.getRow(1).getLastCellNum();
		
		for(int r=2,c=0;r<=35;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			eleminated_list.add(rowvalue);
		}
		workbook.close();
	return eleminated_list;
	
	}

}
