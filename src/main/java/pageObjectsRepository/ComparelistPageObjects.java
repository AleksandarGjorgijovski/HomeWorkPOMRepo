package pageObjectsRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Base;
public class ComparelistPageObjects extends Base {
	//Defining Elements
	@FindBy(xpath = "//a[@class='clear-list']")
	public WebElement compListClearListBtn;
	@FindBy(xpath = "//button[@class='button-2 remove-button']")
	public WebElement compListRemoveBtn;
	@FindBy(xpath = "//div[@class='no-data']")
	public WebElement compListEmptyListTxt;
	

	//Initiation
public ComparelistPageObjects() {
	PageFactory.initElements(driver, this);
	}
//methods
public void waitClearCompareList() {
	// explicit wait condition
	WebDriverWait wait = new WebDriverWait(driver, 30);
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='clear-list']")));
}
//Verifications
public void verifyEmptyCompareListPage() {
	Assert.assertTrue(compListEmptyListTxt.isDisplayed(), "Error list is not Empty");
	//proverka
//	Assert.assertFalse	(compListEmptyListTxt.isDisplayed(), "Error list is Empty");
	
}

}