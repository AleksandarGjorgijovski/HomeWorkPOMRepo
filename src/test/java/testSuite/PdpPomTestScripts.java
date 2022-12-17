package testSuite;

import org.testng.Assert;
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
	TestData td;
	HomePageObjects hp;
	LoginPageObjects lp;
	PlpObjects plp;
	RegisterPageObjects rp;
	SearchPageObjects sp;
	ShoppingCartPageObjects sh;
	WishlistPageObjects wp;
	PdpObjects pdp;
	CommonPOM comm;
	CheckoutPageObjects ch;
	
	@BeforeMethod
	public void start() {
		testSetup();
		hp = new HomePageObjects();
		lp = new LoginPageObjects();
		plp = new PlpObjects();
		rp = new RegisterPageObjects();
		sp = new SearchPageObjects();
		sh = new ShoppingCartPageObjects();
		wp = new WishlistPageObjects();
		td = new TestData();
		pdp = new PdpObjects();
		comm = new CommonPOM();
		ch = new CheckoutPageObjects();
		
	}
	@AfterMethod
	public void end() {
		testTeardown();
	}
	@Test
	public void TC_DETAILS_013_VerifyDonwloadSample() throws InterruptedException {
		hp.hpDigitalDownloadsBanner.click();
		plp.plpNightVisionLink.click();
		pdp.pdpNightVisionDownloadSample.click();		
		Thread.sleep(5000);
		
		pdp.verifySuccessfulDownload(td.downloadedFile);
		
	}
	@Test
	public void TC_DETAILS_003_ReviewWithLoggedInUser() {
		hp.navigateLoginPage();
		lp.loginUserAndLoginBtn(td.validEmail, td.validPassword);
		rp.registerUserIfNotAlreadyRegistered(td.firstName, td.lastName, td.validEmail, td.validPassword, td.validConfrimPassword);
		comm.mouseOverAndClickAction(hp.hpElectronicsBanner, plp.plpCameraSubCategory);
		plp.plpLeicaLink.click();
		pdp.reviewLink.click();
		pdp.insertReview(td.reviewTitle, td.reviewTxt);
		pdp.ratingExcellent.click();
		pdp.submitReviewBtn.click();
		pdp.verifySuccesfulReview(td.reviewTitle, td.reviewTxt);
	}
	@Test
	public void VerifyCorrectProductIsAdded() {
		/*1.Open Home Page
		 * 2.Mouse over on "Apparel" and click on "Clothing"
		 * 3.Open "Nike Tailwind" product
		 * 4. Select 3x size
		 * 5.Get (fetch) the sku value of the product
		 * 6. add quantity 4
		 * 7. add to cart
		 * 8.Go to shopping cart and verify the correct product is added/The sku value should be identical with the PDP SKU 
		 */
		
		comm.mouseOverAndClickAction(hp.hpApparelBanner, hp.hpClothingBanner);
		plp.plpNikeTailwindLink.click();
		comm.selectFromDropManu(pdp.pdpSizeDropBox, td.validNikeTailwindSizeValue);
		
		//Using RETURN
		String expSku =  pdp.fetchSkuCodePdp();
		
		pdp.insertQuantityPDP(4);
		pdp.addCartBtn.click();
		hp.hpShoppingCartLink.click();
		comm.waitElement(sh.shSkuCode);
		
		Assert.assertEquals(sh.shSkuCode.getText(), expSku);
		
	}
	@Test
	public void VerifyRentalItemCalendarSendKeys() {
		hp.hpJewelryBanner.click();
		plp.plpNecklaceRental.click();
		pdp.pdpRentalStartDate.sendKeys("12/14/2022");
		pdp.pdpRentalEndDate.sendKeys("12/20/2022");
		pdp.addCartBtn.click();
		hp.shoppingCartMsgLink.click();
		sh.sourcePageContains("Elegant");
	}
	@Test
	public void VerifyRentalItemCalendarLOOP() { 
		hp.hpJewelryBanner.click();
		plp.plpNecklaceRental.click();
		
		pdp.pdpRentalStartDate.click();
		pdp.ChooseStartDateCalendarLOOP(td.startMonth, td.startYear, td.startDay);
		
		pdp.pdpRentalEndDate.click();
		pdp.ChooseEndDateCalendarLOOP(td.endMonth, td.endYear, td.endDay);
		
		pdp.addCartBtn.click();
		hp.shoppingCartMsgLink.click();
		sh.sourcePageContains("Elegant");
		sh.verifyCorectStartEndDate("12/20/2022", "3/31/2023");
		
		
	}
	@Test
	public void VerifyRentalItemCalendarFORLoop() {
		hp.hpJewelryBanner.click();
		plp.plpNecklaceRental.click();
		
		pdp.pdpRentalStartDate.click();
		pdp.ChooseStartDateCalendar(td.startMonth, td.startYear, td.startDay);
		
		pdp.pdpRentalEndDate.click();
		pdp.ChooseEndDateCalendar(td.endMonth, td.endYear, td.endDay);
		
		pdp.addCartBtn.click();
		hp.shoppingCartMsgLink.click();
		sh.sourcePageContains("Elegant");
		sh.verifyCorectStartEndDate("12/20/2022", "3/31/2023");
		
	}
  
}
























