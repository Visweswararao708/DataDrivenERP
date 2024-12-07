package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import applicatinLayer.AdminLogin;
import applicatinLayer.AdminLogout;

public class Base {
public static WebDriver driver;
public static Properties conpro;
@BeforeTest
public static void setup()throws Throwable
{
	conpro = new Properties();
	// load property file
	conpro.load(new FileInputStream("PropertyFiles/Envinorment.Properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	else if (conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	else 
	{
		Reporter.log("Bowser value is not Matching",true);
	}
}
@AfterTest
public static void teardown()throws Throwable
{
	AdminLogout logout = PageFactory.initElements(driver,AdminLogout.class);
	logout.verifyLogout();
	driver.quit();
}

}
