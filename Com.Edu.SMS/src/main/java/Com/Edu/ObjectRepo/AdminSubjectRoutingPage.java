package Com.Edu.ObjectRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.WebdriverUtility;

public class AdminSubjectRoutingPage 
{
	//declaration
	@FindBy(xpath = "//input[@id='fee']") private WebElement fees;
	@FindBy(xpath = "//tbody//tr//td[2]") private List<WebElement> gradeDetails; 
	@FindBy(xpath = "//tbody//tr//td[3]") private List<WebElement> subjectDetails;
	@FindBy(xpath = "//tbody//tr//td[4]") private List<WebElement> teacherDetails;


	@FindBy(xpath = "(//a[normalize-space()='Next'])[1]") private WebElement nextBtn;
	
	//initialization
	public AdminSubjectRoutingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public WebElement getFees()
	{
		return fees;
	}
	public List<WebElement> getGradeDetails() {
		return gradeDetails;
	}

	public List<WebElement> getSubjectDetails() 
	{
		return subjectDetails;
	}

	public List<WebElement> getTeacherDetails() {
		return teacherDetails;
	}

	public WebElement getNextBtn() 
	{
		return nextBtn;
	}

	//actions
	//add subject routing
	public void addSubjectRouting(WebDriver driver,WebdriverUtility wLib,String grade, String subject, String teacher, String fee)
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		CommonComponents commonComponents = new CommonComponents(driver);
		adminDashboard.subjectRoutingLink();
		commonComponents.clickOnAddBtn();
		commonComponents.selectGrade(wLib, driver,grade);
		commonComponents.selectSubject(subject, wLib);
		commonComponents.selectTeacher(teacher, wLib);
		fees.sendKeys(fee);
		commonComponents.getSubmitBtnOfPopup().click();
	}
	
	
}
