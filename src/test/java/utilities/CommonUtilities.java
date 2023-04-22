package utilities;

import java.io.IOException;
import java.util.List;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities {
	// public static WebDriver driver;

	public static WebDriver driver;

	public void scrollPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	public boolean hasEliminateItems(List<String> eleminateItems, String recipeIngredients) {

		for (String avoidItem : eleminateItems) {

			if (recipeIngredients.toUpperCase().contains(avoidItem.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasAllergyItems(String allergyItem, String recipeIngredients) {

		if (recipeIngredients.toUpperCase().contains(allergyItem.toUpperCase())) {
				return true;
			}
		else
		return false;
	}
	public void waitForElement(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));

	}

	public String getElementText(WebElement element) {
		WebElement textElement = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(element));
		return textElement.getText();
	}

	public void findByID(WebDriver driver, String id) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebElement findByXpath(WebDriver driver, String xpath) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void isElementPresent(WebDriver driver, String id) {
		// WebElement myElement driver.findElement(By.cssSelector("#myElement"));
		if (driver.findElement(By.id(id)) != null) {
			System.out.println("My element was found on the page");
		} else {
			System.out.println("My Element was not found on the page");
		}
	}
}
