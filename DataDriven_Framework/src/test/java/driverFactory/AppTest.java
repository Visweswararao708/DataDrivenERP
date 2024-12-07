package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import applicatinLayer.AddEmp;
import config.Base;
import utilities.ExcelFileUtil;

public class AppTest extends Base{
String inputpath = "./Fileinput/Employee.xlsx";
String outputpath = "./Fileoutput/DataDrivenResults.xlsx";
ExtentReports reports;
ExtentTest logger;
String TCSheet ="EmpData";
@Test
public void startTest() throws Throwable
{
	//define path of htmi report in to target folder
	reports = new ExtentReports("./target/reports/AddEmp.html");
	// create reference object for excel file utill class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	// count no of rows in TCSheet
	int rc = xl.rowCount(TCSheet);
	Reporter.log("No of rows are:::"+rc);
	for(int i=1;i<=rc;i++)
	{
		// start test here
		logger = reports.startTest("Validate Add Emp");
		logger.assignAuthor("visweswararao");
		// read all cells from TCSheet
		String Fname = xl.getCellData(TCSheet, i, 0);
		String Mname = xl.getCellData(TCSheet, i, 1);
		String Lname = xl.getCellData(TCSheet, i, 2);
		AddEmp emp = PageFactory.initElements(driver, AddEmp.class);
		boolean res = emp.verifyEmp(Fname, Mname, Lname);
		if(res)
		{
			// if res is true write as pass in status cell
			xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
		}
		else
		{
			// if res is false write as fail in status cell
			xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
		}
		reports.endTest(logger);
		reports.flush();
	}
}

}
