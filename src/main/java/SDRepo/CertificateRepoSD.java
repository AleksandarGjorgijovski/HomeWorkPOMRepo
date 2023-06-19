package SDRepo;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;

public class CertificateRepoSD extends Base{

	TestData testData = new TestData();
	CommonPOM comm = new CommonPOM();
	
	
	@FindBy(xpath = "//input[@id='summary']")
	public WebElement certificateSummaryField;
	
	@FindBy(xpath = "//span[contains(text(),'Search for a user')]")
	public WebElement certificateUserBtn;
	
	@FindBy (xpath = "//body/div[@id='select2-drop']/div[1]/input[1]")
	public WebElement certificateUserSearch;
	
	@FindBy(xpath = "//span[contains(text(),'None')]")
	public WebElement certificatePracticeDropdown;
	
	@FindBy(xpath = "//input[@id='customfield_15801']")
	public WebElement certificateName;
	
	@FindBy(xpath = "//textarea[@id='customfield_15802']")
	public WebElement certificateReasonField;
	
	@FindBy(xpath = "//input[@id='customfield_15803']")
	public WebElement certificateTotalCostField;
	
	@FindBy(xpath = "//button[contains(text(),'Create')]")
	public WebElement certificateCreateBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement certificateCancelBtn;
	
	
	
	
	public CertificateRepoSD() {
		PageFactory.initElements(driver, this);
	}
	
	public void certificateUserName(String name) throws AWTException, InterruptedException {
		
		certificateUserBtn.click();
		certificateUserSearch.sendKeys(name);
		Thread.sleep(3000);
		comm.pressEnter();
	}
	
	public void certificateChoosePractice(String practice) throws InterruptedException {
		certificatePracticeDropdown.click();
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
