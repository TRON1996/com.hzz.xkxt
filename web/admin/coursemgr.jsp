<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hzz.xkxt.bean.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">

<title>教师信息管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
	<!--====================================================
                     MAIN NAVBAR
======================================================-->
	<jsp:include page="mainnavbar.jsp" />

	<!--====================================================
                    PAGE CONTENT
======================================================-->
	<div class="page-content d-flex align-items-stretch">

		<!--***** SIDE NAVBAR *****-->
		<jsp:include page="sidenavbar.jsp" />

		<div class="content-inner form-cont">
			<div class="row">
				<div class="col-md-12">

					<!--*****课程信息管理 *****-->
					<div class="card form" id="form1">
						<div class="card-header">
							<h3>
								<i class="fa fa-user-circle"></i>课程信息管理
							</h3>
						</div>
						<br>
						<form action="CourseServlet" method="post">
							<div class="row">

								<div class="col-md-6">
									<div class="form-group">
										所属学院：<select class="form-control" id="colselect"
											name="collegeID" onchange="loaddep()">

										</select>
									</div>
									<div class="form-group">
										所属系部：<select class="form-control" id="depselect"
											name="departmentID" onchange="loadpro()">

										</select>
									</div>
									<div class="form-group">
										所属专业：<select class="form-control" id="proselect"
											name="ProfessionID" onchange="loadcou()">

										</select>
									</div>
									<div class="form-group">
										<label for="cont-number"></label> 课程号：<input type="text"
											class="form-control" id="CourseID" name="CourseID"
											aria-describedby="emailHelp" placeholder="请输入课程号！">
									</div>

									<div class="form-group">
										课程名：<input type="text" class="form-control" id="CourseName"
											name="CourseName" aria-describedby="emailHelp"
											placeholder="请输入课程名！">
									</div>

								</div>


								<div class="form-group">
									<div class="form-group">
										<label for="cont-number"></label> 课程时段：<input type="text"
											class="form-control" id="CurriculumTime"
											name="CurriculumTime" aria-describedby="emailHelp"
											placeholder="请输入课程时段！">
									</div>
									<div class="form-group">
										<label for="cont-number"></label> 课程时长：<input type="text"
											class="form-control" id="StudyTime" name="StudyTime"
											aria-describedby="emailHelp" placeholder="请输入课程时长！">
									</div>
									<div class="form-group">
										<label for="cont-number"></label> 课程学分：<input type="text"
											class="form-control" id="Crediy" name="Crediy"
											aria-describedby="emailHelp" placeholder="请输入课程学分！">
									</div>

									<label for="cont-number"></label> 备注：<input type="text"
										class="form-control" id="Remark" name="Remark"
										aria-describedby="emailHelp" placeholder="请输入备注信息！">
								</div>


							</div>
							<button type="submit" class="btn btn-general btn-blue mr-2">保存</button>
							<button type="reset" class="btn btn-general btn-white">重置</button>
							<input type="hidden" value="in" name="mg" id="mg">
					</div>

					</form>
				</div>

				<!--***** 表格 *****-->


				<div class="card form" id="form2"
					style="padding:20px 30px 20px 30px;">
					<div class="row">

						<table class="table table-hover" style="width:1300px;" id="coutab">
							<thead>
								<tr class="bg-info text-white">
									<th style="width:5%">序号</th>
									<th style="width: 8%">所属学院</th>
									<th style="width: 8%">所属系部</th>
									<th style="width: 8%">所属专业</th>
									<th style="width: 8%">课程号</th>
									<th style="width: 8%">课程名称</th>
									<th style="width: 8%">上课时长</th>
									<th style="width: 8%">上课学分</th>
									<th style="width: 10%">课程时段</th>
									<th style="width: 10%">备注</th>
									<th style="width:  10%">操作</th>
								</tr>
							</thead>
							<tbody>


							</tbody>
						</table>
					</div>

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
		$(document).ready(loadcol());
		function loadcol() {
			$.ajax({
				url : "CollegeServlet?mg=se",
				type : "post",
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						$("#colselect").append(
								"<option value='"+data[i].collegeID+"'>"
										+ data[i].collegeName + "</option>");
					}
					loaddep();
				},
				error : function() {
					alert("分院数据请求失败！");
				}

			});
		}
		function loaddep() {
			var id = $("#colselect").find("option:selected").val();

			$
					.ajax({
						url : "DepartmentServlet?mg=se",
						type : "post",
						dataType : "json",
						data : {
							"collegeID" : id
						},
						success : function(data) {
							$(depselect).html("");
							$("#depselect option").remove();
							for (var i = 0; i < data.length; i++) {
								$("#depselect").append(
										"<option value='"+data[i].departmentID+"'>"
												+ data[i].departmentName
												+ "</option>");
							}
							loadpro();
						},
						error : function() {
							alert("系部数据请求失败！");
						}
					});
		}
		function loadpro() {
			var id = $("#depselect").find("option:selected").val();

			$
					.ajax({
						url : "ProfessionServlet?mg=se",
						type : "post",
						dataType : "json",
						data : {
							"departmentID" : id
						},
						success : function(data) {

							$("#proselect option").remove();
							for (var i = 0; i < data.length; i++) {
								$("#proselect").append(
										"<option value='"+data[i].professionID+"'>"
												+ data[i].professionName
												+ "</option>");
							}
							loadcou();
						},
						error : function() {
							alert("专业数据请求失败！");
						}
					});
		}

		function loadcou() {
			var id = $("#proselect").find("option:selected").val();

			$
					.ajax({
						url : "CourseServlet?mg=Cse",
						type : "post",
						dataType : "json",
						data : {
							"ProfessionID" : id
						},
						success : function(data) {
							$("#coutab tbody").html("");
							var trhtml = "";
							for (var i = 0; i < data.length; i++) {
								trhtml += "<tr><th scope='row'>";
								trhtml += i + 1;
								trhtml += "</th><td>";
								trhtml += $("#colselect").find(
										"option:selected").text();
								trhtml += "</td><td>";
								trhtml += $("#depselect").find(
										"option:selected").text();
								trhtml += "</td><td>";
								trhtml += $("#proselect").find(
										"option:selected").text();
								trhtml += "</td><td>";
								trhtml += data[i].courseID;
								trhtml += "</td><td>";
								trhtml += data[i].courseName;
								trhtml += "</td><td>";
								trhtml += data[i].studyTime;
								trhtml += "</td><td>";
								trhtml += data[i].crediy;
								trhtml += "</td><td>";
								trhtml += data[i].curriculumTime;
								trhtml += "</td><td>"
								trhtml += data[i].remark;
								trhtml += "</td><td><a href='javascript:void(0)' onclick='rowselect("
										+ (i + 1)
										+ ")'>选择</a>&nbsp;&nbsp;<a href='CourseServlet?mg=de&CourseID="
								trhtml += data[i].courseID;
								trhtml += "'>删除</a></td></tr>";

							}
							$("#coutab tbody").append(trhtml);
						},
						error : function() {
							alert("课程数据加载失败！");
						}
					});
		}
		function rowselect(index) {
			var tb = document.getElementById("coutab");
			
			$("#CourseID").val(tb.rows[index].cells[4].innerHTML);
			$("#CourseName").val(tb.rows[index].cells[5].innerHTML);
			$("#StudyTime").val(tb.rows[index].cells[6].innerHTML);
			$("#Crediy").val(tb.rows[index].cells[7].innerHTML);
			$("#CurriculumTime").val(tb.rows[index].cells[8].innerHTML);
			$("#Remark").val(tb.rows[index].cells[9].innerHTML);
			$("#CourseID").attr("readOnly", true);
			$("#mg").val("up");
		}
		function inputreset() {
			$("#CourseID").attr("readOnly", false);
		}
	</script>
</body>

</html>
