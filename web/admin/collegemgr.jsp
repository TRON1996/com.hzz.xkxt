<%@page import="com.hzz.xkxt.bean.College"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<base href="<%=basePath%>">

	<title>分院信息管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="shortcut icon" href="img/favicon.ico">

	<!-- global stylesheets -->

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/font-icon-style.css">
	<link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">

	<!-- Core stylesheets -->
	<link rel="stylesheet" href="css/form.css">
</head>

<body>
	<%@ include file="mainnavbar.jsp"%>


	<div class="page-content d-flex align-items-stretch">
		<%@ include file="sidenavbar.jsp"%>


		<div class="content-inner chart-cont">
			<div class="card form" id="form2" style="padding:10px 20px 20px 40px;">
				<div class="card-header">
					<h4>分院信息管理</h4>
				</div>
				<br>
				<form action="CollegeServlet" method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-text-input" style="width: 85px;"
									class=" col-form-label">分院编号：</label>
								<div class="col-9">
									<input class="form-control" type="text" value="" id="collegeID" name="collegeID">
								</div>
							</div>
							<div class="form-group row">
								<label for="example-datetime-local-input" style="width: 85px;"class=" col-form-label">分院名称:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="" id="collegeName"name="collegeName">
								</div>
							</div>
							

						</div>
						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">备注:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="" id="Remark" name="Remark">
								</div>
							</div>
							
							<div class="form-group row">
								<label for="example-datetime-local-input" style="width: 85px;"
									class=" col-form-label"></label>
								<div class="col-9">
									<input type="hidden" value="in" name="mg" id="mg">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<button type="submit" class="btn btn-general btn-white" onclick="tableload()">保存</button>
							<button type="reset" class="btn btn-general btn-white" onclick="inputreset()">重置</button>
						</div>
					</div>
				</form>
			</div>


			<div class="card form" id="form2" style="padding:20px 30px 20px 30px;">
				<div class="row">
					<%
						//request.setCharacterEncoding("utf-8");
					                //List<College> clist=(List<College>)request.getAttribute("clist");
					%>
					<table class="table table-hover" style="width:100%;" id="coltab">
						<thead>
							<tr class="bg-info text-white">
								<th style="width: 10%;">序号</th>
								<th style="width: 15%;">分院编号</th>
								<th style="width: 30%;">分院名称</th>
								<th>备注</th>
								<th style="width: 15%;">操作</th>
							</tr>
						</thead>
						<tbody>
							

						</tbody>
					</table>
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
		    $(document).ready(tableload());
		    function tableload(){				
				$.ajax({
					url:"CollegeServlet?mg=se",
					type:"post",
					dataType:"json",
					success:function(json){
						$("#coltab tbody").html("");						
						var trhtml="";
						for(var i=0;i<json.length;i++){						
						trhtml+="<tr><th scope='row'>"+(i+1)+"</th>";
						trhtml+="<td>"+json[i].collegeID+"</td><td>"+json[i].collegeName+"</td><td>"+json[i].remark+"</td>";
						trhtml+="<td><a href='javascript:void(0);' onclick='rowselect("+(i+1)+")'>选择</a>&nbsp;&nbsp;";
						trhtml+="<a onclick='tableload()' href='CollegeServlet?mg=de&collegeID="+json[i].collegeID+"'>删除</a></td></tr>";}						
						$("#coltab tbody").append(trhtml);						
					},
					error:function(){
						alert("error");
					}					
				});
			}
			function rowselect(index) {
				var tb = document.getElementById("coltab");				
				$("#collegeID").val(tb.rows[index].cells[1].innerHTML);
				$("#collegeName").val(tb.rows[index].cells[2].innerHTML);
				$("#Remark").val(tb.rows[index].cells[3].innerHTML);
				$("#collegeID").attr("readOnly",true);
				$("#mg").val("up");
			}
			function inputreset() {
				$("#collegeID").attr("readOnly", false);
			}
		</script>
</body>

</html>