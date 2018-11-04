package positive;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AdminLoginTest {
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAdminPositiveLogIn() {
        WebElement myAccount = driver.findElement(By.cssSelector("span.caret"));
        myAccount.click();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        assertEquals( "Register Account", driver.getTitle());


        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("Nadezhda");

        WebElement lastName = driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Hristeva");

        WebElement email = driver.findElement(By.id("input-email"));
        email.sendKeys("nadysthad@hotmail.com");

        WebElement telephonenumber = driver.findElement(By.id("input-telephone"));
        telephonenumber.sendKeys("0035988742568");

        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("1234");

        WebElement passwordConfirm = driver.findElement(By.id("input-confirm"));
        passwordConfirm.sendKeys("1234");

        WebElement subscribeYes = driver.findElement(By.xpath("//label[@class='radio-inline']//input[@value='1']"));
        if (!subscribeYes.isSelected()) subscribeYes.click();
        Assert.assertTrue(subscribeYes.isSelected());

        WebElement agreePrivatePolicy = driver.findElement(By.xpath("//div[@class='pull-right']//input[@value='1']"));
        if (!agreePrivatePolicy.isSelected()) agreePrivatePolicy.click();
        Assert.assertTrue(agreePrivatePolicy.isSelected());

        WebElement clickButton = driver.findElement(By.xpath("//div[@class='pull-right']//input[@value='Continue']"));
        clickButton.click();

        assertEquals("Your account was not created!", "Your Account Has Been Created!", driver.getTitle());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}