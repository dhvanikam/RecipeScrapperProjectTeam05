package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverFactory.DriverFactory;
import utilities.ConfigReader;
import utilities.Loggerload;

import utilities.ScraperUtility;

public class TestScrapeAll {
	public static WebDriver driver;
	ScraperUtility scrapeutil = new ScraperUtility();

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

	@Test(priority = 1)
	public void test01loop() throws IOException, InterruptedException {
		scrapeutil.test01loop(driver, "PCOS");

	}

	@Test(priority = 2)
	public void test02loop() throws IOException, InterruptedException {

		scrapeutil.test01loop(driver, "Hypothyroidism");

	}

	@Test(priority = 3)
	public void test03loop() throws IOException, InterruptedException {

		scrapeutil.test01loop(driver, "Diabetic");

	}

	@Test(priority = 4)
	public void test04loop() throws IOException, InterruptedException {

		scrapeutil.test01loop(driver, "High Blood Pressure");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
