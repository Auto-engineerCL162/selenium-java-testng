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
import java.util.Random;


public class Topic_11_Custom_Dropdown {
    // Step 1_set up browser / page /...
    WebDriver driver;
    WebDriverWait explicitWait;
    String firstName, lastName, employeeID, userName, password, passportNumber, passportComment;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");


    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // driver.get("http://www.facebook.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        firstName = "John";
        lastName = "Kennedy";
        userName = "john.phillip" + new Random().nextInt(999);
        password = "123456";
        passportNumber = "12441-1233-1414";
        passportComment = "1231/123fff/12";
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
        selectItemDropdownByCss("span#speed-button", "span#speed-button", "Slow");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");

        selectItemDropdownByCss("span#speed-button", "span#speed-button", "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");

        // Thao tác với Salutation
        selectItemDropdownByCss("span#salutation-button", "ul#salutation-menu div", "Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");

        selectItemDropdownByCss("span#salutation-button", "ul#salutation-menu div", "Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");

    }

    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("http://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemDropdownByCss("div.ui.fluid.selection", "div.visible.menu>div>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectItemDropdownByCss("div.ui.fluid.selection", "div.visible.menu>div>span", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("http://mikerodham.github.io/vue-dropdowns/");

        selectItemDropdownByCss("div.btn-group", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemDropdownByCss("div.btn-group", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

        selectItemDropdownByCss("div.btn-group", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

    }

    @Test
    public void TC_04_OrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");

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
        selectItemDropdown(By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//i"),
            By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"),"Vietnamese");

        // Verify text
        selectItemDropdown(By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//i"),
            By.xpath("//label[text()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"),"Single");

        // Verify text
        selectItemDropdown(By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//i"),
            By.xpath("//label[text()='Blood Type']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"),"B+");



    }

    @Test
    public void Topic_05_Editable_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        //Selectable
        selectItemDropdownByCss("div.ui.fluid.selection", "div.visible.menu>div>span", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");

        selectItemDropdownByCss("div.ui.fluid.selection", "div.visible.menu>div>span", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

        //Editable
        selectItemEditableDropdown("div.ui.fluid.selection", "div.visible.menu>div>span", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");

        selectItemEditableDropdown("div.ui.fluid.selection", "div.visible.menu>div>span", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
    }

//    @Test
//    public void TC_07_Honda() {
//        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
//
//        // Custom
//        selectItemDropdownByCss("button#selectize-input", "div.choose-car div.dropdown-menu>a", "CR-V L AWD (Đ");
//
//        // Default
//        selectItemDropdownByCss("select#province", "select#province>option", "Long An");
//        selectItemDropdownByCss("select#registration_fee", "select#registration_fee>option", "Khu vực II");
//    }
//



// hàm tái sử dụng
    public void selectItemDropdownByCss(String parentLocator, String childLocator, String itemValue) throws InterruptedException {
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

    public void selectItemEditableDropdown(String parentLocator, String childLocator, String itemValue) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).sendKeys();
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

    // hàm tái sử dụng
    public void selectItemDropdown(By parentLocator, By childLocator, String itemValue) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(2000);
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
