package webdriver;


import org.bouncycastle.jcajce.provider.symmetric.Threefish;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Set;

public class Topic_18_Window {
    // Step 1_set up browser / page /...
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        System.out.println("Driver ID" +  driver.toString());


    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Auto() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // lay ra id window của tab dang active
        String githubId = driver.getWindowHandle();
        System.out.println("Github Window ID: " + githubId);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(2000);

        switchToWindowByID(githubId);

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Donald Trump");
        Thread.sleep(2000);
        switchToWindowByTitle("Selenium WebDriver");

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Facebook");

        driver.findElement(By.cssSelector("input[name='email'")).sendKeys("longnguyen@gmail.com");
        driver.findElement(By.cssSelector("input[name='pass'")).sendKeys("123456");

        closeAllWindows(githubId);
    }

    @Test
    public void TC_02_TechPanda(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();

        String mobileWindowID = driver.getWindowHandle();
        driver.findElement(By.cssSelector("button[title='Compare']")).click();

        switchToWindowByID(mobileWindowID);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();

        switchToWindowByTitle("Mobile");

        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
    }

    @Test
    public void TC_03_Havard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");

        String courseWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(2000);

        switchToWindowByID(courseWindowID);

        Assert.assertEquals(driver.findElement(By.cssSelector("header>h1")).getText(), "DCE Login Portal");

        closeAllWindows(courseWindowID);
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p.sam-wait__message")).getText(),
                "Authentication was not successful. Please try again.");

        driver.findElement(By.cssSelector("button.sam-wait__close")).click();
        Thread.sleep(2000);

        String courseName = "Data Science: An Artificial Ecosystem";

        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys(courseName);

        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2026");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");
        driver.findElement(By.cssSelector("button#search-button")).click();

        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__title")).getText(),courseName);
    }

    @Test
    public void TC_04_Selenium_4x() throws InterruptedException {
        // Page A
        driver.get("https://live.techpanda.org/index.php");

        // Page B
        driver.switchTo().newWindow(WindowType.TAB).get("https//admin-demo.nopcommerece.com" );

        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

        driver.findElement(By.cssSelector("button.login-button")).click();

        switchToWindowByTitle("Home page");

        // Page A
        driver.findElement(By.xpath("a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();
    }



// Apply when only have 2 tabs
       private void switchToWindowByID(String windowID){
            Set<String> allIDs = driver.getWindowHandles();
            for (String id : allIDs) {
                if (!id.equals(windowID)) {
                    driver.switchTo().window(id);
                    break;
                }
            }
        }

        private void switchToWindowByTitle(String Title){
            Set<String> allIDs = driver.getWindowHandles();
            for (String id : allIDs) {
                System.out.println("windowID = " + id);
                driver.switchTo().window(id);

                String pageTitle = driver.getTitle();
                System.out.println("pageTitle = " + pageTitle);
                if (pageTitle.equals(Title)) {
                    break;
                }
            }
        }

        private void closeAllWindows(String WindowID){
            Set<String> allIDs = driver.getWindowHandles();
            for (String id : allIDs) {
                if (!id.equals(WindowID)) {
                    driver.switchTo().window(id);
                    driver.close();
                }
            }
            driver.switchTo().window(WindowID);
        }



    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
