package testcases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.Snapshot;
import genericLibs.WebActionUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import pom.HomePage;
import pom.LoginPage;

public class BaseTest implements AutoContants
{
	public WebDriver driver;
	public WebActionUtils webActionUtils;
	LoginPage lp;
	HomePage hp;
	
	@BeforeSuite
	/*------------------------------- Browser selection -------------------------------*/
	public void environment()
	{
		if(PropertyFileLib.getValue(CON_PROP_PATH, "browser").equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Reporter.log("Chrome is being launched!", true);
		}
		else if(PropertyFileLib.getValue(CON_PROP_PATH, "browser").equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Reporter.log("Firefox is being launched!",true);
		}
		else
		{
			Assert.fail("sorry this browser is not supported");
		}
	}
	
	/*------------------- Browser Configuration -------------------*/
	@BeforeTest
	public void configure()
	{
		long implicit = Long.parseLong(ITO);
		Duration explicit = Duration.ofSeconds(Long.parseLong(ETO)); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
		webActionUtils = new WebActionUtils(driver, explicit);
		Reporter.log("Setup complete", true);
	}
	
	/*------------------- BNavigating to the application -------------------*/
	@BeforeClass
	public void setup()
	{
		driver.get(PropertyFileLib.getValue(CON_PROP_PATH,"testurlHO")); 
	}
	
	/*------------------------------- Login into the Application -------------------------------*/
	  @BeforeMethod public void loginToApp() 
	  { 
		  String usertype, username, password;
		  usertype = DEFAULT_HO_USERTYPE; 
		  username = DEFAULT_HO_USERNAME; 
		  password = DEFAULT_HO_PASSWORD; 
		  lp = new LoginPage(driver, webActionUtils);
		  lp.login(usertype,username, password); 
		  Reporter.log("Login success", true); 
	  }
	  
	 /*--------------- Control will be transferred to the test case or @Test ---------------*/
	  
	  /*------------------------------ Logout from App ------------------------------*/
	  @AfterMethod public void logoutFromApp(ITestResult result) 
	  { 
		  String testCaseName = result.getName(); 
		  if (ITestResult.FAILURE==result.getStatus())
		  { 
			  String imageFilePath = Snapshot.getSnapshot(driver, testCaseName);
			  Reporter.log(imageFilePath,true); 
		  } 
		  try 
		  { 
			  hp = new HomePage(driver,webActionUtils); 
			  hp.myHome(); 
			  hp.logoutApp();
			  Reporter.log("Logout Success!!", true); 
		  } 
		  catch (Exception e) 
		  {
				  Reporter.log("Logout Failed!!", true); 
		  }
		 
	  }
	 
	  /*------------------------------ close openned windows ------------------------------*/
	  @AfterClass
	  public void tearDown()
	  {
		  	try
		  	{
		  		
				Reporter.log("Teardown Success!!", true);
		  	}
		  	catch (Exception e) 
		  	{
				
			}
	  }
	
	  /*------------------------------ closing the connection ------------------------------*/
	  @AfterTest
	  public void shutDown()
	  {
		  try
		  {
			  Reporter.log("Shutdown Success!!", true);
		  }
		  catch(Exception e)
		  {
			  
		  }
	  }
	  
	  @AfterSuite
	  public void endServer()
	  {
		  try
		  {
			  Reporter.log("Suite Success!", true);
		  }
		  catch (Exception e) 
		  {
			  driver.close();
		  }
	  }
}