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

	@Test
	public void test01loop() throws IOException, InterruptedException {
		scrapeutil.test01loop(driver, "PCOS");

	
	}
	@Test
	public void test02loop() throws IOException, InterruptedException {

		scrapeutil.test01loop(driver, "Hyperthyroidism");
	
	}
	@Test
	public void test03loop() throws IOException, InterruptedException {
	
		scrapeutil.test01loop(driver, "Diabetic");
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}