package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CommonPOM extends Base {

	// Defining Elements
	@FindBy(xpath = "//a[@class='ico-login']")
	WebElement hpLoginLink;
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

	// Methods
	public void waitElement(WebElement elementToWait) {
		// explicit wait condition
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(elementToWait));
		// or
		// wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(ElementToWait,
		// null));

		// presenceOfElementLocated condition
		// wait.until(ExpectedConditions.presenceOfElementLocated
		// (By.xpath("//*[@class='poll-results']")));
	}

	public void mouseOverAndClickAction(WebElement elementToHoverOver, WebElement clickAfterHover) {
		// Creating object of an Actions class
		Actions actionHover = new Actions(driver);

		// Performing the mouse hover action on the target element.
		actionHover.moveToElement(elementToHoverOver).perform();
		clickAfterHover.click();
	}

	public void navigateTo(WebElement pageLink) {
		pageLink.click();
	}

	public void scrollTo(WebElement scrollToElement) {
		// set the driver
		JavascriptExecutor jsScroll = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found
		jsScroll.executeScript("arguments[0].scrollIntoView();", scrollToElement);

//		//Locating element by link text and store in variable "Element"        		
//        WebElement voteBtnElement = driver.findElement(By.xpath("//button[@id='vote-poll-1']"));	
	}

	public void print(String string) {
		System.out.println(string);
	}

	public void selectFromDropManu(WebElement elementDropDown, String valueDropDown) {
		Select select = new Select(elementDropDown);
		select.selectByValue(valueDropDown);
	}

	public void selectFromDropManuByText(WebElement elementDropDown, String visibleText) {
		Select select = new Select(elementDropDown);
		select.selectByVisibleText(visibleText);
	}

	public void selectFromDropManuByIndex(WebElement elementDropDown, int index) {
		Select select = new Select(elementDropDown);
		select.selectByIndex(index);
	}

	public void selectFromDropManuByLastIndex(WebElement elementDropDown) {
		Select select = new Select(elementDropDown);
		select.selectByIndex(select.getOptions().size() - 1);
	}

	public void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void goBack() {
		driver.navigate().back();
	}

	public void goForward() {
		driver.navigate().forward();
	}
	public void verifyByPageSourceNotContains(String ItemSorcePage) {
		String pageSource = driver.getPageSource(); 
		Assert.assertFalse((pageSource.contains(ItemSorcePage)));
	}
	public void verifyByPageSourceContains(String ItemSorcePage) {
		String pageSource = driver.getPageSource(); 
		Assert.assertTrue((pageSource.contains(ItemSorcePage)));
	}
	public void pressEnter() throws AWTException {
		 Robot robot = new Robot();
		  robot.keyPress(KeyEvent.VK_ENTER); //press enter key
		  robot.keyRelease(KeyEvent.VK_ENTER); //release enter key
	}
	

}
