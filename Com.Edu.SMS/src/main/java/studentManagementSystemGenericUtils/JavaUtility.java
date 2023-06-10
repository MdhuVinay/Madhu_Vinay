package studentManagementSystemGenericUtils;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
/**
 * 
 * @author dell
 *
 */

public class JavaUtility {
	 /**
     * this method is used to get a random number
     * @return
     */
	public int getRandomNumber()
	{
		Random random = new Random();
		int randNum = random.nextInt(100000);
		return randNum;
	}
	
	/**
	 *  this method is used to get a random alphabets
	 * @return
	 */
	
	public String getRandomString()
	{
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		//to convert to upper case
		//randomAlphabetic.toUpperCase();
		return randomAlphabetic;
	}
	
	/**
	 * this method is used to generate random 10 digit number
	 * @return
	 */
	public String gerRandomMobileNum()
	{
		String randomNumeric = RandomStringUtils.randomNumeric(10);
		return randomNumeric;
	}
	/**
	 * this method is used to generate random alphanumeric 
	 * @return
	 */
	public String gerRandomAlphaNumaric()
	{
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		String randomNumeric = RandomStringUtils.randomNumeric(10);
		return (randomNumeric+randomAlphabetic);
	}
	
	/**
	 * this method will current system date
	 * @return
	 */
	public String currentSystemDate()
	{
		Date date = new Date();
		String currentDate = date.toString();
		return currentDate;
	}
	
	/**
	 * this method is used to get current system date in required format
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		Date date = new Date();
		String cdate = date.toString();
		String[] d = cdate.split(" ");
		 
		int month = date.getMonth();
		String week = d[0];
		String Cdate = d[2];
		String year = d[5];
		
		String dateFormat=month+" "+week+" "+Cdate+" "+year;
		return dateFormat;
	}
	/**
	 * this method is used to press escape button
	 * @throws Throwable
	 */
	
	public void escapeKey() throws Throwable
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	/**
	 * this method is used to press cancel button
	 * @throws Throwable
	 */
	public void printCancelBtn()throws Throwable
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CANCEL);
		robot.keyRelease(KeyEvent.VK_CANCEL);
	}
}
