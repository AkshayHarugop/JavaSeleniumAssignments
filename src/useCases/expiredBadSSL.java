package useCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class expiredBadSSL {
	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) {
		driver = utilities.loginRelated.expiredBadSSLLoginChrome();
		wait = utilities.loginRelated.wait(driver);
		chrome();
		driver.close();
		
		driver = utilities.loginRelated.expiredBadSSLLoginFirefox();
		firefox();
		driver.close();
		
		driver = utilities.loginRelated.expiredBadSSLLoginEdge();
		edge();
		driver.close();
	}

	private static void edge() {
		Assert.assertEquals("expired.badssl.com", driver.getTitle());
	}

	private static void firefox() {
		Assert.assertEquals("expired.badssl.com", driver.getTitle());
	}

	public static void chrome() {
		Assert.assertEquals("expired.badssl.com", driver.getTitle());
	}
	

}
