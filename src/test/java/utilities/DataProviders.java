package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
//Data Provider 1
	
	@DataProvider (name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_loginData.xlsx"; //taking xl flie from testdata
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String [totalrows][totalcols]; //Created two dimension array which can store data
		
		for (int i=1; i<=totalrows; i++) //1 //read the data from xl storing in two dimensional array
		
		{
			for (int j=0; j<totalcols; j++) //0 i is rows j is column
			{
				logindata [i-1][j]=xlutil.getCelldata("Sheet1", i, j); //1,0
				
				
			}
				
		}
		return logindata; //Returning two dimension array
	}
	
	// DataProvider 2
	//DataProvider 3
}
