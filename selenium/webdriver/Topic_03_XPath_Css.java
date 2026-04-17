package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_XPath_Css {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser ()
    {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_() {
        driver.findElement(By.xpath("//a[@title='My account']")).click();
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void cleanBrowser ()
    {
        driver.quit();
    }
}
