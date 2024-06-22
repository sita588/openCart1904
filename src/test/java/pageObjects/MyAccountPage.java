package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[contains(text(),'My Account')]")
	WebElement myAcct;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement logout;
	
	public boolean isMyAccountPageExists()
	{
		try {
			return (myAcct.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public void logout()
	{
		logout.click();
	}

}
