package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Topic_01_Check_Environment {
    WebDriver driver;


    @Test
    public void TC_01_Run_on_Firefox() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        // add new linedd //
        driver.quit();
    }

    @Test
    public void TC_02_Run_on_Chrome() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_03_Run_on_Edge() {
        driver = new EdgeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
        // test commit
    }

}