package SDRepo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;

public class TeamBuildingRepoSD extends Base {
	TestData testData = new TestData();
	CommonPOM comm = new CommonPOM();

	@FindBy(xpath = "//span[contains(text(),'None')]")
	public WebElement teamBuildPracticeDropdown;

	@FindBy(xpath = "//input[@id='customfield_20401']")
	public WebElement teamBuildCurrentEmployedField;

	@FindBy(xpath = "//textarea[@id='customfield_20400']")
	public WebElement teamBuildOtherMembersField;

	@FindBy(xpath = "//input[@id='customfield_20403']")
	public WebElement teamBuildBudgetPerPersonField;

	@FindBy(xpath = "//textarea[@id='customfield_16701']")
	public WebElement teamBuildWhyField;

	@FindBy(xpath = "//textarea[@id='customfield_16700']")
	public WebElement teamBuildWhatField;

	@FindBy(xpath = "//span[@class='sd-calendar-icon aui-icon aui-icon-large aui-iconfont-calendar']")
	public WebElement teamBuildCalendarBtn;

	@FindBy(xpath = "//input[@id='customfield_19200']")
	public WebElement teamBuildWhereField;
	
	@FindBy(xpath = "//textarea[@id='customfield_20402']")
	public WebElement teamBuildCostField;

	@FindBy(xpath = "//input[@id='customfield_15803']")
	public WebElement teamBuildTotalCostField;

	@FindBy(xpath = "//span[contains(text(),'Medium')]")
	public WebElement teamBuildRiskDropdown;

	@FindBy(xpath = "//div[@id='select2-drop']")
	public WebElement teamBuildRiskList;
	
	@FindBy(xpath = "//button[contains(text(),'Create')]")
	public WebElement teamBuildCreateBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement teamBuildCancelBtn;

	public TeamBuildingRepoSD() {
		PageFactory.initElements(driver, this);
	}

	public void teamBuildChoosePractice(String practice) throws InterruptedException {
		teamBuildPracticeDropdown.click();
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

	public void teamBuildChooseRiskLevel(String riskLevel) {

		teamBuildRiskDropdown.click();
		comm.waitElement(teamBuildRiskList);

		switch (riskLevel) {
		case "Critical":
			driver.findElement(By.xpath("//div[contains(text(),'Critical')]")).click();
			break;
		case "High":
			driver.findElement(By.xpath("//div[contains(text(),'High')]")).click();
			break;
		case "Medium":
			driver.findElement(By.xpath("//div[contains(text(),'Medium')]")).click();
			break;
		case "Low":
			driver.findElement(By.xpath("//div[contains(text(),'Low')]")).click();
			break;

		default:
			System.out.println("Deafult Medium Risk");
			break;
		}
	}
}
