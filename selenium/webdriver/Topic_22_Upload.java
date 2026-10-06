package webdriver;


import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.devtools.v143.network.Network;
import org.openqa.selenium.devtools.v143.network.model.ConnectionType;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class Topic_22_Upload {
    // Step 1_set up browser / page /...
    WebDriver driver;
    String uploadFilePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String firstImage = "TestImg1.jpg";
    String secondImage = "TestImg2.jpg";
    String thirdImage = "TestImg3.jpg";

    String firstImagePath = uploadFilePath + firstImage;
    String secondImagePath = uploadFilePath + secondImage;
    String thirdImagePath = uploadFilePath + thirdImage;

    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_SingleFile() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(secondImagePath);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(thirdImagePath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + secondImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thirdImage + "']")).isDisplayed());

        List<WebElement> starButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement star : starButtons) {
            star.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + secondImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thirdImage + "']")).isDisplayed());
    }

    @Test
    public void TC_02_MultipleFiles() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath + "\n" + secondImagePath + "\n" + thirdImagePath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + secondImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thirdImage + "']")).isDisplayed());

        List<WebElement> starButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement star : starButtons) {
            star.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + firstImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + secondImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thirdImage + "']")).isDisplayed());
    }
    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
