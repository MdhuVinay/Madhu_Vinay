package com.edu.subjectrouting;

import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminClassroomPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminGradePage;
import Com.Edu.ObjectRepo.AdminSubjectPage;
import Com.Edu.ObjectRepo.AdminSubjectRoutingPage;
import Com.Edu.ObjectRepo.CommonComponents;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.FileUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

@Listeners(studentManagementSystemGenericUtils.ListenerImplements.class)
public class VerifySubjectRouting extends BaseClass
{
	@Test
	public void VerifySubjectRouting() throws Throwable 
	{
		Robot robot = new Robot();
		AdminSubjectPage adminSubject = new AdminSubjectPage(driver);
		AdminClassroomPage adminClassroom = new AdminClassroomPage(driver);
		AdminGradePage adminGrade = new AdminGradePage(driver, jLib, eLib);
		CommonComponents commonComponents = new CommonComponents(driver);
		AdminSubjectRoutingPage adminSubjectRoutingPage = new AdminSubjectRoutingPage(driver);

		//create object for utility classes
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();

		//		Assert.fail();
		//verify the dashboard page is displayed or not
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("dashboard"));


		String createdSub = adminSubject.createSubject(jLib,eLib, driver, wLib);

		//press escape button
		jLib.escapeKey();

		//create classroom
		String createdClassroom = adminClassroom.createClassromm(driver, eLib);

		//press escape button
		jLib.escapeKey();

		//create grade
		String createdGrade = adminGrade.createGrade(jLib, eLib, driver);

		//press escape button
		jLib.escapeKey();

		//create teacher
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.teacherLink();
		adminDashboardPage.addTeacherLink();
		AddTeacherPage addTeacherPage = new AddTeacherPage(driver);
		HashMap<String,String> createdTeacher = addTeacherPage.createTeacher(eLib.getMultipleData("Data"), driver, wLib, jLib);

		//press escape button
		jLib.escapeKey();
		
		String teacherName = null;
		String teacheremail;
		for(Entry<String, String> set:createdTeacher.entrySet()) {
			if(set.getKey().contains("i_name")) {
				teacherName = set.getValue();
			}
			else if (set.getKey().contains("email")) 
			{
				teacheremail = set.getValue();
			}
		}

		//addsubject routing
		BusinessClassPage businessClass = new BusinessClassPage();
		businessClass.addSubjectRouting(driver, wLib, createdGrade, createdSub, teacherName, "200");

		//press escape key
		jLib.escapeKey();

		//verify subject routing
		commonComponents.showDropdown(wLib);
		commonComponents.search(createdSub);
		List<WebElement> subjectDetails = adminSubjectRoutingPage.getSubjectDetails();
		for (WebElement sub : subjectDetails) 
		{
			String text = sub.getText();
			assertTrue(text.contains(createdSub), "subject routing not added");

		}

		System.out.println("subject routing added");
//		Reporter.log("subject routing added", true);


	}

}
