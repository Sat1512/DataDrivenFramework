package co.morsum.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import co.morsum.utilities.MonitoringMail;
import co.morsum.utilities.TestConfig;

public class TestHostAdd {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		// TODO Auto-generated method stub
		
		MonitoringMail mail = new MonitoringMail();
		String messagebody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/AnchoviWebQAAutomationJobs/Extent_20Report/";
        System.out.println(messagebody);
        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,TestConfig.subject,messagebody);
		
	}

}
