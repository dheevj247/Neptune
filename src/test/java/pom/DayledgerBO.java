package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import genericLibs.WebActionUtils;

public class DayledgerBO extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "My account - My Store";
	final static String pageUrl = "http://automationpractice.com/index.php?controller=my-account";
	

	public DayledgerBO(WebDriver driver, WebActionUtils webActionUtils)
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	@FindBy(xpath = "//input[@id='CrystalReportViewer1_toptoolbar_search_textField']")
	private WebElement orderSearchTB; 
	
	@FindBy(xpath = "//img[@id='IconImg_CrystalReportViewer1_toptoolbar_search_button']")
	private WebElement findBTN;
	
	
	public void search(String search)
	{
		try
		{
			webActionUtils.actionClick(orderSearchTB);
			webActionUtils.fill(orderSearchTB, search);
			webActionUtils.actionClick(findBTN);
		}
		catch (Exception e) 
		{
			Reporter.log("Unable to find order number", true);
		}
	}

	
}
