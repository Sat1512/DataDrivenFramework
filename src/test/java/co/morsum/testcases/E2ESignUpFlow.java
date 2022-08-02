package co.morsum.testcases;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;
import co.morsum.utilities.GMail;
import co.morsum.utilities.GMail2;

public class E2ESignUpFlow extends TestBase{
	
	@Test(dataProvider="getData13")
	public void e2ESignUpFlow(String lemailid15, String lpassword15, String operatorName, String operatorEmail, String address, String lemailid16, String lpassword16, String ioperatoremail, String password ) throws InterruptedException {
		
		driver.get(config.getProperty("botestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("botestsiteurl"));
		log.debug("Start E2E Sign Up Flowon Anchovi Web");
		test.log(LogStatus.INFO, "Start E2E Sign Up Flowon Anchovi Web ");
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
	    
		//login to Central BO

		type("CentralLoginEmailInputText1_XPATH",lemailid15);
		
		driver.findElement(By.xpath(OR.getProperty("CentralLoginPasswordInput"))).sendKeys(lpassword15);
		test.log(LogStatus.INFO, "Entering Password.. ");
		
		click("CentralLoginLoginBTN1_XPATH");
		
		//Create Operator
		click("CentralNavSettings1_XPATH");	
		click("CentralNavSettingsOperators_XPATH");
		click("CentalOperatorAddNewBTN_XPATH");
		type("OperatorName_XPATH", operatorName);
		type("OperatorEmail_XPATH",operatorEmail);
		click("OperatorSave_XPATH");
		
		//Map an Operator to a Location
		click("CentralNavFood_XPATH");
		click("CentralNavFoodPurchasing_XPATH");
		click("CentralNavFoodPurchasingOperatorLocCreate_XPATH");
		type("CentralOperatorLocName_XPATH",operatorName);
		click("CentralOperatorLocNameDropDown_XPATH");
		type("CentralOperatorName_XPATH",operatorName);
		click("CentralOperatorNameSelect_XPATH");
		
		Thread.sleep(2000);
		
	    type("CentralOperatorLocNameAddress_XPATH",address);
	   
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath(OR.getProperty("CentralOperatorLocNameAddress_XPATH"))).sendKeys(Keys.ENTER);
	    
	    //Sending an Invite email out
	    driver.get(config.getProperty("novapanelurl"));
		log.debug("Navigated To: "+config.getProperty("novapanelurl"));
		log.debug("Login to Noval Panel...");
		test.log(LogStatus.INFO, "Login to Noval Panel...");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//Login To Nova Panel
		
		type("emailid_XPATH",lemailid16);
		driver.findElement(By.xpath(OR.getProperty("password_XPATH"))).sendKeys(lpassword16);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("login_XPATH");
		Thread.sleep(2000);
		
		click("vendor_XPATH");
		driver.findElement(By.xpath(OR.getProperty("search_XPATH"))).sendKeys("23");
		Thread.sleep(3000);
		
		click("invitebyemailbtn_XPATH");
		type("operatoremailid_XPATH",ioperatoremail);
		click("send_XPATH");
		
		Thread.sleep(3000);
		
		click("Novausername_XPATH");
		Thread.sleep(1000);
		
		click("NovaLogoff_XPATH");
		Thread.sleep(3000);
		
		
		
		//Fetch the first invite email url
		
		HashMap<String, String> hm = GMail.getGmailData("subject:STG - You have been invited to Dash by S&W");
		System.out.println(hm.get("link"));
		driver.get(hm.get("link"));
		
		//Sign up phase 1
		
		type("SignUpFullName_XPATH",operatorName);
		type("SignUpEmail_XPATH",operatorEmail);
		click("SignUpBTN_XPATH");
		
		Thread.sleep(10000);
		
		//Fetch the first invite email url
		
		
		HashMap<String, String> hm1 = GMail2.getGmailData("subject:Dash Email Verification");
		System.out.println(hm1.get("link"));
		driver.get(hm1.get("link"));
		
		//Sign up phase 2
		
		type("PasswordSetUp_XPATH",password);
		Thread.sleep(3000);
		type("PasswordSetUp1_XPATH",password);
		click("Continue_XPATH");
		Thread.sleep(10000);
		//click("LogOff_CSS");
		
		//Running the customer location sync
	    driver.get(config.getProperty("novapanelurl"));
		log.debug("Navigated To: "+config.getProperty("novapanelurl"));
		log.debug("Login to Noval Panel...");
		test.log(LogStatus.INFO, "Login to Noval Panel...");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//Login To Nova Panel
		
		type("emailid_XPATH",lemailid16);
		driver.findElement(By.xpath(OR.getProperty("password_XPATH"))).sendKeys(lpassword16);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("login_XPATH");
		Thread.sleep(2000);
		
		click("CustomerLocations_XPATH");
		Thread.sleep(3000);
	
		click("SyncLocationsBTN_XPATH");
		Thread.sleep(1000);
		
		click("YesBTN_XPATH");
		Thread.sleep(10000);
		
		//Login to Anchovi as new operator
		
		driver.get(config.getProperty("dashtestsiteurl"));
		Thread.sleep(3000);
		type("DashLoginEmailInputText_XPATH",operatorEmail);
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput_XPATH"))).sendKeys(password);
		Thread.sleep(1000);
		click("DashLoginPageSignInBTN_XPATH");
		Thread.sleep(1000);
		click("DashCachePopUp_XPATH");
		click("DashHomePageLocationDropDown_XPATH");
		Thread.sleep(5000);
		
			
	}
	
	@DataProvider
	public Object[][] getData13(){
		
		String sheetName ="E2ESignUpFlow";
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
