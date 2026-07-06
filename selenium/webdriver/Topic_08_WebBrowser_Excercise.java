package webdriver;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


@Test
//    public void TC_02_Title(){
//        driver.get("http://live.techpanda.org/");
//
//        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
//
//        Assert.assertEquals(driver.getTitle(), "Customer Login");
//
//        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
//
//        Assert.assertEquals(driver.getCurrentUrl(), "Create New Customer Account");
//    }

    public void TC_02_Login_empty(){
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.cssSelector("div.footer a[title='My account']")).click();

        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
    }



    @Test
    public void TC_03_Login_Invalid_Email(){
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.cssSelector("div.footer a[title='My account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("12341.2.44");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("121441");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");

    }
//    public void TC_03_Navigate(){
//        driver.get("http://live.techpanda.org/");
//
//        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
//
//        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
//
//        Assert.assertEquals(driver.getCurrentUrl(),
//                "http://live.techpanda.org/index.php/customer/account/create/");
//
//        driver.navigate().back();
//
//        Assert.assertEquals(driver.getCurrentUrl(),
//                "http://live.techpanda.org/index.php/customer/account/login/");
//
//        driver.navigate().forward();
//
//        Assert.assertEquals(driver.getTitle(),
//                "Create New Customer Account");
//
//    }


     @Test
//    public void TC_04_Page_Source(){
//        driver.get("http://live.techpanda.org/");
//
//        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//
//        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
//
//        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
//
//        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
//    }


    public void TC_04_Login_Invalid_Password() {
         driver.get("https://live.techpanda.org/index.php/");

         driver.findElement(By.cssSelector("div.footer a[title='My account']")).click();

         driver.findElement(By.cssSelector("input#email")).sendKeys("long@gmail.com");
         driver.findElement(By.cssSelector("input#pass")).sendKeys("121441");

         driver.findElement(By.cssSelector("button#send2")).click();

         Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),
                 "Please enter 6 or more characters without leading or trailing spaces. ");
     }

        @Test
         public void TC_05_Login_Incorrect() {
            driver.get("https://live.techpanda.org/index.php/");

            driver.findElement(By.cssSelector("div.footer a[title='My account']")).click();

            driver.findElement(By.cssSelector("input#email")).sendKeys("long@gmail.com");
            driver.findElement(By.cssSelector("input#pass")).sendKeys("121441");

            driver.findElement(By.cssSelector("button#send2")).click();


        }
    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
