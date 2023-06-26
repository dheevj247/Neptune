package genericLibs;

public class Utility 
{
	public static void sleepInSeconds(int secs)
	{
		try 
		{
			Thread.sleep(secs*1000);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static String split(String value)
	{
		return value.split("\\.")[0];
	}
}
