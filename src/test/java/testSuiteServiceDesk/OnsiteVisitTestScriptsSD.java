package testSuiteServiceDesk;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SDRepo.DashboardRepoSD;
import SDRepo.HomePageRepoSD;
import SDRepo.OnsiteVisitRepoSD;
import SDRepo.TeamBuildingRepoSD;
import SDRepo.WorkOfficeRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class OnsiteVisitTestScriptsSD extends Base {

	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	DashboardRepoSD dashBoard;
	WorkOfficeRepoSD workOffice;
	TeamBuildingRepoSD teamBuild;
	OnsiteVisitRepoSD onsite;

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageRepoSD();
		testData = new TestData();
		comm = new CommonPOM();
		dashBoard = new DashboardRepoSD();
		workOffice = new WorkOfficeRepoSD();
		teamBuild = new TeamBuildingRepoSD();
		onsite = new OnsiteVisitRepoSD();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		// testTeardown();
	}
	
	@Test
	public void SD_O_001_ValidRequestOnsiteVisit() {
		
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.visitOnsiteVisit();
		
		comm.waitElement(onsite.onsiteSummaryField);
	}
}
