package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static io.netty.util.internal.SystemPropertyUtil.contains;

public class Topic_12_Button {
    // Step 1_set up browser / page /...
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }


    // Step 2_TC/ Execute
    @Test
    public void TC_01_Huawei(){
        driver.get("http://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");

        By registerButton = By.cssSelector("div.hwid-btn-reg");

        //verify button disabled
        Assert.assertTrue(driver.findElement(registerButton).getDomProperty("className").contains("hwid-disabled"));
        Assert.assertTrue(driver.findElement(registerButton).getDomAttribute("class").contains("hwid-disabled"));

        //verify button text
        Assert.assertEquals(driver.findElement(registerButton).getText(),"REGISTER");

        //background color
        String registerBtnRgbColor = driver.findElement(registerButton).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(registerBtnRgbColor).asHex().toUpperCase(),"#CA141D");

//        // edge/ chrome
//        Assert.assertEquals(registerBtnRgbColor,"rgba(202, 20, 29, 1)");
//
//        //firefox
//        Assert.assertEquals(registerBtnRgbColor,"rgba(202, 20, 29)");

    }

    @Test
    public void TC_02_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();



        // tìm element th
        By loginButton = By.cssSelector("button.fhs-btn-login");

        // verify disabled
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        //background color
        String loginBtnRgbColor = driver.findElement(loginButton).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnRgbColor).asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("long@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        Thread.sleep(2000);

        //verify is enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
//        loginBtnRgbColor = driver.findElement(loginButton).getCssValue("background-color");
//        Assert.assertEquals(Color.fromString(loginBtnRgbColor).asHex().toUpperCase(),"#C92127");
    }


    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
