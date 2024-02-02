package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static Properties canpro;
public static WebDriver driver;
@BeforeTest
public static void setup()throws Throwable
{
	canpro = new Properties();
	//load property file
	canpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(canpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	else if(canpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	else
	{
		Reporter.log("browser value is not matching",true);
	}
}
@AfterTest
public static void teardown()
{
	driver.quit();
	
}
}
