package amazon.pageobject;


import amazon.utilities.Report;
import amazon.utilities.Utility;

public class AddProduct extends Utility {
	
	String productName="//*[@resource-id='title_feature_div']//android.view.View";
	String addToCart = "//*[contains(@resource-id,'add-to-cart-button')]";
	String cartIcon = "com.amazon.mShop.android.shopping:id/action_bar_cart_count";
	
	/*
	 * @author Sukanya 
	 * Decription:Get the text of the product name
	 * Attributes: report - class object for generating HTML report and logging
	 */
	
	public String getProductDetails(Report report) 
	{
		clickAndWait("xpath", productName,report);
		String productNameUi=getText("xpath",productName,report);
		
		scrollToElement(addToCart,report);
		clickElement("xpath", addToCart, report);
		return productNameUi;

	}
	
	/*
	 * @author Sukanya 
	 * Decription:Navigate to the Application cart
	 * Attribute: report - class object for generating HTML report and logging 
	 */
	public void navigateToCart(Report report)
	{
		waitForElementToBeClickable("id", cartIcon,report);

		clickElement("id", cartIcon, report);
	}

}
