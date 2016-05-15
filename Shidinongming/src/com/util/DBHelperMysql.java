package com.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.po.Chayue;
import com.po.Daiyu;
import com.po.Dyhdl;
import com.po.Sdnm;

public class DBHelperMysql {
	public static final String url = "jdbc:mysql://127.0.0.1:3306/shidinongming?characterEncoding=utf-8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "PK8912445";

	public static String printSql="";
	
	public Connection conn = null;
	public PreparedStatement pst = null;

	public Connection getConnection() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//
//			System.out.println(conn);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return conn;
	}

	public static void main(String[] args) {
		DBHelperMysql dbh = new DBHelperMysql();
//		dbh.queryTuBiaoTongji();
		dbh.queryShidiDaoling();

	}
	
	
	public String test(){
		return printSql;
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 
	 * 批量导入数据
	 * 
	 * @param dyhdl
	 */
	public void InsertData(Map<Integer, String> map) {

		Connection conn = null;
		DBHelperMysql dbh = new DBHelperMysql();
		PreparedStatement psts = null;

		String sql = "INSERT INTO tb_shidinongming (yuanid,zyqmj,bczymj,zdgsynmj,zysj,name,sex,relation,idnum,nonghucode,hktype,birthday"
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		
		
		
		
		int count = 0;
		try {

			conn = dbh.getConnection();

			conn.setAutoCommit(false); // 设置手动提交
			psts = conn.prepareStatement(sql);

			int j=0;
			for (int i = 1; i <= map.size(); i++) {

					String[] infos = map.get(i).split("\\|");
					// String[] infos = map.get(i).split("|");
					System.out.println("array"+Arrays.toString(infos));

					psts.setString(1, infos[0]);
					psts.setString(2, infos[1]);
					psts.setString(3, infos[2]);
					psts.setString(4, infos[3]);
					psts.setString(5, infos[4]);
					psts.setString(6, infos[5]);
					psts.setString(7, infos[6]);
					psts.setString(8, infos[7]);
					psts.setString(9, infos[8]);
					psts.setString(10, infos[9]);
					psts.setString(11, infos[10]);
					
					
					if((infos[8]!=null||infos[8]!="")&&infos[8].length()==18){
						
//						java.util.Date date=new SimpleDateFormat("yyyyMM").parse(infos[8].substring(6, 12));
						
						psts.setString(12, infos[8].substring(6, 12));
					}else{
						psts.setString(12,"");
//						psts.setDate(12, null);
					}
					
					
					psts.addBatch(); // 加入批量处理
					count++;
				}
				psts.executeBatch(); // 执行批量处理
			conn.commit(); // 提交
			j++;
//			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			if (psts != null) {
				psts.close();
			}

			if (conn != null) {

				conn.close();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out
					.println("------------------数据插入失败-------------------------------");
			e1.printStackTrace();
		} // 关闭数据库连接

		System.out
				.println("-------------------------数据插入成功------共-------------------"
						+ count + "条");

	}

	/**
	 * 
	 * 
	 * 导出数据到EXCEL
	 * 
	 * @param isPrint
	 * @param request
	 * @param response
	 * @param out
	 * @param saveUrl
	 * @param saveName
	 * @return
	 */
	public String exportExcel(String isPrint, HttpServletRequest request,
			HttpServletResponse response, PrintWriter out, String saveUrl,
			String saveName) {

		ResultSet rs = null;
		Connection conn = null;
		String results = "";

		String printResult = "false";

//		String sql = "select * from tb_danganshuju"; // 查询数据的sql语句
		
		String sql=printSql;
		System.out.println("--------------printSql----------"+printSql);
		
		List<Daiyu> list = new ArrayList<Daiyu>();
		DBHelperMysql dbh = new DBHelperMysql();
		try {
			conn = dbh.getConnection();

			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// ----------------导出excel核心代码------------------------------------------------
			printResult = CreateXL.resultSetToExcel(rs, saveUrl + saveName
					+ ".xls", "sheet1");

			// 返回给页面 提醒导出成功！！！！！
			response.getWriter().write("success");
			System.out
					.println("------------------------------------导出成功率--------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (rs != null) {

				rs.close();
			}

			if (conn != null) {

				conn.close();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // 关闭数据库连接
		return "success";

	}

	/**
	 * 
	 * 模糊查询
	 * 
	 * @param s_name
	 */
	public void queryInfo(String s_name) {

		String sql = "select * from tb_danganshuju"; // 查询数据的sql语句
		ResultSet rs = null;

		if (s_name != null) {
			sql += " where detail like '%" + s_name + "%'";
		}

		System.out.println(sql);

		List<Dyhdl> list = new ArrayList<Dyhdl>();
		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = dbh.getConnection();

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			System.out.println("最后的查rs询结果为：" + rs.getRow());

			while (rs.next()) {

				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);


			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// request.setAttribute("printResult", printResult);
		//
		//
		// results=JSONArray.fromObject(list).toString();
		//

	}

	public int totalData() {
		
		// 总记录数
		int total = 0;

		String sql = "select count(*) from tb_shidinongming ";

		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = null;
		conn = dbh.getConnection();

		ResultSet rs = null;

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			if (rs.next()) {

				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return total;

	}
	
	/**
	 * 
	 * 根据前台传递过来的  需要查询的档案类型和字段进行查询记录数
	 * @param ziduan
	 * @param keys
	 * @return
	 */
	public int countLines(String ziduan,String keys) {
		// 总记录数
		int total = 0;

		String sql = "select count(*) from tb_danganshuju where " +ziduan+" like '%"+keys+"%'";
		
		
		System.out.println("---------------ziduan:"+ziduan+"    keys:"+keys);
		System.out.println("---------------sql------------:"+sql);

		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = null;
		conn = dbh.getConnection();

		ResultSet rs = null;

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			if (rs.next()) {

				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return total;

	}
	
	
	
	
	
	
	
	
	public int countLinesForRightSearch(String nianfen,String xiangzhen,String shifouquecailiao,String danghao2) {
		// 总记录数
		int total = 0;
		String sql1 = "select count(*) from tb_danganshuju where 1=1 ";
		StringBuffer sql=new StringBuffer(sql1);
		
		if(nianfen!=null&&!nianfen.equals("")){
			sql.append("and niandu='"+nianfen+"'");
		}
		
		if(xiangzhen!=null&&!xiangzhen.equals("")){
			sql.append(" and townid='"+xiangzhen+"'");
		}
		
		if(shifouquecailiao!=null&&!shifouquecailiao.equals("")){
			sql.append(" and ps='"+shifouquecailiao+"'");
		}
		
		if(danghao2!=null&&!danghao2.equals("")){
			sql.append(" and danghao='"+danghao2+"'");
		}
		

		
		
		
		System.out.println("---------------sql------------:"+sql.toString());

		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = null;
		conn = dbh.getConnection();

		ResultSet rs = null;

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql.toString()); // 执行sql查询语句，返回查询数据的结果集

			if (rs.next()) {

				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return total;

	}
	
	
	
	
	
	
	
	
	
	

	// ******************按照乡镇编号来查找***************************************

	public void queryByTownid(String townid2) {

		String sql = "select * from tb_danganshuju"; // 查询数据的sql语句
		ResultSet rs = null;

		if (townid2 != null) {
			sql += " where townid =" + townid2;
		}

		System.out.println(sql);

		List<Dyhdl> list = new ArrayList<Dyhdl>();
		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = dbh.getConnection();

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			int count = 0;
			while (rs.next()) {
				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

			}

			System.out
					.println("------------------conout--------------" + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// request.setAttribute("printResult", printResult);
		//
		//
		// results=JSONArray.fromObject(list).toString();
		//

	}

	// ******************按照年度分类来查找***************************************

	public void queryByNiandu(String niandu2) {

		String sql = "select * from tb_danganshuju"; // 查询数据的sql语句
		ResultSet rs = null;

		if (niandu2 != null) {
			sql += " where niandu =" + niandu2;
		}

		System.out.println(sql);

		List<Dyhdl> list = new ArrayList<Dyhdl>();
		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = dbh.getConnection();

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			int count = 0;
			while (rs.next()) {
				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);


			}

			System.out
					.println("------------------conout--------------" + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// request.setAttribute("printResult", printResult);
		//
		//
		// results=JSONArray.fromObject(list).toString();
		//

	}

	// ******************按照档案类型分类来查找***************************************

	public void queryByDanganTpye(String dangantype2) {

		String sql = "select * from tb_danganshuju"; // 查询数据的sql语句
		ResultSet rs = null;

		if (dangantype2 != null) {
			sql += " where dangantype =" + dangantype2;
		}

		System.out.println(sql);

		List<Dyhdl> list = new ArrayList<Dyhdl>();
		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = dbh.getConnection();

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			int count = 0;
			while (rs.next()) {
				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);


			}

			System.out
					.println("------------------conout--------------" + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// request.setAttribute("printResult", printResult);
		//
		//
		// results=JSONArray.fromObject(list).toString();
		//

	}

	// ******************按照档案号查找***************************************

	public void queryByDangHao(String danganhao) {

		String sql = "select * from tb_danganshuju"; // 查询数据的sql语句
		ResultSet rs = null;

		if (danganhao != null) {
			sql += " where danghao =" + danganhao;
		}

		System.out.println(sql);

		List<Dyhdl> list = new ArrayList<Dyhdl>();
		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = dbh.getConnection();

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			int count = 0;
			while (rs.next()) {
				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);



			}

			System.out
					.println("------------------conout--------------" + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// request.setAttribute("printResult", printResult);
		//
		//
		// results=JSONArray.fromObject(list).toString();
		//

	}
	
	
	// ******************按照档案号查找来打印案卷封面***************************************

		public void queryByDangHaoToPrintAnjuanfengmian(String danganhao, 
				HttpServletResponse response, HttpServletRequest request,
				PrintWriter out) {
			
			
			Dyhdl dyhdl=null ;

//			String sql = "select max(id) from tb_danganshuju"; // 查询数据的sql语句
			String sql = "select * from tb_danganshuju where id=(select max(id) from tb_danganshuju where danghao ='" + danganhao+"') and danghao='"+danganhao+"'"; // 查询数据的sql语句


			ResultSet rs = null;
			
			String result="";


			System.out.println(sql);

			DBHelperMysql dbh = new DBHelperMysql();
			Connection conn = dbh.getConnection();

			try {
				Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

				rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

				int count = 0;
				while (rs.next()) {
					count++;
					String id = rs.getString(1);
					String dept = rs.getString(2);
					String detail = rs.getString(3);
					String zhenglidate = rs.getString(4);
					String code = rs.getString(5);
					String ps = rs.getString(6);
					String dangantype = rs.getString(7);
					
					if("dy1".equals(dangantype)){
						dangantype="待遇核定类";
								
					}
					String townid = rs.getString(8);
					String niandu = rs.getString(9);
					String danghao = rs.getString(10);
					String operator = rs.getString(11);
					String beiyong1 = rs.getString(12);
					String beiyong2 = rs.getString(13);
					String beiyong3 = rs.getString(14);
					String beiyong4 = rs.getString(15);
					String beiyong5 = rs.getString(16);
					int onlyid = rs.getInt(17);
					dyhdl= new Dyhdl(id, dept, detail, zhenglidate, code, ps,
							dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);

					System.out.println(id + "|" + dept + "|" + detail + "|"
							+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);



				}
				
				
				result = JSONObject.fromObject(dyhdl).toString();// 格式化result
				// 一定要是JSONObject

System.out.println("----------------分页返回的总字符串：" + result);

out.print(result);

				System.out
						.println("------------------conout--------------" + count);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}


		}
	
	
	
	
	
	
	
	
	

	/**
	 * 
	 * 分页查询
	 * 
	 * @param rows
	 * @param page
	 */
		public void fenyeQuery(String rows, String page,
				HttpServletResponse response, HttpServletRequest request,
				PrintWriter out) {

			Connection conn = null;
			ResultSet rs = null;

			DBHelperMysql dbh = new DBHelperMysql();
			conn = dbh.getConnection();

			int total = dbh.totalData();

			// 返回的json字符串
			String result = "";

			String sql = "select * from tb_shidinongming"; // 查询数据的sql语句

			System.out.println("---------------");
			// 当前页
			int intPage = Integer.parseInt((page == null || page == "0") ? "1"
					: page);
			// 每页显示条数
			int number = Integer.parseInt((rows == null || rows == "0") ? "10"
					: rows);
			// 每页的开始记录 第一页为1 第二页为number +1
			int start = (intPage - 1) * number;

			sql += " limit " + start + "," + number;
			
			printSql=sql;

			System.out.println("++++++++++++分页查询执行的sql++++++++:" + sql);
			try {
				Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

				rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

				// -----------------------------------------------------------------
				// response.getWriter().write("success");

				System.out.println("最后的查rs询结果为：" + rs.getRow());

				List<Sdnm> list = new ArrayList<Sdnm>();

				System.out
						.println("分页查询结果--------------------------------------------------------------------");
				
				int time1=(int) System.currentTimeMillis();
				while (rs.next()) {
					
					

					int id = rs.getInt(1);
					String yuanid = rs.getString(2);
					String zyqmj = rs.getString(3);
					String bczymj = rs.getString(4);
					String zdgsynmj = rs.getString(5);
					String zysj = rs.getString(6);
					String name = rs.getString(7);
					String sex = rs.getString(8);
					String relation = rs.getString(9);
					String idnum = rs.getString(10);
					String hktype = rs.getString(11);
					String nonghucode = rs.getString(12);
					String birthday=rs.getString(13);
					String qian=rs.getString(18);
					Sdnm sdnm=new Sdnm(id, yuanid, zyqmj, bczymj, zdgsynmj, zysj, name, sex, relation, idnum, hktype, nonghucode,birthday,qian);
					list.add(sdnm);
//					System.out.println(id + "|" + dept + "|" + detail + "|"
//							+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

				}
				
				int time2=(int) System.currentTimeMillis();
				
				System.out.println("花费的总的时间是----"+(time2-time1));
				System.out
						.println("分页查询结果--------------------------------------------------------------------");

				Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
				jsonMap.put("total", total);// total键 存放总记录数，必须的
				jsonMap.put("rows", list);// rows键 存放每页记录 list
				result = JSONObject.fromObject(jsonMap).toString();// 格式化result
																	// 一定要是JSONObject

				System.out.println("----------------分页返回的总字符串：" + result);

				out.print(result);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		}
	
	
	/**
	 * 
	 * 左侧分类异步加载刷新
	 * 
	 * @param rows
	 * @param page
	 */
	public void choiceDanganType(String rows, String page,String ziduan,String keys,
			HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {

		Connection conn = null;
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		conn = dbh.getConnection();

		
		//所查的记录总数
		int total = dbh.countLines(ziduan, keys);

		// 返回的json字符串
		String result = "";

		String sql = "select * from tb_danganshuju where "+ziduan+" like '%"+keys+"%'"; // 查询数据的sql语句

		System.out.println("---------------");
		
		printSql=sql;
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		
		System.out.println("[[[[[[[[[[[[[[[[[[["+rows+"]]]]]]]]]]]]]]]]]]]");
		int number = Integer.parseInt((rows == null || rows == "0") ? "30"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;

		sql += " limit " + start + "," + number;

		System.out.println("++++++++++++按类型查询结果sql++++++++:" + sql);
		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集


			List<Dyhdl> list = new ArrayList<Dyhdl>();

			System.out
					.println("按类型查询结果--------------------------------------------------------------------");
			while (rs.next()) {


				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

			}
			System.out
					.println("按类型查询结果--------------------------------------------------------------------");

			Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
			jsonMap.put("total", total);// total键 存放总记录数，必须的
			jsonMap.put("rows", list);// rows键 存放每页记录 list
			result = JSONObject.fromObject(jsonMap).toString();// 格式化result
																// 一定要是JSONObject

			System.out.println("----------------按类型查询结果：" + result);

			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	
	
	
	/**
	 * 
	 * 左侧分类异步加载刷新
	 * 
	 * @param rows
	 * @param page
	 */
	public void SearchByXiangzhenAndNianfen(String rows, String page,String xiangzhen,String nianfen,String shifouquecailiao,String danghao2
			,HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {

		Connection conn = null;
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		conn = dbh.getConnection();

		
		//所查的记录总数
		int total = dbh.countLinesForRightSearch(nianfen, xiangzhen, shifouquecailiao,danghao2);

		// 返回的json字符串
		String result = "";

		String sql1 = "select * from tb_danganshuju where 1=1 ";
		StringBuffer sql=new StringBuffer(sql1);
		
		System.out.println("xiangzhen:"+xiangzhen+"|");
		System.out.println("shifouquecailiao:"+shifouquecailiao+"|");
		System.out.println("nianfen:"+nianfen+"|");
		
		
		if(nianfen!=null&&!nianfen.equals("")){
			sql.append("and niandu='"+nianfen+"'");
		}
		if(xiangzhen!=null&&!xiangzhen.equals("")){
			sql.append(" and townid='"+xiangzhen+"'");
		}
		
		if(shifouquecailiao!=null&&!shifouquecailiao.equals("")){
			sql.append(" and ps='"+shifouquecailiao+"'");
		}
		
		if(danghao2!=null&&!danghao2.equals("")){
			sql.append(" and danghao='"+danghao2+"'");
			
		}
		
		

		System.out.println("---------------");
		
		printSql=sql.toString();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		
		int number = Integer.parseInt((rows == null || rows == "0") ? "30"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;

		sql.append(" limit " + start + "," + number);

		System.out.println("++++++++++++按鄉鎮查詢結果sql++++++++:" + sql.toString());
		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql.toString()); // 执行sql查询语句，返回查询数据的结果集


			List<Dyhdl> list = new ArrayList<Dyhdl>();

			System.out
					.println("按类型查询结果--------------------------------------------------------------------");
			while (rs.next()) {


				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

			}
			System.out
					.println("按类型查询结果--------------------------------------------------------------------");

			Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
			jsonMap.put("total", total);// total键 存放总记录数，必须的
			jsonMap.put("rows", list);// rows键 存放每页记录 list
			result = JSONObject.fromObject(jsonMap).toString();// 格式化result
																// 一定要是JSONObject

			System.out.println("----------------按类型查询结果：" + result);

			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	
	
	
	
	
	public void XiugaiChaxun(String onlyid1,HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {

		Connection conn = null;
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		conn = dbh.getConnection();

		

		// 返回的json字符串
		String result = "";

		String sql = "select * from tb_danganshuju where onlyid= "+onlyid1;
		

		


		System.out.println("++++++++++++按onlyid  sql++++++++:" + sql);
		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集


			List<Dyhdl> list = new ArrayList<Dyhdl>();

			System.out
					.println("按类型查询结果--------------------------------------------------------------------");
			while (rs.next()) {


				String id = rs.getString(1);
				String dept = rs.getString(2);
				String detail = rs.getString(3);
				String zhenglidate = rs.getString(4);
				String code = rs.getString(5);
				String ps = rs.getString(6);
				String dangantype = rs.getString(7);
				
				if("dy1".equals(dangantype)){
					dangantype="待遇核定类";
							
				}
				String townid = rs.getString(8);
				String niandu = rs.getString(9);
				String danghao = rs.getString(10);
				String operator = rs.getString(11);
				String beiyong1 = rs.getString(12);
				String beiyong2 = rs.getString(13);
				String beiyong3 = rs.getString(14);
				String beiyong4 = rs.getString(15);
				String beiyong5 = rs.getString(16);
				int onlyid = rs.getInt(17);
				Dyhdl dyhdl = new Dyhdl(id, dept, detail, zhenglidate, code, ps,
						dangantype,townid,niandu,danghao,operator,beiyong1,beiyong2,beiyong3,beiyong4,beiyong5,onlyid);
				list.add(dyhdl);

				System.out.println(id + "|" + dept + "|" + detail + "|"
						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

			}
			System.out
					.println("按类型查询结果--------------------------------------------------------------------");

			Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
			jsonMap.put("rows", list);// rows键 存放每页记录 list
			result = JSONObject.fromObject(jsonMap).toString();
																// 一定要是JSONObject

			System.out.println("----------------按类型查询结果：" + result);

			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
		
	
	
	  /* 更新符合要求的记录，并返回更新的记录数目*/    
    public static void update(String detail1,String code1,String danghao1,
    		String onlyid,HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {    
        Connection conn = null;
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		conn = dbh.getConnection();

        try {    
            String sql = "update tb_danganshuju set detail='"+detail1+"',code='"+code1+"',danghao='"+danghao1+"' where onlyid ='"+onlyid+"'";// 更新数据的sql语句     
                
            System.out.println("更新数据库的sql---------------------->"+sql);
            Statement st = (Statement) conn.createStatement();   //创建用于执行静态sql语句的Statement对象，st属局部变量     
                
            int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数     
                
            System.out.println("tb_danganshuju表中更新 " + count + " 条数据");      //输出更新操作的处理结果     
                
            out.print("success");  
        } catch (SQLException e) {   
        	e.printStackTrace();
            System.out.println("更新数据失败");    
        }  finally{
        	
        	if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
        	
        }
    }

	public void deleteInfo(String onlyid1,PrintWriter out ) {
		 Connection conn = null;
			ResultSet rs = null;

			DBHelperMysql dbh = new DBHelperMysql();
			conn = dbh.getConnection();

	        try {    
	            String sql = "delete from tb_danganshuju where onlyid='"+onlyid1+"'";// 更新数据的sql语句     
	                
	            System.out.println("删除数据库的sql---------------------->"+sql);
	            Statement st = (Statement) conn.createStatement();   //创建用于执行静态sql语句的Statement对象，st属局部变量     
	                
	            int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数     
	                
	            System.out.println("tb_danganshuju表中删除 " + count + " 条数据");      //输出更新操作的处理结果     
	                
	            out.print("success");  
	        } catch (SQLException e) {   
	        	e.printStackTrace();
	            System.out.println("删除数据失败");    
	        }  finally{
	        	
	        	if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
	        	
	        }
	    }
	
	public String queryTuBiaoTongji() {
		
		String beiyongSql="SELECT townid, count(1) AS counts FROM tb_danganshuju GROUP BY townid";

		String sqlYuanshi = "select count(id) from tb_danganshuju where townid="; // 查询数据的sql语句
		String sql = "select count(id) from tb_danganshuju where townid="; // 查询数据的sql语句
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		int total=0;
		
		String results="";

		try {
		for(int i=1;i<=15;i++){
			Connection conn = dbh.getConnection();
			if(i==1){
				
				sql=sql+" '尧渡镇'";
			}
			if(i==2){
				
				sql=sql+" '东流镇'";
			}
			if(i==3){
				
				sql=sql+" '香隅镇'";
			}
			if(i==4){
				
				sql=sql+" '胜利镇'";
			}
			if(i==5){
				
				sql=sql+" '张溪镇'";
			}
			if(i==6){
				
				sql=sql+" '龙泉镇'";
			}
			if(i==7){
				
				sql=sql+" '木塔乡'";
			}
			if(i==8){
				
				sql=sql+" '大渡口镇'";
			}
			if(i==9){
				
				sql=sql+" '泥溪镇'";
			}
			if(i==10){
				
				sql=sql+" '昭潭镇'";
			}
			if(i==11){
				
				sql=sql+" '洋湖镇'";
			}
			if(i==12){
				
				sql=sql+" '葛公镇'";
			}
			if(i==13){
				
				sql=sql+" '青山乡'";
			}
			if(i==14){
				
				sql=sql+" '官港镇'";
			}
			if(i==15){
				
				sql=sql+" '花园乡'";
			}
			
			
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

				
				
				
				rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
				
				sql=sqlYuanshi;
				if (rs.next()) {
					
					total = rs.getInt(1);
					if(i!=15){
						
						
						results+=String.valueOf(total)+",";
					}else{
						results+=String.valueOf(total);
					}
				}
				
				st.close();
				rs.close();
				conn.close();
				
				
			}
		System.out.println(results);
			
			

		



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
			return results;

	}		
		
	public void CheckUserAndPassword(String name, String password,
			HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {

		Connection conn = null;
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		conn = dbh.getConnection();

		int total = dbh.totalData();

		// 返回的json字符串
		String result = "";

		String sql = "select password from user where name= '"+name+"' and password='"+password+"'"; // 查询数据的sql语句

		System.out.println(sql);
		

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			boolean flag=false;
			
			while (rs.next()) {

				
				flag=true;

			}
			
			if(flag){
				
				result="success";
				
//				
//				HttpSession session = request.getSession(); 
//				
//				String sessionId=session.getId();
//				session.setAttribute("name", name);
//				String path=request.getContextPath();
//				
//				request.getRequestDispatcher("/LayoutDemo.jsp").forward(request, response);
//				
//				System.out.println("session:"+session.getId());
				
			}else{
				result="fail";
			}
			


			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	
	
	
	public void ChaYueQuery(
			HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {

		Connection conn = null;
		ResultSet rs = null;

		DBHelperMysql dbh = new DBHelperMysql();
		conn = dbh.getConnection();


		// 返回的json字符串
		String result = "";

		String sql = "select * from chayue"; // 查询数据的sql语句

		System.out.println("---------------");
		// 当前页

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			System.out.println("最后的查rs询结果为：" + rs.getRow());

			List<Chayue> list = new ArrayList<Chayue>();

			System.out
					.println("分页查询结果--------------------------------------------------------------------");
			
			while (rs.next()) {

				int id = rs.getInt(1);
				String seeDate = rs.getString(2);
				String seeUser = rs.getString(3);
				String seeContent = rs.getString(4);
				String ps = rs.getString(5);
				System.out.println("id:;;;kkkkk;"+id);
				
				Chayue chayue=new Chayue(id,seeDate, seeUser, seeContent, ps);
				list.add(chayue);

//				System.out.println(id + "|" + dept + "|" + detail + "|"
//						+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

			}
			
			
			System.out
					.println("档案查阅结果--------------------------------------------------------------------");

			Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
			jsonMap.put("rows", list);// rows键 存放每页记录 list
			result = JSONObject.fromObject(jsonMap).toString();// 格式化result
																// 一定要是JSONObject

			System.out.println("----------------分页返回的总字符串：" + result);

			out.print(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	
	
	public void InsertChayueXinxi(String chayueshijian, String chayuezhe, String chayueneirong, String beizhu,HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {
		
		

		Connection conn = null;
		DBHelperMysql dbh = new DBHelperMysql();
		PreparedStatement psts = null;

		String sql = "INSERT INTO chayue (seeDate,seeUser,seeContent,ps) " +
				"VALUES (?,?,?,?)";

		int count = 0;
		try {

			conn = dbh.getConnection();

			conn.setAutoCommit(false); // 设置手动提交
			psts = conn.prepareStatement(sql);



					psts.setString(1, chayueshijian);
					psts.setString(2, chayuezhe);
					psts.setString(3, chayueneirong);
					psts.setString(4, beizhu);
					
					psts.addBatch(); // 加入批量处理
				psts.executeBatch(); // 执行批量处理
			conn.commit(); // 提交
			out.print("success"); 
//			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			if (psts != null) {
				psts.close();
			}

			if (conn != null) {

				conn.close();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out
					.println("------------------数据插入失败-------------------------------");
			e1.printStackTrace();
		} // 关闭数据库连接

		System.out
				.println("-------------------------数据插入成功------共-------------------");

	}	
	
	
	
/**
 * 核定待遇算法	
 */
	public void queryShidiDaoling() {

		String sql = "select id,nonghucode,idnum,SUBSTR(idnum,7,6) as birthday from tb_shidinongming "; // 查询数据的sql语句
		ResultSet rs = null;

		/*if (s_name != null) {
			sql += " where detail like '%" + s_name + "%'";
		}*/

		System.out.println(sql);

		List<Sdnm> list = new ArrayList<Sdnm>();
		DBHelperMysql dbh = new DBHelperMysql();
		Connection conn = dbh.getConnection();

		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

			// -----------------------------------------------------------------
			// response.getWriter().write("success");

			System.out.println("最后的查rs询结果为：" + rs.getRow());

			  getDate();  
			
			while (rs.next()) {

				String id = rs.getString(1);
				String nonghucode = rs.getString(2);
				String idnum = rs.getString(3);
				String birthday = rs.getString(4);
				System.out.println(id+"|"+nonghucode+"|"+idnum+"|"+birthday);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		// request.setAttribute("printResult", printResult);
		//
		//
		// results=JSONArray.fromObject(list).toString();
		//

	}

public void getDate() throws ParseException {
	Calendar now = Calendar.getInstance();  
//	    System.out.println("年: " + now.get(Calendar.YEAR));  
//	    System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
//	    System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
//	    System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
//	    System.out.println("分: " + now.get(Calendar.MINUTE));  
//	    System.out.println("秒: " + now.get(Calendar.SECOND));  
//	    System.out.println("当前时间毫秒数：" + now.getTimeInMillis());  
//	    System.out.println(now.getTime());  
//  
	    Date d = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
	    String dateNowStr = sdf.format(d);  
	    System.out.println("格式化后的日期：" + dateNowStr);  
	      
	    String str = "20160612";  //要跟上面sdf定义的格式一样  
	    Date today = sdf.parse(str);  
	    System.out.println("字符串转成日期：" + today);
}


//核算方法
	
public void Hesuan(String rows, String page,
		HttpServletResponse response, HttpServletRequest request,
		PrintWriter out,String hesuanshijian_year,String hesuanshijian_month) {

	Connection conn = null;
	ResultSet rs = null;

	DBHelperMysql dbh = new DBHelperMysql();
	conn = dbh.getConnection();

//	int total = dbh.totalData();

	// 返回的json字符串
	String result = "";

	String sql = "select * from tb_shidinongming"; // 查询数据的sql语句

	System.out.println("---------------");
	// 当前页
//	int intPage = Integer.parseInt((page == null || page == "0") ? "1"
//			: page);
//	// 每页显示条数
//	int number = Integer.parseInt((rows == null || rows == "0") ? "10"
//			: rows);
//	// 每页的开始记录 第一页为1 第二页为number +1
//	int start = (intPage - 1) * number;

//	sql += " limit " + start + "," + number;
	
//	printSql=sql;

	System.out.println("++++++++++++分页查询执行的sql++++++++:" + sql);
	try {
		Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

		rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

		// -----------------------------------------------------------------
		// response.getWriter().write("success");

		System.out.println("最后的查rs询结果为：" + rs.getRow());

		List<Sdnm> list = new ArrayList<Sdnm>();

		System.out
				.println("分页查询结果--------------------------------------------------------------------");
		
		int time1=(int) System.currentTimeMillis();
		
		
		//一个list记录 满足发钱的人员的id
		
//		ArrayList<Integer> faqianId=new ArrayList<Integer>();
		
		//一个hashmap记录 满足发钱的人员的id，发放的月份数
		
		HashMap<Integer ,Integer> faqianid=new HashMap<Integer,Integer>();
		
		

		
		
		while (rs.next()) {
			
			

			int id = rs.getInt(1);
			String yuanid = rs.getString(2);
			String zyqmj = rs.getString(3);
			String bczymj = rs.getString(4);
			String zdgsynmj = rs.getString(5);
			String zysj = rs.getString(6);
			String name = rs.getString(7);
			String sex = rs.getString(8);
			String relation = rs.getString(9);
			String idnum = rs.getString(10);
			String hktype = rs.getString(11);
			String nonghucode = rs.getString(12);
			String birthday=rs.getString(13);
			String qian=rs.getString(18);
			if(birthday!=null&&!("".equals(birthday))){			
			
			
			int birthday_year=Integer.parseInt(birthday.substring(0, 4));
			int birthday_month=Integer.parseInt(birthday.substring(4, 6));
			
			int total_year=Integer.parseInt(hesuanshijian_year)-birthday_year;
			int total_month=Integer.parseInt(hesuanshijian_month)-birthday_month;
			
			int zongshijian=(total_year*12)+total_month;
			
			
			if(zongshijian<=16*12){
				if(zongshijian<=(16*12-2)){
					faqianid.put(id, 3);
					
				}else if(zongshijian==(16*12-1)){
					faqianid.put(id, 2);
					
				}else if(zongshijian==(16*12)){
					faqianid.put(id, 1);
				}else {
					
				}
				
				
				
				
			}else if("男".equals(sex)){
				
				if(zongshijian>=(60*12+1)){
					
					//核算季度的第一个月前 已经到龄的发三个月
					faqianid.put(id, 3);
					
				}else if(zongshijian==60*12){
					//核算季度的第一个月  已经到龄的发两个月
					faqianid.put(id, 2);
					
				}else if(zongshijian==(60*12-1)){
					//核算季度的第二个月  已经到龄的发一个月
					faqianid.put(id, 1);
					
				}else{
					
				}
				
				
				
				
			}else if("女".equals(sex)){
				if(zongshijian>=(55*12+1)){
					
					//核算季度的第一个月前 已经到龄的发三个月
					faqianid.put(id, 3);
					
				}else if(zongshijian==55*12){
					//核算季度的第一个月  已经到龄的发两个月
					faqianid.put(id, 2);
					
				}else if(zongshijian==(55*12-1)){
					//核算季度的第二个月  已经到龄的发一个月
					faqianid.put(id, 1);
					
				}else if(zongshijian<=(55*12-2)){
					
				}
				
				
				
			}else{
				
				
			}
			
			System.out.println(birthday_year+"------"+birthday_month);
			
			
			
			
			
			
			
			
			
			
			
}		
			
			
			
			
			
			
			
			Sdnm sdnm=new Sdnm(id, yuanid, zyqmj, bczymj, zdgsynmj, zysj, name, sex, relation, idnum, hktype, nonghucode,birthday,qian);			list.add(sdnm);
//			System.out.println(id + "|" + dept + "|" + detail + "|"
//					+ zhenglidate + "|" + code + "|" + ps + "|" + dangantype+ "|" + townid + "|" + niandu+ "|" + danghao + "|" + operator);

		}
		
		

		
		
		queryById(rows,page,response,request,out,faqianid);
		
		


	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
	
/**
 * 
 * 根据id 查询失地保障的人员详细信息
 * @param rows
 * @param page
 * @param response
 * @param request
 * @param out
 */

public void queryById(String rows, String page,
		HttpServletResponse response, HttpServletRequest request,
		PrintWriter out,HashMap<Integer,Integer> idMap) {

	Connection conn = null;
	ResultSet rs = null;

	DBHelperMysql dbh = new DBHelperMysql();
	conn = dbh.getConnection();

	
	//满足条件的记录总数
	int total =idMap.size();
	
	
	//解析满足条件的 id
	
	StringBuffer sb=new StringBuffer();
	
	String updateSql="update tb_shidinongming SET qian=? where id=?";
	
	for (Entry<Integer, Integer> entry : idMap.entrySet()) {
		int id=entry.getKey();
		sb.append(id).append(",");
		
		
		int yuefen=entry.getValue();
		
		
		    
		try {
			PreparedStatement pstmt=conn.prepareStatement(updateSql);
			     
			    pstmt.setString(1,"120×"+yuefen+"="+yuefen*120);  //这儿要对应的，1代表sql语句中第一个问号，那是int类型的score值
			    pstmt.setInt(2,id); //2代表sql语句中第二个问号，是String类型的username值
			    pstmt.executeUpdate();
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		System.out.println("发钱的id:"+id+" 发钱的月份："+yuefen);
	}

	
	
	
	String manzutiaojianids=sb.toString().substring(0, sb.length()-1);
	
	
	
	
	
	

	// 返回的json字符串
	String result = "";

	String sql = "select * from tb_shidinongming "; // 查询数据的sql语句
	
	if (!"".equals(manzutiaojianids)){
		
		sql+=" where id in (" +manzutiaojianids+" )";
	}

//	System.out.println("---------------");
	// 当前页
	int intPage = Integer.parseInt((page == null || page == "0") ? "1"
			: page);
	// 每页显示条数
	int number = Integer.parseInt((rows == null || rows == "0") ? "10"
			: rows);
	// 每页的开始记录 第一页为1 第二页为number +1
	int start = (intPage - 1) * number;

	sql += " limit " + start + "," + number;
	
	printSql=sql;

	System.out.println("++++++++++++分页查询执行的sql++++++++:" + sql);
	try {
		Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

		rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集

		// -----------------------------------------------------------------
		// response.getWriter().write("success");

		System.out.println("最后的查rs询结果为：" + rs.getRow());

		List<Sdnm> list = new ArrayList<Sdnm>();

		System.out
				.println("分页查询结果--------------------------------------------------------------------");
		
		int time1=(int) System.currentTimeMillis();
		while (rs.next()) {
			
			

			int id = rs.getInt(1);
			String yuanid = rs.getString(2);
			String zyqmj = rs.getString(3);
			String bczymj = rs.getString(4);
			String zdgsynmj = rs.getString(5);
			String zysj = rs.getString(6);
			String name = rs.getString(7);
			String sex = rs.getString(8);
			String relation = rs.getString(9);
			String idnum = rs.getString(10);
			String hktype = rs.getString(11);
			String nonghucode = rs.getString(12);
			String birthday=rs.getString(13);
			String qian=rs.getString(18);
			Sdnm sdnm=new Sdnm(id, yuanid, zyqmj, bczymj, zdgsynmj, zysj, name, sex, relation, idnum, hktype, nonghucode,birthday,qian);
			list.add(sdnm);
			System.out.println(id + "|" + name + "|" + sex + "|"
					+ relation + "|" + idnum + "|" + hktype + "|" + nonghucode+ "|" + birthday + "|" + zdgsynmj);

		}
		
		
		System.out
				.println("分页查询结果--------------------------------------------------------------------");

		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", total);// total键 存放总记录数，必须的
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		result = JSONObject.fromObject(jsonMap).toString();// 格式化result
															// 一定要是JSONObject

		System.out.println("----------------分页返回的总字符串：" + result);

		out.print(result);

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
	
	
		
	}    
	
	
	
	
	
	

