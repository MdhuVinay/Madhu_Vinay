package Com.Edu.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.WebdriverUtility;

public class AdminDashboardPage 
{
	//initialization
	@FindBy(xpath = "//span[normalize-space()='Classroom']")
	@CacheLookup
	private WebElement classroomLink;

	@FindBy(xpath = "//span[normalize-space()='Grade']")
	@CacheLookup
	private WebElement gradeLink ;

	@FindBy(xpath = "//span[normalize-space()='Subject']")
	@CacheLookup
	private WebElement subjectLink ;

	@FindBy(xpath = "//span[normalize-space()='Teacher']")
	@CacheLookup
	private WebElement teacherLink ;

	@FindBy(xpath  = "//a[.=' Add Teacher']")
	@CacheLookup
	private WebElement addTeacherLink ;

	@FindBy(xpath  = "//a[normalize-space()='All Teacher']")
	@CacheLookup
	private WebElement allTeacherLink ;

	@FindBy(xpath = "//span[normalize-space()='Subject Routing']")
	@CacheLookup
	private WebElement subjectRoutingLink ;

	@FindBy(xpath = "//span[normalize-space()='Timetable']")
	@CacheLookup
	private WebElement timetableLink ;

	@FindBy(xpath = "//span[normalize-space()='Student']")
	@CacheLookup
	private WebElement studentLink ;

	@FindBy(xpath  = "//a[.=' Add Student']")
	@CacheLookup
	private WebElement addStudentLink ;

	@FindBy(xpath = "//a[normalize-space()='All Student']")
	@CacheLookup
	private WebElement allStudentLink ;

	@FindBy(xpath = "//a[normalize-space()='Leave Student']")
	@CacheLookup
	private WebElement leaveStudentLink ;

	@FindBy(xpath = "//span[normalize-space()='Exam']")private WebElement examLink ;
	
	@FindBy(xpath = "//a[normalize-space()='Create Exam']")
	private WebElement createExamLink ;
	
	@FindBy(xpath = "//a[normalize-space()='Exam Timetable']")
	private WebElement examTimetableLink ;
	
	@FindBy(xpath = "//a[normalize-space()='Student Exam Marks']")private WebElement studentExamMarksLink ;
	@FindBy(xpath = "//a[normalize-space()='Student Exam Marks History']")private WebElement studentExamMarksHistoryLink ;

	@FindBy(xpath = "//span[normalize-space()='Petty Cash']")private WebElement pettyCashLink ;

	@FindBy(xpath =   "//img[@class='user-image']")private WebElement menuBtn ;

	@FindBy(xpath = "//a[normalize-space()='Sign out']")private WebElement signOutLink ;

	//delcaration
	public AdminDashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getClassroomLink() {
		return classroomLink;
	}

	public WebElement getGradeLink() {
		return gradeLink;
	}

	public WebElement getSubjectLink() {
		return subjectLink;
	}

	public WebElement getTeacherLink() {
		return teacherLink;
	}

	public WebElement getAddTeacherLink() {
		return addTeacherLink;
	}

	public WebElement getAllTeacherLink() {
		return allTeacherLink;
	}

	public WebElement getSubjectRoutingLink() {
		return subjectRoutingLink;
	}

	public WebElement getTimetableLink() {
		return timetableLink;
	}

	public WebElement getStudentLink() {
		return studentLink;
	}

	public WebElement getAddStudentLink() {
		return addStudentLink;
	}

	public WebElement getAllStudentLink() {
		return allStudentLink;
	}

	public WebElement getLeaveStudentLink() {
		return leaveStudentLink;
	}

	public WebElement getExamLink() {
		return examLink;
	}

	public WebElement getCreateExamLink() {
		return createExamLink;
	}

	public WebElement getExamTimetableLink() {
		return examTimetableLink;
	}

	public WebElement getStudentExamMarksLink() {
		return studentExamMarksLink;
	}

	public WebElement getStudentExamMarksHistoryLink() {
		return studentExamMarksHistoryLink;
	}

	public WebElement getPettyCashLink() {
		return pettyCashLink;
	}

	public WebElement getMenuBtn() {
		return menuBtn;
	}

	

	public WebElement getSignOutLink() {
		return signOutLink;
	}


	//clickOnGradeLink
	public void GradeLink()
	{
		gradeLink.click();
	}

	//click on classroomLink
	public void classroomLink()
	{
		classroomLink.click();
	}

	//click on subjectLink
	public void subjectLink()
	{
		subjectLink.click();
	}

	//click on teacherLink
	public void teacherLink()
	{
		teacherLink.click();
	}

	//click on addTeacherLink
	public void addTeacherLink()
	{
		addTeacherLink.click();
	}

	//click On llTeacherLink
	public void allTeacherLink()
	{
		allTeacherLink.click();
	}

	//click On subjectRoutingLink
	public void subjectRoutingLink()
	{
		subjectRoutingLink.click();
	}

	//click On timetableLink
	public void timetableLink()
	{
		timetableLink.click();
	}

	//click On studentLink
	public void clickOnstudentLink()
	{
		studentLink.click();
	}
	
	//click on all Student link
	public void clickOnAllStudentLink()
	{
		allStudentLink.click();
	}
	
	//click on add student link
	public void clickOnAddStudentLink()
	{
		addStudentLink.click();
	}

	/*//click On addStudentLink
	public void addStudentLink()
	{
		addStudentLink.click();
	}

	//click On allStudentLink
	public void allStudentLink()
	{
		allStudentLink.click();
	}

	//click On leaveStudentLink
	public void leaveStudentLink()
	{
		leaveStudentLink.click();
	}*/

	//click On examLink
	public void examLink(String linkName)
	{
		examLink.click();
		if (linkName.equalsIgnoreCase("Create Exam")) 
		{
			createExamLink.click();
		}
		else if(linkName.equalsIgnoreCase("Exam TimeTable"))
		{
			examTimetableLink.click();
		}
		else if(linkName.equalsIgnoreCase("Student Exam Marks"))
		{
			studentExamMarksLink.click();
		}
		else if(linkName.equalsIgnoreCase("student Exam Marks History"))
		{
			studentExamMarksHistoryLink.click();
		}
		
	}
/*
	//click On createExamLink
	public void createExamLink()
	{
		createExamLink.click();
	}

	//click On examTimetableLink
	public void examTimetableLink()
	{
		examTimetableLink.click();
	}

	//click On studentExamMarksLink
	public void studentExamMarksLink()
	{
		studentExamMarksLink.click();
	}

	//click On studentExamMarksHistoryLink
	public void studentExamMarksHistoryLink()
	{
		studentExamMarksHistoryLink.click();
	}
*/
	//click On pettyCashLink
	public void pettyCashLink()
	{
		pettyCashLink.click();
	}

	//signout from App

	public void signOut()
	{
		menuBtn.click();
		signOutLink.click();
	}
	
	//verify admin dashboard page
	public void verifyAdminDashboard(WebDriver driver)
	{
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("dashboard"))
		{
			System.out.println("Admin dashboard page is displayed");
		}
		else
		{
			System.out.println("Admin dashboard page is not displayed");
		}
	}
	

}
