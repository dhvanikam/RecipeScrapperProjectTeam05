package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import utilities.ExcelUtilityReader;
import utilities.ExcelUtilityWriter;
import utilities.Loggerload;

public class TestScrapeDiabetes {
	public static WebDriver driver;
	ExcelUtilityWriter util = new ExcelUtilityWriter();
	CommonUtilities comnutil = new CommonUtilities();
	ExcelUtilityReader utilReader = new ExcelUtilityReader();
	boolean isContainEliminateItem;

	// Locators
	String recipesButton = "//*[@id='nav']/li[1]/a";
	String diabeteslink = "//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht70']";
	String recipelist = "//*[@id='maincontent']//article";
	String listRecipes = "//*[@id='maincontent']//article";
	String recipeIDXpath = "//*[contains(text(),'Recipe#')]";
	String recipeNameClassName = "rcc_recipename";
	String recipe_catg_path = "//*[@id='ctl00_cntleftpanel_lblSearchTerm']/span/h1";
	String ingredientsId = "rcpinglist";
	String preparationTimeXpath = "//*[@itemprop='prepTime']";
	String cookingTimeXpath = "//*[@itemprop='cookTime']";
	String preparationMethodId = "ctl00_cntrightpanel_pnlRcpMethod";
	String nutrientValuesId = "accompaniments";

	// Recipe data variables
	WebElement recipeID;
	WebElement recipeName;
	WebElement recipe_category;
	WebElement ingredients;
	WebElement preparationTime;
	WebElement cookingTime;
	String foodcategory = "Vegetarian";
	WebElement preparationMethod;
	WebElement nutrientValues;
	String targettedMorbid = "Diabetes";
	String recipeURL;
	String ingredientslist;

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
		System.out.println("We are currently on the following URL " + driver.getCurrentUrl());
	}

	@Test
	public void test01loop() throws IOException, InterruptedException {
		List<LinkedHashMap<String, String>> allData = new ArrayList<LinkedHashMap<String, String>>();

		driver.findElement(By.xpath(recipesButton)).click();
		driver.findElement(By.xpath(diabeteslink)).click();
		comnutil.scrollPage(driver);

		// Thread.sleep(2000);
		String pages = driver.findElement(By.xpath("//*[@id='pagination']//a[last()]")).getText();
		int totalPages = Integer.parseInt(pages);
		for (int i = 1; i <= 3; i++) {
			if (i > 1) {
				try {
					String pagenumber = "//*[@id='pagination']/a[" + i + "]";
					driver.findElement(By.xpath(pagenumber)).click();
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<WebElement> list = driver.findElements(By.xpath(listRecipes));
			for (int j = 1; j < list.size(); j++) {

				LinkedHashMap<String, String> eachData = new LinkedHashMap<>();

				try {
					// Scrape from main page
					recipeID = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//div[2]/span"));
					comnutil.waitForElement(recipeID);
					eachData.put("RecipeID", recipeID.getText());

					recipeName = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a"));
					eachData.put("RecipeName", recipeName.getText());

					recipe_category = driver.findElement(By.xpath(recipe_catg_path));
					eachData.put("Recipe Category", recipe_category.getText());

					// Click on recipe
					driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a")).click();

					// Scrape from recipe page
					ingredients = driver.findElement(By.id(ingredientsId));
					eachData.put("Ingredients", ingredients.getText());
					ingredientslist = ingredients.getText();

					preparationTime = driver.findElement(By.xpath(preparationTimeXpath));
					comnutil.waitForElement(preparationTime);
					eachData.put("PreparationTime", preparationTime.getText());

					comnutil.findByXpath(driver, cookingTimeXpath);
					cookingTime = driver.findElement(By.xpath(cookingTimeXpath));
					eachData.put("CookingTime", cookingTime.getText());

					preparationMethod = driver.findElement(By.id(preparationMethodId));
					eachData.put("PreparationMethod", preparationMethod.getText());

					nutrientValues = driver.findElement(By.id(nutrientValuesId));
					eachData.put("NutrientValues", nutrientValues.getText());

					recipeURL = driver.getCurrentUrl();
					eachData.put("RecipeURL", recipeURL);

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println(recipeID);

				// Compare the eliminate List with Ingredients
//				List<String> readEliminateList = utilReader.getDiabeticElimination();
//
//				isContainEliminateItem = cmnutil.hasEliminateItems(readEliminateList, ingredientslist);
//
//				if (isContainEliminateItem) {
//
//					// continue with the loop - need to add continue statement here inside the for
//					// loop
//
//				}

				driver.navigate().back();
				allData.add(eachData);
			}

		}
		util.saveDataToExcel(allData, "Diabetes");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
