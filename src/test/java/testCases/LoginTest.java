package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {
	
	@Test (groups="Sanity")
	
	public void verifylogin()
	
	{
		logger.info("**Starting the login Test**");
		try
		{
			
		
		//Home Page
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress (p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickonLogin();
		
		//My Account
		
		MyAccountPage macc=new MyAccountPage(driver);
		
		Boolean targetPage=macc.isMyAccountPageExists();
		Assert.assertTrue(targetPage); //Assert.assertEquals (targetPage, true, "Login failed");
	}
	
	catch (Exception e)
		
		{
		Assert.fail();
		}
		
	logger.info("**Finished Login Test**");
	
				
	}
}




