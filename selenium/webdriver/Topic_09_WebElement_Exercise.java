package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebElement_Exercise {
    // Step 1_set up browser / page /...
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();

        driver.get("http://demo.nopcommerce.com");
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01_Displayed() {
        // isDisplayed kiểm tra 1 element nào có thể tương tác dc
        // hiển thị có kích thước cụ thể
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));

        if (emailTextbox.isDisplayed()) {
            System.out.println("Email Textbox is displayed");
            emailTextbox.sendKeys("Automation Testing");
        } else {
            System.out.println("Email Textbox is not displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#email"));
        if (ageUnder18Radio.isDisplayed()) {
            System.out.println("Age Under 18 Radio is displayed");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age Under 18 Radio is not displayed");

            WebElement educationTextArea = driver.findElement(By.cssSelector("textarea#edu"));
            if (educationTextArea.isDisplayed()) {
                System.out.println("Education TextArea is displayed");
                educationTextArea.sendKeys("Automation Testing");
            } else {
                System.out.println("Education TextArea is not displayed");
            }

            WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
            if (user5Text.isDisplayed()) {
                System.out.println("User5Text is displayed");
            } else {
                System.out.println("User5Text is not displayed");
            }

        }
    }

    @Test
    public void TC_02_Enabled() {
        // isDisplayed kiểm tra 1 element nào có thể tương tác dc
        // hiển thị có kích thước cụ thể
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));

        if (emailTextbox.isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email Textbox is not enabled");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#email"));
        if (ageUnder18Radio.isEnabled()) {
            System.out.println("Age Under 18 Radio is enabled");
        } else {
            System.out.println("Age Under 18 Radio is not enabled");
        }


        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
        if (passwordTextbox.isEnabled()) {
            System.out.println("Password Textbox is enabled");
        } else {
            System.out.println("Password Textbox is not enabled");
        }


        WebElement biographyTextArea = driver.findElement(By.cssSelector("input#disable_password"));
        if (biographyTextArea.isEnabled()) {
            System.out.println("Biography Text Area is enabled");
        } else {
            System.out.println("Biograpy Text Area is not enabled");
        }
    }
        @Test
        public void TC_03_Selected () {
            //: Kiểm tra 1 element được chọn thành công (radio/ checkbox/)
            driver.get("https://automationfc.github.io/basic-form/index.html");

            WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
            if (ageUnder18Radio.isSelected()) {
                System.out.println("Age Under 18 Radio is selected");
            } else {
                System.out.println("Age Under 18 Radio is not selected");
            }

            WebElement interestCheckbox = driver.findElement(By.cssSelector("input#development"));
            if (interestCheckbox.isSelected()) {
                System.out.println("Interest Checkbox is selected");
            } else {
                System.out.println("Interested Checkbox is de-selected");
            }

            ageUnder18Radio.click();
            interestCheckbox.click();

            if (ageUnder18Radio.isSelected()) {
                System.out.println("Age Under 18 Radio is selected");
            } else {
                System.out.println("Age Under 18 Radio is de-selected");
            }

            if (interestCheckbox.isSelected()) {
                System.out.println("Interest Checkbox is selected");
            } else {
                System.out.println("Interested Checkbox is de-selected");
            }




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