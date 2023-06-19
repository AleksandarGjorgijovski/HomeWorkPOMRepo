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

public class LoginPomTestScripts extends Base {

	HomePageObjects homePage;
	LoginPageObjects loginPage;
	TestData testData;
	CommonPOM comm;
	RegisterPageObjects registerPage;

	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageObjects();
		loginPage = new LoginPageObjects();
		testData = new TestData();
		comm = new CommonPOM();
		registerPage = new RegisterPageObjects();
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
	public void TC_LOGIN_001_NavigateToLoginPage() {
		homePage.navigateLoginPage();
		loginPage.verifyLoginPageOpened();
		comm.print("Login page is opend");
	}

	@Test
	public void TC_LOGIN_002_ValidEmailAndPassword() {
		homePage.navigateLoginPage();
		loginPage.loginUser(testData.validEmail1, testData.validPassword);
		loginPage.lpLoginBtn.click();
		loginPage.verifySuccessfulLogin();
	}

	@Test
	public void TC_LOGIN_003_ValidEmailAndInvalidPassword() {
		homePage.navigateLoginPage();
		loginPage.loginUser(testData.validEmail1, testData.invalidPassword);
		loginPage.lpLoginBtn.click();
		loginPage.verifyUnsuccessfilLogin();
	}


	@Test
	public void TC_LOGIN_008EmptyEmailAndValidPassword() {
		homePage.navigateLoginPage();
		loginPage.loginUser(testData.emptyEmail, testData.validPassword);
		loginPage.lpLoginBtn.click();
		loginPage.verifyUnsuccessfulLoginEmpltyEmail();
	}

	@Test
	public void TC_LOGIN_009ForgetPasswordValidEmail() {
		homePage.navigateLoginPage();
		loginPage.loginForgetPassword(testData.validEmail1);
		loginPage.verifySuccesfulRecovery();
	}

	@Test
	public void TC_LOGIN_009ForgetPasswordInvalidEmail() {
		// verify that recovery info is not send to unregistered user
		homePage.navigateLoginPage();
		loginPage.loginForgetPassword(testData.invalidEmail);
		loginPage.verifyUnsuccesfulrecovery();
	}

	@Test
	public void verifyUserIsNotLoggedoutWhenPressingBackNavigationButton() {
		homePage.hpLoginLink.click();
		loginPage.loginUserAndLoginBtn(testData.validEmail, testData.validPassword);
		registerPage.registerUserIfNotAlreadyRegistered(testData.firstName, testData.lastName, testData.validEmail, testData.validPassword,
				testData.validConfrimPassword);
		comm.goBack();
		comm.goForward();
		loginPage.verifySuccessfulLogin();
	}
	@Test
	public void verifyUserIsNotAbleToLoginWithInvalidEmailAndPassword() {
		homePage.hpLoginLink.click();
		loginPage.loginUserAndLoginBtn(testData.invalidEmail, testData.invalidPassword);
		loginPage.verifyUnsuccessfilLogin();
	}
}
