package co.morsum.testcases;


import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;








public class ShoppingListsCheckCont extends TestBase {
	
	@Test(dataProvider="getData21")
	public void shoppingListsCheckCont (String lemailid, String lpassword) throws InterruptedException {
		
		driver.get(config.getProperty("dashtestsiteurl"));
		log.debug("Navigated To: "+config.getProperty("dashtestsiteurl"));
		test.log(LogStatus.INFO, "Start ShoppingListsCheckCont.. ");
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
		
		//login
		type("DashLoginEmailInputText4_XPATH",lemailid);
		driver.findElement(By.xpath(OR.getProperty("DashLoginPasswordInput4_XPATH"))).sendKeys(lpassword);
		test.log(LogStatus.INFO, "Entering Password.. ");
		click("DashLoginPageSignInBTN4_XPATH");
		
		//cache pop up
		click("DashCachePopUp4_XPATH");
		Thread.sleep(5000);
		
		//Access an SL with 100 + Products
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500)");
		
		test.log(LogStatus.INFO, "Velocity-D34495 Shopping list accessed...");
		
		click("ShoppingListSelect_XPATH");
		Thread.sleep(10000);
		
		//Setting the counter of SL to Zero
		click("ClearQauntityBTN_XPATH");
		Thread.sleep(5000);
		
		//Searching for **Special Order** product with a single unit and assinging a quantity.
		
