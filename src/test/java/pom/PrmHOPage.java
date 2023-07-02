package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class PrmHOPage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	
	public PrmHOPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*-------------------------- Actions --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSearch']")
	private WebElement searchTB;
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwProdRegManager']/tbody/tr[1]/td[9]")
	private WebElement barcodeQty;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtProdCode1']")
	private WebElement barcodefield;
	
	public void getBarcodeQty(String barcode)
	{
		try
		{
			webActionUtils.elementClick(searchTB);
			webActionUtils.enterData(barcodefield, barcode);
			webActionUtils.elementClick(searchTB);
			//String qty =  webActionUtils.getText(barcodeQty);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        String qty = (String) jsExecutor.executeScript("return arguments[0].textContent;", barcodeQty);
			Reporter.log("Barcode Qty is : " + qty, true);
		}
		catch (Exception e)
		{
			Reporter.log("No barcode as such", true);
		}
	}
	
}
