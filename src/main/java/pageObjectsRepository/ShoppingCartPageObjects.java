package pageObjectsRepository;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class ShoppingCartPageObjects extends Base {
	TestData td = new TestData();
	PdpObjects pdp = new PdpObjects();

	// Defining Elements
	@FindBy(xpath = "//input[@id='termsofservice']")
	public WebElement shTermsChbox;

	@FindBy(xpath = "//button[@id='checkout']")
	WebElement shCheckoutBtn;

	@FindBy(xpath = "//input[@class='qty-input']")
	WebElement shQuantuty;

	@FindBy(xpath = "//button[@class='button-2 update-cart-button']")
	public WebElement shUpdateCartBtn;

	@FindBy(xpath = "//button[@class='button-2 continue-shopping-button']")
	WebElement shContinueShopBtn;

	@FindBy(xpath = "//button[@class='remove-btn']")
	WebElement shRemoveBtn;

	@FindBy(xpath = "//input[@id='discountcouponcode']")
	WebElement shDiscountCodeField;

	@FindBy(xpath = "//button[@id='applydiscountcouponcode']")
	WebElement shApplyCouponBtn;

	@FindBy(xpath = "//input[@id='giftcardcouponcode']")
	WebElement shGiftCardsField;

	@FindBy(xpath = "//button[@id='applygiftcardcouponcode']")
	WebElement shAddGiftCardBtn;

	@FindBy(xpath = "//a[@id=\"read-terms\"]")
	public WebElement shReadTermsLink;

	@FindBy(xpath = "//select[@id='checkout_attribute_1']")
	WebElement shGiftWrapManu;

	@FindBy(xpath = "//a[@href='/levis-511-jeans' and @class]")
	public WebElement shLevisItem;

	@FindBy(xpath = "//span[text()='LV_511_JN']/parent::td[@class='sku']/following-sibling::td[@class='quantity']/child::input")
	public WebElement shLevisQuantityField;

	@FindBy(xpath = "//span[@class='sku-number']")
	public WebElement shSkuCode;

	@FindBy(xpath = "//span[@class='product-unit-price']")
	public WebElement SinglePriceShoppingCart;

	@FindBy(xpath = "//p[contains(text(),'conditions')]")
	public WebElement shTermsOfServiceDialog;
	// Initiation
	public ShoppingCartPageObjects() {
		PageFactory.initElements(driver, this);
	}

	// Methods
	public void updateShoppingCart(WebElement qunatityField) {
		shUpdateCartBtn.click();
	}

	public int randomQuantityGenerator(int scaleTo) {
		Random randomGenerator = new Random();
		int randomQuantity = randomGenerator.nextInt(scaleTo);

		return randomQuantity;
	}

	public void updateShoppingCartWithRandomNumber(WebElement quantityField, int numGenerated) {
		quantityField.sendKeys("" + numGenerated);
		System.out.println("Random generated quantity: " + numGenerated);
		shUpdateCartBtn.click();
	}

	public int generateRandomAndUpdateShoppingCart(int scaleTo, WebElement quantityField) {
		Random randomGenerator = new Random();
		int randomQuantity = randomGenerator.nextInt(scaleTo);
		quantityField.sendKeys("" + randomQuantity);
		System.out.println("Random generated quantity: " + randomQuantity);
		shUpdateCartBtn.click();

		return randomQuantity;
	}

	public Integer convertPriceShoppingCartToInteger(WebElement priceShoppingCart) {
		String priceShopingCart = priceShoppingCart.getText().replace("$", "").replace(".", "");
		Integer priceShopingCartNum = Integer.valueOf(priceShopingCart);
		System.out.println("Shopping cart single Price: " + priceShopingCartNum);

		return priceShopingCartNum;
	}

	public Integer convertTablePriceToInteger(WebElement priceTable) {
		String tablePriceTxt = priceTable.getText().replace("$", "").replace(".", "");
		Integer tablePriceNum = Integer.valueOf(tablePriceTxt);
		System.out.println("Shopping cart single Price: " + tablePriceNum);

		return tablePriceNum;

	}

	public void acceptTermsAndCheckoutBtn() {
		shTermsChbox.click();
		shCheckoutBtn.click();

	}
	public void removeAllItemsFromShoppingCart() {
		List<WebElement> totalItemsShoppingCart = driver.findElements(By.xpath("//a[@class='product-name']"));
		for (int i = 1; i <= totalItemsShoppingCart.size(); i++) {
			shRemoveBtn.click();
		}
	}
	
	//verifications

	public void sourcePageContains(String nameOfItem) {
		String pageSource = driver.getPageSource();
		// Provervam deka vo PageSource nema "Elegant Gemstone Necklace"
		Assert.assertTrue((pageSource.contains(nameOfItem)));
	}

	public void verifyCorectStartEndDate(String typeExpStartDate, String typeExpEndDate) {
		String actDate = driver.findElement(By.xpath("//div[@class='rental-info']")).getText();
		String expDate = "Start date: " + typeExpStartDate + "." + " End date: " + typeExpEndDate + ".";
		Assert.assertEquals(actDate, expDate);
	}
	public void verifyShoppingCartIsEmpty() {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='no-data']")).isDisplayed(), "Error: the Shopping cart is not empty");
	}
	public void verifyTermsOfServiceDialogWindow() {
		Assert.assertTrue(shTermsOfServiceDialog.isDisplayed());
	}

}
