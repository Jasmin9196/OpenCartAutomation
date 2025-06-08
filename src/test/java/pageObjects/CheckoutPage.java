package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Billing Details (Guest)
    @FindBy (xpath="//input[@id='input-shipping-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath="//input[@id='input-shipping-lastname']")
    WebElement txtLastName;

    @FindBy(xpath="//input[@id='input-shipping-address-1']")
    WebElement txtAddress1;

    @FindBy(xpath="//input[@id='input-shipping-city']")
    WebElement txtCity;

    @FindBy(xpath="//input[@id='input-shipping-postcode']")
    WebElement txtPostcode;

    @FindBy(xpath="//select[@id='input-shipping-country']")
    WebElement drpCountry;

    @FindBy(xpath="//select[@id='input-shipping-zone']")
    WebElement drpRegionState;

    // Continue Billing Details
    @FindBy(xpath="//button[@id='button-shipping-address']")
    WebElement btnContinueBilling;

    // Delivery Method
    @FindBy(xpath="//button[@id='button-shipping-methods']")
    WebElement shippingMethodRadio;
    
    @FindBy (xpath="//button[@id='button-shipping-method']")
    WebElement radioclick;
    
    @FindBy (xpath="//button[@id='button-payment-methods']")
    WebElement btnContinueDeliveryMethod;

    @FindBy (xpath="//button[@id='button-payment-method']")
    WebElement clickradio;
    
    @FindBy(xpath="//textarea[@id='input-comment']")
    WebElement txtDeliveryComment;


    // Confirm Order
    @FindBy(id = "button-confirm")
    WebElement btnConfirmOrder;

    // Confirmation message (optional)
    @FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
    WebElement confirmationMessage;

    // Methods
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    public void placeOrderWithShippingDetails(String firstName, String lastName, String address1, String city, String postcode, String country,String region, String comment) {
        // Fill shipping address details
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);

        txtLastName.clear();
        txtLastName.sendKeys(lastName);

        txtAddress1.clear();
        txtAddress1.sendKeys(address1);

        txtCity.clear();
        txtCity.sendKeys(city);

        txtPostcode.clear();
        txtPostcode.sendKeys(postcode);

        // Select country
        Select countryDropdown = new Select(drpCountry);
        countryDropdown.selectByVisibleText(country);

        // Select region/state
        wait.until(ExpectedConditions.elementToBeClickable(drpRegionState));
        
        Select regionDropdown = new Select(drpRegionState);
        regionDropdown.selectByVisibleText(region);
        
    }

        // Continue from shipping address
        
    public void continueFromShippingAddress() {
        btnContinueBilling.click();
    }
       
        // Click on shipping method radio if needed
    public void selectShippingMethod() {
        shippingMethodRadio.click();
        radioclick.click();
    }

        // Continue from delivery method
    public void continueFromDeliveryMethod() {
        btnContinueDeliveryMethod.click();
    }


        // Continue from payment method
    
    public void selectonclick() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(clickradio));
        clickradio.click();
    }

        // Optional: enter delivery comment
    
    public void enterDeliveryComment(String comment) {
    	wait.until(ExpectedConditions.elementToBeClickable(txtDeliveryComment));
        txtDeliveryComment.clear();
        txtDeliveryComment.sendKeys(comment);
    }

        // Confirm the order
    public void confirmOrder() {
    	{
    		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btnConfirmOrder);
    		  wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOrder));
    		  btnConfirmOrder.click();
    		}
    		
    }

        // Return confirmation message

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    }
