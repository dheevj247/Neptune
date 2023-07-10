package pom;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;


public class OrderFormPage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	HomePageBO hp;
	PrmBOPage prm;
	DayledgerBO dl;
	JavascriptExecutor jse;
	
	public OrderFormPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*------------------------- C R U D -------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnNew']")
	WebElement newBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSave1']")
	WebElement saveBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUser1']")
	WebElement usernameTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	WebElement passwordTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtOrderNo']")
	WebElement ordernoTB; 
	
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
			webActionUtils.enterData(usernameTB, AutoContants.BO_EMP_UN);
			webActionUtils.enterData(passwordTB, AutoContants.BO_EMP_PWD);
			webActionUtils.elementClick(saveBTN);
			Reporter.log("Click on save button successfully ", true);
			String msg = webActionUtils.getAlert(driver);
			webActionUtils.acceptAlert(driver);
			Reporter.log("Click on " + msg + " popup", true);
			String orderno = webActionUtils.getAttribute(ordernoTB);
			Reporter.log("Order no : " + ordernoTB, true);
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

	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtDDate']")
	WebElement delivery_Date;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstPayType']")
	WebElement payType; 
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtAdvance']")
	WebElement advanceAmount;
	
	@FindBy(xpath = "//td[@style='height: 19px' and @valign='top'][1]")
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
	
	public void scrollToTop()
	{
		try
		{
			jse =(JavascriptExecutor) driver;
			jse.executeScript("window.scrollTo(0, 0);");
			Reporter.log("Scrolled to the Top", true);
		}
		catch (Exception e) {
			Reporter.log("Unable to scroll to the Top", true);
		}
	}
	
	public void enterCustomerDetails(String mobileNum, String custName, String dd)
	{
		try
		{
			webActionUtils.enterData(mobile, mobileNum);
			Reporter.log("Mobile number entered successfully", true);
			webActionUtils.elementClick(loadClick);
			webActionUtils.enterData(name, custName);
			Reporter.log("Customer Name entered successfully", true);
			webActionUtils.enterData(delivery_Date, dd);
			Reporter.log("Delivery Date entered successfully", true);
			webActionUtils.elementClick(loadClick);
		}
		catch (Exception e)
		{
			Reporter.log("Failed to enter Customer Details", true);
		}
	}
	
	public void pay(String advance, String type)
	{
		webActionUtils.enterData(advanceAmount, advance);
		Reporter.log("Advance Entered", true);
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
			webActionUtils.elementClick(loadClickbottom);
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
	
	/*----------------------------- Lens Details -----------------------------*/
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstCategory']")
	private WebElement categoryName;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstProduct']")
	private WebElement ProductName;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstLType']/option")
	private List<WebElement> lensTypes;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstLMat']/option")
	private List<WebElement> lensMaterials;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstIndex']/option")
	private List<WebElement> lensIndexs;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstCoating']/option")
	private List<WebElement> lensCoats;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnLensOK1']")
	private WebElement lensDetailsOK;

	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtRate']")
	private WebElement lensRate;
	
	public void selectCategory(String category)
	{
		try
		{
			webActionUtils.selectByText(categoryName, category);
			Reporter.log("Category Selected", true);
		}
		catch(Exception e)
		{
			Reporter.log("Failed to select Categoty", true);
		}
	}
	
	public void selectProduct(String prod)
	{
		try
		{
			webActionUtils.selectByText(ProductName, prod);
			Reporter.log("Product Selected", true);
		}
		catch(Exception e)
		{
			Reporter.log("Failed to select Product", true);
		}
	}
	
	public void selectType(String type)
	{
		try
		{
			for(WebElement lensType : lensTypes)
			{
				if(lensType.getText().equals(type))
				{
					webActionUtils.elementClick(lensType);
					Reporter.log("Selected Lens Type", true);
					break;
				}
			}
		}
		catch(Exception e)
		{
			Reporter.log("Failed to select Lens Type", true);
		}
	}
	
	public void selectMaterial(String material)
	{
		try
		{
			for(WebElement lensmaterial : lensMaterials)
			{
				if(lensmaterial.getText().equals(material))
				{
					webActionUtils.elementClick(lensmaterial);
					Reporter.log("Selected lens Material", true);
					break;
				}
			}
		}
		catch(Exception e)
		{
			Reporter.log("Failed to select lens Material", true);
		}
	}
	
	public void selectIndex(String index)
	{
		try
		{
			for(WebElement lensIndex : lensIndexs)
			{
				if(lensIndex.getText().equals(index))
				{
					webActionUtils.elementClick(lensIndex);
					Reporter.log("Selected lens index", true);
					break;
				}
			}
		}
		catch(Exception e)
		{
			Reporter.log("Failed to select lens index", true);
		}
	}
	
	public void selectCoating(String coating)
	{
		try
		{
			for(WebElement lensCoating : lensCoats)
			{
				if(lensCoating.getText().equals(coating))
				{
					webActionUtils.elementClick(lensCoating);
					Reporter.log("Selected lens coating", true);
					break;
				}
			}
		}
		catch(Exception e)
		{
			Reporter.log("Failed to select lens coating", true);
			Reporter.log("clicked Ok", true);
		}
	}
	
	public void clickOk()
	{
		try 
		{
			webActionUtils.elementClick(lensDetailsOK);
		} 
		catch (Exception e) 
		{
			Reporter.log("Failed to click Ok", true);
		}
	}
	
	public void enterRate(String rate)
	{
		try
		{
			webActionUtils.enterData(lensRate, rate);
			Reporter.log("Quantity Entered", true);
		}
		catch(Exception e)
		{
			Reporter.log("Failed to Enter Quantity", true);
		}
	}
	
	/*----------------------- Lens Power -----------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtRDVSPH']")
	private WebElement powerTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnLoadPower1']")
	private WebElement LoadBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btn33']")
	private WebElement lensPowerOkBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtDVRSPH']")
	private WebElement lensSPH;	
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtDVRCYL']")
	private WebElement lensCYL;	
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtDVRAxis']")
	private WebElement lensAXIS;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtADDR']")
	private WebElement lensADD;
	
	public void selectPower(String SPH, String CYL, String AXIS, String ADD)
	{
		try
		{
			String parentWindow = driver.getWindowHandle();
			webActionUtils.elementClick(powerTB);
			String expectedTitle = "Lens Power Specification";
			Set<String> windowids=driver.getWindowHandles();
			for(String windowId:windowids) 
			{
				driver.switchTo().window(windowId);
				String actualTitle=driver.getTitle();
				if(actualTitle.contains(expectedTitle)) 
				{
					break;
				}
			}
			webActionUtils.enterData(lensSPH, SPH);
			webActionUtils.enterData(lensCYL, CYL);
			webActionUtils.enterData(lensAXIS, AXIS);
			webActionUtils.enterData(lensADD, ADD);
			webActionUtils.elementClick(lensPowerOkBTN);
			webActionUtils.elementClick(lensPowerOkBTN);
			Reporter.log("Successfully Selected Lens Power", true);
			driver.switchTo().window(parentWindow);
			webActionUtils.elementClick(LoadBTN);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Power", true);
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
	
	/**/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_lbtnDayledger']")
	private WebElement dlBTN; 
	
	public void downLoadDayledger()
	{
		webActionUtils.elementClick(dlBTN);
		
	}
}
