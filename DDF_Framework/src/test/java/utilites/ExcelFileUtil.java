package utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil{
Workbook wb;
//constructor for reading path of excel file
public ExcelFileUtil(String excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath);
	wb = WorkbookFactory.create(fi);
}
//method for counting no of rows in sheet
public int rowcount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
//method for reading cell data
public String getcellData(String sheeetname,int row,int column)
{
	String data ="";
	if(wb.getSheet(sheeetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int celldata = (int)wb.getSheet(sheeetname).getRow(row).getCell(column).getNumericCellValue();
		data= String.valueOf(celldata);
	}
	else
	{
		data = wb.getSheet(sheeetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method for writing results
public void setcelldata(String sheetname,int row, int column,String status,String writeExcel)throws Throwable
{
	//get sheet from wb
	Sheet ws = wb.getSheet(sheetname);
	//get row from sheet
	Row rownum = ws.getRow(row);
	//create cell
	Cell cell = rownum.createCell(column);
	//write status
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
	 CellStyle style = wb.createCellStyle();
	 org.apache.poi.ss.usermodel.Font font = wb.createFont();
	 font.setColor(IndexedColors.GREEN.getIndex());
	 font.setBold(true);
	 style.setFont(font);
	 ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("fail"))
	{
		CellStyle style = wb.createCellStyle();
		 org.apache.poi.ss.usermodel.Font font = wb.createFont();
		 font.setColor(IndexedColors.RED.getIndex());
		 font.setBold(true);
		 style.setFont(font);
		 ws.getRow(row).getCell(column).setCellStyle(style);		
	}
	else if(status.equalsIgnoreCase("blocked"))
	{
		CellStyle style = wb.createCellStyle();
		 org.apache.poi.ss.usermodel.Font font = wb.createFont();
		 font.setColor(IndexedColors.BLUE.getIndex());
		 font.setBold(true);
		 style.setFont(font);
		 ws.getRow(row).getCell(column).setCellStyle(style);
	}
	FileOutputStream fo = new FileOutputStream(writeExcel);
	wb.write(fo);
}
public static void main(String[] args) throws Throwable {
	ExcelFileUtil xl = new ExcelFileUtil("D:/sample.xlsx");
	//count no of rows in emp sheet
	int rc = xl.rowcount("emp");
	System.out.println(rc);
	for(int i=1;i<=rc;i++) {
		String fname = xl.getcellData("emp", i, 0);
		String mname = xl.getcellData("emp", i, 1);
		String lname = xl.getcellData("emp", i, 2);
		String eid = xl.getcellData("emp", i, 3);
		System.out.println(fname+"   "+mname+"   "+lname+"   "+eid);
		//xl.setcelldata("emp", i, 4, "pass", "D:/results.xlsx");
		//xl.setcelldata("emp", i, 4, "fail", "D:/results.xlsx");
		xl.setcelldata("emp", i, 4, "blocked", "D:/results.xlsx");
	}
}
{
	
}



















}
