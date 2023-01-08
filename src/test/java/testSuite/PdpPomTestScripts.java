package testSuite;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.CheckoutPageObjects;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.LoginPageObjects;
import pageObjectsRepository.PdpObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.RegisterPageObjects;
import pageObjectsRepository.SearchPageObjects;
import pageObjectsRepository.ShoppingCartPageObjects;
import pageObjectsRepository.WishlistPageObjects;
import testData.TestData;

public class PdpPomTestScripts extends Base {
	TestData testData;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	PlpObjects plp;
	RegisterPageObjects registerPage;
	SearchPageObjects searchPage;
	ShoppingCartPageObjects shoppingCartPage;
	WishlistPageObjects wishlistPage;
	PdpObjects pdp;
	CommonPOM comm;
	CheckoutPageObjects checkoutPage;

	@BeforeMethod
	public void start() {
		testSetup();
		homePage = new HomePageObjects();
		loginPage = new LoginPageObjects();
		plp = new PlpObjects();
		registerPage = new RegisterPageObjects();
		searchPage = new SearchPageObjects();
		shoppingCartPage = new ShoppingCartPageObjects();
		wishlistPage = new WishlistPageObjects();
		testData = new TestData();
		pdp = new PdpObjects();
		comm = new CommonPOM();
		checkoutPage = new CheckoutPageObjects();

	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		testTeardown();
	}

	@Test(enabled = true)
	public void TC_DETAILS_013_VerifyDonwloadSample() throws InterruptedException {
		homePage.hpDigitalDownloadsBanner.click();
		plp.plpNightVisionLink.click();
		pdp.pdpNightVisionDownloadSample.click();
		Thread.sleep(5000);

		pdp.verifySuccessfulDownload(testData.downloadedFile);

	}

	@Test(enabled = true)
	public void TC_DETAILS_003_ReviewWithLoggedInUser() {
		homePage.navigateLoginPage();
		loginPage.loginUserAndLoginBtn(testData.validEmail, testData.validPassword);
		registerPage.registerUserIfNotAlreadyRegistered(testData.firstName, testData.lastName, testData.validEmail,
				testData.validPassword, testData.validConfrimPassword);
		comm.mouseOverAndClickAction(homePage.hpElectronicsBanner, plp.plpCameraSubCategory);
		plp.plpLeicaLink.click();
		pdp.reviewLink.click();
		pdp.insertReview(testData.reviewTitle, testData.reviewTxt);
		pdp.ratingExcellent.click();
		pdp.submitReviewBtn.click();
		pdp.verifySuccesfulReview(testData.reviewTitle, testData.reviewTxt);
	}

	@Test
	public void VerifyCorrectProductIsAdded() {
		/*
		 * 1.Open Home Page 2.Mouse over on "Apparel" and click on "Clothing" 3.Open
		 * "Nike Tailwind" product 4. Select 3x size 5.Get (fetch) the sku value of the
		 * product 6. add quantity 4 7. add to cart 8.Go to shopping cart and verify the
		 * correct product is added/The sku value should be identical with the PDP SKU
		 */

		comm.mouseOverAndClickAction(homePage.hpApparelBanner, homePage.hpClothingBanner);
		plp.plpNikeTailwindLink.click();
		comm.selectFromDropManu(pdp.pdpSizeDropBox, testData.validNikeTailwindSizeValue);

		// Using RETURN
		String expSku = pdp.fetchSkuCodePdp();

		pdp.insertQuantityPDP(4);
		pdp.addCartBtn.click();
		homePage.hpShoppingCartLink.click();
		comm.waitElement(shoppingCartPage.shSkuCode);

		Assert.assertEquals(shoppingCartPage.shSkuCode.getText(), expSku);

	}

	@Test
	public void VerifyRentalItemCalendarSendKeys() {
		homePage.hpJewelryBanner.click();
		plp.plpNecklaceRental.click();
		pdp.pdpRentalStartDate.sendKeys("7/14/2023");
		pdp.pdpRentalEndDate.sendKeys("8/20/2023");
		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		shoppingCartPage.sourcePageContains("Elegant");
	}

	@Test
	public void VerifyRentalItemCalendarLOOP() {
		homePage.hpJewelryBanner.click();
		plp.plpNecklaceRental.click();

		pdp.pdpRentalStartDate.click();
		pdp.ChooseStartDateCalendarLOOP(testData.startMonth, testData.startYear, testData.startDay);

		pdp.pdpRentalEndDate.click();
		pdp.ChooseEndDateCalendarLOOP(testData.endMonth, testData.endYear, testData.endDay);

		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		shoppingCartPage.sourcePageContains("Elegant");
		shoppingCartPage.verifyCorectStartEndDate("8/20/2023", "9/30/2023");

	}

	@Test
	public void VerifyRentalItemCalendarFORLoop() {
		homePage.hpJewelryBanner.click();
		plp.plpNecklaceRental.click();

		pdp.pdpRentalStartDate.click();
		pdp.ChooseStartDateCalendar(testData.startMonth, testData.startYear, testData.startDay);

		pdp.pdpRentalEndDate.click();
		pdp.ChooseEndDateCalendar(testData.endMonth, testData.endYear, testData.endDay);

		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		shoppingCartPage.sourcePageContains("Elegant");
		shoppingCartPage.verifyCorectStartEndDate("8/20/2023", "9/30/2023");

	}

	@Test
	public void VerifyUserIsAbleToSelectVariationsOfTheProduct() {
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpDesktopBanner);
		plp.plpBuildYourOwnComputer.click();
		comm.selectFromDropManu(pdp.pdpProcessorDropMenu, testData.processor2_2GHZ);
		comm.selectFromDropManu(pdp.pdpRamDropMenu, testData.ram8GB);
		pdp.pdpHDD400RadioBtn.click();
		pdp.pdpVistaPremiumRadioBtn.click();
		pdp.addCartBtn.click();
		Assert.assertTrue(homePage.shoppingCartMsgLink.isDisplayed());

	}
}
