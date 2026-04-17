package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();

        driver.get("http://demo.nopcommerce.com/register");
    }


    @Test
    public void TC_01_ID () throws InterruptedException {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");
        Thread.sleep(3000);

        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        Thread.sleep(3000);
    }
    @Test
    public void TC_02_Class() throws InterruptedException {
        // ko có khoảng trắng mới lấy toàn bộ
        // có khoảng trắng thi lấy phan duy nhất
        driver.findElement(By.className("register-next-step-button")).click();
        Thread.sleep(3000);
    }

    @Test
    public void TC_03_Name() throws InterruptedException {
        driver.findElement(By.name("DateOfBirthDay"));
        driver.findElement(By.name("DateOfBirthMonth"));
        driver.findElement(By.name("DateOfBirthYear"));

    }

    @Test
    public void TC_04_LinkText() throws InterruptedException {
        driver.findElement(By.linkText("Register"));
        driver.findElement(By.linkText("Log In"));
        driver.findElement(By.linkText("WishList"));
    }

    @Test
    public void TC_05_Partial_Link_Text()  {
        // tìm phần tử có text dạng mapping 1 phần
        driver.findElement(By.partialLinkText("Register"));
        driver.findElement(By.partialLinkText("Digital"));
        driver.findElement(By.partialLinkText("downloads"));
    }

    @Test
    public void TC_06_Tagname()  {
        // tìm phần tử theo tag name có sẵn
        driver.findElement(By.tagName("button"));
        driver.findElement(By.tagName("input"));
        driver.findElement(By.tagName("label"));
    }

    @Test
    public void TC_07_CSS() {
    driver.findElement(By.cssSelector("input#Company"));
    driver.findElement(By.cssSelector("#Company"));
    driver.findElement(By.cssSelector("input[id='Company']"));

    driver.findElement(By.cssSelector("button.register-next-step-button"));
    driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']"));

    driver.findElement(By.cssSelector("Select[name='DateOfBirthDay']"));
    driver.findElement(By.cssSelector("Select[name='DateOfBirthMonth']"));
    driver.findElement(By.cssSelector("Select[name='DateOfBirthYear']"));

    driver.findElement(By.cssSelector("a[href*='register?']"));
    driver.findElement(By.cssSelector("a[href*='login?']"));

    driver.findElement(By.cssSelector("a"));
    driver.findElement(By.cssSelector("button"));
    driver.findElement(By.cssSelector("input"));
    }

    @Test
    public void TC_07_Xpath()  {
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        // lấy 1 phần mapping
        driver.findElement(By.xpath("//button[contains(@class,'register-next-step-button')]"));

//        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
//        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
//        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));

        driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Shipping')]"));
        driver.findElement(By.xpath("//a[contains(text(),'& returns')]"));
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//button"));
        driver.findElement(By.xpath("//input"));

    }

    @Test
    public void TC_08_Relative_Locator()  {
        driver.get("http://demo.nopcommerce.com/login");
        // Element /By A
        By passwordTextboxBy = By.cssSelector("input#Password");
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));

        // Element /By B
        By remmemberMeCheckboxBy = By.id("RememberMe");

        // Element /By C
        By ForgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        // Element /By D
        By loginButtonBy = By.cssSelector("button.login-button");

        // Element /By E
        driver.findElements(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy) // label nằm trên login button
                .below(passwordTextbox) // label nằm dưới password textbox
                .toRightOf(remmemberMeCheckboxBy) // label nằm bên phải remmember me checkbox
                .toLeftOf(ForgotPasswordLinkBy) // label nằm bên trái forgot pw link
        );
            // sử dụng khi ko thể định vị element bằng cách thông thường
            // sử dụng khi test GUI (giao diện và position khớp design)

    }


    @AfterClass
    public void clearBrowser (){
        driver.quit();
    }
}
