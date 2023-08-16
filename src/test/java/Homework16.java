import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework16 extends BaseTest {
    @Test
    public void registrationPage() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Step 1. Open koel login page
        String url = "https://qa.koel.app/";
        driver.get(url);

        //Step 2. Click the Registration link
        WebElement registrationButton = driver.findElement(By.xpath("//a[contains(text(), 'Registration / Forgot password')]"));
        registrationButton.click();

        //Step 3. Verify that you are redirected to Registration page using Assert method.
        String registrationUrl = "https://qa.koel.app/registration";
        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl );

        driver.quit();
        
    }
}
