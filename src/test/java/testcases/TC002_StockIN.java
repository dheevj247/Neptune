package testcases;

import org.testng.annotations.Test;
import genericLibs.NewXLLib;
import genericLibs.PropertyFileLib;
import pom.HomePage;
import pom.StockINPage;

public class TC002_StockIN extends BaseTest
{
	HomePage hp;
	StockINPage stp;

	@Test(description = "ContactLens Entry", dataProviderClass = NewXLLib.class, dataProvider = DATA_PROVIDER_NAME)
	public void newStockContactLens(String CL_Category, String CL_Product, String CL_Qty, String CL_rate, 
			String CL_sprice, String CL_Model, String CL_Tint, String CL_Usage)
	{
		hp = new HomePage(driver, webActionUtils);
		stp = new StockINPage(driver, webActionUtils);
		
		/*------------------- Navigating to StockIn -------------------*/
		hp.selectFromList(HO_STOCKS, STOCKIN_MENU);
		
		/*------------------- I N F O -------------------*/
		stp.clickNewBTN();
		stp.selectSupplier(PropertyFileLib.getValue(CON_PROP_PATH, "Supplier"));
		
		/*------------------- D E T A I L S -------------------*/
		stp.selectCategory(CL_Category);
		stp.selectProduct(CL_Product);
		stp.enterQuantity(CL_Qty);
		stp.enterRate(CL_rate);
		stp.enterSPrice(CL_sprice);
		
		/*------------------- Contact Model details -------------------*/
		stp.selectCLModel(CL_Model);
		stp.selectCLTint(CL_Tint);
		stp.selectCLUsage(CL_Usage);
		stp.clickAdd();
		/*------------------- S A V E -------------------*/
		
		stp.save();
	}
}
