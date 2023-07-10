package testcasesBO;

import org.testng.annotations.Test;
import pom.HomePageBO;
import pom.PrmBOPage;
import pom.StockAcceptancePage;

public class TC001_StockAcceptance extends BaseTest
{
	HomePageBO hmb;
	StockAcceptancePage sa;
	PrmBOPage prm;
	
	@Test(description = "Test Acceptance")
	public void acceptStock()
	{
		hmb = new HomePageBO(driver, webActionUtils);
		sa = new StockAcceptancePage(driver, webActionUtils);
		prm = new PrmBOPage(driver, webActionUtils);
		
		/*------------------- Navigating to StockIn -------------------*/
		
		hmb.gotoStockAcceptance();
		String newProdcode = sa.acceptStock();
		sa.checkInventory(newProdcode);
		String saveProdcode = sa.save();
		sa.Inventory(saveProdcode);
	}
}
