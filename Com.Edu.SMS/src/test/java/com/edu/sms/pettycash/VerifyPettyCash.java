package com.edu.sms.pettycash;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.AdminPettycashPage;
import Com.Edu.ObjectRepo.CommonComponents;
import Com.Edu.ObjectRepo.LoginPage;
import Com.Edu.ObjectRepo.TeacherDashboardPage;
import Com.Edu.ObjectRepo.TeacherPettyCashPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.FileUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class VerifyPettyCash extends BaseClass
{
	@Test(priority = 1)
	public  void verifyPettyCash() throws Throwable
	{
	
		//create object for utility classes
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();	
		
		//verify the admin dashboard page is displayed or not
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);	
		adminDashboard.verifyAdminDashboard(driver);
		
		//click on  Teacher link in RHN
		adminDashboard.teacherLink();
		
		//click on add teacher link
		adminDashboard.addTeacherLink();
		
		//create teacher
		AddTeacherPage addTeacher = new AddTeacherPage(driver);
		addTeacher.createTeacher(eLib.getMultipleData("Data"), driver, wLib);
		
		//wait for 4sec
		jLib.escapeKey();
		
		//verify the teacher
		addTeacher.verifyTeacher(driver, wLib, eLib, jLib);
		
		//logout as admin
		adminDashboard.signOut();

		//fetch teacher credentials
		String teacherUsername = fLib.getPropertyKeyValue("teacheremail");
		String teacherPassword = fLib.getPropertyKeyValue("teacherpassword");
		
		//login as teacher
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(teacherUsername, teacherPassword);
		
		//verify whether teacher dashboard is displayed or not
		TeacherDashboardPage teacherDashboard = new TeacherDashboardPage(driver);
		teacherDashboard.verifyTeacherDashboard(driver);
		

		//Add pettycash
		TeacherPettyCashPage teacherPettyCash = new TeacherPettyCashPage(driver);
		teacherPettyCash.addPettycash(driver, "Baggg"+jLib.getRandomNumber(), "4000");
		
		//press escape button
		jLib.escapeKey();
	/*
		//sihnout as teacher
		adminDashboard.signOut();
	
		//fetch admin login details
		String adminUsername = fLib.getPropertyKeyValue("adminemail");
		String adminPassword = fLib.getPropertyKeyValue("adminpassword");
		
		//login as admin
		lp.loginToApp(adminUsername, adminPassword);

		//verify petty cash
		AdminPettycashPage adminPettycash = new AdminPettycashPage(driver);
		adminPettycash.verifyPettycash(driver, wLib, "Baggg"+jLib.getRandomNumber(), "4000");
		
	*/
	}
}

