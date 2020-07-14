<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hzz.xkxt.bean.*"%>
<%@page import="com.hzz.xkxt.util.util"%>

<html>
<head>

</head>

<body>
	<!--***** SIDE NAVBAR *****-->
	<%
		Admin theadm = (Admin) (request.getSession().getAttribute("user"));
	%>
	<nav class="side-navbar">
		<div class="sidebar-header d-flex align-items-center">
			<div class="avatar">
				<img src="adminphoto/<%=theadm.getAdminID()%>.jpg"
					class="img-fluid rounded-circle">
			</div>
			<div class="title">
				<h1 class="h4">
					<%=theadm.getAdminName()%>
				</h1>
			</div>
		</div>
		<hr>
		<!-- Sidebar Navidation Menus-->
		<ul class="list-unstyled">
			<li><a href="#apps" aria-expanded="false" data-toggle="collapse">
					<i class="icon-home"></i>基础信息管理
			</a>
				<ul id="apps" class="collapse list-unstyled">
					<li><a href="admin/collegemgr.jsp">分院信息管理</a></li>
					<li><a href="admin/departmentmgr.jsp">系部信息管理</a></li>
					<li><a href="admin/professionmgr.jsp">专业信息管理</a></li>
					<li><a href="admin/classmgr.jsp">班级信息管理</a></li>
					<!-- <li><a href="admin/teachermgr.jsp">教师信息管理</a></li>
					<li><a href="admin/studentmgr.jsp">学生信息管理</a></li> -->
					<li><a href="admin/coursemgr.jsp">课程信息管理</a></li>
					<li><a href="admin/classmgr.jsp">教室信息管理</a></li>
				</ul></li>


			<%
				Admin admin = (Admin) request.getSession().getAttribute("user");
				if (admin != null) {
					if (admin.getRole().equals("超级管理员")) {
			%>
			<li class="active"><a href="#forms" aria-expanded="false"
				data-toggle="collapse"> <i class="fa fa-building-o"></i>账户信息管理
			</a>
				<ul id="forms" class="collapse list-unstyled">
					<li><a href="admin/adminmgr.jsp">管理员账户管理</a></li>
					<li><a href="admin/teachermgr.jsp">教师信息管理</a></li>
					<li><a href="admin/studentmgr.jsp">学生信息管理</a></li>
				</ul>
			<%
				}
				}
			%>
			<li>
			<a href="#pages" aria-expanded="false" data-toggle="collapse"> <i
				class="fa fa-file-o"></i>个人信息维护
			</a>
			<ul id="pages" class="collapse list-unstyled">
				<li><a href="admin/personalinfo.jsp">个人信息修改</a></li>
				<li><a href="admin/updatapwd.jsp">账户密码修改</a></li>
			</ul>
			</li>
			</li><li><a href="#elements" aria-expanded="false"
				data-toggle="collapse"> <i class="fa fa-globe"></i>基础操作
			</a>
				<ul id="elements" class="collapse list-unstyled">
					<li><a href="admin/AdminRelease.jsp">公告发布</a></li>
					<li><a href="admin/AdminReleaseInfo.jsp">公告管理</a></li>
					<li><a href="admin/AdminReleaseShow.jsp">最新公告</a></li>
					<li><a href="admin/audit.jsp">审核课程</a></li>

				</ul></li>
		</ul>
	</nav>

</body>
</html>