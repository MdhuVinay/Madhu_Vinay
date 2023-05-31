package studentManagementSystemGenericUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Com.Edu.ObjectRepo.AdminDashboardPage;
import Com.Edu.ObjectRepo.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public static WebDriver sdriver;
	public JavaUtility jLib=new JavaUtility();
	public WebdriverUtility wLib=new WebdriverUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public DataBaseUtility dLib=new DataBaseUtility();
	public WebDriver driver;

	//connect to database
	@BeforeSuite
	public void configureBeforeSuite() throws Throwable
	{
//		dLib.connectDB();
		System.out.println("-- connected to DB--");
	}

	//browser setup
	@BeforeClass
	public void configureBeforeClass() throws Throwable
	{
		String BROWSER = fLib.getPropertyKeyValue("browser");
		if (BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		sdriver=driver;
		wLib.maximizeWindow(driver);
	}

	//login to application
	@BeforeMethod
	public void configureBeforeMethod() throws Throwable
	{
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("adminemail");
		String PASSWORD = fLib.getPropertyKeyValue("adminpassword");

		driver.get(URL);
		wLib.waitTillPageGetsLoad(driver);

		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);
		System.out.println("--login successfull--");
	}

	//logout from application
	@AfterMethod
	public void configureAfterMethod() throws Throwable
	{
		AdminDashboardPage adminDashboard = new AdminDashboardPage(driver);
		adminDashboard.signOut();
		System.out.println("--logout successfull--");
	}

	//close the browser
	@AfterClass
	public void configureAfterClass() throws Throwable
	{
		driver.quit();
	}
	
	//close database
	@AfterSuite
	public void configureAfterSuite() throws Throwable
	{
//		dLib.closeDB();
		System.out.println("DB closed");
	}

}
