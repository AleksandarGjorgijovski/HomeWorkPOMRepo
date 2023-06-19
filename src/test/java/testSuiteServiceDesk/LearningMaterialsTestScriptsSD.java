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
import SDRepo.LearningMaterialsRepoSD;
import SDRepo.WorkOfficeRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class LearningMaterialsTestScriptsSD extends Base {
	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	DashboardRepoSD dashBoard;
	WorkOfficeRepoSD workOffice;
	EventRepoSD event;
	CertificateRepoSD certificate;
	LearningMaterialsRepoSD materials;

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
		materials = new LearningMaterialsRepoSD();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		// testTeardown();
	}
	@Test
	public void SD_M_001_ValidExpenseRequestBellow500() throws AWTException, InterruptedException, IOException {
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.learningMaterialsPage();
		
		comm.waitElement(materials.learMaterialsSummaryField);
		materials.learMaterialsSummaryField.sendKeys("Road to cyber security -  Bellow 500");
		materials.learningMaterilasUserName("Aleksandar Gjorgijovski");
		materials.learningMaterialsChoosePractice("QA");
		materials.learMaterialsName.sendKeys("Cyber security ");
		materials.learMaterialsReasonField.sendKeys("for gaining knowledge for project use, and it can be made knowledge transfer in IWEC");
		materials.learMaterialsYesRadioBtn.click();
		materials.learMaterialsTotalCostField.sendKeys("52.12");
		captureFullScreenshot("Learnining Materials bellow 500 Screenshot");
//		materials.learMaterialsCancelBtn.click();	
	}
	
	@Test
	public void SD_M_002_ValidExpenseRequestBellow500() throws AWTException, InterruptedException, IOException {
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.learningMaterialsPage();
		
		comm.waitElement(materials.learMaterialsSummaryField);
		materials.learMaterialsSummaryField.sendKeys("Road to cyber security -  above 500");
		materials.learningMaterilasUserName("Aleksandar Gjorgijovski");
		materials.learningMaterialsChoosePractice("QA");
		materials.learMaterialsName.sendKeys("Cyber security ");
		materials.learMaterialsReasonField.sendKeys("for gaining knowledge for project use, and it can be made knowledge transfer in IWEC");
		materials.learMaterialsYesRadioBtn.click();
		materials.learMaterialsTotalCostField.sendKeys("501");
		captureFullScreenshot("Learnining Materials above 500 Screenshot");
//		materials.learMaterialsCancelBtn.click();	
	}
}
