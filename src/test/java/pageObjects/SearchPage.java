package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[normalize-space()='MacBook']")
    WebElement macbookSearchResult;
	
	 public boolean isSearchResultDisplayed() {
	        return macbookSearchResult.isDisplayed();
	    }

	    public String getSearchResultText() {
	        return macbookSearchResult.getText();
	    }
	
	
	
	

}
