package CalculatorApp;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

    public class TestListenerClass implements ITestListener {
        ExtentSparkReporter sparkReporter;
        ExtentReports extent;
        ExtentTest test;

        ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
        String repName;
        @Override
        public void onStart(ITestContext context) {
            String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HHH.mm.ss").format(new Date());
            repName="Test-Report-"+timeStamp+".html";
            sparkReporter = new ExtentSparkReporter("./reports/"+repName);

            // Set report information
            sparkReporter.config().setDocumentTitle("Calculator Report");
            sparkReporter.config().setReportName("Calculator Automation testing");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Set system information
            extent.setSystemInfo("Application","Calculator");
            extent.setSystemInfo("Testcase","Operations");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Username",System.getProperty("user.name"));
            extent.setSystemInfo("Environment","QA");
        }

        @Override
        public void onTestStart(ITestResult result) {
            test = extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }

        @Override
        public void onTestSuccess(ITestResult result)

        {
            extentTest.get().log(Status.PASS, "Test passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            // System.out.println("Test Method "+result.getName()+" has failed");
            extentTest.get().log(Status.FAIL, "Test failed");

        }


        @Override
        public void onTestSkipped(ITestResult result)
        {
            extentTest.get().log(Status.SKIP, "Test skipped");
            extentTest.get().log(Status.SKIP, result.getThrowable().getMessage());
        }

        @Override
        public void onFinish(ITestContext context)
        {
            extent.flush();
        }
        // Implement other methods as necessary
    }
