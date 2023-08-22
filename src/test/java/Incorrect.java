import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Incorrect extends BaseTest {

    @Test
    public void InvalidEmail() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Step 1. Open koel login page
        String url = "https://qa.koel.app/";
        driver.get(url);

        //Step 2. Enter NOT existing username
        WebElement emailField = driver.findElement(By.cssSelector("[type = 'email']"));
        emailField.clear();
        emailField.sendKeys("invalidEmail@testpro.io");

        //Step 3. Enter correct password
        WebElement passwordField = driver.findElement(By.cssSelector("[type = 'password']"));
        passwordField.clear();
        passwordField.sendKeys("OPJKDUhA");
        //passwordField.sendKeys("te$t$tudent");

        //Step 4. Click Log In button
        WebElement submitButton = driver.findElement(By.cssSelector("[type = 'submit']"));
        submitButton.click();

        //WebElement avatarIcon = driver.findElement(By.cssSelector("[class='avatar']"));
        //Expected result: User should stay on Login page
        //Assert.assertTrue(avatarIcon.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();

    }
}
