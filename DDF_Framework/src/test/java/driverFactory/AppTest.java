package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import config.AppUtil;
import utilites.ExcelFileUtil;

public class AppTest extends AppUtil{
	String inputpath = "./FileInput/LoginData.xlsx";
	String outputpath ="./FileOutput/DataDrivenResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void starttest() throws Throwable
	{
		//define path of html
		report = new ExtentReports("./target/reports/datadriven.html");
		//create object for excelfile util class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc = xl.rowcount("login");
		Reporter.log("no of rows::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = report.startTest("validate login");
			String user = xl.getcellData("login", i, 0);
			String pass = xl.getcellData("login", i, 1);
			//call adminlogin method from function library class
			boolean res = FunctionLibrary.adminLogin(user, pass);
			if(res)
			{
				//write as login success into results cell
				xl.setcelldata("login", i, 2, "login sucess", outputpath);
				//write as pass into status cell
				xl.setcelldata("login", i, 3, "pass", outputpath);
				logger.log(LogStatus.PASS, "valid username and password");
			}
			else
			{
				//takescreenshot and store
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copy screenshot into local system
				FileUtils.copyFile(screen, new File("./screenshot/iteration/"+i+"Loginpage.png"));
				//write as login Fail into results cell
				xl.setcelldata("login", i, 2, "login fail", outputpath);
				//write as fail into status cell
				xl.setcelldata("login", i, 3, "fail", outputpath);
				logger.log(LogStatus.FAIL, "invalid username and password");
			}
			report.endTest(logger);
			report.flush();
		}
	}
}
