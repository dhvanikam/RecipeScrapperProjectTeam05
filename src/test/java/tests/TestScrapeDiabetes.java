package tests;

import java.io.IOException;
import java.util.ArrayList;
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
import utilities.ExcelUtility;
import utilities.Loggerload;

public class TestScrapeDiabetes {
	public static WebDriver driver;
	ExcelUtility util = new ExcelUtility();
	CommonUtilities cmnutil = new CommonUtilities();
	String recipesButton = "//*[@id='nav']/li[1]/a";
	String diabeteslink = "//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht70']";//// a[contains(@title,"allrecipes under Diabetic recipes")]
	String recipelist = "//*[@id='maincontent']//article";
	String listRecipes = "//*[@id=\"maincontent\"]//article";
	// *[@id="maincontent"]//article[1]//span/a

	String Food_category="Vegetarian";
	String recipe_catg_path="//*[@id='ctl00_cntleftpanel_lblSearchTerm']/span/h1";
	 
	
	
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
		List<String> IngredientsList = new ArrayList<String>();
		driver.findElement(By.xpath(recipesButton)).click();
		driver.findElement(By.xpath(diabeteslink)).click();
		cmnutil.scrollPage(driver);
		List<WebElement> list = driver.findElements(By.xpath(listRecipes));
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]//article[1]//span/a")).click();
		Thread.sleep(2000);
		String Ingredients = driver.findElement(By.id("rcpinglist")).getText();
		IngredientsList.add(Ingredients);
		util.setCellDataHeaders();
		util.setCellData("recipelist", 1, 4, Ingredients);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
