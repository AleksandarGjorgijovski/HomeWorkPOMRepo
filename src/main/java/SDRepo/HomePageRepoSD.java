package SDRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;

public class HomePageRepoSD extends Base {

	//Locators
		
	@FindBy(xpath = "//input[@id='os_username']")
	public WebElement lpUsernameField;

	@FindBy(xpath = "//input[@id='os_password']")
	public WebElement lpPasswordField;

	@FindBy(xpath = "//button[@id='js-login-submit']")
	public WebElement lpLoginBtn;
	
	@FindBy(xpath = "//label[contains(text(),'Keep me logged in')]")
	public WebElement lpKeepLoggedInBtn;
	
	@FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
	public WebElement lpForgotPasswordBtn;
	
	@FindBy(xpath = "//p[contains(text(),'Welcome! You can raise a IW Service Desk request f')]")
	public WebElement logInWelcomeText;
	
	//Initiation
	public HomePageRepoSD() {
		PageFactory.initElements(driver, this);
	}
	//Methods for Home Page
	
	public void loginUser(String userEmail, String userPassword) {
		lpUsernameField.sendKeys(userEmail);
		lpPasswordField.sendKeys(userPassword);
		lpLoginBtn.click();
	}
	
	//Verifications
	public void verifySuccessfulLogin() {
		Assert.assertTrue(logInWelcomeText.isDisplayed());
		}
	
	
}








