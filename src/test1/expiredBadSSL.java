package test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class expiredBadSSL {
	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) {
		driver = Utilities.loginRelated.expiredBadSSLLoginChrome();
		wait = Utilities.loginRelated.wait(driver);
		chrome();
		driver.close();
		
		driver = Utilities.loginRelated.expiredBadSSLLoginFirefox();
		firefox();
		driver.close();
		
		driver = Utilities.loginRelated.expiredBadSSLLoginEdge();
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
