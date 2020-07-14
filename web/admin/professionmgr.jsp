<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'professionmgr.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <title>专业信息管理</title>
  <link rel="shortcut icon" href="img/favicon.ico">

<!-- global stylesheets -->

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/font-icon-style.css">
<link rel="stylesheet" href="css/style.default.css"
	id="theme-stylesheet">

<!-- Core stylesheets -->
<link rel="stylesheet" href="css/form.css">
  </head>
  
  <body>
  <%@ include file="mainnavbar.jsp"%>
  <div class="page-content d-flex align-items-stretch">
		<%@ include file="sidenavbar.jsp"%>

		<div class="content-inner chart-cont">
			<div class="card form" id="form2"
				style="padding:10px 20px 20px 40px;">
				<div class="card-header">
					<h4>专业信息管理</h4>
				</div>
				<br>
				<form action="ProfessionServlet" method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-text-input" style="width: 85px;"class=" col-form-label">所属分院：</label>
								<div class="col-9">
									<select class="form-control" id="colselect" name="collegeID" onchange="loaddep()">																		
										
									</select>

								</div>
							</div>
							</div>
							</div>
							<div class="row">
						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-text-input" style="width: 85px;"
									class=" col-form-label">所属系部：</label>
								<div class="col-9">
									<select class="form-control" id="depselect" name="departmentID" onchange="loadpro()">																		
										
									</select>

								</div>
							</div>
							<div class="form-group row">
								<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">专业编号:</label>
								<div class="col-9">
									<input class="form-control" type="text" value=""id="ProfessionID" name="ProfessionID">
								</div>
							</div>
							

						</div>
						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-datetime-local-input" style="width: 85px;"
									class=" col-form-label">专业名称:</label>
								<div class="col-9">
									<input class="form-control" type="text" value=""
										id="ProfessionName" name="ProfessionName">
								</div>
							</div>
							<div class="form-group row">
								<label for="example-datetime-local-input" style="width: 85px;"
									class=" col-form-label">备注:</label>
								<div class="col-9">
									<input class="form-control" type="text" value=""
										id="Remark" name="Remark">
								</div>
							</div>
							
									<input type="hidden" value="in" name="mg" id="mg">
								<button type="submit" class="btn btn-general btn-white">保存</button>
							<button type="reset" class="btn btn-general btn-white" onclick="inputreset()">重置</button>
							
						</div>
						
					</div>
				</form>
			</div>


			<div class="card form" id="form2"
				style="padding:20px 30px 20px 30px;">
				<div class="row">
					
					<table class="table table-hover" style="width:100%;" id="protab">
						<thead>
							<tr class="bg-info text-white">
								<th style="width: 10%;">序号</th>
								<th style="width: 10%;">专业编号</th>
								<th style="width: 20%;">专业名称</th>
								<th style="width: 20%;">所属学院</th>
								<th style="width: 20%;">所属系部</th>
								<th style="width: 20%;">备注</th>
								<th style="width: 10%;">操作</th>
							</tr>
						</thead>
						<tbody>
							

						</tbody>
					</table>
				</div>
			</div>

		</div>
		</div>
		<!--Global Javascript -->
		<script src="js/jquery.min.js"></script>
		<script src="js/popper/popper.min.js"></script>
		<script src="js/tether.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.cookie.js"></script>
		<script src="js/jquery.validate.min.js"></script>
		<script src="js/chart.min.js"></script>
		<script src="js/front.js"></script>

		<!--Core Javascript -->
  <script type="text/javascript">
		$(document).ready(loadcols());
		
		function loadcols(){
			
			$.ajax({
				url:"CollegeServlet?mg=se",
				type:"post",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
						$("#colselect").append("<option value='"+data[i].collegeID+"'>"+data[i].collegeName+"</option>");
					}
					loaddep();
				},
				error:function(){
					alert("分院数据请求失败！");
				}
			});
		}
			function loaddep(){
				var id=$("#colselect").find("option:selected").val(); 
			$.ajax({		     	
				url:"DepartmentServlet?mg=se",
				type:"post",
				dataType:"json",
				data:{"collegeID":id},
				success:function(data){
				    $("#depselect option").remove();
				    
					for(var i=0;i<data.length;i++){
					$("#depselect").append("<option value='"+data[i].departmentID+"'>"+data[i].departmentName+"</option>");
					}
					loadpro(); 
				},
				error:function(){
					alert("系部数据请求失败！");
				}
			});
		}
		
		function loadpro(){
	
		    var id=$("#depselect").find("option:selected").val();	  
		    
			 $.ajax({
				url:"ProfessionServlet?mg=se",
				type:"post",
				data:{"departmentID":id},
				dataType:"json",
				success:function(data){
					$("#protab tbody").html("");
				     var trhtml="";
				     for(var i=0;i<data.length;i++){
					    trhtml+="<tr><th scope='row'>";    
						trhtml+=i+1;
						trhtml+="</th><td>";
						trhtml+=data[i].professionID;
						trhtml+="</td><td>";
						trhtml+=data[i].professionName;
						trhtml+="</td><td>";
						trhtml+=$("#colselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=$("#depselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=data[i].remark;
						trhtml+="</td><td><a href='javascript:void(0)' onclick='rowselect("+(i+1)+")'>选择</a>&nbsp;&nbsp;<a href='ProfessionServlet?mg=de&ProfessionID="
						trhtml+=data[i].professionID;
						trhtml+="'>删除</a></td></tr>";
					} 				
					$("#protab tbody").append(trhtml);
				},
				error:function(){
					 alert("专业数据请求失败！"); 
				}
			}); 
		}
		
		
		function rowselect(index) {
			var tb = document.getElementById("protab");				
			$("#ProfessionID").val(tb.rows[index].cells[1].innerHTML);
			$("#ProfessionName").val(tb.rows[index].cells[2].innerHTML);
			$("#Remark").val(tb.rows[index].cells[5].innerHTML);
			$("#ProfessionID").attr("readOnly",true);
			$("#mg").val("up");
		}
		function inputreset() {
			$("#ProfessionID").attr("readOnly", false);
		}
		
		</script>
  </body>
</html>
