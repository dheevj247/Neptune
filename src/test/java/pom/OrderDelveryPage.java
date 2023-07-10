package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class OrderDelveryPage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	HomePageBO hp;
	PrmBOPage prm;
	JavascriptExecutor jse; 
	
	public OrderDelveryPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}

	/*-------------------------- C R U D --------------------------*/
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtOrderNo']")
	private WebElement ordernoTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSearch']")
	private WebElement searchBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnEdit']")
	private WebElement editBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_radDelivery']")
	private WebElement deliveryRadioBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUser']")
	private WebElement usernameTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	private WebElement passwordTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSave1']")
	private WebElement saveBTN;

	
	public void search(String orderno)
	{
		try
		{
			webActionUtils.elementClick(searchBTN);
			webActionUtils.enterData(ordernoTB, orderno);
			webActionUtils.elementClick(searchBTN);
			Reporter.log("Found orderno : " + orderno, true);
		}
		catch (Exception e)
		{
			Reporter.log("No barcode as such", true);
		}
	}
	
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
	
	public void orderDelivery()
	{
		try 
		{
			webActionUtils.elementClick(editBTN);
			this.scrollToBottom();
			webActionUtils.elementClick(deliveryRadioBTN);
			webActionUtils.enterData(usernameTB, AutoContants.BO_EMP_UN);
			webActionUtils.enterData(passwordTB, AutoContants.BO_EMP_PWD);
			webActionUtils.elementClick(saveBTN);
			Reporter.log("Successfully Delivered", true);
			String msg = webActionUtils.getAlert(driver);
			webActionUtils.acceptAlert(driver);
			Reporter.log("Click on " + msg + " popup", true);
			String orderno = webActionUtils.getAttribute(ordernoTB);
			Reporter.log("Order no : " + orderno, true);
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed To deliver", true);
		}
	}
}
