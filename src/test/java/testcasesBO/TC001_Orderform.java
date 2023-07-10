package testcasesBO;

import org.testng.annotations.Test;
import genericLibs.NewXLLib;
import genericLibs.PropertyFileLib;
import pom.HomePageBO;
import pom.OrderDelveryPage;
import pom.OrderFormPage;

public class TC001_Orderform extends BaseTest
{	
	HomePageBO hp;
	OrderFormPage of;
	OrderDelveryPage od;
	
	@Test(description = "Testing orderform", dataProviderClass = NewXLLib.class, dataProvider = DATA_PROVIDER_NAME)
	public void order(String Barcode, String Category, String Product, String Type, String Material, 
			String Index, String Coating, String Rate, String SPH, String CYL, String AXIS, String ADD)
	{	
		hp = new HomePageBO(driver, webActionUtils);
		of = new OrderFormPage(driver, webActionUtils);
		od = new OrderDelveryPage(driver, webActionUtils);
		
		/*------------------- Customer Details from Config File -------------------*/
		String mobile = PropertyFileLib.getValue(CON_PROP_PATH, "O_Mobile");
		String name = PropertyFileLib.getValue(CON_PROP_PATH, "O_Name");
		String delivery = PropertyFileLib.getValue(CON_PROP_PATH, "O_Delivery_Date");
		String payType = PropertyFileLib.getValue(CON_PROP_PATH, "O_PayType");	
		String advanceAMT = PropertyFileLib.getValue(CON_PROP_PATH, "O_Amount");
		
		/*------------------- Navigating to Order Form -------------------*/
		hp.selectFromList(ORDERS, ORDER_FORM);
		
		/*------------------- Click New -------------------*/
		of.clickNew();
		
		/*------------------- Fill customer Details -------------------*/
		of.enterCustomerDetails(mobile, name, delivery);
		
		/*------------------- Scan Frame barcode -------------------*/
		of.scanBarcode(Barcode);
		
		/*------------------- Lens Entry -------------------*/
		of.scrollToBottom();
		of.selectCategory(Category);
		of.selectProduct(Product);
		of.selectType(Type);
		of.selectMaterial(Material);
		of.selectIndex(Index);
		of.selectCoating(Coating);
		of.clickOk();
		of.enterRate(Rate);
		
		/*------------------- Lens Power -------------------*/
		of.selectPower(SPH, CYL, AXIS, ADD);
		of.clickAdd();
		
		/*------------------- Advance Payment -------------------*/
		of.pay(advanceAMT, payType);
		
		/*------------------- Inventory Check Before Save -------------------*/
		of.checkInventory(Barcode);
		String orderno = of.save();
		
		/*------------------- Inventory Check After Save -------------------*/
		of.checkInventory(Barcode);
		
		/*------------------- ORDER DELIVERY -------------------*/
		hp.selectFromList(ORDERS, ORDER_Delivery);
		
		/*------------------- Search an Order -------------------*/
		od.search(orderno);
		od.orderDelivery();
	}
}
