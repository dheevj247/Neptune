package pom;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class StockINPage extends BasePage
{
	/*------------------------- Page Verification -------------------------*/
	
	final static String pageTitle = "OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	HomePage hp;
	PrmHOPage prm;
	
	public StockINPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*-------------------------- Username & Password --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtEnteredBy']")
	private WebElement unBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtPassword']")
	private WebElement pwdBTN;
	
	
	/*-------------------------- C R U D --------------------------*/
	
	@FindBy(id="ctl00_ContentPlaceHolder1_btnNew")
	private WebElement newBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSave1']")
	private WebElement saveBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnAdd']")
	private WebElement addBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnModelOK']")
	private WebElement okBTN;
	
	@FindBy(xpath = "//table[@id='ctl00_ContentPlaceHolder1_gvwStocksDetails']//td[12]")
	private WebElement newBarcode;
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwStocksDetails']/tbody/tr/td[12]")
	private WebElement savedBarcode;
	
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
	
	public String save()
	{
		try
		{
			webActionUtils.enterData(unBTN,AutoContants.HO_EMP_UN);
			webActionUtils.enterData(pwdBTN,AutoContants.HO_EMP_PWD);
			webActionUtils.elementClick(saveBTN);
			Reporter.log("Click on save button successfully ", true);
			String msg = webActionUtils.getAlert(driver);
			webActionUtils.acceptAlert(driver);
			Reporter.log("Click on " + msg + " popup", true);
			String barcode = webActionUtils.getText(savedBarcode);
			Reporter.log("Barcode generated successfully", true);
			return barcode;
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click save button", true);
			Reporter.log("No popup appeared", true);
			Reporter.log("Failed to generated Barcode", true);
			return null;
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
			Reporter.log("Failed to generated Barcode", true);
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
			Reporter.log("Barcode generated successfully", true);
			return barcode;
			
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click Add Button", true);
			Reporter.log("Failed to generated Barcode", true);
			return null;
			
		}
	}
	
	
	/*-------------------------- G R I D --------------------------*/
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstSupplier']")
	private WebElement supplier;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtInvoiceNo']")
	private WebElement invoiceNo;
	
	@FindBy(xpath = "//textarea[@id='ctl00_ContentPlaceHolder1_txtRemarks']")
	private WebElement notes;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_imgPrintBracode']")
	private WebElement barcodeImg;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstCategory']")
	private WebElement categoryName;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstProduct']")
	private WebElement ProductName;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstModel']")
	private WebElement ModelName;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtQuantity']")
	private WebElement qty;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtUnitRate']")
	private WebElement rate;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtSPrice']")
	private WebElement sPrice;
	
	public void selectSupplier(String sup)
	{
		try
		{
			webActionUtils.selectByText(supplier, sup);
			Reporter.log("Supplier Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Supplier", true);
		}
	}
	
	public void selectCategory(String cat)
	{
		try
		{
			webActionUtils.selectByText(categoryName, cat);
			Reporter.log("Category Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Category", true);
		}
	}
	
	public void selectProduct(String prod)
	{
		try
		{
			webActionUtils.selectByText(ProductName, prod);
			Reporter.log("Product Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Product", true);
		}
	}
	
	public void selectModel(String mod)
	{
		try
		{
			webActionUtils.selectByText(ModelName, mod);
			Reporter.log("Model Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Model", true);
		}
	}
	
	public void enterQuantity(String quantity)
	{
		try
		{
			webActionUtils.enterData(qty, quantity);
			Reporter.log("Quantity Entered successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Entered Quantity", true);
		}
	}
	
	public void enterRate(String cp)
	{
		try
		{
			webActionUtils.enterData(rate, cp);
			Reporter.log("Cost Price Entered successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Entered Cost Price", true);
		}
	}
	
	public void enterSPrice(String sp)
	{
		try
		{
			webActionUtils.enterData(sPrice, sp);
			Reporter.log("Selling Price Entered successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Entered Selling Price", true);
		}
	}
	
	public void clickOK()
	{
		try
		{
			webActionUtils.elementClick(okBTN);
			Reporter.log("CLicked OK successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to click OK", true);
		}
	}
	
	
	/*-------------------------- G E N E R A L --------------------------*/
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstColor']/option")
	private List<WebElement> colors;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstProperty1']/option")
	private List<WebElement> materials;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstProperty2']/option")
	private List<WebElement> designs;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnModelOK']")
	private WebElement modelDetailsOKBTN;
	
	public void selectColor(String actualclr)
	{
		try
		{
			for (WebElement color : colors) 
			{
				if(color.getText().equals(actualclr))
				{
					webActionUtils.elementClick(color);
					Reporter.log("Color Selected successfully",true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Color", true);
		}
	}
	
	public void selectMaterial(String actualMtrl)
	{
		try
		{
			for (WebElement material : materials) 
			{
				if(material.getText().equals(actualMtrl))
				{
					webActionUtils.elementClick(material);
					Reporter.log("Material Selected successfully",true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Material", true);
		}
	}
	
	public void selectDesign(String actualDgn)
	{
		try
		{
			for (WebElement design : designs) 
			{
				if(design.getText().equals(actualDgn))
				{
					webActionUtils.elementClick(design);
					Reporter.log("Design Selected successfully",true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Design", true);
		}
	}
	
	/*-------------------------- C O N T A C T L E N S --------------------------*/
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstCLensModel']")
	private WebElement clModel;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstTint']")
	private WebElement Tint;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlstQuality']")
	private WebElement Usage;
	
	public void selectCLModel(String clMod)
	{
		try
		{
			webActionUtils.selectByText(clModel, clMod);
			Reporter.log("Contact Lens Model Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Contact Lens Model", true);
		}
	}
	
	public void selectCLTint(String clTint)
	{
		try
		{
			webActionUtils.selectByText(Tint, clTint);
			Reporter.log("Contact Lens Tint Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Contact Lens Tint", true);
		}
	}
	
	public void selectCLUsage(String clUsage)
	{
		try
		{
			webActionUtils.selectByText(Usage, clUsage);
			Reporter.log("Contact Lens Usage Selected successfully", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Contact Lens Usage", true);
		}
	}
	
	/*-------------------------- L E N S --------------------------*/
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstLType']/option")
	private List<WebElement> lensTypes;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstIndex']/option")
	private List<WebElement> lensIndexs;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstLMat']/option")
	private List<WebElement> lensMaterials;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstTint']/option")
	private List<WebElement> lensColors;
	
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_lstCoat']/option")
	private List<WebElement> lensCoats;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnLensAttriOK']")
	private WebElement lensDetailsOK;
	
	public void lensType(String lt)
	{
		try
		{
			for(WebElement lensType : lensTypes)
			{
				if(lensType.getText().equals(lt))
				{
					webActionUtils.elementClick(lensType);
					Reporter.log("Lens Type Selected successfully", true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Type", true);
		}
	}
	
	public void lensIndex(String li)
	{
		try
		{
			for(WebElement lensindex : lensIndexs)
			{
				if(lensindex.getText().equals(li))
				{
					webActionUtils.elementClick(lensindex);
					Reporter.log("Lens Index Selected successfully", true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Index", true);
		}
	}
	
	public void lensMaterial(String mt)
	{
		try
		{
			for(WebElement lensMaterial : lensMaterials)
			{
				if(lensMaterial.getText().equals(mt))
				{
					webActionUtils.elementClick(lensMaterial);
					Reporter.log("Lens Material Selected successfully", true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Material", true);
		}
	}
	
	public void lensColor(String clr)
	{
		try
		{
			for(WebElement lensColor : lensColors)
			{
				if(lensColor.getText().equals(clr))
				{
					webActionUtils.elementClick(lensColor);
					Reporter.log("Lens Color Selected successfully", true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Color", true);
		}
	}
	public void lensCoat(String co)
	{
		try
		{
			for(WebElement lensCoat : lensCoats)
			{
				if(lensCoat.getText().equals(co))
				{
					webActionUtils.elementClick(lensCoat);
					Reporter.log("Lens Coat Selected successfully", true);
					break;
				}
			}
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Coat", true);
		}
	}
	
	public void clickLensDetailsOK()
	{
		try
		{
			webActionUtils.elementClick(lensDetailsOK);
			Reporter.log("Successfully click ok on Lens Details", true);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Coat", true);
		}
	}
	
	/*----------------------- Lens Power -----------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtDVRSPH']")
	private WebElement powerTB;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnShowPower']")
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
	
	public void loadPower()
	{
		try
		{
			webActionUtils.elementClick(LoadBTN);
		}
		catch (Exception e) 
		{
			Reporter.log("Failed to Select Lens Power", true);
		}	
	}
	
	/*------------------------- Inventory Check ------------------------------------*/
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


