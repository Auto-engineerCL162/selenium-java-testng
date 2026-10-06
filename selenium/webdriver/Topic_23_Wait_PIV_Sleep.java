package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_PIV_Sleep {
    // Step 1_set up browser / page /...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        // 1- FindElement/s bị ảnh hưởng bởi implicitWait
        // Nếu có set time out thì lấy đó làm mốc tổng time
        // Nếu ko set thì thì tổng time = 0
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_NoSet() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");


    }

    @Test
    public void TC_02_LessThan() throws InterruptedException {
//        Thread.sleep(5000);
//        Thread.sleep(Duration.ofSeconds(5).toMillis());
//        Thread.sleep(Duration.ofMillis(5000).toMillis());

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        sleepInSecond(5);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }


    @Test
    public void TC_03_Equal() {
     //    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        sleepInSecond(5);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_04_MoreThan() {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        sleepInSecond(10);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }


    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser() {
        driver.quit();
    }

    public void sleepInSecond(long timesInSecond) {
        try {
            Thread.sleep(Duration.ofSeconds(timesInSecond).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}