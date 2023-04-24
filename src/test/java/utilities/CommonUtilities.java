package utilities;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities {

	public static WebDriver driver;

	public void scrollPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
	}

	public boolean hasEliminateItems(List<String> eleminateItems, String recipeIngredients) {

		for (String avoidItem : eleminateItems) {
			if (recipeIngredients == null) {
				Loggerload.info("No recipeIngredients");
			} else {
				if (recipeIngredients.toUpperCase().contains(avoidItem.toUpperCase())) {
					Loggerload.info("Contains Eliminated Item :" + avoidItem);
					return true;
				}
			}
		}
		return false;
	}

	public boolean isToAddItemsPresent(List<String> toAddItems, String recipeIngredients) {

		for (String bestFood : toAddItems) {
			if (recipeIngredients == null) {
				Loggerload.info("No recipeIngredients");
			} else {
				if (recipeIngredients.toUpperCase().contains(bestFood.toUpperCase())) {
					Loggerload.info("Contains to Add Item :" + bestFood);
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasAllergyItems(List<String> eleminateItems, String allergyItem, String recipeIngredients) {

		for (String avoidItem : eleminateItems) {
			if (recipeIngredients == null) {
				Loggerload.info("No recipeIngredients");
			} else {
				if (recipeIngredients.toUpperCase().contains(avoidItem.toUpperCase())) {
					return true;
				}
				if (recipeIngredients.toUpperCase().contains(allergyItem.toUpperCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public String findRecipeCategory(WebDriver driver) {

		String recipeCat = "";
		ArrayList<String> tagList = new ArrayList<String>();
		List<WebElement> listTagElem = driver.findElements(By.xpath("//*[@id='recipe_tags']/a"));
		for (WebElement eachTag : listTagElem) {

			tagList.add(eachTag.getText());
		}

		for (String eachTagItem : tagList) {

			if (eachTagItem.toUpperCase().contains("Breakfast".toUpperCase())) {
				recipeCat = "Breakfast";
			} else if (eachTagItem.toUpperCase().contains("Snack".toUpperCase())) {
				recipeCat = "Snack";
			} else if (eachTagItem.toUpperCase().contains("Snacks".toUpperCase())) {
				recipeCat = "Snacks";
			} else if (eachTagItem.toUpperCase().contains("Lunch".toUpperCase())) {
				recipeCat = "Lunch";
			} else if (eachTagItem.toUpperCase().contains("Dinner".toUpperCase())) {
				recipeCat = "Dinner";
			} else {
				recipeCat = "No Category Available";
			}
		}

		return recipeCat;
	}

	public void waitForElement(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));

	}

	public String getElementText(WebElement element) {
		WebElement textElement = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(element));
		return textElement.getText();
	}

	public WebElement findByElement(WebDriver driver, String locator) {
		WebElement ele = null;
		try {
			ele = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			Loggerload.error(e.getMessage());
		}

		return ele;
	}

	public WebElement findByXpath(WebDriver driver, String xpath) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

}
