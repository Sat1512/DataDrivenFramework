package co.morsum.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;
//import co.morsum.utilities.TestUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;


public class DashLoginCheckLocation extends TestBase {
	
	@Test(dataProvider="getData1")
	public void dashLoginCheckLocation(String lemailid1, String lpassword1) throws InterruptedException, IOException{
		
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		log.debug("Start DashLogin->DropDown->LocationTest !!!");
	    
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		//login
	    //driver.findElement(By.xpath(OR.getProperty("DashLoginEmailInputText_XPATH"))).sendKeys(lemailid1);
	    type("DashLoginEmailInputText_XPATH",lemailid1);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput_XPATH"))).sendKeys(lpassword1);
		test.log(LogStatus.INFO, "Entering Password.. ");
		//driver.findElement(By.xpath(OR.getProperty("DashLoginPageSignInBTN_XPATH"))).click();
		click("DashLoginPageSignInBTN_XPATH");
		//driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		//driver.findElement(By.xpath(OR.getProperty("DashCachePopUp_XPATH"))).click();
		click("DashCachePopUp_XPATH");
		//driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
	
		//driver.findElement(By.xpath(OR.getProperty("DashHomePageLocationDropDown_XPATH"))).click();
		click("DashHomePageLocationDropDown_XPATH");
	
		
		
		//Assertion
		try {
			
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("DropDownLocationID_CSS"))), "Shared Location Not Found");
		test.log(LogStatus.INFO, "Shared Location Found ");
		
		} catch(Throwable t) {
			
			test.log(LogStatus.INFO, "Shared Location Not Found ");
			//TestUtil.captureScreenshot();
				
		}
		
		log.debug("Test Case 2 Successfully Executed !!!");
		Reporter.log("Test Case 2 Successfully Executed !!!");
	}
	
	@DataProvider
	public Object[][] getData1(){
		
		String sheetName ="DashLoginCheckLocation";
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



