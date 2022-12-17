package pageObjectsRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;

public class HomePageObjects extends Base {

	//Locators
	@FindBy(xpath = "//a[@class='ico-login']")
	public WebElement hpLoginLink;
	
	@FindBy(xpath = "//select[@id='customerCurrency']")
	WebElement hpCurrencyManu;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	public WebElement hpLogoutLink;
	
	@FindBy(xpath = "//a[@href='/register?returnUrl=%2F']")
	public WebElement hpRegisterLink;
	
	@FindBy(xpath = "//span[@class='wishlist-label']")
	public WebElement hpWishlistLink;
	
	@FindBy(xpath = "//span[@class='cart-label']")
	public WebElement hpShoppingCartLink;
	
	@CacheLookup
	@FindBy(xpath = "(//a[@href='/cart'])[1]")
	public WebElement shoppingCartMsgLink;
	
	@FindBy(xpath = "//input[@id='small-searchterms']")
	WebElement hpSearchField ;
	
	@FindBy(xpath = "//button[text()='Search']")
	WebElement hpSearchBtn;
	
	@FindBy(xpath = "//a[@href='/search']")
	public WebElement hpSearchPageLink;
	
	
	//locators category banner
	@FindBy(xpath = "(//a[@href='/computers'])[1]")
	public WebElement hpComputersBanner;
	
	@FindBy(xpath = "//a[@href='/desktops']")
	public WebElement hpDesktopBanner;
	
	@FindBy(xpath = "(//a[@href='/electronics'])[1]")
	public WebElement hpElectronicsBanner;
	
	@FindBy(xpath = "(//a[@href='/apparel'])[1]")
	public WebElement hpApparelBanner;
	
	@FindBy(xpath = "//a[@href='/software']")
	public WebElement hpSoftWareBanner;
	
	@CacheLookup
	@FindBy(xpath = "(//a[@href='/shoes'])[1]")
	public WebElement hpShoesBanner;
	
	@FindBy(xpath = "(//a[@href='/clothing'])[1]")
	public WebElement hpClothingBanner;
	
	@FindBy(xpath = "(//a[@href='/digital-downloads'])[1]")
	public WebElement hpDigitalDownloadsBanner;
	
	@FindBy(xpath = "(//a[@href='/books'])[1]")
	public WebElement hpBooksBanner;
	
	@CacheLookup
	@FindBy(xpath = "(//a[@href='/jewelry'])[1]")
	public WebElement hpJewelryBanner;
	
	@FindBy(xpath = "(//a[@href='/gift-cards'])[1]")
	WebElement hpGiftCardsBanner;
	
	
	@FindBy(xpath = "//input[@name='pollanswers-1']")
	public WebElement hpVoteExellentRadioBtn;
	
	@FindBy(xpath = "//button[@id='vote-poll-1']")
	public WebElement hpVoteBtn;
	
	
	@FindBy(xpath = "//*[@class='poll-results']")
	public WebElement hpPollResults;
	
	//Initiation
	public HomePageObjects() {
		PageFactory.initElements(driver, this);
	}
	//Methods for Home Page
	public void navigateLoginPage() {
		hpLoginLink.click();
	}
	public void navigateRegisterPage() {
		hpRegisterLink.click();
	}
	public void navigateWishlistPage() {
		hpWishlistLink.click();
	}
	public void navigateShoppingcartPage() {
		hpShoppingCartLink.click();
	}
	public void navigateSearchPage() {
		hpSearchBtn.click();
	}
	//Verifications
	public void verifyVoteIsSuccessful() {
		Assert.assertTrue(hpPollResults.isDisplayed(), "Error: vote is not successful");
		//proverka
		//Assert.assertFalse(hpPollResults.isDisplayed(), "Error: vote is successful");
	}
}








