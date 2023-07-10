package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import genericLibs.WebActionUtils;

public class HomePageBO extends BasePage
{
	
	/*------------------------- Page Verification -------------------------*/
	final static String pageTitle = "My account - My Store";
	final static String pageUrl = "http://automationpractice.com/index.php?controller=my-account";

	public HomePageBO(WebDriver driver, WebActionUtils webActionUtils)
	{
		super(driver, webActionUtils, pageTitle, pageUrl);
	}
	
	/*------------------------ Basic Home Page Operation ------------------------*/
	
	@FindBy(xpath = "//a[@id='ctl00_btnLogout1']")
	private WebElement logoutBTN;
	
	@FindBy(xpath = "//input[@id='ctl00_ibtn_home']")
	private WebElement homeBTN;
	
	public void logoutApp()
	{
		webActionUtils.elementClick(logoutBTN);
		webActionUtils.acceptAlert(driver);
	}
	
	public boolean logoutIsDisplayed()
	{
		return logoutBTN.isDisplayed();
	}
	
	public void myHome()
	{
		webActionUtils.elementClick(homeBTN);
	}
	
	/*-------------------------- Navigation --------------------------*/
	
	@FindBy(xpath = "//a[@id='ctl00_ContentPlaceHolder1_lbtnNewStock']")
	private WebElement stockAccpt;
	
	public void gotoStockAcceptance()
	{
		try
		{
			webActionUtils.elementClick(stockAccpt);
			Reporter.log("clicked on Navigate to Stock Accetance - General",true);
		}
		catch(Exception e)
		{
			Reporter.log("Failed to navigate to Stock Acceptance - General",true);
		}
	}
	
	@FindBy(xpath = "//ul[@id='nav1']/li")
	private List<WebElement> navigationBar;
	
	@FindBy(xpath = "//ul[@id='nav1']/li/div/div/ul/li")
	private List<WebElement> menuList;
	
	@FindBy(xpath = "//ul[@id='nav1']/li/div/div/ul/li/div/div/ul/li")
	private List<WebElement> subMenuList;
	
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

		}
	}
	
	public void selectFromList(String navigation, String menuName, String subMenuName)
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
							webActionUtils.moveToElement(menu);
							for(WebElement subMenu : subMenuList)
							{
								if(subMenu.getText().contains(subMenuName))
								{
									webActionUtils.elementClick(subMenu);
									Reporter.log("successfully clicked " + subMenuName, true);
									break;	
								}			
							}
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
	
	public void contextSelectFromList(String navigation, String menuName)
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
							webActionUtils.newTab(menu);
							Reporter.log("successfully clicked " + menuName, true);
							break;
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
	
	public void contextSelectFromList(String navigation, String menuName, String subMenuName)
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
							webActionUtils.moveToElement(menu);
							for(WebElement subMenu : subMenuList)
							{
								if(subMenu.getText().contains(subMenuName))
								{
									webActionUtils.newTab(subMenu);
									Reporter.log("successfully clicked " + subMenuName, true);
									break;	
								}			
							}
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
	
}
