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
import java.util.List;


public class Topic_11_Custom_Dropdown {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // driver.get("http://www.facebook.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

//        // THAO TÁC VỚI SPEED
//        //Click chuột vào 1 thẻ bên ngoài để cho nó xổ hết item ra
//        driver.findElement(By.cssSelector("span#speed-button")).click();
//
//        // chờ cho tất cả item dc load lên
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div")));
//
//        // duyệt qa từng item ktra text
//        List<WebElement> childSpeedItems = driver.findElements(By.cssSelector("ul#speed-menu div"));
//
//        // Duyệt qua từng cái
//        for (WebElement item: childSpeedItems){
//            //Kiểm tra từng cái
//            if (item.getText().equals("Faster")){
//                // lấy ra cái mình cần và thao tác
//                item.click();
//                break;
//            }
//        }
//
//        // THAO TÁC VỚI SALUTATION
//        driver.findElement(By.cssSelector("span#salutation-button")).click();
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#salutation-menu div")));
//        List<WebElement> childSalutationItems = driver.findElements(By.cssSelector("ul#salutation-menu div"));
//        for (WebElement item : childSalutationItems) {
//            if (item.getText().equals("Dr.")) {
//                item.click();
//                break;
//            }
//        }
        // Thao tác với speed
        selectItemDropdown("span#speed-button", "span#speed-button", "Slow");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");

        selectItemDropdown("span#speed-button", "span#speed-button", "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");

        // Thao tác với Salutation
        selectItemDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");

        selectItemDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");

    }

    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("http://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemDropdown("div.ui.fluid.selection", "div.visible.menu>div>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectItemDropdown("div.ui.fluid.selection", "div.visible.menu>div>span", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("http://mikerodham.github.io/vue-dropdowns/");

        selectItemDropdown("div.btn-group", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

        selectItemDropdown("div.btn-group", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

    }
// hàm tái sử dụng
    public void selectItemDropdown(String parentLocator, String childLocator, String itemValue) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();
        List<WebElement> childItems = explicitWait.until
                (ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        for (WebElement item : childItems) {
            if (item.getText().equals(itemValue)) {
                item.click();
                Thread.sleep(3000);
                break;
            }
        }
    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser() {
        driver.quit();
    }
}
