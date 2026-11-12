package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

public class Topic_23_Wait_PVIII_Page_Ready {
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

    @Test
    public void TC_01() {
        driver.get("https://admin-demo.nopcommerce.com/admin/");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(isPageLoadSuccess());

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());
    }

    // WebDriverWait
        public boolean isPageLoadSuccess() {
            FluentWait<WebDriver> fluentWait = new FluentWait(driver);
            fluentWait.withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(JavascriptException.class);

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Điều kiện 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        // Điều kiện 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @Test
    public void TC_02() {
        driver.get("https://api.orangehrm.com/");

    }

//    public boolean isLoadingIconInvisible() {
//        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(15), Duration.ofMillis(100));
//        return fluentWait.until(new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                try {
//                    return !driver.findElement(By.cssSelector("div.spinner")).isDisplayed();
//                } catch (NoSuchElementException | StaleElementReferenceException elementDisappeared) {
//                    return true;
//                }
//
//            }
//        });
//    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser() {
        driver.quit();
    }

}
