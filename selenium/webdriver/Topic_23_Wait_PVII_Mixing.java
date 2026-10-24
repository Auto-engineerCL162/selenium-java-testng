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
import java.util.Date;

import static org.bouncycastle.oer.its.ieee1609dot2.CertificateType.explicit;

public class Topic_23_Wait_PVII_Mixing {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser () {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }
    // Step 2_TC/ Execute
    @Test
    public void TC_01_Only_Implicit(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            System.out.println("Start time=" + new Date().toString());
            driver.findElement(By.cssSelector("input[name='email]"));
        } catch  (Exception e) {
            throw new RuntimeException(e);
        } finally{
            System.out.println("End time=" + new Date().toString());
        }
    }

    @Test
    public void TC_02_Only_Explicit(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Start time=" + new Date().toString());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
        } catch  (Exception e) {
            throw new RuntimeException(e);
        } finally{
            System.out.println("End time=" + new Date().toString());
        }
    }

    @Test
    public void TC_03_Implicit_Explicit_By(){
        //Equal - Less - More
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Start time=" + new Date().toString());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
        } catch  (Exception e) {
            throw new RuntimeException(e);
        } finally{
            System.out.println("End time=" + new Date().toString());
        }

    }


    // wait theo tìm kiếm list element thì sẽ cộng dồn time
    // wait theo tìm kiếm by thì

    // Selenium khuyến cáo ko nên dùng tham số explicit là webElement / list webElement

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
