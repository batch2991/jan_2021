package testSuites;


import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.Compose;
import pages.Inbox;
import pages.Login;

public class ReTest extends BaseClass
{
  
  @Test	
  public void retesting() throws Exception
  {
	 
	  FileInputStream fin=new FileInputStream(prop.getProperty("testsuites"));	
	  XSSFWorkbook wb=new XSSFWorkbook(fin);			
	  XSSFSheet ws=wb.getSheet("retest");			
		
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
	  
	 /* Login l=new Login();
	  l.validate_login();
	  l.signup();
	  l.valid_login();
	  
	  Inbox i=new Inbox();
	  i.deletemail();
	  
	  Compose c=new Compose();
	  c.close();*/
	  
	 
  }
}
