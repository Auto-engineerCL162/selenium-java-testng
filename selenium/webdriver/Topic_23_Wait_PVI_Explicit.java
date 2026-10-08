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

public class Topic_23_Wait_PVI_Explicit {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(40));

    }

    @Test
    public void TC_01_Implicit() throws InterruptedException {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        Assert.assertEquals(driver.findElement(By.cssSelector("span#ct100_ContentPlaceholder_Lable1")).getText(),
                "No Selected Dates to display.");

        driver.findElement(By.xpath("//td/a[text()='2']")).click();

        Assert.assertTrue(driver.findElement(By.xpath
                ("//span[@id='ct100_ContentPlaceholder_Lable1' and text()='Tuesday, June 2, 2026']")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("span#ct100_ContentPlaceholder_Lable1")).getText(),
                "Tuesday, June 2, 2026");
    }


    @Test
    public void TC_02_Explicit() throws InterruptedException {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //    WebElement textSelected = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"))
        //      Nếu gan bien va tim truoc khi chon sẽ la trang thai A và sau khi click da thay đổi trạng thái nên cần tìm lại, ko dc dùng biến trạng thái A để chạy

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),  "No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='2']")));
        driver.findElement(By.xpath("//td/a[text()='2']")).click();

        // Waiting Loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='2']")));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Friday, October 2, 2026");
    }

    @Test
    public void TC_03_MoreThan() {


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