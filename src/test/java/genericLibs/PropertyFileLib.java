package genericLibs;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class PropertyFileLib 
{
	/* FileReader: This class is used for reading character data from a file. 
	 * It is specifically designed for reading text files. */
	public static String readPropertyValue(String filePath , String key)
	{
		try
		{
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			return prop.getProperty(key);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/* FileInputStream: This class is used for reading binary data from a file. 
	 * It is commonly used for reading image files, video files etc. */
	public static String getValue(String filePath , String key)
	{
		try
		{
			FileInputStream propFile = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(propFile);
			return prop.getProperty(key);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
