package co.morsum.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import co.morsum.base.TestBase;
import co.morsum.utilities.MonitoringMail;
import co.morsum.utilities.TestConfig;
import co.morsum.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener,ISuiteListener {

	public String messagebody;
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test =rep.startTest(result.getName().toUpperCase());
		//Check Run Mode is Y/N
		
		if(!TestUtil.isTestRunnable(result.getName(), excel)) {
			
			throw new SkipException("Skipping the test " + result.getName().toUpperCase() + " as the Run mode is ON");
			
		}
			
}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS, result.getName().toUpperCase()+"PASS");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.setProperty("org.uncommons.reportng.escape-output","false");
		Reporter.log("Click to see Screenshot..");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Failed with excpetion: "+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotname));
		
	
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotname+">Screenshot</a>");
		Reporter.log("..or Click on the thumbnail below");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotname+"><img src="+TestUtil.screenshotname+" height=200 width = 200></img></a>");                   
		
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
		
	}

	
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			messagebody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/AnchoviWebQAAutomationJobs/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,TestConfig.subject,messagebody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
	
	

}
