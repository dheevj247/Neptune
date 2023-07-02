package testcases;

import org.testng.annotations.Test;	
import genericLibs.NewXLLib;
import genericLibs.PropertyFileLib;
import pom.HomePage;
import pom.PrmHOPage;
import pom.StockINPage;

public class TC001_StockIN extends BaseTest
{
	HomePage hp;
	StockINPage stp;
	PrmHOPage prm;
	
	@Test(description = "Test New General Stock Entry", dataProviderClass = NewXLLib.class, dataProvider = DATA_PROVIDER_NAME)
	public void newStockGeneral(String Category, String Product, String Model, String Color,
			String Material, String design, String Qty, String rate, String sprice)
	{
		hp = new HomePage(driver, webActionUtils);
		stp = new StockINPage(driver, webActionUtils);
		prm = new PrmHOPage(driver, webActionUtils);
		
		/*------------------- Navigating to StockIn -------------------*/
		
		hp.selectFromList(HO_STOCKS, STOCKIN_MENU);
		
		/*------------------- I N F O -------------------*/
		stp.clickNewBTN();
		stp.selectSupplier(PropertyFileLib.getValue(CON_PROP_PATH, "Supplier"));
		
		/*------------------- D E T A I L S -------------------*/
		stp.selectCategory(Category);
		stp.selectProduct(Product);
		stp.selectModel(Model);
		stp.selectColor(Color);
		stp.selectMaterial(Material);
		stp.selectDesign(design);
		stp.clickOK();
		stp.enterQuantity(Qty);
		stp.enterRate(rate);
		stp.enterSPrice(sprice);
		String barcode = stp.clickAddBTN();
		
		/*------------------- Inventory Validation before Save------------------- */

		stp.checkInventory(barcode);
		
		/*------------------- S A V E -------------------*/
		String savedBarcode = stp.save();
		
		/*------------------- Inventory Validation before Save------------------- */

		stp.Inventory(savedBarcode);
		
	}
}
