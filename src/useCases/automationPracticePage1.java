package useCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utilities.browserRelated;
import utilities.keys;
import utilities.loginRelated;

public class automationPracticePage1 {
	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) {
		System.out.println("start");
		driver = loginRelated.automationPracticePageLogin();
		wait = loginRelated.wait(driver);
//		radioButtonValidation();
//		suggessionClassExample();
//		dropdownExample();
//		checkboxExample();
////		switchWindowExample();
////		switchTabExample();
//		switchToAlertExample("Testing!");
//		webTableExample(235);
//		elementDisplayedExample("Testing!");
//		webTableFixedheader(296);
//		mouseHoverExample();
//		iFrameExample();
//		footer();
		brokenLinkChecks();
		driver.close();
		System.out.println("End");
	}

	private static void brokenLinkChecks() {
		WebElement footer = driver.findElement(By.cssSelector("div[class*='col-sm-8 right-align']"));
//		Scroll to the footer element
		Actions action = new Actions(driver);
//		action.scrollToElement(footer).build().perform();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", footer);
		List<WebElement> links = driver.findElements(By.cssSelector("li.gf-li a"));
		SoftAssert sa = new SoftAssert();
		for (WebElement lin : links) {
			String link = lin.getAttribute("href");
//		String link = driver.findElement(By.cssSelector("a[href*='brokenlink']")).getAttribute("href");
//			System.out.println(link);
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
				conn.setRequestMethod("HEAD");
				conn.connect();
				System.out.println(conn.getResponseCode());
				sa.assertTrue(conn.getResponseCode() < 400,
						"The link with the Text " + link + " is broken with the code " + conn.getResponseCode());

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		sa.assertAll();		

	}

	private static void footer() {
		/*
		 * WebElement footer = driver.findElement(By.cssSelector("div#gf-BIG"));
		 * List<WebElement> links = footer.findElements(By.tagName("a"));
		 * Assert.assertEquals(20, links.size()); WebElement discountCoupons =
		 * footer.findElement(By.cssSelector("td:nth-child(1)")); List<WebElement>
		 * dClinks = discountCoupons.findElements(By.cssSelector("a"));
		 * Assert.assertEquals(5, dClinks.size()); ArrayList<String> Window = null;
		 * for(WebElement dClink : dClinks) { dClink.sendKeys(keys.ctlrClick()); Window
		 * = browserRelated.multiWindowHandling(driver);
		 * 
		 * } // ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		 * for(int i=0;i<Window.size();i++) { driver.switchTo().window(Window.get(i));
		 * System.out.println(driver.getTitle()); driver.close(); }
		 */

		ArrayList<String> Window = null;
		WebElement footer = driver.findElement(By.cssSelector("div#gf-BIG"));
		List<WebElement> links = footer.findElements(By.tagName("a"));
		Assert.assertEquals(20, links.size());
		for (WebElement link : links) {
			link.sendKeys(keys.ctlrClick());
			Window = browserRelated.multiWindowHandling(driver);
		}
		for (int i = 0; i < Window.size(); i++) {
			driver.switchTo().window(Window.get(i));
			System.out.println(driver.getTitle());
//			driver.close();
		}

	}

	private static void iFrameExample() {
//		scroll to the iframe 
		WebElement iframe = driver.findElement(By.xpath("//legend[text()='iFrame Example']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", iframe);
//		Switch to frame
		driver.switchTo().frame("courses-iframe");
//		Click on the more button
		driver.findElement(By.cssSelector("div.header-upper li.dropdown a.dropdown-toggle")).click();
//		click on about us
		driver.findElement(By.xpath("//div[@class='header-upper']//a[text()='About us']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='inner-box']/h1[text()='About Us']")));
//		Switch to frame
		driver.switchTo().window(browserRelated.parentWindow(driver));
		driver.switchTo().frame("courses-iframe");
//		Click on the more button
		driver.findElement(By.cssSelector("div.header-upper li.dropdown a.dropdown-toggle")).click();
//		click on Part time jobs
		driver.findElement(By.xpath("//div[@class='header-upper']//a[text()='Part time jobs']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='inner-box']/h1[text()='Part time Jobs']")));
//		Switch to frame
		driver.switchTo().window(browserRelated.parentWindow(driver));
		driver.switchTo().frame("courses-iframe");
//		Click on the more button
		driver.findElement(By.cssSelector("div.header-upper li.dropdown a.dropdown-toggle")).click();
//		click on contact
		driver.findElement(By.xpath("//div[@class=\"header-upper\"]//a[text()='Contact']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='inner-box']/h1[text()='Contact Us']")));
//		Switch to frame
		driver.switchTo().window(browserRelated.parentWindow(driver));
		driver.switchTo().frame("courses-iframe");
