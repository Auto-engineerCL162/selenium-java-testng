package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Textbox_TextArea_II {
    // Step 1_set up browser / page /...
    WebDriver driver;
    By loadingIcon = By.cssSelector("div.oxd-loading-spiner");
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();

        driver.get("https://opensource-demo.orangehrmlive.com");
        driver.manage().window().maximize();
        // chờ element xuất hiện
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Employee() {
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.
                invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Employee list
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();

        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.
                invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Add employee
        driver.findElement(By.xpath("//button[contains(string(),'Add')]")).click();
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.
                invisibilityOfAllElements(driver.findElements(loadingIcon))));

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("Long");
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys("Nguyen");

        //get employee ID
        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getDomProperty("value");
        System.out.println("Employee ID = " + employeeID);

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();

        // create new employee
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("long123");
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("long123@");
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("long123@");

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();


        // Verify text
       // Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-toast-content--success>p.oxd-text--toast-message")).getText(),"Successfully Saved");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-toast-content--success')]/p[text()='Successfully Saved']")).isDisplayed());


        //loading icon 1 (add new)
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.
                invisibilityOfAllElements(driver.findElements(loadingIcon))));


        //loading icon 2 (personal detail)
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.
                invisibilityOfAllElements(driver.findElements(loadingIcon))));


        }

        @Test
        public void TC_04_MailChimp_Register_Validate () {

        }


        @AfterClass
        // Step 3_Clean data test
        public void clearBrowser () {
            driver.quit();
        }

    }