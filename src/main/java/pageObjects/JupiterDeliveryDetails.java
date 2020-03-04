package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JupiterDeliveryDetails {
	WebDriver driver;
	//created a constructor 
	public JupiterDeliveryDetails(WebDriver driver) 
	{
		this.driver=driver;
		//Initializing FindBy annotations
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="forename")
	WebElement forename;
	
	@FindBy(id="surname")
	WebElement surname;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="telephone")
	WebElement telephone;
	
	@FindBy(id="address")
	WebElement address;
	
	@FindBy(id="card")
	WebElement cardNumber;
	
	@FindBy(id="checkout-submit-btn")
	WebElement submit;
	
	@FindBy(id="cardType")
	WebElement cardtype;
		
	@FindBy(xpath="//div[contains(@class,'alert-error')]")
	WebElement alertError;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	WebElement alertSuccess;
	
	
	public WebElement Forename()
	{
		return forename;
	}
	public WebElement Surname()
	{
		return surname;
	}
	public WebElement Telephone()
	{
		return telephone;
	}
	public WebElement Email()
	{
		return email;
	}
	public WebElement Address()
	{
		return address;
	}
	public WebElement CardNum()
	{
		return cardNumber;
	}
	public WebElement Submit()
	{
		return submit;
	}
	public WebElement AlertSuccess()
	{
		return alertSuccess;
	}
	public WebElement cardType()
	{
		return  cardtype;
	}
	

}
