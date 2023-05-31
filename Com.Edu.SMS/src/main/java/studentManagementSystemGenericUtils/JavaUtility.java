package studentManagementSystemGenericUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;
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
	
	public void escapeKey() throws Throwable
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

	}
}
