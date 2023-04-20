package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverFactory.DriverFactory;
import utilities.ConfigReader;
import utilities.Loggerload;

public class TestScrapeDiabetes {
	public static WebDriver driver;

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
	public void test01() {
		System.out.println("test");
	}
	
	@AfterClass
	public void afterClass()
	{
		driver.quit();
	}
	
}
