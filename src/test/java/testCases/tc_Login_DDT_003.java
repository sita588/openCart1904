package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class tc_Login_DDT_003 extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven") //getting data provider from different class
	public void verify_Login_DDT(String username,String password, String expResult) {
		
		try
		{
			logger.info("Starting verify_Login_DDT");
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Clicked on Login button in My Account Page");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmailAddress(username);
			lp.setPassword(password);
			lp.clickLogin();
			logger.info("Clicked on Login button after entering username and password");
			
			MyAccountPage mapp=new MyAccountPage(driver);
			boolean targetPage=mapp.isMyAccountPageExists();		
			
			/*
			 * Data Valid -----Login success --Pass --logout
			 * 					Login Fail ---Fail
			 * 
			 * Data Invalid ----Login Success --Fail --logout
			 * 					Login Fail ----Pass
			 */
			
			if(expResult.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					mapp.logout();
					Assert.assertTrue(true);
					
				}else
				{
					Assert.assertTrue(false);
				}
			}
			else if(expResult.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					mapp.logout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
					
			}
			
			logger.info("Finished verify_Login_DDT");
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
	}
		

}
