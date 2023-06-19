package testSuiteServiceDesk;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SDRepo.HomePageRepoSD;
import base.Base;
import base.CommonPOM;
import testData.TestData;

public class LoginPomTestScriptsSD extends Base {

	HomePageRepoSD homePage;
	TestData testData;
	CommonPOM comm;
	

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageRepoSD();
		testData = new TestData();
		comm = new CommonPOM();
		}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException{
		if(ITestResult.FAILURE==result.getStatus())
		{
			captureScreenshotURL(result.getName());
		}
		testTeardown();
	}

	@Test
	public void TC_LOGIN_001_SuccessfulLogin() {
		homePage.loginUser(testData.validUser, testData.validPassword);
		comm.waitElement(homePage.logInWelcomeText);
		homePage.verifySuccessfulLogin();
	}

		
}
