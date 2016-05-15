<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("utf-8");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

 <%-- <%
	if(session.getAttribute("name") == null) {
%>
		<script type="text/javascript" language="javascript">
			alert("您还没有登录，请登录...");
			window.document.location.href="login.jsp";
		</script>	
<%
	}
%>  --%>


<head>
<meta charset="UTF-8">

<title>东至县失地农民保障系统</title>
<link rel="icon" type="image/gif" href="imagess/favicon.gif" />
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>


<OBJECT ID="jatoolsPrinter"
	CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255"
	codebase="jatools/jatoolsPrinter.cab#version=10,0,7564"></OBJECT>

<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts-3d.src.js"></script>
<script type="text/javascript" src="js/highcharts-3d.js"></script>
<script type="text/javascript" src="js/highcharts-more.js"></script>
<script type="text/javascript" src="js/highcharts-more.src.js"></script>
<script>
   
 /* 图标显示的柱状图 */
	


/* 显示饼图	 */

﻿$(function () {

	

    $('#bingtu').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: '2011-2016各年度档案统计'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '档案数目',
            data: [
                ['2011',   80000],
                ['2012',       34000],
                {
                    name: '2016',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['2013',    12005],
                ['2014',     21000],
                ['2015',   15441]
            ]
        }]
    });
});				


   </script>

</head>

<script>
  
 function doPrint1(how) { 
 
    var myDoc = {
    	autoBreakPage:true,
        documents: document,
        copyrights: '杰创软件拥有版权  www.jatools.com'
    }; 
    if (how == '打印预览') 
         jatoolsPrinter.printPreview(myDoc); // 打印预览
    else if (how == '打印') 
         jatoolsPrinter.print(myDoc, true); // 打印前弹出打印设置对话框
    else
         jatoolsPrinter.print(myDoc, false); // 不弹出对话框打印
}
  
  
  
  
  
  
  
  
  </script>






