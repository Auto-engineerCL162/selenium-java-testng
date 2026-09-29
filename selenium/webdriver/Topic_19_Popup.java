package webdriver;


import org.bouncycastle.oer.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Popup {
    // Step 1_set up browser / page /...
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // re-find element each 0.5s and will end section after 30s
        setImplicitTimeout(longTimeout);

    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_DeHieu() throws InterruptedException {
        driver.get("http://dehieu.vn/");
       Thread.sleep(3000);

        WebElement registerPopup = driver.findElement(By.cssSelector("div.modal-dialog"));
        if (registerPopup.isDisplayed()){
            // Close popup
            driver.findElement(By.cssSelector("div.modal-dialog button.close")).click();
        }
        String courseName = "Khóa học Lập Trình PLC Mitsubishi";
        driver.findElement(By.cssSelector("input.search-form")).sendKeys(courseName);
        driver.findElement(By.cssSelector("i.fa-search")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("h3.title>a")).getDomAttribute("title"), courseName);

    }


    @Test
    public void TC_02_VNK_InDOM() throws InterruptedException {
        driver.get("http://vnk.edu.vn/");
        WebElement marketingPopup = driver.findElement(By.cssSelector("div.pum-container"));
        if (marketingPopup.isDisplayed()){
            // Close popup
            driver.findElement(By.cssSelector("button.pum-close")).click();
            Thread.sleep(2000);
            System.out.println("Closed popup");
        }
        driver.findElement(By.cssSelector("button.btn-danger")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://vnk.edu.vn/lich-khai-giang/");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(),
                "Lịch Khai Giảng Trung Tâm VNK EDU");


    }


    @Test
    public void TC_03_KMPlayer_InDOM() throws InterruptedException {
        driver.get("http://www.kmplayer.com/home");

        closePopups();

        new Select(driver.findElement(By.cssSelector("select#selectLang"))).selectByVisibleText("한국어");

        closePopups();

    }
    private void closePopups() throws InterruptedException {

        By popup1Close = By.cssSelector("span.notranslate");
        By popup2Close = By.cssSelector("span.close_icon");

        List<WebElement> popup1 = driver.findElements(popup1Close);

        if (!popup1.isEmpty() && popup1.get(0).isDisplayed()) {
            popup1.get(0).click();
            Thread.sleep(1000);
        }

        List<WebElement> popup2 = driver.findElements(popup2Close);

        if (!popup2.isEmpty() && popup2.get(0).isDisplayed()) {
            popup2.get(0).click();
            Thread.sleep(1000);
        }
    }

    @Test
    public void TC_03_1_KMPlayer_InDOM() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");

        WebElement popupContainer = driver.findElement(By.cssSelector("div.pop-container"));
        if (popupContainer.isDisplayed()) {
            driver.findElement(By.cssSelector("span.close_icon")).click();
            Thread.sleep(3000);
            System.out.println("Popup is displayed and closed!!!");
        }

        new Select(driver.findElement(By.cssSelector("select#selectLang"))).selectByVisibleText("한국어");


        popupContainer = driver.findElement(By.cssSelector("div.pop-container"));
        if (popupContainer.isDisplayed()) {
            driver.findElement(By.cssSelector("span.close_icon")).click();
            Thread.sleep(3000);
            System.out.println("Popup is displayed and closed!!!");
        }
    }

    @Test
    public void TC_04_Tiki_InDOM() throws InterruptedException {
        driver.get("https://tiki.vn");
        Thread.sleep(5000);

        //Case 1: If you have popup, close it and go to next step
        //Case 2: If you do not have popup, go to next step

        /*
           Với trường hợp popup ko có trong HTML thì ko thể dùng findElement được vì sẽ trả về noexception và lỗi
           Trươờng hợp này nên dùng findElements và gán vào chuỗi để xác nhận nếu chuỗi rỗng thì trả về false và chạy step2
         */
        setImplicitTimeout(shortTimeout);
        List<WebElement> popupContainer = driver.findElements(By.cssSelector("div#VIP_BUNDLE"));
        setImplicitTimeout(longTimeout);

        // Step 1
        if (!popupContainer.isEmpty() && popupContainer.get(0).isDisplayed()) {
            driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
            Thread.sleep(3000);
            System.out.println("Popup is displayed and closed!!!");
        } else  {
            System.out.println("Popup is not displayed");
        }
//        try {
//            setImplicitTimeout(shortTimeout);
//            if (driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed()) {
//                driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
//                Thread.sleep(3000);
//                System.out.println("Popup is displayed and closed!!!");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println("Popup is not displayed!!!");
//        } finally {
//            setImplicitTimeout(longTimeout);
//        }
        // Step 2
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        WebElement loginPopup = driver.findElement(By.cssSelector("div.ReactModal__Content"));
        Assert.assertTrue(loginPopup.isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' " +
                "and text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' " +
                "and text()='Mật khẩu không được để trống']")).isDisplayed());

        driver.findElement(By.cssSelector("button.btn-close>img")).click();
        Thread.sleep(2000);

        // ko chạy dc sẽ fail
        //Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        setImplicitTimeout(shortTimeout);
        Assert.assertEquals(driver.findElements(By.cssSelector("p.login-with-email")).size(),0);
        setImplicitTimeout(longTimeout);



    }

    @Test
    public void TC_05_MaiPhuong_InDOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.MuiDialog-container>div"));

        Assert.assertTrue(driver.findElement(By.cssSelector("div.MuiDialog-container>div")).isDisplayed());
        Assert.assertTrue(!loginPopup.isEmpty() && loginPopup.get(0).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("longnguyen@gmail.com");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("123456");

        driver.findElement(By.xpath("//form//button[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(),
                "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        driver.findElement(By.cssSelector("button.close-btn")).click();

        setImplicitTimeout(shortTimeout);
        loginPopup = driver.findElements(By.cssSelector("div.MuiDialog-container>div"));

        setImplicitTimeout(longTimeout);
        Assert.assertTrue(loginPopup.size() == 0 && loginPopup.isEmpty());
    }
    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
    driver.quit();
    }

    private void setImplicitTimeout(long timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }
    private long shortTimeout = 5;
    private long longTimeout = 30;
}
