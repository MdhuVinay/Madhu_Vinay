package Com.Edu.ObjectRepo;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class CommonComponents 
{
	//initialization
	@FindBy(xpath = "//button[@class='btn btn-primary']") private WebElement submitBtnOfPage;

	@FindBy(xpath = "//a[@class='btn btn-success btn-sm pull-right']") private WebElement addButton;

	@FindBy(xpath = "//select[@id='day']") private WebElement dayDropdown;

	@FindBy(xpath = "//select[@id='subject']") private WebElement subjectDropdown;

	@FindBy(xpath = "//select[@id='teacher']") private WebElement teacherDropdown;

	@FindBy(xpath = "//select[@id='classroom']") private WebElement classroomDropdown;

	@FindBy(xpath = "//input[@id='start_time']") private WebElement startTime;

	@FindBy(xpath = "//input[@id='end_time']") private WebElement endTime;

	@FindBy(xpath = "//button[@id='btnSubmit']") private WebElement submitBtnOfPopup;

	@FindBy(xpath = "//div[@class='panel-heading']//span[@class='glyphicon glyphicon-remove']") private WebElement closeBtnOfPopup;

	@FindBy(xpath = "//input[@class='form-control input-sm']") private WebElement searchTxtFld;

	@FindBy(xpath = "//select[@class='form-control input-sm']") private WebElement showDropdown;
	
	@FindBy(xpath = "//select[@id='gender']") private WebElement genderDropdown;
	
	@FindBy(xpath = "//input[@id='fileToUpload']") private WebElement chooseFileBtn;
	@FindBy(xpath = "//a[normalize-space()='Next']") private WebElement nextBtn;
	@FindBy(xpath = "//select[@id='grade']") private WebElement grade;
	@FindBy(xpath = "//input[@id='fee']") private WebElement fees;
	


	

	



	

	//declaration
	public CommonComponents(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getSubmitBtnOfPage() {
		return submitBtnOfPage;
	}

	public WebElement getAddButton() {
		return addButton;
	}

	public WebElement getDayDropdown() {
		return dayDropdown;
	}

	public WebElement getSubjectDropdown() {
		return subjectDropdown;
	}

	public WebElement getTeacherDropdown() {
		return teacherDropdown;
	}

	public WebElement getClassroomDropdown() {
		return classroomDropdown;
	}

	public WebElement getStartTime() {
		return startTime;
	}

	public WebElement getEndTime() {
		return endTime;
	}

	public WebElement getSubmitBtnOfPopup() {
		return submitBtnOfPopup;
	}

	public WebElement getCloseBtnOfPopup() {
		return closeBtnOfPopup;
	}

	public WebElement getSearchTxtFld() 
	{
		return searchTxtFld;
	}

	public WebElement getShowDropdown() {
		return showDropdown;
	}
	

	public WebElement getGenderDropdown() {
		return genderDropdown;
	}
	
	public WebElement getChooseFileBtn() {
		return chooseFileBtn;
	}
	public WebElement getNextBtn() {
		return nextBtn;
	}
	public WebElement getGrade() {
		return grade;
	}
	public WebElement getFees() {
		return fees;
	}

	//select day in daydropdown
	public void selectDay(String day,WebdriverUtility wLib)
	{
		wLib.selectElementInDropdown(dayDropdown, day);
	}

	//select subject in subjectdropdown
	public void selectSubject(String subject,WebdriverUtility wLib)
	{
		wLib.selectElementInDropdown(subject, subjectDropdown);
	}

	//select teacher in teacherdropdown
	public void selectTeacher(String teacher,WebdriverUtility wLib)
	{
		wLib.selectElementInDropdown(teacher, teacherDropdown);
	}

	//select classroom in classroomdropdown
	public void selectClassroom(String classroom,WebdriverUtility wLib)
	{
		wLib.selectElementInDropdown(classroom, classroomDropdown);
	}

	//enter start time into start time textfield
	public void startTime(ExcelUtility eLib, String sheetname, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetname, rowNum, cellNum);
		startTime.sendKeys(value);
	}

	//enter end time into end time textfield
	public void endTime(ExcelUtility eLib, String sheetname, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetname, rowNum, cellNum);
		endTime.sendKeys(value);
	}
	//select gender from dropdown
	public void selectGender(WebdriverUtility wLib, String text)
	{
		wLib.selectElementInDropdown(text,genderDropdown );
	}
	
	//upload photo
	public void uploadPhoto(WebdriverUtility wLib,WebElement element, String path)
	{
		wLib.fileUpload(element, path);
	}
	
	//search textfield
	public void search(String text)
	{
		searchTxtFld.sendKeys(text);
	}
	public void clickOnAddBtn()
	{
		addButton.click();
	}
	
	public void showDropdown(WebdriverUtility wLib)
	{
		wLib.selectElementInDropdown("100", showDropdown);
	}
	public void clickOnNextBtn()
	{
		nextBtn.click();
	}
	public void selectGrade(WebdriverUtility wLib, WebDriver driver, String text)
	{
		wLib.selectElementInDropdown(text, grade);
	}
	public void clickOnSubmitBtnOfPage()
	{
		submitBtnOfPage.click();
	}
	public void showDropdown(WebdriverUtility wLib, WebDriver driver)
	{
		wLib.selectElementInDropdown("100", showDropdown);
	}
	public void fees(String fee) 
	{
		fees.sendKeys(fee);
	}
	public String getTeacherText()
	{
		return teacherDropdown.getText();
		
	}
	public String verifyTeacher()
	{
		String text = teacherDropdown.getText();
		return text;
	}
	public void clickOnSubmitButtonOfPopup()
	{
		try {
			submitBtnOfPopup.click();
		} catch (Exception e) {
			submitBtnOfPopup.click();		}
		
	}
}
