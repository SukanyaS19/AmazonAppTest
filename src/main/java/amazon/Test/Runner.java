package amazon.Test;

import amazon.pageobject.AddProduct;
import amazon.pageobject.Cart;
import amazon.pageobject.LandingPage;
import amazon.pageobject.LoginPage;
import amazon.utilities.ReadExcelData;
import amazon.utilities.Report;
import amazon.utilities.Utility;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Runner extends Utility {
	
	Report extentReport;
	/*
	 * @author=sukanya
	 * Description-Creating constructor for initializing the class variables and the parent class
	 */
	public Runner() {
		super();
	}
	
	
	/*public void killAllNodes() throws IOException, InterruptedException
	{
	//taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		
	}*/
	
	
	@BeforeTest
	public void driverInitialize() {
		Runner run=new Runner();
		extentReport=new Report();
		extentReport.extentReportInit();
		extentReport.logger=extentReport.report.createTest("Amazon");
		driverinit(extentReport);
	}
	
	/*
	 * Description: To Run all the test
	 * Created By: Sukanya  
	 */

	@Test
	public void TestApp() {
		
		ReadExcelData readExcel=new ReadExcelData();
		readExcel.excelRead("AmazonTest");
		new LoginPage().login(extentReport);
		new LandingPage().productSearch(readExcel,extentReport);
		new LandingPage().selectProduct(extentReport);
		String productName=new AddProduct().getProductDetails(extentReport);
		new AddProduct().navigateToCart(extentReport);
		new Cart().compareProductDetails(productName,extentReport);
		new Cart().navigateToCheckout(extentReport);
		
	}
	/*
	 * Description: After Method to tear down the driver and check execution status
	 * Created By: Sukanya
	 */
	
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{

		if(ITestResult.FAILURE==result.getStatus())
		{
			extentReport.extentReportFail(result.getThrowable().getMessage());
			//extentReport.logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		driver.quit();
		extentReport.report.flush();
	}
	
	

}
