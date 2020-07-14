<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hzz.xkxt.bean.*" %>
<%@page import="com.hzz.xkxt.util.util"%>

<html>
<head>

</head>

<body>
	<!--***** SIDE NAVBAR *****-->
	<%
		Teacher thetea=(Teacher)(request.getSession().getAttribute("user"));
	%>
   <nav class="side-navbar">
		<div class="sidebar-header d-flex align-items-center">
			<div class="avatar">
				<img src="teacherphoto/<%=thetea.getTeacherID() %>.jpg" class="img-fluid rounded-circle">
					
			</div>
			<div class="title">
				<h1 class="h4">
				  <%=thetea.getTeacherName() %> </h1>
			</div>
		</div>
		<hr>
		<!-- Sidebar Navidation Menus-->
		<ul class="list-unstyled">
			<li><a href="#home" aria-expanded="false" data-toggle="collapse">
					<i class="icon-interface-windows"></i>个人信息维护
			</a>
				<ul id="home" class="collapse list-unstyled">
					<li><a href="teacher/teaperson.jsp">个人信息修改</a></li>
					<li><a href="teacher/teapwd.jsp">账户密码修改</a></li>
					
				</ul></li>
				
				</ul>
				<li><a href="#kk" aria-expanded="false" data-toggle="collapse">
					<i class="icon-interface-windows"></i>操作
		            </a>
				<ul id="kk" class="collapse list-unstyled">
					<li><a href="teacher/teachtask.jsp">开课</a></li>
					<li><a href="teacher/Schedule1.jsp">课表配置</a></li>
					<li><a href="teacher/teaSchedule.jsp">课程表</a></li>
					<li><a href="teacher/teaSelect.jsp">查询</a></li>
					<li><a href="teacher/grade.jsp">成绩评分</a></li>			
				
				</ul></li>

	</nav>
	
</body>
</html>