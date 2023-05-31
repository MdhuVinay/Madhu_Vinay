package com.edu.sms.timetable;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminClassroomPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminGradePage;
import Com.Edu.ObjectRepo.AdminSubjectPage;
import Com.Edu.ObjectRepo.AdminTimetablePage;
import Com.Edu.ObjectRepo.LoginPage;
import Com.Edu.ObjectRepo.TeacherDashboardPage;
import Com.Edu.ObjectRepo.TeacherTimetablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
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

		//create timetable
		adminDashboardPage.timetableLink();
		adminTimetablePage.createTimeTable(wLib, "Grade 3", "Friday", "Subject 2", "Teacher 2", "Class E","100","200");
		
		//press escape key
		jLib.escapeKey();

		//logout as admin
		adminDashboardPage.signOut();

		//login as teacher
		String teacherUsername = properties.getProperty("teacheremail");
		String teacherPassword = properties.getProperty("teacherpassword");
		loginPage.loginToApp(teacherUsername, teacherPassword);

		//verify whether teacher dashboard is displayed or not
		String TeachercurrentUrl = driver.getCurrentUrl();
		assertTrue(TeachercurrentUrl.contains("dashboard2"), "taecher dashboard is not displayed");

		//click on timetable button
		
		teacherDashboardPage.clickOnTimetableLink();
		
		//verify timetable
		List<WebElement> timeDetails = teacherTimetablePage.getTimeDetails();
		List<WebElement> allDetails = teacherTimetablePage.getAllDetails();
		for (int i = 0; i < timeDetails.size(); i++) 
		{
			String timeDeatilsText = timeDetails.get(i).getText();
			String allDetailsText = allDetails.get(i).getText();
			assertTrue(timeDeatilsText.contains("100 - 200") && allDetailsText.contains("Subject 2"), " timetable not created");
			System.out.println("timetable is created");
		}
		

	}

}
