package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    // Step 1_set up browser / page /...
    WebDriver driver;


    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();

        driver = new ChromiumDriver();
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01(){

    }
    @Test
    public void TC_02(){
        // Lấy ra tất cả ID của tất cả các tab/ window đang có
        driver.getWindowHandles();

        // Đi tìm 1 element
        driver.findElement(By.xpath(""));

        // Đi tìm n element
        driver.findElements(By.xpath(""));

        WebDriver.Options options = driver.manage();

        // Selenium ver 3
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        options.timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        WebDriver.Timeouts  timeouts = driver.manage().timeouts();


        // Selenium ver 4
        // Dùng để chờ cho việc tìm element (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        timeouts.implicitlyWait(Duration.ofSeconds(15));


        // Dùng để chờ cho việc page dc load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        //Dùng để chờ cho 1 đoạn script dc thực thi xong
        // JavascriptExecutor -js
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));


        WebDriver.Window window =driver.manage().window();


        // Thu nhỏ về taskbar để chạy
        driver.manage().window().maximize();
        window.maximize();

        // tràn màn hình vẫn có taskbar
        driver.manage().window().maximize();

        // tràn màn hình ko có taskbar
        driver.manage().window().fullscreen();

        //test GUI: graphic,
        // fon, color, size, position
        driver.manage().window().setSize(new Dimension(1366, 768);
        driver.manage().window().getSize();

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();


        //Lấy hết tất cả cookie
        driver.manage().getCookies();

        // Lấy cookie ở tab debuger -> cookie
        driver.manage().getCookieNamed("...");

        //Xoá cookie
        driver.manage().deleteAllCookies();

        for (Cookie cookie: cookie){
            // xoá cookie theo thứ tự
            driver.manage().deleteCookie(cookie);
        }


        // Xoá cookie theo tên
        driver.manage().deleteCookieNamed("Name");

        // đến 1 tc khác ... (ko cần login - set cookie cũ r refresh
        for (Cookie cookie : cookie){
            driver.manage().addCookie(cookie);
        }

        driver.navigate().refresh(); // Login thành công

        Logs log = driver.manage();






















    }
    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}



