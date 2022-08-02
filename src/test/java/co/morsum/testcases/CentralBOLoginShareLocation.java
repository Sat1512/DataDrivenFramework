package co.morsum.testcases;

import java.util.concurrent.TimeUnit;

//import java.util.List;
import org.openqa.selenium.By;
import org.testng.Reporter;
//import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;

public class CentralBOLoginShareLocation extends TestBase{
	
	@Test(dataProvider="getData")
	public void centralBOLoginShareLocation(String lemailid, String lpassword, String uemailid, String ulocation, String locname) throws InterruptedException {
		
		
		driver.get(config.getProperty("botestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("botestsiteurl"));
		log.debug("Start CentralBOLoginTest !!!");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
	    
		//login
		//driver.findElement(By.xpath(OR.getProperty("CentralLoginEmailInputText_XPATH"))).sendKeys(lemailid);
		type("CentralLoginEmailInputText_XPATH",lemailid);
		driver.findElement(By.xpath(OR.getProperty("CentralLoginPasswordInput"))).sendKeys(lpassword);
		test.log(LogStatus.INFO, "Entering Password.. ");
		//driver.findElement(By.xpath(OR.getProperty("CentralLoginLoginBTN_XPATH"))).click();
		click("CentralLoginLoginBTN_XPATH");
		
		//user Access
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettings_XPATH"))).click();
		click("CentralNavSettings_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsers_XPATH"))).click();
		click("CentralNavSettingsUsers_XPATH");
		
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersSearch_XPATH"))).sendKeys(uemailid);
		type("CentralNavSettingsUsersSearch_XPATH",uemailid);
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersSearchBTN_XPATH"))).click();
		click("CentralNavSettingsUsersSearchBTN_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersSearchUserClick_XPATH"))).click();
		click("CentralNavSettingsUsersSearchUserClick_XPATH");
		
		//user Location Tab
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersOpLocationTab_XPATH"))).click();
		click("CentralNavSettingsUsersOpLocationTab_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersOpLocationTabSearch_XPATH"))).sendKeys(ulocation);
		type("CentralNavSettingsUsersOpLocationTabSearch_XPATH",ulocation);
		//driver.findElement(By.xpath(OR.getProperty("SearchBTN"))).click();
		click("SearchBTN_XPATH");
		
		//Toggle on/off
		driver.findElement(By.linkText(locname)).click();
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersOpLocationTabLoNameToggle_XPATH"))).click();
		click("CentralNavSettingsUsersOpLocationTabLoNameToggle_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersOpLocationTabLoNameToggleSave"))).click();
		click("CentralNavSettingsUsersOpLocationTabLoNameToggleSave_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("CentralNavSettingsUsersOpLocationTabLoNameToggleExit_XPATH"))).click();
		click("CentralNavSettingsUsersOpLocationTabLoNameToggleExit_XPATH");
		
		log.debug("Test Case 1 Successfully Executed !!!");
		Reporter.log("Test Case 1 Successfully Executed !!!");
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName ="CentralBOLoginShareLocation";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum=2; rowNum <=rows; rowNum++)
		{
			
			for (int colNum=0; colNum<cols; colNum++)
		
			{
				
				data[rowNum-2][colNum]  = excel.getCellData(sheetName, colNum, rowNum);
						
			}
			
		}
		
		return data;
		
	}
	
}
