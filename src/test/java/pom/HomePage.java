package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import genericLibs.WebActionUtils;

public class HomePage extends BasePage
{
	final static String pageTitle = "My account - My Store";
	final static String pageUrl = "http://automationpractice.com/index.php?controller=my-account";
	
	@FindBy(id="ctl00_btnLogout1")
	private WebElement logoutBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ibtn_home']")
	private WebElement homeBTN;
	
	public void logoutApp()
	{
		webActionUtils.elementClick(logoutBTN);
		webActionUtils.acceptAlert(driver);
	}
	
	public void myHome()
	{
		webActionUtils.elementClick(homeBTN);
	}
	
	/* navigationBar, Menu & Sub Menu */ 
	
	@FindBy(xpath = "//ul[@id='nav1']/li")
	private List<WebElement> navigationBar;
	
	@FindBy(xpath = "//ul[@id='nav1']/li/div/div/ul/li")
	private List<WebElement> menuList;
	
	@FindBy(xpath = "//ul[@id='nav1']/li/div/div/ul/li/div/div/ul/li/a")
	private List<WebElement> childMenu;

	public void selectFromList(String navigation, String menuName)
	{
		try 
		{
			for (WebElement targetNavigation : navigationBar) 
			{
				if(targetNavigation.getText().equals(navigation))
				{
					webActionUtils.moveToElement(targetNavigation);
					for (WebElement menu : menuList) 
					{
						if(menu.getText().contains(menuName))
						{
							webActionUtils.elementClick(menu);
							Reporter.log("successfully clicked " + menuName, true);
							break;
						}
					}
				}
			}
		} catch (Exception e) 
		{
			//Reporter.log("Operation FAILED!",true);
		}
	}
	/*
	public void selectFromList(List<WebElement> targetNavigations, List<WebElement> targetMenus, 
			List<WebElement> targetSubMenus, String navigation, String menu, String subMenu)
	{
		for (WebElement targetNavigation : targetNavigations) 
		{
			if(targetNavigation.getText().equals(navigation))
			{
				webActionUtils.moveToElement(targetNavigation);
				for (WebElement targetMenu : targetMenus) 
				{
					if(targetMenu.getText().equals(menu))
					{
						webActionUtils.moveToElement(targetMenu);
						for (WebElement targetSubMenu : targetSubMenus) 
						{
							webActionUtils.elementClick(targetSubMenu);
						}
					}
				}
			}
		}
	}
	*/
	/*----------------------------------*/
	
	public boolean logoutIsDisplayed()
	{
		return logoutBTN.isDisplayed();
	}
	
	public HomePage(WebDriver driver, WebActionUtils webActionUtils)
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
}
