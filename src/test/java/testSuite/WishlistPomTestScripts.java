package testSuite;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.PdpObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.RegisterPageObjects;
import pageObjectsRepository.ShoppingCartPageObjects;
import pageObjectsRepository.WishlistPageObjects;
import testData.TestData;

public class WishlistPomTestScripts extends Base {
	HomePageObjects homePage;
	WishlistPageObjects wishlistPage;
	TestData testData;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects shoppingCartPage;
	RegisterPageObjects registerPage;
	CommonPOM comm;

	@BeforeMethod
	public void start() {
		testSetup();
		homePage = new HomePageObjects();
		wishlistPage = new WishlistPageObjects();
		testData = new TestData();
		plp = new PlpObjects();
		pdp = new PdpObjects();
		shoppingCartPage = new ShoppingCartPageObjects();
		registerPage = new RegisterPageObjects();
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
	public void TC_WISHLIST_003_1_AddItemsToCartFromWishlistWihtLevis() {
		// insert two items it no Wish list, and only one send to Shopping cart
		comm.waitElement(homePage.hpApparelBanner);
		comm.mouseOverAndClickAction(homePage.hpApparelBanner, homePage.hpShoesBanner);
		pdp.adidasLink.click();
		comm.selectFromDropManu(pdp.adidasSizeDropBox, testData.validSizeValue);
		pdp.addWishlistBtn.click();

		comm.mouseOverAndClickAction(homePage.hpApparelBanner, homePage.hpClothingBanner);
		homePage.hpApparelBanner.click();
		plp.plpClothingSubCategory.click();
		pdp.levisLink.click();
		pdp.quantityField.clear();
		pdp.quantityField.sendKeys(testData.validQuantity);
		pdp.addWishlistBtn.click();
		homePage.hpWishlistLink.click();
		wishlistPage.wLeviAddTocartChBox.click();
		wishlistPage.wAddToCartBtn.click();
		wishlistPage.verifyItemAddToCartSuccessful();
	}

	@Test
	public void TC_WISHLIST_007_1_CheckTotalPriceIsCalculated() {
		homePage.hpBooksBanner.click();
		pdp.firstPrizePiesLink.click();
		pdp.addWishlistBtn.click();
		homePage.hpWishlistLink.click();
		wishlistPage.wQunatity1st.clear();
		wishlistPage.wQunatity1st.sendKeys(testData.validQuantity5);
		wishlistPage.wUpdateBtn.click();
		wishlistPage.verifyInciresePriceWithQuantity();
	}

	@Test
	public void VerifyEuroCurrencyIsWorkingProperly() {
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpNotebooksBanner);
		comm.waitElement(plp.plpAppleMacBookLink);
		plp.plpAppleMacBookLink.click();
		pdp.pdpAddToWishlist();
		homePage.hpWishlistLink.click();
		comm.selectFromDropManuByText(homePage.hpCyrrencyDropdown, "Euro");
		wishlistPage.verifyPriceIsDisplayedInEuros();
	}
}