<script type="text/javascript">
  
   $(function () {
   /* 默认关闭文件选择框 */
   $("#dayinganjuanfengmian").window('close');
   $("#w").window('close');
   $("#outputExcel").window('close');
   $("#findIDCard").window('close');
   $("#shujutongji").window('close');
   $('#hesuan').window('close');
   $('#dayingxuanze').window('close');
   
   
   $("#closeTongji").click(function(event) {
	$('#shujutongji').window('close');
	
	
   
});
   
   
   
   
   
   
   /*默认关闭对话框  */
   
   $("#dlg").dialog('close');
   $("#daoru").dialog('close');
   $("#daochuDialog").dialog('close');
   
   
   
   
   
   
  		 $('#list_data').datagrid({
  	toolbar: [{ 
            text: '档案借阅', 
            iconCls: 'icon-add', 
            handler: function() { 
                /* $.messager.alert('温馨提示',"考虑到档案的整理规范，目前暂不放开单个增加档案功能" ); */
               
 										
 										window.open('http://127.0.0.1:8089/Demo01/danganchayue.jsp','_blank');
 									
		
		
		
		
		
		
				
		
		}
                
                
                
                
                
             
        }, '-',{ 
            text: '添加', 
            iconCls: 'icon-add', 
            handler: function() { 
                $.messager.alert('温馨提示',"考虑到档案的整理规范，目前暂不放开单个增加档案功能" );
            } 
        }, '-', { 
            text: '修改', 
            iconCls: 'icon-edit', 
            handler: function() { 
				var row = $('#list_data').datagrid('getSelected');
					if (row){
					
					  $("#hesuan").dialog("open").dialog('setTitle', '修改档案信息');
					  $("#keyinfo").val(row.detail);
					  $("#danganbianhao1").val(row.danghao);
					  $("#code").val(row.code);
					  $("#onlyid1").val(row.onlyid);
					
						/* $.messager.alert('请谨慎修改！', row.onlyid+":"+row.detail+":"+row.dept); */
					}
			}
 
             
        }, '-',{ 
            text: '删除', 
            iconCls: 'icon-remove', 
            handler: function(){ 
            
            var row = $('#list_data').datagrid('getSelected');
					if (row){
					
					 /*  $("#hesuan").dialog("open").dialog('setTitle', '修改档案信息');
					  $("#keyinfo").val(row.detail);
					  $("#danganbianhao1").val(row.danghao);
					  $("#code").val(row.code);
					  $("#onlyid1").val(row.onlyid); */
					
					/* 	 $.messager.alert('确定删除此条数据？',row.detail); */
						 
						 $.messager.confirm('确定删除此条数据？', row.detail, function(r){
				if (r){
					 	$.ajax(
	        							{
	        							url:"servlet/XiugaiServlet?random="+Math.random()+"&onlyid1="+encodeURI(row.onlyid,'utf-8')+"&isDelete="+encodeURI("isDelete",'utf-8'),
	        							async:true,
	        							type:"post",
	        							 contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	        							 
	        							 success: function (msg){
 										
	 							     			if(msg=="success"){
	 							     				
	 							     			$.messager.alert('删除成功！',"数据删除成功，请核实！");
	 							     			}
										    }
 							     			
 							     			
 							     			
 							     			
 							     			
 				         				,  
						 				 error: function (data,status,e){  
						 					alert("导入出错");   
			  							}
	        							 
	        							 
	        							 }); 
				}
			});
						 
					}
            
            
            
            
            
            
            
            
            
            } 
        },'-',{ 
            
            text: '打印本页', 
            iconCls: 'icon-print',
            handler: function(){ 
            
            /* $('#page1').style.width='500'; */
           
            
            $('.datagrid-btable').attr('id','page1');
            $('.datagrid-btable').attr('autoBreakTable','*');
            doPrint1('打印预览');
            
            
               
            }
            
         },'-',{ 
            text: '打印案卷封面', 
            iconCls: 'icon-print', 
            handler: function(){ 
            
            
             $("#dayingxuanze").window('open');
            
            
            
            
            
               /*  $("#dayinganjuanfengmian").window('open'); */
            }
          },'-',{ 
            text: '批量从Excel导入', 
            iconCls: 'icon-redo', 
            handler: function(){ 
                $("#w").window('open');
            }
          },'-',{ 
            text: '精确查找', 
            iconCls: 'icon-search', 
            handler: function(){
            
            
                $("#findIDCard").window("open"); 
            }
          },'-',{ 
            id:'b01',
            text: '导出到Excel', 
            iconCls: 'icon-undo', 
            handler: function(){ 
            
            
            $("#outputExcel").window("open");
            
            
     
          }
          },'-',{ 
            text: '数据统计', 
            iconCls: 'icon-tongjishuju', 
            handler: function(){ 
            
            	var data=[];
            	
            	
            
            
            
            	$.ajax(
	        							{
	        							url:"servlet/TuBiaoServlet?random="+Math.random(),
	        							async:false,
	        							 contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	        							 
	        							 success: function (msg){
 										
 							     			data=msg.split(",");
 							     			
 							     			
 							     			 var current_time=0;
    
										    for (i = 0; i <=14; i++) {
										        data.push({
										            x: current_time,
										            y: parseInt(data[i])
										        });
										        if(current_time<=14){
										            current_time++;
										        }
										    }
 							     			
 							     			
 							     			
 							     			
 							     			
 				         				},  
						 				 error: function (data,status,e){  
						 					alert("导入出错");   
			  							}
	        							 
	        							 
	        							 }); 
            
            
            
            
            
                          $("#shujutongji").window("open");
            
            
            
            
            
            
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'column',
            margin: 75,
            options3d: {
                enabled: false,
                alpha: 1,
                beta: 1,
                depth: 50,
                viewDistance: 25
            }
        },
        shadow:true,
        
        yAxis:{
  			 title:{
      			 text:null
   			}
		},
		
		xAxis: { 
			title:{
			
			text:''
			},
            categories: ['尧渡镇', '东流镇', '香隅镇', '胜利镇', '张溪镇', '龙泉镇', '木塔乡', '大渡口镇', '泥溪镇','昭潭镇','洋湖镇','葛公镇','青山乡','官港镇','花园乡']
         } ,
        
        title: {
            useHTML:true,
            text: '<font style="color:#432fff;size:50px">东至县各乡镇档案案卷统计图</font>'
        },
        subtitle: {
            text: '档案数据依据纸质档案和电子档比对综合生成'
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        
        
       
        
        series: [{
        	name: '档案数目',
            data: data
        }]
        
        
    });
    

   
            
            
            
            
            
            
            
            
            
            
            
            
            
            
              
            }
          },'-',{ 
            text: '帮助', 
            iconCls: 'icon-help', 
            handler: function(){ 
            
            	$.messager.alert('帮	助',"如对此档案系统有问题，请联系18856637970|潘同学" );
            }
          },'-',{ 
            text: '备份数据', 
            iconCls: 'icon-ok', 
            handler: function(){ 
            
              $.messager.alert('警告！',"关键操作，请联系数据库管理员" );
            }
          }]
  
  
  });
  
 /* /*  获得柱状图 y轴数据 */
 /*  function getFirstData(msg){
  		alert(msg);
    var data = [];
    var y_mx=Math.round(Math.random()*10000);
    var i;
    var current_time=0;
    
    for (i = 0; i <= 14; i++) {
        data.push({
            x: current_time,
            y: parseInt(msg[i])
        });
        if(current_time<=14){
            current_time++;
        }
    }
    return data;} */
