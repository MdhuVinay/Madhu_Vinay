package com.edu.sms.student;

import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddStudentPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.FileUtility;
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

		//handle chrome notifications
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");

		//verify the dashboard page is displayed or not
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("dashboard")); 
		
		//click on Student link in RHN
		adminDashboard.clickOnstudentLink("Add Student", wLib, driver);

		//create student
		AddStudentPage addStudent = new AddStudentPage(driver);
		addStudent.createStudent(eLib.getMultipleData("AddStudent"), driver, wLib, "C:\\Users\\dell\\Pictures\\sms.png", "Grade 2");
		
		//press escape button
		jLib.escapeKey();
		
		addStudent.editStudent(driver, wLib, eLib);
	}
}
