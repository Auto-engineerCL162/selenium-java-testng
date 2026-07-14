package webdriver;


import org.openqa.selenium.By;
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

public class Topic_10_Default_Dropdown {
    // Step 1_set up browser / page /...
    WebDriver driver;
    Select cityDropdown, districtDropdown;

    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01(){
        driver.get("https://egov.danang.gov.vn/reg");

        //khời tạo
        cityDropdown = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));
        //index dễ bị thay đổi dễ bị lỗi khi sửa xoá item
        // khó nhớ
        // reproduce bug khó
              // select.selectByIndex(4);
        //value - ít thay đổi
        // value ko phải 1 thuôc tính bắt buộc
        // dev thiết kế ko có value
        // khó nhớ dữ liệu
              // select.selectByValue("4091");

        // Kiểm tra có bao nhiêu option trong droplist
        int tinhThanhNumber = cityDropdown.getOptions().size();
        Assert.assertEquals(tinhThanhNumber, 67);

        // Kiểm tra dropdown là multiple/ single
        Assert.assertFalse(cityDropdown.isMultiple());

        //Chọn thành phố DN
        //Text
        // bị thay đổi thì text sẽ đc cập nhật => Lỗi
        // thêm xoá sửa dễ cập nhật ít lỗi
        // tc dễ reproduce khi có lỗi dễ giả lập dữ liệu
        cityDropdown.selectByVisibleText("thành phố Hà Nội");
        // Kiểm tra chọn thành công
        Assert.assertEquals(cityDropdown.getFirstSelectedOption().getText(), "thành phố Hà Nội");

        districtDropdown = new Select(driver.findElement(By.cssSelector("select#thuongtru_quanhuyen")));
        districtDropdown.selectByVisibleText("quận Tây Hồ");
        Assert.assertEquals(districtDropdown.getFirstSelectedOption().getText(), "quận Tây Hồ");

    }
    @Test
    public void TC_02(){
        driver.get("https://rode.com/en-au/support/where-to-buy");

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/h4"));

        for (WebElement dealer :  dealers) {
            System.out.println(dealer.getText());
        }
    }




    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
