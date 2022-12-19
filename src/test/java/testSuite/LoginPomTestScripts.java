package testSuite;

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

	HomePageObjects hp;
	LoginPageObjects lp;
	TestData td;
	CommonPOM comm;
	RegisterPageObjects rp;

	@BeforeMethod
	public void startTest() {
		testSetup();
		hp = new HomePageObjects();
		lp = new LoginPageObjects();
		td = new TestData();
		comm = new CommonPOM();
		rp = new RegisterPageObjects();
	}

	@AfterMethod
	public void endTest() {
		testTeardown();
	}

	@Test
	public void TC_LOGIN_001_NavigateToLoginPage() {
		hp.navigateLoginPage();
		// za proverka
		// homePage.navigateRegisterPage();
		lp.verifyLoginPageOpened();
		comm.print("Login page is opend");
	}

	@Test
	public void TC_LOGIN_002_ValidEmailAndPassword() {
		hp.navigateLoginPage();
		lp.loginUser(td.validEmail1, td.validPassword);
		lp.lpLoginBtn.click();
		lp.verifySuccessfulLogin();
	}

	@Test
	public void TC_LOGIN_003_ValidEmailAndInvalidPassword() {
		hp.navigateLoginPage();
		lp.loginUser(td.validEmail1, td.invalidPassword);
		lp.lpLoginBtn.click();
		lp.verifyUnsuccessfilLogin();
	}

	@Test
	public void TC_LOGIN_008EmptyEmailAndValidPassword() {
		hp.navigateLoginPage();
		lp.loginUser(td.emptyEmail, td.validPassword);
		lp.lpLoginBtn.click();
		lp.verifyUnsuccessfulLoginEmpltyEmail();
	}

	@Test
	public void TC_LOGIN_009ForgetPasswordValidEmail() {
		hp.navigateLoginPage();
		lp.loginForgetPassword(td.validEmail1);
		lp.verifySuccesfulRecovery();
	}

	@Test
	public void TC_LOGIN_009ForgetPasswordInvalidEmail() {
		// verify that recovery info is not send to unregistered user
		hp.navigateLoginPage();
		lp.loginForgetPassword(td.invalidEmail);
		lp.verifyUnsuccesfulrecovery();
	}

	@Test
	public void verifyUserIsNotLoggedoutWhenPressingBackNavigationButton() {
		hp.hpLoginLink.click();
		lp.loginUserAndLoginBtn(td.validEmail, td.validPassword);
		rp.registerUserIfNotAlreadyRegistered(td.firstName, td.lastName, td.validEmail, td.validPassword,
				td.validConfrimPassword);
		comm.goBack();
		comm.goForward();
		lp.verifySuccessfulLogin();
	}
	@Test
	public void verifyUserIsNotAbleToLoginWithInvalidEmailAndPassword() {
		hp.hpLoginLink.click();
		lp.loginUserAndLoginBtn(td.invalidEmail, td.invalidPassword);
		lp.verifyUnsuccessfilLogin();
	}
}
