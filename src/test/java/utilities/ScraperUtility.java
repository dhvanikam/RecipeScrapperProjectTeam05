package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScraperUtility {
	public static WebDriver driver;
	boolean isContainEliminateItem;
	boolean isContainEliminateItems;
	boolean isContainToAddItems;
	ExcelUtilityWriter util = new ExcelUtilityWriter();
	ToAddItemsExcelWriter addItemsExcelWriter = new ToAddItemsExcelWriter();
	CommonUtilities comnutil = new CommonUtilities();
	ExcelUtilityReader utilReader = new ExcelUtilityReader();
	boolean isContainAllergyItem;

	// Locators
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
	String lastPageXpath = "//*[@id='pagination']//a[last()]";

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
	String recipeURL;
	String Allery_item;

	public void test01loop(WebDriver driver, String morbiditi) throws IOException, InterruptedException {
		String recipedatapath = ConfigReader.getRecipePath();
		String toAddItemRecipePath = ConfigReader.getToAddItemPath();
		String allergydatapath = ConfigReader.getAllergyPath();

		// morbiditi = ConfigReader.getMorbiditi();
		String morbiditiLink = "//*[@id='tdcpgtyp2_leftpanel']/table//div/table//tr//td[3]//a[contains(@title,'"
				+ morbiditi + "')]";

		List<LinkedHashMap<String, String>> allData = new ArrayList<LinkedHashMap<String, String>>();
		List<LinkedHashMap<String, String>> toAddItemsData = new ArrayList<LinkedHashMap<String, String>>();

		List<LinkedHashMap<String, String>> allergyData = new ArrayList<LinkedHashMap<String, String>>();

		driver.findElement(By.xpath(recipesButton)).click();
		driver.findElement(By.xpath(morbiditiLink)).click();
		comnutil.scrollPage(driver);

		String pages = driver.findElement(By.xpath(lastPageXpath)).getText();
		int totalPages = Integer.parseInt(pages);
		for (int i = 1; i <= 1; i++) {
			if (i > 1) {
				try {
					String pagenumber = "//*[@id='pagination']/a[" + i + "]";
					WebElement pagen = driver.findElement(By.xpath(pagenumber));
					comnutil.waitForElement(pagen);
					pagen.click();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<WebElement> list = driver.findElements(By.xpath(listRecipes));
			for (int j = 1; j < list.size(); j++) {

				LinkedHashMap<String, String> eachData = new LinkedHashMap<>();
				LinkedHashMap<String, String> Allergy_eachData = new LinkedHashMap<>();

				try {
					// Scrape from main page
					recipeID = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//div[2]/span"))
							.getText();
					String[] recipeid = recipeID.split("\\R");
					eachData.put("RecipeID", recipeid[0]);
					Allergy_eachData.put("RecipeID", recipeid[0]);

					recipeName = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a"))
							.getText();
					eachData.put("RecipeName", recipeName);
					Allergy_eachData.put("RecipeName", recipeName);

					// Click on recipe
					driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a")).click();

					// recipe_category = driver.findElement(By.xpath(recipe_catg_path)).getText();
					String recipecategory = comnutil.findRecipeCategory(driver);
					eachData.put("Recipe Category", recipecategory);
					Allergy_eachData.put("Recipe Category", recipecategory);

					// Scrape from recipe page
					ingredients = driver.findElement(By.id(ingredientsId)).getText();
					eachData.put("Ingredients", ingredients);
					Allergy_eachData.put("Ingredients", ingredients);

					WebElement prepTime = driver.findElement(By.xpath(preparationTimeXpath));
					comnutil.findByXpath(driver, preparationTimeXpath);
					preparationTime = prepTime.getText();
					eachData.put("PreparationTime", preparationTime);
					Allergy_eachData.put("PreparationTime", preparationTime);

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
					Allergy_eachData.put("RecipeURL", recipeURL);

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println(recipeID);

				// Compare the eliminate List with Ingredients
				List<String> readEliminateList = utilReader.getmorbidityElimination(morbiditi);
				List<String> readToAddItemList = utilReader.getmorbidityTOADD(morbiditi);

				if (ingredients == null) {
					ingredients = "";

				}
				isContainEliminateItems = comnutil.hasEliminateItems(readEliminateList, ingredients);

				isContainToAddItems = comnutil.isToAddItemsPresent(readToAddItemList, ingredients);

				// compare the eliminate list and ToADD list
				if (!isContainEliminateItems) {
					System.out.println("This receipe does not contain eliminateditem(s): " + recipeName);
					allData.add(eachData);

					if (isContainToAddItems) {
						System.out.println("This receipe contains to Add Item(s): " + recipeName);
						toAddItemsData.add(eachData);
					}

					System.out.println("Contains eliminated item");

				} else {

					System.out.println("Contains eliminateditem in recipeName: " + recipeName);
				}

				// scrapping allergy item milk
				String[] Allery_item = { "milk", "peanuts", "egg", "sesame", "peanuts", "walnut", "almond", "hazelnut",
						"pecan", "cashew", "pistachio", "shell fish", "seafood" };
				ArrayList<String> filtered_items = new ArrayList<String>();
				for (String item : Allery_item) {
					isContainAllergyItem = comnutil.hasAllergyItems(readEliminateList, item, ingredients);

					if (isContainAllergyItem) {
						System.out.println("Contains allergy item  " + item);
						//Allergy_eachData.put("Contains "+item +" ?", "Yes");
					} else {
//						Allergy_eachData.put("Donot have Allergy Item"+item, item);
//						allergyData.add(Allergy_eachData);
//						filtered_items.add(item);
						System.out.println("Does not Contains allergy item" + item);
						Allergy_eachData.put("Contains "+item+" ?" , "No");
					}
				}
				
				allergyData.add(Allergy_eachData);	

//				if (filtered_items.size() != 0) {
//					Allergy_eachData.put("Donot have Allergy Item", filtered_items.toString());
//					allergyData.add(Allergy_eachData);	
//				}

				driver.navigate().back();

			}

		}

		util.saveDataToExcel(allData, morbiditi, recipedatapath);
		util.saveDataToExcel(toAddItemsData, morbiditi, toAddItemRecipePath);
		// addItemsExcelWriter.saveDataToExcel(toAddItemsData, morbiditi,
		// toAddItemRecipePath);
		util.saveDataToExcel(allergyData, morbiditi, allergydatapath);
	}
}
