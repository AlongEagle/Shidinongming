package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewMethod2007 {

	public static void main(String[] args) {

		readExcel2007("D:\\3333.xlsx");
	}

	// 构造 XSSFWorkbook 对象，strPath 传入文件路径

	public static Map<Integer, String> readExcel2007(String strPath) {
		
		 Map<Integer, String> content = new HashMap<Integer, String>();
	        String str = "";
	        
	        //多个sheet总的读取行数。。。。
	        int totalTimes=0;

		XSSFWorkbook xwb;
		try {
			xwb = new XSSFWorkbook(new FileInputStream(strPath));
			//获得总的sheet数量
			int sheetTotalNumber=0;
			sheetTotalNumber=xwb.getNumberOfSheets();
	for(int n=0;n<sheetTotalNumber;n++){
				
			
			
			XSSFSheet sheet = xwb.getSheetAt(n);
			// 读取第一章表格内容
			// 定义 row、cell
			XSSFRow row;
			String cell;
			// 循环输出表格中的内容 从第四行开始读取
			for (int i = 4; i < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				for (int j = row.getFirstCellNum(); j < row
						.getPhysicalNumberOfCells(); j++) {
					
					
					
					String temp="";
					
					
					// 通过 row.getCell(j).toString() 获取单元格内容，
					
					
					
					
            		   if("到了末尾了".equals(temp=getCellFormatValue(row.getCell(j),j).trim())){
            			   
//            			   System.out.println("到了末尾了");	
            			   str="";
            			   break;
            		   }else{
            			   str += getCellFormatValue(row.getCell(j),j).trim()+"|";
            			   
            		   }
					
					
					
					
					
					
					
					
				}
	               if(str!=""){
	            	   totalTimes++;
	            	   
	            	   content.put(totalTimes, str);
	            	   str = "";
	               }
			}
	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return content;
	}
	
	
	private static String getCellFormatValue(XSSFCell cell,int j) {
        String cellvalue = "";
        if (cell != null) {
        	
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case XSSFCell.CELL_TYPE_NUMERIC:{
            	
            	DecimalFormat df= new DecimalFormat("0");  
                cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
            	break;
            }
            // 如果当前Cell的Type为STRIN
            case XSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue =cell.getStringCellValue();
                
                break;
            // 默认的Cell值
            default:
            	
            	if(j==2){
            		
            		cellvalue = "到了末尾了";
            	}else{
            		cellvalue = "此项为空";
            		
            	}
            }
        } else {
        	
        	if(j==2){
        		
        		cellvalue = "到了末尾了";
        	}else{
        		cellvalue = "此项为空";
        		
        	}
        }
        return cellvalue;

    }
	
	
}
