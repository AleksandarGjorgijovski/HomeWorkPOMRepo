package pageObjectsRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class PlpObjects extends Base {
	TestData td = new TestData();
	HomePageObjects hp = new HomePageObjects();

	// Defining Elements
	@FindBy(xpath = "//a[@href='/notebooks']")
	public WebElement plpNotebooksSubCategory;

	@CacheLookup
	@FindBy(xpath = "//h2[@class='title']/a[@href='/clothing']")
	public WebElement plpClothingSubCategory;

	@FindBy(xpath = "//input[@id='attribute-option-6']")
	public WebElement plpFilterByIntelI5ChBox;

	@FindBy(xpath = "//input[@id='attribute-option-9']")
	public WebElement plpFilterByMemory8GB;

	@FindBy(xpath = "//a[@class='viewmode-icon list']")
	public WebElement listViewbtn;

	@CacheLookup
	@FindBy(xpath = "//a[@href='/night-visions']")
	public WebElement plpNightVisionLink;

	@CacheLookup
	@FindBy(xpath = "//h2[@class='product-title']/child::a[@href='/elegant-gemstone-necklace-rental']")
	public WebElement plpNecklaceRental;

	@FindBy(xpath = "//ul[@class='sublist first-level']/descendant::a[@href='/cell-phones']")
	public WebElement plpCellPhones;

	@FindBy(xpath = "(//button[@class='button-2 add-to-compare-list-button'])[2]")
	public WebElement plpCompareHtcOne;

	@FindBy(xpath = "(//button[@class='button-2 add-to-compare-list-button'])[3]")
	public WebElement plpCompareNokia;

	@FindBy(xpath = "//a[@href='/software' and @title]")
	public WebElement plpSoftwareSubCategory;

	@FindBy(xpath = "//a[@href='/windows-8-pro' and @title]")
	public WebElement plpWindows8;

	@FindBy(xpath = "//a[@href='/windows-8-pro' ]/ancestor::div[@class='details']/descendant::button[text()='Add to cart']")
	public WebElement plpWindows8addToCart;

	@FindBy(xpath = "//span[@itemprop='name' and text()='Computers']")
	public WebElement plpComputerBreadcrumbs;

	@FindBy(xpath = "//a[@title='Home']")
	public WebElement plpHomeBreadcrumbs;

	@FindBy(xpath = "//a[@href='/compareproducts']")
	public WebElement plpCompareProductList;

	@FindBy(xpath = "//div[@class='description']")
	public WebElement plpDescriptionTxt;

	@FindBy(xpath = "(//a[@href='/camera-photo'])[1]")
	public WebElement plpCameraSubCategory;

	@FindBy(xpath = "//h2[@class='product-title']/child::*[contains(text(),'Leica')]")
	public WebElement plpLeicaLink;

	@FindBy(xpath = "//a[@href='/nike-tailwind-loose-short-sleeve-running-shirt']")
	public WebElement plpNikeTailwindLink;

	@FindBy(xpath = "//div[@class='description']")
	public WebElement plpItemDescription;

	// initi
	public PlpObjects() {
		PageFactory.initElements(driver, this);
	}

	// methods
	public void addToCartFromPLP(WebElement plpAddtoCartBtn) {
		plpAddtoCartBtn.click();

	}

	// verifications
	public void verifyComputerPageisDisplayed() {
		Assert.assertEquals(driver.getTitle(), td.computersPageTitle);
	}

	public void verifyWindows8PageisDisplayed() {
		Assert.assertEquals(driver.getTitle(), td.windows8PageTitle);
	}

	public void verifyHomePageisDisplayed() {
		Assert.assertEquals(driver.getTitle(), td.homePageTitle);
	}

	public void verifyWithForLoopDescription(String filterCpu, String filterMemory) {
		int totalNumItems = driver.findElements(By.xpath("//div[@class='description']")).size();
		System.out.println("Total Number of items: " + totalNumItems);
		for (int i = 1; i <= totalNumItems; i++) {
			String findItemDescription = driver.findElement(By.xpath("(//div[@class=\"description\"])[" + i + "]"))
					.getText().toUpperCase();
			System.out.println("Description of the itmes: " + findItemDescription);
			Assert.assertTrue(findItemDescription.contains(filterCpu.toUpperCase()),
					"Error: the item with " + filterMemory + " and " + filterCpu + " is not displayed.");
			Assert.assertTrue(findItemDescription.contains(filterMemory.toUpperCase()),
					"Error: the item with " + filterMemory + " and " + filterCpu + " is not displayed.");
		}
		System.out.println(
				"Currently shopping by: CPU Type - intel Core " + filterCpu + "\r\n" + "Memory: " + filterMemory);
	}

	public void verifyWithForEachLoopDescription(String filterCpu, String filterMemory) {
		List<WebElement> listItems = driver.findElements(By.xpath("//div[@class='description']"));
		for (WebElement items : listItems) {
			String itmesTxt = items.getText().toLowerCase();
			System.out.println("Description of the itmes: " + itmesTxt);
			Assert.assertTrue(itmesTxt.contains(filterCpu.toLowerCase()),
					"Error: the item with " + filterMemory + " and " + filterCpu + " is not displayed.");
			Assert.assertTrue(itmesTxt.contains(filterMemory.toLowerCase()),
					"Error: the item with " + filterMemory + " and " + filterCpu + " is not displayed.");
		}
		System.out.println(
				"Currently shopping by: CPU Type: intel Core " + filterCpu + "\r\n" + "Memory: " + filterMemory);

	}
}
