package SDRepo;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;


public class DashboardRepoSD extends Base{
	CommonPOM comm = new CommonPOM();
	TestData td = new TestData();
	

	//Defining WebElements
	@FindBy(xpath = "//a[@aria-label='Sponsorship Requests']")
	public WebElement dashboardSponsorshipMenu;
	
	@FindBy(xpath = "//strong[contains(text(),'Work from other office')]")
	public WebElement dashboardWorkFromOtherOfficeLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Request for Conference or Event attendance')]")
	public WebElement dashboardEventLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Request for Certificate')]")
	public WebElement dashboardCertificateLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Request for Learning Materials')]")
	public WebElement dashboardMaterialsLink;
		
	@FindBy(xpath = "//strong[contains(text(),'Team Building Request')]")
	public WebElement dashboardTeamBuildingLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Client requests an Onsite Visit')]")
	public WebElement dashboardOnsiteVisitLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Restaurant expenses request')]")
	public WebElement dashboardRestaurantLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Practice Budget expenses request')]")
	public WebElement dashboardPracticeBudgetLink;
	
	@FindBy(xpath = "//strong[contains(text(),'Gifts')]")
	public WebElement dashboardGiftsLink;
	
	
	
	
	//initiation
	public DashboardRepoSD() {
		PageFactory.initElements(driver, this);
	}

	//methods
	
	public void workFromOtherOfficePage() {
		comm.waitElement(dashboardSponsorshipMenu);
		dashboardSponsorshipMenu.click();
		comm.waitElement(dashboardWorkFromOtherOfficeLink);
		dashboardWorkFromOtherOfficeLink.click();
	}
	public void requestForConferenceOrEventPage() {
		comm.waitElement(dashboardSponsorshipMenu);
		dashboardSponsorshipMenu.click();
		comm.waitElement(dashboardEventLink);
		dashboardEventLink.click();
	}
	
	public void certificatePage() {
		comm.waitElement(dashboardSponsorshipMenu);
		dashboardSponsorshipMenu.click();
		comm.waitElement(dashboardCertificateLink);
		dashboardCertificateLink.click();
		
	}
	
	public void learningMaterialsPage() {
		comm.waitElement(dashboardSponsorshipMenu);
		dashboardSponsorshipMenu.click();
		comm.waitElement(dashboardMaterialsLink);
		dashboardMaterialsLink.click();
	}
	
	public void teamBuidlingPage() {
		comm.waitElement(dashboardSponsorshipMenu);
		dashboardSponsorshipMenu.click();
		comm.waitElement(dashboardTeamBuildingLink);
		dashboardTeamBuildingLink.click();
	}
	
	public void visitOnsiteVisit() {
		
		comm.waitElement(dashboardSponsorshipMenu);
		dashboardSponsorshipMenu.click();
		comm.waitElement(dashboardOnsiteVisitLink);
		dashboardOnsiteVisitLink.click();
		
	}
	
		//verifications
//	public void verifySuccessfulCheckoutAndEmpltyShoppingCart() {
//		Assert.assertTrue(chEmpltyShoppingCart.isDisplayed(), "Shopping cart is not empty");
//	}
}


























