package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import pageObjects.LoginP;

public class Login extends BaseClass
{
 private void open() 
 {		
	driver.get(LoginP.yurl);	
 }
 public void valid_login() throws Exception
 {
	open();
	Thread.sleep(5000);
	driver.findElement(By.name(LoginP.nemail)).sendKeys(prop.getProperty("userid"));
	driver.findElement(By.name(LoginP.nnext)).click();
	driver.findElement(By.name(LoginP.npwd)).sendKeys(prop.getProperty("passwd"));
	driver.findElement(By.name(LoginP.nnext2)).click();
 }
 public void validate_login() throws Exception
 {
	    String err;
		
		FileInputStream fin=new FileInputStream(prop.getProperty("testdata"));
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		XSSFSheet ws=wb.getSheet("Sheet2");  
		
		Row row;
		for(int r=1;r<=ws.getLastRowNum();r++) //for all the rows in the sheet
		{
			row=ws.getRow(r);
			open();
			Thread.sleep(2000);
			driver.findElement(By.name(LoginP.nemail)).sendKeys(row.getCell(0).getStringCellValue());
			driver.findElement(By.name(LoginP.nnext)).click();
			Thread.sleep(5000);
			driver.findElement(By.name(LoginP.npwd)).sendKeys(row.getCell(1).getStringCellValue());
			driver.findElement(By.name(LoginP.nnext2)).click();
			Thread.sleep(5000);
			
			try
			{
				if(driver.findElement(By.linkText(LoginP.lsignout)).isDisplayed())
				{
					row.createCell(2).setCellValue("Login is success");
					driver.findElement(By.linkText(LoginP.lsignout)).click();
				}
			}
			catch(Exception e)
			{
				err=driver.findElement(By.xpath(LoginP.xerrmsg)).getText();
				row.createCell(2).setCellValue("Login is failed  :  "+err);			
			}			
		}
		FileOutputStream fout=new FileOutputStream(prop.getProperty("testdata"));
		wb.write(fout);
		fin.close();
		fout.close(); 
 }
 public void signup() throws Exception
 {
	 open();
	 Thread.sleep(5000);
	 driver.findElement(By.id(LoginP.isignup)).click();
	 try
	 {
		 if(driver.findElement(By.name(LoginP.nfname)).isDisplayed())
		 {
			testlog=report.createTest("Login_signup");
			testlog.log(Status.PASS, "Signup is working");
			takescreenshot("signup.png");
		 }
	 }
	 catch(Exception e)
	 {
		 	testlog=report.createTest("Login_signup");
			testlog.log(Status.FAIL, "Signup NOT working");
			takescreenshot("signup.png");
	 }
 }
}
