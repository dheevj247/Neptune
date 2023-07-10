package genericLibs;

import java.awt.Robot;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class WebActionUtils 
	{
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;
	Actions actions;
	Robot robot; 
	
	public WebActionUtils(WebDriver driver , Duration ETO) 
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, ETO);
		jse = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}
	
	public void enterData(WebElement targetElement , String txt)
	{
		targetElement.clear();
		targetElement.sendKeys(txt);
	}
	
	public void fill(WebElement targetElement , String txt)
	{
		targetElement.sendKeys(txt);
	}
	
	public void enterDataJS(WebElement targetElement, String value)
	{
		jse.executeScript("arguments[0].value = arguments[1]", targetElement, value);
	}
	
	public void elementClick(WebElement targetElement)
	{
		wait.until(ExpectedConditions.elementToBeClickable(targetElement));
		targetElement.click();
	}
	
	public void jsClick(WebElement targetElement)
	{
		jse.executeScript("arguments[0].click();", targetElement);
	}
	
	public void scrollToEnd()
	{
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}
	
	public void scrollToTop()
	{
		jse.executeScript("window.scrollTo(0,-document.body.scrollHeight);");
	}
	
	public void moveToElement(WebElement target)
	{
		actions = new Actions(driver);
		actions.moveToElement(target).perform();
	}
	
	public void selectByText(WebElement targetListBox,String text)
	{
		Select s = new Select(targetListBox) ;
		s.selectByVisibleText(text);
	}
	
	public void switchToFrame(String nameIdOrIndex)
	{
		try 
		{
			int index = Integer.parseInt(nameIdOrIndex);
			driver.switchTo().frame(index);
		} 
		catch (Exception e) 
		{
			driver.switchTo().frame(nameIdOrIndex);
		}
	}
	
	public void closeAllChildWindow()
	{
		Set<String> allWindowIds = driver.getWindowHandles();
		String parentWid = driver.getWindowHandle();
		allWindowIds.remove(parentWid);
		for (String wid : allWindowIds) 
		{
			driver.switchTo().window(wid);
			driver.close();
		}
		driver.switchTo().window(parentWid);//once after closing all window we need to switch the control back to parent
	}
	
	public void switchToAWindow(String wid)
	{
		driver.switchTo().window(wid);
	}
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public String getAlert(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	public String getText(WebElement element)
	{
		return element.getText();
	}
	
	public String getAttribute(WebElement element)
	{
		return element.getAttribute("value");
	}
	
	public String getAttribute(WebElement element, String value)
	{
		return element.getAttribute(value);
	}
	
	public void newTab(WebElement element)
	{
		actions = new Actions(driver);
        // Perform the right-click action & Select
        actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();

        // Switch to the newly opened tab
        String currentWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) 
        {
            if (!windowHandle.equals(currentWindowHandle)) 
            {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
	}
	
	public void actionClick(WebElement element)
	{
		actions = new Actions(driver);
		actions.click(element).perform();
	}
	
	public void enterDataAction(WebElement element, String value)
	{
		actions = new Actions(driver);
		actions.sendKeys(element, value).perform();
	}
}
