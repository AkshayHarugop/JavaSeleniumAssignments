package Utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class keys {
	
	public static String ctlrClick(WebDriver driver) {
		String ctlrClick = Keys.chord(Keys.CONTROL,Keys.ENTER);
		return ctlrClick;
	}

}