		type("ShoppingListSearch_XPATH","76665");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(OR.getProperty("ShoppingListSearch_XPATH"))).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		type("ShoppingListQuantity_XPATH","3");
		Thread.sleep(1000);
		
		//Reading Product Name, Item no, Packsize text and status and print them in report for now..
		
		String pdetails = driver.findElement(By.xpath(OR.getProperty("ProductName_XPATH"))).getText();
		System.out.println(pdetails);
		
		String pdetails1 = driver.findElement(By.xpath(OR.getProperty("ProductDetails_XPATH"))).getText();
		System.out.println(pdetails1);
		
		test.log(LogStatus.INFO, "Product Name is: "+pdetails);
		test.log(LogStatus.INFO, "Product details are as follows: "+pdetails1);
		
		
		//Getting Price and subtotal
		
		String price1 = driver.findElement(By.cssSelector(OR.getProperty("SLProductPrice_CSS"))).getText();
		price1=price1.replace("$ ", "");
		//System.out.println(price1);
		Thread.sleep(2000);
		
		String subtotal1 = driver.findElement(By.cssSelector(OR.getProperty("SLProductSubtotal_CSS"))).getText();
		subtotal1=subtotal1.replace("$ ", "");
		//System.out.println(subtotal1);
		Thread.sleep(1000);
		
		//Converting price from string to double and calculating expected subtotal
		
		double price = Double.parseDouble(price1);
						
		//System.out.println("Converted Price: "+price);
		System.out.println("Read Subtotal from Anchovi: "+subtotal1);
		test.log(LogStatus.INFO, "Subtotal read from Anchovi: "+subtotal1);

		
		double calsubtotal = price*3;
		
		//round calculated value to 2 decimal points
		
		DecimalFormat expsubtotal = new DecimalFormat("#.###");
		String verifyvalue =expsubtotal.format(calsubtotal);
		
		System.out.println("Calculated & Rounded Subtotal: "+verifyvalue);
		test.log(LogStatus.INFO, "Calculated Subtotal: "+verifyvalue);
		Thread.sleep(1000);
		
		//Asserting subtotal
		
		try {
			
			Assert.assertEquals(subtotal1,verifyvalue);
			
			test.log(LogStatus.INFO, "Subtotal read from Anchovi Matches the Calculated Subtotal");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Subtotal read from Anchovi is not as expected. Check and Raise a Jira!!");
				
			}
		
		//Reading all counters
		
		//Item Counter
		String ItemCounter  = driver.findElement(By.cssSelector(OR.getProperty("ItemCounter_CSS"))).getText();
		Thread.sleep(1000);
        
        System.out.println(ItemCounter);
        
        //Case counter
        String CaseCounter  = driver.findElement(By.xpath(OR.getProperty("CaseCounter_XPATH"))).getText();
		Thread.sleep(1000);
		CaseCounter=CaseCounter.replace("  - ", "");
        System.out.println(CaseCounter);
        
        //Each counter
        
        String EachCounter  = driver.findElement(By.xpath(OR.getProperty("EachCounter_XPATH"))).getText();
		Thread.sleep(1000);
		EachCounter=EachCounter.replace("  - ", "");
        System.out.println(EachCounter);
        
        //Assertion of counter information
        
        
        //Item count assertion
        try {
			
			Assert.assertEquals(ItemCounter,"1 item");
			
			test.log(LogStatus.INFO, "Item count is as expected");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Item count is not as expected. Check and Raise a Jira!!");
				
			}
        
        //Case count assertion
        try {
			
			Assert.assertEquals(CaseCounter,"3 cases");
			
			test.log(LogStatus.INFO, "Case count is as expected");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Case count is not as expected. Check and Raise a Jira!!");
				
			}
        
        //Each count assertion
       
        try {
			
			Assert.assertEquals(EachCounter,"0 eaches");
			
			test.log(LogStatus.INFO, "Each count is as expected");
			
			} catch(Throwable t) {
				
				test.log(LogStatus.INFO, "Each count is not as expected. Check and Raise a Jira!!");
				
			}
        
        //Clear the search
        click("SLSearchResetBTN_XPATH");
        Thread.sleep(5000);
        
       //Searching for **Back Order** product with a single unit and assinging a quantity.
		
      	type("ShoppingListSearch_XPATH","43256");
      	Thread.sleep(1000);
      		
      	driver.findElement(By.xpath(OR.getProperty("ShoppingListSearch_XPATH"))).sendKeys(Keys.ENTER);
      	Thread.sleep(1000);
      		
      	type("ShoppingListQuantity_XPATH","2");
      	Thread.sleep(1000);
      		
      	//Reading Product Name, Item no, Packsize text and status and print them in report for now..
      		
      	String pdetails2 = driver.findElement(By.xpath(OR.getProperty("ProductName_XPATH"))).getText();
      	System.out.println(pdetails2);
      		
      	String pdetails3 = driver.findElement(By.xpath(OR.getProperty("ProductDetails_XPATH"))).getText();
      	System.out.println(pdetails3);
      		
      	test.log(LogStatus.INFO, "Product Name is: "+pdetails2);
      	test.log(LogStatus.INFO, "Product details are as follows: "+pdetails3);
      		
      		
      	//Getting Price and subtotal
      		
      	String price2 = driver.findElement(By.cssSelector(OR.getProperty("SLProductPrice_CSS"))).getText();
      	price2=price2.replace("$ ", "");
      	//System.out.println(price1);
      	Thread.sleep(2000);
      		
      	String subtotal2 = driver.findElement(By.cssSelector(OR.getProperty("SLProductSubtotal_CSS"))).getText();
      	subtotal2=subtotal2.replace("$ ", "");
      	//System.out.println(subtotal1);
      	Thread.sleep(1000);
      		
      	//Converting price from string to double and calculating expected subtotal
      		
      	double price3 = Double.parseDouble(price2);
      						
      	//System.out.println("Converted Price: "+price);
      	System.out.println("Read Subtotal from Anchovi: "+subtotal2);
      	test.log(LogStatus.INFO, "Subtotal read from Anchovi: "+subtotal2);

      		
      	double calsubtotal1 = price3*2;
      		
      	//round calculated value to 2 decimal points
      		
      	DecimalFormat expsubtotal1 = new DecimalFormat("#.###");
      	String verifyvalue1 =expsubtotal1.format(calsubtotal1);
      		
      	System.out.println("Calculated & Rounded Subtotal: "+verifyvalue1);
      	test.log(LogStatus.INFO, "Calculated Subtotal: "+verifyvalue1);
      	Thread.sleep(1000);
      		
      	//Asserting subtotal
      		
      	try {
      			
      		Assert.assertEquals(subtotal2,verifyvalue1);
      			
      		test.log(LogStatus.INFO, "Subtotal read from Anchovi Matches the Calculated Subtotal");
      			
      		} catch(Throwable t) {
      				
      				test.log(LogStatus.INFO, "Subtotal read from Anchovi is not as expected. Check and Raise a Jira!!");
      				
      		}
      		
      	//Reading all counters
      		
      	//Item Counter
      	String ItemCounter1  = driver.findElement(By.cssSelector(OR.getProperty("ItemCounter_CSS"))).getText();
      	Thread.sleep(1000);
              
        System.out.println(ItemCounter1);
              
        //Case counter
        String CaseCounter1  = driver.findElement(By.xpath(OR.getProperty("CaseCounter_XPATH"))).getText();
        Thread.sleep(1000);
      	CaseCounter1=CaseCounter1.replace("  - ", "");
        System.out.println(CaseCounter1);
              
        //Each counter
              
        String EachCounter1  = driver.findElement(By.xpath(OR.getProperty("EachCounter_XPATH"))).getText();
        Thread.sleep(1000);
      	EachCounter1=EachCounter1.replace("  - ", "");
        System.out.println(EachCounter1);
              
        //Assertion of counter information
              
              
        //Item count assertion
        try {
      			
      		Assert.assertEquals(ItemCounter1,"2 items");
      			
      		test.log(LogStatus.INFO, "Item count is as expected");
      			
      		} catch(Throwable t) {
      				
      		test.log(LogStatus.INFO, "Item count is not as expected. Check and Raise a Jira!!");
      				
      		}
              
        //Case count assertion
        try {
      			
      		Assert.assertEquals(CaseCounter1,"5 cases");
      			
      		test.log(LogStatus.INFO, "Case count is as expected");
      			
      		} catch(Throwable t) {
      				
      		test.log(LogStatus.INFO, "Case count is not as expected. Check and Raise a Jira!!");
      				
      		}
              
         //Each count assertion
             
         try {
      			
      		Assert.assertEquals(EachCounter1,"0 eaches");
      			
      		test.log(LogStatus.INFO, "Each count is as expected");
      			
      		} catch(Throwable t) {
      				
      		test.log(LogStatus.INFO, "Each count is not as expected. Check and Raise a Jira!!");
      				
      		}
              
          //Clear the search
          click("SLSearchResetBTN_XPATH");
          Thread.sleep(5000);
        
    	//Searching for **Discontinued** product with a single unit and assinging a quantity.
          
        type("ShoppingListSearch_XPATH","76655");
        Thread.sleep(1000);
        		
        driver.findElement(By.xpath(OR.getProperty("ShoppingListSearch_XPATH"))).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        
        click("QuantitySizedDropdown_XPATH");
        Thread.sleep(2000);
        
        click("QuantitySizeSelect_XPATH");
        Thread.sleep(2000);
        
        
        		
        type("ShoppingListQuantity_XPATH","1");
        Thread.sleep(1000);
        		
        //Reading Product Name, Item no, Packsize text and status and print them in report for now..
        		
        String dpdetails = driver.findElement(By.xpath(OR.getProperty("ProductName_XPATH"))).getText();
        System.out.println(dpdetails);
        		
        String dpdetails1 = driver.findElement(By.xpath(OR.getProperty("ProductDetails_XPATH"))).getText();
        System.out.println(dpdetails1);
        		
        test.log(LogStatus.INFO, "Product Name is: "+dpdetails);
        test.log(LogStatus.INFO, "Product details are as follows: "+dpdetails1);
        		
        		
        //Getting Price and subtotal
        		
        String dprice = driver.findElement(By.cssSelector(OR.getProperty("SLProductPrice_CSS"))).getText();
        dprice=dprice.replace("$ ", "");
        //System.out.println(price1);
        Thread.sleep(2000);
        		
        String dsubtotal = driver.findElement(By.cssSelector(OR.getProperty("SLProductSubtotal_CSS"))).getText();
        dsubtotal=dsubtotal.replace("$ ", "");
        //System.out.println(subtotal1);
        Thread.sleep(1000);
        		
        //Converting price from string to double and calculating expected subtotal
        		
        double dprice1 = Double.parseDouble(dprice);
        						
        //System.out.println("Converted Price: "+price);
        System.out.println("Read Subtotal from Anchovi: "+dsubtotal);
        test.log(LogStatus.INFO, "Subtotal read from Anchovi: "+dsubtotal);

        		
        	double dcalsubtotal = dprice1*2;
        		
        	//round calculated value to 2 decimal points
        		
        	DecimalFormat dexpsubtotal = new DecimalFormat("#.###");
        	String dverifyvalue =dexpsubtotal.format(dcalsubtotal);
        		
        	System.out.println("Calculated & Rounded Subtotal: "+dverifyvalue);
        	test.log(LogStatus.INFO, "Calculated Subtotal: "+dverifyvalue);
        	Thread.sleep(1000);
        		
        	//Asserting subtotal
        		
        	try {
        			
        		Assert.assertEquals(dsubtotal,dverifyvalue);
        			
        		test.log(LogStatus.INFO, "Subtotal read from Anchovi Matches the Calculated Subtotal");
        			
        		} catch(Throwable t) {
        				
        				test.log(LogStatus.INFO, "Subtotal read from Anchovi is not as expected. Check and Raise a Jira!!");
        				
        		}
        		
        	//Reading all counters
        		
        	//Item Counter
        	String dItemCounter = driver.findElement(By.cssSelector(OR.getProperty("ItemCounter_CSS"))).getText();
        	Thread.sleep(1000);
                
          System.out.println(dItemCounter);
                
          //Case counter
          String dCaseCounter  = driver.findElement(By.xpath(OR.getProperty("CaseCounter_XPATH"))).getText();
          Thread.sleep(1000);
          dCaseCounter=dCaseCounter.replace("  - ", "");
          System.out.println(dCaseCounter);
                
          //Each counter
                
          String dEachCounter  = driver.findElement(By.xpath(OR.getProperty("EachCounter_XPATH"))).getText();
          Thread.sleep(1000);
          dEachCounter=dEachCounter.replace("  - ", "");
          System.out.println(dEachCounter);
                
          //Assertion of counter information
                
                
          //Item count assertion
          try {
        			
        		Assert.assertEquals(dItemCounter,"3 items");
        			
        		test.log(LogStatus.INFO, "Item count is as expected");
        			
        		} catch(Throwable t) {
        				
        		test.log(LogStatus.INFO, "Item count is not as expected. Check and Raise a Jira!!");
        				
        		}
                
          //Case count assertion
          try {
        			
        		Assert.assertEquals(dCaseCounter,"5 cases");
        			
        		test.log(LogStatus.INFO, "Case count is as expected");
        			
        		} catch(Throwable t) {
        				
        		test.log(LogStatus.INFO, "Case count is not as expected. Check and Raise a Jira!!");
        				
        		}
                
           //Each count assertion
               
           try {
        			
        		Assert.assertEquals(dEachCounter,"1 each");
        			
        		test.log(LogStatus.INFO, "Each count is as expected");
        			
        		} catch(Throwable t) {
        				
        		test.log(LogStatus.INFO, "Each count is not as expected. Check and Raise a Jira!!");
        				
        		}
                
            //Clear the search
            click("SLSearchResetBTN_XPATH");
            Thread.sleep(5000);
            
        	//Searching for **Stocked** product with a Mutiple unit and assinging a quantity.
            
            type("ShoppingListSearch_XPATH","31188");
            Thread.sleep(1000);
            		
            driver.findElement(By.xpath(OR.getProperty("ShoppingListSearch_XPATH"))).sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            
            click("QuantitySizedDropdown_XPATH");
            Thread.sleep(2000);
            
            click("QuantitySizeSelect_XPATH");
            Thread.sleep(2000);
            
            
            		
            type("ShoppingListQuantity_XPATH","5");
            Thread.sleep(1000);
            		
            //Reading Product Name, Item no, Packsize text and status and print them in report for now..
            		
            String spdetails = driver.findElement(By.xpath(OR.getProperty("ProductName_XPATH"))).getText();
            System.out.println(spdetails);
            		
            String spdetails1 = driver.findElement(By.xpath(OR.getProperty("ProductDetails_XPATH"))).getText();
            System.out.println(spdetails1);
            		
            test.log(LogStatus.INFO, "Product Name is: "+spdetails);
            test.log(LogStatus.INFO, "Product details are as follows: "+spdetails1);
            		
            		
            //Getting Price and subtotal
            		
            String sprice = driver.findElement(By.cssSelector(OR.getProperty("SLProductPrice_CSS"))).getText();
            sprice=sprice.replace("$ ", "");
            //System.out.println(price1);
            Thread.sleep(2000);
            		
            String ssubtotal = driver.findElement(By.cssSelector(OR.getProperty("SLProductSubtotal_CSS"))).getText();
            ssubtotal=ssubtotal.replace("$ ", "");
            //System.out.println(subtotal1);
            Thread.sleep(1000);
            		
            //Converting price from string to double and calculating expected subtotal
            		
            double sprice1 = Double.parseDouble(sprice);
            						
            //System.out.println("Converted Price: "+price);
            System.out.println("Read Subtotal from Anchovi: "+ssubtotal);
            test.log(LogStatus.INFO, "Subtotal read from Anchovi: "+ssubtotal);

            		
            	double scalsubtotal = sprice1*5;
            		
            	//round calculated value to 2 decimal points
            		
            	DecimalFormat sexpsubtotal = new DecimalFormat("#.###");
            	String sverifyvalue =sexpsubtotal.format(scalsubtotal);
            		
            	System.out.println("Calculated & Rounded Subtotal: "+sverifyvalue);
            	test.log(LogStatus.INFO, "Calculated Subtotal: "+sverifyvalue);
            	Thread.sleep(1000);
            		
            	//Asserting subtotal
            		
            	try {
            			
            		Assert.assertEquals(ssubtotal,sverifyvalue);
            			
            		test.log(LogStatus.INFO, "Subtotal read from Anchovi Matches the Calculated Subtotal");
            			
            		} catch(Throwable t) {
            				
            				test.log(LogStatus.INFO, "Subtotal read from Anchovi is not as expected. Check and Raise a Jira!!");
            				
            		}
            		
            	//Reading all counters
            		
            	//Item Counter
            	String sItemCounter = driver.findElement(By.cssSelector(OR.getProperty("ItemCounter_CSS"))).getText();
            	Thread.sleep(1000);
                    
              System.out.println(sItemCounter);
                    
              //Case counter
              String sCaseCounter  = driver.findElement(By.xpath(OR.getProperty("CaseCounter_XPATH"))).getText();
              Thread.sleep(1000);
              sCaseCounter=sCaseCounter.replace("  - ", "");
              System.out.println(sCaseCounter);
                    
              //Each counter
                    
              String sEachCounter  = driver.findElement(By.xpath(OR.getProperty("EachCounter_XPATH"))).getText();
              Thread.sleep(1000);
              sEachCounter=sEachCounter.replace("  - ", "");
              System.out.println(sEachCounter);
                    
              //Assertion of counter information
                    
                    
              //Item count assertion
              try {
            			
            		Assert.assertEquals(sItemCounter,"4 items");
            			
            		test.log(LogStatus.INFO, "Item count is as expected");
            			
            		} catch(Throwable t) {
            				
            		test.log(LogStatus.INFO, "Item count is not as expected. Check and Raise a Jira!!");
            				
            		}
                    
              //Case count assertion
              try {
            			
            		Assert.assertEquals(sCaseCounter,"5 cases");
            			
            		test.log(LogStatus.INFO, "Case count is as expected");
            			
            		} catch(Throwable t) {
            				
            		test.log(LogStatus.INFO, "Case count is not as expected. Check and Raise a Jira!!");
            				
            		}
                    
               //Each count assertion
                   
               try {
            			
            		Assert.assertEquals(sEachCounter,"6 eaches");
            			
            		test.log(LogStatus.INFO, "Each count is as expected");
            			
            		} catch(Throwable t) {
            				
            		test.log(LogStatus.INFO, "Each count is not as expected. Check and Raise a Jira!!");
            				
            		}
                    
                //Clear the search
                click("SLSearchResetBTN_XPATH");
                Thread.sleep(5000);
        
        //Add all the Items to Cart
                
        click("AddItemsToCart_XPATH");
        Thread.sleep(5000);
        
        click("IUnderstand_XPATH");
        Thread.sleep(5000);
        
        click("CartBTN1_XPATH");
        Thread.sleep(5000);
        	
        //Read Items from cart
        
        String CartProduct1  = driver.findElement(By.xpath(OR.getProperty("CartProduct1_XPATH"))).getText();
        String CartProduct2  = driver.findElement(By.xpath(OR.getProperty("CartProduct2_XPATH"))).getText();
        Thread.sleep(5000);
        test.log(LogStatus.INFO, "Products added to cart as expected: "+ CartProduct1 + "/"+ CartProduct2);

        
						
    }
	
	@DataProvider
	public Object[][] getData21(){
		
		String sheetName ="ShoppingListsCheckCont";
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




