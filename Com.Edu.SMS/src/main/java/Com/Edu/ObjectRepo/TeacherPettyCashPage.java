package Com.Edu.ObjectRepo;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import studentManagementSystemGenericUtils.ExcelUtility;

public class TeacherPettyCashPage 
{
	//declaration
	@FindBy(xpath = "//input[@class='_desc form-control']") private WebElement discriptionTxtFld;

	@FindBy(xpath = "//input[@class='amount form-control']") private WebElement amountTxtFld;

	@FindBy(xpath = "//input[@id='btnSubmit']")private WebElement submitBtn;

	@FindBy(xpath = "//div[@class='msk-heading']//span[@class='glyphicon glyphicon-remove']") private WebElement cancelBtn;
	
	@FindBy(xpath = "//input[@class='form-control input-sm']") private WebElement searchTxtFld;

	@FindBy(xpath = "//input[@id='btnSubmit']") private WebElement submitBtnOfPopup;

	

	//initialization
	public	TeacherPettyCashPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getDiscriptionTxtFld() {
		return discriptionTxtFld;
	}

	public WebElement getAmountTxtFld() {
		return amountTxtFld;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getCancelBtn() {
		return cancelBtn;
	}
	public WebElement getSearchTxtFld() {
		return searchTxtFld;
	}
	public WebElement getSubmitBtnOfPopup() {
		return submitBtnOfPopup;
	}

	//actions
	//enter value into description text field
	public void description(String value) throws EncryptedDocumentException, IOException
	{
		discriptionTxtFld.sendKeys(value);
	}
	//enter amount into amount text field
	public void amount(String value) throws EncryptedDocumentException, IOException
	{
		amountTxtFld.sendKeys(value);
	}

	//click on submit button
	public void submitBtn()
	{
		submitBtn.click();
	}

	//click on cancel button
	public void cancelBtn()
	{
		cancelBtn.click();
	}
	//search
	public void search(String value )
	{
		searchTxtFld.sendKeys(value);	
	}
	public void submitBtnOfPopup()
	{
		submitBtnOfPopup.click();
	}

	//add pettycash
	public void addPettycash(WebDriver driver, String description, String amount) throws Throwable, IOException
	{
		TeacherDashboardPage teacherDashboard = new TeacherDashboardPage(driver);
		CommonComponents commonComponents = new CommonComponents(driver);
		teacherDashboard.clickOnMyPettycash();
		commonComponents.clickOnAddBtn();
		description(description);
		amount(amount);
		submitBtnOfPopup();
	}
	
	
	


}
