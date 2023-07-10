package genericLibs;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLLib implements AutoContants
{
	public static String getXLData(String sheetName , int row,int cell)
	{
		try
		{
			FileInputStream xl = new FileInputStream(XL_PATH);
			Workbook wb = WorkbookFactory.create(xl);
			Sheet sh = wb.getSheet(sheetName);
			return sh.getRow(row).getCell(cell).toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[][] getMultipleXLData(String xlFilePath, String sheetName)
	{
		try
		{
			FileInputStream xl = new FileInputStream(xlFilePath);
			Workbook wb = WorkbookFactory.create(xl);
			Sheet sh = wb.getSheet(sheetName);
			int rowCount = sh.getPhysicalNumberOfRows();
			
			String[][] sarr=new String[rowCount-1][];
			for(int i=1,k=0;i<=rowCount-1;i++,k++)
			/* rowCount-1 because i want to avoid 1st row since it contains headers */
			{
				int cellCount = sh.getRow(i).getPhysicalNumberOfCells();
				sarr[k]=new String[cellCount];
				for( int j=0;j<=cellCount-1;j++)
				/* if i wont give cell count -1, i will get AIOOBE as iterations is starting from 0 */
				{
					sarr[k][j]=sh.getRow(i).getCell(j).toString();
				}
			}
			return sarr;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}