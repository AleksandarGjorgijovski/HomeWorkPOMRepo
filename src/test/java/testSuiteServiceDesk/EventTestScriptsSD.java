package testSuiteServiceDesk;

import java.awt.AWTException;
import java.io.IOException;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SDRepo.DashboardRepoSD;
import SDRepo.EventRepoSD;
import SDRepo.HomePageRepoSD;
import SDRepo.WorkOfficeRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class EventTestScriptsSD extends Base {
	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	DashboardRepoSD dashBoard;
	WorkOfficeRepoSD workOffice;
	EventRepoSD event;

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageRepoSD();
		testData = new TestData();
		comm = new CommonPOM();
		dashBoard = new DashboardRepoSD();
		workOffice = new WorkOfficeRepoSD();
		event = new EventRepoSD();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		// testTeardown();
	}
	
	@Test
	public void SD_E_001_ValidEventRequest() throws InterruptedException, AWTException, IOException {
		homePage.loginUser(testData.validUser, testData.validPassword);
		dashBoard.requestForConferenceOrEventPage();
		Thread.sleep(10000);
		event.eventEnterSummary("Collabdays Zagreb 2023");
		event.eventChoosePractice("QA");
		event.eventEnterName("Collabdays Zagreb 2023");
		event.eventEnterLocation("Zagreb, Croatia");
		workOffice.ChooseStartDateCalendar(testData.startMonthYear, testData.startDay);
		comm.waitElement(workOffice.workOfficeConfirmFromCalednarBtn);
		workOffice.workOfficeConfirmFromCalednarBtn.click();
		event.eventPresentationChkBtn.click();
		event.eventFlyersChkBtn.click();
		event.eventReasonField.sendKeys("Networking Opportunities");
		event.eventExpectedResultsField.sendKeys("gaining potential customers");
		event.eventNumberPeopleField.sendKeys("2");
		event.eventGoingPeopleSerachBtn.sendKeys("Aleksandar Gjorgijovski");
		Thread.sleep(2000);
		comm.pressEnter();
		event.eventChooseBrachSwitch("IWC");
		event.eventCostDescField.sendKeys("Admission - $20, Transport - $200, Hotel - $200, Diner - $100");
		event.eventTotalCostField.sendKeys("520");
		captureFullScreenshot("Event Screenshot");
		event.eventCancelBtn.click();
		
	}
	
}
