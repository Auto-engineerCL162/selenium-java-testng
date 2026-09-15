package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Actions_II {
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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_Double() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.toString().contains("Firefox")) {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
                    driver.findElement(By.xpath("//button[text()='Double click me']")));
            actions.pause(Duration.ofSeconds(3)).perform();
        }
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());

    }

    @Test
    public void TC_02_Right_click() throws InterruptedException {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        actions.pause(Duration.ofSeconds(3)).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        Thread.sleep(2000);

        driver.switchTo().alert().accept();
        actions.pause(Duration.ofSeconds(3)).perform();

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }

    @Test
    public void TC_03_DragDropHTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(2000);

        String loginBtnRgbColor = targetCircle.getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnRgbColor).asHex().toUpperCase(),"#03A9F4");

    }

    @Test
    public void TC_04_DragDropHTML5() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop");

        WebElement sourceRect = driver.findElement(By.cssSelector("div#column-a"));
        WebElement targetRect = driver.findElement(By.cssSelector("div#column-b"));

        actions.dragAndDrop(sourceRect, targetRect).perform();
        Thread.sleep(2000);



    }




    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
