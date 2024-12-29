package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* data is valid - login is success -test pass -logout
  data is valid- login failed -test fail
  
  data is invalid- login success-test fail-logout
  data is invalid-login failed-test pass
  
 */

public class LoginDDT extends BaseClass {
	
	@Test (dataProvider="LoginData", dataProviderClass=DataProviders.class) //Getting data provider from different class
	
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("***starting LoginDDT test**");
		
		try {
		
		        //Home Page
		
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				//Login Page
				
				LoginPage lp=new LoginPage(driver);
				lp.setEmailAddress(email);
				lp.setPassword(pwd);
				lp.clickonLogin();
				
				//My Account
				
				MyAccountPage macc=new MyAccountPage(driver);
				
				Boolean targetPage=macc.isMyAccountPageExists();
				
				if (exp.equalsIgnoreCase("valid"))
				{
					if (targetPage==true)
					{
						macc.clickonlogout();
					
						Assert.assertTrue(true);
						
					}
				
					
				else
				{
					Assert.assertTrue(false);
				}
				
				}
				
	
	
	
	if (exp.equalsIgnoreCase("Invalid"))
	{
		if (targetPage==true)
		{
			macc.clickonlogout();
		
			Assert.assertTrue(false);
		}
	
		else
		{
			Assert.assertTrue(true);
		}
	}
		}
		
	
	catch(Exception e)
	{
		Assert.fail();
	}
		
	logger.info("***Finished LoginDDT test**");
	}
}
		

		
	

	
	
	


