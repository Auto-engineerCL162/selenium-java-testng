package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_PI_Element_Status {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    // Step 2_TC/ Execute
    @Test
    public void TC_01_Visible(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // ĐK 1: Elements có trên UI và có trong HTML
        // Email address error message xuất hiện

        // Wait
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("advice-required-entry-email")));

        // Verify
        driver.findElement(By.id("advice-required-entry-email")).isDisplayed();

    }
    @Test
    public void TC_02_Invisible_Element_Found(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();


        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();


        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("advice-required-entry-email")));
        Assert.assertFalse(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }
    @Test
    public void TC_03_Invisible_Element_Not_Found(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();


        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.switchTo().alert().accept();

        //ĐK 3: Ko có trên UI và ko còn trong HTML
        // Đi tìm element fail và tìm lại cho đến khi hết timeout => nên chưa chạy dc tới bước verify
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("advice-required-entry-email")));
      // Case false:  Assert.assertFalse(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }

    @Test
    public void TC_04_Presence(){
        // ĐK 1: Element có trên UI và có trong HTML
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("advice-required-entry-email")));

        // ĐK 2: Element ko có trên UI nhưng có trong HTML
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("advice-required-entry-email")));

        // Miễn sao có element trong HTML thì là presence
    }

    @Test
    public void TC_05_Stateless(){
        // Tại thời điểm A element đang xuất hiện = lưu element lại
        // Tại thời điểm B element ko còn xuất hiện trong HTML nữa = dùng element đã lưu tại thời điểm A check
        // Element đó đã stateless tại thời điểm B
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement emailErrorMessage = driver.findElement(By.id("advice-required-entry-email"));

        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.switchTo().alert().accept();

        // ĐK3: Element ko có trên UI and ko xuất hiện trong HTML
        driverWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));

    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
