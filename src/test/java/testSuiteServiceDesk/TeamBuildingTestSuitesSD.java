package testSuiteServiceDesk;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SDRepo.DashboardRepoSD;
import SDRepo.HomePageRepoSD;
import SDRepo.TeamBuildingRepoSD;
import SDRepo.WorkOfficeRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class TeamBuildingTestSuitesSD extends Base {
	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	DashboardRepoSD dashBoard;
	WorkOfficeRepoSD workOffice;
	TeamBuildingRepoSD teamBuild;

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageRepoSD();
		testData = new TestData();
		comm = new CommonPOM();
		dashBoard = new DashboardRepoSD();
		workOffice = new WorkOfficeRepoSD();
		teamBuild = new TeamBuildingRepoSD();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		// testTeardown();
	}

	@Test
	public void SD_T_001_ValidRequestTeamBuilding() throws InterruptedException, IOException {
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.teamBuidlingPage();
		
		comm.waitElement(teamBuild.teamBuildPracticeDropdown);
		teamBuild.teamBuildChoosePractice("QA");
		teamBuild.teamBuildCurrentEmployedField.sendKeys("81");
		teamBuild.teamBuildOtherMembersField.sendKeys("comm.waitElement(workOffice.workOfficeConfirmFromCalednarBtn);\r\n"
				+ "		workOffice.workOfficeConfirmFromCalednarBtn.click();");
		teamBuild.teamBuildBudgetPerPersonField.sendKeys("1500mkd");
		teamBuild.teamBuildWhyField.sendKeys("to improve team sync, teamwork, and collaboration, enhance morale and motivation, Identifying and utilize individual strengths");
		teamBuild.teamBuildWhatField.sendKeys("Team work games");
		comm.waitElement(workOffice.workOfficeFromCalednarBtn);
		workOffice.ChooseStartDateCalendar(testData.startMonthYear, testData.startDay);
		comm.waitElement(workOffice.workOfficeConfirmFromCalednarBtn);
		workOffice.workOfficeConfirmFromCalednarBtn.click();
		teamBuild.teamBuildWhereField.sendKeys("Ohird");
		teamBuild.teamBuildCostField.sendKeys("Hot- 75000mkd\r\n"
				+ "Restaurant - 60000mkd\r\n"
				+ "Fuel - 500mkd per person");
		teamBuild.teamBuildTotalCostField.sendKeys("2548");
		teamBuild.teamBuildChooseRiskLevel("Low");
		captureFullScreenshot("Team Building Screenshot");
//		teamBuild.teamBuildCancelBtn.click();
	}
}
