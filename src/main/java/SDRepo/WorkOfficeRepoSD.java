package SDRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import base.CommonPOM;
import testData.TestData;

public class WorkOfficeRepoSD extends Base {
	TestData testData = new TestData(); 
	CommonPOM comm = new CommonPOM();
	
	//Defining WebElements
	@FindBy(xpath ="//input[@id='customfield_18000']")
	public WebElement workOfficeFromDateField;
	
	@FindBy(xpath = "//input[@id='customfield_18001']")
	public WebElement workOfficeToDateField;
	
	@FindBy(xpath = "//span[contains(text(),'None')]")
	public WebElement workOfficeOfficeDropdown;
	
	@FindBy(xpath = "//body/div[@id='select2-drop']/div[1]/input[1]")
	public WebElement workOfficeOfficeSearchField;
	
	@FindBy(xpath = "//label[contains(text(),'Yes')]")
	public WebElement workOfficeHotelYesRadioBtn;
	
	@FindBy(xpath = "//label[contains(text(),'No')]")
	public WebElement workOfficeHotelNoRadioBtn;
	
	@FindBy(xpath = "//label[contains(text(),'Business')]")
	public WebElement workOfficeBusinessTripRadioBtn;
	
	@FindBy(xpath = "//label[contains(text(),'Private')]")
	public WebElement workOfficePrivateTripRadioBtn;
	
	@FindBy(xpath = "//textarea[@id='customfield_19000']")
	public WebElement workOfficeExpensesField;
	
	@FindBy(xpath = "//ul[@class='select2-choices']")
	public WebElement workOfficeBranchDropdown;
	
	@FindBy(xpath = "//body/div[@id='select2-drop']/ul[1]/li[2]/div[1]")
	public WebElement workOfficePartners;
		
	@FindBy(xpath = "//textarea[@id='customfield_16003']")
	public WebElement workOfficeResonField;
	
	@FindBy(xpath = "//button[contains(text(),'Create')]")
	public WebElement workOfficeCreateBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement workOfficeCancelBtn;
	
	@FindBy(xpath = "(//span[@class='sd-calendar-icon aui-icon aui-icon-large aui-iconfont-calendar'])[1]")
	public WebElement workOfficeFromCalednarBtn;
	
	@FindBy(xpath = "(//span[@class='sd-calendar-icon aui-icon aui-icon-large aui-iconfont-calendar'])[2]")
	public WebElement workOfficeToCalednarBtn;

	@FindBy(xpath = "//div[@class='actions-row']/child::button[@aria-label='Confirm']")
	public WebElement workOfficeConfirmFromCalednarBtn;
	
	@FindBy(xpath = "(//div[@class='actions-row']/child::button[@aria-label='Confirm'])[2]")
	public WebElement workOfficeConfirmToCalendarBtn;
	
	@FindBy(xpath = "//div[@class=\"select2-container select2-container-multi trigger-select2 full-width-field js-select2-picker aui-select2-container\"]")
	public WebElement workOfficeBranchSearch;

	@FindBy(xpath = "//li[@class=\"select2-results-dept-0 select2-result select2-result-selectable\"]")
	public WebElement workOfficeBranchList;
	

	
	//initiation
	public WorkOfficeRepoSD() {
		PageFactory.initElements(driver, this);
	}

	
	
	//methods
	public void ChooseStartDateCalendar(String startMonthYear, String startDay) throws InterruptedException {
		// repeat the loop till finding the expected month and year
		comm.waitElement(workOfficeFromCalednarBtn);
		workOfficeFromCalednarBtn.click();
		while (true) {
			String displayedMonthYear = driver.findElement(By.xpath("//h2[@id='calendar-title']")).getText();
			

			if (displayedMonthYear.equals(startMonthYear)) {
				break;
				// click on the next button till find the expected month and year
			} else {
				driver.findElement(By.xpath("//button[contains(text(),'›')]")).click();
			}
		}
		// get to list all the dates from the month
		List<WebElement> allStartDates = driver.findElements(By.xpath("//td[@role='gridcell']"));
		for (WebElement element : allStartDates) {
			String startDate = element.getText();
			// if the expected and actual day is found perform click
			if (startDate.equals(startDay)) {
				element.click();
				break;
			}
		}
//		Thread.sleep(2000);
//		workOfficeConfirmFromCalednarBtn.click();
	}

	public void ChooseEndDateCalendar(String endMonthYear, String endDay) {
		comm.waitElement(workOfficeToCalednarBtn);
		workOfficeToCalednarBtn.click();
		// repeat the loop till finding the expected month and year
		while (true) {
			String displayedMonth = driver.findElement(By.xpath("(//h2[@id='calendar-title'])[2]")).getText();
			

			if (displayedMonth.equals(endMonthYear)) {
				break;
				// click on the next button till find the expected month and year
			} else {
				driver.findElement(By.xpath("(//button[contains(text(),'›')])[2]")).click();
			}
		}
		// get to list all the dates from the month
		List<WebElement> allStartDates = driver.findElements(By.xpath("//td[@role='gridcell']"));
		for (WebElement element : allStartDates) {
			String startDate = element.getText();
			// if the expected and actual day is found perform click
			if (startDate.equals(endDay)) {
				element.click();
				break;
			}
		}
	}

	public void workOfficeChooseBranch(String cityOffice) {
	workOfficeOfficeDropdown.click();
	workOfficeOfficeSearchField.sendKeys(cityOffice);
	workOfficeOfficeSearchField.sendKeys(Keys.RETURN);
	
	}
	public void workOfficeNoHotel() {
		workOfficeHotelNoRadioBtn.click();
	}
	
	public void workOfficeYesHotel() {
		workOfficeHotelYesRadioBtn.click();
	}
	
	
	public void workOfficeBusinessTrip() {
		workOfficeBusinessTripRadioBtn.click();
	}
	 
	public void workOfficePrivateTrip() {
		workOfficePrivateTripRadioBtn.click();
	}
	public void workOfficeExpenses(String expenses) {
		workOfficeExpensesField.sendKeys(expenses);
	}
	public void workOfficePartnersCoverBranch() {
		comm.waitElement(workOfficeBranchSearch);
		workOfficeBranchSearch.click();
		comm.waitElement(workOfficePartners);
		workOfficePartners.click();;
	}
	
	public void workOfficeReason(String reason) {
		workOfficeResonField.sendKeys(reason);
	}
	public void workOfficeCancel() {
		workOfficeCancelBtn.click();
	}
	
	public void workOfficeChooseBrachSwitch(String branch) {
		comm.waitElement(workOfficeBranchSearch);
		workOfficeBranchSearch.click();
		comm.waitElement(workOfficeBranchList);
		
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
