package co.morsum.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;






public class ShoppingListsCheck extends TestBase{
	
	@Test(dataProvider="getData21")
	public void shoppingListsCheck (String lemailid, String lpassword, String slname, String slname1, String slname2, String slname3) throws InterruptedException {
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		test.log(LogStatus.INFO, "Start Smoke Test - ShoppingListsCheck ");
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//login
		type("DashLoginEmailInputText4_XPATH",lemailid);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput4_XPATH"))).sendKeys(lpassword);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("DashLoginPageSignInBTN4_XPATH");
		
		//cache pop up
		click("DashCachePopUp4_XPATH");
		Thread.sleep(5000);
		
		//Create a New SL from Home Page
		click("ShoppingListCreate_XPATH");
		Thread.sleep(3000);
		
		type("ShoppingListName_XPATH", slname);
		Thread.sleep(1000);
		
		click("ShoppingListcolor1_CSS");
		Thread.sleep(1000);
		
		click("ShoppingListDropdown_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListVendorSelect_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListCreateBTN_CSS");
		Thread.sleep(1000);
		
		//View the new SL
		click("ViewAllLists_XPATH");
		Thread.sleep(5000);
		
		//Create New SL from Shopping Lists Page
		
		click("SLDropDown_XPATH");
		
		Thread.sleep(1000);
		click("AddNewList_XPATH");
		
		//Repeat of create SL code after standard pop up window
		
		type("ShoppingListName_XPATH", slname1);
		Thread.sleep(1000);
		
		click("ShoppingListcolor1_CSS");
		Thread.sleep(1000);
		
		click("ShoppingListDropdown_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListVendorSelect_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListCreateBTN_CSS");
		Thread.sleep(1000);
		
		//View the new SL
		
		click("SLNameselect_XPATH");
		Thread.sleep(1000);
		
		click("AnchoviImage_XPATH");
		Thread.sleep(1000);
	
		click("ViewAllLists_XPATH");
		Thread.sleep(5000);
				
		
		//Create new SL from Product Index Page
		
		click("AnchoviImage_XPATH");
		Thread.sleep(2000);
		
		click("StartShopping1_XPATH");
		Thread.sleep(10000);
		
		type("ProductIndexPageSearch1_XPATH","39205");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500)");
		
		click("BookMarkIndex_XPATH");
		Thread.sleep(1000);
		
		click("NewListIndex_CSS");
		Thread.sleep(1000);
		
		//Repeat of SL creation from Pop up Window
		
		type("ShoppingListName1_CSS", slname2);
		Thread.sleep(1000);
		
		click("ShoppingListcolor1_CSS");
		Thread.sleep(1000);
		
		click("ShoppingListDropdown_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListVendorSelect_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListCreateBTN_CSS");
		Thread.sleep(1000);
		
		driver.navigate().refresh();
		Thread.sleep(6000);
		
		//View the new SL
		
		click("AnchoviImage_XPATH");
		Thread.sleep(2000);
		
		
		click("ViewAllLists_XPATH");
		Thread.sleep(5000);
				
		
		//Create New SL from Product Details Page
		
		click("AnchoviImage_XPATH");
		Thread.sleep(5000);
		
		click("StartShopping1_XPATH");
		Thread.sleep(10000);
		
		type("ProductIndexPageSearch1_XPATH","39205");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		
		JavascriptExecutor js1= (JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,-500)");
		
		click("ProductImage_XPATH");
		Thread.sleep(5000);
		
		click("BookMarkDetails_XPATH");
		Thread.sleep(1000);
		
		click("NewListDetails_CSS");
		Thread.sleep(2000);
		
		//Repeat of code for creating new SL
		
		type("ShoppingListName2_XPATH", slname3);
		Thread.sleep(1000);
		
		click("ShoppingListcolor1_CSS");
		Thread.sleep(1000);
		
		click("ShoppingListDropdown_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListVendorSelect_XPATH");
		Thread.sleep(1000);
		
		click("ShoppingListCreateBTN_XPATH");
		Thread.sleep(1000);
		
		driver.navigate().refresh();
		Thread.sleep(6000);
		
		//View the new SL
		
		click("AnchoviImage_XPATH");
		Thread.sleep(5000);
		
		click("ViewAllLists_XPATH");
		Thread.sleep(5000);
					
    }
	
	@DataProvider
	public Object[][] getData21(){
		
		String sheetName ="ShoppingListsCheck";
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

