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







public class DVMarginChecksScenario3 extends TestBase {
	
	@Test(dataProvider="getData17")
	public void dVMarginChecksScenario3 (String lemailid21, String lpassword21, String lemailid22, String lpassword22, String lemailid23, String lpassword23) throws InterruptedException {
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		test.log(LogStatus.INFO, "Start DSR - Margin Checks - Scenario 3: Product(2) - Mutiple Units Product + both units + Increase and decrease Margins");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//login
		type("DashLoginEmailInputText2_XPATH",lemailid21);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput2_XPATH"))).sendKeys(lpassword21);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("DashLoginPageSignInBTN2_XPATH");
				
		//cache pop up
		click("DashCachePopUp2_XPATH");
		Thread.sleep(4000);
		
		//Adding two Items to Cart - Same product with different units.
		//Product 1
		click("StartShopping1_XPATH");
		Thread.sleep(10000);
		type("ProductIndexPageSearch1_XPATH","66112");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		
		click("ProductImage_XPATH");
		Thread.sleep(3000);
		
		click("Unit1BTN_XPATH");
		Thread.sleep(3000);
		
		click("AddToCart1_XPATH");
		Thread.sleep(5000);
		
		//click("GoBack_XPATH");
		//Thread.sleep(5000);
		
		//driver.navigate().refresh();
		//Thread.sleep(10000);
		
		
		//Product 2
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500)");
		
		//click("ProductIndexPageSearch1_XPATH");
		//Thread.sleep(1000);
		
		//click("ProductIndexPageClear_XPATH");
		//Thread.sleep(2000);
		
		//type("ProductIndexPageSearch1_XPATH","66112");
		//Thread.sleep(1000);
		//driver.findElement(By.xpath(OR.getProperty("ProductIndexPageSearch1_XPATH"))).sendKeys(Keys.ENTER);
		//Thread.sleep(10000);
		
		//click("ProductImage_XPATH");
		//Thread.sleep(3000);
		
		click("Unit2BTN_XPATH");
		Thread.sleep(3000);
		
		click("AddToCart1_XPATH");
		Thread.sleep(5000);
		
		click("GoBack_XPATH");
		Thread.sleep(5000);
		
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		//cart
		click("CartBTN1_XPATH");
		Thread.sleep(2000);
		click("Toggle_XPATH");
		Thread.sleep(2000);
		
		//Increase Margin
		type("CartProductMarginEditUnit1_XPATH","13.5");
		Thread.sleep(2000);
		
		
		//Decrease Margin
		type("CartProductMarginEdit1Unit2_XPATH","6.2");
		Thread.sleep(2000);
		
		
		//Order
		click("ProceedToCheckout1_XPATH");
		Thread.sleep(2000);
		
		click("Confirm1_XPATH");
		Thread.sleep(2000);
		
		click("ContinueToSummary1_XPATH");
		Thread.sleep(2000);
		
		click("ConfirmOrder1_XPATH");
		Thread.sleep(2000);
		
		//Capture Order no
		
		String i = driver.findElement(By.xpath(OR.getProperty("Order1_XPATH"))).getText();
		i=i.replace("#", "");
		System.out.println("");
		System.out.println(i);
		
		test.log(LogStatus.INFO, "Anchovi Order No is: "+i);
		
		//logoff Anchovi Web
		
		click("GoBackHome1_XPATH");
		Thread.sleep(2000);
		
		click("LogOff1_CSS");
		Thread.sleep(2000);
		
		//Login to NovaPanel STG
		driver.get(config.getProperty("novapanelurl"));
		log.debug("Navigated To: "+config.getProperty("novapanelurl"));
		test.log(LogStatus.INFO, "Logging into Noval Panel STG...");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		type("emailid_XPATH",lemailid22);
		driver.findElement(By.xpath(OR.getProperty("password_XPATH"))).sendKeys(lpassword22);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("login_XPATH");
		Thread.sleep(2000);
		
		//Go to Order Deatils 
		
