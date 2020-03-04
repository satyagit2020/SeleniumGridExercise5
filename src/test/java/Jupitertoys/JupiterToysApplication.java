package Jupitertoys;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

//import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.JupiterContactPage;
import pageObjects.JupiterDeliveryDetails;
import pageObjects.JupiterLoginPage;
import pageObjects.JupiterShopPage;
import resources.SetupDriver;
import resources.base;

public class JupiterToysApplication extends base {

	public Properties pro;

	@BeforeClass(alwaysRun = true)

	@Parameters({ "os", "browser", "url", "node" })

	public void setUp(String os, String browser, String url, String node) throws MalformedURLException {

		SetupDriver setupDriver = new SetupDriver(os, browser, url, node);

		driver = setupDriver.getDriver();

	}

	// Method to setup data
	public void datasetup() throws IOException {
		pro = new Properties();
		FileInputStream datafile = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		pro.load(datafile);

	}

	@Test(priority = 1)
	// Login to Jupiter toys
	public void loginJupiterToys() throws IOException, InterruptedException {

		driver = initializeDriver();

		datasetup();

		JupiterLoginPage loginobj = new JupiterLoginPage(driver);
		loginobj.loginLink().click();
		loginobj.userName().sendKeys(pro.getProperty("username"));
		loginobj.password().sendKeys(pro.getProperty("password"));
		loginobj.login().click();
		String actualuser = loginobj.currentUser().getText();

		// To validate the name of the user logged in
		Assert.assertEquals(actualuser, "test");
		
		driver = tearDown();
	}

	// Exercise 2- Submitting feedback using contact page

	@Test(priority = 2)
	public void contactJupiterToys() throws InterruptedException, IOException {
		driver = initializeDriver();
		
		datasetup();

		JupiterContactPage contactobj = new JupiterContactPage(driver);
		contactobj.contactLink().click();
		contactobj.Submit().click();

		// To validate error message Assert.assertTrue(true,
		contactobj.AlertError().getText();

		contactobj.Forename().sendKeys(pro.getProperty("username"));
		contactobj.Surname().sendKeys(pro.getProperty("password"));
		contactobj.Email().sendKeys(pro.getProperty("email"));
		contactobj.Telephone().sendKeys(pro.getProperty("telephone"));
		contactobj.Message().sendKeys(pro.getProperty("message"));

		String expectedText = "Thanks " + contactobj.Forename().getAttribute("value")
				+ ", we appreciate your feedback.";

		// Taking screen shot to verify error messages are gone
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(src, new File("C:\\Users\\Smallipudi\\Documents\\Satya\\screenshot1.png"));
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\screenshot1.png"));

		contactobj.Submit().click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(contactobj.AlertSuccess()));
		String actualText = contactobj.AlertSuccess().getText();
		
		// To validate success message
		Assert.assertEquals(actualText, expectedText);
		
