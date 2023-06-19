package SDRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;

public class OnsiteVisitRepoSD extends Base{

	TestData testData = new TestData();
	CommonPOM comm = new CommonPOM();
	
	
	@FindBy(xpath = "//input[@id='summary']")
	public WebElement onsiteSummaryField;
	
	@FindBy(xpath = "//span[contains(text(),'None')]")
	public WebElement onsiteCientNameDropdown;
	
	@FindBy(xpath = "//input[@id='customfield_19200']")
	public WebElement onsiteWhereField;
	
	@FindBy(xpath = "(//span[@class='sd-calendar-icon aui-icon aui-icon-large aui-iconfont-calendar'])[1]")
	public WebElement onsiteStartCalendarBtn;
	
	@FindBy(xpath = "(//span[@class='sd-calendar-icon aui-icon aui-icon-large aui-iconfont-calendar'])[2]")
	public WebElement onsiteEndCalednarBtn;
	
	@FindBy(xpath = "//div[@id=\"s2id_customfield_19101\"]")
	public WebElement onsiteBranchDropdown;
	
	@FindBy(xpath = "//span[contains(text(),'Medium')]")
	public WebElement onsiteRistDropdown;
	
	@FindBy(xpath = "//button[contains(text(),'Create')]")
	public WebElement onsiteCreateBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement onsiteCancelBtn;
	
	
	public OnsiteVisitRepoSD() {
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void onsitePopulateSummaryField(String summary) {
		
		onsiteSummaryField.sendKeys(summary);
		
	}
}
