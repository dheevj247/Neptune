package genericLibs;

public interface AutoContants 
{
	String DEFAULT_BROWSER = "chrome";
	
	String XL_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\SmokeTestData.xlsx";
	String CON_PROP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\configFile\\config.properties";
	String IMAGE_PATH = System.getProperty("user.dir")+"\\errorShots";
	String ITO = "5";
	String ETO = "5";	
	String DATA_PROVIDER_NAME = "smokeTest";
	
	/*-------------------- Head Office* --------------------*/
	
	String DEFAULT_HO_URL = "http://optimnet-tech.com/qaho";
	String DEFAULT_HO_USERTYPE = "SuperAdmin";
	String DEFAULT_HO_USERNAME = "admin";
	String DEFAULT_HO_PASSWORD = "test";
	String HO_EMP_UN = "empho";
	String HO_EMP_PWD = "test";
		
	String XL_ST_SheetName = "stockTransfer";
	
	/*-------------------- Head Office Navigation Bar ---------------------*/
	String HO_MASTERS = "Masters";
	String HO_STOCKS = "Stocks";

	/*-------------------- Head Office Menu ---------------------*/
	
	String STOCKIN_MENU = "StockIn/Purchase Details";
	String STOCK_TRANSFER_MENU = "Stock Transfer";
	String PRM = "Product Registry Manager";
	
	/*-------------------- Head Office Sub - Menu ---------------------*/
	
	String ST_NORMAL_MODE = "Stock Transfer Normal Mode";
	String PRM_HO = "Product Registry - HO";
	
	
	/*-------------------- Branch Office --------------------*/
	
	String DEFAULT_BO_URL = "http://optimnet-tech.com/qabo";
	String DEFAULT_Branch = "Srikiran Opticals, City Center";
	String DEFAULT_BO_USERNAME = "bo2";
	String DEFAULT_BO_PASSWORD = "test";
	String BO_EMP_UN = "empbo";
	String BO_EMP_PWD = "test";
	
	/*-------------------- Branch Office Navigation Bar ---------------------*/
	
	String BO_STOCKS = "Stocks";
	String ORDERS = "Orders";
	String BILLS = "Bills";
	
	/*-------------------- Branch Office Menu ---------------------*/
	
	String PRM_BO = "Product Registry";
	String ORDER_FORM = "Order Form";
	String ORDER_Delivery = "Order Delivery";
	String CASH_BILL = "Cash Bill";
	
	/*-------------------- Branch Office Sub - Menu ---------------------*/
	
	String PRM_BO_Gen = "Product Registry Manager";
}

