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

public class Topic_15_Alert {
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

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
                "You clicked an alert successfully");

//        // accept alert
//        alert.accept();
//
//        // dismiss alert
//        alert.dismiss();
//
//        // get text alert
//        alert.getText();
//
//        // input into alert
//        alert.sendKeys("Hello World");
    }

    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        driver.findElement(By.cssSelector("button.search-button")).click();

        // chờ alert xuất hiện và switch qua alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();
        Assert.assertTrue(alertMessage.contains("Samsung Galaxy"));
        Assert.assertTrue(alertMessage.contains("Samsung Galaxy"));

        alert.accept();
        Thread.sleep(3000);

        List<WebElement> products =  driver.findElements(By.cssSelector("ul.products-grid>li"));
        Assert.assertEquals(products.size(), 2);

    }

    @Test
    public void TC_03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String name = "Autommation FC";
        alert.sendKeys(name);
        Thread.sleep(3000);

        alert.accept();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
                "You entered: " + name);
    }

    @Test
    public void TC_04_Authentication_Alert() throws InterruptedException {

    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
