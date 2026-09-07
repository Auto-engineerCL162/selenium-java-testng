package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Actions {
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

    }

    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();

        actions.pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_CoinMarketCap() throws InterruptedException {
        driver.get("https://coinmarketcap.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[text()='Dashboards']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // web element click => click dưới dạng người dùng click trên UI
        driver.findElement(By.xpath("//div[@class='section']//a[text()='Bitcoin ETFs']")).click();

        // action click click dưới dạng move chuột để click -> có the sai xot
        //actions.click(driver.findElement(By.xpath("//div[@class='section']//a[text()='Bitcoin ETFs']"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//div/a[text()='BTC ETFs']")).isDisplayed());


    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        actions.pause(Duration.ofSeconds(10)).perform();


        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        actions.moveToElement(driver.findElement(By.xpath(
                "//span[@class='menu-title' and text()='Sách Trong Nước']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        actions.click(driver.findElement(By.xpath(
                "//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        Assert.assertTrue(driver.findElement(By.xpath(
                "//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
    }

    @Test
    public void TC_04_Click_and_hold_BLOCK() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        actions.clickAndHold(numbers.get(5))
        .moveToElement(numbers.get(23))
        .release().perform();

        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(),15);
    }

    @Test
    public void TC_04_Click_and_hold_RANDOM() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        String osName = System.getProperty("os.name");
// cú pháp: biến = điều kiện ? kq 1 ? kq 2
        Keys keys = osName.contains("Windows") ? Keys.CONTROL : Keys.COMMAND;

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        actions.keyDown(keys).perform();
        actions.click(numbers.get(11))
                .click(numbers.get(13))
                .click(numbers.get(20))
                .click(numbers.get(24))
                .click(numbers.get(25))
                .perform();
        actions.keyUp(keys).perform();

        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(),5);
    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
