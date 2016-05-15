package com.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("标题总列数colNum:" + colNum);
        String[] title = new String[colNum];
        
        //循环拿出标题
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        //单个导入第一个sheet
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        
        System.out.println("最后一行的行数是："+rowNum);
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + "|";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }
    
    
    
    /**
     * 
     * 批量导入sheet
     * @param is
     * @return
     */
    public Map<Integer, String> batReadExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        int sheetSize=wb.getNumberOfSheets();
        
        //多个sheet总的读取行数。。。。
        int totalTimes=0;
        
        for(int k=0;k<sheetSize;k++){
        	sheet = wb.getSheetAt(k);
        	
        	 int rowNum = sheet.getLastRowNum();
             
//           System.out.println("最后一行的行数是："+rowNum);
           row = sheet.getRow(0);
           int colNum = row.getPhysicalNumberOfCells();
           
           
          
           
           // 正文内容应该从第二行开始,第一行为表头的标题
           for (int i = 1; i <= rowNum; i++) {
               row = sheet.getRow(i);
               int j = 0;
               while (j < colNum) {
                   // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                   // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                   // str += getStringCellValue(row.getCell((short) j)).trim() +
                   // "-";
                   str += getCellFormatValue(row.getCell((short) j)).trim() + "|";
                   j++;
               }
               totalTimes++;
               content.put(totalTimes, str);
               str = "";
           }
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
        
        
        
        //单个导入第一个sheet
//        sheet = wb.getSheetAt(0);
        // 得到总行数
       
        return content;
    }

    
    
    
    /**
     * 
     * 批量导入sheet
     * @param is
     * @return
     */
    public Map<Integer, String> batReadExcelContentFromFourRow(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        int sheetSize=wb.getNumberOfSheets();
        
        //多个sheet总的读取行数。。。。
        int totalTimes=0;
        
        //从第二个sheet开始读
        for(int k=1;k<sheetSize;k++){
        	sheet = wb.getSheetAt(k);
        	
        	 int rowNum = sheet.getLastRowNum();
             
//           System.out.println("最后一行的行数是："+rowNum);
           
//        	 以第四行的横向单元格数目为准 确定每行有几个属性
        	 
        	 row = sheet.getRow(3);
           int colNum = row.getPhysicalNumberOfCells();
           
           
          
           
           // 正文内容应该从第四行开始
           for (int i = 3; i <= rowNum; i++) {
               row = sheet.getRow(i);
               int j = 0;
               while (j < colNum) {
                   // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                   // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                   // str += getStringCellValue(row.getCell((short) j)).trim() +
                   // "-";
//                   str += getCellFormatValue(row.getCell((short) j)).trim() + "|";
            	 
            	   String temp="";
            		   if("到了末尾了".equals(temp=getCellFormatValueForString(row.getCell((short) j),j).trim())){
            			   
//            			   System.out.println("到了末尾了");	
            			   str="";
            			   break;
            		   }else{
            			   str += getCellFormatValueForString(row.getCell((short) j),j).trim()+"|";
            			   
            		   }
            		   
            		   
                   j++;
               }
               if(str!=""){
            	   totalTimes++;
            	   
            	   content.put(totalTimes, str);
            	   str = "";
               }
           }
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
        
        
        
        //单个导入第一个sheet
//        sheet = wb.getSheetAt(0);
        // 得到总行数
       
        return content;
    }
    
    
    
    

    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue =cell.getStringCellValue();
                
                break;
            // 默认的Cell值
            default:
                cellvalue = "此项为空";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    
    
    private String getCellFormatValueForString(HSSFCell cell,int j) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            	
            	
//            	  DecimalFormat df= new DecimalFormat("0.00");  
//                  cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
            	cellvalue = String.valueOf(cell.getNumericCellValue());
            	
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                	
                	
                    // 取得当前Cell的数值
                    
                   /* DecimalFormat dfd = new DecimalFormat("0.00");  
                    cellvalue = String.valueOf(dfd.format(cell.getNumericCellValue()));*/
                	cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue =cell.getStringCellValue();
                
                break;
            // 默认的Cell值
            default:
            	if(j==0){
            		cellvalue="到了末尾了";
            	}else{
            		
            		cellvalue = "此项为空";
            	}
            }
        } else {
        	if(j==0){
        		cellvalue="到了末尾了";
        	}else{
        		
        		cellvalue = "此项为空";
        	}
        }
        return cellvalue;

    }
    
    /**
     * 
     * 
     * 如果是xls文件则用该方法导出
     * @param fileUrl
     * @param out
     */
    public void getExcelData(String fileUrl,PrintWriter out){
    	 try {
             // 对读取Excel表格标题测试
             InputStream is = new FileInputStream(fileUrl);
             ExcelReader excelReader = new ExcelReader();
             String[] title = excelReader.readExcelTitle(is);
             System.out.println("获得Excel表格的标题:");
             for (String s : title) {
                 System.out.print(s + " ");
             }

             // 对读取Excel表格内容测试
             InputStream is2 = new FileInputStream(fileUrl);
             
             
             //读取单独一个sheet的xls文件
//             Map<Integer, String> map = excelReader.readExcelContent(is2);
             
           //批量读取sheet的xls文件
             Map<Integer, String> map = excelReader.batReadExcelContent(is2);
             
             System.out.println("获得Excel表格的内容:");
             for (int i = 1; i <= map.size(); i++) {
                 System.out.println(map.get(i));
             }
             
             
             //获得内容之后导入到数据库
             DBHelperMysql dbh=new DBHelperMysql();
            //插入到数据库 dbh.InsertData(map);
             
             
             
             out.write("fileUploadSuccess");
          

         } catch (FileNotFoundException e) {
             System.out.println("未找到指定路径的文件!");
             e.printStackTrace();
         }
     }
    
    
    
    
    
    
    public void DaoruShujuku(String fileUrl,PrintWriter out){
   	 try {
            ExcelReader excelReader = new ExcelReader();

            // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream(fileUrl);
            
          //批量读取sheet的xls文件(带标题的)
//            Map<Integer, String> map = excelReader.batReadExcelContent(is2);
            
            
            //批量读取sheet的xls文件(从第四行开始读) 
            Map<Integer, String> map = excelReader.batReadExcelContentFromFourRow(is2);
            
            System.out.println("获得Excel表格的内容:");
            for (int i =1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }
            
            
            //获得内容之后导入到数据库
            DBHelperMysql dbh=new DBHelperMysql();
           //插入到数据库 dbh.InsertData(map);
            
            dbh.InsertData(map);
            
            
            
            out.write("fileUploadSuccess");
         

        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    	
    	

    public static void main(String[] args) {
        try {
            // 对读取Excel表格标题测试
            InputStream is = new FileInputStream("e:\\1.xls");
            ExcelReader excelReader = new ExcelReader();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }

            // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream("e:\\1.xls");
            Map<Integer, String> map = excelReader.readExcelContent(is2);
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }
}