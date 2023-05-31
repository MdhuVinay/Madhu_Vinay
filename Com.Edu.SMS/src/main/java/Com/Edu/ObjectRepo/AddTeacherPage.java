package Com.Edu.ObjectRepo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class AddTeacherPage {

	//initialization
	@FindBy(xpath = "//input[@id='index_number']") private WebElement indexNumTxtFld;
	@FindBy(xpath = "//input[@id='full_name']") private WebElement fullNameTxtFld;
	@FindBy(xpath = "//input[@id='i_name']") private WebElement nameWithInitialTxtFld;
	@FindBy(xpath = "//input[@id='address']") private WebElement addressTxtFld;
	@FindBy(xpath = "//input[@id='phone']") private WebElement phoneNumTxtFld;
	@FindBy(xpath = "//input[@id='email']") private WebElement emailTxtFld;
	@FindBy(xpath = "//select[@id='gender']") private WebElement genderDropdown;
	@FindBy(xpath = "//button[@class='btn btn-primary']") private WebElement submitBtnOfPage;
	@FindBy(xpath = "//input[@class='form-control input-sm']") private WebElement searchTxtFld;
	@FindBy(xpath = "//a[@href='#modalViewform']") private  List<WebElement> allTeacherNames;


	//declaration
	public  AddTeacherPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//utilization
	public WebElement getIndexNumTxtFld() {
		return indexNumTxtFld;
	}


	public WebElement getFullNameTxtFld() {
		return fullNameTxtFld;
	}


	public WebElement getNameWithInitialTxtFld() {
		return nameWithInitialTxtFld;
	}


	public WebElement getAddressTxtFld()
	{
		return addressTxtFld;
	}


	public WebElement getPhoneNumTxtFld() {
		return phoneNumTxtFld;
	}

	public WebElement getEmailTxtFld() {
		return emailTxtFld;
	}
	public WebElement getGenderDropdown() {
		return genderDropdown;
	}
	public WebElement getSubmitBtnOfPage() {
		return submitBtnOfPage;
	}
	public WebElement getSearchTxtFld() {
		return searchTxtFld;
	}
	
	public List<WebElement> getAllTeacherNames() {
		return allTeacherNames;
	}
	

	//utilization
	//enetr index nmber
	public void indexNumber(String indexNum) throws EncryptedDocumentException, IOException	
	{
//		String value = eLib.getExcelData(sheetName, rowNum, cellNum);
		indexNumTxtFld.sendKeys(indexNum);
	}
	
	//enter full name into full name textfield
	public void fullName(String fullName) throws EncryptedDocumentException, IOException
	{
//		String value = eLib.getExcelData(sheetName, rowNum, cellNum);
		fullNameTxtFld.sendKeys(fullName);
	}

	//enter Name With Initials into Name With Initials textfield
	public void nameWithInitial(String nameWithInitial) throws EncryptedDocumentException, IOException
	{
//		String nameWithInitial = eLib.getExcelData(sheetName, rowNum, cellNum);
		nameWithInitialTxtFld.sendKeys(nameWithInitial);
	}

	//enetr Address into Adress textfield
	public void adress(String adress) throws EncryptedDocumentException, IOException
	{
//		String adress = eLib.getExcelData(sheetName, rowNum, cellNum);
		addressTxtFld.sendKeys(adress);
	}
	//enetr Phone Number into Phone Number textfield
	public void phoneNum(String phoneNum) throws EncryptedDocumentException, IOException
	{
//		String phoneNum = eLib.getExcelData(sheetName, rowNum, cellNum);
		phoneNumTxtFld.sendKeys(phoneNum);
	}
	//enetr emanil into email textfield
	public void email(String email) throws EncryptedDocumentException, IOException
	{
//		String email = eLib.getExcelData(sheetName, rowNum, cellNum);
		emailTxtFld.sendKeys(email);
	}
	
	//search
		public void search(String value )
		{
			searchTxtFld.sendKeys(value);	
		}

	/*//create teacher
	public void createTeacher(WebdriverUtility wLib, JavaUtility jLib,ExcelUtility eLib) throws EncryptedDocumentException, IOException
	{
		int randomNumber = jLib.getRandomNumber();
		String indexNum = eLib.getExcelData("Data", 0, 1+randomNumber);
		indexNumber(indexNum);
		String fullName = eLib.getExcelData("Data", 1, 1+randomNumber);
		fullName(fullName);
		String nameWithInitial = eLib.getExcelData("Data", 2, 1+randomNumber);
		nameWithInitial(nameWithInitial);
		String adress = eLib.getExcelData("Data", 3, 1+randomNumber);
		adress(adress);
		String phoneNum = eLib.getExcelData("Data", 4, 1);
		phoneNum(phoneNum);
		String email = eLib.getExcelData("Data", 5, 1);
		email(email);
		CommonComponents commonComponents = new CommonComponents();
		commonComponents.selectGender(wLib, "Male");
		commonComponents.uploadPhoto(wLib, addressTxtFld, "C:\\Users\\dell\\Pictures\\sms.png");
		commonComponents.getSubmitBtnOfPage();
	}*/


	//Business Library
	//create teacher
	JavaUtility jLib=new JavaUtility();
	int randomNumber = jLib.getRandomNumber();
	public void createTeacher(HashMap<String, String> fields, WebDriver driver, WebdriverUtility wLib )throws Throwable
	{
		for(Entry<String, String> set: fields.entrySet())
		{
			
			if(set.getKey().equalsIgnoreCase("index_number")||set.getKey().equalsIgnoreCase("email")||set.getKey().equalsIgnoreCase("i_name"))
			{
				driver.findElement(By.name(set.getKey())).sendKeys(randomNumber+set.getValue());
			}
			else
			{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
			}
		}
		wLib.selectElementInDropdown("Male", genderDropdown);
		submitBtnOfPage.click();
		
	}
	
	//verify teacher
	public void verifyTeacher(WebDriver driver, WebdriverUtility wLib, ExcelUtility eLib, JavaUtility jLib) throws Throwable
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		String fullname =randomNumber+eLib.getExcelData("Data", 2, 1);
		CommonComponents commonComponents = new CommonComponents(driver);
		adminDashboard.teacherLink();
		Thread.sleep(1000);
		adminDashboard.allTeacherLink();
		commonComponents.showDropdown(wLib);
		searchTxtFld.sendKeys(fullname);
		for (WebElement teacher : allTeacherNames)
		{
			String text = teacher.getText();
			if (text.equalsIgnoreCase(fullname))
			{
				System.out.println("teacher is created");
			}
			else
			{
				System.out.println("teacher is not created");
			}
		}
	}
}
