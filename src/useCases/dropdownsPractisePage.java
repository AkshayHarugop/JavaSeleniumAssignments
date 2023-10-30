package useCases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.browserRelated;
import Utilities.loginRelated;

public class dropdownsPractisePage {

	public static void main(String[] args) {
//		https://rahulshettyacademy.com/dropdownsPractise/
		holidayPackage();
		specialAssistance();
		checkboxAndRadio();
		oneWay();
		roundTrip();
		multiTrip();
	}
	
	

	public static void holidayPackage() {
		WebDriver driver = loginRelated.flightLogin();
		WebDriverWait wait = loginRelated.wait(driver);
//		autosuggest dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> dropdowns = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
		for (WebElement i : dropdowns) {
			if (i.getText().equals("India")) {
				i.click();
			}
		}

//		click on Holiday Package tab
		driver.findElement(By.xpath("//li[@class='holiday-packages']")).click();

//		Wait for page to load adding some checkpoints
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='DESTINATION']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='DEPARTURE']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='RETURN']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='DEPART DATE']")));

//		
		driver.findElement(By.id("ctl00_mainContent_HolidayPackages_DropDownListPackage_CTXT")).click();
		driver.findElement(By.xpath(
				"//div[@id='glsctl00_mainContent_HolidayPackages_DropDownListPackage_CTNR']//a[@text='Bengaluru']"))
				.click();
		driver.findElement(By.id("ctl00_mainContent_HolidayPackages_DropDownListFrom_CTXT")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//div[@id='glsctl00_mainContent_HolidayPackages_DropDownListFrom_CTNR']//a[@text=' Pune  ']")));
		driver.findElement(
				By.xpath("//div[@id='glsctl00_mainContent_HolidayPackages_DropDownListFrom_CTNR']//a[@text=' Pune  ']"))
				.click();
		driver.findElement(By.id("ctl00_mainContent_HolidayPackages_DropDownListTo_CTXT")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[@id='glsctl00_mainContent_HolidayPackages_DropDownListTo_CTNR']//a[@text=' Bengaluru ']")));
		driver.findElement(By
				.xpath("//div[@id='glsctl00_mainContent_HolidayPackages_DropDownListTo_CTNR']//a[@text=' Bengaluru ']"))
				.click();

//		Selecting departure date
		driver.findElement(By.id("ctl00_mainContent_HolidayPackages_TxtTravelDate")).click();
		driver.findElement(
				By.xpath("//td[contains(@class,' ui-datepicker-days-cell-over  ui-datepicker-current-day')]")).click();

//		click on search
		driver.findElement(By.id("btnFindHolidays")).click();

//		Switch to second window
		Set<String> window = driver.getWindowHandles();
		Iterator<String> It = window.iterator();
		String parentId = It.next();
		String childId = It.next();
		driver.switchTo().window(childId);

//		click on the reload button
		driver.findElement(By.xpath("//button[@id='reload-button']")).click();
		driver.close();

//		Switch to parent window
		driver.switchTo().window(parentId);

//		Again click on search
		driver.findElement(By.id("btnFindHolidays")).click();
		
		ArrayList<String> win = browserRelated.multiWindowHandling(driver);
		driver.switchTo().window(win.get(1));

//		click on the reload button
		driver.findElement(By.xpath("//button[@id='reload-button']")).click();
		driver.close();

//		Switch to parent window
		driver.switchTo().window(browserRelated.parentWindow(driver));
		driver.close();

	}

	public static void checkboxAndRadio() {
		WebDriver driver = loginRelated.flightLogin();
//		autosuggest dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> dropdowns = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
		for (WebElement i : dropdowns) {
			if (i.getText().equals("India")) {
				i.click();
			}
		}

//		Radio button validation
		List<WebElement> radioButtonSize = driver
				.findElements(By.xpath("(//input[contains(@id,'ctl00_mainContent_rbtnl_Trip_')][@type='radio'])"));
		for (int i = 1; i < radioButtonSize.size() + 1; i++) {
			if (i == 1) {
				Assert.assertEquals(true,
						driver.findElement(By.xpath(
								"(//input[contains(@id,'ctl00_mainContent_rbtnl_Trip_')][@type='radio'])[" + i + "]"))
								.isSelected());
			} else {
				Assert.assertEquals(false,
						driver.findElement(By.xpath(
								"(//input[contains(@id,'ctl00_mainContent_rbtnl_Trip_')][@type='radio'])[" + i + "]"))
								.isSelected());
			}
		}

//		Checkbox valiadations
		List<WebElement> checkBoxSize = driver.findElements(By.xpath(
				"//div[@id='discount-checkbox']//input[contains(@id,'ctl00_mainContent_chk_')][@type='checkbox']"));
		for (int i = 1; i <= checkBoxSize.size(); i++) {
			Assert.assertEquals(false, driver.findElement(By.xpath(
					"(//div[@id='discount-checkbox']//input[contains(@id,'ctl00_mainContent_chk_')][@type='checkbox'])["
							+ i + "]"))
					.isSelected());
		}

//		exit the browser
		driver.close();
	}

	public static void specialAssistance() {
		WebDriver driver = loginRelated.flightLogin();
//		autosuggest dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> dropdowns = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
		for (WebElement i : dropdowns) {
			if (i.getText().equals("India")) {
				i.click();
			}
		}
		driver.findElement(By.xpath("//a[text()='Special Assistance']")).click();
		Assert.assertEquals("SPECIAL ASSISTANCE",
				driver.findElement(By.xpath("//div[@class='SpecialAssistanceModalHeader']/h2")).getText());
//		close the specialAssistance window
		driver.findElement(By.xpath("//a[@id='SpecialAssistanceWindow']")).click();

//		exit the browser
		driver.close();
	}

	public static void oneWay() {
		WebDriver driver = loginRelated.flightLogin();
		WebDriverWait wait = loginRelated.wait(driver);
//		autosuggest dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> dropdowns = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
		for (WebElement i : dropdowns) {
			if (i.getText().equals("India")) {
				i.click();
			}
		}

//		Click on the Departure City > Select IXG > Select AMD as Arrival city > Select todays date
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='IXG']"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='AMD']")));
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='AMD']"))
				.click();
		driver.findElement(By.xpath("//td[contains(@class,' ui-datepicker-days-cell-over  ui-datepicker-today')]"))
				.click();

