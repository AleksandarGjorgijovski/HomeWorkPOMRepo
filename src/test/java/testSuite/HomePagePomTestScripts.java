package testSuite;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.LoginPageObjects;
import pageObjectsRepository.RegisterPageObjects;
import testData.TestData;

public class HomePagePomTestScripts extends Base {
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	TestData testData;
	RegisterPageObjects registerPage;
	CommonPOM comm;

	@BeforeMethod
	public void start() {
		testSetup();
		homePage = new HomePageObjects();
		loginPage = new LoginPageObjects();
		testData = new TestData();
		registerPage = new RegisterPageObjects();
		comm = new CommonPOM();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		testTeardown();
	}

	@Test
	public void VerifyVoteByScrollingToElement() {
		homePage.navigateLoginPage();
		comm.scrollTo(loginPage.lpLoginBtn);
		loginPage.loginUser(testData.validEmail1, testData.validPassword);
		loginPage.lpLoginBtn.click();
		comm.scrollTo(homePage.hpVoteBtn);
		homePage.hpVoteExellentRadioBtn.click();
		homePage.hpVoteBtn.click();
		comm.waitElement(homePage.hpLogoutLink);
		homePage.verifyVoteIsSuccessful();
	}

	@Test
	public void VerifyVoteByScrollingToElementWithRandomUserGenerated() {
		registerPage.registerNewUserWithRandomEmail(testData.firstName, testData.lastName, testData.validPassword,
				testData.validConfrimPassword);
		comm.scrollTo(homePage.hpVoteBtn);
		homePage.hpVoteExellentRadioBtn.click();
		homePage.hpVoteBtn.click();
		comm.waitElement(homePage.hpLogoutLink);
		comm.waitElement(homePage.hpPollResults);
		homePage.verifyVoteIsSuccessful();
	}

}