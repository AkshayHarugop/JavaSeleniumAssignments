package useCases;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;
import utilities.browserRelated;
import utilities.keys;
import utilities.loginRelated;

public class herokuAppPage {
	static WebDriver driver;
	static WebDriverWait wait;
	static ArrayList<String> Window;

	@BeforeMethod
	public void method() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("This method will be invoked before every test method");
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start");
		driver = loginRelated.herokuappLogin();
		wait = loginRelated.wait(driver);
		ABTesting();
		addRemoveElements();
		basicAuth();
		brokenImages();
		challengingDOM();
		checkBoxes();
		contextMenu();
		digestAuthentication();
		Thread.sleep(5000);
		disappearingElements();
		Thread.sleep(5000);
		dragAndDrop();
		Thread.sleep(5000);
		dropDown();
		dynamicContent();
		dynamicControls();
		dynamicLoading();
		Thread.sleep(5000);
		entryAd();
		Thread.sleep(5000);
		exitIntent(); // need to check one more time
		fileDownload();
		fileUpload();
		floatingMenu();
		forgotPassword();
		formAuthentication();	
		Thread.sleep(5000);
		frames();
		Thread.sleep(5000);
		geolocation();
		horizontalSlider();
		hovers();
		infiniteScroll();
		inputs();
		jQueryUIMenus(); // Check one more time
		javaScriptAlerts();
		javaScriptOnloadEventError();
		keyPresses();
		largeDeepDOM(); // Need to work some more on this thing
		multipleWindows();
		nestedFrames();
		notificationMessages();
		redirectLink();
		secureFileDownload();
//		shadowDOM(); // Pending
		shiftingContent();
		slowResources();
		sortableDataTables();
		statusCodes();
		Typos();
		wYSIWYGEditor();
		driver.quit();
		System.out.println("End");
	}

	private static void wYSIWYGEditor() {
		driver.findElement(By.xpath("//a[text()='WYSIWYG Editor']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h3[text()='An iFrame containing the TinyMCE WYSIWYG Editor']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'mce_')][contains(@id,'_ifr')]")));
		Actions action = new Actions(driver);
		WebElement content = driver.findElement(By.xpath("//body[@id='tinymce']"));
		content.clear();
		action.moveToElement(content).click().sendKeys("hello Hi How are you !!").build().perform();
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void Typos() {
		driver.findElement(By.xpath("//a[text()='Typos']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Typos']")));
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.xpath("(//div[@id='content']/div[@class='example']/p)[2]")).getText();
//	    	Assert.assertEquals(driver.findElement(By.xpath("(//div[@id='content']/div[@class='example']/p)[2]")).getText(), "Sometimes you'll see a typo, other times you won,t.");
			try {
				Assert.assertEquals(
						driver.findElement(By.xpath("(//div[@id='content']/div[@class='example']/p)[2]")).getText(),
						"Sometimes you'll see a typo, other times you won't.");
				break;
			} catch (AssertionError e) {
				driver.navigate().refresh();
			}
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void statusCodes() {
		driver.findElement(By.xpath("//a[text()='Status Codes']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Status Codes']")));
		List<WebElement> links = driver.findElements(By.xpath("//div[@id='content']//ul/li/a"));
		for (WebElement link : links) {
			System.out.println(link.getText());
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void sortableDataTables() {
		driver.findElement(By.xpath("//a[text()='Sortable Data Tables']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Data Tables']")));
		List<WebElement> rowsNo = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));

		for (int i = 1; i <= rowsNo.size(); i++) {
			List<WebElement> columnNo = driver
					.findElements(By.cssSelector("table#table1 tbody tr:nth-child(" + i + ") td"));
			for (int j = 1; j <= columnNo.size(); j++) {
				System.out.print(
						driver.findElement(By.xpath("((//table[@id='table1']/tbody/tr)[" + i + "]/td)[" + j + "]"))
								.getText() + ", ");
			}
			System.out.println();
		}
		System.out.println("*************************");
		List<WebElement> rowsNo1 = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr"));
		for (int i = 1; i <= rowsNo.size(); i++) {
			List<WebElement> columnNo = driver
					.findElements(By.cssSelector("table#table2 tbody tr:nth-child(" + i + ") td"));
			for (int j = 1; j <= columnNo.size(); j++) {
				System.out.print(
						driver.findElement(By.xpath("((//table[@id='table2']/tbody/tr)[" + i + "]/td)[" + j + "]"))
								.getText() + ", ");
			}
			System.out.println();
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void slowResources() {
		driver.findElement(By.xpath("//a[text()='Slow Resources']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Slow Resources']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void shiftingContent() {
		driver.findElement(By.xpath("//a[text()='Shifting Content']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Shifting Content']")));
		List<WebElement> links = driver.findElements(By.cssSelector("div.example a"));
		ArrayList<String> Window1 = null;
		for (WebElement link : links) {
			link.sendKeys(keys.ctlrClick());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Window1 = browserRelated.multiWindowHandling(driver);
		}
		driver.switchTo().window(Window1.get(2));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h3[text()='Shifting Content: Menu Element']")));
		for (int i = 0; i < 3; i++) {
			driver.findElement(By.xpath("(//a[text()='click here'])[1]")).click();
			driver.findElement(By.xpath("(//a[text()='click here'])[2]")).click();
			driver.findElement(By.xpath("(//a[text()='click here'])[3]")).click();
		}
		driver.close();

		driver.switchTo().window(Window1.get(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Shifting Content: Image']")));
		for (int i = 0; i < 3; i++) {
			driver.findElement(By.xpath("(//a[text()='click here'])[1]")).click();
			driver.findElement(By.xpath("(//a[text()='click here'])[2]")).click();
			driver.findElement(By.xpath("(//a[text()='click here'])[3]")).click();
			driver.findElement(By.xpath("(//a[text()='click here'])[4]")).click();
		}
		driver.close();

		driver.switchTo().window(Window1.get(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Shifting Content: List']")));
		for (int i = 0; i < 3; i++) {
			driver.navigate().refresh();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Shifting Content: List']")));
		}
		driver.close();
		driver.switchTo().window(Window.get(1));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void shadowDOM() {
		driver.findElement(By.xpath("//a[text()='Shadow DOM']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Simple template']")));
		Shadow shadow = new Shadow(driver);

		WebElement root1 = driver.findElement(By.tagName("my-paragraph"));
		WebElement shadowRoot1 = browserRelated.expandRootElement(driver, root1);
		shadowRoot1.findElement(By.xpath("//span[@slot='my-text']")).getText();

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void secureFileDownload() {
		driver.findElement(By.xpath("//a[text()='Secure File Download']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		driver.get("https://admin:admin@the-internet.herokuapp.com/download_secure");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Secure File Downloader']")));
		List<WebElement> links = driver.findElements(By.xpath("//div[@class='example']/a"));
		for (WebElement link : links) {
			link.click();
		}

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void redirectLink() {
		driver.findElement(By.xpath("//a[text()='Redirect Link']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Redirection']")));
		driver.findElement(By.xpath("//a[@id='redirect']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window1 = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window1.get(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Status Codes']")));
		List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'status_codes/')]"));
		for (int i = 1; i <= links.size(); i++) {
			String text = driver.findElement(By.xpath("(//a[contains(@href,'status_codes/')])[" + i + "]")).getText();
			driver.findElement(By.xpath("(//a[contains(@href,'status_codes/')])[" + i + "]")).click();
			driver.switchTo().window(Window1.get(2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Status Codes']")));
			By visi = By.xpath("//div[@class='example']/p[contains(text(),'" + text + " status code.')]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(visi));
			driver.findElement(By.xpath("//a[normalize-space()='here']")).click();
			driver.switchTo().window(Window1.get(2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Status Codes']")));
		}
		driver.close();
		driver.switchTo().window(Window.get(1));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void notificationMessages() {
		driver.findElement(By.xpath("//a[text()='Notification Messages']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Notification Message']")));
		String msg = driver.findElement(By.xpath("//div[@id='flash']")).getText().replace("\n", "!").split("!")[0];
		if (msg.equals("Action successful")) {
			do {
				driver.findElement(By.xpath("//a[normalize-space()='Click here']")).click();
				msg = driver.findElement(By.xpath("//div[@id='flash']")).getText().replace("\n", "!").split("!")[0];
			} while (!msg.equals("Action unsuccesful, please try again"));
		} else {
			Assert.assertEquals(msg.equals("Action unsuccesful, please try again"), true);
			do {
				driver.findElement(By.xpath("//a[normalize-space()='Click here']")).click();
				msg = driver.findElement(By.xpath("//div[@id='flash']")).getText().replace("\n", "!").split("!")[0];
			} while (!msg.equals("Action successful"));
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void nestedFrames() {
		driver.findElement(By.xpath("//a[text()='Nested Frames']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Opening a new window']")));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-left");
		Assert.assertEquals(driver.findElement(By.xpath("//html/body")).getText(), "LEFT");

		driver.switchTo().window(Window.get(1));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-middle");
		Assert.assertEquals(driver.findElement(By.xpath("//html/body/div")).getText(), "MIDDLE");

		driver.switchTo().window(Window.get(1));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-right");
		Assert.assertEquals(driver.findElement(By.xpath("//html/body")).getText(), "RIGHT");

		driver.switchTo().window(Window.get(1));
		driver.switchTo().frame("frame-bottom");
		Assert.assertEquals(driver.findElement(By.xpath("//html/body")).getText(), "BOTTOM");

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void multipleWindows() {
		driver.findElement(By.xpath("//a[text()='Multiple Windows']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Opening a new window']")));
		driver.findElement(By.xpath("//a[normalize-space()='Click Here']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window1 = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window1.get(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='New Window']")));
		driver.close();
		driver.switchTo().window(Window.get(1));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void largeDeepDOM() {
		driver.findElement(By.xpath("//a[text()='Large & Deep DOM']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Large & Deep DOM']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='No Siblings']")));
		List<WebElement> value1 = driver.findElements(By.xpath("(//h4[text()='No Siblings']/parent::div/div)[1]//div"));
		for (int i = value1.size(); i > 0; i--) {
			if (i == value1.size() - 1) {
				Assert.assertEquals("No siblings", value1.get(i).getText());
				break;
			}
		}

//		sibling 1. 
		List<WebElement> value2 = driver.findElements(By.xpath(
				"(//h4[text()='No Siblings']/parent::div/div)[2]//div[@id='siblings']//div[contains(@id,'sibling-1.')]"));
		String value = value2.get(0).getText();
//		System.out.println(value);

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void keyPresses() {
		driver.findElement(By.xpath("//a[text()='Key Presses']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Key Presses']")));
		WebElement textBox = driver.findElement(By.id("target"));
//		Click on the tab an verify the response
		textBox.sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='You entered: TAB']")));
//		Click on the Shift an verify the response
		textBox.sendKeys(Keys.SHIFT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='You entered: SHIFT']")));
//		Click on the ESCAPE an verify the response
		textBox.sendKeys(Keys.ESCAPE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='You entered: ESCAPE']")));

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void javaScriptOnloadEventError() {
		driver.findElement(By.xpath("//a[text()='JavaScript onload event error']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='JavaScript Alerts']")));
		By error = By.xpath("//p[contains(text(),'JavaScript error')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(error));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void javaScriptAlerts() {
		driver.findElement(By.xpath("//a[text()='JavaScript Alerts']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='JavaScript Alerts']")));
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		Alert alert1 = driver.switchTo().alert();
		Assert.assertEquals(alert1.getText(), "I am a JS Alert");
		alert1.accept();
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		Alert alert2 = driver.switchTo().alert();
		Assert.assertEquals(alert2.getText(), "I am a JS Confirm");
		alert2.accept();
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		Alert alert3 = driver.switchTo().alert();
		Assert.assertEquals(alert3.getText(), "I am a JS Confirm");
		alert3.dismiss();
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		Alert alert4 = driver.switchTo().alert();
		Assert.assertEquals(alert4.getText(), "I am a JS prompt");
		alert4.sendKeys("Hello!!");
		alert4.accept();
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		Alert pop1 = driver.switchTo().alert();
		System.out.println(pop1.getText());
		pop1.sendKeys("Welcome to Selenium 4");
		pop1.accept();
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void jQueryUIMenus() {
		driver.findElement(By.xpath("//a[text()='JQuery UI Menus']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='JQueryUI - Menu']")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Disabled']"))).build().perform();
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Enabled']"))).build().perform();
//		action.moveToElement(driver.findElement(By.xpath("//a[text()='Downloads']"))).build().perform();

//		action.moveToElement(driver.findElement(By.xpath("//li[@class='ui-menu-item ui-state-focus']/a[text()='Back to JQuery UI']"))).build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']/a[text()='Back to JQuery UI']")));

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void inputs() {
		driver.findElement(By.xpath("//a[text()='Inputs']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Inputs']")));
		WebElement input = driver.findElement(By.xpath("//input[@type='number']"));
		Actions action = new Actions(driver);
		action.moveToElement(input).click().sendKeys("5000").build().perform();
		for (int i = 0; i < 10; i++) {
			action.moveToElement(input).click().sendKeys(Keys.ARROW_UP).build().perform();
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void infiniteScroll() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Infinite Scroll']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Infinite Scroll']")));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		for (int i = 0; i < 5; i++) {
			jse.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(1000);
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void hovers() {
		driver.findElement(By.xpath("//a[text()='Hovers']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Hovers']")));
		List<WebElement> userAvatars = driver.findElements(By.xpath("//div[@class='figure']"));
		Actions action = new Actions(driver);
		for (WebElement userAvatar : userAvatars) {
			action.moveToElement(userAvatar).build().perform();
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void horizontalSlider() {
		driver.findElement(By.xpath("//a[text()='Horizontal Slider']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Horizontal Slider']")));
		// check for the initial range
		Assert.assertEquals("0", driver.findElement(By.xpath("//span[@id='range']")).getText());
		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		Actions action = new Actions(driver);
		action.clickAndHold(slider);
		action.moveByOffset(6, 0).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='range'][text()='2.5']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void geolocation() {
		driver.findElement(By.xpath("//a[text()='Geolocation']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Geolocation']")));
		driver.findElement(By.xpath("//button[@onclick='getLocation()']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='demo']/div[@id='lat-value']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='demo']/div[@id='long-value']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='See it on Google']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='getLocation()']")));
		driver.findElement(By.xpath("//button[@onclick='getLocation()']")).click();
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void frames() {
		driver.findElement(By.xpath("//a[text()='Frames']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Frames']")));
		List<WebElement> links = driver.findElements(By.xpath("//div[@class='example']//a"));
		for (WebElement link : links) {
			link.sendKeys(keys.ctlrClick());
		}
		ArrayList<String> Window1 = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window1.get(3));

		// iFrame
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h3[text()='An iFrame containing the TinyMCE WYSIWYG Editor']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']")));
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//body[@id='tinymce']")).clear();
		action.moveToElement(driver.findElement(By.xpath("//body[@id='tinymce']"))).click().sendKeys("Hello World!")
				.build().perform();
		driver.switchTo().window(Window1.get(3));
		driver.close();

		// Nested frame
		driver.switchTo().window(Window1.get(2));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-left");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[contains(text(),'LEFT')]")));
		driver.switchTo().window(Window1.get(2));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-middle");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'MIDDLE')]")));
		driver.switchTo().window(Window1.get(2));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-right");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[contains(text(),'RIGHT')]")));
		driver.switchTo().window(Window1.get(2));
		driver.switchTo().frame("frame-bottom");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[contains(text(),'BOTTOM')]")));
		driver.switchTo().window(Window1.get(2));
		driver.close();

		driver.switchTo().window(Window.get(1));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void formAuthentication() {
		driver.findElement(By.xpath("//a[text()='Form Authentication']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login Page']")));
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.xpath("//button[@type='submit']/i")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='flash'][contains(text(),'Your username is invalid!')]")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void forgotPassword() {
		driver.findElement(By.xpath("//a[text()='Forgot Password']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Forgot Password']")));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("akshayharugop@gmail.com");
		driver.findElement(By.xpath("//button[@id='form_submit']/i")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Internal Server Error']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void floatingMenu() {
		driver.findElement(By.xpath("//a[text()='Floating Menu']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Floating Menu']")));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='Home']"))).click().build()
				.perform();
		jse.executeScript("window.scrollBy(0,500)");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='News']"))).click().build()
				.perform();
		jse.executeScript("window.scrollBy(0,500)");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='Contact']"))).click().build()
				.perform();
		jse.executeScript("window.scrollBy(0,500)");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='About']"))).click().build()
				.perform();
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void fileUpload() {
		driver.findElement(By.xpath("//a[text()='File Upload']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='File Uploader']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='file-upload']")));
		Path path = Paths.get("D:\\javaSeleniumLibraries&Assignments\\Capture001.png");
		File imagePath = new File(path.toUri());
		driver.findElement(By.id("file-upload")).sendKeys(imagePath.toString());
		driver.findElement(By.xpath("//input[@id='file-submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='File Uploaded!']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void fileDownload() {
		driver.findElement(By.xpath("//a[text()='File Download']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='File Downloader']")));
		List<WebElement> hyperLinks = driver.findElements(By.xpath("//div[@class='example']/a"));
		for (WebElement hyperLink : hyperLinks) {
			hyperLink.click();
		}

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void exitIntent() {
		driver.findElement(By.xpath("//a[text()='Exit Intent']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Exit Intent']")));

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void entryAd() {
		driver.findElement(By.xpath("//a[text()='Entry Ad']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='This is a modal window']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-footer']/p")));
		driver.findElement(By.xpath("//div[@class='modal-footer']/p")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Entry Ad']")));
		driver.findElement(By.xpath("//a[@id='restart-ad']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='This is a modal window']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-footer']/p")));
		driver.findElement(By.xpath("//div[@class='modal-footer']/p")).click();

		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void dynamicLoading() {
		driver.findElement(By.xpath("//a[text()='Dynamic Loading']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamically Loaded Page Elements']")));
		List<WebElement> links = driver.findElements(By.cssSelector("div.example a"));
		for (WebElement link : links) {
			link.sendKeys(keys.ctlrClick());
		}
		ArrayList<String> Window1 = browserRelated.multiWindowHandling(driver);
		for (int i = 0; i < Window1.size(); i++) {
			driver.switchTo().window(Window1.get(i));
		}

		driver.switchTo().window(Window1.get(2));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamically Loaded Page Elements']")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h4[text()='Example 2: Element rendered after the fact']")));
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading'][text()='Loading... ']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
		driver.close();

		driver.switchTo().window(Window1.get(3));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamically Loaded Page Elements']")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h4[text()='Example 1: Element on page that is hidden']")));
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading'][text()='Loading... ']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));

		driver.close();
		driver.switchTo().window(Window.get(1));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void dynamicControls() {
		driver.findElement(By.xpath("//a[text()='Dynamic Controls']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Dynamic Controls']")));
		driver.findElement(By.xpath("//form[@id='checkbox-example']//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@type='button'][text()='Remove']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='loading'][text()='Wait for it... ']")));