/*  */ 
  
  
  
  
   var p = $('#list_data').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 60,//每页显示的记录条数，默认为10 
        pageList: [60,40,50,30,120],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共{total} 条记录'
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
        
        
    }); 
    //满足发放待遇的人员分页设置
   var p = $('#list_data_manzufafang').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 60,//每页显示的记录条数，默认为10 
        pageList: [60,40,50,30,120],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共{total} 条记录'
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
        
        
    }); 
    
  
   $("#list_data_manzufafang").datagrid('reload');  
 
  
  	

 
  });
  
  
  
  
 
  
  

  
  
   /* ****************弹出导出excel对话框******************* */
  /* //打印。。。。。。。。。。 */
  
  
  var tableString = "<linkrel='stylesheet' type='text/css' href='Lodop/print.css' /><tablecellspacing='0;' id='PrintBody'>";
function doPrint()
{
      tableString += "<scriptlanguage='javascript'>window.print();</s"+"cript>";
      // tableString.insertAdjacentHTML("beforeBegin","<scriptlanguage='javascript'>window.print();</s"+"cript>")
      document.open('','','height=500,width=611,scrollbars=yes,status =yes');
     document.write(tableString);
      document.close();
}
 
// strPrintName 打印任务名
// printDatagrid 要打印的datagrid
function CreateFormPage(strPrintName, printDatagrid) {
    var frozenColumns = printDatagrid.datagrid("options").frozenColumns;  // 得到frozenColumns对象
    var columns = printDatagrid.datagrid("options").columns;    // 得到columns对象
 
    // 载入title
    tableString = tableString + "\n<tr>";
    if(frozenColumns != undefined && frozenColumns != '') {
       for(var i = 0;i<frozenColumns[0].length; i++) {
           if(frozenColumns[0][i].hidden != true) {
              tableString = tableString + "\n<th width= '" +frozenColumns[0][i].width  +"'>" + frozenColumns[0][i].title + "</th>";
           }
       }
    }
    if(columns != undefined && columns != '') {
       for(var i = 0;i<columns[0].length; i++) {
           if(columns[0][i].hidden != true) {
              tableString = tableString + "\n<th width= '" +columns[0][i].width  +"'>" + columns[0][i].title + "</th>";
           }
       }
    }
    tableString = tableString + "\n</tr>";
 
    // 载入内容
    var rows = printDatagrid.datagrid("getRows"); // 这段代码是获取当前页的所有行。
    for(var j = 0; j <rows.length;j++) {
       tableString = tableString + "\n<tr>";
       if(frozenColumns != undefined && frozenColumns != '') {
           for(var i = 0;i<frozenColumns[0].length; i++) {
              if(frozenColumns[0][i].hidden != true) {
                  tableString = tableString + "\n<td >" + rows[j][frozenColumns[0][i].field] + "</td>";
              }
           }
       }
       if(columns != undefined && columns != '') {
           for(var i = 0;i<columns[0].length; i++) {
              if(columns[0][i].hidden != true) {
                  tableString = tableString + "\n<td >" + rows[j][columns[0][i].field] + "</td>";
              }
           }
       }
       tableString = tableString + "\n</tr>";
    }
    tableString = tableString + "\n</table>";
    doPrint();
}


	function printData() {


	alert("222");
	 var dg=$('#list_data').datagrid();
    CreateFormPage("核销账款明细",dg); 
    
    
    
    
    
};




	  $(function () {
	  
	  $('#xxk').tabs('getTab', '满足发放的人员信息').panel('options').tab.hide(); 
   $('#hesuan').window('close');
   });
	function add() { 
	$('#xxk').tabs('getTab', '满足发放的人员信息').panel('options').tab.show(); 
				$('#xxk').tabs('select', '满足发放的人员信息'); 
					
					  $("#hesuan").dialog("open").dialog('setTitle', '填写核算时间');
					
						/* $.messager.alert('请谨慎修改！', row.onlyid+":"+row.detail+":"+row.dept); */
					}

  </script>



