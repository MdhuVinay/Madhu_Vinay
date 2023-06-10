package com.edu.sms.timetable;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminClassroomPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminGradePage;
import Com.Edu.ObjectRepo.AdminSubjectPage;
import Com.Edu.ObjectRepo.AdminSubjectRoutingPage;
import Com.Edu.ObjectRepo.AdminTimetablePage;
import Com.Edu.ObjectRepo.LoginPage;
import Com.Edu.ObjectRepo.TeacherDashboardPage;
import Com.Edu.ObjectRepo.TeacherTimetablePage;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.FileUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class VerifyTimetable extends BaseClass
{
	@Test
	public void VerifyTimetable() throws Throwable
	{

		//create object for utility classes
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		AdminGradePage adminGradePage = new AdminGradePage(driver, jLib, eLib);
		AdminSubjectPage adminSubjectPage = new AdminSubjectPage(driver);
		AddTeacherPage addTeacherPage = new AddTeacherPage(driver);
		AdminClassroomPage adminClassroomPage = new AdminClassroomPage(driver);
		AdminTimetablePage adminTimetablePage = new AdminTimetablePage();
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		TeacherDashboardPage teacherDashboardPage = new TeacherDashboardPage(driver);
		TeacherTimetablePage teacherTimetablePage = new TeacherTimetablePage(driver);

		//fetch the data from property file
		FileInputStream fis = new FileInputStream("./src/test/resources/smscommondata.properties");
		Properties properties = new Properties();
		properties.load(fis);

		//verify the admin dashboard page is displayed or not
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("dashboard"),"admin dashboard is not displayed ");
		System.out.println("admin dashboard is displayed");

		//create grade
		adminGradePage=new AdminGradePage(driver, jLib, eLib);
		String gradeName = adminGradePage.createGrade(jLib, eLib, driver);

		//press escape button
		jLib.escapeKey();

		//create subject
		adminSubjectPage = new AdminSubjectPage(driver);
		String subName = adminSubjectPage.createSubject(jLib, eLib, driver, wLib);


		//press escape button
		jLib.escapeKey();



		//create teacher
		adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.teacherLink();
		adminDashboardPage.addTeacherLink();
		addTeacherPage = new AddTeacherPage(driver);
		HashMap<String,String> createdTeacher = addTeacherPage.createTeacher(eLib.getMultipleData("Data"), driver, wLib, jLib);

		//press escape button
		jLib.escapeKey();

		//fetch teacher details
		String teacherName = null;
		String teacheremail = null;
		for(Entry<String, String> set:createdTeacher.entrySet()) {
			if(set.getKey().contains("i_name"))
			{
				teacherName = set.getValue();
			}
			else if (set.getKey().contains("email")) 
			{
				teacheremail = set.getValue();
			}
		}

		//create classroom
		adminClassroomPage = new AdminClassroomPage(driver);
		String classroomName = adminClassroomPage.createClassromm(driver, eLib);

		//press escape button
		jLib.escapeKey();

		//add subject routing
		AdminSubjectRoutingPage adminSubjectRoutingPage = new AdminSubjectRoutingPage(driver);
		adminSubjectRoutingPage.addSubjectRouting(driver, wLib, gradeName, subName, teacherName, classroomName);

		//press escape button
		jLib.escapeKey();

		//create timetable
		adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.timetableLink();
		adminTimetablePage.createTimeTable(driver,wLib, gradeName, "Friday", subName, teacherName,classroomName,"1"+jLib.getRandomNumber(),"2"+jLib.getRandomNumber());

		//press escape key
		jLib.escapeKey();

		//logout as admin
		adminDashboardPage.signOut();

		//login as teacher
		String teacherUsername = properties.getProperty("teacheremail");
		String teacherPassword = properties.getProperty("teacherpassword");

		loginPage.loginToApp(teacheremail, teacherPassword);

		//verify whether teacher dashboard is displayed or not
		String TeachercurrentUrl = driver.getCurrentUrl();
		assertTrue(TeachercurrentUrl.contains("dashboard2"), "taecher dashboard is not displayed");
		System.out.println("teacehr dashboard is displayed");

		//click on timetable button
		teacherDashboardPage.clickOnTimetableLink();

		//click on my time table link
		teacherDashboardPage.clickOnMyTimetableLink();


		//verify timetable
		List<WebElement> timeDetails = teacherTimetablePage.getTimeDetails();
		List<WebElement> allDetails = teacherTimetablePage.getAllDetails();
		for (int i = 0; i < timeDetails.size(); i++) 
		{
			String timeDeatilsText = timeDetails.get(i).getText();
			String allDetailsText = allDetails.get(i).getText();
			if((timeDeatilsText.contains("1"+jLib.getRandomNumber()) && allDetailsText.contains(subName)))
			{		
				System.out.println("timetable is created");
			}
			else
			{
				System.out.println("timetable is not created");
			}
		}


	}

}
