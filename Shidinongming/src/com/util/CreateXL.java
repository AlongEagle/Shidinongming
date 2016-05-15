package com.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CreateXL {
	public static String xlsFile = "d:/test.xls"; // �����Excel�ļ������

	public static void main(String args[]) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook(); 
			HSSFSheet sheet = workbook.createSheet(); 
			workbook.setSheetName(0, "firstSheet", HSSFWorkbook.ENCODING_UTF_16);
			
			
			HSSFRow row = sheet.createRow((short) 0);
			
			
			HSSFCell cell = row.createCell((short) 0);
			
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			
			
			cell.setCellValue("���Գɹ�");
			FileOutputStream fOut = new FileOutputStream(xlsFile);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
			
			
			
			FileInputStream fIn = new FileInputStream(xlsFile);
			HSSFWorkbook readWorkBook = new HSSFWorkbook(fIn);
			HSSFSheet readSheet = readWorkBook.getSheet("firstSheet");
			HSSFRow readRow = readSheet.getRow(0);
			HSSFCell readCell = readRow.getCell((short) 0);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String resultSetToExcel(ResultSet rs, String xlsName,
			String sheetName) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName, HSSFWorkbook.ENCODING_UTF_16);
		HSSFRow row = sheet.createRow((short) 0);
		;
		HSSFCell cell;
		ResultSetMetaData md = rs.getMetaData();
		int nColumn = md.getColumnCount();
		//
		for (int i = 1; i <= nColumn; i++) {
			cell = row.createCell((short) (i - 1));
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(md.getColumnLabel(i));
			

		}

		int iRow = 1;
		//
		while (rs.next()) {
			row = sheet.createRow((short) iRow);
			;
			for (int j = 1; j <= nColumn; j++) {
				cell = row.createCell((short) (j - 1));
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				// System.out.println("rs------------>"+rs.getObject(1).toString());
				// System.out.println("j----->"+j);
				cell.setCellValue(rs.getObject(j).toString());
			}
			iRow++;
		}
		FileOutputStream fOut = new FileOutputStream(xlsName);
		workbook.write(fOut);
		rs.close();
		fOut.flush();
		fOut.close();
		return "printSuccess";

	}

}