<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="overflow:hidden; height:60px;background-image:url('shidi3.jpg');background-repeat:repeat-x; padding:10px;text-align:center;texe-size:40">
		<font color="#FFFFFF" size="100px">东至县|<font
			color="#FFFFFF" size="100px">失地农民保障系统</font>
		</font>
	</div>


	<!--  <div data-options="region:'north',border:false" style="height:62px;background:;padding:0px;text-align:center"><img src="logo3.png"></div>
 -->

	<div data-options="region:'west',split:true,title:'档案分类'"
		style="width:230px;padding:1px;">

			<button  style="width:98% " onclick="add();" >核定待遇</button>
				<hr>
			<button  style="width:98%" onclick="">删除查阅记录</button>

		<div class="easyui-panel" style="padding:0px;width:100%;height: 100%">
			<ul class="easyui-tree" id='allTree'>
				<li><span>失地农民所属乡镇</span>
					<ul>
						<li data-options="state:'open'"><span>分类</span>
							<ul>
								<li><span id='cbdjl'>尧渡镇</span></li>
								<li><span id='xxbgl'>大渡口</span></li>
								<li><span id='gxzyl'>东流</span></li>
							</ul></li>
					</ul>
						


			</ul>
		</div>

	</div>


	<script type="text/javascript">
		
		
		
		$('#allTree').tree({   
    onDblClick: function(node){
    			var ziduan=node.text;
		        getSelected(ziduan);
	    }

});



	function getSelected(ziduan){
	
	
		
		 $('#list_data').datagrid({
		 
		 url:"servlet/FenleiQueryServlet?random="+Math.random()+"&ziduan="+encodeURI(ziduan,'utf-8'),
		 
		 });
		 
		 $("#list_data").datagrid('reload');  
		  var p = $('#list_data').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 60,//每页显示的记录条数，默认为10 
        pageList: [60,40,50,30,120],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共{total} 条记录'
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
        
        
    }); 
		
		
		
		
		
	
	}
		
		
		
	
	</script>

	<!-- 	弹出框
 -->
	<div id="dlg" class="easyui-dialog" title="操作成功"
		data-options="dialog:close,iconCls:'icon-ok'"
		style="text-align:center;width:400px;height:90px;padding:1px;">
		<h2>恭喜你！导出Excel成功！</h2>
	</div>


	<div id="daoru" class="easyui-dialog" title="操作成功"
		data-options="dialog:close,iconCls:'icon-ok'"
		style="text-align:center;width:400px;height:90px;padding:1px;">
		<h2>恭喜你！导入Excel数据成功！</h2>
	</div>

	<div id="daochuDialog" class="easyui-dialog" title="操作成功"
		data-options="dialog:close,iconCls:'icon-ok'"
		style="text-align:center;width:400px;height:90px;padding:1px;">
		<h2>恭喜你！导出Excel数据成功！</h2>
	</div>

	<!------------数据统计窗口----------------------------------------------------------------------------------  -->
	<script type="text/javascript">



</script>

	<!-- 	<div id="shujutongji" class="easyui-window" title="" data-options="iconCls:'icon-save'" style="width:1300px;height:500px;padding:5px;">
 -->
	<!-- 柱状图 -->
	<div id="shujutongji" class="easyui-dialog" title=""
		data-options="dialog:close,iconCls:'icon-ok'"
		style="text-align:center;width:1300px;height:500px;padding:1px;">
		<div align="right">
			<button id='closeTongji' value="关闭窗口">X</button>
		</div>
		<div id="container" style="min-width:100px;height:400px"></div>

	</div>



	<!------------文件上传弹出窗口----------------------------------------------------------------------------------  -->

	<div id="w" class="easyui-window" title="请选择标准的档案Excel文件上传"
		data-options="iconCls:'icon-save'"
		style="width:500px;height:190px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">

				<!--文件上传框---------------------------------------------------------------------- -->

				<!-- 						<div class="easyui-panel" title="Upload File" style="width:400px;padding:30px 70px 50px 70px">
 -->
				<div style="margin-bottom:20px">
					<div>您选择的文件地址为:</div>
					<input id="chosedFile" class="easyui-filebox" name="file1"
						data-options="prompt:'请选择一个Excel文件...'" style="width:100%">
				</div>

				<div style="padding-bottom:3px">
					<a href="#" id="startUpload" class="easyui-linkbutton"
						style="width:100%">开始导入</a>
				</div>


				<script type="text/javascript">
								$(function() {
									$("#startUpload").bind('click', function(){    
	        							
	        							
	        							var fileUrl=$("#chosedFile").filebox('getValue');
	        							
	        	/* ------------------把选择的文件地址发给服务器处理------------------------------------------------------- */						
	        							htmlobj=$.ajax(
	        							{
	        							url:"servlet/ImportExcel?fileUrl="+encodeURI(fileUrl,'utf-8')+"&random="+Math.random(),
	        							async:false,
	        							 contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	        							  beforeSend:function(){
											        },
	        							 
	        							 success: function (msg){
 											/* alert(msg); */
 											if(msg=='fileUploadSuccess'){
 											
 											$("#w").window("close");
	 				           				$("#daoru").window("open");         
 											}else if(msg=='fileUploadFail'){
 											
 												alert("选择的文件不是excel,请重新选择");
 											}
 							     
 				         				},  
						 				 error: function (data,status,e){  
						 					alert("导入出错");   
			  							}
	        							 
	        							 
	        							 });
	 		 							$("#myDiv").html(htmlobj.responseText); 
	        							    
	    							});  
								
								})  ;
							
							</script>

				<div>
					<a href="#" class="easyui-linkbutton" style="width:100%">取消导入</a>
				</div>
				<div id='vvv'></div>
				<!--文件上传框---------------------------------------------------------------------- -->



			</div>

		</div>
	</div>


	
