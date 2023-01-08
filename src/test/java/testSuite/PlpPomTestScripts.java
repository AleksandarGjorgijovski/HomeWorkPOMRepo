package testSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.ComparelistPageObjects;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.SearchPageObjects;
import testData.TestData;

public class PlpPomTestScripts extends Base {
	HomePageObjects homepage;
	SearchPageObjects searchpage;
	TestData testdata;
	PlpObjects plp;
	ComparelistPageObjects compareListPage;
	CommonPOM comm;
	
	@BeforeMethod
	public void start() {
		testSetup();
		homepage = new HomePageObjects();
		searchpage = new SearchPageObjects();
		testdata = new TestData();
		plp = new PlpObjects();
		compareListPage = new ComparelistPageObjects();
		comm = new CommonPOM();
	}
	@AfterMethod
	public void end() {
		testTeardown();
	}
	@Test
	public void TC_PRODUCT_016_CheckIfBreadcrumbsMenuNavigateToCorespondingPage() {
		homepage.hpComputersBanner.click();
		plp.plpSoftwareSubCategory.click();
		plp.plpWindows8.click();
		plp.verifyWindows8PageisDisplayed();
		plp.plpComputerBreadcrumbs.click();
		plp.verifyComputerPageisDisplayed();
		plp.plpHomeBreadcrumbs.click();
		plp.verifyHomePageisDisplayed();
	}
	@Test
	public void TC_PRODUCT_011_VerifyClearListOnComperasonPage(){
		comm.mouseOverAndClickAction(homepage.hpElectronicsBanner, plp.plpCellPhones);
		comm.waitElement(plp.plpCompareHtcOne);
		plp.plpCompareHtcOne.click();
		comm.waitElement(plp.plpCompareNokia);
		plp.plpCompareNokia.click();
		plp.plpCompareProductList.click();
		compareListPage.waitClearCompareList();
		compareListPage.compListClearListBtn.click();
		compareListPage.verifyEmptyCompareListPage();
	}
	@Test
	public void TC_PRODUCT_002_VerifyFilterOptionIsWorking() {
		comm.waitElement(homepage.hpComputersBanner);
		comm.mouseOverAndClickAction(homepage.hpComputersBanner, plp.plpNotebooksSubCategory);
		comm.waitElement(plp.plpFilterByIntelI5ChBox);
		plp.plpFilterByIntelI5ChBox.click();
		comm.waitElement(plp.plpFilterByMemory8GB);
		plp.plpFilterByMemory8GB.click();
		comm.waitElement(plp.listViewbtn);
		plp.listViewbtn.click();
		comm.waitElement(plp.plpItemDescription);
		plp.verifyWithForLoopDescription(testdata.filterItemByCpu5, testdata.filterByMemory8);
	}
	@Test
	public void TC_PRODUCT_002_VerifyFilterOptionIsWorkingForEachLoop() {
		comm.mouseOverAndClickAction(homepage.hpComputersBanner, plp.plpNotebooksSubCategory);
		plp.plpFilterByIntelI5ChBox.click();
		plp.plpFilterByMemory8GB.click();
		plp.listViewbtn.click();
		comm.waitElement(plp.plpItemDescription);
		plp.verifyWithForEachLoopDescription(testdata.filterItemByCpu5, testdata.filterByMemory8);
	}
	
}
