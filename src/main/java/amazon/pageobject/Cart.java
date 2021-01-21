package amazon.pageobject;

import java.util.StringTokenizer;

import amazon.utilities.Report;
import amazon.utilities.Utility;

public class Cart extends Utility {
	String productName="(//*[@resource-id='activeCartViewForm']//*[@class='android.widget.TextView'])[1]";
	String productCount = "//android.widget.TextView";
	String checkOutBtn = "(//*[@resource-id='sc-mini-buy-box']//*[@class='android.widget.Button'])";
	
	
	/*
	 * Description: Method to Compare Cart product details 
	 * Created By:Sukanya
	 * Attributes: report - class object for generating HTML report and logging
	 * 				prodName - Product Name retrieved from product details page for comparison 
	 */
	public void compareProductDetails(String prodName, Report report)
	{

		waitForElementToBeClickable("xpath", productName,report);
		String uiProdName=getText("xpath",productName,report);
		System.out.println("uiProdName"+uiProdName);
		StringTokenizer st=new StringTokenizer(uiProdName, "...");
		System.out.println("prodName"+prodName);
		System.out.println("uiProdName"+uiProdName);
		stringContains(st.nextToken(),prodName,report);
		String elemCount=getText("xpath", productCount,report);
		stringContains(elemCount, "1",report);

	}
	
	/*
	 * Description: Method to navigate to checkout page
	 * Created By:Sukanya
	 * Attribute: report - class object for generating HTML report and logging
	 */
	public void navigateToCheckout( Report report)
	{
		clickElement("xpath",checkOutBtn,report);
	}
	
	

}
