package webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Network;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.devtools.v143.network.Network;
import org.openqa.selenium.devtools.v143.network.model.ConnectionType;
import java.time.Duration;
import java.util.Optional;

public class Topic_22_Upload {
    // Step 1_set up browser / page /...
    WebDriver driver;
    @BeforeClass
    public void initialBrowser (){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools =driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty(),
                        Optional.empty(),Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                        false,                              // offline
                        500,                                // latency: 500 ms
                        50 * 1024 / 8,                      // download: 50 kbps
                        50 * 1024 / 8,                      // upload: 50 kbps
                        Optional.of(ConnectionType.CELLULAR3G)
                ));
    }

    // Step 2_TC/ Execute
    @Test
    public void TC_01(){

    }
    @Test
    public void TC_02(){

    }
    @AfterClass
    // Step 3_Clean data test
    public void clearBrowser (){
        driver.quit();
    }
}
