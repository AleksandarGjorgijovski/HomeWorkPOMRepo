package pageObjectsRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import testData.TestData;

public class LoginPageObjects extends Base {
	TestData userTestData = new TestData();

	@FindBy(xpath = "//input[@id='Email']")
	public WebElement lpEmailField;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement lpPasswordField;
	
	@FindBy(xpath = "//button[text()='Log in']")
	public WebElement lpLoginBtn;
	
	@FindBy(xpath = "//input[@id='RememberMe']")
	WebElement lpRremember;
	
	@FindBy(xpath = "//a[text()='Forgot password?']")
	WebElement lpForgotPasswordLink;
	
	@FindBy(xpath = "//button[@class='button-1 register-button']")
	public WebElement lpRegisterLoginBtn;
	
	@FindBy(xpath = "//button[@name='send-email']")
	WebElement lpRecoveryBtn;
	
	@FindBy(xpath = "//p[text()='Email with instructions has been sent to you.']")
	WebElement lpRecoverySuccMsg;
	
	@FindBy(xpath = "//p[text()='Email not found.']")
	WebElement lpRecoveryUnsuccMsg;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	WebElement hpLogoutLink;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	WebElement lpEmptyEmailMsg;
	
	public LoginPageObjects() {
		PageFactory.initElements(driver, this);
	}
	// methods
	public void loginUser(String userEmail, String userPassword) {
		lpEmailField.sendKeys(userEmail);
		lpPasswordField.sendKeys(userPassword);
	}
	public void loginUserAndLoginBtn(String userEmail, String userPassword) {
		lpEmailField.sendKeys(userEmail);
		lpPasswordField.sendKeys(userPassword);
		lpLoginBtn.click();
	}
	
	public void loginForgetPassword(String userEmail) {
		lpEmailField.sendKeys(userEmail);
		lpForgotPasswordLink.click();
		Assert.assertEquals(driver.getTitle(), userTestData.passwordRecoveryTitle);
		lpEmailField.sendKeys(userEmail);
		lpRecoveryBtn.click();
	}
	// verifications
	public void verifySuccessfulLogin() {
		Assert.assertTrue(hpLogoutLink.isDisplayed(),"User is not logged in successfully");
	}
	public void verifyUnsuccessfilLogin() {
		Assert.assertEquals(driver.getTitle(), userTestData.loginPageTitle);
	}
	public void verifyUnsuccessfulLoginEmpltyEmail() {
		Assert.assertTrue(lpEmptyEmailMsg.isDisplayed(), "Error message is not displayed");
		//proverka
		//Assert.assertFalse(lpEmptyEmailMsg.isDisplayed(), "Error message is displayed");
	}
	public void verifyLoginPageOpened() {
		Assert.assertEquals(driver.getTitle(), userTestData.loginPageTitle);
	}
	public void verifySuccesfulRecovery() {
		Assert.assertTrue(lpRecoverySuccMsg.isDisplayed(), "Recovery was unsuccessful!");
		System.out.println(lpRecoverySuccMsg.getText());
	}
	public void verifyUnsuccesfulrecovery() {
		Assert.assertTrue(lpRecoveryUnsuccMsg.isDisplayed());
		System.out.println(lpRecoveryUnsuccMsg.getText());
	}
}
