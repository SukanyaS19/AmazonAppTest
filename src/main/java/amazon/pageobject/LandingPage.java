package amazon.pageobject;

import java.util.concurrent.TimeUnit;

import amazon.utilities.ReadExcelData;
import amazon.utilities.Report;
import amazon.utilities.Utility;

public class LandingPage extends Utility {
	
	String searchBox="//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']";
	String searchedProductList="//*[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions']";
	String TotalProducts="com.amazon.mShop.android.shopping:id/rs_results_count_text";
	String ProductList="//*[@resource-id='com.amazon.mShop.android.shopping:id/list_product_description_layout']";
	
	/*
	 * @author Sukanya 
	 * Decription:Search Product in the app by reading the testdata from excel
	 * Attributes:readExcel-class object for getting the data for the particular testcase 
	 * 			  report - class object for generating HTML report and logging
	 */
	public void productSearch(ReadExcelData readExcel, Report report) {
		waitForElementToBeClickable("xpath", searchBox,report);
		clickElement("xpath", searchBox,report);
		waitForElementToBeClickable("xpath", searchBox,report);
		sendKeysElement("xpath", searchBox,readExcel.getValue("SearchText"),report);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		picktRandomValue("xpath", searchedProductList, report);	
	}
	
	/*
	 * @author Sukanya 
	 * Decription:Selecting random product from the result
	 * Attributes: report - class object for generating HTML report and logging
	 */
	public void selectProduct(Report report) {
		waitForElementToBeClickable("id", TotalProducts,report);
		picktRandomValue("xpath", ProductList, report);
	}
}
