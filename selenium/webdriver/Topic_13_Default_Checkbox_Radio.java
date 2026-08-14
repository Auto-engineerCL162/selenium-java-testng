package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_13_Default_Checkbox_Radio {
    // Step 1_set up browser / page /...
    private static final Logger log = LoggerFactory.getLogger(Topic_13_Default_Checkbox_Radio.class);
    WebDriver driver;


    // Step 2_TC/ Execute
    @BeforeClass
    public void initBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        // chọn
        By cancerCheckbox = By.xpath("//label[contains(text(),'Cancer')]/preceding-sibling::input");
        if(!driver.findElement(cancerCheckbox).isSelected()) {
            driver.findElement(cancerCheckbox).click();
        }
        // verify chọn
            Assert.assertTrue(driver.findElement(cancerCheckbox).isSelected());
            Thread.sleep(3000);

        // bỏ chọn
        if(driver.findElement(cancerCheckbox).isSelected()) {
            driver.findElement(cancerCheckbox).click();
        }
        // verify bỏ chọn
            Assert.assertFalse(driver.findElement(cancerCheckbox).isSelected());
            Thread.sleep(3000);

        // Chọn hết
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));
        for(WebElement checkbox : allCheckboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        //verify chọn hết
        for(WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
            }

        // Bỏ chọn hết
        for(WebElement checkbox : allCheckboxes) {
            if(checkbox.isSelected()) {
                checkbox.click();
            }
        }

        //verify bỏ chọn hết
        for(WebElement checkbox : allCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        //chọn bất kì
        for(WebElement checkbox : allCheckboxes) {
            if(!checkbox.isSelected() && checkbox.getAttribute("value").equals("Heart Attack")) {
                checkbox.click();
            }
        }


    }


    @Test
    public void TC_02_Radio() throws InterruptedException {
        driver.get("https://material.angular.dev/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminateCheckbox = By.xpath("//lable[text()='Indeterminate']/preceding-sibling::div/input");
        By disabledCheckbox = By.xpath("//lable[text()='Disabled']/preceding-sibling::div/input");
        By resultCheckbox = By.xpath("//lable[contains(text(),\"I'm a checkbox\")]/preceding-sibling::div/input");
        By afterRadio = By.xpath("//lable[contains(text(),'After')]/preceding-sibling::div/input");
        By beforeRadio = By.xpath("//lable[contains(text(),'Before')]/preceding-sibling::div/input");

        // verify checkbox deselected
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(disabledCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(resultCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(afterRadio).isSelected());

        // click on checkbox
        driver.findElement(checkedCheckbox).click();
        driver.findElement(indeterminateCheckbox).click();
        driver.findElement(disabledCheckbox).click();

        //verify checkbox is selected
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(disabledCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(resultCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(resultCheckbox).isEnabled());
        Assert.assertTrue(driver.findElement(beforeRadio).isSelected());

        //Verify checkbox / radio disabled and deselected
        Assert.assertFalse(driver.findElement(resultCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(afterRadio).isEnabled());

    }

    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser() {
        driver.quit();
    }
}