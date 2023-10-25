package Utilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loginRelated {
	public static WebDriver flightLogin() {
		WebDriverManager.chromedriver().setup();
		
		
//		System.setProperty("webdribver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();
//		implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
	public static WebDriverWait wait(WebDriver driver) {
//		Explicit wait
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public static WebDriver automationPracticePageLogin() {
		System.setProperty("webdribver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
//		implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
	public static WebDriver greenKartLogin() {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().window().maximize();
//		implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
	public static WebDriver herokuappLogin() {
		ChromeOptions options =  new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "D:\\TestDownload");
        options.setExperimentalOption("prefs", prefs);
        
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
//		implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
	public static WebDriver expiredBadSSLLoginChrome() {
		ChromeOptions Options = new ChromeOptions();
		Options.setAcceptInsecureCerts(true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(Options);
		driver.get("https://expired.badssl.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
	public static WebDriver expiredBadSSLLoginFirefox() {
		FirefoxOptions Options = new FirefoxOptions();
		Options.setAcceptInsecureCerts(true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new FirefoxDriver(Options);
		driver.get("https://expired.badssl.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
	public static WebDriver expiredBadSSLLoginEdge() {
		EdgeOptions Options = new EdgeOptions();
		Options.setAcceptInsecureCerts(true);
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new EdgeDriver(Options);
		
		driver.get("https://expired.badssl.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

}
