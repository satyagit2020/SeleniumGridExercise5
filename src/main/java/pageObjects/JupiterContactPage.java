package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This class is implemented in the page object  factory method
public class JupiterContactPage {

	WebDriver driver;
	//created a constructor 
	public JupiterContactPage(WebDriver driver) 
	{
		this.driver=driver;
		//Initializing FindBy annotations
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[contains(text(),'Contact')]")
	WebElement contactLink;
	
	@FindBy(id="forename")
	WebElement forename;
	
	@FindBy(id="surname")
	WebElement surname;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="telephone")
	WebElement telephone;
	
	@FindBy(id="message")
	WebElement message;
	
	@FindBy(xpath="//a[@class='btn-contact btn btn-primary']")
	WebElement submit;
	
		
	@FindBy(xpath="//div[contains(@class,'alert-error')]")
	WebElement alertError;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	WebElement alertSuccess;
	
	public WebElement contactLink()
	{
		return contactLink;
	}
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
	public WebElement Message()
	{
		return message;
	}
	public WebElement Submit()
	{
		return submit;
	}
	public WebElement AlertSuccess()
	{
		return alertSuccess;
	}
	public WebElement AlertError()
	{
		return alertError;
	}
	
}

