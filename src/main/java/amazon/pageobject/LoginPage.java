package amazon.pageobject;

import amazon.utilities.Report;
import amazon.utilities.Utility;

public class LoginPage extends Utility {
	
	String appLogo="android.widget.ImageView/[@resource-id='com.amazon.mShop.android.shopping:id/sso_splash_logo']";
	String signIn="//*[@text='Skip sign in']";
	/*
	 * @author Sukanya 
	 * Decription:Login to the app
	 * Attributes: report - class object for generating HTML report and logging
	 */
	
	public void login(Report report)
	{
		
		waitForElementToBeClickable("xpath",signIn,report);
		clickElement("xpath", signIn,report);
	}
}
