package amazon.pageobject;

import amazon.utilities.Report;
import amazon.utilities.Utility;

public class LoginPage extends Utility {
	
	String appLogo="android.widget.ImageView/[@resource-id='com.amazon.mShop.android.shopping:id/sso_splash_logo']";
	String signIn="//*[@text='Skip sign in']";
	/*
	 * @author dell
	 * Decription:Login to the app
	 */
	
	public void login(Report report)
	{
		//waitForElementToBeVisible("xpath", appLogo);
		waitForElementToBeClickable("xpath",signIn);
		clickElement("xpath", signIn,report);
	}
}
