package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Authen_Alert {
    // Step 1_set up browser / page /...
    WebDriver driver;
    Alert alert;
    Select select;
    WebDriverWait explicitWait;

    JavascriptExecutor jsExecutor;
    WebDriverWait exiplitwait;
    Actions actions;



    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();

        //Khởi tạo sau driver vì tham số cần khởi tạo nó yêu cầu driver
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Authen_Alert() throws InterruptedException {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text(),"+
                "'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_02_Authen_Alert() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com");

        String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");
        String[] urlSplit = basicAuthenUrl.split("//");
        basicAuthenUrl =  urlSplit[0] + "//" + "admin:admin@" + urlSplit[1];

        driver.get(basicAuthenUrl);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text(),"+
                "'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_03_Authen_Alert() throws InterruptedException {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text(),"+
                "'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
