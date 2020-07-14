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
		<jsp:include page="teasidenavbar.jsp" flush="false"></jsp:include>

		<div class="content-inner chart-cont">

			<!--***** FORM LAYOUTS *****-->
			<div class="row">
				<div class="col-md-12">

					<!--***** USER INFO *****-->
					<div class="card form" id="form1">
						<div class="card-header">
							<h3>
								<i class="fa fa-user-circle"></i>评分
							</h3>
						</div>
						<br>
						<form action="GradeServlet" method="POST">
							<div class="row">
								
									<div class="col-md-6">
										<div class="form-group">
											<label for="cont-number">学生姓名</label> <input type="text"
												class="form-control" id="StudentName" name="StudentName">
										</div>
									</div>
                                     <div class="col-md-6">
										<div class="form-group">
											<label for="cont-number">学号</label> <input type="text"
												class="form-control" id="StudentID" name="StudentID">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="cont-number">课程号</label> <input type="text"
												class="form-control" id="TeachTaskID" name="TeachTaskID">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="cont-number">考勤分</label> <input type="text"
												class="form-control" id="ScoreOne" name="ScoreOne">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="cont-number">课堂表现分</label> <input type="text"
												class="form-control" id="ScoreTwo" name="ScoreTwo">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="cont-number">期末成绩</label> <input type="text"
												class="form-control" id="ScoreThree" name="ScoreThree">
										</div>
									</div>
									
								

							</div>
			
			<input type="hidden" value="up" id="mg" name="mg">
			<button type="submit" class="btn btn-general btn-blue mr-2">提交</button>
			<button type="reset" class="btn btn-general btn-white">取消</button>

			</form>
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
											<th>操作</th>
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
						trhtml+="</td><td><a href='javascript:void(0)' onclick='rowselect(";
						trhtml+=(i+1);
						trhtml+=")'>选择</a></td></tr>";
					}
					$("#schtab tbody").append(trhtml);
			},
			error:function(){
					alert("课程表数据请求失败！");
				}
		});
	}
	 function rowselect(index) {
			var tb = document.getElementById("schtab");				
			$("#StudentName").val(tb.rows[index].cells[2].innerHTML);
			$("#StudentID").val(tb.rows[index].cells[3].innerHTML);
			$("#ScoreOne").val(tb.rows[index].cells[7].innerHTML);
            $("#ScoreTwo").val(tb.rows[index].cells[8].innerHTML);
			$("#ScoreThree").val(tb.rows[index].cells[9].innerHTML);
		
			$("#TeachTaskID").val(tb.rows[index].cells[6].innerHTML);
			$("#StudentID").attr("readOnly",true);		
			$("#StudentName").attr("readOnly",true);
			$("#mg").val("up");
		}
		
		</script>
</body>
</html>
