package webdriver;


import graphql.util.EscapeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_23_Wait_PII_FindElement {
    // Step 1_set up browser / page /...
    WebDriver driver;

    @BeforeClass
    public void initialBrowser () {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        // 1- FindElement/s bị ảnh hưởng bởi implicitWait
        // Nếu có set time out thì lấy đó làm mốc tổng time
        // Nếu ko set thì thì tổng time = 0
    }
    // Step 2_TC/ Execute
    @Test
    public void TC_01_FindElement() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
            // Xét 3 trường hợp để xem phản ứng và kết quả của từng trường hợp khi sử dụng các hàm
            // TH1: Tìm nhưng thấy 1 element
            // Trả ve element đó (first)
        driver.findElement(By.cssSelector("input#email")).click();

            // TH2: Tìm nhưng thấy nhiều elements
            // Trả về element đầu tiên - các element còn lại ko qtam
        System.out.println(driver.findElement(By.xpath("//input")).getDomAttribute("name"));

            // TH3: Tìm nhưng ko thấy element
            // lặp lại mỗi nửa s nếu tiìm thấy thì trả về element đầu tiên (ko chờ hết time còn lại)
            // Nếu lặp lại ko thấy cho đến khi hết time thì fail trả về lỗi exception NoSuchElement
        driver.findElement(By.cssSelector("input#selenium"));

    }
    @Test
    public void TC_02_FindElements() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        List<WebElement> elements ;
        // TH1: Tìm nhưng thấy 1 element => Trả về list chứa 1 element
        elements = driver.findElements(By.cssSelector("input#email"));
        System.out.println(elements.size());

        // TH2: Tìm nhưng thấy nhiều elements => Trả về 1 list chứa nhiều elements
        elements = driver.findElements(By.xpath("//input"));
        System.out.println(elements.size());

        // TH3: Tìm nhưng ko thấy element => Lặp lại đến hết time ko thấy thì trả về list rỗng
        elements = driver.findElements(By.cssSelector("input#selenium"));
        System.out.println(elements.size());
    }

    @Test
    public void TC_03(){

    }


    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
