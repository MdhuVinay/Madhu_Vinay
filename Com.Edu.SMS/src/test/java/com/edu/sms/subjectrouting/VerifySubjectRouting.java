package com.edu.sms.subjectrouting;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import studentManagementSystemGenericUtils.BaseClass;

public class VerifySubjectRouting extends BaseClass
{
	public  void VerifySubjectRouting() throws Throwable 
	{
		//handle chrome notifications
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		//verify the dashboard page is displayed or not
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("dashboard"));
		
		//click on subject button in LHN
		driver.findElement(By.xpath("//span[normalize-space()='Subject']")).click();
		
		//enter subject name into subject text field
		driver.findElement(By.id("name")).sendKeys("Kadamba");
		
		//click on submit button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Thread.sleep(3000);
		//click on Classroom link
		driver.findElement(By.xpath("//span[normalize-space()='Classroom']")).click();
		
		//enter classroom name into Name textfield
		driver.findElement(By.id("name")).sendKeys("room 105");
		
		//enter student count in student count TextField
		driver.findElement(By.id("student_count")).sendKeys("48");
		
		//click on submit button
		driver.findElement(By.id("btnSubmit")).click();
		
		Thread.sleep(4000);
		//search the created classroom 
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("room 105");
		
		//check the created classroom
		WebElement room = driver.findElement(By.xpath("//table[@class='table table-bordered table-striped dataTable no-footer']//td[2][contains(.,'r')]"));
		String roomtext = room.getText();
		if (roomtext.contains("room")) 
		{
			System.out.println("classroom is created");
		}
		else
		{
			System.out.println("classroom is not created");
		}
		
		//click on subject rooting
		driver.findElement(By.xpath("//a[@href='subject_routing.php']")).click();
		
		//click on add button in subject routing page
		driver.findElement(By.xpath("//a[@class='btn btn-success btn-sm pull-right']")).click();
		
//		Alert alert = driver.switchTo().alert();
		//click on Grade dropdown
		WebElement grade = driver.findElement(By.xpath("//select[@id='grade']"));
		//grade.click();
		
		//select text in Grade dropdown by using select class
		Select s=new Select(grade);
		s.selectByVisibleText("Grade 1");
		
		//click on subject drop down
		WebElement sub = driver.findElement(By.xpath("//select[@id='subject']"));
//		sub.click();
		
		//select subject in subject dropdown
		Select s2=new Select(sub);
		s2.selectByVisibleText("Subject 1");
		
		//click on Teacher dropdown
		WebElement teacher = driver.findElement(By.id("teacher"));
//		teacher.click();
		
		//select Teacher in Teacher dropdown
		Select s3=new Select(teacher);
		s3.selectByVisibleText("mad m");
		
		//enter fee in fee textfield
		driver.findElement(By.id("fee")).sendKeys("2000");
		
		//click on submit button in Add Subject Routing popup in Sugject Routing Page
		driver.findElement(By.id("btnSubmit")).click();
		
		Thread.sleep(3000);
		//check whether subject routing is added or not
		
		//click on subject routing button in RHN
		driver.findElement(By.xpath("//span[normalize-space()='Subject Routing']")).click();
		
		//select the show dropdown element
		WebElement show = driver.findElement(By.xpath("//select[@class='form-control input-sm']"));
		Select s4=new Select(show);
		s4.selectByVisibleText("100");
		
		//fetch the all teachers
		List<WebElement> teacherslist = driver.findElements(By.xpath("//tbody//td[4]"));
		
		for (WebElement tchr : teacherslist) 
		{
			String text = tchr.getText();
			if (text.contains("mad m")) 
			{
				System.out.println("subject routing is added");
				break;
			}
			/*
			  else 
			  { System.out.println("subject routing not added");
			   }
			 */
			
		}
		
		
	}

}
