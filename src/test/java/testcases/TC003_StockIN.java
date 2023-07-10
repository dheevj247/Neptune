package testcases;

import org.testng.annotations.Test;
import genericLibs.NewXLLib;
import genericLibs.PropertyFileLib;
import pom.HomePage;
import pom.StockINPage;

public class TC003_StockIN extends BaseTest
{
	HomePage hp;
	StockINPage si;

	@Test(description = "Lens Entry", dataProviderClass = NewXLLib.class, dataProvider = DATA_PROVIDER_NAME)
	public void newLensStock(String Category, String Product, String type, String index, 
			String material, String coat, String color, String qty, String rate, String sprice, 
			String SPH, String CYL, String AXIS, String ADD)
	{
		hp = new HomePage(driver, webActionUtils);
		si = new StockINPage(driver, webActionUtils);
		
		/*------------------- Navigating to StockIn -------------------*/
		
		hp.selectFromList(HO_STOCKS, STOCKIN_MENU);
		
		/*------------------- I N F O -------------------*/
		si.clickNewBTN();
		si.selectSupplier(PropertyFileLib.getValue(CON_PROP_PATH, "Supplier"));
		
		/*------------------- D E T A I L S -------------------*/
		si.selectCategory(Category);
		si.selectProduct(Product);
		si.enterQuantity(qty);
		si.enterRate(rate);
		si.enterSPrice(sprice);
		
		/*------------------- Lens Model details -------------------*/
		si.lensType(type);
		si.lensIndex(index);
		si.lensMaterial(material);
		si.lensCoat(coat);
		si.lensColor(color);
		si.clickLensDetailsOK();
		
		/*------------------- Lens Model details -------------------*/
		si.selectPower(SPH, CYL, AXIS, ADD);
		//si.loadPower();
		si.clickAdd();
		/*------------------- S A V E -------------------*/
		si.save();
	}		
}
