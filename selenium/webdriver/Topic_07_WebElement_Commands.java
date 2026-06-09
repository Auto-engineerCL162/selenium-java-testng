package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_WebElement_Commands {
    // Chứa các hàm để tương tác với Browser

    WebDriver driver;

    WebElement element;

    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_WebElement(){
        driver.findElement(By.xpath("")).click();

        element = driver.findElement(By.xpath(""));

        //Click vào các element dạng:
        element.click();

        // Nhập liệu các element dạng:
        element.clear(); // xoá dữ liệu trước khi sendkey
        element.sendKeys("long@gmail.com");
        element.sendKeys(Keys.ENTER);

        driver.findElement(By.id(""))
                .findElement(By.cssSelector("dđ"))
                .findElement(By.id("Email"));


        driver.findElement(By.cssSelector("div.login-page div.customer" +
                "-blocks input#Email"));

        // tác dụng với form (sign up/ login/ search)
        // thẻ form
        driver.findElement(By.id("Email")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("Password")).sendKeys("12345");
        driver.findElement(By.id("Password")).submit();

        // Áp dụng cho tất cả các loại element
        // Kiểm tra 1 element có hiển thị hay ko
        // Size > 0: width/ height > 0 
        element.isDisplayed();

        element.isSelected();
        element.isEnabled();
    }
}
