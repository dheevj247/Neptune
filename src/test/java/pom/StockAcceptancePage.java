package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class StockAcceptancePage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	HomePageBO hp;
	PrmBOPage prm;
	
	public StockAcceptancePage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}

	/*-------------------------- C R U D --------------------------*/
	
	@FindBy(xpath = "//a[@id='ctl00_ContentPlaceHolder1_lnkbtnShowAll']")
	private WebElement showAll;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnEdit']")
	private WebElement editBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_chkRecevied']")
	private WebElement accept;
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwStockDetails']//tr[2]/td[3]")
	private WebElement Barcode;
	
	public String acceptStock()
	{
		try 
		{
			//webActionUtils.elementClick(showAll);
			//Reporter.log("clicked on ShowALl",true);
			webActionUtils.elementClick(editBTN);
			Reporter.log("clicked on Edit",true);
			webActionUtils.elementClick(accept);
			Reporter.log("Acceptance checkbox selected",true);
			String barcode = webActionUtils.getText(Barcode);
			Reporter.log("Barcode Returnned",true);
			return barcode;
		} 
		catch (Exception e) 
		{	
			Reporter.log("Operation Failed",true);
			return "";
		}
	}
	
	/*-------------------------- Inventory Validation-------------------------- */
	
/*------------------------- Inventory Check ------------------------------------*/
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwStocksDetails']/tbody/tr")
	private List<WebElement> gridDetails;
	
	public void checkInventory(String barcode)
	{
		try
		{
			String parentWindow = driver.getWindowHandle();
			hp = new HomePageBO(driver, webActionUtils);
			prm = new PrmBOPage(driver, webActionUtils);
			hp.contextSelectFromList(AutoContants.BO_STOCKS, AutoContants.PRM_BO, AutoContants.PRM_BO_Gen);
			prm.getBarcodeQty(barcode);
			driver.close();
			driver.switchTo().window(parentWindow);
		}
		catch (Exception e) 
		{
			Reporter.log("operation failed", true);
		}
	}
	
	public void Inventory(String barcode)
	{
		try
		{
			hp = new HomePageBO(driver, webActionUtils);
			prm = new PrmBOPage(driver, webActionUtils);
			hp.selectFromList(AutoContants.BO_STOCKS, AutoContants.PRM_BO, AutoContants.PRM_BO_Gen);
			prm.getBarcodeQty(barcode);
		}
		catch (Exception e) 
		{
			Reporter.log("operation failed", true);
		}
	}
	
	/*-------------------------- Save : Username & Password --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUser']")
	private WebElement unBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	private WebElement pwdBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSave1']")
	private WebElement saveBTN;
	
	public String save()
	{
		try
		{
			webActionUtils.enterData(unBTN,AutoContants.BO_EMP_UN);
			//Utility.sleepInSeconds(3);
			webActionUtils.enterData(pwdBTN,AutoContants.BO_EMP_PWD);
			//Utility.sleepInSeconds(3);
			webActionUtils.elementClick(saveBTN);
			//Utility.sleepInSeconds(3);
			Reporter.log("Clicked on save button successfully ", true);
			String msg = webActionUtils.getAlert(driver);
			webActionUtils.acceptAlert(driver);
			Reporter.log("Clicked on " + msg + " popup", true);
			String bcode = webActionUtils.getText(Barcode);
			Reporter.log("Barcode transfered successfully", true);
			return bcode;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click save button", true);
			Reporter.log("No popup appeared", true);
			Reporter.log("Failed to transfer Barcode", true);
			return null;
		}
	}

}
