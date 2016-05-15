package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBHelperMysql;

public class HesuanServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HesuanServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String hesuanshijian_year=request.getParameter("hesuanshijian_year");
		String hesuanshijian_month=request.getParameter("hesuanshijian_month");
		
		
		
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		System.out.println("----------------------page------:"+page);
		System.out.println("----------------------rows------:"+rows);
		
		DBHelperMysql dbh=new DBHelperMysql();
		dbh.Hesuan(rows, page, response,request,out,hesuanshijian_year,hesuanshijian_month);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.flush();
		out.close();
	}
	
	
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
