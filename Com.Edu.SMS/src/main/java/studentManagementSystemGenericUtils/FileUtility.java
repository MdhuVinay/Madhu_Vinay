package studentManagementSystemGenericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class is used to handle file utilities
 * @author dell
 *
 */

public class FileUtility 
{
	/**
     * this method is used to get value from the property File
     * @param key
     * @return
     * @throws IOException
     */
	public String getPropertyKeyValue(String key)throws Throwable 
	{
		FileInputStream fileInputStream = new FileInputStream(IPathConstants.propertyFilePath);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String value = properties.getProperty(key);
		return value;	
	}
}