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

public class AddStudentPage 
{
	@FindBy(xpath = "//input[@id='g_full_name']") private WebElement g_FullNameTxtFld;
	@FindBy(xpath = "//input[@id='g_i_name']") private WebElement g_Name_WI_TxtFld;
	@FindBy(xpath = "//input[@id='g_address']") private WebElement g_Address_TxtFld;
	@FindBy(xpath = "//input[@id='g_email']") private WebElement g_Email_TxtFld;
	@FindBy(xpath = "//input[@id='g_phone']") private WebElement g_phoneNumTxtFld;
	@FindBy(xpath = "//input[@id='g_b_date']") private WebElement g_DOB_TxtFld;
	@FindBy(xpath = "(//select[@id='g_gender'])[1]") private WebElement g_Gender_Dropdown;
	@FindBy(xpath = "//input[@id='g_fileToUpload']") private WebElement g_Uploadfile_Btn;
	@FindBy(xpath = "//button[@id='btnSubmit']") private WebElement next_Btn;
	@FindBy(xpath = "//select[@id='grade']") private WebElement gradeDropdown;
	@FindBy(xpath = "//input[@id='checkbox']") private List<WebElement> checkbox;
	@FindBy(xpath = "//button[.='Submit']") private WebElement submitBtn;
	@FindBy(xpath = "//div[@class='msk-heading']//span[@class='glyphicon glyphicon-remove']") private WebElement cancelBtn;
	@FindBy(xpath = "//div[@class='box-body table-responsive']//tr//td[2]") private List<WebElement> StdNames;

	


	public AddStudentPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getG_FullNameTxtFld() {
		return g_FullNameTxtFld;
	}


	public WebElement getG_Name_WI_TxtFld() {
		return g_Name_WI_TxtFld;
	}


	public WebElement getG_Address_TxtFld() {
		return g_Address_TxtFld;
	}


	public WebElement getG_Email_TxtFld() {
		return g_Email_TxtFld;
	}


	public WebElement getG_phoneNumTxtFld() {
		return g_phoneNumTxtFld;
	}


	public WebElement getG_DOB_TxtFld() {
		return g_DOB_TxtFld;
	}


	public WebElement getG_Gender_Dropdown() {
		return g_Gender_Dropdown;
	}


	public WebElement getG_Uploadfile_Btn() {
		return g_Uploadfile_Btn;
	}


	public WebElement getG_Next_Btn() {
		return next_Btn;
	}


	public WebElement getGradeDropdown() {
		return gradeDropdown;
	}


	public List<WebElement> getCheckbox() {
		return checkbox;
	}


	public WebElement getSubmitBtn()
	{
		return submitBtn;
	}


	public WebElement getCancelBtn() {
		return cancelBtn;
	}
	public List<WebElement> getStdNames() {
		return StdNames;
	}


	//Actions
	public void enterGuardianFullName(ExcelUtility eLib,String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetName, rowNum, cellNUm);
		g_FullNameTxtFld.sendKeys(value);
	}

	public void enterGuardianNameWithInitials(ExcelUtility eLib,String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		String fullname = eLib.getExcelData(sheetName, rowNum, cellNUm);
		g_Name_WI_TxtFld.sendKeys(fullname);
	}

	public void enterGuardianAddress(ExcelUtility eLib,String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetName, rowNum, cellNUm);
		g_Address_TxtFld.sendKeys(value);
	}

	public void enterGuardianEmail(ExcelUtility eLib,String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetName, rowNum, cellNUm);
		g_Email_TxtFld.sendKeys(value);
	}

	public void enterGuardinPhoneNum(ExcelUtility eLib,String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetName, rowNum, cellNUm);
		g_phoneNumTxtFld.sendKeys(value);
	}

	public void enterGuardianDOB(ExcelUtility eLib,String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		String value = eLib.getExcelData(sheetName, rowNum, cellNUm);
		g_DOB_TxtFld.sendKeys(value);
	}
	public void nextBtn()
	{
		next_Btn.click();
	}
	public void uploadGuardianPhoto(String path)
	{
		g_Uploadfile_Btn.sendKeys(path);	
	}
	public void selectCheckbox()
	{
		for (WebElement cb : checkbox) 
		{
			cb.click();
		}
	}
	public void submitBtn()
	{
		submitBtn.click();
	}
	public void cancelBtn()
	{
		cancelBtn.click();
	}


	//create teacher
	JavaUtility jLib=new JavaUtility();
	int randomNumber = jLib.getRandomNumber();
	public void createStudent(HashMap<String, String> fields, WebDriver driver, WebdriverUtility wLib, String path, String grade )throws Throwable
	{
		for(Entry<String, String> set: fields.entrySet())
		{

			if(set.getKey().equalsIgnoreCase("//input[@id='index_number']")||set.getKey().equalsIgnoreCase("//input[@id='i_name']")||set.getKey().equalsIgnoreCase("//input[@id='email']")||set.getKey().equalsIgnoreCase("//input[@id='g_i_name']")||set.getKey().equalsIgnoreCase("//input[@id='g_email']"))
			{
				driver.findElement(By.xpath(set.getKey())).sendKeys(randomNumber+set.getValue());
			}
			else
			{
				driver.findElement(By.xpath(set.getKey())).sendKeys(set.getValue());
			}
		}
		CommonComponents commonComponents = new CommonComponents(driver);
		commonComponents.selectGender(wLib, "Male");
		wLib.selectElementInDropdown("Male", g_Gender_Dropdown);
		uploadGuardianPhoto(path);
		nextBtn();
		wLib.selectElementInDropdown(grade, gradeDropdown);
//		selectCheckbox();
		wLib.waitTillElementToBeClickable(driver, submitBtn);
		cancelBtn();
	}
	//edit student
	public void editStudent(WebDriver driver, WebdriverUtility wLib, ExcelUtility eLib) throws Throwable, IOException
	{
		String iName = randomNumber+eLib.getExcelData("AddTeacher", 2, 1);
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		CommonComponents commonComponents = new CommonComponents(driver);
		adminDashboard.clickOnstudentLink("All Student", wLib, driver);
		commonComponents.selectGrade(wLib, driver, "Grade 2");
		commonComponents.clickOnSubmitBtnOfPage();
		commonComponents.showDropdown(wLib);
		commonComponents.search(iName);
		//	boolean flag=false;
			for (WebElement std : StdNames)
			{
				String text = std.getText();
				if(text.equalsIgnoreCase(iName))
				{
					std.click();
					break;
				}
			}
		}
	}


