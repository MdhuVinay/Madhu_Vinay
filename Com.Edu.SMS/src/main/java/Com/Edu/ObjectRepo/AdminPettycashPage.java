package Com.Edu.ObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.WebdriverUtility;

public class AdminPettycashPage
{
	//declaration
	@FindBy(xpath = "//tbody//td[7]//a[1]") private List<WebElement> details;
	@FindBy(xpath = "//div[@id='modalViewPettyCash']//table[@class='table table-bordered']//tbody//tr[1]//td[2]") private WebElement description;
	@FindBy(xpath = "//div[@id='modalViewPettyCash']//table[@class='table table-bordered']//tbody//tr[1]//td[3]") private WebElement amount;
	@FindBy(xpath = "//div[@id='modalViewPettyCash']//span[@class='glyphicon glyphicon-remove']") private WebElement cancelBtn;
	@FindBy(xpath = "//div[@class='dataTables_info']") private WebElement tableInfo;
	@FindBy(xpath = "//th[@aria-label='Date: activate to sort column ascending']") private WebElement dateSort;
	//tbody//td[7]//a[contains(@data-id,'1,')][1]
	//
	
	

	//initialization
	public AdminPettycashPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public List<WebElement> getDetails() {
		return details;
	}
	public WebElement getDescription() {
		return description;
	}
	public WebElement getAmount() {
		return amount;
	}
	public WebElement getCancelBtn() {
		return cancelBtn;
	}
	public void details()
	{
		List<WebElement> allDetails = details;
	}

	public WebElement getDateSort() {
		return dateSort;
	}
	
	
	
	//Actions
	public void dateSort()
	{
		dateSort.click();
	}
	//verify pettycash
	public void verifyPettycash(WebDriver driver, WebdriverUtility wLib, String description, String amount, String teacher) throws Throwable
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		CommonComponents commonComponents = new CommonComponents(driver);
		adminDashboard.pettyCashLink();
		commonComponents.showDropdown(wLib);
		commonComponents.search(teacher);
		details();
		for (int i=0; i<details.size() ; i++)
		{
			WebElement index = details.get(i);
			index.click();
			String discription = getDescription().getText();
			String amount2 = getAmount().getText();
			if(discription.contains(description) && amount2.contains(amount))
			{
				AdminDashboardPage adminDashboard1 = new AdminDashboardPage(driver);
				cancelBtn.click();
				driver.findElement(By.xpath("//a[@id='aApprove_"+index+"']"));
				System.out.println("petty cash approver successfully");
				Thread.sleep(1000);
				break;
			}
			else
			{
				cancelBtn.click();
			}
		}	
	}
}
