package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;


public class StockTransferPage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	HomePage hp;
	PrmHOPage prm;
	
	public StockTransferPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*-------------------------- Username & Password --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUser']")
	private WebElement unBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	private WebElement pwdBTN;
	
	/*-------------------------- C R U D --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnNew']")
	private WebElement newBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnEdit']")
	private WebElement editBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSave1']")
	private WebElement saveBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnAdd']")
	private WebElement addBTN;
	
	@FindBy(xpath = "//table[@id='ctl00_ContentPlaceHolder1_gvwStocksDetails']//td[10]")
	private WebElement newBarcode;
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwStocksDetails']/tbody/tr/td[10]")
	private WebElement savedBarcode;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_radTransfer']")
	private WebElement transferredBTN;
	

	public void clickNewBTN()
	{
		try
		{
			webActionUtils.elementClick(newBTN);
			Reporter.log("New Button clicked successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click New Button", true);
		}
	}
	
	public void editTransfer()
	{
		try
		{
			webActionUtils.elementClick(editBTN);
			Reporter.log("Edit Button clicked successfully", true);
			webActionUtils.elementClick(transferredBTN);
			Reporter.log("Transferred Button clicked successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click Edit Button", true);
			Reporter.log("Failed to click Transfer Button", true);
		}
	}
	
	public String clickAddBTN()
	{
		try
		{
			webActionUtils.elementClick(addBTN);
			webActionUtils.elementClick(addBTN);
			Reporter.log("Add Button clicked successfully", true);
			String barcode = webActionUtils.getText(newBarcode);
			Reporter.log("Barcode generated successfully", true);
			return barcode;
			
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click Add Button", true);
			Reporter.log("Failed to Add Barcode to the Grid", true);
			return null;
		}
	}
	
	public String clickAdd()
	{
		try
		{
			webActionUtils.elementClick(addBTN);
			Reporter.log("Add Button clicked successfully", true);
			String barcode = webActionUtils.getText(newBarcode);
			Reporter.log("Barcode Added successfully", true);
			return barcode;
			
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click Add Button", true);
			Reporter.log("Failed to Add Barcode to the Grid", true);
			return null;
			
		}
	}
	
	public String save()
	{
		try
		{
			webActionUtils.enterData(unBTN,AutoContants.HO_EMP_UN);
			webActionUtils.enterData(pwdBTN,AutoContants.HO_EMP_PWD);
			webActionUtils.elementClick(saveBTN);
			Reporter.log("Clicked on save button successfully ", true);
			String msg = webActionUtils.getAlert(driver);
			webActionUtils.acceptAlert(driver);
			Reporter.log("Clicked on " + msg + " popup", true);
			String barcode = webActionUtils.getText(savedBarcode);
			Reporter.log("Barcode transfered successfully", true);
			return barcode;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click save button", true);
			Reporter.log("No popup appeared", true);
			Reporter.log("Failed to transfer Barcode", true);
			return null;
		}
	}
	
	/*-------------------------- I N F O --------------------------*/
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstBranch']")
	private WebElement branchName;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtProdCode']")
	private WebElement scanBarcode;
	
	/* for clicking outside after scanning the barcode to wait for the page refresh */
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_UpdatePanel2']/table/tbody/tr[1]/td[3]")
	private WebElement empty;
	
	
	
	public void selectBranch(String branch)
	{
		try
		{
			webActionUtils.selectByText(branchName, branch);
			Reporter.log("Branch Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Branch", true);
		}
	}
	
	public void scanMe(String barcode)
	{
		try
		{
			webActionUtils.enterData(scanBarcode, barcode);
			Reporter.log("Barcode scanned successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to scan barcode", true);
		}
	}
	
	/*-------------------------- G R I D --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtQty']")
	private WebElement qty;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_chkLoadQty']")
	private WebElement qtyCheckBox;
	
	
	public void enterQty(String value)
	{
		try
		{
			webActionUtils.elementClick(qtyCheckBox);
			webActionUtils.enterDataJS(qty, value);
			Reporter.log("Entered Qty successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed Entered Qty", true);
		}
	}
	
	/*------------------------- Inventory Check ------------------------------------*/
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwStocksDetails']/tbody/tr")
	private List<WebElement> gridDetails;
	
	public void checkInventory(String barcode)
	{
		try
		{
			String parentWindow = driver.getWindowHandle();
			hp = new HomePage(driver, webActionUtils);
			prm = new PrmHOPage(driver, webActionUtils);
			hp.contextSelectFromList(AutoContants.HO_STOCKS, AutoContants.PRM, AutoContants.PRM_HO);
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
			hp = new HomePage(driver, webActionUtils);
			prm = new PrmHOPage(driver, webActionUtils);
			hp.selectFromList(AutoContants.HO_STOCKS, AutoContants.PRM, AutoContants.PRM_HO);
			prm.getBarcodeQty(barcode);
		}
		catch (Exception e) 
		{
			Reporter.log("operation failed", true);
		}
	}
	
	
}
