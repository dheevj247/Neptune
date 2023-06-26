package genericLibs;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Snapshot 
{
	public static String getSnapshot(WebDriver driver, String testCaseName) 
	{
		String timeStamp = LocalDateTime.now().toString().replace(":", "-");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String imagePath = "./errorShots/"+testCaseName+timeStamp+".png";
		
		File dest = new File(imagePath);
		try 
		{
			FileUtils.copyFile(src, dest);
			return imagePath;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
