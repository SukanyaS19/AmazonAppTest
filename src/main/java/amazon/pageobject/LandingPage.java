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
	
	
	public void productSearch(ReadExcelData readExcel, Report report) {
		waitForElementToBeClickable("xpath", searchBox);
		clickElement("xpath", searchBox,report);
		waitForElementToBeClickable("xpath", searchBox);
		sendKeysElement("xpath", searchBox,readExcel.getValue("SearchText"),report);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		picktRandomValue("xpath", searchedProductList, report);	
	}
	
	public void selectProduct(Report report) {
		waitForElementToBeClickable("id", TotalProducts);
		picktRandomValue("xpath", ProductList, report);
	}
}
