package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_00_Template {
    // Step 1_set up browser / page /...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser () {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }
    // Step 2_TC/ Execute
    @Test
    public void TC_01(){

    }
    @Test
    public void TC_02(){

    }

    @Test
    public void TC_03(){

    }


    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
