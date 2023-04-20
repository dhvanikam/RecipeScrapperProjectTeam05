package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverFactory.DriverFactory;
import utilities.CommonUtilities;
import utilities.ConfigReader;
import utilities.ExcelUtilityWriter;
import utilities.Loggerload;

public class TestScrapeDiabetes {
	public static WebDriver driver;
	ExcelUtilityWriter util = new ExcelUtilityWriter();
	CommonUtilities cmnutil = new CommonUtilities();

	// Xpaths
	String recipesButton = "//*[@id='nav']/li[1]/a";
	String diabeteslink = "//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht70']";//// a[contains(@title,"allrecipes
																						//// under Diabetic recipes")]
	String recipelist = "//*[@id='maincontent']//article";
	String listRecipes = "//*[@id=\"maincontent\"]//article";
	// *[@id="maincontent"]//article[1]//span/a

	String recipeIDXpath = "//*[contains(text(),'Recipe#')]";
	String recipeNameClassName = "rcc_recipename";
	String recipe_catg_path = "//*[@id='ctl00_cntleftpanel_lblSearchTerm']/span/h1";
	String ingredientsId = "rcpinglist";
	String preparationTimeXpath = "//*[@itemprop='prepTime']";
	String cookingTimeXpath = "//*[@itemprop='cookTime']";
	String preparationMethodId = "ctl00_cntrightpanel_pnlRcpMethod";
	String nutrientValuesId = "accompaniments";

	// Recipe data variables
	String recipeID;
	String recipeName;
	String recipe_category;
	String ingredients;
	String preparationTime;
	String cookingTime;
	String foodcategory = "Vegetarian";
	String preparationMethod;
	String nutrientValues;
	String targettedMorbid = "Diabetes";
	String recipeURL;

	@BeforeClass
	public void testSetup() throws Throwable {
		// Get browser Type from Config file
		Loggerload.info("Loading Config file");
		ConfigReader.loadConfig();
		String browser = ConfigReader.getBrowserType();

		// Initialize driver from driver factory
		driver = DriverFactory.initializeDrivers(browser);
		Loggerload.info("Initializing driver for : " + browser);
	}

	@BeforeMethod
	public void openBrowser() {
		driver.get(ConfigReader.getApplicationUrl());
		System.out.println("We are currently on the following URL" + driver.getCurrentUrl());
	}

	@Test
	public void test01() throws IOException, InterruptedException {
		// List<String> IngredientsList = new ArrayList<String>();
		driver.findElement(By.xpath(recipesButton)).click();
		driver.findElement(By.xpath(diabeteslink)).click();
		cmnutil.scrollPage(driver);
		// List<WebElement> list = driver.findElements(By.xpath(listRecipes));

		Thread.sleep(2000);

		// Scrape from main page
		recipeID = driver.findElement(By.xpath(recipeIDXpath)).getText();
		recipeName = driver.findElement(By.className(recipeNameClassName)).getText();
		recipe_category = driver.findElement(By.xpath(recipe_catg_path)).getText();

		// Click on recipe
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]//article[1]//span/a")).click();

		// Scrape from recipe page
		ingredients = driver.findElement(By.id(ingredientsId)).getText();
		preparationTime = driver.findElement(By.xpath(preparationTimeXpath)).getText();
		cookingTime = driver.findElement(By.xpath(cookingTimeXpath)).getText();
		preparationMethod = driver.findElement(By.id(preparationMethodId)).getText();
		nutrientValues = driver.findElement(By.id(nutrientValuesId)).getText();
		recipeURL = driver.getCurrentUrl();
		
		//Compare
		//get from xls and compare isContains()
		
		//if condition()
		// Store To xls
		util.setCellDataHeaders();
		util.setCellData("recipelist", 1, 0, recipeID);
		util.setCellData("recipelist", 1, 1, recipeName);
		util.setCellData("recipelist", 1, 2, recipe_category);
		util.setCellData("recipelist", 1, 3, ingredients);
		util.setCellData("recipelist", 1, 4, preparationTime);
		util.setCellData("recipelist", 1, 5, cookingTime);
		util.setCellData("recipelist", 1, 6, foodcategory);
		util.setCellData("recipelist", 1, 7, preparationMethod);
		util.setCellData("recipelist", 1, 8, nutrientValues);
		util.setCellData("recipelist", 1, 9, targettedMorbid);
		util.setCellData("recipelist", 1, 10, recipeURL);

	}

//	@Test
//	public void test01loop() throws IOException, InterruptedException {
//		// List<String> IngredientsList = new ArrayList<String>();
//		driver.findElement(By.xpath(recipesButton)).click();
//		driver.findElement(By.xpath(diabeteslink)).click();
//		cmnutil.scrollPage(driver);
//		List<WebElement> list = driver.findElements(By.xpath(listRecipes));
//
//		Thread.sleep(2000);
//
//		for (int i=1;i<list.size();i++) {
//		
//			// Scrape from main page
//			recipeID = driver.findElement(By.xpath("//*[contains(text(),'Recipe#')])")).getText();
//			recipeName = driver.findElement(By.className(recipeNameClassName)).getText();
//			recipe_category = driver.findElement(By.xpath(recipe_catg_path)).getText();
//
//			// Click on recipe
//			driver.findElement(By.xpath("//*[@id=\"maincontent\"]//article["+i+"]//span/a")).click();
//
//			// Scrape from recipe page
//			ingredients = driver.findElement(By.id(ingredientsId)).getText();
//			preparationTime = driver.findElement(By.xpath(preparationTimeXpath)).getText();
//			cookingTime = driver.findElement(By.xpath(cookingTimeXpath)).getText();
//			preparationMethod = driver.findElement(By.id(preparationMethodId)).getText();
//			nutrientValues = driver.findElement(By.id(nutrientValuesId)).getText();
//			recipeURL = driver.getCurrentUrl();
//			//Go 
//			// Store To xls
//			util.setCellDataHeaders();
//			util.setCellData("recipelist", 1, 0, recipeID);
//			util.setCellData("recipelist", 1, 1, recipeName);
//			util.setCellData("recipelist", 1, 2, recipe_category);
//			util.setCellData("recipelist", 1, 3, ingredients);
//			util.setCellData("recipelist", 1, 4, preparationTime);
//			util.setCellData("recipelist", 1, 5, cookingTime);
//			util.setCellData("recipelist", 1, 6, foodcategory);
//			util.setCellData("recipelist", 1, 7, preparationMethod);
//			util.setCellData("recipelist", 1, 8, nutrientValues);
//			util.setCellData("recipelist", 1, 9, targettedMorbid);
//			util.setCellData("recipelist", 1, 10, recipeURL);
//			
//		}
//
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
