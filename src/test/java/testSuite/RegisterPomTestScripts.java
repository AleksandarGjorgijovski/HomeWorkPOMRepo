package testSuite;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.Keys;
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

public class RegisterPomTestScripts extends Base {
	HomePageObjects homePage;
	RegisterPageObjects registerPage;
	TestData testData;
	CommonPOM comm;
	LoginPageObjects loginPage;

	@BeforeMethod
	public void start() {
		testSetup();
		homePage = new HomePageObjects();
		registerPage = new RegisterPageObjects();
		testData = new TestData();
		comm = new CommonPOM();
		loginPage = new LoginPageObjects();
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
	public void TC_REGISTER_002_RegisterUseOnlyMendatoryFields() {
		homePage.hpRegisterLink.click();
		registerPage.registerUserMandatoryFields(testData.firstName, testData.lastName, testData.validEmail1,
				testData.validPassword, testData.validConfrimPassword);
		registerPage.registerBtn.click();
		registerPage.regContinueBtn.click();
		registerPage.verifySuccessfulRegister();
	}

	@Test
	public void TC_REGISTER_002_1_RegisterUseAllFields() {
		homePage.navigateRegisterPage();
		registerPage.registerUserOtherFields(testData.companyName);
		comm.selectFromDropManu(registerPage.regDayManu, testData.validDay);
		comm.selectFromDropManu(registerPage.regMonthManu, testData.validMonth);
		comm.selectFromDropManu(registerPage.regYearManu, testData.validYear);
		registerPage.registerUserMandatoryFields(testData.firstName, testData.lastName, testData.validEmail,
				testData.validPassword, testData.validConfrimPassword);
		registerPage.registerBtn.click();
		registerPage.regContinueBtn.click();
		registerPage.verifySuccessfulRegister();
	}

	@Test
	public void TC_REGISTER_004_RegisterNewUserWithExistingEmail() {
		homePage.navigateLoginPage();
		loginPage.loginUserAndLoginBtn(testData.validEmail1, testData.validPassword);
		registerPage.registerUserIfNotAlreadyRegisteredFromHomePageAndLogout(testData.firstName, testData.lastName,
				testData.validEmail1, testData.validPassword, testData.validConfrimPassword);
		homePage.navigateRegisterPage();
		registerPage.registerUserMandatoryFields(testData.firstName, testData.lastName, testData.validEmail1,
				testData.validPassword, testData.validConfrimPassword);
		registerPage.registerBtn.click();
		registerPage.verifyUnuccessfulRegisterExistingEmail();

	}

	@Test
	public void RegisterNewRandomUser() {
		homePage.navigateRegisterPage();
		registerPage.regFirstNameField.sendKeys(testData.firstName);
		registerPage.regLastNameField.sendKeys(testData.lastName);
		registerPage.randomGeneratorEmail(testData.firstName);
		registerPage.regPasswordField.sendKeys(testData.validPassword);
		registerPage.regConfirmPasswordField.sendKeys(testData.validConfrimPassword);
		registerPage.registerBtn.sendKeys(Keys.ENTER);
		registerPage.regContinueBtn.sendKeys(Keys.ENTER);
		registerPage.verifySuccessfulRegister();
	}

	@Test
	public void VerifyUnsuccesfulRegisterByNotEnteringDataInAllFields() {
		homePage.navigateRegisterPage();
		registerPage.registerBtn.sendKeys(Keys.ENTER);
		registerPage.verifyUnuccessfulRegister();
	}

	@Test
	public void VerifyUnsiccesfulRegisterByNotProvidingConfirmPassword() {
		homePage.navigateRegisterPage();
		registerPage.registerUserMandatoryFields(testData.firstName, testData.lastName, testData.validEmail,
				testData.validPassword, testData.emptyConfirmPassword);
		registerPage.verifyUnuccessfulRegister();

	}

	@Test
	public void VerifyUnsuccesfulRegisterInvalidEmail() {
		homePage.navigateRegisterPage();
		registerPage.registerUserMandatoryFields(testData.firstName, testData.lastName, testData.invalidEmail,
				testData.validPassword, testData.validConfrimPassword);
	}

}
