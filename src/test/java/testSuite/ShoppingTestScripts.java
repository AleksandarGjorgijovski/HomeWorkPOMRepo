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
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.LoginPageObjects;
import pageObjectsRepository.PdpObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.RegisterPageObjects;
import pageObjectsRepository.SearchPageObjects;
import pageObjectsRepository.ShoppingCartPageObjects;
import pageObjectsRepository.WishlistPageObjects;
import testData.TestData;

public class ShoppingTestScripts extends Base {
	TestData testData;
	HomePageObjects homePage;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects shoppingCartPage;
	WishlistPageObjects wishlistPage;
	SearchPageObjects searchPage;
	CommonPOM comm;
	LoginPageObjects loginPage;
	RegisterPageObjects registerPage;

	@BeforeMethod
	public void start() {
		testSetup();
		testData = new TestData();
		homePage = new HomePageObjects();
		plp = new PlpObjects();
		pdp = new PdpObjects();
		shoppingCartPage = new ShoppingCartPageObjects();
		wishlistPage = new WishlistPageObjects();
		searchPage = new SearchPageObjects();
		comm = new CommonPOM();
		loginPage = new LoginPageObjects();
		registerPage = new RegisterPageObjects();

	}

	@AfterMethod
	public void end(ITestResult result) throws IOException, AWTException {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshotURL(result.getName());
		}
		testTeardown();
	}

	@Test
	public void TC_CART_022_CheckIfThePriceChangesAccordingTheDiscountSimplified() {
		comm.waitElement(homePage.hpApparelBanner);
		comm.mouseOverAndClickAction(homePage.hpApparelBanner, homePage.hpClothingBanner);
		pdp.levisLink.click();
		comm.waitElement(pdp.pdpSkuCode);

		Integer originalPriceprice435Num = shoppingCartPage.convertTablePriceToInteger(pdp.levisTabelPrice435);
		Integer tabelPriceprice40Num = shoppingCartPage.convertTablePriceToInteger(pdp.levisTabelPrice40);
		Integer tabelPriceprice38Num = shoppingCartPage.convertTablePriceToInteger(pdp.levisTabelPrice38);
		Integer tabelPriceprice35Num = shoppingCartPage.convertTablePriceToInteger(pdp.levisTabelPrice35);

		pdp.addCartBtn.click();
		homePage.hpShoppingCartLink.click();
		shoppingCartPage.shLevisQuantityField.clear();

		int randomQuantity = shoppingCartPage.generateRandomAndUpdateShoppingCart(15,
				shoppingCartPage.shLevisQuantityField);

		comm.waitElement(wishlistPage.wSinglePrice);

		Integer singlePriceShoppingCart = shoppingCartPage.convertPriceShoppingCartToInteger(wishlistPage.wSinglePrice);

		if (randomQuantity == 1 && randomQuantity == 2) {
			Assert.assertEquals(singlePriceShoppingCart, originalPriceprice435Num);

		} else if (randomQuantity >= 3 && randomQuantity <= 5) {
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice40Num);

		} else if (randomQuantity >= 6 && randomQuantity <= 9) {
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice38Num);

		} else if (randomQuantity >= 10) {
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice35Num);
		}
	}

	@Test
	public void verifyShoppingCartRemoveItem() {
		homePage.hpBooksBanner.click();
		plp.plpFahrenheitLink.click();
		pdp.addCartBtn.click();
		comm.goBack();
		plp.plpFirstPrizePiesLink.click();
		pdp.addCartBtn.click();
		comm.goBack();
		plp.plpPrideAndPrejudiceLink.click();
		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		shoppingCartPage.removeAllItemsFromShoppingCart();
		shoppingCartPage.verifyShoppingCartIsEmpty();

	}

	@Test
	public void verifyTermsOfServiceLink() {
		homePage.hpJewelryBanner.click();
		plp.plpFlowerGirlBraceletLink.click();
		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		shoppingCartPage.shReadTermsLink.click();

	}

	@Test
	public void VerifyAddProducInShoppingCartAsAGuestUser() {
		homePage.hpBooksBanner.click();
		plp.plpFahrenheitLink.click();
		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		comm.verifyByPageSourceContains(testData.SKUFahrenheit);
	}

	@Test
	public void VerifyAddingProductInShoppingCartWithValidUser() {
		homePage.navigateLoginPage();
		loginPage.loginUserAndLoginBtn(testData.validEmail2, testData.validPassword);
		registerPage.registerUserIfNotAlreadyRegisteredFromHomePageAndStayLoggedIn(testData.firstName,
				testData.lastName, testData.validEmail2, testData.validPassword, testData.validConfrimPassword);
		homePage.hpJewelryBanner.click();
		plp.plpFlowerGirlBraceletLink.click();
		pdp.addCartBtn.click();
		comm.verifyByPageSourceContains(testData.SKUFlowerGirlBracelet);

	}

	@Test
	public void VerifyItemQuantityIncreasedIfUserAddsSameItemInCartAgain() {
		comm.mouseOverAndClickAction(homePage.hpApparelBanner, homePage.hpAccessoriesBanner);
		plp.plpObeyPropagandaHatLink.click();
		comm.selectFromDropManu(pdp.pdpSizeHatDropBox, testData.validHatSizeMedium);
		pdp.addCartBtn.click();

		comm.mouseOverAndClickAction(homePage.hpApparelBanner, homePage.hpAccessoriesBanner);
		plp.plpObeyPropagandaHatLink.click();
		comm.selectFromDropManu(pdp.pdpSizeHatDropBox, testData.validHatSizeMedium);
		pdp.addCartBtn.click();

		homePage.shoppingCartMsgLink.click();
		Assert.assertEquals(shoppingCartPage.shQuantityField.getAttribute("value"), "2");

	}

}
