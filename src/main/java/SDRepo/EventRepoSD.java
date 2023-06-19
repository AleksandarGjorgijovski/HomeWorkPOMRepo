package SDRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;

public class EventRepoSD extends Base {
	TestData testData = new TestData();
	CommonPOM comm = new CommonPOM();

	@FindBy(xpath = "//input[@id='summary']")
	public WebElement eventSummaryField;

	@FindBy(xpath = "//span[contains(text(),'None')]")
	public WebElement eventPracticeDropdown;

	@FindBy(xpath = "//input[@id='customfield_15804']")
	public WebElement eventEventNameField;

	@FindBy(xpath = "//input[@id='customfield_15805']")
	public WebElement eventLocationField;

	@FindBy(xpath = "//span[@class='sd-calendar-icon aui-icon aui-icon-large aui-iconfont-calendar']")
	public WebElement eventCalendarBtn;

	@FindBy(xpath = "//label[contains(text(),'Presentation')]")
	public WebElement eventPresentationChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Company Brochure')]")
	public WebElement eventCompanyBrochureChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Product Brochure')]")
	public WebElement eventProductBrochureChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Business Cards')]")
	public WebElement eventBusinessCardsChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Flyers')]")
	public WebElement eventFlyersChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Banner')]")
	public WebElement eventBannerChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Case Study')]")
	public WebElement eventCaseStudyChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Blog Post')]")
	public WebElement eventBlogPostChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Promo materials (mug, t-shirt, notebook, pen, back')]")
	public WebElement eventPromoMaterialsChkBtn;

	@FindBy(xpath = "//label[contains(text(),'Social Media Posts')]")
	public WebElement eventSocialMediaPostsChkBtn;

	@FindBy(xpath = "//textarea[@id='customfield_15802']")
	public WebElement eventReasonField;

	@FindBy(xpath = "//input[@id='customfield_15900']")
	public WebElement eventNumberPeopleField;

	@FindBy(xpath = "//input[@id='s2id_autogen3']")
	public WebElement eventGoingPeopleSerachBtn;

	@FindBy(xpath = "(//ul[@class=\"select2-choices\"])[2]")
	public WebElement eventBranchCoverDropdown;

	@FindBy(xpath = "//textarea[@id='customfield_15901']")
	public WebElement eventCostDescField;

	@FindBy(xpath = "//textarea[@id='customfield_15807']")
	public WebElement eventExpectedResultsField;

	@FindBy(xpath = "//input[@id='customfield_15803']")
	public WebElement eventTotalCostField;

	@FindBy(xpath = "//button[contains(text(),'Create')]")
	public WebElement eventCreateBtn;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement eventCancelBtn;
	
	@FindBy(xpath = "//body/div[@id='select2-drop']/ul[1]")
	public WebElement eventBranchList;
	

	public EventRepoSD() {
		PageFactory.initElements(driver, this);
	}

	// methods
	public void eventEnterSummary(String summary) {
		
		eventSummaryField.sendKeys(summary);
	}

	public void eventEnterName(String name) {

		eventEventNameField.sendKeys(name);
	}
	
	public void eventEnterLocation(String location) {
		
		eventLocationField.sendKeys(location);
	}

	public void eventChoosePractice(String practice) throws InterruptedException {
		eventPracticeDropdown.click();
		Thread.sleep(3000);

		switch (practice) {
		case "QA":
			driver.findElement(By.xpath("//div[contains(text(),'QA')]")).click();
			break;
		case "Cloud":
			driver.findElement(By.xpath("//div[contains(text(),'Cloud')]")).click();
			break;
		case "Java":
			driver.findElement(By.xpath("//div[contains(text(),'Java')]")).click();
			break;
		case "Business Support":
			driver.findElement(By.xpath("//div[contains(text(),'Business Support')]")).click();
			break;
		case "Data Management":
			driver.findElement(By.xpath("//div[contains(text(),'Data Management')]")).click();
			break;
		case "Support":
			driver.findElement(By.xpath("//div[contains(text(),'Support')]")).click();
			break;
		case "Integration":
			driver.findElement(By.xpath("//div[contains(text(),'Integration')]")).click();
			break;
		case "PHP":
			driver.findElement(By.xpath("//div[contains(text(),'PHP')]")).click();
			break;

		default:
			System.out.println("Unknown Branch");
			break;
		}
	}
	public void eventChooseBrachSwitch(String branch) {
		
		eventBranchCoverDropdown.click();
		comm.waitElement(eventBranchList);
		
	    switch (branch) {
	        case "IWC Partners Skopje":
	            driver.findElement(By.xpath("(//div[contains(text(),'⋮IWC Partners Skopje')])[2]")).click();
	            break;
	        case "IWC":
	           	driver.findElement(By.xpath("(//div[contains(text(),'⋮IWC')])[2]")).click();
	            break;
	        case "Client":
	        	driver.findElement(By.xpath("(//div[contains(text(),'Client')])[2]")).click();
	            break;
	        case "Employee":
	        	driver.findElement(By.xpath("(//div[contains(text(),'Employee')])[2]")).click();
	        	break;
	        case "IWC Novi Sad":
	        	driver.findElement(By.xpath("(//div[contains(text(),'⋮IWC Novi Sad')])[2]")).click();
	        	break;
	        case "IWC Netherlands":
	        	driver.findElement(By.xpath("(//div[contains(text(),'⋮IWC Netherlands')])[2]")).click();
	        	break;
	        case "IWFirstCall":
	        	driver.findElement(By.xpath("(//div[contains(text(),'⋮IWFirstCall')])[2]")).click();
	        	break;
	        case "IWEnvision":
	        	driver.findElement(By.xpath("(//div[contains(text(),'⋮IWEnvision')])[2]")).click();
	        	break;
	                       
	        default:
	            System.out.println("Unknown Branch");
	            break;
	    }
	}
}
