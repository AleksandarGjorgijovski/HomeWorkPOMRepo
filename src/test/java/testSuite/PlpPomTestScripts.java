package testSuite;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.ComparelistPageObjects;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.PlpObjects;
import testData.TestData;

public class PlpPomTestScripts extends Base {
	HomePageObjects homePage;
	TestData testData;
	PlpObjects plp;
	ComparelistPageObjects compareListPage;
	CommonPOM comm;

	@BeforeMethod
	public void start() {
		testSetup();
		homePage = new HomePageObjects();
		testData = new TestData();
		plp = new PlpObjects();
		compareListPage = new ComparelistPageObjects();
		comm = new CommonPOM();
	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		testTeardown();
	}

	@Test
	public void TC_PRODUCT_016_CheckIfBreadcrumbsMenuNavigateToCorespondingPage() {
		homePage.hpComputersBanner.click();
		plp.plpSoftwareSubCategory.click();
		plp.plpWindows8.click();
		plp.verifyWindows8PageisDisplayed();
		plp.plpComputerBreadcrumbs.click();
		plp.verifyComputerPageisDisplayed();
		plp.plpHomeBreadcrumbs.click();
		plp.verifyHomePageisDisplayed();
	}

	@Test
	public void TC_PRODUCT_011_VerifyClearListOnComperasonPage() {
		comm.mouseOverAndClickAction(homePage.hpElectronicsBanner, plp.plpCellPhones);
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
		comm.waitElement(homePage.hpComputersBanner);
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, plp.plpNotebooksSubCategory);
		comm.waitElement(plp.plpFilterByIntelI5ChBox);
		plp.plpFilterByIntelI5ChBox.click();
		comm.waitElement(plp.plpFilterByMemory8GB);
		plp.plpFilterByMemory8GB.click();
		comm.waitElement(plp.listViewbtn);
		plp.listViewbtn.click();
		comm.waitElement(plp.plpItemDescription);
		plp.verifyWithForLoopDescription(testData.filterItemByCpu5, testData.filterByMemory8);
	}

	@Test
	public void TC_PRODUCT_002_VerifyFilterOptionIsWorkingForEachLoop() {
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, plp.plpNotebooksSubCategory);
		plp.plpFilterByIntelI5ChBox.click();
		plp.plpFilterByMemory8GB.click();
		plp.listViewbtn.click();
		comm.waitElement(plp.plpItemDescription);
		plp.verifyWithForEachLoopDescription(testData.filterItemByCpu5, testData.filterByMemory8);
	}

}
