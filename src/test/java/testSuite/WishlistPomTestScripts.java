package testSuite;

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
	HomePageObjects hp;
	WishlistPageObjects wp;
	TestData td;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects shp;
	RegisterPageObjects rp;
	CommonPOM comm;
	
	@BeforeMethod
	public void start() {
		testSetup();
		hp = new HomePageObjects();
		wp = new WishlistPageObjects();
		td = new TestData();	
		plp = new PlpObjects();
		pdp = new PdpObjects();
		shp = new ShoppingCartPageObjects();
		rp = new RegisterPageObjects();
		comm = new CommonPOM();
	}
	@AfterMethod
	public void end() {
		testTeardown();
	}
	@Test
	public void TC_WISHLIST_003_1_AddItemsToCartFromWishlistWihtLevis(){
		//insert two items it no Wish list, and only one send to Shopping cart
		comm.waitElement(hp.hpApparelBanner);
		comm.mouseOverAndClickAction(hp.hpApparelBanner, hp.hpShoesBanner);
		pdp.adidasLink.click();
		comm.selectFromDropManu(pdp.adidasSizeDropBox, td.validSizeValue);
		pdp.addWishlistBtn.click();
		
		comm.mouseOverAndClickAction(hp.hpApparelBanner, hp.hpClothingBanner);
		hp.hpApparelBanner.click();
		plp.plpClothingSubCategory.click();
		pdp.levisLink.click();
		pdp.quantityField.clear();
		pdp.quantityField.sendKeys(td.validQuantity);
		pdp.addWishlistBtn.click();
		hp.hpWishlistLink.click();
		wp.wLeviAddTocartChBox.click();
		wp.wAddToCartBtn.click();
		wp.verifyItemAddToCartSuccessful();
	}
	@Test
	public void TC_WISHLIST_007_1_CheckTotalPriceIsCalculated() {
		hp.hpBooksBanner.click();
		pdp.firstPrizePiesLink.click();
		pdp.addWishlistBtn.click();
		hp.hpWishlistLink.click();
		wp.wQunatity1st.clear();
		wp.wQunatity1st.sendKeys(td.validQuantity5);
		wp.wUpdateBtn.click();
		wp.verifyInciresePriceWithQuantity();
	}
}
