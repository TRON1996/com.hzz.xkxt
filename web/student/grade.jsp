<%@page import="com.hzz.xkxt.bean.*"%>
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
		<jsp:include page="sidenavbar.jsp" flush="false"></jsp:include>

		<div class="content-inner chart-cont">

			<!--***** FORM LAYOUTS *****-->
			<div class="row">
				<div class="col-md-12">

					<!--***** USER INFO *****-->
					<div class="card form" id="form1">
						<div class="card-header">
							<h3>
								<i class="fa fa-user-circle"></i>成绩查看
							</h3>
						</div>
						<br>
	
		</div>
</div></div>
					<!--***** PRODUCT INFO *****-->
					<div class="card form" id="form2"style="padding:20px 30px 20px 30px;">
						
							<!--***** CONTENT *****-->
							<div class="row">
								<table class="table table-hover" id="schtab" style="width:1300px;" >
									<thead>
										<tr class="bg-info text-white">
											<th>序号</th>
											<th>课程名称</th>
											<th>学生名称</th>
											<th>学号</th>
											<th>学期</th>
											<th>学分</th>
											<th>课程号</th>
											<th>考勤分</th>
											<th>课堂表现分</th>
											<th>期末成绩</th>
											<th>总成绩</th>
						
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
	$(document).ready(loadsch());

	function loadsch(){
		$.ajax({
			url:"GradeServlet?mg=se",
			type:"post",
			dataType:"json",
			success:function(data){
			/*  alert(data)  */
				$("#schtab tbody").html("");
				var trhtml="";
				for(var i=0;i<data.length;i++){
					    trhtml+="<tr><th scope='row'>";    
						trhtml+=i+1;
						trhtml+="</th><td>";
						trhtml+=data[i].courseName;
						trhtml+="</td><td>";
						trhtml+=data[i].studentName;
						trhtml+="</td><td>";
						trhtml+=data[i].studentID;
						trhtml+="</td><td>";
						trhtml+=data[i].curricula;
						trhtml+="</td><td>";
						trhtml+=data[i].crediy;
						trhtml+="</td><td>";
						trhtml+=data[i].ID;
						trhtml+="</td><td>";
						trhtml+=data[i].scoreOne;
						trhtml+="</td><td>";
						trhtml+=data[i].scoreTwo;
						trhtml+="</td><td>";
						trhtml+=data[i].scoreThree;
						trhtml+="</td><td>";
						trhtml+=data[i].totalScore;
						
					}
					$("#schtab tbody").append(trhtml);
			},
			error:function(){
					alert("课程表数据请求失败！");
				}
		});
	}
	
		
		</script>
</body>
</html>
