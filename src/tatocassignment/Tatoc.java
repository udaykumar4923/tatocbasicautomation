package tatocassignment;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//import com.gargoylesoftware.htmlunit.util.Cookie;

public class Tatoc {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.linkText("Basic Course")).click();
		
		
		driver.findElement(By.className("greenbox")).click();
		
		
		driver.switchTo().frame(driver.findElement(By.id("main")));
		String firstcolor = driver.findElement(By.id("answer")).getAttribute("class");
		driver.switchTo().frame(driver.findElement(By.id("child")));
		String secondcolor = driver.findElement(By.id("answer")).getAttribute("class");
		driver.switchTo().defaultContent();
		while(firstcolor.equals(secondcolor) != true) {
			driver.switchTo().frame(driver.findElement(By.id("main")));
			driver.findElement(By.linkText("Repaint Box 2")).click();
			driver.switchTo().frame(driver.findElement(By.id("child")));
			secondcolor = driver.findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().defaultContent();
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("main")));
		driver.findElement(By.linkText("Proceed")).click();
		
		
		WebElement dragfrom = driver.findElement(By.id("dragbox"));
		WebElement dropto = driver.findElement(By.id("dropbox"));
		Actions act = new Actions(driver);
		act.dragAndDrop(dragfrom,dropto).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.partialLinkText("Launch")).click();
		ArrayList windowslist = new ArrayList(driver.getWindowHandles());
		String window1 = ((String)windowslist.get(1));
		System.out.println(window1);
		driver.switchTo().window(window1);
		driver.findElement(By.id("name")).sendKeys("uday");
		driver.findElement(By.id("submit")).click();
		String window2 = (String)(windowslist.get(0));
		driver.switchTo().window(window2);
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Generate Token")).click();
		String token = driver.findElement(By.id("token")).getText();
		String tokenid = token.substring(7, token.length());
		Cookie cookie = new Cookie("Token",tokenid);
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();
	}

}