<script>
function submitForm1(){
	      	var hesuanshijian_year=$('#hesuanshijian_year').val();
	      	
	      	
	      	var hesuanshijian_month=$("input[name='season']:checked").val();
	      	
	      alert(hesuanshijian_month);
	      	
	      	
	      	
	      	$.ajax(
	        							{
	        							url:"servlet/HesuanServlet?random="+Math.random()+"&hesuanshijian_year="+encodeURI(hesuanshijian_year,'utf-8')+"&hesuanshijian_month="+encodeURI(hesuanshijian_month,'utf-8'),
	        							async:true,
	        							type:"post",
	        							 contentType:"application/x-www-form-urlenchayueneirongd:charset=UTF-8",
	        							 
	        							 
	        							 
	        							 }); 
	        $("#hesuan").window('close');
	    
	    
	    
	    
	    }
	    
	    
	    
	function submitForm3(){
	      	var hesuanshijian_year=$('#hesuanshijian_year').val();
	      	
	      	var hesuanshijian_month=$("input[name='season']:checked").val();
	      	
	      alert(hesuanshijian_month);
	      	
	      	
	      
	      	 $('#list_data_manzufafang').datagrid({
		 
	        	url:"servlet/HesuanServlet?random="+Math.random()+"&hesuanshijian_year="+encodeURI(hesuanshijian_year,'utf-8')+"&hesuanshijian_month="+encodeURI(hesuanshijian_month,'utf-8')
		 
		 });
		 
		  $('#hesuan').window('close');
		 
		  $("#list_data_manzufafang").datagrid('reload'); 
	  
	    
	    
	    
	    
	    }
	    


</script>


<div id="hesuan" closed="true" class="easyui-window" title="请务必仔细核对填写！" data-options="iconCls:'icon-edit'"
		style="width:520px;height:320px;padding:5px;">
		<div style="padding:10px 10px 10px 10px;align:center">
		<font style="color:red;text-align:center"><h3>请填写相关信息（全部都不能为空！）</h3>
				</font>
		
			<form id="xiugaiform" method="post" style="align:center">
				<table>
					<tr>
						<td>核算年份:</td>
						<td><input type="text" style="width: 350px" id="hesuanshijian_year"
							name="hesuanshijian_year" data-options=""></input>
						</td>
					</tr>
					<!-- <tr>
						<td>核算月份:</td>
						<td><input type="text" style="width: 350px" id="hesuanshijian_month"
							name="hesuanshijian_month" data-options=""></input>
						</td>
					</tr> -->
					
					<tr>
						<td>核算季度:</td>
						<td>
						