		driver = tearDown();

	

	}
	// Exercise 3 - Cart Checkout

	@Test(priority = 3)
	public void ShopJupiterToys() throws InterruptedException, IOException {
		driver = initializeDriver();
		
		datasetup();
		
		WebDriverWait ewait = new WebDriverWait(driver, 5);
		JupiterShopPage shopobj = new JupiterShopPage(driver);
		shopobj.ShopLink().click();
		// Code to select two products from cart randomly
		int productCount = shopobj.ProductList().size();
		int count = 0;

		// ArrayList created to store items selected in the cart
		ArrayList<String> selectedItemPrice = new ArrayList<String>();
		while (true) {

			int randomNumber = ThreadLocalRandom.current().nextInt(0, productCount); //
			System.out.println(randomNumber);
			WebElement w = shopobj.ProductList().get(randomNumber);
			w.click();

			// code to Capture the price of the items
			System.out.println("Price of item " + count + " " + shopobj.PriceList().get(randomNumber).getText());
			selectedItemPrice.add(shopobj.PriceList().get(randomNumber).getText());

			count++;
			if (count == 2) {
				break;
			}
		}
		System.out.println(selectedItemPrice);

		shopobj.Cart().click();

		// ArrayList created to store item prices in the cart
		ArrayList<String> itemspriceincart = new ArrayList<String>();
		itemspriceincart.add(shopobj.itemOnePrice().getText());
		itemspriceincart.add(shopobj.itemTwoPrice().getText());
		System.out.println(itemspriceincart);
		
		// Validate the correct items are in the cart
		boolean isEqual = selectedItemPrice.equals(itemspriceincart);
		Assert.assertTrue(isEqual);
		
		// Update the number of items for each product in the cart
		shopobj.Quantity1().clear();
		shopobj.Quantity1().sendKeys(pro.getProperty("itemonequantity"));
		shopobj.Quantity2().clear();
		shopobj.Quantity2().sendKeys(pro.getProperty("itemtwoquantity"));

		// Validate all pricing

		String price1 = itemspriceincart.get(0);
		String[] arrOfprice1 = price1.split("\\$");
		// code for item1 and its subtotal validation
		System.out.println(arrOfprice1[1]);
		float iprice1 = Float.parseFloat(arrOfprice1[1]);
		float itemonesubtotal = iprice1 * Float.parseFloat((shopobj.Quantity1().getAttribute("value"))); 
		System.out.println(itemonesubtotal);
		String st1 = shopobj.itemOneSubtotal().getText();
		String[] arrstr1 = st1.split("\\$");
		System.out.println(arrstr1[1]);

		// code for item2 and its subtotal validation

		String price2 = itemspriceincart.get(1);
		String[] arrOfprice2 = price2.split("\\$");
		
		// System.out.println(arrOfprice2[1]);
		float iprice2 = Float.parseFloat(arrOfprice2[1]);
		float itemtwosubtotal = iprice2 * Float.parseFloat((shopobj.Quantity2().getAttribute("value"))); //
		System.out.println(itemtwosubtotal);
		String st2 = shopobj.itemTwoSubtotal().getText();
		String[] arrstr2 = st2.split("\\$");
		System.out.println(arrstr2[1]);

		// code for total price validation

		String totalprice = shopobj.Total().getText();
		String[] totalarr = totalprice.split(" ");
		System.out.println(totalarr[1]);
		float total = Float.parseFloat(totalarr[1]);

		Assert.assertEquals(itemonesubtotal, Float.parseFloat(arrstr1[1]));
		Assert.assertEquals(itemtwosubtotal, Float.parseFloat(arrstr2[1]));
		Assert.assertEquals((itemonesubtotal + itemtwosubtotal), total);

		shopobj.Checkout().click();
		JupiterDeliveryDetails deliveryobj = new JupiterDeliveryDetails(driver);
		deliveryobj.Forename().sendKeys(pro.getProperty("forename"));
		deliveryobj.Surname().sendKeys(pro.getProperty("surname"));
		deliveryobj.Email().sendKeys(pro.getProperty("email"));
		deliveryobj.Telephone().sendKeys(pro.getProperty("telephone"));
		deliveryobj.Address().sendKeys(pro.getProperty("address"));
		deliveryobj.cardType().click();
		Select s = new Select(deliveryobj.cardType());
		s.selectByIndex(2);
		deliveryobj.CardNum().sendKeys(pro.getProperty("cardnum"));
		String expectedText = deliveryobj.Forename().getAttribute("value");
		deliveryobj.Submit().click();

		ewait.until(ExpectedConditions.visibilityOf(deliveryobj.AlertSuccess()));
		String actualText = deliveryobj.AlertSuccess().getText();
		
//Validate success message
		Assert.assertTrue(actualText.contains(expectedText), expectedText);
		
		driver = tearDown();
	}

// Exercise 4 - Login with invalid password
	@Test(priority = 4)
	public void invalidLoginJupiterToys() throws IOException {
		driver = initializeDriver();
	
		datasetup();
		JupiterLoginPage loginobj = new JupiterLoginPage(driver);
		loginobj.loginLink().click();
		loginobj.userName().sendKeys(pro.getProperty("username"));
		loginobj.password().sendKeys(pro.getProperty("invalidpassword"));
		loginobj.login().click();
		String errormessage = loginobj.invalidLogin().getText();
		
		//validate error message
		Assert.assertEquals(errormessage, "Your login details are incorrect");
		
		driver = tearDown();
	}

}
