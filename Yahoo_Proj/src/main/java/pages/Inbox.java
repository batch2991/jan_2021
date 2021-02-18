package pages;

import org.openqa.selenium.By;

import base.BaseClass;
import pageObjects.InboxP;

public class Inbox extends BaseClass
{
	
  public void deletemail() throws Exception
  {
	  Thread.sleep(5000);
	  driver.findElement(By.xpath(InboxP.xcheckbox)).click();
	  driver.findElement(By.xpath(InboxP.xdelete)).click();	  
  }  
}
