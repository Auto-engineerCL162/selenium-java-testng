package automationfc; // Lưu ý: Tên package này có thể thay đổi tùy thuộc vào dự án của bạn

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Element_Excercise_Register {
    WebDriver driver;


    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_SignUp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/?locale=en");

        // Empty Data
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "An email address must contain a");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#new_username~span.invalid-error")).getText(),
                "Please enter a value");

        // Invalid Email
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail@!$&com");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "An email address must contain a");

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123@454.345");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "The domain portion of the email");

        // Valid Email + Invalid Password
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.net");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());



        // Invalid password - number
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());



        // Invalid password - number
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("124123");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());



        // Invalid password - special char
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("*#&($");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        // Invalid password - 8 char
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("automation123");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        // Valid password -
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Automation123@");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}