//		Select the required number of passangers
		driver.findElement(By.id("divpaxinfo")).click();
		for (int i = 0; i < 9; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		String alertText = driver.switchTo().alert().getText();
//		System.out.println(alertText);
		Assert.assertEquals(alertText,
				"You are allowed a maximum of 9 passengers per booking online. If your party is larger than this, please call our reservation center.");
		driver.switchTo().alert().accept();
//		click on Done
		driver.findElement(By.id("btnclosepaxoption")).click();

//		Select currency
		WebElement currency = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select s = new Select(currency);
		s.selectByValue("AED");

//		Click on the search
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_btn_FindFlights']")).click();

//		exit the browser
		driver.close();
	}

	private static void multiTrip() {
		WebDriver driver = loginRelated.flightLogin();
		WebDriverWait wait = loginRelated.wait(driver);
//		autosuggest dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> dropdowns = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
		for (WebElement i : dropdowns) {
			if (i.getText().equals("India")) {
				i.click();
			}
		}
//		Select the Multicity
		driver.findElement(By.xpath("//label[text()='Multicity']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='MultiCityModelPopup']/h2[text()='Information']")));
		String info = driver.findElement(By.xpath("(//div[@class='MultiCityContent']/p)[1]")).getText();
		Assert.assertEquals(info,
				"Multicity booking does not mean connecting flight. Customers must ensure sufficient connecting time between flights. QAclickjet will not be responsible for missed connections due to any reason.");
		driver.findElement(By.xpath("//a[@id='MultiCityModelAlert']")).click();

//		Click on the Departure City > Select IXG > Select AMD as Arrival city > Select todays date
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='IXG']"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='AMD']")));
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='BLR']"))
				.click();
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_view_date1']")).click();
		driver.findElement(By.xpath("//td[contains(@class,' ui-datepicker-days-cell-over  ui-datepicker-today')]"))
				.click();

//		Select the required number of passangers
		driver.findElement(By.id("divpaxinfo")).click();
		for (int i = 0; i < 9; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		String alertText = driver.switchTo().alert().getText();
//		System.out.println(alertText);
		Assert.assertEquals(alertText,
				"You are allowed a maximum of 9 passengers per booking online. If your party is larger than this, please call our reservation center.");
		driver.switchTo().alert().accept();
//		click on Done
		driver.findElement(By.id("btnclosepaxoption")).click();

//		Select currency
		WebElement currency = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select s = new Select(currency);
		s.selectByValue("AED");

//		Click on the Departure City > Select IXG > Select AMD as Arrival city > Select todays date
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation2_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation2_CTNR']//a[@value='IXG']"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation2_CTNR']//a[@value='AMD']")));
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation2_CTNR']//a[@value='AMD']"))
				.click();

//		Click on the Departure City > Select IXG > Select AMD as Arrival city > Select todays date
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation3_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation3_CTNR']//a[@value='AIP']"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation3_CTNR']//a[@value='BHO']")));
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation3_CTNR']//a[@value='BHO']"))
				.click();

//		Click on the search
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_btn_FindFlights']")).click();

//		Wait for one way
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='One Way']")));

//		exit the browser
		driver.close();

	}

	private static void roundTrip() {
		WebDriver driver = loginRelated.flightLogin();
		WebDriverWait wait = loginRelated.wait(driver);
//		autosuggest dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("Ind");
		List<WebElement> dropdowns = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
		for (WebElement i : dropdowns) {
			if (i.getText().equals("India")) {
				i.click();
			}
		}
//		Select the round Trip
		driver.findElement(By.xpath("//label[text()='Round Trip']")).click();

//		Click on the Departure City > Select IXG > Select AMD as Arrival city > Select todays date
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='IXG']"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='AMD']")));
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='AMD']"))
				.click();
		driver.findElement(By.xpath("//td[contains(@class,' ui-datepicker-days-cell-over  ui-datepicker-today')]"))
				.click();

//		select the return date
		driver.findElement(By.id("ctl00_mainContent_view_date2")).click();
		driver.findElement(By.xpath(
				"//td[contains(@class,' ui-datepicker-days-cell-over  ui-datepicker-current-day ui-datepicker-today')]"))
				.click();

//		Select the required number of passangers
		driver.findElement(By.id("divpaxinfo")).click();
		for (int i = 0; i < 9; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		String alertText = driver.switchTo().alert().getText();
//		System.out.println(alertText);
		Assert.assertEquals(alertText,
				"You are allowed a maximum of 9 passengers per booking online. If your party is larger than this, please call our reservation center.");
		driver.switchTo().alert().accept();
//		click on Done
		driver.findElement(By.id("btnclosepaxoption")).click();

//		Select currency
		WebElement currency = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select s = new Select(currency);
		s.selectByValue("AED");

//		Click on the search
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_btn_FindFlights']")).click();

//		exit the browser
		driver.close();

	}
}
