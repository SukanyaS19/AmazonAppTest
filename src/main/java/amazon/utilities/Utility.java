package amazon.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class Utility {
	
	public static AndroidDriver<AndroidElement>driver;
	public DesiredCapabilities cap ;
	Properties prop;
		
	/**
	 * @author Sukanya
	 * @Description creating Desired capability and properties objects 
	 */
	public Utility() {
		cap=new DesiredCapabilities();
		prop=new Properties();
	}
	/**
	 * @author Sukanya
	 * @throws MalformedURLException
	 * Description-initiating driver
	 * Attribute: report-class object of Report to generate extent report
	 */
	public void driverinit(Report extentReport) {
		try {
		prop=loadPropertyFile(System.getProperty("user.dir")+"\\src\\resource\\java\\propertiesFile\\global.properties");
		File appDir = new File("src");
	    File app = new File(appDir, prop.getProperty("appName"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,prop.getProperty("device"));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability("appPackage",prop.getProperty("appPackage"));
		cap.setCapability("appActivity",prop.getProperty("appActivity"));
		driver = new AndroidDriver<>(new URL(prop.getProperty("port")), cap);
			} 
		catch (MalformedURLException e) {
			e.printStackTrace();
			extentReport.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/**
	 *@author Sukanya
	 * Description- Loading property File
	 * Attribute: Path: Set path to the global.properties file
	 */
	public Properties loadPropertyFile(String Path) {
		try {
			FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"\\src\\resource\\java\\propertiesFile\\global.properties");
			
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
				Assert.assertTrue(false, e.getMessage());
			}
		
		return prop;
	}

	/**
	 *@author Sukanya
	 * Description- Reusable Click action
	 * Attribute: elementType- Element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 * 			  report- Class object of Report to generate extent report
	 */
	
	public void clickElement(String elementType,String identifier,Report report) {
		try 
		{
		if(elementType.equalsIgnoreCase("id"))
			driver.findElement(By.id(identifier)).click();
		else if(elementType.equalsIgnoreCase("xpath"))
			driver.findElement(By.xpath(identifier)).click();
		
		report.extentReportPass(identifier+ "is clicked");
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable sendkeys action
	 *  Attribute: elementType- Element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 * 			  value- To enter text into any field
	 * 			  report- Class object of Report to generate extent report
	 */
	public void sendKeysElement(String elementType,String identifier,String value,Report report) {
		try {
		if(elementType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(identifier)).click();
			driver.findElement(By.id(identifier)).sendKeys(value);
			report.extentReportPass(identifier+ "is inputed with "+value);
		}
		else if(elementType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(identifier)).click();
			driver.findElement(By.xpath(identifier)).sendKeys(value);			
			report.extentReportPass(identifier+ "is inputed with "+value);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		report.extentReportFail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
	}
		
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable to get the text of the element
	 * Attribute: elementType- Element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 * 			  report- Class object of Report to generate extent report
	 */
	
	public String getText(String elementType,String identifier,Report report)
	{

		String text="";
		try {
			if(elementType.equalsIgnoreCase("id"))
				text=driver.findElement(By.id(identifier)).getText();
			else if(elementType.equalsIgnoreCase("xpath"))
				text=driver.findElement(By.xpath(identifier)).getText();
			report.extentReportPass(text+" is printed from "+identifier);
	}
	catch (Exception e) {
		e.printStackTrace();
		report.extentReportFail(e.getMessage());
	}
		return text;
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable method to wait till the element is discovered
	 * Attribute: elementType- Element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 * 			  report- Class object of Report to generate extent report
	 */

	public void waitForElementToBeClickable(String elementType,String identifier,Report report)
	{
		try {
			WebDriverWait wait=new WebDriverWait(driver, 60);
			if(elementType.equalsIgnoreCase("id"))
				wait.until(ExpectedConditions.elementToBeClickable(By.id(identifier)));
			else if(elementType.equalsIgnoreCase("xpath"))
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(identifier)));
				}
	catch (Exception e) {
		e.printStackTrace();
		Assert.assertTrue(false, e.getMessage());
		report.extentReportFail(e.getMessage());
	}
		
		}
	
		
	/**
	 *@author Sukanya
	 * Description- Reusable to pick random values
	 * Attribute: elementType- Element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 * 			  report- Class object of Report to generate extent report
	 */
	public void picktRandomValue(String elementType,String identifier,Report report)
	{
		try
		{
			int size=0;
			Random rand = new Random();
			if(elementType.equalsIgnoreCase("id"))
			{
				size=driver.findElements(By.id(identifier)).size();
				int num=rand.nextInt(size);
				driver.findElements(By.id(identifier)).get(num).click();
				
			}
			else if(elementType.equalsIgnoreCase("xpath"))
			{
				size=driver.findElements(By.xpath(identifier)).size();
				int num=rand.nextInt(size);
				driver.findElements(By.xpath(identifier)).get(num).click();
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
		}
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable to verify if element is present
	 * Attribute: property- xpath value of the element to be identified 
	 */
	public boolean verifyElement(String property) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean present = true;
		try {
			driver.findElement(By.xpath(property));
			return present;

		} catch (Exception e) {
			present = false;
			e.printStackTrace();
			return present;
		}
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable method to scroll till we find the obj
	 * Attribute:property- xpath value of the element to be identified
	 * 			 report- Class object of Report to generate extent report
	 * 
	 */
	
	public void scrollToElement(String property,Report report) {
		int count=0;
		try {
		while (true) {
			if (verifyElement(property)) {
				report.extentReportPass("Element visible");
				break;
			}
			scrollFromTopToBottom();
			count++;
			if(count>10)
				Assert.assertTrue(false, "Element Not Visible");
					}
			}
		
		catch(Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());			
		}
		
		
		
	}
			
	/**
	 *@author Sukanya
	 * Description- Reusable to scroll fully from top to bottom of the screen
	 */
	private void scrollFromTopToBottom() {
		try {
			Thread.sleep(2000);
			Dimension scrnSize = driver.manage().window().getSize();
			int startx = (int) (scrnSize.width / 2);
			int endy = (int) (scrnSize.height*0.9);
			int starty = (int) (scrnSize.height * 0.2);


			(new TouchAction(driver))
			.press(PointOption.point(startx, endy))
			.moveTo(PointOption.point(startx,starty))
			.release()
			.perform();



		} catch (InterruptedException e) {
			Assert.assertTrue(false,e.getMessage());
		}
		
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable method to compare two products
	 * Attribute: smallString-String value to be compared with
	 * 			  bigString- String value to be compared in
	 * 			  report- Class object of the Report class for generating extent report
	 */
	
	public void stringContains(String smallString,String bigString,Report report)
	{
		if(bigString.contains(smallString))
		{
			Assert.assertTrue(true, "String comparison Successful");
			report.extentReportPass("String match Successful");
			
		}
		else
		{
			Assert.assertTrue(false, "String comparison Failed expected:"+bigString+" contains "+smallString );
			report.extentReportFail("String match Failed");
			
		}
	}
	
	/**
	 *@author Sukanya
	 * Description- Reusable method for implicit wait 
	 * Attribute:elementType- element type String passed is an id or xpath
	 * 			  identifier- unique element identifier
	 */
	public void clickAndWait(String elementType,String identifier,Report report)  {
	
		try {
		for(int i=0;i<60;i++){
		WebDriverWait wait= new WebDriverWait(driver,10);
		if(elementType.equalsIgnoreCase("id"))
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(identifier)));
		if(elementType.equalsIgnoreCase("xpath"))
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(identifier)));
		report.extentReportPass(identifier +"is visible");
		break;
			}
		}
		catch(Exception e) {
				e.printStackTrace();
				report.extentReportFail(e.getMessage()); 
					
				}
			}
			
	}
	
	
	



