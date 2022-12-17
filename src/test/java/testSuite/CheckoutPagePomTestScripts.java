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
	TestData td;
	CommonPOM comm;
	HomePageObjects hp;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects sh;
	WishlistPageObjects wh;
	SearchPageObjects sp;
	LoginPageObjects lp;
	CheckoutPageObjects ch;
	RegisterPageObjects rp;

	
	@BeforeMethod
	public void start(Method testCase) {
		testSetup();
		td = new TestData();
		comm = new CommonPOM();
		hp = new HomePageObjects();
		plp = new PlpObjects();
		pdp = new PdpObjects();
		sh = new ShoppingCartPageObjects();
		wh = new WishlistPageObjects();
		sp = new SearchPageObjects();
		lp = new LoginPageObjects();
		ch = new CheckoutPageObjects();
		rp = new RegisterPageObjects();
		
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
		driver.quit();
	}
	@Test
	public void TC_CHECKOUT_011_ReturningCustomerDifferentBillingAndShippingAddress() throws IOException, AWTException {
		comm.waitElement(hp.hpComputersBanner);
		comm.mouseOverAndClickAction(hp.hpComputersBanner, hp.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		hp.hpShoppingCartLink.click();
		comm.waitElement(sh.shTermsChbox);
		sh.acceptTermsAndCheckoutBtn();
		lp.loginUserAndLoginBtn(td.validEmail, td.validPassword);
		rp.registerUserIfNotAlreadyRegistered(td.firstName, td.lastName, td.validEmail, td.validPassword, td.validConfrimPassword);
		sh.acceptTermsAndCheckoutBtn();
		ch.chBillShipToTheSameAddressCheckbox.click();
		ch.ifBillingAddressBookIsPopulatedOrNot(0);
		comm.waitElement(ch.chSelectShippingAddressBook);
		comm.selectFromDropManuByLastIndex(ch.chSelectShippingAddressBook);
		ch.validShippingAddressMandatoryField(td.validMacedoniaValue, td.validCityPrilep, td.validShipAddress, td.validShipZipCode, td.validShipPhoneNumber);
		captureScreenshotURL("shippingAddress");
	//	captureFullScreenshot("shippingAddress");
		ch.shippingMethod(ch.chShipMetodGorund);
		comm.waitElement(ch.chPayMethodCard);
		ch.paymentMethod(ch.chPayMethodCard);
		comm.waitElement(ch.chPayInfoConBtn);
		ch.paymentInformation(td.firstName, td.lastName, td.validCardNumber, td.validExpireMonth, td.validExpireYear, td.validCardCode);
		comm.waitElement(ch.chConfirmOrderConBtn);
		ch.chConfirmOrderConBtn.click();
		comm.waitElement(ch.chOrderCopmliteConBtn);
		ch.chOrderCopmliteConBtn.click();
		comm.waitElement(ch.chEmpltyShoppingCart);
		ch.verifySuccessfulCheckoutAndEmpltyShoppingCart();
	}
	@Test
	public void TC_CHECKOUT_007_CheckoutAsGuestAndPayWithCheckMoneyOrder() {
		comm.waitElement(hp.hpComputersBanner);
		comm.mouseOverAndClickAction(hp.hpComputersBanner, hp.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		hp.hpShoppingCartLink.click();
		comm.waitElement(sh.shTermsChbox);
		sh.acceptTermsAndCheckoutBtn();
		ch.chCheckoutAsGuest.click();
		ch.validBillAddressAllMandatoryField(td.firstName, td.lastName, td.validEmail6, td.validMacedoniaValue, td.validCityBitola, td.validAddress, td.validZipCode, td.validPhoneNumber);
		comm.waitElement(ch.chShipMetodGorund);
		ch.shippingMethod(ch.chShipMetodGorund);
		comm.waitElement(ch.chPayMethodCheck);
		ch.chPayMethodCheck.click();
		ch.chPayMethodConBtn.click();
		comm.waitElement(ch.chPayInfoConBtn);
		ch.chPayInfoConBtn.click();
		comm.waitElement(ch.chConfirmOrderConBtn);
		ch.chConfirmOrderConBtn.click();
		comm.waitElement(ch.chOrderCopmliteConBtn);
		ch.chOrderCopmliteConBtn.click();
		comm.waitElement(ch.chEmpltyShoppingCart);
		ch.verifySuccessfulCheckoutAndEmpltyShoppingCart();
	}
	@Test
	public void TC_CHECKOUT_004_CheckIfErrorMessageIsDisplayedWhenUserEntersInvalidData() {
		comm.waitElement(hp.hpComputersBanner);
		comm.mouseOverAndClickAction(hp.hpComputersBanner, hp.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		comm.scrollTo(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		hp.hpShoppingCartLink.click();
		comm.waitElement(sh.shTermsChbox);
		captureScreenshot("shopping cart");
		sh.acceptTermsAndCheckoutBtn();
		ch.chCheckoutAsGuest.click();
		ch.validBillAddressAllMandatoryField(td.firstName, td.lastName, td.incorrectEmail, td.validMacedoniaValue, td.validCityBitola, td.validAddress, td.validZipCode, td.validPhoneNumber);
//		Assert.assertTrue(ch.chShipMetodConBtn.isDisplayed(), "User is not navigated to Shopping Method");
			//Proverka
		Assert.assertFalse(ch.chShipMetodConBtn.isDisplayed(), "User is navigated to Shopping Method");

	}
	@Test
	public void TC_CHECKOUT_005_verifyScreenshotWhereTheMistakeIsMade() {
		/*Check if error message is displayed when user doesn't enter data in all of 
		the required filed in billing address while checking out as guest*/
		comm.waitElement(hp.hpComputersBanner);
		comm.mouseOverAndClickAction(hp.hpComputersBanner, hp.hpSoftWareBanner);
		comm.waitElement(plp.plpWindows8addToCart);
		plp.addToCartFromPLP(plp.plpWindows8addToCart);
		hp.hpShoppingCartLink.click();
		comm.waitElement(sh.shTermsChbox);
		sh.acceptTermsAndCheckoutBtn();
		ch.chCheckoutAsGuest.click();
		ch.chBillContinueBtn.click();
		
		Assert.assertTrue(ch.chShipMetodConBtn.isDisplayed(), "Required mandatory Billing Address fields are not properly populated");
	}
}












