package com.edu.sms.pettycash;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import Com.Edu.ObjectRepo.AddTeacherPage;
import Com.Edu.ObjectRepo.AdminDashboardPage;
import studentManagementSystemGenericUtils.BaseClass;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class VerifyPettycashTestNG extends BaseClass
{

	@Test
	public void createTeacher() throws Throwable
	{
	//create object for utility classes
		
	JavaUtility jLib = new JavaUtility();
	WebdriverUtility wLib = new WebdriverUtility();
	ExcelUtility eLib = new ExcelUtility();

	//fetch the data from property file
	FileInputStream fis = new FileInputStream("./src/test/resources/smscommondata.properties");
	Properties properties = new Properties();
	properties.load(fis);
	

	//handle chrome notifications
	ChromeOptions option=new ChromeOptions();
	option.addArguments("--disable-notifications");


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
	Thread.sleep(4000);

	//verify the teacher
	addTeacher.verifyTeacher(driver, wLib, eLib, jLib);

	}
/*
	//login as teacher
	lp.loginToApp(teacherUsername, teacherPassword);

	//verify whether teacher dashboard is displayed or not
	TeacherDashboardPage teacherDashboard = new TeacherDashboardPage(driver);
	teacherDashboard.verifyTeacherDashboard(driver);


	//Add pettycash
	TeacherPettyCashPage teacherPettyCash = new TeacherPettyCashPage(driver);
	teacherPettyCash.addPettycash(driver, "Bagg", "3000");

	//wait for 3 seconds to confirmation to disappear
	Thread.sleep(3000);

	//logout as teacher
	adminDashboard.signOut();

	//login as admin
	lp.loginToApp(adminUsername, adminPassword);

	//verify petty cash
	AdminPettycashPage adminPettycash = new AdminPettycashPage(driver);
	adminPettycash.verifyPettycash(driver, wLib, "Bagg", "3000");

	driver.quit();*/

}
