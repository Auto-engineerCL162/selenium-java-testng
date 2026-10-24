package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_23_Wait_PVI_Explicit {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;

    String uploadFilePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String firstImage = "TestImg1.jpg";
    String secondImage = "TestImg2.jpg";
    String thirdImage = "TestImg3.jpg";

    String firstImagePath = uploadFilePath + firstImage;
    String secondImagePath = uploadFilePath + secondImage;
    String thirdImagePath = uploadFilePath + thirdImage;

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
    public void TC_03_Upload() {
        driver.get("https://gofile.io");
        // Wait all element disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin.rounded-full"))));

        driver.findElement(By.xpath("//span[contains(@class,'truncate') and contains(text(),'File Manager')]")).click();

        // Waiting for File manager button clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//span[contains(@class,'hidden items-center')]//button[contains(@class,'btn-secondary')]")));

        driver.findElement(By.xpath("//span[contains(@class,'hidden items-center')]//button[contains(@class,'btn-secondary')]")).click();

       // explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//button[@class='btn-primary']"))));

       // driver.findElement(By.xpath("//button[@class='btn-primary']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#fm-new-folder-name"))).sendKeys("Selenium");

        driver.findElement(By.xpath("//button[@class='btn-primary']")).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements
                (driver.findElements(By.xpath("//p[contains(text(),'Creating “Selenium”…')]")))));

//        String successText = driver.findElement(By.xpath("//h3[text()='Success']/parent::div/following-sibling::div//p")).getText();
//        Assert.assertTrue(successText.equals("Folder") && successText.contains("Success") && successText.contains("created successfully!"));

        //String successText = driver.findElement(By.xpath("//p[contains(string(),'Folder “Selenium” created')]")).getText();
        //Assert.assertTrue(successText.contains("Folder") && successText.contains("Selenium") && successText.contains("created"));

       // driver.findElement(By.cssSelector("button[aria-label=Dismiss notification]")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Selenium']")));

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Selenium']")).isDisplayed());
        driver.findElement(By.xpath("//p[text()='Selenium']")).click();

        // Wait for all loading icons at current screen disappear
        // Đợi cho tiêu đề của thư mục selenium xh
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h1[@title='Selenium']"))));

        driver.findElement(By.cssSelector("#fm-list button[data-action=upload]")).click();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath + "\n" + secondImagePath + "\n" + thirdImagePath);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='upload-tray']//div[contains(@class,'mt-2.5')]")));
       // explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("div.animate-spin.rounded-full"))));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'truncate') and text()='" + secondImage + "']")));

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@class,'truncate') and text()='" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@class,'truncate') and text()='" + secondImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(@class,'truncate') and text()='" + thirdImage + "']")).isDisplayed());


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