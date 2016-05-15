package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBHelperMysql;
import com.util.ExcelReader;
import com.util.NewMethod2007;

@Stateless
public class ImportExcel extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		String fileUrl=request.getParameter("fileUrl");
		System.out.println("文件的地址为--------------------------： "+fileUrl);
		
		
		//----------------------------------是否上传文件--------------------------------------------------------------------
    	
    	
    	if(fileUrl!=null){
    		

    		//获得上传的文件路径
    		
    		fileUrl=new String(request.getParameter("fileUrl").getBytes("iso-8859-1"),"UTF-8");
    		
    		System.out.println("------------->解码后:"+fileUrl);
    		
    		 
    		//判断导入的excel类型 xls 还是 xlsx
    		if(fileUrl.endsWith("xls")){ 
    			
    			System.out.println("---------------------上传了xls------------------------------");
    			 
    			ExcelReader er=new ExcelReader();
    			
    			
    			
    			
//    			er.getExcelData(fileUrl,out);
    			
    			
    			er.DaoruShujuku(fileUrl, out);
    			

    			
    			
    			
    			
    			
    			
    			
    			
    			
    			//    			response.getWriter().write("fileUploadSuccess");
    			System.out.println("--------------------------上传excel成功---------------");
    			
    			
    		 }else  if(fileUrl.endsWith("xlsx")){
	    			System.out.println("---------------------上传了xlsx------------------------------");

    			 
	    			//导出xlsx文件核心方法
	    			Map<Integer, String> map=NewMethod2007.readExcel2007(fileUrl);
	    			
	    			 DBHelperMysql dbh=new DBHelperMysql();
	    	           //插入到数据库 dbh.InsertData(map);
	    	            
	    	            dbh.InsertData(map);
	    			
    			 
	    			response.getWriter().write("fileUploadSuccess");
    			 System.out.println("--------------------------上传excel成功---------------");
    		 }else{
	    			System.out.println("---------------------选择的文件类型不正确------------------------------");
	    			response.getWriter().write("fileUploadFail");

    		 }
    		
    		
    		
    		
    		
    	}
		
		
	}
	
	

}
