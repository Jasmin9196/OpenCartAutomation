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
	
	@FindBy (xpath="//input[@placeholder='Search']")// Search Text box
	WebElement Searchbox;
	
	@FindBy (css="button.btn.btn-default.btn-lg")
	WebElement Clicksearcbutton;
	
	
	
	
	
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
	public void SetSearchText (String search) {
		Searchbox.sendKeys(search);
	}
	
	public void clickonsearchbutton() {
		Clicksearcbutton.click();
	}
}
