package Com.Edu.ObjectRepo;
/**
 * 
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	
	//declaration
	@FindBy(id = "email") private WebElement emailTextEdit;
	
	@FindBy(id = "password") private WebElement passwordTextEdit;
	
	@FindBy(id = "btnSubmit") private WebElement submitButton;
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//utilization
	public WebElement getEmailTextEdit() {
		return emailTextEdit;
	}

	public WebElement getPasswordTextEdit() {
		return passwordTextEdit;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	//create a method for login
	public void loginToApp(String email, String password)
	{
		emailTextEdit.sendKeys(email);
		passwordTextEdit.sendKeys(password);
		submitButton.click();
	}
	

}