//		Scroll to the join now button in bottom of the frame
		WebElement joinNow = driver.findElement(By.xpath("//a[text()='JOIN NOW']"));
		jse.executeScript("arguments[0].scrollIntoView(true);", joinNow);
		driver.switchTo().window(browserRelated.parentWindow(driver));
	}

	private static void mouseHoverExample() {
//		Mouse Hover Example
//		Scroll to element
		WebElement element = driver.findElement(By.xpath("//legend[text()='Mouse Hover Example']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		Hover on the mouse
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();
		ac.moveToElement(driver.findElement(By.cssSelector("a[href='#top']"))).build().perform();
		driver.findElement(By.cssSelector("a[href='#top']")).click();

//		Scroll to element again
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		Hover on the mouse
		ac.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();
		ac.moveToElement(driver.findElement(By.xpath("//a[text()='Reload']"))).build().perform();
		driver.findElement(By.xpath("//a[text()='Reload']")).click();
//		wait for page to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Practice Page']")));
	}

	private static void webTableFixedheader(int Count) {
		int sum = 0;
		JavascriptExecutor jse = (JavascriptExecutor) driver;

//		Web Table Fixed header
		List<WebElement> amountList = driver.findElements(By.cssSelector("div.tableFixHead tr td:nth-child(4)"));
		for (WebElement amount : amountList) {
			sum = sum + Integer.parseInt(amount.getText());
			jse.executeScript("document.querySelector('div.tableFixHead').scrollBy(0,15)");
		}
		Assert.assertEquals(sum, Count);
	}

	private static void elementDisplayedExample(String Text) {
//		Element Displayed Example
		driver.findElement(By.cssSelector("input#displayed-text")).sendKeys(Text);
//		click on hide
		driver.findElement(By.cssSelector("input#hide-textbox")).click();
//		Validate the box is not visible on the screen
		Assert.assertEquals(false, driver.findElement(By.cssSelector("input#displayed-text")).isDisplayed());
//		click on show
		driver.findElement(By.cssSelector("input#show-textbox")).click();
//		Validate the box is not visible on the screen
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input#displayed-text")).isDisplayed());
	}

	private static void webTableExample(int count) {
		int sum = 0;
//		Web Table Example 
//		count the price and check with the actuals
		List<WebElement> priceList = driver.findElements(By.cssSelector("table.table-display tr td:nth-child(3)"));
		for (WebElement price : priceList) {
			sum = sum + Integer.parseInt(price.getText());
		}
		Assert.assertEquals(count, sum);
	}

	private static void switchToAlertExample(String Text) {
//		Switch To Alert Example
		driver.findElement(By.cssSelector("fieldset.pull-right input#name")).sendKeys(Text);
//		click on alert
		driver.findElement(By.cssSelector("fieldset.pull-right input#alertbtn")).click();
//		Validate the alert
		String msg = driver.switchTo().alert().getText().split(",")[0].split(" ")[1];
		Assert.assertEquals(Text, msg);
		driver.switchTo().alert().accept();
//		Switch To Alert Example
		driver.findElement(By.cssSelector("fieldset.pull-right input#name")).sendKeys(Text);
//		click on alert
		driver.findElement(By.cssSelector("fieldset.pull-right input#confirmbtn")).click();
//		Validate the alert
		String msg1 = driver.switchTo().alert().getText().split(",")[0].split(" ")[1];
		Assert.assertEquals(Text, msg1);
		driver.switchTo().alert().accept();

	}

	/*
	 * private static void switchTabExample() { // Switch Tab Example
	 * driver.findElement(By.id("opentab")).click();
	 * driver.switchTo().window(browserRelated.multiWindowHandling(driver));
	 * driver.manage().window().maximize(); // click on the courses on the 2nd
	 * window
	 * driver.findElement(By.xpath("//li[@class='nav-item']/a[text()='Courses']")).
	 * click(); driver.close();
	 * driver.switchTo().window(browserRelated.parentWindow(driver)); }
	 * 
	 * private static void switchWindowExample() { // Switch Window Example
	 * driver.findElement(By.id("openwindow")).click();
	 * driver.switchTo().window(browserRelated.multiWindowHandling(driver));
	 * driver.manage().window().maximize(); // click on the courses on the 2nd
	 * window
	 * driver.findElement(By.xpath("//li[@class='nav-item']/a[text()='Courses']")).
	 * click(); driver.close();
	 * driver.switchTo().window(browserRelated.parentWindow(driver)); }
	 */
	private static void checkboxExample() {
//		Checkbox Example
		List<WebElement> checkBoxList = driver
				.findElements(By.cssSelector("div#checkbox-example input[type='checkbox']"));
		for (WebElement checkBox : checkBoxList) {
			Assert.assertEquals(false, checkBox.isSelected());
			checkBox.click();
		}
		for (WebElement checkBox : checkBoxList) {
			Assert.assertEquals(true, checkBox.isSelected());
		}

	}

	private static void dropdownExample() {
//		With the select constructor from the select class  
		List<WebElement> options = driver.findElements(By.xpath("//option[contains(@value,'option')]"));
		WebElement selectChoices = driver.findElement(By.cssSelector("select#dropdown-class-example"));
		Select s = new Select(selectChoices);
		for (int i = 1; i <= options.size(); i++) {
			s.selectByIndex(i);
		}

	}

	private static void suggessionClassExample() {
//		Auto select dropdown handling
		driver.findElement(By.cssSelector("#autocomplete")).sendKeys("Ind");
		List<WebElement> countries = driver.findElements(By.cssSelector("li.ui-menu-item div.ui-menu-item-wrapper"));
		for (WebElement i : countries) {
			if (i.getText().equals("India")) {
				i.click();
				break;
			}
		}

	}

	private static void radioButtonValidation() {
//		Radio Button Example
		List<WebElement> radioButtons = driver.findElements(By.cssSelector("fieldset input.radioButton"));
		for (int i = 1; i <= radioButtons.size(); i++) {
			Assert.assertEquals(false,
					driver.findElement(By.cssSelector("fieldset input[value='radio" + i + "']")).isSelected());
			driver.findElement(By.cssSelector("fieldset input[value='radio" + i + "']")).click();
		}
		for (int i = 1; i <= radioButtons.size(); i++) {
			if (i == radioButtons.size()) {
				Assert.assertEquals(true,
						driver.findElement(By.cssSelector("fieldset input[value='radio" + i + "']")).isSelected());
			} else {
				Assert.assertEquals(false,
						driver.findElement(By.cssSelector("fieldset input[value='radio" + i + "']")).isSelected());
			}
		}

	}

}
