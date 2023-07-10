package testcasesBO;

import org.testng.annotations.Test;

import genericLibs.NewXLLib;
import genericLibs.PropertyFileLib;
import pom.HomePageBO;
import pom.TaxInvoicePage;

public class TC001_TaxInvoice extends BaseTest
{	
	HomePageBO hp;
	TaxInvoicePage tx;
	
	@Test(description = "Testing orderform", dataProviderClass = NewXLLib.class, dataProvider = DATA_PROVIDER_NAME)
	public void invoice(String Barcode)
	{	
		hp = new HomePageBO(driver, webActionUtils);
		tx = new TaxInvoicePage(driver, webActionUtils);
		
		/*------------------- Customer Details from Config File -------------------*/
		String mobile = PropertyFileLib.getValue(CON_PROP_PATH, "C_Mobile");
		String name = PropertyFileLib.getValue(CON_PROP_PATH, "C_Name");
		String payType = PropertyFileLib.getValue(CON_PROP_PATH, "C_PayType");	
		
		/*------------------- Navigating to Order Form -------------------*/
		hp.selectFromList(BILLS, CASH_BILL);
		
		/*------------------- Click New -------------------*/
		tx.clickNew();
		
		/*------------------- Fill customer Details -------------------*/
		tx.enterCustomerDetails(mobile, name);
		
		/*------------------- Scan Frame barcode -------------------*/
		tx.scanBarcode(Barcode);
		
		/*------------------- Advance Payment -------------------*/
		tx.pay(payType);
		
		/*------------------- Inventory Check Before Save -------------------*/
		tx.checkInventory(Barcode);
		tx.save();
		
		/*------------------- Inventory Check After Save -------------------*/
		tx.checkInventory(Barcode);
	}
}
