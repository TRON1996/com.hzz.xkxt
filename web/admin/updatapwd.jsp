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

	<title>账户信息管理</title>

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
    <%if(request.getSession().getAttribute("user")==null) {
      response.sendRedirect("login.jsp");
    } 
     %>
	<div class="page-content d-flex align-items-stretch">
		<%@ include file="sidenavbar.jsp"%>


		<div class="content-inner chart-cont">
			<div class="card form" id="form2" style="padding:10px 20px 20px 40px;">
				<div class="card-header">
					<h4>修改密码</h4>
				</div>
				<br>
				<% Admin ad=(Admin)(request.getSession().getAttribute("user"));%>
				<form action="UpdatapwdServlet" method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-text-input" style="width: 85px;"
									class=" col-form-label">用户名：</label>
								<div class="col-9">
									<input class="form-control" type="text" id="AdminID" name="AdminID" value="<%=ad.getAdminID() %>" readonly="readonly">
								</div>
							</div>
							<div class="form-group row">
								<label for="example-datetime-local-input" style="width: 85px;"
									class=" col-form-label">原密码:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="" id="oldpwd"
										name="oldpwd">
								</div>
							</div>
						</div>
						<div class="col-md-6">
						<div class="form-group row">
						    <label for="example-datetime-local-input" style="width: 85px;"
									class=" col-form-label">新密码:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="" id="newpwd"
										name="newpwd">
								</div>
							
								</div>
							<div class="form-group row">
								<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">确认密码:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="" id="newpwd1" name="newpwd1">
								</div>
							</div>
							
							
						</div>
						<div class="col-md-6">
							<input type="hidden" value="reset" name="mg" id="mg">
							<button type="submit" class="btn btn-general btn-white" onclick="tableload()">保存</button>
							<button type="reset" class="btn btn-general btn-white" onclick="inputreset()">重置</button>
						</div>
					</div>
				</form>
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
  </body>
</html>
