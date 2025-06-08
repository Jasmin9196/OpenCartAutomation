package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;


public class AddToCartPageTest extends BaseClass {
	
	@Test(groups= {"Master"})
	public void verify_addToCart() throws InterruptedException {
		logger.info(" Starting TC_005_AddToCartPageTest ");

		try {
			
			HomePage hp=new HomePage(driver);
			
			hp.enterProductName("iPhone");
			hp.clickSearch();
			
						
			SearchPage sp=new SearchPage(driver);
			
			if(sp.isProductDisplayed("iPhone"))
			{
				sp.clickProduct("iPhone");
				sp.setQuantity("2");
				sp.addToCart();
				String message=sp.confimationmessage();
				System.out.println("Check the message after added product:" +message);
				
			}
						
			Assert.assertEquals(sp.checkConfMsg(),true);

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info(" Finished TC_004_SearchProductTest ");

	}
}
