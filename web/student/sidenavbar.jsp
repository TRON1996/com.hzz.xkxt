<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hzz.xkxt.bean.*" %>
<%@page import="com.hzz.xkxt.util.util"%>

<html>
<head>

</head>

<body>
	<!--***** SIDE NAVBAR *****-->
	 
	
	
	<%
	 				Student thestu=(Student)(request.getSession().getAttribute("user"));
	 			%>
   <nav class="side-navbar">
		<div class="sidebar-header d-flex align-items-center">
			<div class="avatar">
				<img src="studentphoto/<%=thestu.getStudentID()%>.jpg" class="img-fluid rounded-circle">
			</div>
			<div class="title">
				<h1 class="h4">
				<%=thestu.getStudentName()%> </h1>
			</div>
		</div>
		<hr>
		<!-- Sidebar Navidation Menus-->
		<ul class="list-unstyled">
			<li><a href="#home" aria-expanded="false" data-toggle="collapse">
					<i class="icon-interface-windows"></i>个人信息维护
			</a>
				<ul id="home" class="collapse list-unstyled">
					<li><a href="student/stuperson.jsp">个人信息修改</a></li>
					<li><a href="student/stupwd.jsp">账户密码修改</a></li>
					
				</ul></li>
				</ul>
        <ul class="list-unstyled">
			<li><a href="#kc" aria-expanded="false" data-toggle="collapse">
					<i class="icon-interface-windows"></i>操作
			</a>
				<ul id="kc" class="collapse list-unstyled">
					<li><a href="student/stuxk.jsp">选课</a></li>
					<li><a href="student/stuxkb.jsp">已选选课</a></li>
					<li><a href="student/stuSchedule.jsp">课程表</a></li>
					<li><a href="student/grade.jsp">成绩查看</a></li>
				
				</ul></li>
				</ul>  
	</nav>
	
</body>
</html>