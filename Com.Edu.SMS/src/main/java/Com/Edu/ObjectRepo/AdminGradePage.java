package Com.Edu.ObjectRepo;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.ExcelUtility;
import studentManagementSystemGenericUtils.JavaUtility;

public class AdminGradePage 
{
	//declaration
	@FindBy(xpath = "//input[@id='name']") private WebElement gradeName;
	@FindBy(xpath = "//input[@id='admission_fee']") private WebElement admissionFee;
	@FindBy(xpath = "//input[@id='hall_charge']") private WebElement hallCharge;
	@FindBy(xpath = "//button[@id='btnSubmit']") private WebElement nextBtn;
	@FindBy(xpath = "//input[@class='mark-range form-control']") private WebElement rangeTxtFld;
	@FindBy(xpath = "//input[@class='mark-grade form-control']")private WebElement gradeTxtFld;
	@FindBy(xpath = "//button[@class='btn btn-info btnS']") private WebElement submitBtn;
	
	//initialization
	public AdminGradePage(WebDriver driver,JavaUtility jLib, ExcelUtility eLib)
	{
		PageFactory.initElements(driver, this);
	}
	
	//actions
	//create grade
	public String createGrade(JavaUtility jLib, ExcelUtility eLib, WebDriver driver) throws Throwable
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		adminDashboard.GradeLink();
		String name=jLib.getRandomNumber()+ eLib.getExcelData("Grade", 0, 1);
		gradeName.sendKeys(name);
		String admissionFees=jLib.getRandomNumber()+ eLib.getExcelData("Grade", 1, 1);
		admissionFee.sendKeys(admissionFees);
		String hallCharges=jLib.getRandomNumber()+ eLib.getExcelData("Grade", 2, 1);
		hallCharge.sendKeys(hallCharges);
		nextBtn.click();
		rangeTxtFld.sendKeys("70-80");
		gradeTxtFld.sendKeys("F");
		submitBtn.click();
		return name;
	}
	
	
}
