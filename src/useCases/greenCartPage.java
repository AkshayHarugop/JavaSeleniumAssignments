package useCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.browserRelated;

public class greenCartPage {
	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) {
		driver = utilities.loginRelated.greenKartLogin();
		wait = utilities.loginRelated.wait(driver);
		addAllVegies();
		topDeals();
	}

	private static void topDeals() {
//		click on the top deals
		driver.findElement(By.xpath("//a[normalize-space()='Top Deals']")).click();
		driver.switchTo().window(browserRelated.multiWindowHandling(driver).get(1));
		WebElement pageSize = driver.findElement(By.xpath("//div[@class='container']//select[@id='page-menu']"));
		Select s = new Select(pageSize);
		s.selectByValue("20");
//		make the list of vegies, price and discount Price
		List<WebElement> fruitNames = driver
				.findElements(By.cssSelector("table.table-bordered tbody tr td:nth-child(1)"));
		ArrayList<String> fN = new ArrayList<String>();
		for (WebElement fruitName : fruitNames) {
			fN.add(fruitName.getText());
		}
		List<WebElement> priceList = driver
				.findElements(By.cssSelector("table.table-bordered tbody tr td:nth-child(2)"));
		ArrayList<String> pL = new ArrayList<String>();
		for (WebElement price : priceList) {
			pL.add(price.getText());
		}
		List<WebElement> discountPrices = driver
				.findElements(By.cssSelector("table.table-bordered tbody tr td:nth-child(3)"));
		ArrayList<String> dP = new ArrayList<String>();
		for (WebElement discountPrice : discountPrices) {
			dP.add(discountPrice.getText());
		}
		System.out.println(fN);
		System.out.println(pL);
		System.out.println(dP);
		driver.close();
		driver.switchTo().window(browserRelated.parentWindow(driver));
		driver.close();
	}

	private static void addAllVegies() {
		int sum = 0;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		make the list of the vegies
		List<WebElement> vegieNames = driver
				.findElements(By.xpath("//div[@class='products']//h4[@class='product-name']"));
		ArrayList<String> Al = new ArrayList<String>();
		for (WebElement vegieName : vegieNames) {
			Al.add(vegieName.getText().split("-")[0].trim());
		}
		for (int i = 0; i < Al.size(); i++) {
			sum = sum
					+ Integer
							.parseInt(driver
									.findElement(By.xpath("//h4[contains(text(),'" + Al.get(i)
											+ "')]/parent::div[@class='product']/p[@class='product-price']"))
									.getText());
//			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h4[contains(text(),'"+Al.get(i)+"')]/parent::div[@class='product']/div[@class='stepper-input']/a[@class='increment']")));
			driver.findElement(By.xpath("//h4[contains(text(),'" + Al.get(i)
					+ "')]/parent::div[@class='product']/div[@class='stepper-input']/a[@class='increment']")).click();
//			Testing
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//h4[contains(text(),'Brocolli')]/parent::div[@class='product']//div[@class='stepper-input']/input[@value='2']")));
			driver.findElement(By.xpath("//h4[contains(text(),'" + Al.get(i)
					+ "')]/parent::div[@class='product']/div[@class='product-action']/button[@type='button']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'" + Al.get(i)
					+ "')]/parent::div[@class='product']/div[@class='product-action']/button[@type='button'][text()='✔ ADDED']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'" + Al.get(i)
					+ "')]/parent::div[@class='product']/div[@class='product-action']/button[@type='button'][text()='ADD TO CART']")));
		}
//	 	Integer.parseInt(driver.findElement(By.xpath("//div[@class='cart-info']//td[text()='Price']/parent::tr/td/strong")).getText());
		Assert.assertEquals(Integer.parseInt(driver
				.findElement(By.xpath("//div[@class='cart-info']//td[text()='Price']/parent::tr/td/strong")).getText()),
				sum * 2);
		Assert.assertEquals(Integer.parseInt(driver
				.findElement(By.xpath("//div[@class='cart-info']//td[text()='Items']/parent::tr/td/strong")).getText()),
				Al.size());

//		Click on the cart
		driver.findElement(By.cssSelector("a.cart-icon img[alt='Cart']")).click();
