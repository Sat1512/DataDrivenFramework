package co.morsum.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;





public class SupplierTabCheck extends TestBase{
	
	@Test(dataProvider="getData4")
	public void supplierTabCheck (String lemailid4, String lpassword4) throws InterruptedException {
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		test.log(LogStatus.INFO, "Start Test - SupplierTabCheck");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//login
		type("DashLoginEmailInputText3_XPATH",lemailid4);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput3_XPATH"))).sendKeys(lpassword4);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("DashLoginPageSignInBTN3_XPATH");
						
		//cache pop up
		click("DashCachePopUp3_XPATH");
		Thread.sleep(1000);
		click("SupplierTab_XPATH");
		Thread.sleep(2000);
		click("ShowProducts_XPATH");
		Thread.sleep(10000);
		type("ProductIndexPageSearch1_XPATH","56959");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		click("GoBackHome2_XPATH");
		Thread.sleep(2000);
		click("LogOff2_CSS");	
		
	}
	
	@DataProvider
	public Object[][] getData4(){
		
		String sheetName ="SupplierTabCheck";
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
