package testcases;

import org.testng.annotations.Test;
import genericLibs.NewXLLib;
import genericLibs.PropertyFileLib;
import pom.HomePage;
import pom.PrmHOPage;
import pom.StockTransferPage;

public class TC001_StockTransfer extends BaseTest
{
	HomePage hp;
	StockTransferPage stp;
	PrmHOPage prm;
	
	@Test(description = "Test New Stock Transfer", dataProviderClass = NewXLLib.class, dataProvider = DATA_PROVIDER_NAME)
	public void TransferStock(String Barcode, String Qty)
	{
		hp = new HomePage(driver, webActionUtils);
		stp = new StockTransferPage(driver, webActionUtils);
		prm = new PrmHOPage(driver, webActionUtils);
		
		/*------------------- Navigating to StockIn -------------------*/
		
		hp.selectFromList(HO_STOCKS, STOCK_TRANSFER_MENU,ST_NORMAL_MODE);
		
		/*------------------- I N F O -------------------*/
		stp.clickNewBTN();
		stp.selectBranch(PropertyFileLib.getValue(CON_PROP_PATH, "Branch_Name"));
		
		/*------------------- D E T A I L S -------------------*/
		stp.scanMe(Barcode);
		stp.enterQty(Qty);
		String barcode = stp.clickAdd();
		
		/*------------------- Inventory Validation before Save------------------- */

		stp.checkInventory(barcode);
		
		/*------------------- S A V E -------------------*/
		String savedBarcode = stp.save();
		
		/*------------------- Inventory Validation before Save------------------- */

		stp.Inventory(savedBarcode);
		
		/* Transferring Barcode */
		
		hp.selectFromList(HO_STOCKS, STOCK_TRANSFER_MENU,ST_NORMAL_MODE);
		stp.editTransfer();
		stp.save();
	}
}