//		storing the cart items into another arraylist and comparing it with previous arraylist
		List<WebElement> cartList = driver.findElements(By.cssSelector("div.cart-preview li.cart-item p.product-name"));
		ArrayList<String> Al1 = new ArrayList<String>();
		for (WebElement cartItem : cartList) {
			jse.executeScript("arguments[0].scrollIntoView(true);", cartItem);
			Al1.add(cartItem.getText().split("-")[0].trim());
		}
//		System.out.println(Al);
//		System.out.println(Al1);
		Assert.assertEquals(true, Al.equals(Al1));
		for (int i = 0; i < Al1.size(); i++) {
//			Validating the doubling is happened or not
			int n = Integer.parseInt(driver
					.findElement(
							By.xpath("//div[@class='cart-preview active']//p[@class='product-name'][contains(text(),'"
									+ Al1.get(i) + "')]/parent::div[@class='product-info']/p[@class='product-price']"))
					.getText());
			int m = Integer.parseInt(driver.findElement(By.xpath(
					"//div[@class='cart-preview active']//p[@class='product-name'][contains(text(),'" + Al1.get(i)
							+ "')]/parent::div[@class='product-info']/following-sibling::div[@class='product-total']/p[@class='amount']"))
					.getText());
			Assert.assertEquals(n * 2, m);
//			Quantity verification as '2'
			Assert.assertEquals("2 Nos.", driver.findElement(By.xpath(
					"//div[@class='cart-preview active']//p[@class='product-name'][contains(text(),'" + Al1.get(i)
							+ "')]/parent::div[@class='product-info']/following-sibling::div[@class='product-total']/p[@class='quantity']"))
					.getText());
		}

//		Click on the checkout page
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();

//		Make the Arraylist of the product name
		List<WebElement> productNameList = driver
				.findElements(By.cssSelector("table#productCartTables tbody td:nth-child(2) p"));
		ArrayList<String> Al2 = new ArrayList<String>();
		for (WebElement productName : productNameList) {
			jse.executeScript("arguments[0].scrollIntoView(true);", productName);
			Al2.add(productName.getText().split("-")[0].trim());
		}
		Assert.assertEquals(true, Al.equals(Al2));
		for (int i = 0; i < Al2.size(); i++) {

//			Quantity verification as '2'
			Assert.assertEquals(
					2, Integer
							.parseInt(driver
									.findElement(
											By.xpath("//table[@id='productCartTables']//tbody//td/p[contains(text(),'"
													+ Al2.get(i) + "')]/parent::td/parent::tr/td/p[@class='quantity']"))
									.getText()));
//			Note the price
			int n1 = Integer.parseInt(
					driver.findElement(By.xpath("(//table[@id='productCartTables']//tbody//td/p[contains(text(),'"
							+ Al2.get(i) + "')]/parent::td/parent::tr/td/p[@class='amount'])[1]")).getText());
			int m1 = Integer.parseInt(
					driver.findElement(By.xpath("(//table[@id='productCartTables']//tbody//td/p[contains(text(),'"
							+ Al2.get(i) + "')]/parent::td/parent::tr/td/p[@class='amount'])[2]")).getText());
			Assert.assertEquals(n1 * 2, m1);
		}

//		Enter the promoCode check it is valid or not
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("Test!");
		driver.findElement(By.cssSelector("button.promoBtn")).click();

//		Wait for the loading button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promo-btn-loader']")));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='promoBtn'][text()='Apply']")));
//		Validate the msg
		Assert.assertEquals("Invalid code ..!", driver.findElement(By.cssSelector("span.promoInfo")).getText());

//		click on the place order
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();

//		wait for select country to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Choose Country']")));

//		Select the country as India

		Select s = new Select(driver.findElement(By.tagName("select")));
		s.selectByValue("India");
		Assert.assertEquals(false, driver.findElement(By.cssSelector("input.chkAgree")).isSelected());
		driver.findElement(By.cssSelector("input.chkAgree")).click();
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input.chkAgree")).isSelected());
//		Click on proceed
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();

//		Verify the thank you page
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(text(),'Thank you, your order has been placed successfully')]")));
	}

}
