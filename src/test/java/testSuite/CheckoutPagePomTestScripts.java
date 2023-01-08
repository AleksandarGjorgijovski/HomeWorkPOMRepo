package testSuite;



import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

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

public class CheckoutPagePomTestScripts extends Base{
	TestData testData;
	CommonPOM comm;
	HomePageObjects homePage;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects shoppingCartPage;
	WishlistPageObjects wishlistPage;
	SearchPageObjects searchPage;
	LoginPageObjects loginPage;
	CheckoutPageObjects checkoutPage;
	RegisterPageObjects registerPage;

	
	@BeforeMethod
	public void start(Method testCase) {
		testSetup();
		testData = new TestData();
		comm = new CommonPOM();
		homePage = new HomePageObjects();
		plp = new PlpObjects();
		pdp = new PdpObjects();
		shoppingCartPage = new ShoppingCartPageObjects();
		wishlistPage = new WishlistPageObjects();
		searchPage = new SearchPageObjects();
		loginPage = new LoginPageObjects();
		checkoutPage = new CheckoutPageObjects();
		registerPage = new RegisterPageObjects();
		
		//precondition for TC_CHECKOUT_004_CheckIfErrorMessageIsDisplayedWhenUserEntersInvalidData()
//		if(testCase.getName().equals("TC_CHECKOUT_004_CheckIfErrorMessageIsDisplayedWhenUserEntersInvalidData()")) { 
//			rp.registerUserForCheckoutTestsBeforeMethod(td.firstName, td.lastName, td.validEmail1, td.validPassword, td.validConfrimPassword);
//			}
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
	public void TC_CHECKOUT_011_ReturningCustomerDifferentBillingAndShippingAddress() throws IOException, AWTException {
		comm.waitElement(homePage.hpComputersBanner);
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		homePage.hpShoppingCartLink.click();
		comm.waitElement(shoppingCartPage.shTermsChbox);
		shoppingCartPage.acceptTermsAndCheckoutBtn();
		loginPage.loginUserAndLoginBtn(testData.validEmail, testData.validPassword);
		registerPage.registerUserIfNotAlreadyRegistered(testData.firstName, testData.lastName, testData.validEmail, testData.validPassword, testData.validConfrimPassword);
		shoppingCartPage.acceptTermsAndCheckoutBtn();
		checkoutPage.chBillShipToTheSameAddressCheckbox.click();
		checkoutPage.ifBillingAddressBookIsPopulatedOrNot(0, testData.validMacedoniaValue, testData.validCityBitola, testData.validAddress, testData.validZipCode, testData.validPhoneNumber);
		comm.waitElement(checkoutPage.chSelectShippingAddressBook);
		comm.selectFromDropManuByLastIndex(checkoutPage.chSelectShippingAddressBook);
		checkoutPage.validShippingAddressMandatoryField(testData.validMacedoniaValue, testData.validCityPrilep, testData.validShipAddress, testData.validShipZipCode, testData.validShipPhoneNumber);
		captureScreenshotURL("shippingAddress");
	//	captureFullScreenshot("shippingAddress");
		checkoutPage.shippingMethod(checkoutPage.chShipMethodGorund);
		comm.waitElement(checkoutPage.chPayMethodCard);
		checkoutPage.paymentMethod(checkoutPage.chPayMethodCard);
		comm.waitElement(checkoutPage.chPayInfoConBtn);
		checkoutPage.paymentInformation(testData.firstName, testData.lastName, testData.validCardNumber, testData.validExpireMonth, testData.validExpireYear, testData.validCardCode);
		comm.waitElement(checkoutPage.chConfirmOrderConBtn);
		checkoutPage.chConfirmOrderConBtn.click();
		comm.waitElement(checkoutPage.chOrderCopmliteConBtn);
		checkoutPage.chOrderCopmliteConBtn.click();
		comm.waitElement(checkoutPage.chEmpltyShoppingCart);
		checkoutPage.verifySuccessfulCheckoutAndEmpltyShoppingCart();
	}
	@Test
	public void TC_CHECKOUT_007_CheckoutAsGuestAndPayWithCheckMoneyOrder() {
		comm.waitElement(homePage.hpComputersBanner);
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		homePage.hpShoppingCartLink.click();
		comm.waitElement(shoppingCartPage.shTermsChbox);
		shoppingCartPage.acceptTermsAndCheckoutBtn();
		checkoutPage.chCheckoutAsGuest.click();
		checkoutPage.validBillAddressAllMandatoryField(testData.firstName, testData.lastName, testData.validEmail6, testData.validMacedoniaValue, testData.validCityBitola, testData.validAddress, testData.validZipCode, testData.validPhoneNumber);
		comm.waitElement(checkoutPage.chShipMethodGorund);
		checkoutPage.shippingMethod(checkoutPage.chShipMethodGorund);
		comm.waitElement(checkoutPage.chPayMethodCheck);
		checkoutPage.chPayMethodCheck.click();
		checkoutPage.chPayMethodConBtn.click();
		comm.waitElement(checkoutPage.chPayInfoConBtn);
		checkoutPage.chPayInfoConBtn.click();
		comm.waitElement(checkoutPage.chConfirmOrderConBtn);
		checkoutPage.chConfirmOrderConBtn.click();
		comm.waitElement(checkoutPage.chOrderCopmliteConBtn);
		checkoutPage.chOrderCopmliteConBtn.click();
		comm.waitElement(checkoutPage.chEmpltyShoppingCart);
		checkoutPage.verifySuccessfulCheckoutAndEmpltyShoppingCart();
	}
	@Test
	public void TC_CHECKOUT_004_CheckIfErrorMessageIsDisplayedWhenUserEntersInvalidData() {
		comm.waitElement(homePage.hpComputersBanner);
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		comm.scrollTo(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		homePage.hpShoppingCartLink.click();
		comm.waitElement(shoppingCartPage.shTermsChbox);
		captureScreenshot("shopping cart");
		shoppingCartPage.acceptTermsAndCheckoutBtn();
		checkoutPage.chCheckoutAsGuest.click();
		checkoutPage.validBillAddressAllMandatoryField(testData.firstName, testData.lastName, testData.incorrectEmail, testData.validMacedoniaValue, testData.validCityBitola, testData.validAddress, testData.validZipCode, testData.validPhoneNumber);
//		Assert.assertTrue(ch.chShipMetodConBtn.isDisplayed(), "User is not navigated to Shopping Method");
			//Proverka
		Assert.assertFalse(checkoutPage.chShipMetodConBtn.isDisplayed(), "User is navigated to Shopping Method");

	}
	@Test
	public void TC_CHECKOUT_005_verifyScreenshotWhereTheMistakeIsMade() {
		/*Check if error message is displayed when user doesn't enter data in all of 
		the required filed in billing address while checking out as guest*/
		comm.waitElement(homePage.hpComputersBanner);
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		homePage.hpShoppingCartLink.click();
		comm.waitElement(shoppingCartPage.shTermsChbox);
		shoppingCartPage.acceptTermsAndCheckoutBtn();
		checkoutPage.chCheckoutAsGuest.click();
		checkoutPage.chBillContinueBtn.click();
		
		Assert.assertTrue(checkoutPage.chShipMetodConBtn.isDisplayed(), "Required mandatory Billing Address fields are not properly populated");
	}
	@Test
	public void VerifyInvalidCheckoutWithBlankPaymentInformation() {
		homePage.hpLoginLink.click();
		loginPage.loginUserAndLoginBtn(testData.validEmail, testData.validPassword);
		registerPage.registerUserIfNotAlreadyRegistered(testData.firstName, testData.lastName, testData.validEmail, testData.validPassword, testData.validConfrimPassword);
		comm.mouseOverAndClickAction(homePage.hpComputersBanner, homePage.hpDesktopBanner);
		comm.waitElement(plp.plpBuildYourOwnComputer);
		plp.plpBuildYourOwnComputer.click();
		comm.selectFromDropManu(pdp.pdpProcessorDropMenu, testData.processor2_5GHz);
		comm.selectFromDropManu(pdp.pdpRamDropMenu, testData.ram8GB);
		pdp.pdpHDD400RadioBtn.click();
		pdp.pdpVistaPremiumRadioBtn.click();
		pdp.addCartBtn.click();
		homePage.shoppingCartMsgLink.click();
		shoppingCartPage.acceptTermsAndCheckoutBtn();
		checkoutPage.ifBillingAddressBookIsPopulatedOrNot(0, testData.validMacedoniaValue, testData.validCityBitola, testData.validAddress, testData.validZipCode, testData.validPhoneNumber);
		comm.waitElement(checkoutPage.chShipMethodGorund);
		checkoutPage.shippingMethod(checkoutPage.chShipMethodGorund);
		comm.waitElement(checkoutPage.chPayMethodCard);
		checkoutPage.paymentMethod(checkoutPage.chPayMethodCard);
		comm.waitElement(checkoutPage.chPayInfoConBtn);
		checkoutPage.chPayInfoConBtn.click();
		comm.waitElement(checkoutPage.chPaymentInformationErrorMsg);
		Assert.assertTrue(checkoutPage.chPaymentInformationErrorMsg.isDisplayed());
		
	}
}












