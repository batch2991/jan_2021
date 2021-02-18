package testSuites;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.Compose;
import pages.Login;

public class SanityTest extends BaseClass
{
  @Test	
  public void sanitytesting()  throws Exception
  {
	  
	  FileInputStream fin=new FileInputStream(prop.getProperty("testsuites"));	
	  XSSFWorkbook wb=new XSSFWorkbook(fin);			
	  XSSFSheet ws=wb.getSheet("sanitytest");			
		
	  Row row;
	  String classname,methodname;
	  for(int r=1;r<=ws.getLastRowNum();r++)//for all rows in the sheet
	  {
		row=ws.getRow(r); 
		if(row.getCell(5).getStringCellValue().matches("yes"))
		{
			classname=row.getCell(3).getStringCellValue();
			methodname=row.getCell(4).getStringCellValue();
			Class c=Class.forName(classname);//load the Sample class into memory
			Method m=c.getMethod(methodname, null);
			Object obj1=c.newInstance();
			m.invoke(obj1, null);
		}
		fin.close();
	  }
	 /*Login l = new Login();
	 l.valid_login();
	 
	 Compose c= new Compose();
	 c.sendmail();
	 c.close();*/

  }
}
