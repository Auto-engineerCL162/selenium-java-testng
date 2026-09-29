package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_ShadowDOM {
    // Step 1_set up browser / page /...
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Github(){

        driver.get("https://automationfc.github.io/shadow-dom/");

        //Go into first shadow > find first shadow root and go into dom to get text
        WebElement shadowHostFirst = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootFirst = shadowHostFirst.getShadowRoot();

        String someText = shadowRootFirst.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);

        // Go into second shadow > find second shadow root and go into dom to get text
        WebElement shadowHostSecond = shadowRootFirst.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowRootSecond = shadowHostSecond.getShadowRoot();

        String nestedText =shadowRootSecond.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);

        shadowRootFirst.findElement(By.cssSelector("input[type='text']")).sendKeys("Automation Testing");
    }

    @Test
    public void TC_02_Shop(){
        driver.get("https://shop.polymer-project.org/");

        SearchContext shadowRootFirst = driver.findElement(By.cssSelector("shop-app[page='home']")).getShadowRoot();

        SearchContext shadowRootSecond = shadowRootFirst.findElement(By.cssSelector("shop-home.iron-selected")).getShadowRoot();

        shadowRootSecond.findElement(By.cssSelector("shop-button>a[aria-label=\"Men's Outerwear Shop Now\"]")).click();

        System.out.println(driver.getCurrentUrl());
    }

//    @Test
//    public void TC_03_Saleforce() throws InterruptedException {
//        driver.get("https://developer.salesforce.com/free-trials");
//
//        Thread.sleep(5000);
//// Ko nen lay shadow root từ xa nen lay element ngay tren shadow root
//        WebElement shadowHostFirst = driver.findElement(By.cssSelector("dx-global-header"));
//        SearchContext shadowRootFirst = shadowHostFirst.getShadowRoot();
//
//        WebElement shadowHostSecond = shadowRootFirst.findElement(By.cssSelector("hgf-c360nav"));
//        SearchContext shadowRootSecond = shadowHostSecond.getShadowRoot();
//
//        WebElement shadowHostThird = shadowRootSecond.findElement(By.cssSelector("div.desktop-cta>hgf-button"));
//        SearchContext shadowRootThird = shadowHostThird.getShadowRoot();
//
//        shadowRootThird.findElement(By.cssSelector("a.hgf-button")).click();
//        Thread.sleep(3000);
//
//    }
    @Test
    public void TC_03_Salesforce() throws InterruptedException {
        driver.get("https://developer.salesforce.com/free-trials");

        // Đợi trang tải hoàn tất các component JS
        Thread.sleep(5000);

        // 1. Tìm Host Element đầu tiên thực sự chứa Shadow Root (hgf-c360nav)
        WebElement shadowHostFirst = driver.findElement(By.cssSelector("dx-global-header hgf-c360nav"));
        SearchContext shadowRootFirst = shadowHostFirst.getShadowRoot();

        // 2. Đi vào bên trong Shadow Root thứ nhất để tìm Host Element thứ hai (hgf-button)
        WebElement shadowHostSecond = shadowRootFirst.findElement(By.cssSelector("div.desktop-cta > hgf-button"));
        SearchContext shadowRootSecond = shadowHostSecond.getShadowRoot();

        // 3. Tìm thẻ <a> hoặc nút bấm bên trong Shadow Root thứ hai và click
        WebElement ctaButton = shadowRootSecond.findElement(By.cssSelector("a.hgf-button, button"));
        ctaButton.click();

        Thread.sleep(3000);
    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
