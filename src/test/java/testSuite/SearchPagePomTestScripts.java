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
import pageObjectsRepository.RegisterPageObjects;
import pageObjectsRepository.SearchPageObjects;
import testData.TestData;

public class SearchPagePomTestScripts extends Base {
	HomePageObjects homePage;
	RegisterPageObjects registerPage;
	TestData testData;
	SearchPageObjects searchPage;
	CommonPOM comm;

	@BeforeMethod
	public void start() {
		testSetup();
		homePage = new HomePageObjects();
		registerPage = new RegisterPageObjects();
		testData = new TestData();
		searchPage = new SearchPageObjects();
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
	public void searchMissingElement() throws InterruptedException {
		searchPage.searchFiled.sendKeys(testData.searchItemApple);
		searchPage.hpSearchBtn.click();
		searchPage.advancedSearch.click();
		searchPage.searchComNoteDropManu.click();
		searchPage.searchBtnPg.click();
		Thread.sleep(5000);
		comm.verifyByPageSourceNotContains(testData.invaildItemSourcePage);
	}

	@Test
	public void TC_SEARCH_001_CaseInsensitive() {
		searchPage.searchFiled.sendKeys(testData.searchItemNike);
		searchPage.hpSearchBtn.click();
		searchPage.verifyContainsForEachLoop(testData.searchItemNike);

	}

	@Test
	public void VerifyAnErrorMessageShouldDisplayForBlankInput() {
		searchPage.hpSearchBtn.click();
		searchPage.verifyAlertBlankSearch();
	}

	@Test
	public void searchXssVulnerability() {
		searchPage.searchFiled.sendKeys(testData.xssAttack);
		searchPage.hpSearchBtn.sendKeys(Keys.ENTER);
		searchPage.verifySuccessfulBlockOnAttack();
	}

	@Test
	public void searchSqlInjection() {
		searchPage.searchFiled.sendKeys(testData.sqlInjection);
		searchPage.hpSearchBtn.sendKeys(Keys.ENTER);
		searchPage.verifySuccessfulBlockOnAttack();
	}

}
