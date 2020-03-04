package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class JupiterShopPage {
	WebDriver driver;
	//created a constructor 
	public JupiterShopPage(WebDriver driver) 
	{
		this.driver=driver;
		//Initializing FindBy annotations
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//li[@id='nav-shop']//a[contains(text(),'Shop')]")
	WebElement shopLink;
	
	
	@FindBy(xpath="//li[@id='nav-cart']//a[1]")
	WebElement cart;
	
	@FindBy(xpath="//tr[1]//td[3]//input[1]")
	WebElement quantity1;
	
	@FindBy(xpath="//tr[2]//td[3]//input[1]")
	WebElement quantity2;
	
	@FindBy(xpath="//a[contains(text(),'Check Out')]")
	WebElement checkout;
	
	@FindBys(@FindBy(xpath="//a[@class='btn btn-success']"))
	List<WebElement> allProducts;
	
	@FindBys(@FindBy(xpath="//span[@class='product-price ng-binding']"))
	List<WebElement> allPrices;
	
	@FindBys(@FindBy(xpath="//a[@class='product ng-scope']"))
	List<WebElement> products;
	
	//cart objects
	@FindBy(xpath="//tr[1]//td[2]")
	WebElement itemoneprice;
	@FindBy(xpath="//tr[2]//td[2]")
	WebElement itemtwoprice;
	@FindBy(xpath="//tr[1]//td[4]")
	WebElement itemonesubtotal;
	@FindBy(xpath="//tr[2]//td[4]")
	WebElement itemtwosubtotal;
	@FindBy(xpath="//tfoot//tr[1]//td[1]")
	WebElement total;
	
	public WebElement ShopLink()
	{
		return shopLink;
	}
	
	
	public WebElement Cart()
	{
		return cart;
	}
	public WebElement Quantity1()
	{
		return quantity1;
	}
	public WebElement Quantity2()
	{
		return quantity2;
	}
	public WebElement Checkout()
	{
		return checkout;
	}
	public List<WebElement> PriceList()
	{
		return  allPrices;
	}
	public List<WebElement> ProductList()
	{
		return  allProducts;
	}
	public List<WebElement> Products()
	{
		return  products;
	}
	public WebElement itemOnePrice()
	{
				return itemoneprice;
	}
	public WebElement itemTwoPrice()
	{
		return itemtwoprice;
	}
	public WebElement itemOneSubtotal()
	{
		return itemonesubtotal;
	}
	public WebElement itemTwoSubtotal()
	{
		return itemtwosubtotal;
	}
	public WebElement Total()
	{
		return total;
	}
}

