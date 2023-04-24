package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScraperUtility {
	public static WebDriver driver;
	ExcelUtilityWriter utilWriter = new ExcelUtilityWriter();
	ToAddItemsExcelWriter addItemsExcelWriter = new ToAddItemsExcelWriter();
	CommonUtilities commonUtil = new CommonUtilities();
	ExcelUtilityReader utilReader = new ExcelUtilityReader();

	boolean isContainEliminateItem;
	boolean isContainEliminateItems;
	boolean isContainToAddItems;
	boolean isContainAllergyItem;

	// Locators
	String recipesButton = "//*[@id='nav']/li[1]/a";
	String listRecipesXpath = "//*[@id='maincontent']//article";
	String recipeNameClassName = "rcc_recipename";
	String ingredientsId = "rcpinglist";
	String preparationTimeXpath = "//*[@itemprop='prepTime']";
	String cookingTimeXpath = "//*[@itemprop='cookTime']";
	String preparationMethodId = "ctl00_cntrightpanel_pnlRcpMethod";
	String nutrientValuesId = "accompaniments";
	String lastPageXpath = "//*[@id='pagination']//a[last()]";

	// Recipe data variables
	String recipeID;
	String recipeName;
	String ingredients;
	String recipeCategory;
	String preparationTime;
	String cookingTime;
	String foodcategory = "Vegetarian";
	String preparationMethod;
	String nutrientValues;
	String recipeURL;

	public void scrapePages(WebDriver driver, String morbiditi) throws IOException, InterruptedException {
		String recipeDataPath = ConfigReader.getRecipePath();
		String toAddItemRecipePath = ConfigReader.getToAddItemPath();
		String allergyDataPath = ConfigReader.getAllergyPath();

		 String morbiditiLink =
		 "//*[@id='tdcpgtyp2_leftpanel']/table//div/table//tr//td[3]//a[contains(@title,'"
		 + morbiditi + "')]";
		
		// List of map to store data
		List<LinkedHashMap<String, String>> allRecipeData = new ArrayList<LinkedHashMap<String, String>>();
		List<LinkedHashMap<String, String>> toAddItemsData = new ArrayList<LinkedHashMap<String, String>>();

		// Comparision list : The eliminate List with Ingredients
		List<String> readEliminateList = utilReader.getmorbidityElimination(morbiditi);
		List<String> readToAddItemList = utilReader.getmorbidityTOADD(morbiditi);
		List<String> readToAllergyList = utilReader.getAllergy();

		// Click on Recipe button
		driver.findElement(By.xpath(recipesButton)).click();

		// Click on Morbidity under Healthy Recipes
		driver.findElement(By.xpath(morbiditiLink)).click();
		commonUtil.scrollPage(driver);

		// Pagination : Get the last page number
		String pages = driver.findElement(By.xpath(lastPageXpath)).getText();
		int totalPages = Integer.parseInt(pages);
		Loggerload.info("Total Pages" + totalPages);
		// Pagination : Loop through all pages
		for (int i = 1; i <= totalPages; i++) {
			if (i > 1) {
				try {
					String pagenumber = "//*[@id='pagination']/a[" + i + "]";
					driver.findElement(By.xpath(pagenumber)).click();

				} catch (Exception e) {
					Loggerload.info("Cannot click on page : " + i);
					Loggerload.info(e.getCause());
				}
			}

			List<WebElement> listOfRecipesOnPage = driver.findElements(By.xpath(listRecipesXpath));

			for (int j = 1; j < listOfRecipesOnPage.size(); j++) {

				// eachRecipeData Map
				LinkedHashMap<String, String> eachRecipeData = new LinkedHashMap<>();

				try {
					// Scrape from each recipe on a page and store in LinkedHashMap
					// ****On the page***//
					// Get RecipeID
					recipeID = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//div[2]/span"))
							.getText();
					String[] recipeid = recipeID.split("\\R");
					eachRecipeData.put("Recipe ID", recipeid[0]);

					// Get RecipeName
					recipeName = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a"))
							.getText();
					eachRecipeData.put("Recipe Name", recipeName);

					// ****Inside the recipe***//
					// Click on Recipe
					driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a")).click();

					// Get the Recipe Category
					recipeCategory = commonUtil.findRecipeCategory(driver);
					eachRecipeData.put("Recipe Category", recipeCategory);

					// Get the Recipe Ingredients
					ingredients = driver.findElement(By.id(ingredientsId)).getText();
					eachRecipeData.put("Ingredients", ingredients);

					// Get the recipe PreparationTime
					WebElement prepTime = driver.findElement(By.xpath(preparationTimeXpath));
					commonUtil.findByXpath(driver, preparationTimeXpath);
					preparationTime = prepTime.getText();
					eachRecipeData.put("Preparation Time", preparationTime);

					// Get the recipe CookingTime
					commonUtil.findByXpath(driver, cookingTimeXpath);
					cookingTime = driver.findElement(By.xpath(cookingTimeXpath)).getText();
					eachRecipeData.put("Cooking Time", cookingTime);

					// Set the Food Category
					eachRecipeData.put("Food category", foodcategory);

					// Get the recipe PreparationMethod
					preparationMethod = driver.findElement(By.id(preparationMethodId)).getText();
					eachRecipeData.put("Preparation Method", preparationMethod);

					// Get the NutrientValues
					nutrientValues = driver.findElement(By.id(nutrientValuesId)).getText();
					eachRecipeData.put("Nutrient Values", nutrientValues);

					// Get the recipe URL
					recipeURL = driver.getCurrentUrl();
					eachRecipeData.put("Recipe URL", recipeURL);

				} catch (Exception e) {
					Loggerload.info(e.getMessage());

				}

				if (ingredients == null) {
					ingredients = "";

				}
				// Check ingredients against EliminateItem list
				isContainEliminateItems = commonUtil.hasEliminateItems(readEliminateList, ingredients);

				// Check ingredients against ToAdd item list
				isContainToAddItems = commonUtil.isToAddItemsPresent(readToAddItemList, ingredients);

				// Filter the eliminate list and ToADD list
				if (!isContainEliminateItems) {
					Loggerload.info(morbiditi + " : This receipe does not contain eliminateditem(s): " + recipeName);
					allRecipeData.add(eachRecipeData);

					if (isContainToAddItems) {
						Loggerload.info(morbiditi + " : This receipe contains to Add Item(s): " + recipeName);
						toAddItemsData.add(eachRecipeData);
					}

				} else {

					Loggerload.info(morbiditi + " : Contains eliminateditem in recipeName: " + recipeName);
				}

				driver.navigate().back();

			} // recipe loop

		}

		for (String allergy : readToAllergyList) {
			ArrayList<LinkedHashMap<String, String>> filteredItemsList = new ArrayList<>();
			for (LinkedHashMap<String, String> recipe : allRecipeData) {
				isContainAllergyItem = commonUtil.hasAllergyItems(readEliminateList, allergy,
						recipe.get("Ingredients"));
				if (!isContainAllergyItem) {
					Loggerload.info("Not Contains allergy item : " + allergy);
					filteredItemsList.add(recipe);
				}
			}
			utilWriter.saveDataToExcel(filteredItemsList, allergy,
					allergyDataPath.replace("AllergyData", "AllergyData_" + morbiditi));
		}

		utilWriter.saveDataToExcel(allRecipeData, morbiditi, recipeDataPath);
		utilWriter.saveDataToExcel(toAddItemsData, morbiditi, toAddItemRecipePath);

	}
}
