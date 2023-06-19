package testSuiteServiceDesk;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SDRepo.CertificateRepoSD;
import SDRepo.DashboardRepoSD;
import SDRepo.EventRepoSD;
import SDRepo.HomePageRepoSD;
import SDRepo.WorkOfficeRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class CertificateTestScriptsSD extends Base {

	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	DashboardRepoSD dashBoard;
	WorkOfficeRepoSD workOffice;
	EventRepoSD event;
	CertificateRepoSD certificate;

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageRepoSD();
		testData = new TestData();
		comm = new CommonPOM();
		dashBoard = new DashboardRepoSD();
		workOffice = new WorkOfficeRepoSD();
		event = new EventRepoSD();
		certificate = new CertificateRepoSD();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		// testTeardown();
	}
	
	@Test
	public void SD_C_001_ValidExpensesRequestBellow_500() throws AWTException, InterruptedException, IOException {
		
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.certificatePage();
		comm.waitElement(certificate.certificateSummaryField);
		certificate.certificateSummaryField.sendKeys("Selenium Automation: a complete package Udemy - bellow 500");
		certificate.certificateUserName("Aleksandar Gjorgijovski");
		certificate.certificateChoosePractice("QA");
		certificate.certificateName.sendKeys("Selenium Automation");
		certificate.certificateReasonField.sendKeys("for gaining knowledge for project use, and it can be made knowledge transfer in IWEC");
		certificate.certificateTotalCostField.sendKeys("54.99");
		captureFullScreenshot("Certificate bellow 500 Screenshot");
		certificate.certificateCancelBtn.click();		
		
	}
	@Test
	public void SD_C_002_ValidExpenseRequestabove500() throws AWTException, InterruptedException, IOException {
		
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.certificatePage();
		comm.waitElement(certificate.certificateSummaryField);
		certificate.certificateSummaryField.sendKeys("CompTIA A+ certification above 500");
		certificate.certificateUserName("Aleksandar Gjorgijovski");
		certificate.certificateChoosePractice("QA");
		certificate.certificateName.sendKeys("CompTIA A+");
		certificate.certificateReasonField.sendKeys("Taking this course provides several benefits, including gaining knowledge that can be directly applied to projects. Additionally, the course creates opportunities for knowledge transfer within the IWEC");
		certificate.certificateTotalCostField.sendKeys("720");
		captureFullScreenshot("Certificate above 500 Screenshot");
		certificate.certificateCancelBtn.click();		
		
	}
}
