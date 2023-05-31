package studentManagementSystemGenericUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * this class consists of excel related methods like insert data,fetch data,getRowNumer
 * @author dell
 *
 */
public class ExcelUtility 
{
	/**
	 * this method is used to insert data into excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void insertDataIntoExcel(String sheetName,int rowNum, int cellNum,String data ) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fileInputStream = new FileInputStream(IPathConstants.excelPath);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fileOutputStream = new FileOutputStream(IPathConstants.excelPath);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
	}
	/**
	 * this method is used to fetch the data into excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getExcelData(String sheetName,int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(IPathConstants.excelPath);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		DataFormatter dataFormatter = new DataFormatter();
		String data = dataFormatter.formatCellValue(cell);
		return data;
	}
	
	/**
	 * this method is used to get the count of rows created in excel sheet
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getLastRowNumber(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(IPathConstants.excelPath);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		int row = sheet.getLastRowNum();
		return row;
	}
	
	/**
	 * this method is used to get the count of cells created in excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getLastCellNum(String sheetName, int rowNum) throws EncryptedDocumentException, IOException {
		FileInputStream fileInputStream = new FileInputStream(IPathConstants.excelPath);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		short lastCellNum = sheet.getRow(rowNum).getLastCellNum();
		return lastCellNum;
	}
	
	public HashMap<String,String> getMultipleData(String SheetName) throws Throwable
	{
		
		FileInputStream fi = new FileInputStream(IPathConstants.excelPath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(SheetName);
		int count = sh.getLastRowNum();		
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < count; i++) 
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}
		return map;
	}
	
	
	public Object[][] data(String sheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.excelPath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		int lastRowNum = sheet.getLastRowNum()+1;
		short lastCellNum = sheet.getRow(1).getLastCellNum();
		Object[][] obj=new Object[lastRowNum][lastCellNum];
		for (int i = 1; i <lastRowNum; i++) 
		{
			for (int j = 0; j <lastCellNum; j++)
			{
				obj[i][j]= sheet.getRow(i).getCell(j).getStringCellValue();
			}
		} 
		return obj;
	}	
}




















