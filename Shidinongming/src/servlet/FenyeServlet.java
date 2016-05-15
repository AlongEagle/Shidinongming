package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBHelperMysql;


/*
 * 实现主页面的分页
 * 
 * 
 */
public class FenyeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FenyeServlet() {
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

		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		System.out.println("----------------------page------:"+page);
		System.out.println("----------------------rows------:"+rows);
		
		DBHelperMysql dbh=new DBHelperMysql();
		dbh.fenyeQuery(rows, page, response,request,out);
		


			/*s_name = new String(request.getParameter("s_name").getBytes(
					"iso-8859-1"), "UTF-8");*/

		
		
		System.out.println("-----------------分页成功------------------------");

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
