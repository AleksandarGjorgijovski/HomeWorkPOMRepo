package testSuite;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.RegisterPageObjects;
import pageObjectsRepository.SearchPageObjects;
import testData.TestData;

public class SearchPagePomTestScripts extends Base {
	HomePageObjects hp;
	RegisterPageObjects rp;
	TestData td;
	SearchPageObjects sp;

	@BeforeMethod
	public void start() {
		testSetup();
		hp = new HomePageObjects();
		rp = new RegisterPageObjects();
		td = new TestData();
		sp = new SearchPageObjects();
	}

	@AfterMethod
	public void end() {
		testTeardown();
	}

	@Test
	public void searchMissingElement() throws InterruptedException {
		sp.searchFiled.sendKeys(td.searchItemApple);
		sp.hpSearchBtn.click();
		sp.advancedSearch.click();
		sp.searchComNoteDropManu.click();
		sp.searchBtnPg.click();
		Thread.sleep(5000);
		sp.verifyByPageSource(td.invaildItemSorcePage);
	}

	@Test
	public void TC_SEARCH_001_CaseInsensitive() {
		sp.searchFiled.sendKeys(td.searchItemNike);
		sp.hpSearchBtn.click();
		sp.verifyContainsForEachLoop(td.searchItemNike);

	}

	@Test
	public void VerifyAnErrorMessageShouldDisplayForBlankInput() {
		sp.hpSearchBtn.click();
		sp.verifyAlertBlankSearch();
	}

	@Test
	public void searchXssVulnerability() {
		sp.searchFiled.sendKeys(td.xssAttack);
		sp.hpSearchBtn.sendKeys(Keys.ENTER);
		sp.verifySuccessfulBlockOnAttack();
	}

	@Test
	public void searchSqlInjection() {
		sp.searchFiled.sendKeys(td.sqlInjection);
		sp.hpSearchBtn.sendKeys(Keys.ENTER);
		sp.verifySuccessfulBlockOnAttack();
	}

}
