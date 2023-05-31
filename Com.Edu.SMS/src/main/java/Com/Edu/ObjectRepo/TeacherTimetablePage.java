package Com.Edu.ObjectRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherTimetablePage 
{
	@FindBy(xpath = "//tbody//tr//th[1]") private List<WebElement> timeDetails;
	@FindBy(xpath = "//tbody//tr//td")private List<WebElement> allDetails;
	
	public TeacherTimetablePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getTimeDetails() {
		return timeDetails;
	}

	public List<WebElement> getAllDetails() {
		return allDetails;
	}
	
	
	
}
