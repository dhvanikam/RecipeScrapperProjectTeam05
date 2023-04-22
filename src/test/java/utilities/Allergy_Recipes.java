package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Allergy_Recipes {
	public static WebDriver driver;
	ExcelWriter_allergy util = new ExcelWriter_allergy();
	CommonUtilities comnutil = new CommonUtilities();
	ExcelUtilityReader utilReader = new ExcelUtilityReader();
	boolean isContainEliminateItem;

	// Locators
	// String morbiditi=ConfigReader.getMorbiditi();
	String recipesButton = "//*[@id='nav']/li[1]/a";
	String recipelist = "//*[@id='maincontent']//article";
	String listRecipes = "//*[@id='maincontent']//article";
	String recipeNameClassName = "rcc_recipename";
	
	String ingredientsId = "rcpinglist";
	String preparationTimeXpath = "//*[@itemprop='prepTime']";
	String cookingTimeXpath = "//*[@itemprop='cookTime']";
	String preparationMethodId = "ctl00_cntrightpanel_pnlRcpMethod";
	

	// Recipe data variables
	
	String recipeName;
	
	String ingredients;
	String preparationTime;
	String cookingTime;
	
	String preparationMethod;

	String recipeURL;

	public void Allergy_Data(WebDriver driver,String Allergy_item) throws IOException, InterruptedException {
		
		
		
		List<LinkedHashMap<String, String>> allData = new ArrayList<LinkedHashMap<String, String>>();

		driver.findElement(By.xpath(recipesButton)).click();
		driver.findElement(By.xpath("//a[@href='recipes-for-cuisine-1']")).click();
		comnutil.scrollPage(driver);

		// Thread.sleep(2000);
		String pages = driver.findElement(By.xpath("//*[@id='pagination']//a[last()]")).getText();
		int totalPages = Integer.parseInt(pages);
		for (int i = 1; i <= 1; i++) {
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
					

					recipeName = driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a"))
							.getText();
					eachData.put("RecipeName", recipeName);

					

					// Click on recipe
					driver.findElement(By.xpath("//*[@id='maincontent']//article[" + j + "]//span/a")).click();

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

					preparationMethod = driver.findElement(By.id(preparationMethodId)).getText();
					eachData.put("PreparationMethod", preparationMethod);

					
					recipeURL = driver.getCurrentUrl();
					eachData.put("RecipeURL", recipeURL);

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println(recipeName);

				// Compare the eliminate List with Ingredients
				
				
				//List<String> readEliminateList = utilReader.getmorbidityElimination(Allergy_item);
				if(ingredients==null) {
					ingredients="";
				}
				isContainEliminateItem = comnutil.hasAllergyItems(Allergy_item, ingredients);

				if (isContainEliminateItem) {

					System.out.println("Contains Allergy item");

				} else {
					allData.add(eachData);
				}

				driver.navigate().back();

				}

		}
		util.saveDataToExcel(allData, Allergy_item);
	}
	}


