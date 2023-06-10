package com.edu.sms.student;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddStudentPage;
import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminGradePage;
import Com.Edu.ObjectRepo.AdminSubjectPage;
import Com.Edu.ObjectRepo.AdminSubjectRoutingPage;
import Com.Edu.ObjectRepo.CommonComponents;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.FileUtility;
import studentManagementSystemGenericUtils.JavaScriptUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class EditStudent extends BaseClass
{
	@Test
	public  void editStudent() throws Throwable
	{
		//create object for utility classes
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaScriptUtility jsu = new JavaScriptUtility();

		//verify the dashboard page is displayed or not
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("dashboard"));
		System.out.println("admin dashboard is displayed");

		//create grade
		AdminGradePage adminGradePage = new AdminGradePage(driver, jLib, eLib); 
		String gradeName = adminGradePage.createGrade(jLib, eLib, driver);
		System.out.println("grade is created");

		//pressescape button
		jLib.escapeKey();

		//create subject
	
		AdminSubjectPage adminSubjectPage = new AdminSubjectPage(driver);
		String subName = adminSubjectPage.createSubject(jLib, eLib, driver, wLib);
		System.out.println("subject is created");

		//pressescape button
		jLib.escapeKey();

		//create teacher
		adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage=new AdminDashboardPage(driver);
		adminDashboardPage.teacherLink();
		adminDashboardPage.addTeacherLink();
		AddTeacherPage addTeacherPage = new AddTeacherPage(driver);
		HashMap<String, String> teacherName = addTeacherPage.createTeacher(eLib.getMultipleData("Data"), driver, wLib, jLib);
		System.out.println("teacher is created");

		String teacherIName=null;
		for (Entry<String, String> teacher : teacherName.entrySet())
		{
			if (teacher.getKey().contains("i_name")) 
			{
				teacherIName = teacher.getValue();
			}
		}


		//pressescape button
		jLib.escapeKey();

		//add subject routing
		AdminSubjectRoutingPage adminSubjectRoutingPage = new AdminSubjectRoutingPage(driver);
		adminSubjectRoutingPage.addSubjectRouting(driver, wLib, gradeName, subName, teacherIName, "1500");


		//pressescape button
		jLib.escapeKey();

		//click on Student link in RHN
		adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.clickOnstudentLink();
		adminDashboardPage.clickOnAddStudentLink();
	
		
		//create student
		AddStudentPage addStudentPage = new AddStudentPage(driver);
		addStudentPage.createStudent(eLib.getMultipleData("AddStudent"), driver, wLib, "C:\\Users\\dell\\Pictures\\sms.png", gradeName);
		addStudentPage=new AddStudentPage(driver);
		addStudentPage.selectCheckbox();
		CommonComponents commonComponents = new CommonComponents(driver);
		addStudentPage.submitBtnOfPopup();
		Thread.sleep(2000);
		WebElement paidBtn = addStudentPage.getPaidBtn();
//		jsu.scrollTillElement(paidBtn);
		addStudentPage.paidBtn();
		Thread.sleep(5000);
		jLib.printCancelBtn();
		System.out.println("student is created");

		//pressescape button
		jLib.escapeKey();
		jLib.escapeKey();

		//edit student
		adminDashboardPage = new AdminDashboardPage(driver);
		adminDashboardPage.clickOnstudentLink();
		adminDashboardPage.clickOnAllStudentLink();
		addStudentPage.editStudent(driver, subName, wLib, gradeName, teacherIName);
		System.out.println("student is edited");
		
	}
}
