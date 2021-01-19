package amazon.pageobject;

import java.util.concurrent.TimeUnit;

import amazon.utilities.Report;
import amazon.utilities.Utility;

public class AddProduct extends Utility {
	
	//String productName="//*[@resource-id='title_feature_div']//*[@class='android.view.View']";
	String productName="//*[@resource-id='title_feature_div']//android.view.View";
	String addToCart = "//*[contains(@resource-id,'add-to-cart-button')]";
	String cartIcon = "com.amazon.mShop.android.shopping:id/action_bar_cart_count";
	
	
	
	public String getProductDetails(Report report)
	{
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		//waitForElementToBeClickable("xpath", productName);
		
		String productNameUi=getText("xpath",productName,report);
		System.out.println(productNameUi);
		scrollToElement(addToCart);
	clickElement("xpath", addToCart, report);
		return productNameUi;

	}
	
	public void navigateToCart(Report report)
	{
		waitForElementToBeClickable("id", cartIcon);

		clickElement("id", cartIcon, report);
	}

}
