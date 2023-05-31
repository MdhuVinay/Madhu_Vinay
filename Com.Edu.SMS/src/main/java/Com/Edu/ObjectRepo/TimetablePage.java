package Com.Edu.ObjectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TimetablePage 
{
	
	
	@FindBy(xpath = "//div[@class='panel']//select[@name='day']") private WebElement editDayDropdown;
	
	
}
