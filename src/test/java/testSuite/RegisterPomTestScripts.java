package testSuite;


import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.RegisterPageObjects;
import testData.TestData;

public class RegisterPomTestScripts extends Base {
	// se definiraat iminjata na Objectite sto ke se koristet za klasite
	HomePageObjects hp;
	RegisterPageObjects rp;
	TestData td;
	CommonPOM comm;

	@BeforeMethod
	public void start() {
		// se zema metodot od Base za start na testiranje
		testSetup();
		// new Objects od site klasi sto ke se koristi vo ovaa klasa
		hp = new HomePageObjects();
		rp = new RegisterPageObjects();
		td = new TestData();
		comm = new CommonPOM();
	}

	@AfterMethod
	public void end() {
		testTeardown();
	}

	@Test
	public void TC_REGISTER_002_RegisterUseOnlyMendatoryFields() {
		hp.hpRegisterLink.click();
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail1, td.validPassword,td.validConfrimPassword);
		rp.registerBtn.click();
		rp.regContinueBtn.click();
		rp.verifySuccessfulRegister();
	}

	@Test
	public void TC_REGISTER_002_1_RegisterUseAllFields() {
		hp.navigateRegisterPage();
		rp.registerUserOtherFields(td.companyName);
		comm.selectFromDropManu(rp.regDayManu, td.validDay);
		comm.selectFromDropManu(rp.regMonthManu, td.validMonth);
		comm.selectFromDropManu(rp.regYearManu, td.validYear);
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail, td.validPassword,
				td.validConfrimPassword);
		rp.registerBtn.click();
		rp.regContinueBtn.click();
		rp.verifySuccessfulRegister();
	}

	@Test
	public void TC_REGISTER_004_RegisterNewUserWithExistingEmail() {
		hp.navigateRegisterPage();
		rp.registerUserMandatoryFields(td.firstName, td.lastName, td.validEmail4, td.validPassword,
				td.validConfrimPassword);
		rp.registerBtn.click();
		rp.verifyUnuccessfulRegisterExistingEmail();
	}
	@Test
	public void RegisterNewRandomUser() {
		hp.hpRegisterLink.click();
		rp.regFirstNameField.sendKeys(td.firstName);
		rp.regLastNameField.sendKeys(td.lastName);
		rp.randomGeneratorEmail(td.firstName);
		rp.regPasswordField.sendKeys(td.validPassword);
		rp.regConfirmPasswordField.sendKeys(td.validConfrimPassword);
		rp.registerBtn.sendKeys(Keys.ENTER);
		rp.regContinueBtn.sendKeys(Keys.ENTER);
		rp.verifySuccessfulRegister();
	}

}















