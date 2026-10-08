package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_PV_Explicit_Method {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        // Khởi tạo 1 biến WebdriverWait với tổng tg là 5s - polling time là 0.5s (default)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Khởi tạo 1 biến WebdriverWait với tổng tg là 5s - polling time custom là 0.1s
        explicitWait =  new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(100));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Method() {
        // ***    // Visible : chờ cho 1 hoặc nhiều element xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

                // Wait nhieu element khac nhau
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("input#Email")),
                driver.findElement(By.cssSelector("input#Password"))));
                // Wait nhieu element giong nhau
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(""))));


        // ***     // Invisible
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

            // Wait nhieu element khac nhau
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("input#Email")),
                driver.findElement(By.cssSelector("input#Password"))));
            // Wait nhieu element giong nhau
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));


        // Presence + Stateless
        explicitWait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.cssSelector(""))));


        // *** // Clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable((By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(""))));

        // Selected
        explicitWait.until(ExpectedConditions.elementToBeSelected((By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.cssSelector(""))));

        // Frame / Alert
        explicitWait.until(ExpectedConditions.alertIsPresent());
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

        // And/ or / not
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(""))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("")))));

        explicitWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(""))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("")))));

        explicitWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // Title / URL
        explicitWait.until(ExpectedConditions.titleIs(""));
        explicitWait.until(ExpectedConditions.titleContains(""));

        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));

        // Attribute / Property
        explicitWait.until(ExpectedConditions.attributeToBe(driver.findElement(By.cssSelector("")),"", ""));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"",""));






    }

    @Test
    public void TC_02_LessThan() throws InterruptedException {


    }


    @Test
    public void TC_03_Equal() {


    }

    @Test
    public void TC_04_MoreThan() {


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