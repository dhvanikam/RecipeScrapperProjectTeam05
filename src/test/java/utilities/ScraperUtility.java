package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScraperUtility {
	public static WebDriver driver;
	ExcelUtilityWriter util = new ExcelUtilityWriter();
	ToAddItemsExcelWriter addItemsExcelWriter = new ToAddItemsExcelWriter();
	CommonUtilities comnutil = new CommonUtilities();
	ExcelUtilityReader utilReader = new ExcelUtilityReader();
	boolean isContainEliminateItems;
	boolean isContainToAddItems;

	// Locators
	// String morbiditi=ConfigReader.getMorbiditi();
	String recipesButton = "//*[@id='nav']/li[1]/a";
	String recipelist = "//*[@id='maincontent']//article";
	String listRecipes = "//*[@id='maincontent']//article";
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

	public void test01loop(WebDriver driver, String morbiditi) throws IOException, InterruptedException {
		// morbiditi = ConfigReader.getMorbiditi();
		String morbiditiLink = "//*[@id='tdcpgtyp2_leftpanel']/table//div/table//tr//td[3]//a[contains(@title,'"
				+ morbiditi + "')]";
		
		String morbiditiBreakfastLink= "//*[@id=\"cardholder\"]//p[83]//a[contains(text(),\"Diabetic Breakfast\")]";
		List<LinkedHashMap<String, String>> allData = new ArrayList<LinkedHashMap<String, String>>();
		
		List<LinkedHashMap<String, String>> toAddItemsData = new ArrayList<LinkedHashMap<String, String>>();


		driver.findElement(By.xpath(recipesButton)).click();
		driver.findElement(By.xpath(morbiditiLink)).click();
		comnutil.scrollPage(driver);
		Thread.sleep(2000);
		
		//driver.findElement(By.xpath(morbiditiBreakfastLink)).click();
		
		
		// Thread.sleep(2000);
		String pages = driver.findElement(By.xpath("//*[@id='pagination']//a[last()]")).getText();
		int totalPages = Integer.parseInt(pages);
		for (int i = 1; i <= 2; i++) {
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
					recipeID = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//div[2]/span"))
							.getText();
					// comnutil.waitForElement(recipeID);
					eachData.put("RecipeID", recipeID);

					recipeName = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a"))
							.getText();
					eachData.put("RecipeName", recipeName);
	

					// Click on recipe
					driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a")).click();
					
					
					//recipe_category = driver.findElement(By.xpath(recipe_catg_path)).getText();
					String recipecategory=comnutil.findRecipeCategory(driver);
					eachData.put("Recipe Category", recipecategory);
					
					// Scrape from recipe page
					ingredients = driver.findElement(By.id(ingredientsId)).getText();
					eachData.put("Ingredients", ingredients);

					WebElement prepTime = driver.findElement(By.xpath(preparationTimeXpath));
					comnutil.findByXpath(driver, preparationTimeXpath);
					preparationTime = prepTime.getText();
					eachData.put("PreparationTime", preparationTime);
					
					comnutil.findByXpath(driver, cookingTimeXpath);
					cookingTime = driver.findElement(By.xpath(cookingTimeXpath)).getText();
					eachData.put("CookingTime", cookingTime);
					
					eachData.put("Food category", foodcategory);
					
					preparationMethod = driver.findElement(By.id(preparationMethodId)).getText();
					eachData.put("PreparationMethod", preparationMethod);

					nutrientValues = driver.findElement(By.id(nutrientValuesId)).getText();
					eachData.put("NutrientValues", nutrientValues);

					recipeURL = driver.getCurrentUrl();
					eachData.put("RecipeURL", recipeURL);

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println(recipeID);

				// Compare the eliminate List with Ingredients
				List<String> readEliminateList = utilReader.getmorbidityElimination(morbiditi);
				List<String> readToAddItemList = utilReader.getmorbidityTOADD(morbiditi);
				
				
				if(ingredients==null) {
					ingredients="";
				}
				isContainEliminateItems = comnutil.hasEliminateItems(readEliminateList, ingredients);
				
				isContainToAddItems = comnutil.isToAddItemsPresent(readToAddItemList, ingredients);


				if (!isContainEliminateItems) {
					System.out.println("This receipe does not contain eliminateditem(s): "+recipeName);
					allData.add(eachData);
					
					if (isContainToAddItems) {
						System.out.println("This receipe contains to Add Item(s): "+recipeName);
						toAddItemsData.add(eachData);
					}

				} else {
					
					System.out.println("Contains eliminateditem in recipeName: "+recipeName);
				}

				
				driver.navigate().back();

			}

		}
		util.saveDataToExcel(allData, morbiditi);
		addItemsExcelWriter.saveDataToExcel(toAddItemsData, morbiditi);
	}
}
