package Com.Edu.ObjectRepo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class AddTeacherPage 
{
	/*@CacheLookup
	 * This annotation, when applied over a WebElement, instructs Selenium to keep a
	 * cache of the WebElement instead of searching for the WebElement every time
	 * from the WebPage. This helps us save a lot of time.
	 */
	//initialization
	@FindBy(xpath = "//input[@id='index_number']")
	@CacheLookup
	private WebElement indexNumTxtFld;
	

	@FindBy(xpath = "//input[@id='full_name']") 
	@CacheLookup
	private WebElement fullNameTxtFld;

	@FindBy(xpath = "//input[@id='i_name']")
	@CacheLookup
	private WebElement nameWithInitialTxtFld;

	@FindBy(xpath = "//input[@id='address']") 
	@CacheLookup
	private WebElement addressTxtFld;

	@FindBy(xpath = "//input[@id='phone']")
	@CacheLookup
	private WebElement phoneNumTxtFld;

	@FindBy(xpath = "//input[@id='email']") 
	@CacheLookup
	private WebElement emailTxtFld;

	@FindBy(xpath = "//select[@id='gender']") 
	@CacheLookup
	private WebElement genderDropdown;

	@FindBy(xpath = "//button[@class='btn btn-primary']") 
	@CacheLookup
	private WebElement submitBtnOfPage;

	@FindBy(xpath = "//input[@class='form-control input-sm']")
	@CacheLookup
	private WebElement searchTxtFld;

	@FindBy(xpath = "//a[@href='#modalViewform']") 
	@CacheLookup
	private  List<WebElement> allTeacherNames;

	JavaUtility jLib=new JavaUtility();
	int randomNumber=jLib.getRandomNumber();

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

	public List<WebElement> getAllTeacherNames() 
	{
		return allTeacherNames;
	}

	//utilization
	//enetr index nmber
	public void indexNumber(String indexNum) throws EncryptedDocumentException, IOException	
	{
		indexNumTxtFld.sendKeys(indexNum);
	}

	//enter full name into full name textfield
	public void fullName(String fullName) throws EncryptedDocumentException, IOException
	{
		fullNameTxtFld.sendKeys(fullName);
	}

	//enter Name With Initials into Name With Initials textfield
	public void nameWithInitial(String nameWithInitial) throws EncryptedDocumentException, IOException
	{
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
		phoneNumTxtFld.sendKeys(phoneNum);
	}
	//enetr emanil into email textfield
	public void email(String email) throws EncryptedDocumentException, IOException
	{
		emailTxtFld.sendKeys(email);
	}

	//search
	public void search(String value )
	{
		searchTxtFld.sendKeys(value);	
	}

	//Business Library
	//create teacher



	public HashMap<String, String> createTeacher(HashMap<String, String> fields, WebDriver driver, WebdriverUtility wLib,JavaUtility jLib )throws Throwable
	{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String teacher = null;
		for(Entry<String, String> set: fields.entrySet())
		{
			if(set.getKey().equalsIgnoreCase("index_number")||set.getKey().equalsIgnoreCase("email")||set.getKey().equalsIgnoreCase("i_name")||set.getKey().equalsIgnoreCase("full_name"))
			{
				if (set.getKey().equalsIgnoreCase("i_name"))
				{
					teacher = randomNumber+set.getValue();
					driver.findElement(By.name(set.getKey())).sendKeys(teacher);
					hashMap.put(set.getKey(), teacher);
				}
				else if(set.getKey().equalsIgnoreCase("email"))
				{
					teacher =randomNumber+set.getValue();
					driver.findElement(By.name(set.getKey())).sendKeys(teacher);
					hashMap.put(set.getKey(), teacher);
				}
				else 
				{
					teacher = jLib.getRandomNumber()+set.getValue();
					driver.findElement(By.name(set.getKey())).sendKeys(randomNumber+set.getValue());
					hashMap.put(set.getKey(), teacher);
				}
			}
			else
			{
				teacher = randomNumber+set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
				hashMap.put(set.getKey(), teacher);
			}
		}
		wLib.selectElementInDropdown("Male", genderDropdown);
		submitBtnOfPage.click();
		return hashMap;
	}

	//verify teacher
	public void verifyTeacher(WebDriver driver, WebdriverUtility wLib, ExcelUtility eLib, JavaUtility jLib,String fullName) throws Throwable
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		CommonComponents commonComponents = new CommonComponents(driver);
		adminDashboard.teacherLink();
		Thread.sleep(1000);
		adminDashboard.allTeacherLink();
		commonComponents.showDropdown(wLib);
		searchTxtFld.sendKeys(fullName);
		for (WebElement teacher : allTeacherNames)
		{
			String text = teacher.getText();
			if (text.equalsIgnoreCase(fullName))
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
