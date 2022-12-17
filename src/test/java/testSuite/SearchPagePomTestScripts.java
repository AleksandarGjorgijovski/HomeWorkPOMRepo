package testSuite;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.RegisterPageObjects;
import pageObjectsRepository.SearchPageObjects;
import testData.TestData;

public class SearchPagePomTestScripts extends Base {
	HomePageObjects homePage;
	RegisterPageObjects registerPage;
	TestData userTestData;
	SearchPageObjects searchPage;
	
	@BeforeMethod
	public void start() {
		//se zema metodot od Base za start na testiranje
		testSetup();
		// new Objects od site klasi sto ke se koristi vo ovaa klasa
		homePage = new HomePageObjects();
		registerPage = new RegisterPageObjects();
		userTestData = new TestData();
		searchPage = new SearchPageObjects();
	}
	@AfterMethod
	public void end() throws InterruptedException {
		Thread.sleep(3000);
		//driver.quit();
}
	
	@Test
	public void searchMissingElement() throws InterruptedException {
		searchPage.searchFiled.sendKeys(userTestData.searchItemApple);
		searchPage.hpSearchBtn.click();
		searchPage.advancedSearch.click();
		searchPage.searchComNoteDropManu.click();
		searchPage.searchBtnPg.click();
		Thread.sleep(5000);
		searchPage.verifyByPageSource(userTestData.invaildItemSorcePage);
	}
	@Test
	public void TC_SEARCH_001_CaseInsensitive() {
		searchPage.searchFiled.sendKeys(userTestData.searchItemNike);
		searchPage.hpSearchBtn.click();
		searchPage.verifyContainsForEachLoop(userTestData.searchItemNike);	
		
}
	

}
