package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class TaxInvoicePage extends BasePage
{
/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	HomePageBO hp;
	PrmBOPage prm;
	DayledgerBO dl;
	JavascriptExecutor jse;
	
	public TaxInvoicePage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*------------------------- C R U D -------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnNew']")
	WebElement newBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSave1']")
	WebElement saveBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUser']")
	WebElement usernameTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	WebElement passwordTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtBillNo']")
	WebElement csalenoTB; 
	
	public void clickNew()
	{
		try
		{
			webActionUtils.elementClick(newBTN);
			Reporter.log("New Button clicked successfully");
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click on new button");
		}
	}
	
	public String save()
	{
		try
		{
			this.scrollToBottom();
			webActionUtils.enterData(usernameTB, AutoContants.BO_EMP_UN);
			webActionUtils.enterData(passwordTB, AutoContants.BO_EMP_PWD);
			webActionUtils.elementClick(saveBTN);
			Reporter.log("Click on save button successfully ", true);
			String msg = webActionUtils.getAlert(driver);
			webActionUtils.acceptAlert(driver);
			Reporter.log("Click on " + msg + " popup", true);
			String orderno = webActionUtils.getAttribute(csalenoTB);
			Reporter.log("Csale no : " + csalenoTB, true);
			return orderno;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed To Save",true);
			return "";
		}
	}
	
	/*------------------------- I N F O -------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtMobile']")
	WebElement mobile; 
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtCustName']")
	WebElement name; 
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstPayType']")
	WebElement payType; 
	
	@FindBy(xpath = "//span[@id='ctl00_ContentPlaceHolder1_Label6']")
	WebElement loadClick; 
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_UpdatePanel2']/table[2]")
	WebElement loadClickbottom; 
	
	public void scrollToBottom()
	{
		try
		{
			jse =(JavascriptExecutor) driver;
			jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			Reporter.log("Scrolled to the bottom", true);
		}
		catch (Exception e) {
			Reporter.log("Unable to scroll to the bottom", true);
		}
	}
	
	public void enterCustomerDetails(String mobileNum, String custName)
	{
		try
		{
			webActionUtils.enterData(mobile, mobileNum);
			Reporter.log("Mobile number entered successfully", true);
			webActionUtils.elementClick(loadClick);
			webActionUtils.enterData(name, custName);
			Reporter.log("Customer Name entered successfully", true);
		}
		catch (Exception e)
		{
			Reporter.log("Failed to enter Customer Details", true);
		}
	}
	
	public void pay(String type)
	{
		webActionUtils.selectByText(payType, type);
		Reporter.log("Selected : " + type, true);
	}
	
	/*------------------------------ D E T A I L S ------------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtProdCode']")
	WebElement barcodeTB; 

	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnAdd']")
	WebElement addBTN;
	
	public void scanBarcode(String barcode)
	{
		try
		{
			webActionUtils.elementClick(barcodeTB);
			webActionUtils.fill(barcodeTB, barcode);
			webActionUtils.elementClick(loadClick);
			webActionUtils.elementClick(addBTN);
			Reporter.log("Barcode scanned successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to scan barcode", true);
		}
	}
	
	public void clickAdd()
	{
		try
		{
			webActionUtils.elementClick(addBTN);
			Reporter.log("Add Button clicked successfully");
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click on Add button");
		}
	}
	
	/* Inventory Verification */
	
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
}
