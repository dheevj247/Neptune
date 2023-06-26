package genericLibs;

public interface AutoContants 
{
	String DEFAULT_BROWSER = "chrome";
	String DEFAULT_HO_URL = "http://optimnet-tech.com/testho/(S(gin21lhfoo0qnrt12ngpd2s1))/HeadOffice.aspx";
	String DEFAULT_HO_USERTYPE = "SuperAdmin";
	String DEFAULT_HO_USERNAME = "admin";
	String DEFAULT_HO_PASSWORD = "test";
	String HO_EMP_UN = "empho";
	String HO_EMP_PWD = "test";
	
	String XL_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\SmokeTestData.xlsx";
	String CON_PROP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\configFile\\config.properties";
	String IMAGE_PATH = System.getProperty("user.dir")+"\\errorShots";
	
	String ITO = "5";
	String ETO = "5";	
	
	String DATA_PROVIDER_NAME = "smokeTest";
	
	/*-------------------- Head Office Navigation Bar ---------------------*/
	String HO_MASTERS = "Masters";
	String HO_STOCKS = "Stocks";

	/*-------------------- Head Office Menu ---------------------*/
	
	String STOCKIN_MENU = "StockIn/Purchase Details";
	String STOCK_TRANSFER_MENU = "Stock Transfer";
	
/*-------------------- Head Office Sub - Menu ---------------------*/
	
	String ST_NORMAL_MODE = "Stock Transfer Normal Mode";
}

