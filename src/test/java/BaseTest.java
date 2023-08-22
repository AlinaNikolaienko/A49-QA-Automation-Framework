import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver = null;

    public String url = "https://qa.koel.app/";
    @BeforeSuite
    static void setupClass() {

        WebDriverManager.chromedriver().setup();


    }

    @BeforeMethod
    public void launchBrowser() throws InterruptedException {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().window().maximize();




    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void navigateToPage() {
        driver.get(url);
    }

    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("[type = 'email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("[type = 'password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
        //passwordField.sendKeys("te$t$tudent");
    }

    public void clickSubmitButton() {
        WebElement submitButton = driver.findElement(By.cssSelector("[type = 'submit']"));
        submitButton.click();

    }


    public void loginToKoel() {
        navigateToPage();
        provideEmail("alina.nikolaienko@testpro.io");
        //provideEmail("demo@testpro.io");
        providePassword("OPJKDUhA");
        //providePassword("te$t$tudent");
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type = 'submit']")));
        clickSubmitButton();
    }


}