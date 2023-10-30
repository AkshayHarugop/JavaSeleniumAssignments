package useCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDOMExample {
	static WebDriver driver;

	public static void main(String[] args) {
		FirefoxOptions options = new FirefoxOptions();
		options.setAcceptInsecureCerts(true);
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		prefs.put("download.default_directory", "D:\\TestDownload");
//		((Object) options).setExperimentalOption("prefs", prefs);

		WebDriverManager.firefoxdriver().setup();
//		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new FirefoxDriver(options);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.get("https://shop.polymer-project.org/");
		driver.manage().window().maximize();
		By byMenu = By.linkText("Ladies Outerwear");
		wait.until(ExpectedConditions.visibilityOfElementLocated(byMenu));
		driver.findElement(byMenu).click();
		
		//Validate Page title
		By byHeading = By.cssSelector("header>h1");
		wait.until(ExpectedConditions.presenceOfElementLocated(byHeading));
		
		String getActualHeaderText = driver.findElement(byHeading).getText();
		Assert.assertEquals(getActualHeaderText, "Ladies Outerwear");
		
		
		
		
		System.out.println("End");

	}

	// Returns webelement
	public static WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				element);
		return ele;
	}

}