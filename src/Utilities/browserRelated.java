package Utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class browserRelated {

	static String parentId;

	public static ArrayList<String> multiWindowHandling(WebDriver driver) {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> It = window.iterator();
		ArrayList<String> Al = new ArrayList<String>();
		while(It.hasNext()) {
			Al.add(It.next());
		}
		return Al;
	}

	public static String parentWindow(WebDriver driver) {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> It = window.iterator();
		parentId = It.next();
		return parentId;
	}
	
	public static void isImageBroken(WebElement image)
	{
	    if (image.getAttribute("naturalWidth").equals("0"))
	    {
	        System.out.println(image.getAttribute("outerHTML") + " is broken.");
	    }
	}

}
