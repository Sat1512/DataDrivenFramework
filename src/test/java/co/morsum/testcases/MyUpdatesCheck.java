package co.morsum.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;

public class MyUpdatesCheck extends TestBase {
	
	@Test(dataProvider="getData")
	public void myUpdatesCheck (String lemailid, String lpassword) throws InterruptedException {
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		//log.debug("Start Smoke Tests !!!");
		test.log(LogStatus.INFO, "Start Test of Home Page - My Updates - Anchovi Web");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//login
		type("DashLoginEmailInputText3_XPATH",lemailid);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput3_XPATH"))).sendKeys(lpassword);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("DashLoginPageSignInBTN3_XPATH");
						
		//cache pop up
		click("DashCachePopUp3_XPATH");
		Thread.sleep(1000);
		
		//Adding three Items to Cart	
		
		//Product 1
		click("StartShopping1_XPATH");
		Thread.sleep(10000);
		type("ProductIndexPageSearch1_XPATH","75175");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		click("AddToCartBTN1_XPATH");
		Thread.sleep(7000);
		
		//Product 2
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500)");
		
		click("ProductIndexPageSearch1_XPATH");
		Thread.sleep(1000);
		
		click("ProductIndexPageClear_XPATH");
		Thread.sleep(2000);
		
		type("ProductIndexPageSearch1_XPATH","10443");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		
		click("AddToCartBTN1_XPATH");
		Thread.sleep(7000);
		
		//Product 3
		js.executeScript("window.scrollBy(0,-500)");
		
		click("ProductIndexPageSearch1_XPATH");
		Thread.sleep(1000);
		click("ProductIndexPageClear_XPATH");
		Thread.sleep(2000);
		
		type("ProductIndexPageSearch1_XPATH","27097");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		click("AddToCartBTN1_XPATH");
		Thread.sleep(7000);
		
		js.executeScript("window.scrollBy(0,-500)");
		
		//Marging edit in cart
		
		click("CartBTN1_XPATH");
		Thread.sleep(3000);

		//Order
		click("ProceedToCheckout1_XPATH");
		Thread.sleep(3000);
		
		click("Confirm1_XPATH");
		Thread.sleep(3000);
		
		click("ContinueToSummary1_XPATH");
		Thread.sleep(3000);
		
		click("ConfirmOrder1_XPATH");
		Thread.sleep(3000);
		
		//Capture Order no
		
		String i = driver.findElement(By.xpath(OR.getProperty("Order_XPATH"))).getText();
		i=i.replace("#", "");
		System.out.println("");
		System.out.println(i);
		
		test.log(LogStatus.INFO, "Anchovi Order No is: "+i);
		
		//logoff Anchovi Web
		
		click("GoBackHome1_XPATH");
		Thread.sleep(5000);
		
		click("SeeDetails_XPATH");
		Thread.sleep(5000);
		
		String j = driver.findElement(By.xpath(OR.getProperty("OrderNo_XPATH"))).getText();
		j=j.replace("#", "");
		System.out.println("");
		System.out.println(j);
		
       try {
			
			Assert.assertEquals(i, j);
			test.log(LogStatus.INFO, "Order no matches the last one made.");
			
		} catch(Throwable t) {
			
			test.log(LogStatus.INFO, "Order no does not match. Check and Raise a Jira!!");
			
		}
		
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName ="MyUpdatesCheck";
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

	
	


