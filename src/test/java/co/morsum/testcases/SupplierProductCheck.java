package co.morsum.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;

public class SupplierProductCheck extends TestBase {
	
	@Test(dataProvider="getData")
	public void supplierProductCheck(String lemailid, String lpassword) throws InterruptedException {
		
		
		driver.get(config.getProperty("botestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("botestsiteurl"));
		log.debug("Start SupplierProductCheck...!!!");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
	    
		//login
		//driver.findElement(By.xpath(OR.getProperty("CentralLoginEmailInputText_XPATH"))).sendKeys(lemailid);
		type("CentralLoginEmailInputText_XPATH",lemailid);
		driver.findElement(By.xpath(OR.getProperty("CentralLoginPasswordInput"))).sendKeys(lpassword);
		test.log(LogStatus.INFO, "Entering Password.. ");
		//driver.findElement(By.xpath(OR.getProperty("CentralLoginLoginBTN_XPATH"))).click();
		click("CentralLoginLoginBTN_XPATH");
		
		//Navigate to Supplier Product Tab
		click("Food_XPATH");
		click("Purchasing_XPATH");
		click("Supplier_XPATH");
		
		//Search Page
		type("SuppliersID_XPATH","1641");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("SuppliersID_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("SuppliersID_XPATH"))).sendKeys(Keys.ENTER);
		
		type("Searchtext_XPATH","79606");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("Searchtext_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//navigate to Product Details Page
		
		String i = driver.findElement(By.xpath(OR.getProperty("SupplierProduct_XPATH"))).getAttribute("href");
		System.out.println(i);
		
		driver.get(i);
		Thread.sleep(5000);
		
		//List all the attributes on the Page
		//List<WebElement> allElements = driver.findElements(By.xpath("//*"));
		
		//Navigate to the Product Packaging details Page
		
		click("SupplierProductPackaging_XPATH");
		Thread.sleep(2500);
		
		click("SupplierPackagingid_XPATH");
		Thread.sleep(5000);
		
		//List all the attributes on the Page
		//List<WebElement> allElements = driver.findElements(By.xpath("//*"));
		
		
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName ="SupplierProductCheck";
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
