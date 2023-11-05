package useCases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.browserRelated;
import utilities.loginRelated;

public class angularPracticePage {

	static WebDriver driver = loginRelated.angularPractice();
	static WebDriverWait wait = loginRelated.wait(driver);
	
	public static void main(String[] args) {
		System.out.println("Start");
		data();
		driver.quit();
		System.out.println("End");
	}

	private static void data() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Protractor Tutorial']")));
		driver.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys("Akshay");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("akshayharugop@gmail.com");
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys("Akshay@123");
		Assert.assertEquals(driver.findElement(By.id("exampleCheck1")).isSelected(), false);
		driver.findElement(By.id("exampleCheck1")).click();
		Assert.assertEquals(driver.findElement(By.id("exampleCheck1")).isSelected(), true);
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
