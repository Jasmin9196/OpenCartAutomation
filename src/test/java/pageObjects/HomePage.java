package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//a[@title='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="(//a[normalize-space()='Register'])[1]")
	WebElement lnkRegister;
	
	@FindBy (xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") //Login link added
	WebElement linkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")  //For Search Product Test
	WebElement txtSearchbox;

	@FindBy(xpath="//div[@id='search']//button[@type='button']") //For Search Product Test
	WebElement btnSearch;
	
	
	
	
	
	public void clickMyAccount() 
	
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	public void clickLogin()
	{
		linkLogin.click();
	}
	public void enterProductName(String pName)   //For Search Product Test
	{
		txtSearchbox.sendKeys(pName);
	}

	public void clickSearch()  //For Search Product Test
	{
		btnSearch.click();
	}

}
