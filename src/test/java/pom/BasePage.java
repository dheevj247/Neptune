package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import genericLibs.WebActionUtils;

public class BasePage {

	WebDriver driver;
	WebActionUtils webActionUtils;
	String pageTitle;
	String pageUrl;
	
	public BasePage(WebDriver driver,WebActionUtils webActionUtils,String pageTitle,String pageUrl) 
	{
		this.driver=driver;
		this.webActionUtils=webActionUtils;
		this.pageTitle=pageTitle;
		this.pageUrl=pageUrl;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPageTitle()
	{
		return driver.getTitle().equals(pageTitle);
	}
	
	public boolean verifyPageUrl()
	{
		return driver.getCurrentUrl().equals(pageUrl);
	}
}
