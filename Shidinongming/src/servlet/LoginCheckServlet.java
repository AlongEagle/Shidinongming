package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBHelperMysql;

public class LoginCheckServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginCheckServlet() {
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

		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		


		if(name!=null&&name!=""){
			
			name = new String(request.getParameter("name").getBytes("iso-8859-1"), "UTF-8");
		}
		if(password!=null&&password!=""){
			
			password = new String(request.getParameter("password").getBytes("iso-8859-1"), "UTF-8");
		}

		System.out.println("----------------------name------:"+name);
		System.out.println("----------------------password------:"+password);
		
		
		DBHelperMysql dbh=new DBHelperMysql();
		dbh.CheckUserAndPassword(name, password, response,request,out);

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