		click("NovaOrders_XPATH");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(OR.getProperty("NovaOrdersSearch_XPATH"))).sendKeys(i);
		Thread.sleep(2000);
		
		click("OrderNoClick_XPATH");
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0,500)");
		
		//Assertions of flags
		
		//product1
		
		String productname1 = driver.findElement(By.xpath(OR.getProperty("Product3_XPATH"))).getText();
		System.out.println(productname1);
		
		String pflag = null;
		
		if(productname1.equals("philadelphia Cream Cheese 3 Lb Firm Loaf - 66112")) {
			
			pflag="true";
		}
		
		String flag1 = driver.findElement(By.xpath(OR.getProperty("Flag3_XPATH"))).getText();
	    
		System.out.println(pflag);
		System.out.println(flag1);
		
		try {
			
		Assert.assertEquals(pflag, flag1);
		test.log(LogStatus.INFO, "FORWARD PRICES TO INTEGRATIONS Flag is as expected");
		
		} catch(Throwable t) {
			
			test.log(LogStatus.INFO, "FORWARD PRICES TO INTEGRATIONS Flag is not as expected. Check and Raise a Jira!!");
			
		}
		
		//product2
		
		String productname2 = driver.findElement(By.xpath(OR.getProperty("Product2_XPATH"))).getText();
		System.out.println(productname2);
		
		if(productname2.equals("philadelphia Cream Cheese 3 Lb Firm Loaf - 66112")) {
			pflag="true";
			
		}
		
		String flag2 = driver.findElement(By.xpath(OR.getProperty("Flag2_XPATH"))).getText();
		
		System.out.println(pflag);
		System.out.println(flag2);
		
		try {
			
			Assert.assertEquals(pflag, flag2);
			test.log(LogStatus.INFO, "FORWARD PRICES TO INTEGRATIONS Flag is as expected");
			
		} catch(Throwable t) {
			
			test.log(LogStatus.INFO, "FORWARD PRICES TO INTEGRATIONS Flag is not as expected. Check and Raise a Jira!!");
			
		}
		
		
		
		//Login to Integrations STG
		driver.get(config.getProperty("integrationsurl"));
		log.debug("Navigated To: "+config.getProperty("integrationsurl"));
		test.log(LogStatus.INFO, "Logging into Integrations Panel STG...");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		type("iemailid_XPATH",lemailid23);
		driver.findElement(By.xpath(OR.getProperty("ipassword_XPATH"))).sendKeys(lpassword23);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("ilogin_XPATH");
		Thread.sleep(2000);
		
		click("IntegrationsOrders_XPATH");
		click("IntegrationsOrderDetails_XPATH");
		
		js.executeScript("window.scrollBy(0,750)");
		Thread.sleep(3000);
		
		//Assertions of Prices
		
		//product1
				
		String iproductname1 = driver.findElement(By.xpath(OR.getProperty("IProduct2_XPATH"))).getText();
		System.out.println(iproductname1);
				
		double iprice = 0;
				
		if(iproductname1.equals("Cream Cheese 3 Lb Firm Loaf")) {
					
					iprice=82.21;
		}
				
		String iprice1 = driver.findElement(By.xpath(OR.getProperty("IPrice2_XPATH"))).getText();
			    
		System.out.println(iprice);
		System.out.println(Double.parseDouble(iprice1));
				
		try {
					
		Assert.assertEquals(iprice, Double.parseDouble(iprice1));
		test.log(LogStatus.INFO, "FORWARDED PRICE TO INTEGRATIONS is as expected");
				
		 } catch(Throwable t) {
					
		test.log(LogStatus.INFO, "FORWARDED PRICE TO INTEGRATIONS is not as expected. Check and Raise a Jira!!");
					
		 }
				
		//product2
				
		String iproductname2 = driver.findElement(By.xpath(OR.getProperty("IProduct3_XPATH"))).getText();
		System.out.println(iproductname2);
				
		if(iproductname2.equals("Cream Cheese 3 Lb Firm Loaf")) {
			iprice=13.07;
					
		}
				
		String iprice2= driver.findElement(By.xpath(OR.getProperty("IPrice3_XPATH"))).getText();
				
		System.out.println(iprice);
		System.out.println(Double.parseDouble(iprice2));
				
		try {
					
			 Assert.assertEquals(iprice,Double.parseDouble(iprice2));
			 test.log(LogStatus.INFO, "FORWARDED PRICES TO INTEGRATIONS is as expected");
					
		} catch(Throwable t) {
					
			test.log(LogStatus.INFO, "FORWARDED PRICES TO INTEGRATIONS is not as expected. Check and Raise a Jira!!");
					
		}
				
				
		
	}
	
	@DataProvider
	public Object[][] getData17(){
		
		String sheetName ="DVMarginChecksScenario3";
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
