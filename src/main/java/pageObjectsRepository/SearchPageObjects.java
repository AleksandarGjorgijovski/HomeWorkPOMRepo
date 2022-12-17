package pageObjectsRepository;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class SearchPageObjects extends Base {
	TestData td = new TestData();
	
	@FindBy(xpath = "//input[@id='small-searchterms']")
	public WebElement searchFiled ;
	
	@FindBy(xpath = "//button[@class='button-1 search-box-button']")
	public WebElement hpSearchBtn;
	
	@FindBy(xpath = "//input[@id='advs']")
	public WebElement advancedSearch;
	
	@FindBy(xpath = "//option[text()='Computers >> Notebooks']")
	public WebElement searchComNoteDropManu;
	
	@FindBy(xpath = "//button[@class='button-1 search-button']")
	public WebElement searchBtnPg;
	
	@FindBy(xpath = "//select[@id='cid']")
	public WebElement searchCategoryDropManu;
	
	@FindBy(xpath = "//h2[@class='product-title']")
	public WebElement searchProductTitle;
	
	@FindBy(xpath = "//input[@class='search-text']")
	public WebElement serchSearchKeyword;
	
	@FindBy(xpath = "//input[@id='isc']")
	public WebElement serchAutoSubCategoruCheckBox;
	
	@FindBy(xpath = "//select[@id='products-orderby']")
	public WebElement productOrderBy;
	
	@FindBy(xpath = "")
	public WebElement searchBlankAlertMsg;
	
	//Initiation
		public SearchPageObjects() {
			PageFactory.initElements(driver, this);
	
	
		}
		//methods
		public void verifyContainsForEachLoop(String searchItem) {
			List<WebElement> listElements = driver.findElements(By.xpath("//h2[@class='product-title']"));
			
			//Gi zacuvam site elementi so sodrzat "Product-Title" gi stavam vo lista(nikeElements) od WebElements
			for (WebElement element : listElements ) {//koristam for loop, za sekoj WebElement od  listata(nikeElements) da go zemi Tekstot
				String txtElements = element.getText().toLowerCase();
				System.out.println("Name of the find products: " + txtElements);//provermav dali vo teksot postoi Nike
				Assert.assertTrue(txtElements.contains(searchItem.toLowerCase()), "Error: the" + searchItem + "is not displayed");
			}
			
		}
		//verifications
		public void verifyByPageSource(String ItemSorcePage) {
			String pageSource = driver.getPageSource(); 
			Assert.assertFalse((pageSource.contains(ItemSorcePage)));
		}
		public void verifyAlertBlankSearch() {
			String expAlertMsg = "Please enter some search keyword";
			
			String actAlertMsg = driver.switchTo().alert().getText();
			
			Assert.assertEquals(actAlertMsg, expAlertMsg);
			
			driver.switchTo().alert().accept();
		}
		public void verifySuccessfulBlockOnAttack() {
			Assert.assertEquals(driver.getTitle(), td.blockPageTitle);
		}
}

