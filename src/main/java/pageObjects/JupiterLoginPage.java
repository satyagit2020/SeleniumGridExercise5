package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JupiterLoginPage {
	public WebDriver driver;
	//created a constructor 
	public JupiterLoginPage(WebDriver driver) {
		this.driver=driver;
	}
	By loginlink = By.xpath("//a[contains(text(),'Login')]");
	By username = By.id("loginUserName");
	By password = By.id("loginPassword");
	By login = By.xpath("//button[@class='btn btn-primary']");
	By currentuser = By.xpath("//span[@class='user']");
	By errormsessage=By.xpath("//strong[contains(text(),'Your login details are incorrect')]");
	
	public WebElement loginLink()
	{
		return driver.findElement(loginlink);
	}
	public WebElement userName()
	{
		return driver.findElement(username);
	}
	
	public WebElement password()
	{
		return driver.findElement(password);
	}
	public WebElement login()
	{
		return driver.findElement(login);
	}
	public WebElement currentUser()
	{
		return driver.findElement(currentuser);
	}
	public WebElement invalidLogin()
	{
		return driver.findElement(errormsessage);
	}
}
