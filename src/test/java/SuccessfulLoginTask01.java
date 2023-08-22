import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SuccessfulLoginTask01 extends BaseTest {
    @Test
    public void loginValidEmailPassword() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Step 1. Open koel login page
        String url = "https://qa.koel.app/";
        driver.get(url);

        //Step 2. Enter existing username
        WebElement emailField = driver.findElement(By.cssSelector("[type = 'email']"));
        emailField.clear();
        emailField.sendKeys("alina.nikolaienko@testpro.io");

        //Step 3. Enter correct password
        WebElement passwordField = driver.findElement(By.cssSelector("[type = 'password']"));
        passwordField.clear();
        passwordField.sendKeys("OPJKDUhA");
        //passwordField.sendKeys("te$t$tudent");

        //Step 4. Click Log In button
        WebElement submitButton = driver.findElement(By.cssSelector("[type = 'submit']"));
        submitButton.click();

        WebElement avatarIcon = driver.findElement(By.cssSelector("[class='avatar']"));
        //Expected result: User should be directed to home page
        Assert.assertTrue(avatarIcon.isDisplayed());
        //Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();

    }
}
