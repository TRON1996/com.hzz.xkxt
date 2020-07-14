<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="ContentType" content="text/html,charset=utf-8">
</head>

<body>
	<!--====================================================
                         MAIN NAVBAR
======================================================-->
	<header class="header">
		<nav class="navbar navbar-expand-lg ">
			<div class="search-box">
				<button class="dismiss">
					<i class="icon-close"></i>
				</button>
				<form id="searchForm" action="#" role="search">
					<input type="search" placeholder="Search Now" class="form-control">
				</form>
			</div>
			<div class="container-fluid ">
				<div
					class="navbar-holder d-flex align-items-center justify-content-between">
					<div class="navbar-header">
						<a href="index.html" class="navbar-brand">
							<div class="brand-text brand-big hidden-lg-down" >
							    <h4>学生选课系统</h4></div>
							<div class="brand-text brand-small">
								<img src="img/logo-icon.png" alt="Logo" class="img-fluid">
							</div>
						</a> 
						<a id="toggle-btn" href="#" class="menu-btn active"> <span></span>
							<span></span> <span></span>
						</a>
						<a href="#" class="navbar-brand" >
							
								<h6>---管理端---</h6>
							
						</a>
					</div>
				</div>
				<ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
						<!-- Expand-->
						<li class="nav-item d-flex align-items-center full_scr_exp"><a class="nav-link" href="#">2019年4月10日</a></li>
						<!-- Search-->
						<li class="nav-item d-flex align-items-center"><a  class="nav-link" href="stupersonMgrServlet?mg=exit">退出</a></li>
				</ul>
			</div>
		</nav>
	</header>
</body>
</html>
