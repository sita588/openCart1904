package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class tc_Login_002 extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		try
		{
		logger.info("Starting tc_Login_002");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		logger.info("Clicked on Login button in My Account Page");
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		logger.info("Clicked on Login button after entering username and password");
		
		MyAccountPage mapp=new MyAccountPage(driver);
		boolean targetPage=mapp.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true,"Login Failed");
		
		//Assert.assertTrue(targetPage);
		logger.info("Navigated successfully to my Account page");
		
		mapp.logout();
		
		logger.info("Logged out successfully");
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		
	}

}
