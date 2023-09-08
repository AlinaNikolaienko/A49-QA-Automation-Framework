package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    //constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[type = 'email']")
    private  WebElement emailFieldLocator;

    @FindBy(css = "[type = 'password']")
    private  WebElement passwordFieldLocator;

    @FindBy(css = "[type = 'submit']")
    private  WebElement submitButtonFieldLocator;


    /*private By emailFieldLocator = By.cssSelector("[type = 'email']");
    private By passwordFieldLocator = By.cssSelector("[type = 'password']");
    private By submitButtonFieldLocator = By.cssSelector("[type = 'submit']");*/

    public LoginPage provideEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldLocator));
        emailFieldLocator.clear();
        emailFieldLocator.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passwordFieldLocator));
        passwordFieldLocator.clear();
        passwordFieldLocator.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButtonFieldLocator));
        submitButtonFieldLocator.click();
        return this;
    }

    public void loginToKoel() {
        //navigateToPage();
        provideEmail("alina.nikolaienko@testpro.io");
        //provideEmail("demo@testpro.io");
        providePassword("OPJKDUhA");
        //providePassword("te$t$tudent");
        clickSubmitButton();
    }




    /*
    //without Page Factory
    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(emailFieldLocator);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(passwordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        WebElement submitButton = driver.findElement(submitButtonFieldLocator);
        submitButton.click();
    }
    */


}
