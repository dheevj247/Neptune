package genericLibs;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class NewXLLib implements AutoContants
{
	@DataProvider(name = DATA_PROVIDER_NAME)
	public static String[][] getAllData(Method m)
	{
		try
		{
			String sheetName = m.getName();
			File f = new File(XL_PATH);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sname = wb.getSheet(sheetName);
			
			int totalRows = sname.getLastRowNum();
			Row rowCells = sname.getRow(0);
			int totCols = rowCells.getLastCellNum();
			
			DataFormatter format = new DataFormatter();
			String testData[][] = new String[totalRows][totCols];
			for(int i = 1; i <= totalRows; i++)
			{
				for(int j = 0; j <totCols; j++)
				{
					testData[i-1][j] = format.formatCellValue(sname.getRow(i).getCell(j));
				}
			}
			return testData;
		}
		catch (Exception e) 
		{
	
		}
		return null;
	}
	
	
}
