<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>系统登陆</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">
        
        
        <script type="text/javascript" src="easyui/jquery.min.js"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>
    
    <script>
    function check(){
				
		var name=document.getElementById("name").value;
		var password=document.getElementById("password").value;
		
		if(name!=""&&name!=null&&password!=null&&password!=""){
								
            	$.ajax(
	        							{
	        							url:"servlet/LoginCheckServlet?random="+Math.random()+"&name="+encodeURI(name,'utf-8')+"&password="+encodeURI(password,'utf-8'),
	        							async:true,
	        							 contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	        							 
	        							 success: function (msg){
	        							 
 										if(msg=="success"){
 										setTimeout(function(){
 										
 										
 										
 										window.open('http://127.0.0.1:8089/Shidinongming/index.jsp','_self');
 										},2000);
 										
 										
 										
 										}else{
 							     				$('#password').val("");
 							     				alert("账号或者密码错误,请重新输入！" );
 							     			}
 							     			
 							     			
 				         				},  
						 				 error: function (data,status,e){  
			  							},
	        							 
	        							 
	        							 }); 
		
		
		
		
		
		
				
		
		}else{
			alert("用户名密码不能为空！！");

		}
	}

    
    
    
    </script>

    <body>

        <div class="page-container">
            <h1>东至县居保中心失地农民保障系统</h1>
            <form action="" method="post">
                <input type="text" name="username" class="username" id="name" placeholder="用户名">
                <input type="password" name="password" class="password" id="password" placeholder="密	码">
                <button type="submit" onclick="check();return false;">登录失地保障系统</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>数据无价 谨慎操作</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
      <%
	session.setAttribute("name", "ddd"); 
	
	%>

        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>

    </body>

</html>

