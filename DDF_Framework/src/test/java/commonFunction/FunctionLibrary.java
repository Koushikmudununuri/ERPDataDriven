package commonFunction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean adminLogin(String username,String password) throws Throwable
{
	driver.get(canpro.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(canpro.getProperty("ObjReset"))).click();
	driver.findElement(By.xpath(canpro.getProperty("Objuser"))).sendKeys(username);
	driver.findElement(By.xpath(canpro.getProperty("Objpass"))).sendKeys(password);
	driver.findElement(By.xpath(canpro.getProperty("Objlogin"))).click();
	String expected = "dashboard";
	String actual = driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("username and password are valid::"+expected+"_________"+actual,true);
		//click logout link
		driver.findElement(By.xpath(canpro.getProperty("ObjLogout"))).click();
		return true;
	}
	else
	{
	
		String Errormessage = driver.findElement(By.xpath(canpro.getProperty("ObjEroor_Messsage"))).getText();
		driver.findElement(By.xpath(canpro.getProperty("Objokbutton"))).click();
		Reporter.log(Errormessage+"_______"+expected+"______"+actual,true);
		return false;
	}
}
}
