package com.edu.sms.teacher;

import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminGradePage;
import Com.Edu.ObjectRepo.AdminSubjectPage;
import Com.Edu.ObjectRepo.CommonComponents;
import io.github.bonigarcia.wdm.WebDriverManager;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.FileUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class VerifyTeacherInSubjectRotingPopupOfTimetablePage extends BaseClass 
{
	@Test
	public void verifyTeacher() throws Throwable
	{
		//create object for utility classes
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();

		//handle chrome notifications
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");

		//verify the admin dashboard page is displayed or not
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("dashboard"));

		//create grade
		AdminGradePage adminGradePage = new AdminGradePage(driver, jLib, eLib);
		String createdGrade = adminGradePage.createGrade(jLib, eLib, driver);

		//press escape key
		jLib.escapeKey();

		//create subject
		AdminSubjectPage adminSubjectPage = new AdminSubjectPage(driver);
		String createdSubject = adminSubjectPage.createSubject(jLib, eLib, driver, wLib);

		//press escape key
		jLib.escapeKey();

		//click on Teacher link in RHN
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		adminDashboard.teacherLink();

		//click on Add Teacher link 
		adminDashboard.addTeacherLink();

		//create teacher
		AddTeacherPage addTeacher = new AddTeacherPage(driver);
		addTeacher.createTeacher(eLib.getMultipleData("Data"), driver, wLib,jLib);

		//robot class
		jLib.escapeKey();

		//click on Timetablebutton in RHN
		adminDashboard.timetableLink();

		//select grade from grade dropdown
		CommonComponents commonComponents = new CommonComponents(driver);
		commonComponents.selectGrade(wLib, driver, "Grade 2");

		//click on submit button
		commonComponents.clickOnSubmitBtnOfPage();

		//click on Add button
		commonComponents.clickOnAddBtn();

		//select Day in Day dropdown
		commonComponents.selectDay("Thursday", wLib);

		//select subject from subject dropdown
		commonComponents.selectSubject("Subject 2", wLib);

		//select teacher from teacher dropdown
		commonComponents.selectTeacher("mad m", wLib);

		//verify teacher is selected or not
		String text = commonComponents.verifyTeacher();
		assertTrue(text.contains("mad m"));

		//click on cancel button
		commonComponents.getCloseBtnOfPopup().click();

		Reporter.log("teacher is selected", true);


	}
}
