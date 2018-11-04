package negative;

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

public class AdminLoginNegativeTest {

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
    public void testAdminNegativeLogIn() {

        WebElement myAccount = driver.findElement(By.cssSelector("span.caret"));
        myAccount.click();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        assertEquals( "Register Account", driver.getTitle());



        WebElement agreePrivatePolicy = driver.findElement(By.xpath("//div[@class='pull-right']//input[@value='1']"));
        if (!agreePrivatePolicy.isSelected()) agreePrivatePolicy.click();
        Assert.assertTrue(agreePrivatePolicy.isSelected());

        WebElement clickButton = driver.findElement(By.xpath("//div[@class='pull-right']//input[@value='Continue']"));
        clickButton.click();


        String actualFirstNameErrorMessage = driver.findElement(By.cssSelector("input#input-firstname+div")).getText();
        assertEquals("First Name must be between 1 and 32 characters!", actualFirstNameErrorMessage);

        String actualLastNameErrorMessage = driver.findElement(By.cssSelector("input#input-lastname+div")).getText();
        assertEquals("Last Name must be between 1 and 32 characters!", actualLastNameErrorMessage);

        String actualEmailErrorMessage = driver.findElement(By.cssSelector("input#input-email+div")).getText();
        assertEquals("E-Mail Address does not appear to be valid!", actualEmailErrorMessage);

        String actualPhoneErrorMessage = driver.findElement(By.cssSelector("input#input-telephone+div")).getText();
        assertEquals("Telephone must be between 3 and 32 characters!", actualPhoneErrorMessage);

        String actualPasswordErrorMessage = driver.findElement(By.cssSelector("input#input-password+div")).getText();
        assertEquals("Password must be between 4 and 20 characters!", actualPasswordErrorMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }



}
