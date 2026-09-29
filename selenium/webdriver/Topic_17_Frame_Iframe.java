package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {
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
    public void TC_01_Iframe() throws InterruptedException {
//        driver.get("https://toidicodedao.com/");
//
//        // switch to frame = ìndex dễ bị thay đổi
////        driver.switchTo().frame(0);
////        Thread.sleep(5000);
//
//        // name / id thường ko duy nhất or ko có name
//        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));
//
//        driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']")).isDisplayed();
//        Assert.assertEquals(driver.findElement(By.xpath(
//                "//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(),"397,034 followers");
//
//        driver.switchTo().defaultContent(); // back về trang chủ
//
//        driver.switchTo().parentFrame();
        // back về cấp iframe trước nó , nếu đứng ở frame top r sẽ ko thay đổi (ví dụ khi A là main, iframe B là cấp 1, iframe C là cấp 2
        // thì từ C back về parent là B nếu đừng từ B thì sẽ ko dùng dc parent nữa vì nó là lớn nhất r

    }
    @Test
    public void TC_02_Jquery() throws InterruptedException {
        driver.get("https://jqueryui.com/dialog/");

        // switch to iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#content iframe.demo-frame")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-dialog")).isDisplayed());
        driver.findElement(By.cssSelector("button.ui-dialog-titlebar-close")).click();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.cssSelector("div.ui-dialog")).isDisplayed());

        // quay ve main
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input[name='s']")).sendKeys("Dialog");
        Thread.sleep(3000);
    }
   @Test
    public void TC_03_FormSite() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey")).click();
        Thread.sleep(2000);

        driver.switchTo().frame("form85593366");

        new Select(driver.findElement(By.xpath("//lable[contains(text(),'Year')]//following-sibling::select")))
                .selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.xpath("//lable[contains(text(),'Residence')]//following-sibling::select")))
                .selectByVisibleText("West Dorm");
        new Select(driver.findElement(By.xpath("//lable[contains(String(),'Private')]//following-sibling::select")))
                .selectByVisibleText("West Dorm");
    }
        @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
