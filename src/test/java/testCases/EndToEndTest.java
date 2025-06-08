/**
 * 
 */
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.LocalHomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class EndToEndTest extends BaseClass {
	@Test(groups= {"Master"})
	public void endToendTest() throws InterruptedException
	{
	//Soft assertions
		SoftAssert myassert=new SoftAssert();
		
	//Account Registration
	System.out.println("Account Registration................");
	LocalHomePage hp = new LocalHomePage(driver);
	hp.clickMyAccount();
	hp.clickRegister();
	
	AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	regpage.setFirstName(randomeString().toUpperCase());
	regpage.setLastName(randomeString().toUpperCase());
	
	String email=randomeString() + "@gmail.com";
	regpage.setEmail(email);// randomly generated the email
			
	//regpage.setTelephone("1234567");
	regpage.setPassword("test123");
	//regpage.setConfirmPassword("test123");
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	Thread.sleep(3000);

	String confmsg = regpage.getConfirmationMsg();
	System.out.println(confmsg);
	
	myassert.assertEquals(confmsg, "Your Account Has Been Created!"); //validation
	
	MyAccountPage mc=new MyAccountPage(driver);
	mc.clickonlogout();
	Thread.sleep(3000);
	
	
	//Login
	System.out.println("Login to application...............");
	hp.clickMyAccount();
	hp.clickonLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmailAddress(email);
	lp.setPassword("test123");
	lp.clickonLogin();
		
	
	System.out.println("Going to My Account Page? "+ mc.isMyAccountPageExists());
	myassert.assertEquals(mc.isMyAccountPageExists(), true); //validation

	
	//search & add product to cart
	System.out.println("search & add product to cart...............");
	hp.enterlocalProductName(p.getProperty("searchProductName"));
	hp.localclickSearch();
	
	SearchPage sp=new SearchPage(driver);
	
	if(sp.isProductDisplayed(p.getProperty("searchProductName")))
	{
		sp.clickProduct(p.getProperty("searchProductName"));
		sp.setQuantity("2");
		sp.addToCart();
		
	}
	Thread.sleep(3000);
	System.out.println("Added product to cart ? "+ sp.checkConfMsg());
	myassert.assertEquals(sp.checkConfMsg(),true);
		
		
	//Shopping cart
	System.out.println("Shopping cart...............");
	ShoppingCartPage sc=new ShoppingCartPage(driver);
	sc.clickItemsToNavigateToCart();
	sc.clickViewCart();
	Thread.sleep(3000);
	String totprice=sc.getTotalPrice();
	System.out.println("total price is shopping cart: "+totprice);
	myassert.assertEquals(totprice, "$246.40");   //validation
	sc.clickOnCheckout(); //navigate to checkout page
	Thread.sleep(3000);
	
	
	//Checkout Product
	System.out.println("Checkout Product...............");
	CheckoutPage cp = new CheckoutPage(driver);
   cp.placeOrderWithShippingDetails("John", "Doe","Shantiniketan Apartment", "kadi", "382715", "India","Gujarat", "Testing purpose");
   cp.continueFromShippingAddress();
   cp.selectShippingMethod();
   cp.continueFromDeliveryMethod();
   cp.selectonclick();
   cp.enterDeliveryComment("Order Added");
   cp.confirmOrder();
   String actualMessage = cp.getConfirmationMessage();
   System.out.println("Confirmation Message: " + actualMessage);

   if (actualMessage.equals("Your order has been placed!")) {
       Assert.assertTrue(true);
       logger.info("Order confirmation message verified successfully.");
   } else {
       logger.error("Order confirmation message mismatch. Expected: 'Your order has been placed!', but got: " + actualMessage);
       Assert.fail("Order confirmation message not as expected.");
   }


	//Below code works only if you configure SMTP for emails 
	/*ch.clickOnConfirmOrder();
	Thread.sleep(3000);
		
	boolean orderconf=ch.isOrderPlaced();
	System.out.println("Is Order Placed? "+orderconf);
	myassert.assertEquals(ch.isOrderPlaced(),true);*/
		
	}
}




