package co.morsum.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;

public class SearchOptimisation extends TestBase {
	
	@Test(dataProvider="getData")
	public void searchOptimisation (String lemailid, String lpassword) throws InterruptedException {
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		//log.debug("Start Smoke Tests !!!");
		test.log(LogStatus.INFO, "Start Test of Product Index Page - Search Optimisation");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//login
		type("DashLoginEmailInputText3_XPATH",lemailid);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput3_XPATH"))).sendKeys(lpassword);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("DashLoginPageSignInBTN3_XPATH");
						
		//cache pop up
		click("DashCachePopUp3_XPATH");
		Thread.sleep(1000);
		
		//Product Index
		click("StartShopping2_XPATH");
		Thread.sleep(10000);
		
		//Last Purchased Filter
		click("LastPurchased_XPATH");
		Thread.sleep(1000);
		
		click("Last6Months_CSS");
		Thread.sleep(10000);
		
		String i = driver.findElement(By.xpath(OR.getProperty("ItemsCount_XPATH"))).getText();
		i=i.replace("Showing ", "");
		i=i.replace(" items", "");
		System.out.println(i);
		
		//Assert Results
		try {
			
			Assert.assertEquals(i, "79");
			test.log(LogStatus.INFO, "Expected no of items after applying Last 6 months filter");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Not showing expected no of items after applying Last 6 months filter. Check and Raise a Jira!!");
				
			}
	
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(2000);
		
		/*click("Brand_XPATH");
		Thread.sleep(1000);
		
		type("BrandSearch_XPATH","Procter Gamble");
		Thread.sleep(2000);
		
		click("Brand1_XPATH");
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(10000);
		
		String j = driver.findElement(By.xpath(OR.getProperty("ItemsCount_XPATH"))).getText();
		j=j.replace("Showing ", "");
		j=j.replace(" items", "");
		System.out.println(j);
		
		try {
			
			Assert.assertEquals(j, "1");
			test.log(LogStatus.INFO, "Expected no of items after applying Brand Procter Gamble filter");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Not showing expected no of items after applying Brand Procter Gamble filter. Check and Raise a Jira!!");
				
			}*/
		
		//Brand Filter
		click("Brand_XPATH");
		Thread.sleep(1000);
		
		click("Brand1_XPATH");
	    Thread.sleep(2000);
	    
	    click("Brand2_XPATH");
	    Thread.sleep(2000);
	    
	    click("Brand3_XPATH");
	    Thread.sleep(2000);
		
	    click("Brand4_XPATH");
	    Thread.sleep(2000);
		
		Thread.sleep(2000);
		Actions action2 = new Actions(driver);
		action2.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(10000);
		
		String k = driver.findElement(By.xpath(OR.getProperty("ItemsCount_XPATH"))).getText();
		k=k.replace("Showing ", "");
		k=k.replace(" items", "");
		System.out.println(k);
		
		//Assert Results
		
		try {
			
			Assert.assertEquals(k, "13");
			test.log(LogStatus.INFO, "Expected no of items after applying Brand filters");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Not showing expected no of items after applying Brand filters. Check and Raise a Jira!!");
				
			}
		
	    
		//Category Filter
		click("Category_XPATH");
		Thread.sleep(2000);
		
		type("CategorySearch_CSS","other");
		Thread.sleep(2000);
		
		click("Category1_XPATH");
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		Actions action3 = new Actions(driver);
		action3.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(10000);
		
		String l = driver.findElement(By.xpath(OR.getProperty("ItemsCount_XPATH"))).getText();
		l=l.replace("Showing ", "");
		l=l.replace(" items", "");
		System.out.println(l);
		
		//Assert Results
		
		try {
			
			Assert.assertEquals(l, "4");
			test.log(LogStatus.INFO, "Expected no of items after applying Category filters");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Not showing expected no of items after applying Category filters. Check and Raise a Jira!!");
				
			}
		
		//SL Filter
		
		click("SLFilter_XPATH");
		Thread.sleep(2000);
		
		type("SL1_CSS","Catering");
		Thread.sleep(2000);
		
		click("SL1_XPATH");
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		Actions action4 = new Actions(driver);
		action4.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(10000);
		
		String m = driver.findElement(By.xpath(OR.getProperty("ItemsCount_XPATH"))).getText();
		m=m.replace("Showing ", "");
		m=m.replace(" items", "");
		System.out.println(m);
		
		//Assert Results
		
		try {
			
			Assert.assertEquals(m, "1");
			test.log(LogStatus.INFO, "Expected no of items after applying SL filters");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Not showing expected no of items after applying SL filters. Check and Raise a Jira!!");
				
			}
		
		//Clear the Filters
		click("ClearBTN_XPATH");
		Thread.sleep(10000);
		
		String n = driver.findElement(By.xpath(OR.getProperty("ItemsCount_XPATH"))).getText();
		n=n.replace("Showing ", "");
		n=n.replace(" items", "");
		System.out.println(n);
		
		//Assert Results
		
		try {
			
			Assert.assertEquals(n, "8907");
			test.log(LogStatus.INFO, "Expected no of items after removing all filters");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Not showing expected no of items after removing all filters. Check and Raise a Jira!!");
				
			}
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName ="SearchOptimisation";
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

	
	


	


