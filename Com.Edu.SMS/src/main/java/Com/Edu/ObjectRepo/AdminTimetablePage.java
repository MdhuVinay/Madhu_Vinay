package Com.Edu.ObjectRepo;

import org.openqa.selenium.WebDriver;

import studentManagementSystemGenericUtils.WebdriverUtility;

public class AdminTimetablePage 
{
	WebDriver driver;
	CommonComponents commonComponents=new CommonComponents(driver);
	AdminDashboardPage adminDashboardPage=new AdminDashboardPage(driver);
	
	//create timetable
	public void createTimeTable(WebdriverUtility wLib, String grade, String day, String subject, String teacher, String classroom, String startTime, String endTime)
	{
		commonComponents.selectGrade(wLib, driver, grade);
		commonComponents.clickOnSubmitBtnOfPage();
		commonComponents.clickOnAddBtn();
		commonComponents.selectDay(day, wLib);
		commonComponents.selectSubject(subject, wLib);
		commonComponents.selectTeacher(teacher, wLib);
		commonComponents.selectClassroom(classroom, wLib);
		commonComponents.getStartTime().sendKeys(startTime);
		commonComponents.getEndTime().sendKeys(endTime);
		commonComponents.getSubmitBtnOfPopup().click();
	}
}
