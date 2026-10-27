package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Topic_23_Wait_PVIII_Fluent {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;

    // 1 Khai báo
    FluentWait<WebDriver> driverFluent;


    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/dynamic-loading");

        waitAndFindElement("//div[@id='start']/button").click();

        Assert.assertEquals(waitAndGetText("//div[@id='finish']/h4"), "Hello World");

    }
    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countdown = waitAndFindElement("//div[@id='javascript_countdown_time']");
        Assert.assertTrue(countDownText(countdown));

    }

    private boolean countDownText(WebElement element) {
        FluentWait<WebElement> driverElement = new FluentWait<WebElement>(element);

        driverElement.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);

        return driverElement.until(new Function <WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element1) {
                System.out.println(element.getText());
                return element.getText().endsWith("00");
            }
        });
    }
    // 2 Khởi tạo
    //waitAndGetText("");

//        new FluentWait<WebDriver>(driver)
//                .withTimeout(Duration.ofSeconds(10))// Tổng Time = 10s
//                .pollingEvery(Duration.ofSeconds(100)) // 1/10s tìm lại điều kiện 1 lần nếu ko thấy
//                .ignoring(NoSuchElementException.class) // Nếu kiểm tra đk chưa đáp ứng và gặp exception thì ignore di
//                .until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver webDriver) {
//                return webDriver.findElement(By.cssSelector("")).getText();
//            }
//        });


    private String waitAndGetText(String xpathLocator) {
        FluentWait<WebDriver> driverFluent = new FluentWait<WebDriver>(driver);


         driverFluent.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);
        return driverFluent.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath(xpathLocator)).getText();
            }
        });
    }

    // Tìm 1 element
    // T là gì cần driver mới tìm element được
    // R là gì: cái cần lấy ra là WebElement
    private WebElement waitAndFindElementVisible(String xpathLocator) {
        FluentWait<WebDriver> driverFluent = new FluentWait<WebDriver>(driver);

        driverFluent.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);

        return driverFluent.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(xpathLocator)));

    }

    private WebElement waitAndFindElement(String xpathLocator) {
        FluentWait<WebDriver> driverFluent = new FluentWait<WebDriver>(driver);

        driverFluent.withTimeout(Duration.ofSeconds(longTimeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);

        return driverFluent.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath(xpathLocator));

            }
        });
    }


    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser() {
        driver.quit();
    }

    private long longTimeout = 15;
    private long pollingTime = 100;
}
