package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_14_Custom_Checkbox_Radio {
    // Step 1_set up browser / page /...
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        //1- thẻ input bị ẩn ko click dc / dùng để verify dc
        // thẻ input có thể verified selected
//        By ubuntuAccountRadio = By.xpath("//label[@for='id_new_user']/preceding-sibling::input");
//        driver.findElement(ubuntuAccountRadio).click();
//        Thread.sleep(4000);

        //2- thẻ input ko dùng để click / dùng để verify
        // Dùng thẻ label để click
        // thẻ label ko verify dc
//        By ubuntuAccountRadio = By.xpath("//label[@for='id_new_user']");
//        driver.findElement(ubuntuAccountRadio).click();
//        Thread.sleep(4000);

//        Assert.assertTrue(driver.findElement(ubuntuAccountRadio).isSelected());

        //3- Thẻ label để chọn , thẻ input để verify
//        By ubuntuAccountRadioLabel = By.xpath("//label[@for='id_new_user']");
//        By ubuntuAccountRadioCheckbox = By.xpath("//label[@for='id_new_user']/preceding-sibling::input");
//        driver.findElement(ubuntuAccountRadioLabel).click();
//        Thread.sleep(2000);
//
//        Assert.assertTrue(driver.findElement(ubuntuAccountRadioCheckbox).isSelected());


        //4- Ko dùng click của selenium
        // click bằng JS hạn chế dùng, bắt buộc ms dùng
        // Vẫn define checkbox/ radio thành 1 locator (dùng jscript)
        By ubuntuAccountInput = By.xpath("//label[@for='id_new_user']/preceding-sibling::input");

        //Click
        jsExecutor.executeScript("arguments[0].click()",driver.findElement(ubuntuAccountInput));
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(ubuntuAccountInput).isSelected());
    }
    @Test
    public void TC_02_Google_Form() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDKjKqgvpID9kwO29UCzeCVrGGtbNPZXQokoJA/viewform");
        By canthoCity = By.cssSelector("div[aria-label='Cần Thơ']");

        //verify lúc chưa chọn
        Assert.assertEquals(driver.findElement(canthoCity).getDomAttribute("aria-checked"),"false");
        driver.findElement(canthoCity).click();
        Thread.sleep(3000);

        // verify đã chọn
        Assert.assertEquals(driver.findElement(canthoCity).getDomAttribute("aria-checked"),"true");


        //checkbox by Quang city
        By quangCity = By.cssSelector("div[aria-label= 'Mì Quảng']");
        Assert.assertEquals(driver.findElement(quangCity).getDomAttribute("aria-checked"),"false");
        driver.findElement(quangCity).click();
        Thread.sleep(3000);

        // verify đã chọn
        Assert.assertEquals(driver.findElement(quangCity).getDomAttribute("aria-checked"),"true");
    }
    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
