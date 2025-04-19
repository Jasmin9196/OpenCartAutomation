package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class SearchProduct extends BaseClass{
	
	
@Test (groups="Regression")

public void  verifysearchproduct() throws InterruptedException {
	
	logger.info("** Starting the searchproducttest ** ");
	
	try
	{
	HomePage hp=new HomePage(driver);
	hp.SetSearchText(p.getProperty("SearchProductName"));
	hp.clickonsearchbutton();
	
	SearchPage sp=new SearchPage(driver);
	
	String text=sp.getSearchResultText();
	
	System.out.println(text);
	
	if (text.equalsIgnoreCase("macBook"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
	
	}
		
	}
	catch (Exception e)
	{
	
		Assert.fail();
		
	}
	
	logger.info("** Finished searchproducttest **");
}

}

