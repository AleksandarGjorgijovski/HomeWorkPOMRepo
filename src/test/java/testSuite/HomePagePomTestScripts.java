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

public class HomePagePomTestScripts extends Base {
	HomePageObjects hp;
	LoginPageObjects lp;
	TestData td;
	RegisterPageObjects rp;
	CommonPOM comm;

	@BeforeMethod
	public void start() {
		testSetup();
		hp = new HomePageObjects();
		lp = new LoginPageObjects();
		td = new TestData();
		rp = new RegisterPageObjects();
		comm = new CommonPOM();
	}

	@AfterMethod
	public void end() {
		testTeardown();
	}

	@Test
	public void VerifyVoteByScrollingToElement() {
		hp.navigateLoginPage();
		comm.scrollTo(lp.lpLoginBtn);
		lp.loginUser(td.validEmail1, td.validPassword);
		lp.lpLoginBtn.click();
		comm.scrollTo(hp.hpVoteBtn);
		hp.hpVoteExellentRadioBtn.click();
		hp.hpVoteBtn.click();
		comm.waitElement(hp.hpLogoutLink);
		hp.verifyVoteIsSuccessful();
	}

	@Test
	public void VerifyVoteByScrollingToElementWithRandomUserGenerated() {
		rp.registerNewUserWithRandomEmail(td.firstName, td.lastName, td.validPassword, td.validConfrimPassword);
		comm.scrollTo(hp.hpVoteBtn);
		hp.hpVoteExellentRadioBtn.click();
		hp.hpVoteBtn.click();
		comm.waitElement(hp.hpLogoutLink);
		comm.waitElement(hp.hpPollResults);
		hp.verifyVoteIsSuccessful();
	}

}