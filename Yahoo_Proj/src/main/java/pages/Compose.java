package pages;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import pageObjects.ComposeP;
import pageObjects.LoginP;

public class Compose extends BaseClass
{
 public void sendmail() throws Exception
 {
	 Thread.sleep(5000);
	 driver.findElement(By.xpath(ComposeP.xcompose)).click();
	 Thread.sleep(5000);
	 try
	 {
		 if(driver.findElement(By.name(ComposeP.ito)).isDisplayed())
		 {
			 testlog=report.createTest("Compose_sendmail");
			 testlog.log(Status.PASS, "Compose is working");
			 takescreenshot("compose.png");
			 driver.findElement(By.id(ComposeP.ito)).sendKeys("abcd@gmail.com");
			 driver.findElement(By.id(ComposeP.isub)).sendKeys("testmail");
			 driver.findElement(By.id(ComposeP.ibody)).sendKeys("This is sample message in the body");
			 driver.findElement(By.xpath(ComposeP.xsend)).click();
			 Thread.sleep(5000);
			 System.out.println("hello");
		 }
	 }
	 catch(Exception e)
	 {
		 testlog=report.createTest("Compose_sendmail");
		 testlog.log(Status.FAIL, "Compose NOT working");
		 takescreenshot("compose.png");
	 }	 
 }
 public void close()
 {
	 driver.findElement(By.linkText(LoginP.lsignout)).click();	
 }
}
