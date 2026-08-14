package webdriver;


import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Alert {
    // Step 1_set up browser / page /...
    WebDriver driver;
    Alert alert;
    Select select;

    JavascriptExecutor jsExecutor;
    WebDriverWait exiplitwait;
    Actions actions;



    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();

        //Khởi tạo sau driver vì tham số cần khởi tạo nó yêu cầu driver
        jsExecutor = (JavascriptExecutor) driver;
        exiplitwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Accept_Alert(){

    }
    @Test
    public void TC_02_Confirm_Alert(){

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
