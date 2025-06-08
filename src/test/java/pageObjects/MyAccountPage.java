package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	

@FindBy (xpath="//h1[normalize-space()='My Account']") //My account page heading
WebElement AccHeading;


@FindBy (xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
WebElement lnklogout;

public boolean isMyAccountPageExists()
{
	try {
		return (AccHeading.isDisplayed());
	}
	
	catch (Exception e) {
		
		return false;
	}
}
	
	public void clickonlogout()
	{
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lnklogout);
	  lnklogout.click();
	}
	
}




