package useCases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.browserRelated;
import utilities.loginRelated;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class angularPracticePage {

	static WebDriver driver = loginRelated.angularPractice();
	static WebDriverWait wait = loginRelated.wait(driver);
	
	public static void main(String[] args) {
		System.out.println("Start");
		addingNewTabInBrowserAndWorking();
		relativeLocator();
		data();
		driver.quit();
		System.out.println("End");
	}

	private static void addingNewTabInBrowserAndWorking() {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.switchTo().window(browserRelated.multiWindowHandling(driver).get(1));
		driver.get("https://rahulshettyacademy.com/angularpractice/shop");
		String value = driver.findElement(By.xpath("(//a[@class='navbar-brand'])[2]")).getText();
		driver.close();
		driver.switchTo().window(browserRelated.parentWindow(driver));
		driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']")).sendKeys(value);
		
	}

	private static void relativeLocator() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']/input[@name='name']")));
		WebElement nameEditBox = driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']"));
		String value1 = driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText();
		Assert.assertEquals("Name", value1);
		
		WebElement dateofBirth = driver.findElement(By.cssSelector("[for='dateofBirth']"));
		driver.findElement(with(By.tagName("input")).below(dateofBirth)).click();
		
		WebElement iceCreamLabel =driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));
		driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel)).click();

		WebElement rdb = driver.findElement(By.id("inlineRadio1"));
		String value2 = driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText();
		Assert.assertEquals("Student", value2);
	}

	private static void data() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Protractor Tutorial']")));
		driver.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys("Akshay");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("akshayharugop@gmail.com");
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys("Akshay@123");
		Assert.assertEquals(driver.findElement(By.id("exampleCheck1")).isSelected(), true);
		driver.findElement(By.id("exampleCheck1")).click();
		Assert.assertEquals(driver.findElement(By.id("exampleCheck1")).isSelected(), false);
		WebElement select = driver.findElement(By.id("exampleFormControlSelect1"));
		Select s = new Select(select);
		s.selectByVisibleText("Male");
		Assert.assertEquals(driver.findElement(By.id("inlineRadio1")).isSelected(), false);
		driver.findElement(By.id("inlineRadio1")).click();
		Assert.assertEquals(driver.findElement(By.id("inlineRadio1")).isSelected(), true);
		
//		Date of the birth
		Actions a = new Actions(driver);
		WebElement date = driver.findElement(By.xpath("//input[@name='bday']"));
		a.moveToElement(date).click().sendKeys("10").sendKeys("25").sendKeys("1996").build().perform();
		driver.findElement(By.xpath("(//input[@name='name'])[2]")).sendKeys("Akshay");
		
//		click on submit button
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		//take the screen shot
		browserRelated.screenShot(driver,new File("C:\\Users\\aha5\\Desktop\\screenshot.PNG"));
		driver.close();
	}

}
