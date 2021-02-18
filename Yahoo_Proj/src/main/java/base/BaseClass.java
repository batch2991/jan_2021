package base;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class BaseClass 
{
  public static WebDriver driver;  
  public static Properties prop;
  
  public static ExtentHtmlReporter exthtml;
  public static ExtentReports report;
  public static ExtentTest testlog;
  public static DesiredCapabilities ds;
  
  @BeforeSuite
  public void reportinitialise()
  {
	  System.out.println("hello");
	  	prop=new Properties();
		try{prop.load(new FileInputStream("src/main/java/config/config.properties"));}catch(Exception e) {}
		
		ds=new DesiredCapabilities();
		if(prop.getProperty("browsername").matches("firefox"))
		{
			driver=new FirefoxDriver();			
			//ds=DesiredCapabilities.firefox();
			//ds.setPlatform(Platform.WINDOWS);				
		}
		if(prop.getProperty("browsername").matches("chrome"))
		{
			driver=new ChromeDriver();
			//ds=DesiredCapabilities.chrome();
			//ds.setPlatform(Platform.WINDOWS);
		}
		
		//try{driver=new RemoteWebDriver(new URL("http://192.168.29.55:1234/wd/hub"), ds); }catch(Exception e) {}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		   exthtml = new ExtentHtmlReporter(prop.getProperty("reportslocation")+"yahoo_results.html");
		   report = new ExtentReports();
	 	   report.attachReporter(exthtml);
	 	   report.setSystemInfo("Host Name", "TestSystem");  //name of thesystem
	 	   report.setSystemInfo("Environment", "Test Env");
	 	   report.setSystemInfo("User Name", "venkatgn");
	 	   
	 	   exthtml.config().setDocumentTitle("Yahoo");
	 	   exthtml.config().setReportName("Yahoo Functional Testing");
	 	   exthtml.config().setTestViewChartLocation(ChartLocation.TOP);
	 	   exthtml.config().setTheme(Theme.STANDARD);
  }
  public void takescreenshot(String filename)
  {
  	try
  	{
  	File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  	FileUtils.copyFile(f,new File(prop.getProperty("screens")+filename));
  	
  	testlog.addScreenCaptureFromPath(prop.getProperty("screens")+filename);
  	}catch(Exception e) {}
  	
  	
  }
  
  @AfterSuite
  public void endofsuite()
  {
	  report.flush();//save the report
	  driver.quit();
	  
	  try{Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");}catch(Exception e) {}
  }
  
  
}
