package com.edu.subjectrouting;

import org.openqa.selenium.WebDriver;

import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.CommonComponents;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class BusinessClassPage 
{
	//add subject routing
	public void addSubjectRouting(WebDriver driver, WebdriverUtility wLib,String grade, String subject, String teacher, String fee)
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		CommonComponents commonComponents = new CommonComponents(driver);
		adminDashboard.subjectRoutingLink();
		commonComponents.clickOnAddBtn();
		commonComponents.selectGrade(wLib, driver, grade);
		commonComponents.selectSubject(subject, wLib);
		commonComponents.selectTeacher(teacher, wLib);
		commonComponents.fees(fee);
		commonComponents.getSubmitBtnOfPopup().click();
		System.out.println(" ");
	}
}
