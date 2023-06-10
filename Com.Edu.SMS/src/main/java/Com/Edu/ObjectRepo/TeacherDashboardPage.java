package Com.Edu.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherDashboardPage 
{
	//declaration
	@FindBy(xpath = "//a[@href='my_petty_cash.php']") private WebElement myPettyCashLink;
	
	@FindBy(xpath = "//span[@class='hidden-xs']") private WebElement teacherMenuBtn;
	@FindBy(xpath = "//span[normalize-space()='Timetable']") private WebElement timetableLink;
	@FindBy(xpath = "//a[normalize-space()='My Timetable']") private WebElement myTimetableLink;
	
	


	//initialization
	public TeacherDashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	//utilization
	public WebElement getMyPettyCashLink() {
		return myPettyCashLink;
	}

	public WebElement getTeacherMenuBtn() {
		return teacherMenuBtn;
	}
	public WebElement getTimetableLink() {
		return timetableLink;
	}
	public WebElement getMyTimetableLink() {
		return myTimetableLink;
	}
	
	//actions
	//click on mypettysash link
	public void clickOnMyPettycash()
	{
		myPettyCashLink.click();
	}
	
	//click on timetable link
	public void clickOnTimetableLink()
	{
		timetableLink.click();
	}
	//click on my timetable link
	public void clickOnMyTimetableLink()
	{
		myTimetableLink.click();
	}
	
	//verify teacher dashboard
	public void verifyTeacherDashboard(WebDriver driver)
	{
		String TeachercurrentUrl = driver.getCurrentUrl();
		if (TeachercurrentUrl.contains("dashboard2"))
		{
			System.out.println("Teacher dashboard page is displayed");
		}
		else
		{
			System.out.println("Techer dashboard page is not displayed");
		}
	}
	
	//verify pettycash
	
}
