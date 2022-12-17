package testSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.LoginPageObjects;
import testData.TestData;

public class LoginPomTestScripts extends Base {
	
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	TestData userTestData;
	CommonPOM comm;
	
	@BeforeMethod
	public void startTest() {
		testSetup();
		homePage = new HomePageObjects();
		loginPage = new LoginPageObjects();
		userTestData = new TestData();
		comm = new CommonPOM();
	}
	@AfterMethod
	public void endTest() {
		testTeardown();
	}
	@Test	
	public void TC_LOGIN_001_NavigateToLoginPage() {
	homePage.navigateLoginPage();
	//za proverka
	//homePage.navigateRegisterPage();
	loginPage.verifyLoginPageOpened();
	comm.print("Login page is opend");
	}
	@Test
	public void TC_LOGIN_002_ValidEmailAndPassword() {
		homePage.navigateLoginPage();
		loginPage.loginUser(userTestData.validEmail1, userTestData.validPassword);
		loginPage.lpLoginBtn.click();
		loginPage.verifySuccessfulLogin();
	}
	@Test
	public void TC_LOGIN_003_ValidEmailAndInvalidPassword() {
		homePage.navigateLoginPage();
		loginPage.loginUser(userTestData.validEmail1, userTestData.invalidPassword);
		loginPage.lpLoginBtn.click();
		loginPage.verifyUnsuccessfilLogin();	
	}
	@Test
	public void TC_LOGIN_008EmptyEmailAndValidPassword() {
		homePage.navigateLoginPage();
		loginPage.loginUser(userTestData.emptyEmail, userTestData.validPassword);
		loginPage.lpLoginBtn.click();
		loginPage.verifyUnsuccessfulLoginEmpltyEmail();
	}
	@Test
	public void TC_LOGIN_009ForgetPasswordValidEmail() {
		homePage.navigateLoginPage();
		loginPage.loginForgetPassword(userTestData.validEmail1);
		loginPage.verifySuccesfulRecovery();
	} 
	@Test
	public void TC_LOGIN_009ForgetPasswordInvalidEmail() {
		//verify that recovery info is not send to unregistered user
		homePage.navigateLoginPage();
		loginPage.loginForgetPassword(userTestData.invalidEmail);
		loginPage.verifyUnsuccesfulrecovery();
	} 
}























