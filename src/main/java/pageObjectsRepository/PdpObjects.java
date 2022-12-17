package pageObjectsRepository;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class PdpObjects extends Base {
	// Object od TestData klasata sto ke se koristi vo ovaa klasa
	TestData td = new TestData();
	PlpObjects plp;

	// Difining Elements
	@FindBy(xpath = "//a[@href=\"/download/sample/34\"]")
	public WebElement pdpNightVisionDownloadSample;
	
	@FindBy(xpath = "//a[text()='Add your review']")
	public WebElement reviewLink;
	
	@FindBy(xpath = "//input[@class='review-title']")
	WebElement reviewTitleField;
	
	@FindBy(xpath = "//textarea[@class='review-text']")
	WebElement reviewTextField;
	
	@FindBy(xpath = "//input[@aria-label='Bad']")
	WebElement ratingBad;
	
	@FindBy(xpath = "//input[@aria-label='Not good]")
	WebElement ratingNotGood;
	
	@FindBy(xpath = "//input[@aria-label='Not bad but also not excellent']")
	WebElement ratingNotBadNotExcellent;
	
	@FindBy(xpath = "//input[@aria-label='Good']")
	WebElement ratingGood;
	
	@FindBy(xpath = "//input[@aria-label='Excellent']")
	public WebElement ratingExcellent;
	
	@FindBy(xpath = "//button[@name='add-review']")
	public WebElement submitReviewBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement reviewHelpfulYes;
	
	@FindBy(xpath = "//span[contains(text(),'No')]")
	WebElement reviewHelpfulNo;
	
	@FindBy(xpath = "//input[@class='qty-input']")
	public WebElement quantityField;
	
	@FindBy(xpath = "//button[@class='button-1 add-to-cart-button']")
	public WebElement addCartBtn;
	
	@FindBy(xpath = "(//button[@class='button-2 add-to-wishlist-button'])[1]")
	public WebElement addWishlistBtn;
	
	@FindBy(xpath = "(//button[@class='button-2 add-to-compare-list-button'])[1]")
	WebElement addComparelistBtn;
	
	@FindBy(xpath = "//button[@class='button-2 email-a-friend-button']")
	WebElement emailFriendBtn;
	
	@FindBy(xpath = "//a[@href='/levis-511-jeans']")
	public WebElement levisLink;
	
	
	@FindBy(xpath = "//a[@href='/adidas-consortium-campus-80s-running-shoes']")
	public WebElement adidasLink;
	
	@FindBy(xpath = "//select[@data-attr='9']")
	public WebElement adidasSizeDropBox;
	
	@FindBy(xpath = "//a[@href='/first-prize-pies']")
	public WebElement firstPrizePiesLink;

	
	@FindBy(xpath = "//span[@class='price-value-30']")
	public WebElement levisTabelPrice435;
	
	@FindBy(xpath = "(//td[@class='item-price'])[1]")
	public WebElement levisTabelPrice40;
	
	@FindBy(xpath = "(//td[@class='item-price'])[2]")
	public WebElement levisTabelPrice38;
	
	@FindBy(xpath = "(//td[@class='item-price'])[3]")
	public WebElement levisTabelPrice35;
	
	@FindBy(xpath = "//select[@name='product_attribute_11']")
	public WebElement pdpSizeDropBox;
	
	@FindBy(xpath = "//div[@class='sku']/child::*[@class='value']")
	public WebElement pdpSkuCode;

	@CacheLookup
	@FindBy(xpath = "(//input[@class='datepicker hasDatepicker'])[1]")
	public WebElement pdpRentalStartDate;
	@CacheLookup
	@FindBy(xpath = "(//input[@class='datepicker hasDatepicker'])[2]")
	public WebElement pdpRentalEndDate;

	// initi
	public PdpObjects() {
		PageFactory.initElements(driver, this);
	}

	// methods
	public void insertReview(String title, String text) {
		reviewTitleField.sendKeys(title);
		reviewTextField.sendKeys(text);
	}

	public void pdpAddToWishlist() {
		addWishlistBtn.click();
	}

	public String fetchSkuCodePdp() {
		return pdpSkuCode.getText();
	}

	public void insertQuantityPDP(int quantityNumber) {
		quantityField.clear();
		quantityField.sendKeys(Integer.toString(quantityNumber));
	}

	// verification
	public void verifySuccessfulDownload(String downloadedFile) {
		String filesDirectory = "C:\\Users\\Aleksandar\\Downloads";
		File folder = new File(filesDirectory);
		File[] listOfFiles = folder.listFiles();
		boolean found = false;
		File f = null;
		// Look for the file in the files
		for (File listOfFile : listOfFiles) {
			if (listOfFile.isFile()) {
				String fileName = listOfFile.getName();
				System.out.println("File " + listOfFile.getName());
				if (fileName.matches(downloadedFile)) {
					f = new File(fileName);
					found = true;
				}
			}
		}
		Assert.assertTrue(found, "Downloaded document is not found");
		f.deleteOnExit();

	}

	public void verifySuccesfulReview(String title, String text) {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']")).isDisplayed(),
				"Error: review was not seccesfully added");
		System.out.println("Product review is successfully added");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='review-title']")).getText().contains(title),
				"Error: review Title is not displayed");
		System.out.println("Title" + title + " is displayed");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text-body']")).getText().contains(text),
				"Error: review Title is not displayed");
		System.out.println("Review" + text + " is displayed");

	}

	public void ChooseStartDateCalendar(String startMonth, String startYear, String startDay) {
		// repeat the loop till finding the expected month and year
		while (true) {
			String displayedMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String displayedtYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

			if (displayedMonth.equals(startMonth) && displayedtYear.equals(startYear)) {
				break;
				// click on the next button till find the expected month and year
			} else {
				driver.findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
			}
		}
		// get to list all the dates from the month
		List<WebElement> allStartDates = driver
				.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/descendant::td"));
		for (WebElement element : allStartDates) {
			String startDate = element.getText();
			// if the expected and actual day is found perform click
			if (startDate.equals(startDay)) {
				element.click();
				break;
			}
		}
	}

	public void ChooseEndDateCalendar(String endMonth, String endYear, String endDay) {
		// repeat the loop till finding the expected month and year
		while (true) {
			String displayedMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String displayedtYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

			if (displayedMonth.equals(endMonth) && displayedtYear.equals(endYear)) {
				break;
				// click on the next button till find the expected month and year
			} else {
				driver.findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
			}
		}
		// get to list all the dates from the month
		List<WebElement> allStartDates = driver
				.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/descendant::td"));
		for (WebElement element : allStartDates) {
			String startDate = element.getText();
			// if the expected and actual day is found perform click
			if (startDate.equals(endDay)) {
				element.click();
				break;
			}
		}
	}

	public void ChooseStartDateCalendarLOOP(String startMonth, String startYear, String startDay) {
		// repeat the loop till finding the expected month and year
		while (true) {
			String displayedMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String displayedYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

			if (displayedMonth.equals(startMonth) && displayedYear.equals(startYear)) {
				break;
				// click on the next button till find the expected month and year
			} else {
				driver.findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
			}
		}
		// Store all web elements in list
		List<WebElement> allStartDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/descendant::td"));

		// it will run till the number of size
		for (int i = 0; i < allStartDates.size(); i++) {
			// will take all the elements and capture the text
			String startDate = allStartDates.get(i).getText();
			// if the text that is capture is "15" go inside the if statement and click on
			// it, otherwise break
			if (startDate.equals(startDay)) { // or date.equalsIgnoreCase("15")
				allStartDates.get(i).click();
				break;
			}
		}
	}
	public void ChooseEndDateCalendarLOOP(String endMonth, String endYear, String endDay) {
		// repeat the loop till finding the expected month and year
		while (true) {
			String displayedMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String displayedYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			
			if (displayedMonth.equals(endMonth) && displayedYear.equals(endYear)) {
				break;
				// click on the next button till find the expected month and year
			} else {
				driver.findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
			}
		}
		// Store all web elements in list
		List<WebElement> allStartDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/descendant::td"));
		
		// it will run till the number of size
		for (int i = 0; i < allStartDates.size(); i++) {
			// will take all the elements and capture the text
			String startDate = allStartDates.get(i).getText();
			// if the text that is capture is "15" go inside the if statement and click on
			// it, otherwise break
			if (startDate.equals(endDay)) { // or date.equalsIgnoreCase("15")
				allStartDates.get(i).click();
				break;
			}
		}
	}

}
