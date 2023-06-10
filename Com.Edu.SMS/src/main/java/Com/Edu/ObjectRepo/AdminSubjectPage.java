package Com.Edu.ObjectRepo;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class AdminSubjectPage 
{
	
//	SoftAssert softAssert=new SoftAssert();
	
	
	//declaration
	@FindBy(xpath = "//input[@id='name']") private WebElement subName;
	@FindBy(xpath = "//tbody//tr//td[2]") private List<WebElement> allSubName;
	@FindBy(xpath = "//a[normalize-space()='Next']") private WebElement nextBtn;


	//initialization
	public AdminSubjectPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	

	//utilization
	public WebElement getSubName() 
	{
		return subName;
	}
	public List<WebElement> getAllSubName()
	{
		return allSubName;
	}
	public WebElement getNextBtn() {
		return nextBtn;
	}
	
	//Actions
	public void clickOnNextBtn()
	{
		nextBtn.click();
	}
	//create subject
	public String createSubject(JavaUtility jLib, ExcelUtility eLib,WebDriver driver,WebdriverUtility wLib) throws Throwable
	{
		
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		adminDashboard.subjectLink();
		String sub = jLib.getRandomNumber()+eLib.getExcelData("Subject", 0, 1);
		subName.sendKeys(sub);
		CommonComponents commonComponents = new CommonComponents(driver);
		commonComponents.clickOnSubmitBtnOfPage();
		return sub;	
	}
	
	//verify subject
	public void verifySubject(JavaUtility jLib,ExcelUtility eLib,WebDriver driver,WebdriverUtility wLib) throws Throwable
	{
		CommonComponents commonComponents = new CommonComponents(driver);
		AdminDashboardPage adminDashboard=new AdminDashboardPage(driver);
		adminDashboard.subjectLink();
		commonComponents.showDropdown(wLib);
		String sub = jLib.getRandomNumber()+eLib.getExcelData("Subject", 0, 1);
		commonComponents.search(sub);
		for (WebElement subject : allSubName) 
		{
			String subText = subject.getText();
			if (subText.equalsIgnoreCase(sub))
			{
				System.out.println("subject is created");
			}
			else
			{
				clickOnNextBtn();	
			}
		}
	}
	
}
