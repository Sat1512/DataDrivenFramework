package co.morsum.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import co.morsum.utilities.ExcelReader;
import co.morsum.utilities.ExtentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {
	
	/*
	 * 
	 Initialize WebDriver.
	 Mutiple Webbrowser - TBD.
	 Initialize Properties File.
	 Initialize Config File.
	 Application Logs and Selenium Logs.
	 Assertions.
	 Data Provider.
	 Explicit and Implicit waits.
	 ReportNG.
	 Listeners.
	 Caputuring screenshots of failures.*
	 Extent Reports with Steps.
	 RunModes.
	 Jenkins Integration.
	 Email Notifications.
	 *
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel= new ExcelReader(System.getProperty("user.dir")+ "/src/test/resources/excel/testdata.xlsx");
	//public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	

	@BeforeMethod
	public void setUp() {
		
		if(driver==null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {			
				config.load(fis);		
				log.debug("Config File Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {	
				fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/properties/OR.properties");	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
			
			try {
				OR.load(fis);	
				log.debug("OR File Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/src/test/resources/executables/chromedriver");
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");

			} 
			
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		    //wait = new WebDriverWait(driver,20);
		    
		}
			
	}
	
	public void click (String locator) {
		
		if(locator.endsWith("_XPATH")){
			
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		
		} else if(locator.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			
		}
		
		test.log(LogStatus.INFO, "Clicking on : " +locator);
		
	}
		
	public void type(String locator, String value) {
		
		if(locator.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			
		} else if(locator.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			
		}
		
		test.log(LogStatus.INFO, "Typing in : "+locator+" entered values as "+value);
	}
	
	
	
	public boolean isElementPresent(By by) {
		
		try {
			
			driver.findElement(by);
			return true;
			
		} catch(NoSuchElementException e) {
			
			return false;
				
		}
			
	}
	

	@AfterMethod  
	public void tearDown() {
		
		if(driver!=null) {
	
		driver.quit();
		driver=null;
			
	     }
		
		log.debug("Test Suite Execution Completed !!!");
		
	   }
}
