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
		homePage.hpSearchField.sendKeys(testData.searchItemApple);
		homePage.hpSearchBtn.click();
		searchPage.advancedSearch.click();
		comm.selectFromDropManu(searchPage.searchCategoryDropManu, testData.valueSearchComputersNotebooks);
		searchPage.searchBtnPg.click();
		Thread.sleep(5000);
		comm.verifyByPageSourceNotContains(testData.invaildItemSourcePage);
	}

	@Test
	public void TC_SEARCH_001_CaseInsensitive() {
		homePage.hpSearchField.sendKeys(testData.searchItemNike);
		homePage.hpSearchBtn.click();
		searchPage.verifyContainsForEachLoop(testData.searchItemNike);

	}

	@Test
	public void VerifyAnErrorMessageShouldDisplayForBlankSearch() {
		homePage.hpSearchBtn.click();
		searchPage.verifyAlertBlankSearch();
	}

	@Test
	public void searchXssVulnerability() {
		homePage.hpSearchField.sendKeys(testData.xssAttack);
		homePage.hpSearchBtn.sendKeys(Keys.ENTER);
		searchPage.verifySuccessfulBlockOnAttack();
	}

	@Test
	public void searchSqlInjection() {
		homePage.hpSearchField.sendKeys(testData.sqlInjection);
		homePage.hpSearchBtn.sendKeys(Keys.ENTER);
		searchPage.verifySuccessfulBlockOnAttack();
	}
	@Test
	public void VerifyUnsuccessfulSearchSuggestionWithTwoCharacters() {
		homePage.hpSearchField.sendKeys(testData.invalidSearchData);
		homePage.hpSearchBtn.click();
		searchPage.verifyErrorMsgMinimumSearchCharacters();
		
	}

}
