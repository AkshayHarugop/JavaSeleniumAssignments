package useCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.loginRelated;

public class loginpagePractisePage {

	static WebDriver driver = loginRelated.loginpagePractise();
	static WebDriverWait wait = loginRelated.wait(driver);
	
	public static void main(String[] args) {
//		https://rahulshettyacademy.com/loginpagePractise/
		login();
		driver.quit();
	}

	private static void login() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='blinkingText'][text()='Free Access to InterviewQues/ResumeAssistance/Material']")));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("admin");
		Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='checkmark'])[1]")).isEnabled(), true);
		Select s = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
		s.selectByValue("consult");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='terms']")).isSelected(), false);
		driver.findElement(By.xpath("//input[@id='terms']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='terms']")).isSelected(), true);
		driver.findElement(By.xpath("//input[@id='signInBtn']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='signInBtn'][@value='Signing ..']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger col-md-12']/strong[text()='Incorrect']/parent::div[contains(text(),'username/password.')]")));
		String userName = driver.findElement(By.xpath("//p[@class='text-center text-white']")).getText().split("is")[1].split("and")[0].trim();
		String password = driver.findElement(By.xpath("//p[@class='text-center text-white']")).getText().split("Password is")[1].trim().substring(0, 8);
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='signInBtn']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='signInBtn'][@value='Signing ..']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='my-4'][text()='Shop Name']")));
		driver.close();
		
	}

}
