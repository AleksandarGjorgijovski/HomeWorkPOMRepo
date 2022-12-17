package testSuite;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import base.CommonPOM;
import pageObjectsRepository.HomePageObjects;
import pageObjectsRepository.PdpObjects;
import pageObjectsRepository.PlpObjects;
import pageObjectsRepository.SearchPageObjects;
import pageObjectsRepository.ShoppingCartPageObjects;
import pageObjectsRepository.WishlistPageObjects;
import testData.TestData;

public class ShoppingTestScripts extends Base {
	TestData td;
	HomePageObjects hp;
	PlpObjects plp;
	PdpObjects pdp;
	ShoppingCartPageObjects shp;
	WishlistPageObjects wh;
	SearchPageObjects sp;
	CommonPOM comm;
	
	@BeforeMethod
	public void start() {
		testSetup();
		td = new TestData();
		hp = new HomePageObjects();
		plp = new PlpObjects();
		pdp = new PdpObjects();
		shp = new ShoppingCartPageObjects();
		wh = new WishlistPageObjects();
		sp = new SearchPageObjects();
		comm = new CommonPOM();
				
	}
	@AfterMethod
	public void end() {
		testTeardown();
	}
	@Test
	public void TC_CART_022_CheckIfThePriceChangesAccordingTheDiscountSimplified() {
		comm.waitElement(hp.hpApparelBanner);
		comm.mouseOverAndClickAction(hp.hpApparelBanner, hp.hpClothingBanner);
		pdp.levisLink.click();
		comm.waitElement(pdp.pdpSkuCode);
		
		Integer originalPriceprice435Num = shp.convertTablePriceToInteger(pdp.levisTabelPrice435);
		Integer tabelPriceprice40Num = shp.convertTablePriceToInteger(pdp.levisTabelPrice40);
		Integer tabelPriceprice38Num = shp.convertTablePriceToInteger(pdp.levisTabelPrice38);
		Integer tabelPriceprice35Num = shp.convertTablePriceToInteger(pdp.levisTabelPrice35);
	
		pdp.addCartBtn.click();
		hp.hpShoppingCartLink.click();
		shp.shLevisQuantityField.clear();
		
		int randomQuantity = shp.generateRandomAndUpdateShoppingCart(15, shp.shLevisQuantityField);
		
		comm.waitElement(wh.wSinglePrice);
		
		Integer singlePriceShoppingCart = shp.convertPriceShoppingCartToInteger(wh.wSinglePrice);
		
		if(randomQuantity == 1 && randomQuantity == 2)
		{ Assert.assertEquals(singlePriceShoppingCart, originalPriceprice435Num);	  
		
		}else if (randomQuantity >= 3 && randomQuantity <= 5){
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice40Num);
			
		}else if (randomQuantity >= 6 && randomQuantity <=9){
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice38Num);
			
		}else if (randomQuantity >= 10){
			Assert.assertEquals(singlePriceShoppingCart, tabelPriceprice35Num);
		}
	}
}
	

