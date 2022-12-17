package pageObjectsRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import base.CommonPOM;
import testData.TestData;
public class CheckoutPageObjects extends Base{
	CommonPOM comm = new CommonPOM();
	TestData td = new TestData();
	RegisterPageObjects rp = new RegisterPageObjects();

	//Defining WebElements
	@FindBy(xpath = "//input[@id='BillingNewAddress_FirstName']")
	public WebElement billFirstName;
	@FindBy(xpath = "//input[@id='BillingNewAddress_LastName']")
	public WebElement billLastName;
	@FindBy(xpath = "//input[@id='BillingNewAddress_Email']")
	public WebElement billEmail;
	
	@FindBy(xpath = "//select[@id='BillingNewAddress_CountryId']")
	public WebElement billCountryList;
	@FindBy(xpath = "//input[@id='BillingNewAddress_City']")
	public WebElement billCityFiled;
	@FindBy(xpath = "//input[@id='BillingNewAddress_Address1']")
	public WebElement billAddress1Field;
	@FindBy(xpath = "//input[@id='BillingNewAddress_ZipPostalCode']")
	public WebElement billZipCodeField;
	@FindBy(xpath = "//input[@id='BillingNewAddress_PhoneNumber']")
	public WebElement billPhoneField;
	@FindBy(xpath = "(//button[@class='button-1 new-address-next-step-button'])[1]")
	public WebElement chBillContinueBtn;
	@FindBy(xpath = "//input[@type='checkbox']")
	public WebElement chBillShipToTheSameAddressCheckbox;
	@FindBy(xpath = "//select[@id='shipping-address-select']")
	public WebElement chSelectShippingAddressBook;
	@FindBy(xpath = "//select[@id='billing-address-select']")
	public WebElement chSelectBillingAddressBook;
	
	@FindBy(xpath = "//select[@id='ShippingNewAddress_CountryId']")
	public WebElement shipNewCountryList;
	@FindBy(xpath = "//input[@id='ShippingNewAddress_City']")
	public WebElement shipCityFiled;
	@FindBy(xpath = "//input[@id='ShippingNewAddress_Address1']")
	public WebElement shipAddress1Field;
	@FindBy(xpath = "//input[@id='ShippingNewAddress_ZipPostalCode']")
	public WebElement shipZipCodeField;
	@FindBy(xpath = "//input[@id='ShippingNewAddress_PhoneNumber']")
	public WebElement shipPhoneField;
	@FindBy(xpath = "(//button[@class='button-1 new-address-next-step-button'])[2]")
	public WebElement chShipContinueBtn;
	
	@FindBy(xpath = "//input[@value='Ground___Shipping.FixedByWeightByTotal']")
	public WebElement chShipMetodGorund;
	@FindBy(xpath = "//button[@class='button-1 shipping-method-next-step-button']")
	public WebElement chShipMetodConBtn;
	
	@FindBy(xpath = "//input[@value='Payments.CheckMoneyOrder']")
	public WebElement chPayMethodCheck;
	@FindBy(xpath = "//input[@value='Payments.Manual']")
	public WebElement chPayMethodCard;
	@FindBy(xpath = "//button[@class='button-1 payment-method-next-step-button']")
	public WebElement chPayMethodConBtn;
	
	@FindBy(xpath = "//select[@id='CreditCardType']")
	public WebElement chPayInfoSelectCardType;
	@FindBy(xpath = "//input[@id='CardholderName']")
	public WebElement chPayInfoNameField;
	@FindBy(xpath = "//input[@id='CardNumber']")
	public WebElement chPayInfoCardNumberField;
	@FindBy(xpath = "//select[@id='ExpireMonth']")
	public WebElement chPayInfoMonthList;
	@FindBy(xpath = "//select[@id='ExpireYear']")
	public WebElement chPayInfoYearList;
	@FindBy(xpath = "//input[@id='CardCode']")
	public WebElement chPayInfoCodeField;
	@FindBy(xpath = "//button[@class='button-1 payment-info-next-step-button']")
	public WebElement chPayInfoConBtn;
	
