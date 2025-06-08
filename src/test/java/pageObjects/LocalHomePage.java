package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

	
	public class LocalHomePage extends BasePage {

		public LocalHomePage(WebDriver driver)
		{
			super(driver);
		}

    // My Account dropdown
    @FindBy(xpath = "//span[text()='My Account']")
    WebElement myAccountDropdown;

    // Register link
    @FindBy(linkText = "Register")
    WebElement registerLink;

    @FindBy(xpath = "//a[text()='Login']")
    WebElement loginLink;

    @FindBy(xpath= "//input[@placeholder='Search']")
    WebElement searchBox;

    @FindBy(xpath = "//button[@class='btn btn-light btn-lg']")
    WebElement searchButton;
	
	

	
	public void clickMyAccount() 
	
	{
		myAccountDropdown.click();
	}
	
	public void clickRegister()
	{
		registerLink.click();
	}
	public void clickonLogin()
	{
		loginLink.click();
	}
	public void enterlocalProductName(String pName)   //For Search Product Test
	{
		searchBox.sendKeys(pName);
	}

	public void localclickSearch()  //For Search Product Test
	{
		searchButton.click();
	}

}