<%@page import="com.hzz.xkxt.util.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'form-layouts.jsp' starting page</title>

<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">
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

	<!--====================================================
															 MAIN NAVBAR
			======================================================-->
	<jsp:include page="mainnavbar.jsp" flush="false"></jsp:include>

	<!--====================================================
															PAGE CONTENT
			======================================================-->
	<div class="page-content d-flex align-items-stretch">

		<!--***** SIDE NAVBAR *****-->
		<jsp:include page="teasidenavbar.jsp" flush="false"></jsp:include>

		<div class="content-inner chart-cont">

			<!--***** FORM LAYOUTS *****-->
			<div class="row">
				<div class="col-md-12">

					<!--***** USER INFO *****-->
					<div class="card form" id="form1">
						<div class="card-header">
							<h3>
								<i class="fa fa-user-circle"></i>课程管理系统
							</h3>
						</div>
						<br>
						<form action="TeachTaskServlet" method="POST">
							<div class="form-group row">
							<label for="example-text-input"
								style="width: 85px;margin-left: 5%" class=" col-form-label">请输入您要查询的学期:</label>
							<div class="col-9" style="flex-basis:20%">
								<input type="text" value="<%=util.getNow()%>" style="width: 100%;height: 40px;margin-top: 10px" 
								name="Curricula">
							</div>
							
							<input type="button" value="查询" style="height: 40px;margin-top: 10px" onclick="searchCourse()">
							<input class="form-control" type="hidden" style="width: 100%;"
									name="mg" value="se">
						</form>
					</div></div></div>
</div>
					<!--***** PRODUCT INFO *****-->
                      <div class="row">
					
					<table class="table table-hover" style="width:1220px;" id="coursetab">
						<thead>
                                        <tr class="bg-info text-white">
                                            <th>序号</th>
											<th>课程编号</th>
											<th>课程名称</th>
											<th>专业名称</th>
											<th>学分</th>
											<th>课时</th>
											<th>开课时间</th>
                                       </tr>
						</thead>
						<tbody>
							

						</tbody>
					</table>
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
	$(document).ready(searchCourse());
	function searchCourse(){
var Curricula = $("[name=Curricula]").val();
		$.ajax({
			url:"TeachTaskServlet?mg=se",
			type:"post",
			dataType:"json",
			data : {"Curricula" : Curricula},
			success:function(data){
			
				$("#coursetab tbody").html("");
				var trhtml="";
				for(var i=0;i<data.length;i++){
					    trhtml+="<tr><th scope='row'>";    
						trhtml+=i+1;
						trhtml+="</td><td>";
						trhtml+=data[i].course.courseID;
						trhtml+="</td><td>";
						trhtml+=data[i].course.courseName;
						trhtml+="</td><td>";
						trhtml+=data[i].course.profession.professionName;
						trhtml+="</td><td>";
						trhtml+=data[i].course.crediy;
						trhtml+="</td><td>";
						trhtml+=data[i].course.studyTime;
						trhtml+="</td><td>";
						trhtml+=data[i].curricula;
						trhtml+="</td><td></td><td></tr>";
					}
					$("#coursetab tbody").append(trhtml);
			},
			error:function(){
					alert("课程数据请求失败！");
				}
		});
	}
	
		</script>
</body>
</html>
