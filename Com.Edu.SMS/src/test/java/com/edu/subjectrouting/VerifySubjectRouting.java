package com.edu.subjectrouting;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminClassroomPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminGradePage;
import Com.Edu.ObjectRepo.AdminSubjectPage;
import Com.Edu.ObjectRepo.AdminSubjectRoutingPage;
import Com.Edu.ObjectRepo.CommonComponents;
import Com.Edu.ObjectRepo.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
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

		//addsubject routing
		String createdSub = adminSubject.createSubject(jLib,eLib, driver, wLib);
		jLib.escapeKey();
		String createdClassroom = adminClassroom.createClassromm(driver, eLib);
		jLib.escapeKey();
		String createdGrade = adminGrade.createGrade(jLib, eLib, driver);
		jLib.escapeKey();
		BusinessClassPage businessClass = new BusinessClassPage();
		businessClass.addSubjectRouting(driver, wLib, createdGrade, createdSub, "mad m", "200");

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
		
		Reporter.log("subject routing added", true);
		

	}

}
