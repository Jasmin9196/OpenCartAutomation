package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass{
	
	
@Test (groups={"Regression", "Master"})

public void  verifyaccountregistration() throws InterruptedException {
	
	logger.info("** Starting the AccountRegistrationTest ** ");
	
	try
	{
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	logger.info("Clicked on My account link");
			
	hp.clickRegister();
	logger.info("Clicked on Register link");
	
	AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	logger.info("Providing customer details ");
	
	
	regpage.setFirstName(randomeString().toUpperCase());
	regpage.setLastName(randomeString().toUpperCase());
	regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
	regpage.setTelephone(randomeNumber());
	
	String password=randomeAlphaNumberic();
	
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
	
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	
	logger.info("Validating expected message.. ");
	String confmsg=regpage.getConfirmationMsg();
	if (confmsg.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
	
	}
	Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	catch (Exception e)
	{
	
		Assert.fail();
		
	}
	
	logger.info("** Finished AccountRegistrationTest **");
}

}
