package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationDetails;
import pageObjects.HomePage;
import testBase.BaseClass;

public class tc_AccountReg_001 extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verifyAcct_Registration()
	{
		logger.info("Started Test case tc_AccountReg_001");
		
		try
		{
			
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked My Account link");
		
		hp.clickRegister();
		logger.info("clicked Register link");
		
		AccountRegistrationDetails acctreg= new AccountRegistrationDetails(driver);
		
		logger.info("Providing Details");
		acctreg.setFirstName(BaseClass.randomString().toUpperCase());
		acctreg.setLastName(BaseClass.randomString().toUpperCase());
		
		acctreg.setEmail(BaseClass.randomString()+"@gmail.com");
		acctreg.setPhone(BaseClass.randomNumber());
		
		String password=BaseClass.randomAlphaNumeric();
		
		acctreg.setPassword(password);
		acctreg.setConfirmPassword(password);
		
		acctreg.clickPolicyChkBox();
		acctreg.clickContinueBtn();
		
		logger.info("Validating Expected message");
		String msg=acctreg.getConfirmMsg();
		Assert.assertEquals(msg, "Your Account Has Been Created!");		
		}
		
		catch(Exception e)
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("Finished Testcase tc_AccountReg_001");
		
	}
	
	
	
	

}