	@FindBy(xpath = "//button[@class='button-1 confirm-order-next-step-button']")
	public WebElement chConfirmOrderConBtn;
	@FindBy(xpath = "//button[@class='button-1 order-completed-continue-button']")
	public WebElement chOrderCopmliteConBtn;
	
	@FindBy(xpath = "//span[@class='cart-qty' and text()='(0)']")
	public WebElement chEmpltyShoppingCart;
	
	@FindBy(xpath = "//button[text()='Checkout as Guest']")
	public WebElement chCheckoutAsGuest;
	
	//initiation
	public CheckoutPageObjects() {
		PageFactory.initElements(driver, this);
	}

	//methods
	public void validBillAddressMandatoryField(String selectCountryValue, String city, String address1, String zip, String phoneNumber ) {
		comm.selectFromDropManu(billCountryList, selectCountryValue);
		billCityFiled.sendKeys(city);
		billAddress1Field.sendKeys(address1);
		billZipCodeField.sendKeys(zip);
		billPhoneField.sendKeys(phoneNumber);
		chBillContinueBtn.click();
	}
	public void validBillAddressAllMandatoryField(String firstName, String lastName, String email, String selectCountryValue, String city, String address1, String zip, String phoneNumber ) {
		billFirstName.sendKeys(firstName);
		billLastName.sendKeys(lastName);
		billEmail.sendKeys(email);
		comm.selectFromDropManu(billCountryList, selectCountryValue);
		billCityFiled.sendKeys(city);
		billAddress1Field.sendKeys(address1);
		billZipCodeField.sendKeys(zip);
		billPhoneField.sendKeys(phoneNumber);
		chBillContinueBtn.click();
	}
	public void validShippingAddressMandatoryField(String selectCountryValue, String city, String address1, String zip, String phoneNumber ) {
		comm.selectFromDropManu(shipNewCountryList, selectCountryValue);
		shipCityFiled.sendKeys(city);
		shipAddress1Field.sendKeys(address1);
		shipZipCodeField.sendKeys(zip);
		shipPhoneField.sendKeys(phoneNumber);
		chShipContinueBtn.click();
	}
	public void shippingMethod(WebElement chooseShippingMethod) {
		chooseShippingMethod.click();
		chShipMetodConBtn.click();
	}
	public void paymentMethod(WebElement choosePaymentMethod) {
		choosePaymentMethod.click();
		chPayMethodConBtn.click();
	}
	public void paymentInformation(String firstName, String lastName, String mustInclude16numbers, String expireMonth, String expireYear, String cardCode) {
		comm.selectFromDropManu(chPayInfoSelectCardType, td.validCardTypeVisa);
		chPayInfoNameField.sendKeys(firstName + " " + lastName);
		chPayInfoCardNumberField.sendKeys(mustInclude16numbers);
		comm.selectFromDropManu(chPayInfoMonthList, expireMonth);
		comm.selectFromDropManu(chPayInfoYearList, expireYear);
		chPayInfoCodeField.sendKeys(cardCode);
		chPayInfoConBtn.click();
	}
	public void ifBillingAddressBookIsPopulatedOrNot(int index) {
		int billingAddressBook = driver.findElements(By.xpath("//label[@for='billing-address-select']")).size();
		if (billingAddressBook == 0) {
			validBillAddressMandatoryField(td.validMacedoniaValue, td.validCityBitola, td.validAddress, td.validZipCode, td.validPhoneNumber);
		}else if (billingAddressBook > 0){
			comm.waitElement(chSelectBillingAddressBook);
			comm.selectFromDropManuByIndex(chSelectBillingAddressBook, index);
			chBillContinueBtn.click();
		}
	}
	
		//verifications
	public void verifySuccessfulCheckoutAndEmpltyShoppingCart() {
		Assert.assertTrue(chEmpltyShoppingCart.isDisplayed(), "Shopping cart is not empty");
	}
}


