//		System.out.println(driver.findElement(By.xpath("//p[@id='message']")).getText());
		Assert.assertEquals("It's gone!", driver.findElement(By.xpath("//p[@id='message']")).getText());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text'][@disabled='']")));
		driver.findElement(By.xpath("//button[@type='button'][text()='Enable']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@id='loading'][text()='Wait for it... '])[2]")));
		Assert.assertEquals("It's enabled!", driver.findElement(By.xpath("//p[@id='message']")).getText());
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Test!");
		driver.findElement(By.xpath("//button[@type='button'][text()='Disable']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@id='loading'][text()='Wait for it... '])[2]")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@id='loading'][text()='Wait for it... '])[3]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text'][@disabled='']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void dynamicContent() {
		driver.findElement(By.xpath("//a[text()='Dynamic Content']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamic Content']")));
		driver.findElement(By.xpath("//a[text()='click here']")).click();
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void dropDown() {
		driver.findElement(By.xpath("//a[text()='Dropdown']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dropdown List']")));
		Select select = new Select(driver.findElement(By.cssSelector("select#dropdown")));
		select.selectByValue("1");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//option[@selected='selected'][text()='Option 1']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void dragAndDrop() {
		driver.findElement(By.xpath("//a[text()='Drag and Drop']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Drag and Drop']")));
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.cssSelector("div#content div#column-a"));
		WebElement target = driver.findElement(By.cssSelector("div#content div#column-b"));
		action.dragAndDrop(source, target).build().perform();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div#content div#column-a[style='opacity: 1;']")));
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void disappearingElements() {
		driver.findElement(By.xpath("//a[text()='Disappearing Elements']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Disappearing Elements']")));
		ArrayList<String> Window1 = null;
		List<WebElement> links = driver.findElements(By.xpath("//div[@id='content']//ul//a"));
		for (WebElement link : links) {
			link.sendKeys(keys.ctlrClick());
		}
		Window1 = browserRelated.multiWindowHandling(driver);
		for (int i = Window1.size(); i > Window1.size() - 4; i--) {
			driver.switchTo().window(Window1.get(i - 1));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			driver.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Disappearing Elements']")));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void digestAuthentication() {
		System.out.println("digestAuthentication link is not available");
	}

	private static void contextMenu() {
		driver.findElement(By.xpath("//a[text()='Context Menu']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Context Menu']")));
		Actions action = new Actions(driver);
		WebElement contClick = driver.findElement(By.xpath("//div[@id='hot-spot']"));
		action.moveToElement(contClick).click().contextClick().build().perform();
		Assert.assertEquals("You selected a context menu", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void checkBoxes() {
		driver.findElement(By.xpath("//a[text()='Checkboxes']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Checkboxes']")));
		Assert.assertEquals(false, driver.findElement(By.xpath("//input[1]")).isSelected());
		driver.findElement(By.xpath("//input[1]")).click();
		Assert.assertEquals(true, driver.findElement(By.xpath("//input[1]")).isSelected());
		Assert.assertEquals(true, driver.findElement(By.xpath("//input[2]")).isSelected());
		driver.findElement(By.xpath("//input[2]")).click();
		Assert.assertEquals(false, driver.findElement(By.xpath("//input[2]")).isSelected());
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void challengingDOM() {
		driver.findElement(By.xpath("//a[text()='Challenging DOM']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Challenging DOM']")));
		driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button']")).click();
		driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button alert']")).click();
		driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button success']")).click();
		List<WebElement> rows = driver.findElements(By.cssSelector("div.large-10 table tbody tr"));
		for (int i = 1; i < rows.size(); i++) {
			List<WebElement> columns = rows.get(i).findElements(By.cssSelector("td"));
			for (WebElement column : columns) {
				System.out.print(column.getText() + ", ");
			}
			System.out.println();
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	private static void brokenImages() {
		driver.findElement(By.xpath("//a[text()='Broken Images']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Broken Images']")));
		List<WebElement> images = driver.findElements(By.xpath("//div[@class='example']/img"));
		for (WebElement image : images) {
			if (image.getAttribute("naturalWidth").equals("0")) {
				System.out.println(image.getAttribute("outerHTML") + " is broken.");
			}
		}
		driver.close();
		driver.switchTo().window(utilities.browserRelated.parentWindow(driver));
	}

	@Test
	private static void basicAuth() {
//		String name = driver.findElement(By.xpath("(//div[@id='content']/ul/li/a)[3]")).getText();
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
//		1st method proxy login works like this "https://username:password@test.com"
//        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")));
//        driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));

//       2nd Method by HasAuthentication
		((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin"));
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")));
		driver.close();
		driver.switchTo().window(browserRelated.parentWindow(driver));
	}

	@Test
	private static void addRemoveElements() {
//		Window.clear();
		String name = driver.findElement(By.xpath("(//div[@id='content']/ul/li/a)[2]")).getText();
		driver.findElement(By.xpath("//a[text()='Add/Remove Elements']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		Assert.assertEquals(name, driver.findElement(By.cssSelector("div#content h3")).getText());
		List<WebElement> delete = null;
		for (int i = 1; i <= 50; i++) {
			driver.findElement(By.cssSelector("div.example button[onclick='addElement()']")).click();
			delete = driver.findElements(By.cssSelector("div.example button[onclick='deleteElement()']"));
			Assert.assertEquals(i, delete.size());
		}
		for (WebElement del : delete) {
			del.click();
		}
		driver.close();
		driver.switchTo().window(browserRelated.parentWindow(driver));
	}

	@Test
	private static void ABTesting() {
		driver.findElement(By.xpath("//a[text()='A/B Testing']")).sendKeys(keys.ctlrClick());
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		String name2 = "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";
		Assert.assertEquals(name2, driver.findElement(By.cssSelector("div.example p")).getText());
		driver.close();
		driver.switchTo().window(browserRelated.parentWindow(driver));
	}

}
