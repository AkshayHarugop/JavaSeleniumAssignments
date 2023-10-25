package test1;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.browserRelated;
import Utilities.keys;
import Utilities.loginRelated;

public class Test14 {
	static WebDriver driver;
	static WebDriverWait wait;
	static ArrayList<String> Window;

	public static void main(String[] args) {
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
		disappearingElements();
		dragAndDrop();
		dropDown();
		dynamicContent();
		dynamicControls();
		dynamicLoading();
		entryAd();
		exitIntent(); // need to check one more time
		fileDownload();
		fileUpload();
		floatingMenu();
		forgotPassword();
		formAuthentication();
		driver.quit();
		System.out.println("End");
	}

	private static void formAuthentication() {
		driver.findElement(By.xpath("//a[text()='Form Authentication']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login Page']")));
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.xpath("//button[@type='submit']/i")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash'][contains(text(),'Your username is invalid!')]")));
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void forgotPassword() {
		driver.findElement(By.xpath("//a[text()='Forgot Password']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Forgot Password']")));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("akshayharugop@gmail.com");
		driver.findElement(By.xpath("//button[@id='form_submit']/i")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Internal Server Error']")));
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void floatingMenu() {
		driver.findElement(By.xpath("//a[text()='Floating Menu']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Floating Menu']")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='Home']"))).click().build().perform();
		jse.executeScript("window.scrollBy(0,500)");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='News']"))).click().build().perform();
		jse.executeScript("window.scrollBy(0,500)");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='Contact']"))).click().build().perform();
		jse.executeScript("window.scrollBy(0,500)");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='menu']//li/a[text()='About']"))).click().build().perform();
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void fileUpload() {
		driver.findElement(By.xpath("//a[text()='File Upload']")).sendKeys(keys.ctlrClick(driver));
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
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void fileDownload() {
		driver.findElement(By.xpath("//a[text()='File Download']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='File Downloader']")));
		List<WebElement> hyperLinks = driver.findElements(By.xpath("//div[@class='example']/a"));
		for(WebElement hyperLink : hyperLinks) {
			hyperLink.click();
		}
		
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void exitIntent() {
		driver.findElement(By.xpath("//a[text()='Exit Intent']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Exit Intent']")));
		
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void entryAd() {
		driver.findElement(By.xpath("//a[text()='Entry Ad']")).sendKeys(keys.ctlrClick(driver));
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
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void dynamicLoading() {
		driver.findElement(By.xpath("//a[text()='Dynamic Loading']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamically Loaded Page Elements']")));
		List<WebElement> links = driver.findElements(By.cssSelector("div.example a"));
		for(WebElement link : links) {
			link.sendKeys(keys.ctlrClick(driver));
		}
		ArrayList<String> Window1 = browserRelated.multiWindowHandling(driver);
		for(int i=0;i<Window1.size();i++) {
			driver.switchTo().window(Window1.get(i));
		}
		
		driver.switchTo().window(Window1.get(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamically Loaded Page Elements']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Example 2: Element rendered after the fact']")));
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading'][text()='Loading... ']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
		driver.close();
		
		driver.switchTo().window(Window1.get(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamically Loaded Page Elements']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Example 1: Element on page that is hidden']")));
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading'][text()='Loading... ']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
		driver.close();
		
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void dynamicControls() {
		driver.findElement(By.xpath("//a[text()='Dynamic Controls']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Dynamic Controls']")));
		driver.findElement(By.xpath("//form[@id='checkbox-example']//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@type='button'][text()='Remove']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading'][text()='Wait for it... ']")));
//		System.out.println(driver.findElement(By.xpath("//p[@id='message']")).getText());
		Assert.assertEquals("It's gone!", driver.findElement(By.xpath("//p[@id='message']")).getText());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text'][@disabled='']")));
		driver.findElement(By.xpath("//button[@type='button'][text()='Enable']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='loading'][text()='Wait for it... '])[2]")));
		Assert.assertEquals("It's enabled!", driver.findElement(By.xpath("//p[@id='message']")).getText());
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Test!");
		driver.findElement(By.xpath("//button[@type='button'][text()='Disable']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='loading'][text()='Wait for it... '])[2]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='loading'][text()='Wait for it... '])[3]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text'][@disabled='']")));
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void dynamicContent() {
		driver.findElement(By.xpath("//a[text()='Dynamic Content']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dynamic Content']")));
		driver.findElement(By.xpath("//a[text()='click here']")).click();
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void dropDown() {
		driver.findElement(By.xpath("//a[text()='Dropdown']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Dropdown List']")));
		Select select = new Select(driver.findElement(By.cssSelector("select#dropdown")));
		select.selectByValue("1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@selected='selected'][text()='Option 1']")));
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void dragAndDrop() {
		driver.findElement(By.xpath("//a[text()='Drag and Drop']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Drag and Drop']")));
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.cssSelector("div#content div#column-a"));
		WebElement target = driver.findElement(By.cssSelector("div#content div#column-b"));
		action.dragAndDrop(source, target).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#content div#column-a[style='opacity: 1;']")));
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void disappearingElements() {
		driver.findElement(By.xpath("//a[text()='Disappearing Elements']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Disappearing Elements']")));
		ArrayList<String> Window1 = null;
		List<WebElement> links = driver.findElements(By.xpath("//div[@id='content']//ul//a"));
		for(WebElement link : links) {
			link.sendKeys(keys.ctlrClick(driver));
//			Window1 = browserRelated.multiWindowHandling(driver);
		}
		Window1 = browserRelated.multiWindowHandling(driver);
		for(int i=Window1.size();i>Window1.size()-4;i--) {
			driver.switchTo().window(Window1.get(i-1));
//			System.out.println(driver.getTitle());
			driver.close();
		}
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Disappearing Elements']")));
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void digestAuthentication() {
		System.out.println("digestAuthentication link is not available");
	}

	private static void contextMenu() {
		driver.findElement(By.xpath("//a[text()='Context Menu']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Context Menu']")));
		Actions action = new Actions(driver);
		WebElement contClick = driver.findElement(By.xpath("//div[@id='hot-spot']"));
		action.moveToElement(contClick).click().contextClick().build().perform();
		Assert.assertEquals("You selected a context menu", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void checkBoxes() {
		driver.findElement(By.xpath("//a[text()='Checkboxes']")).sendKeys(keys.ctlrClick(driver));
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
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void challengingDOM() {
		driver.findElement(By.xpath("//a[text()='Challenging DOM']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Challenging DOM']")));
		driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button']")).click();
		driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button alert']")).click();
		driver.findElement(By.xpath("//div[@class='large-2 columns']/a[@class='button success']")).click();
		List<WebElement> rows = driver.findElements(By.cssSelector("div.large-10 table tbody tr"));
		for(int i=1;i<rows.size();i++) {
			List<WebElement> columns = rows.get(i).findElements(By.cssSelector("td"));
			for(WebElement column : columns) {
				System.out.print(column.getText()+", ");
			}
			System.out.println();
		}
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void brokenImages() {
		driver.findElement(By.xpath("//a[text()='Broken Images']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Broken Images']")));
		List<WebElement> images = driver.findElements(By.xpath("//div[@class='example']/img"));
		for(WebElement image : images) {
			if(image.getAttribute("naturalWidth").equals("0")) {
				System.out.println(image.getAttribute("outerHTML")+" is broken.");
			}
		}
		driver.close();
		driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	private static void basicAuth() {
//		String name = driver.findElement(By.xpath("(//div[@id='content']/ul/li/a)[3]")).getText();
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
//		1st method proxy login works like this "https://username:password@test.com"
//        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")));
//        driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
        
        
//       2nd Method by HasAuthentication
        ((HasAuthentication)driver).register(UsernameAndPassword.of("admin", "admin"));
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")));
        driver.switchTo().window(Utilities.browserRelated.parentWindow(driver));
	}

	
	private static void addRemoveElements() {
//		Window.clear();
		String name = driver.findElement(By.xpath("(//div[@id='content']/ul/li/a)[2]")).getText();
		driver.findElement(By.xpath("//a[text()='Add/Remove Elements']")).sendKeys(keys.ctlrClick(driver));
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

	private static void ABTesting() {
		driver.findElement(By.xpath("//a[text()='A/B Testing']")).sendKeys(keys.ctlrClick(driver));
		ArrayList<String> Window =  browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(Window.get(1));
		String name2 = "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";
		Assert.assertEquals(name2, driver.findElement(By.cssSelector("div.example p")).getText());
		driver.close();
		driver.switchTo().window(browserRelated.parentWindow(driver));
	}

}
