package utilities;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CommonUtilities {
	// public static WebDriver driver;

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
}
