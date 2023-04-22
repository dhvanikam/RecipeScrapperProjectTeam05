package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverFactory.DriverFactory;
import utilities.Allergy_Recipes;
import utilities.Compare;
import utilities.ConfigReader;
import utilities.Loggerload;
import utilities.Scrape;
import utilities.ScraperUtility;

public class TestScrapeAll {
	public static WebDriver driver;
	ScraperUtility scrapeutil = new ScraperUtility();
	Scrape scrape = new Scrape();
	Compare compare = new Compare();
	
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
		scrapeutil.test01loop(driver, "PCOS");
		
	}

	@Test
	public void test02loop() throws IOException, InterruptedException {

		//scrapeutil.test01loop(driver, "Hyperthyroidism");

	}

	@Test
	public void test03loop() throws IOException, InterruptedException {

		//scrapeutil.test01loop(driver, "Diabetic");


	}

	@Test
	public void test04loop() throws IOException, InterruptedException {

		//scrapeutil.test01loop(driver, "High Blood Pressure");
		

	}


	@Test
	public void test05loop() throws IOException, InterruptedException {
	
		Allergy_Recipes a=new Allergy_Recipes();
		String[] list= {"milk","soy","egg","sesame","peanuts","walnut","almond","hazelnut",
							"pecan","cashew","pistachio","shell fish","seafood"};
		
		for(String Item:list)
		a.Allergy_Data(driver,Item);
		
    }
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
