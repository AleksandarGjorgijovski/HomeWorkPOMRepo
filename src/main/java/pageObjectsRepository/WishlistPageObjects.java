package pageObjectsRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class WishlistPageObjects extends Base {
	TestData td = new TestData();
	ShoppingCartPageObjects shp = new ShoppingCartPageObjects();

	// Defining Elements
	@FindBy(xpath = "//input[@name='addtocart']")
	WebElement wAddToCartChbox;

	@FindBy(xpath = "//a[text()='Edit']")
	WebElement wEditLink;

	@FindBy(xpath = "(//input[@class='qty-input'])[1]")
	public WebElement wQunatity1st;

	@FindBy(xpath = "(//input[@class='qty-input'])[2]")
	WebElement wQunatity2;

	@FindBy(xpath = "//button[@class='remove-btn']")
	WebElement wRemoveBtn;

	@FindBy(xpath = "//button[@id='updatecart']")
	public WebElement wUpdateBtn;

	@FindBy(xpath = "//button[@class='button-2 wishlist-add-to-cart-button']")
	public WebElement wAddToCartBtn;

	@FindBy(xpath = "//button[text()='Email a friend']")
	WebElement wEmailFriendBtn;

	@FindBy(xpath = "//a[@class='share-link']")
	WebElement wShareUrlLink;

	@FindBy(xpath = "//a[@href='/levis-511-jeans' and @class]/ancestor::tr//descendant::input[@name='addtocart']")
	public WebElement wLeviAddTocartChBox;

	@FindBy(xpath = "//span[@class='product-unit-price']")
	public WebElement wSinglePrice;

	@FindBy(xpath = "//span[@class='product-subtotal']")
	public WebElement wTotalPrice;

	// Initiation
	public WishlistPageObjects() {
		PageFactory.initElements(driver, this);
	}

	// Methods
	public void wishlistAddToCart() {
		wAddToCartBtn.click();
	}

	public void levisChekbox() {
		wLeviAddTocartChBox.click();
	}

	public void wishlistChangeQuantityFirstItem(String quantity) {
		wQunatity1st.clear();
		wQunatity1st.sendKeys(quantity);
	}

	public void updateWishlist() {
		wUpdateBtn.click();
	}

	// verification
	public void verifyItemAddToCartSuccessful() {
		Assert.assertTrue(shp.shLevisItem.isDisplayed());
	}

	public void verifyInciresePriceWithQuantity() {
		String singlePriceTxt = wSinglePrice.getText().replace("$", "").replace(".", "");
		System.out.println("singlePriceTxt: " + singlePriceTxt);
		Integer singlePriceNum = Integer.valueOf(singlePriceTxt);
		System.out.println("singlePriceNum: " + singlePriceNum);

		String totalPriceTxt = wTotalPrice.getText().replace("$", "").replace(".", "");
		System.out.println("totalPriceTxt: " + totalPriceTxt);
		Integer totalPriceNum = Integer.valueOf(totalPriceTxt);
		System.out.println("totalPriceNum: " + totalPriceNum);

		int totalPriceNumInt = totalPriceNum;

		String quantityTxt = td.validQuantity5;
		System.out.println("quantityTxt: " + quantityTxt);
		Integer quantityNum = Integer.valueOf(quantityTxt);
		System.out.println("quantityNum: " + quantityNum);

		int totalExp = singlePriceNum * quantityNum;
		System.out.println("totalExp: " + totalExp);

		Assert.assertEquals(totalPriceNumInt, totalExp);
	}

	public void verifyPriceIsDisplayedInEuros() {
		Assert.assertTrue(wSinglePrice.getText().contains("€"), "Price should be displayed in Euros");
		System.out.println("Euro currency is working properly");
	}

}
