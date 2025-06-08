package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage
{   
			
	public SearchPage(WebDriver driver)
	{
		super(driver);
	}	
		@FindBy(xpath="//*[@id='content']//img")
		List<WebElement> searchProducts;
				
		@FindBy(name="quantity")
		WebElement txtquantity;
		
		@FindBy(xpath="//button[@id='button-cart']")
		WebElement btnaddToCart;
		
		@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
		WebElement cnfMsg;
		
		 public boolean isProductDisplayed(String productName) {
		        for (WebElement product : searchProducts) {
		            if (product.getAttribute("title").equalsIgnoreCase(productName)) {
		                return true;
		            }
		        }
		        return false;
		    }

		    public void clickProduct(String productName) {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        for (WebElement product : searchProducts) {
		            if (product.getAttribute("title").equalsIgnoreCase(productName)) {
		                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
		                try {
		                    wait.until(ExpectedConditions.elementToBeClickable(product)).click();
		                } catch (Exception e) {
		                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
		                }
		                break;
			}
		
		}
		    }
		
		public void setQuantity(String qty)
		{
			txtquantity.clear();
			txtquantity.sendKeys(qty);
		}
		
		public void addToCart()
		{
			btnaddToCart.click();
		}
		
		
		public boolean checkConfMsg()
		{
			try
			{
			return cnfMsg.isDisplayed();
			}
			catch(Exception e)
			{
				return false;
			}
		}
		public String confimationmessage() {
			return cnfMsg.getText();
		}
		
}

