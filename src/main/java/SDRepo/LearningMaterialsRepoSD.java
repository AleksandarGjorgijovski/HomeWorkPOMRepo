package SDRepo;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;

public class LearningMaterialsRepoSD extends Base {

	TestData testData = new TestData();
	CommonPOM comm = new CommonPOM();

	@FindBy(xpath = "//input[@id='summary']")
	public WebElement learMaterialsSummaryField;

	@FindBy(xpath = "//span[contains(text(),'Search for a user')]")
	public WebElement learMaterialsUserBtn;

	@FindBy(xpath = "//body/div[@id='select2-drop']/div[1]/input[1]")
	public WebElement learMaterialsUserSearch;

	@FindBy(xpath = "//span[contains(text(),'None')]")
	public WebElement learMaterialsPracticeDropdown;

	@FindBy(xpath = "//input[@id='customfield_15903']")
	public WebElement learMaterialsName;

	@FindBy(xpath = "//textarea[@id='customfield_15802']")
	public WebElement learMaterialsReasonField;

	@FindBy(xpath = "//label[contains(text(),'Yes')]")
	public WebElement learMaterialsYesRadioBtn;

	@FindBy(xpath = "//label[contains(text(),'No')]")
	public WebElement learMaterialsNoRadioBtn;

	@FindBy(xpath = "//input[@id='customfield_15803']")
	public WebElement learMaterialsTotalCostField;

	@FindBy(xpath = "//button[contains(text(),'Create')]")
	public WebElement learMaterialsCreateBtn;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement learMaterialsCancelBtn;

	public LearningMaterialsRepoSD() {
		PageFactory.initElements(driver, this);
	}

	public void learningMaterilasUserName(String name) throws AWTException, InterruptedException {

		learMaterialsUserBtn.click();
		learMaterialsUserSearch.sendKeys(name);
		Thread.sleep(3000);
		comm.pressEnter();
	}

	public void learningMaterialsChoosePractice(String practice) throws InterruptedException {
		learMaterialsPracticeDropdown.click();
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
}
