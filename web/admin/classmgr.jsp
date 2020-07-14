<%@page import="com.hzz.xkxt.bean.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
								<i class="fa fa-user-circle"></i>学院管理系统
							</h3>
						</div>
						<br>
						<form action="ClassServlet" method="post">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="name">学院名称</label>
										<div class="col-9">
											<select class="form-control" id="colselect" name="collegeID"
												onchange="loaddep()">

											</select>

										</div>
									</div>

									<div class="form-group">
										<label for="email">备注</label> <input type="text"
											class="form-control" id="Remark" name="Remark"
											placeholder="备注">
									</div>
								</div>
								<div class="form-group">
									<label for="name" style="width: 85px;"
									class="col-form-label">系部名称</label>
									<div class="col-9">
										<select class="form-control" id="depselect"
											name="departmentID" onchange="loadpro()">

										</select>

									</div>
								</div>
								<div class="form-group">
									<label for="example-text-input"style="width: 85px;"
									class="col-form-label">专业名称</label>
									<div class="col-9">
										<select class="form-control" id="proselect"name="ProfessionID" onchange="loadcla()" style="">

										</select>

									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="cont-number">班级编号</label> <input type="text"class="form-control" id="ClassID" name="ClassID"placeholder="班级编号">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="cont-number">班级名称</label> <input type="text"class="form-control" id="ClassName" name="ClassName"placeholder="班级名称">
									</div>
								</div>
							</div>
							<input type="hidden" value="in" id="mg" name="mg">
							<button type="submit" class="btn btn-general btn-blue mr-2" onclick="loadcla()">提交</button>
							<button type="reset" class="btn btn-general btn-white" onclick="inputreset()">取消</button>

						</form>
					</div>

					<!--***** PRODUCT INFO *****-->
					<div class="card form" id="form1">
						<div class="content-inner chart-cont">
							<!--***** CONTENT *****-->
							<div class="row">
								<table class="table table-hover" id="clatab">
									<thead>
										<tr class="bg-info text-white">
											<th>序号</th>
											<th>班级编号</th>
											<th>班级名称</th>
											<th>所属分院</th>
											<th>所属系部</th>
											<th>所属专业</th>
											<th>备注</th>
											<th>删除</th>
											<th>更新</th>
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
						$(document).ready(loadcol());
						function loadcol() {
							$.ajax({
								url : "CollegeServlet?mg=se",
								type : "post",
								dataType : "json",
								success : function(data) {
									for (var i = 0; i < data.length; i++) {
										$("#colselect").append("<option value='"+data[i].collegeID+"'>"+ data[i].collegeName+ "</option>");
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

							$.ajax({
										url : "DepartmentServlet?mg=se",
										type : "post",
										dataType : "json",
										data : {"collegeID" : id},
										success : function(data) {
											$(depselect).html("");
											$("#depselect option").remove();
											for (var i = 0; i < data.length; i++) {
												$("#depselect").append("<option value='"+data[i].departmentID+"'>"+ data[i].departmentName+ "</option>");
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

							$.ajax({
										url : "ProfessionServlet?mg=se",
										type : "post",
										dataType : "json",
										data : {"departmentID" : id},
										success : function(data) {

											$("#proselect option").remove();
											for (var i = 0; i < data.length; i++) {
												$("#proselect").append("<option value='"+data[i].professionID+"'>"+ data[i].professionName+ "</option>");
											}
											loadcla();
										},
										error : function() {
											alert("专业数据请求失败！");
										}
									});

							function loadcla() {
								var id = $("#proselect").find("option:selected").val();
								$.ajax({
											url : "ClassServlet?mg=se",
											type : "post",
											dataType : "json",
											data : {"ProfessionID" : id},
											success : function(data) {
												var trhtml = "";
												$("#clatab tbody").html("");
												for (var i = 0; i < data.length; i++) {
													trhtml += "<tr><th scope='row'>";
													trhtml += i + 1;
													trhtml += "</th><td>";
													trhtml += data[i].classID;
													trhtml += "</td><td>";
													trhtml += data[i].className;
													trhtml += "</td><td>";
													trhtml += $("#colselect").find("option:selected").text();
													trhtml += "</td><td>";
													trhtml += $("#depselect").find("option:selected").text();
													trhtml += "</td><td>";
													trhtml += $("#proselect").find("option:selected").text();
													trhtml += "</td><td>";
													trhtml += data[i].remark;
													trhtml += "</td><td><a href='ClassServlet?mg=de&ClassID="
													trhtml += data[i].classID;
													trhtml += "'>删除</a></td><td><a href='javascript:void(0)' onclick='rowselect(";
													trhtml += (i + 1);
													trhtml += ")'>选择</a></td></tr>";
												}
												$("#clatab tbody").append(trhtml);
											},
											error : function() {
												alert("班级数据请求失败！");
											}
										});
							}
						}
						function rowselect(index) {
							var tb = document.getElementById("clatab");
							$("#ClassID").val(tb.rows[index].cells[1].innerHTML);
							$("#ClassName").val(tb.rows[index].cells[2].innerHTML);
							$("#Remark").val(tb.rows[index].cells[6].innerHTML);
							$("#ClassID").attr("readOnly", true);
							$("#mg").val("up");
						}
						function inputreset() {
							$("#ClassID").attr("readOnly", false);
						}
					</script>
</body>
</html>