package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class StockTransferPage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	
	public StockTransferPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*-------------------------- Username & Password --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUser']")
	private WebElement unBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	private WebElement pwdBTN;
}
