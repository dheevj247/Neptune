package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import genericLibs.AutoContants;
import genericLibs.PropertyFileLib;
import genericLibs.WebActionUtils;

public class LoginPageBO extends BasePage
{
	final static String pageTitle = " 	OptimNet 4.4";
	final static String pageUrl = PropertyFileLib.readPropertyValue(AutoContants.CON_PROP_PATH,"testurlHO");
	
	public LoginPageBO(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	@FindBy(xpath = "//select[@id='ddlstBranch']")
	private WebElement branchName;
	
	@FindBy(id = "txtUsername")
	private WebElement usernameTB;
	
	@FindBy(id = "txtpassword")
	private WebElement passwordTB;
	
	@FindBy(id = "btnClearLogin")
	private WebElement resetBTN;
	
	@FindBy(id = "btnLogin")
	private WebElement loginBTN;
	
	@FindBy(id="lblError")
	private WebElement loginError;
	
	public void setUserType(String branch)
	{
		webActionUtils.selectByText(branchName, branch);
	}
	
	public void setUsername(String username)
	{
		webActionUtils.enterData(usernameTB, username);
	}
	
	public void setPassword(String password)
	{
		webActionUtils.enterData(passwordTB, password);
	}
	
	public void clickReset()
	{
		webActionUtils.elementClick(resetBTN);
	}
	
	public void clickLogin()
	{
		webActionUtils.elementClick(loginBTN);
	}
	
	public WebElement errorMsg()
	{
		return loginError;
	}
	
	public void login(String branch, String username, String password)
	{
		webActionUtils.selectByText(branchName, branch);
		webActionUtils.enterData(usernameTB, username);
		webActionUtils.enterData(passwordTB, password);
		webActionUtils.elementClick(resetBTN);
		webActionUtils.acceptAlert(driver);
		webActionUtils.enterData(passwordTB, password);
		webActionUtils.elementClick(loginBTN);
	}	
}

