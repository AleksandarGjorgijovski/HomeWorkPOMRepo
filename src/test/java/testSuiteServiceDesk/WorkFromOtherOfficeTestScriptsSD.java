package testSuiteServiceDesk;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SDRepo.DashboardRepoSD;
import SDRepo.HomePageRepoSD;
import SDRepo.WorkOfficeRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class WorkFromOtherOfficeTestScriptsSD extends Base {

	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	DashboardRepoSD dashBoard;
	WorkOfficeRepoSD workOffice;

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageRepoSD();
		testData = new TestData();
		comm = new CommonPOM();
		dashBoard = new DashboardRepoSD();
		workOffice = new WorkOfficeRepoSD();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		// testTeardown();
	}

	@Test
	public void SD_W_001_GoToWorkFromOtherOfficePage() {
		homePage.loginUser(testData.validUser, testData.validPassword);
		comm.waitElement(dashBoard.dashboardSponsorshipMenu);
		dashBoard.dashboardSponsorshipMenu.click();
		comm.waitElement(dashBoard.dashboardWorkFromOtherOfficeLink);
		dashBoard.workFromOtherOfficePage();
	}

	@Test
	public void SD_W_002_WorkFromOtherOfficePage() throws InterruptedException, IOException {
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.workFromOtherOfficePage();

		Thread.sleep(5000);
		workOffice.ChooseStartDateCalendar(testData.startMonthYear, testData.startDay);
		comm.waitElement(workOffice.workOfficeConfirmFromCalednarBtn);
		workOffice.workOfficeConfirmFromCalednarBtn.click();

		workOffice.ChooseEndDateCalendar(testData.endMonthYear, testData.endDay);
		comm.waitElement(workOffice.workOfficeConfirmToCalendarBtn);
		workOffice.workOfficeConfirmToCalendarBtn.click();

		workOffice.workOfficeChooseBranch("Prilep");
		workOffice.workOfficeNoHotel();
		workOffice.workOfficeBusinessTrip();
		workOffice.workOfficeExpenses("fuel 2000, Toll 360, Total 2600");
		workOffice.workOfficePartnersCoverBranch();
		workOffice.workOfficeReason("team sync");
		captureFullScreenshot("Work from other Office Business screenshot");
		workOffice.workOfficeCancel();
	}

	@Test
	public void SD_W_003_ChooseBranchSwitch() throws InterruptedException, IOException {
		homePage.loginUser(testData.validUser, testData.validPassword);
		comm.waitElement(dashBoard.dashboardSponsorshipMenu);
		dashBoard.dashboardSponsorshipMenu.click();
		comm.waitElement(dashBoard.dashboardWorkFromOtherOfficeLink);
		dashBoard.workFromOtherOfficePage();

		workOffice.ChooseStartDateCalendar(testData.startMonthYear, testData.startDay);
		comm.waitElement(workOffice.workOfficeConfirmFromCalednarBtn);
		workOffice.workOfficeConfirmFromCalednarBtn.click();

		workOffice.ChooseEndDateCalendar(testData.endMonthYear, testData.endDay);
		comm.waitElement(workOffice.workOfficeConfirmToCalendarBtn);
		workOffice.workOfficeConfirmToCalendarBtn.click();

		workOffice.workOfficeChooseBranch("Prilep");
		workOffice.workOfficeNoHotel();
		workOffice.workOfficeBusinessTrip();
		workOffice.workOfficeExpenses("fuel 2000, Toll 360, Total 2600");
		workOffice.workOfficeChooseBrachSwitch("IWC");
		workOffice.workOfficeReason("team sync");
		captureFullScreenshot("Work from other Office Business screenshot");
		workOffice.workOfficeCancel();
	}
}
