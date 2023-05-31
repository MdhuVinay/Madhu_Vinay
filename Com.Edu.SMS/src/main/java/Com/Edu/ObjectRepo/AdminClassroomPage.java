package Com.Edu.ObjectRepo;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.JavaUtility;
import studentManagementSystemGenericUtils.WebdriverUtility;

public class AdminClassroomPage 
{






	//declaration
	@FindBy(xpath = "//input[@id='name']") private WebElement name;
	@FindBy(xpath = "//input[@id='student_count']") private WebElement stdCount;
	@FindBy(xpath = "//tbody//tr//td[2]") private List<WebElement> className;
	@FindBy(xpath = "//a[normalize-space()='Next']") private WebElement nextBtn;





	//initialization
	public AdminClassroomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//utilization
	public WebElement getName() {
		return name;
	}
	public WebElement getStdCount() {
		return stdCount;
	}
	public List<WebElement> getClassName()
	{
		return className;
	}
	public WebElement getNextBtn() {
		return nextBtn;
	}

	//actions
	//create classroom

	JavaUtility jLib=new JavaUtility();
	int randomNumber = jLib.getRandomNumber();
	//create classroom
	public String createClassromm( WebDriver driver,ExcelUtility eLib) throws Throwable
	{
		CommonComponents commonComponents = new CommonComponents(driver);
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		adminDashboard.getClassroomLink().click();
		String className=randomNumber+eLib.getExcelData("Classroom", 0, 1);
		name.sendKeys(className);
		String count=randomNumber+eLib.getExcelData("Classroom", 1, 1);
		stdCount.sendKeys(count);
		commonComponents.clickOnSubmitBtnOfPage();
				return className;
	}

	//verify classroom
	public void verifyClassroom(WebDriver driver,ExcelUtility eLib,WebdriverUtility wLib) throws Throwable
	{

		CommonComponents commonComponents = new CommonComponents(driver);
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		adminDashboard.getClassroomLink().click();
		String className=randomNumber+eLib.getExcelData("Classroom", 0, 1);
		commonComponents.showDropdown(wLib);
		commonComponents.search(className);
		
			for (WebElement clsName : this.className)
			{
				String classText = clsName.getText();
				if (classText.equalsIgnoreCase(className)) 
				{
					System.out.println("class is created");
					break;
				} 
				
				else 
				{
					nextBtn.click();
				}
			}


	}




}
