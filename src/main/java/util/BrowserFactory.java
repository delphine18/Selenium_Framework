package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	
			static WebDriver driver;

			public static WebDriver startBrowser() {

				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
				WebDriver driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return driver;
			}
	

}
