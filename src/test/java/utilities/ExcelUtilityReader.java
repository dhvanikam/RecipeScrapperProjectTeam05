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
	
	
	public List<String> getDiabeticToAdd() throws IOException
	{
		List<String>  DiaToAdd_list = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
				
		for(int r=2,c=1;r<=18;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			DiaToAdd_list.add(rowvalue);
		}
		workbook.close();
	return DiaToAdd_list;
	
	}
	
	
	
	
	public List<String> getThyroidElimination() throws IOException
	{
		List<String>  Thyroid_eleList = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
		//int cols=sheet.getRow(1).getLastCellNum();
		
		for(int r=2,c=2;r<=29;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			Thyroid_eleList.add(rowvalue);
		}
		workbook.close();
	return Thyroid_eleList;
	
	}
	
	
	
	public List<String> getThyroidToAdd() throws IOException
	{
		List<String>  ThyroidToAdd_list = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
				
		for(int r=2,c=3;r<=14;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			ThyroidToAdd_list.add(rowvalue);
		}
		workbook.close();
	return ThyroidToAdd_list;
	
	}
	
	
	
	public List<String> getHyperTenseElimination() throws IOException
	{
		List<String>  hyperTense_eleList = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
		//int cols=sheet.getRow(1).getLastCellNum();
		
		for(int r=2,c=4;r<=13;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			hyperTense_eleList.add(rowvalue);
		}
		workbook.close();
	return hyperTense_eleList;
	
	}
	
	
	
	public List<String> getHypertenseToAdd() throws IOException
	{
		List<String>  Hyper_ToAdd = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
				
		for(int r=2,c=5;r<=19;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			Hyper_ToAdd.add(rowvalue);
		}
		workbook.close();
	return Hyper_ToAdd;
	
	}
	
	
	public List<String> getPCOS_Eliminate() throws IOException
	{
		List<String>  PCOS_eleList = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
		//int cols=sheet.getRow(1).getLastCellNum();
		
		for(int r=2,c=6;r<=20;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			PCOS_eleList.add(rowvalue);
		}
		workbook.close();
	return PCOS_eleList;
	
	}
	
	public List<String> getPCOSToAdd() throws IOException
	{
		List<String> PCOS_ToAdd = new ArrayList<String>(); 
		String excelfilepath="./src/test/resources/ExcelData/IngredientsAndComorbidities-ScrapperHackathon (1).xlsx";
		FileInputStream iputstream=new FileInputStream(excelfilepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(iputstream);
		
		XSSFSheet sheet=workbook.getSheet("Diabetes-Hypothyroidism-Hyperte");
		int rows=sheet.getLastRowNum();
				
		for(int r=2,c=7;r<=2;r++)
		{
			XSSFRow row=sheet.getRow(r);
			XSSFCell cell=row.getCell(c);
			String rowvalue=cell.getStringCellValue();
			PCOS_ToAdd.add(rowvalue);
		}
		workbook.close();
	return PCOS_ToAdd;
	
	}
	

}
