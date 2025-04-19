package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener

{

	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populate common information of the report like browser name, executor name
	public ExtentTest test; //creating test case entries in the report and update status of the methods like failed and passed
	
	String repName;
	
	public void onStart(ITestContext testcontext) {
		
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt= new Date();
		String currentdatetimestamp=df.format(dt); */
		
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repName= "Test-Report-" + timeStamp + ".html";		
		sparkReporter = new ExtentSparkReporter (".\\reports\\" + repName); //Specific location of the report
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //Title of the report
		sparkReporter.config().setReportName("Opencart Functional Testing"); //Name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Opearting System", os);
		
		String browser= testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser name", browser);
		
		List<String> includedGroups =testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
			
		}		
	}

	public void onTestSuccess(ITestResult result) {
		

		test =extent.createTest(result.getTestClass().getName());// create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS,result.getName()+ "got successfully executed"); //update status 
		
	}
	
	public void onTestFailure(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.FAIL,result.getName()+ "got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
	
}
	
	public void onTestSkipped (ITestResult result)
	{
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+ "got Skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+ repName ; //Directly open the report
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); //This method can open the report in browser automatically			
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		 try {
		 
			
		
		URL url =new URL("file:///" +System.getProperty("user.dir")+"\\reports\\"+ repName);
		//Create the email message
		
		ImageHtmlEmail email= new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("sureshpatel896198@gmail.com", "frqqpchihgriowei"));
		email.setStartTLSEnabled(true);
		email.setFrom("sureshpatel896198@gmail.com"); //Sender
		email.setSubject("Test Results");
		email.setMsg("Please find attached report....");
		email.addTo("pateljasmin8578.jp@gmail.com"); //Receiver
		email.attach(url, "extent report", "please check report.....");
		email.send(); //send the email
	}
	catch (Exception e)
	{
		e.printStackTrace(); 
	} 
	
	
		
		
		
		
		
	}
}
	

