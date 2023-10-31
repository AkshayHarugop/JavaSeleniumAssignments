package useCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.loginRelated;

public class angularPracticeShopPage {
	
	static WebDriver driver = loginRelated.angularPracticeShop();
	static WebDriverWait wait = loginRelated.wait(driver);
	
	public static void main(String[] args) {
//		https://rahulshettyacademy.com/angularpractice/shop
		electronicGadgetShoping();
		driver.quit();
	}

	private static void electronicGadgetShoping() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Shop Name']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nav-link btn btn-primary'][contains(text(),'0')]")));
		List<WebElement> names = driver.findElements(By.xpath("//div[@class='col-lg-9']/app-card-list[@class='row']//h4[@class='card-title']/a"));
		ArrayList<String> AL = new ArrayList<String>();
		for(WebElement name : names) {
			AL.add(name.getText());
		}
		System.out.println(AL);
		List<WebElement> adds = driver.findElements(By.xpath("//div[@class='card h-100']/div[@class='card-footer']/button[@class='btn btn-info']"));
		for(WebElement add : adds) {
			add.click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nav-link btn btn-primary'][contains(text(),'4')]")));
		driver.findElement(By.xpath("//a[@class='nav-link btn btn-primary'][contains(text(),'4')]")).click();
		List<WebElement> Names = driver.findElements(By.xpath("//h4[@class='media-heading']/a"));
		int i=0;
		for(WebElement Name : Names) {
			Assert.assertEquals(AL.get(i), Name.getText());
			i++;
		}
//		increase the quantity
		Actions a = new Actions(driver);
		List<WebElement> quantities = driver.findElements(By.xpath("//input[@id='exampleInputEmail1']"));
		for(WebElement quantity : quantities) {
			a.moveToElement(quantity).click().sendKeys(Keys.ARROW_UP).build().perform();
		}
		
//		Catch the price and mutilply by 2 and store it in arraylist
		List<WebElement> nos = driver.findElements(By.cssSelector("table.table-hover tbody tr"));
		ArrayList<String> AL1 = new ArrayList<String>();
		for(int j=1;j<=nos.size()-2;j++) {
			int price = Integer.parseInt(driver.findElement(By.cssSelector("table.table-hover tbody tr:nth-child("+j+") td:nth-child(3) strong")).getText().split(" ")[1]);
			int total = price*2;
			String tot = Integer.toString(total);
			AL1.add(tot);
		}
		System.out.println(AL1);
		
//		Assertion of the price and capturing the total count
		int sum =0;
		for(int k=1;k<=nos.size()-2;k++) {
			String total = (driver.findElement(By.cssSelector("table.table-hover tbody tr:nth-child("+k+") td:nth-child(4) strong")).getText().split(" ")[1]);
			Assert.assertEquals(total, AL1.get(k-1));
			int price = Integer.parseInt(total);
			sum=sum+price;
		}
		
//		Assertion of the total count
		System.out.println(Integer.toString(sum));
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='text-right']/h3/strong")).getText().split(" ")[1], Integer.toString(sum));

//		click on the checkout box
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='country'][contains(text(),'Please choose your delivery location.')]")));
		
//		selecting country name
		driver.findElement(By.xpath("//input[@id='country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='suggestions']")));
		List<WebElement> countries = driver.findElements(By.xpath("//div[@class='suggestions']/ul/li/a"));
		for(WebElement country : countries) {
			if(country.getText().equals("India")) {
				country.click();
				break;
			}
		}
//		click on the checkbox
		Assert.assertEquals(driver.findElement(By.id("checkbox2")).isSelected(), false);
		driver.findElement(By.xpath("//label[@for='checkbox2'][contains(text(),'I agree with the')]")).click();
		Assert.assertEquals(driver.findElement(By.id("checkbox2")).isSelected(), true);
//		Click on the purchase
		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		verify the msg
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']/strong[text()='Success!']")));
	}

}