<label><input name="season" type="radio" value="01" />第一季度 </label> 
<label><input name="season" type="radio" value="04" />第二季度 </label> 
<label><input name="season" type="radio" value="07" />第三季度 </label> 
<label><input name="season" type="radio" value="10" />第四季度</label> 
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm3()">确认修改</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">取 消</a>
			</div>


			
	    
	      	
	      	
	    
	    


			<div style="align:center">
				<hr>
				<font style="color:red;text-align:center"><h3>该修改将直接影响档案系统的原始数据，并会记录操作者，请仔细填写!</h3>
				</font>

			</div>
		</div>




	</div>
	





	<!------------导出excel地址选择框----------------------------------------------------------------------------------  -->

	<div id="outputExcel" class="easyui-window" title="请选择保存位置"
		data-options="iconCls:'icon-save'"
		style="width:560px;height:160px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">

				<!--导出excel地址选择框---------------------------------------------------------------------- -->

				<!-- 						<div class="easyui-panel" title="Upload File" style="width:400px;padding:30px 70px 50px 70px">
 -->
				<!-- <div style="margin-bottom:20px">
								<div>您选择的文件地址为:</div>
								<input id="chosedFile" class="easyui-filebox" name="file1" data-options="prompt:'请选择一个Excel文件...'" style="width:100%">
							</div> -->



				<script type="text/javascript">
							
							function browseFolder(path) {
								    try {
								        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
								        var Shell = new ActiveXObject("Shell.Application");
								        var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
								        //var Folder = Shell.BrowseForFolder(0, Message, 0); //起始目录为：桌面
								        if (Folder != null) {
								            Folder = Folder.items(); // 返回 FolderItems 对象
								            Folder = Folder.item(); // 返回 Folderitem 对象
								            Folder = Folder.Path; // 返回路径
								            if (Folder.charAt(Folder.length - 1) != "\\") {
								                Folder = Folder + "\\";
								            }
								            document.getElementById(path).value = Folder;
								            return Folder;
								        }
								    }
								    catch (e) {
								        alert(e.message);
								    }
							}
							
							
							
							
								function ssss(){
								
								
	        							/* alert(document.getElementById(path).value()); */
								
								
								}
								
								
								$(function() {
									$("#startUpload2").bind('click', function(){    
									/* alert($("#path1").val());
									alert($("#filename").val()); */
									var saveUrl=$("#path1").val();
									var saveName=$("#filename").val();
	        							
	        							
	        							
	        	/* ------------------把想要导出到本地的位置和文件名称发给服务器处理------------------------------------------------------- */						
	        							htmlobj=$.ajax(
	        							{
	        							url:"servlet/ExportExcelServlet?saveUrl="+encodeURI(saveUrl,'utf-8')+"&saveName="+encodeURI(saveName,'utf-8')+"&isPrint=yes",
	        							async:false,
	        							 contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	        							 
	        							 success: function (msg){
 											/* alert(msg); */
 											if(msg=='success'){
 											$("#outputExcel").window("close");
	 				           				$("#daochuDialog").window("open");         
 											}else{
 											
 												alert("选择的文件不是excel,请重新选择");
 											}
 							     
 				         				},  
						 				 error: function (data,status,e){  
						 					alert("导入出错");   
			  							}
	        							 
	        							 
	        							 });
	        							    
	    							});  
								
								})  ;
							
							</script>



				<div>

					<table>
						<tr>
							<td>选择保存位置：</td>
							<td><input id="path1" type="text" name="path1" size="30">
							</td>
							<td><input type=button value="选择"
								onclick="browseFolder('path1')">
							</td>
						</tr>

						<tr>
							<td>请输入名字(不要带后缀名！)：</td>
							<td><input id="filename" type="text" name="path" size="30">
							</td>
						</tr>
					</table>
				</div>
				<!--文件上传框---------------------------------------------------------------------- -->

				<div style="padding-bottom:3px">
					<a href="#" id="startUpload2" class="easyui-linkbutton"
						style="width:100%">开始导出</a>
				</div>


			</div>

		</div>
	</div>





	<!----------------------------------------------------------------------------------------------  -->






	<!------------按身份证精确查找区----------------------------------------------------------------------------------  -->

	<div id="findIDCard" class="easyui-window" title="请输入查询信息"
		data-options="iconCls:'icon-search'"
		style="width:405px;height:250px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">




				<script type="text/javascript">
							
							function browseFolder(path) {
								    try {
								        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
								        var Shell = new ActiveXObject("Shell.Application");
								        var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
								        //var Folder = Shell.BrowseForFolder(0, Message, 0); //起始目录为：桌面
								        if (Folder != null) {
								            Folder = Folder.items(); // 返回 FolderItems 对象
								            Folder = Folder.item(); // 返回 Folderitem 对象
								            Folder = Folder.Path; // 返回路径
								            if (Folder.charAt(Folder.length - 1) != "\\") {
								                Folder = Folder + "\\";
								            }
								            document.getElementById(path).value = Folder;
								            return Folder;
								        }
								    }
								    catch (e) {
								        alert(e.message);
								    }
							}
							
							
							
							
								function ssss(){
								
								
	        							/* alert(document.getElementById(path).value()); */
								
								
								}
								
								
								$(function() {
									$("#findIdcardbutton").bind('click', function(){    
									
									if($("#s_name").val()==""){
									
										alert("你未输入任何查询数据!");
									}else{
												var s_name=$("#s_name").val();
												
												
									 $('#list_data').datagrid({
		 
		 url:"servlet/FenleiQueryServlet?random="+Math.random()+"&ziduan="+encodeURI('模糊查询','utf-8')+"&keys="+encodeURI(s_name,'utf-8'),
		 
		 });
		 
		  var p = $('#list_data').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 60,//每页显示的记录条数，默认为10 
        pageList: [60,40,50,30,120],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共{total} 条记录'
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
        
        
    }); 
		 $("#list_data").datagrid('reload');  
	        							
	        							
	        	
									
									} 
									 
	        							    
	    							});  
								
								})  ;
							
							</script>



				<div>

					<div class="easyui-panel" style="width:350px;padding:30px 60px">
						<!-- <div style="margin-bottom:20px">
									<div>你可以输入 身份证 姓名 乡镇 档案号等:</div><br>
									<input id="s_idcardnum" class="easyui-textbox" style="width:100%;height:32px">
								</div> -->
						<div style="margin-bottom:20px">
							<div>
								你可以输入 <font style="color: red">身份证 姓名 乡镇 档案号</font> 等:
							</div>
							<br> <input id="s_name" class="easyui-textbox"
								style="width:100%;height:32px">
						</div>
						<!-- <div style="margin-bottom:20px">
									<div>乡    镇:</div>
									<input id="s_town" class="easyui-textbox" style="width:100%;height:32px">
								</div> -->

						<div>
							<a id="findIdcardbutton" class="easyui-linkbutton"
								iconCls="icon-search" style="width:100%;height:32px">精确查找</a>
						</div>
					</div>

				</div>

			</div>
		</div>

	</div>



	<!----------------------------------------------------------------------------------------------  -->

	<div id="dayingxuanze" class="easyui-window" title="请输入档案号"
		data-options="iconCls:'icon-print'"
		style="width:405px;height:250px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">




				<script type="text/javascript">
							
							
								
								
								$(function() {
									$("#startPrints").bind('click', function(){    
									
									
									
										var danghao=$("#s_name2").val();
										
										
											if(danghao==""){
									
										alert("你未输入任何信息!");
									}else{
											
										
            	$.ajax(
	        							{
	        							url:"servlet/PrintAnjuanfengmianServlet?random="+Math.random()+"&danghao="+encodeURI(danghao,'utf-8'),
	        							async:true,
	        							 contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	        							 
	        							 success: function (msg){
	        							 
 										
 							     			var parsedJson = jQuery.parseJSON(msg); 
 							     			
 							     			if(parsedJson!=null){
 							     			
 							     			
 							     			var dangantype=parsedJson.dangantype;
 							     			var id=parsedJson.id;
 							     			var niandu=parsedJson.niandu;
 							     			var townid=parsedJson.townid;
 							     			var zhenglidate=parsedJson.zhenglidate;
 							     			var code=parsedJson.code;
 							     			var danghao=parsedJson.danghao;
 							     			
             								  window.open('print.jsp?dangantype='+encodeURI(dangantype,'utf-8')+'&niandu='+encodeURI(niandu,'utf-8')+'&townid='+encodeURI(townid,'utf-8')+'&zhenglidate='+encodeURI(zhenglidate,'utf-8')+'&code='+encodeURI(code,'utf-8')+'&danghao='+encodeURI(danghao,'utf-8')+'&id='+encodeURI(id,'utf-8'),'newwindow','height=800,width=780,top=100,left=400,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');   
 							     			}else{
 							     				  $.messager.alert('温馨提示',"无此档案号，或者输入格式错误，请检查后重新输入！" );
 							     			}
 							     			
 							     			
 				         				},  
						 				 error: function (data,status,e){  
			  							},
	        							 
	        							 
	        							 }); 
	        }
									
									});
									 
	        							    
	    							});  
								
							
							</script>



				<div>

					<div class="easyui-panel" style="width:350px;padding:30px 60px">
						<div style="margin-bottom:20px">
							<div>
								<font style="color: red">请输入标准格式的档案号:</font>
							</div>
							<br> <input id="s_name2" class="easyui-textbox"
								style="width:100%;height:32px">
						</div>

						<div>
							<a id="startPrints" class="easyui-linkbutton"
								iconCls="icon-print" style="width:100%;height:32px">开始打印</a>
						</div>
					</div>

				</div>

			</div>
		</div>

	</div>





	<!-- 	------------------------------------------------------------------------------------
 -->



	


	<div data-options="region:'south',border:false"
		style="height:12px;background:#FFFFFF;padding:0px;text-shadow:black; text-align: center;">
		<a style="color: #4A4A4A" href="#">技术支持：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;潘同学
			(QQ:1107635271)&nbsp;&nbsp;&nbsp;【东至县城乡居保中心】</a>
	</div>
	
	
	
	
	
	
	
	
	
	
	<div data-options="region:'center',title:'详细信息'"
		style="width:1250px;padding:1px;">
		<div id="xxk" class="easyui-tabs" style="width:1250px">
		<div title="失地农民信息卡" style="1300px ;text-align:center">
			<div>
				<table headerRows='1' id="list_data" 
					style="text-align:center;width:100%;height:550px;"
					data-options="singleSelect:true,collapsible:true,loadMsg:'档案量大，请稍等，数据装载中......',url:'servlet/FenyeServlet',method:'post',pagination:true">







					<thead>
						<tr>
							<th data-options="field:'id',width:40">编 号</th>
							<th data-options="field:'zyqmj',width:100">土地征用前面积</th>
							<th data-options="field:'bczymj',width:100">本次征地面积</th>
							<th data-options="field:'zdgsynmj',width:100">征地后剩余面积</th>
							<th data-options="field:'zysj',width:65">征地时间</th>
							<th data-options="field:'name',width:60">姓名</th>
							<th data-options="field:'sex',width:40">性别</th>
							<th data-options="field:'relation',width:60">与户主关系</th>
							<th data-options="field:'idnum',width:140">身份证号码</th>
							<th data-options="field:'hktype',width:50">户口类型</th>
							<th data-options="field:'nonghucode',width:100">农户编号</th>
							<th data-options="field:'birthday',width:100">出生日期</th>
							
							





						</tr>
					</thead>
				</table>
			</div>



			<!-- 打印的页面 -->

			<style>
.jp-page {
	position: relative
}

.jp-text,.jp-label,.jp-image,.jp-barcode {
	position: absolute;
	overflow: hidden
}

.jp-auto-stretch,.jp-barcode object,.jp-barcode embed {
	width: 100%;
	height: 100%;
}

.jp-paper-background {
	position: absolute;
	width: 100%;
	height: 100%;
}

.jp-comp-0 {
	filter: none;
}
</style>
			<script>
function doPrint3(how){
	var myDoc={
		settings:{
			paperWidth:2100,
			paperHeight:2970,
			orientation:1
		},
		marginIgnored:true,
		enableScreenOnlyClass:false,
		documents:document,
		/* pagePrefix: pk, */
		copyrights:'杰创软件拥有版权  www.jatools.com'
	};
	var jatoolsPrinter = navigator.userAgent.indexOf('MSIE') > -1 ? document.getElementById('ojatoolsPrinter'): document.getElementById('ejatoolsPrinter');
	if(how=='打印预览...')
		jatoolsPrinter.printPreview(myDoc);
	else if(how=='打印...')
		jatoolsPrinter.print(myDoc,true);
	else
		jatoolsPrinter.print(myDoc,false);
}
</script>


			<div id="dayinganjuanfengmian" class="easyui-window"
				title="Basic Panel" style="width:900px;height:600px;padding:0px;">
				<div>
					<input type="button" value="打印预览..." onclick="doPrint3('打印预览...')">
					<input type="button" value="打印..." onclick="doPrint3('打印...')">
					<input type="button" value="打印" onclick="doPrint3('打印')"> <span
						style="font-size:12px;color:gray;margin-left:20px;">按照案卷档号来打印案卷封面.</span>
				</div>
				<div class="jp-page" id="page1" style="width: 210mm; height: 297mm;">
					<img class="jp-paper-background screen-only"
						src="http://print.jatools.com/jatoolsPrinterUI/../backgroundImages/jp-6770991106267962723.png">
				</div>
				<!-- <object id="ojatoolsPrinter" codebase="jatoolsPrinter.cab#version=5,4,0,0" classid="clsid:B43D3361-D075-4BE2-87FE-057188254255" width="0" height="0">
					<embed id="ejatoolsPrinter" type="application/x-vnd.jatoolsPrinter" width="0" height="0"></embed>
				</object> -->




			</div>











			<!-----------------------图标插件 ----------------------------------- -->

			<!-- <div id="container" style="min-width:800px;height:200px;"></div> -->


			<!-- <div id="container" style="min-width:700px;height:400px"></div> -->
			﻿
			<div id="sliders"
				style="min-width:310px;max-width: 800px;margin: 0 auto;"></div>



		</div>
		<div title="满足发放的人员信息" style="1300px ;text-align:center">
		
		<table headerRows='1' class="easyui-datagrid" id="list_data_manzufafang" 
					style="text-align:center;width:100%;height:550px;"
					data-options="singleSelect:true,collapsible:true,loadMsg:'待遇核算中......',url:'',method:'post',pagination:true">

					<thead>
						<tr>
							<th data-options="field:'id',width:40">编 号</th>
							<th data-options="field:'zyqmj',width:100">土地征用前面积</th>
							<th data-options="field:'bczymj',width:100">本次征地面积</th>
							<th data-options="field:'zdgsynmj',width:100">征地后剩余面积</th>
							<th data-options="field:'zysj',width:65">征地时间</th>
							<th data-options="field:'name',width:60">姓名</th>
							<th data-options="field:'sex',width:40">性别</th>
							<th data-options="field:'relation',width:60">与户主关系</th>
							<th data-options="field:'idnum',width:140">身份证号码</th>
							<th data-options="field:'birthday',width:100">出生日期</th>
							<th data-options="field:'hktype',width:80">户口类型</th>
							<th data-options="field:'nonghucode',width:100">农户编号</th>
							<th data-options="field:'qian',width:100"><font style="color:red" >应发金额</font></th>
							
							





						</tr>
					</thead>
				</table>
		
		
		
		
		</div>
		
		</div>
	</div>




</body>

</html>