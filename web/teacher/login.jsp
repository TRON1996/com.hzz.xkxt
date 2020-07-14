<%@page import="com.hzz.xkxt.util.util"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">

    <title>Bootstrap Admin Template </title>
    <link rel="shortcut icon" href="img/favicon.ico">
    
    <!-- global stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/font-icon-style.css">
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">

    <!-- Core stylesheets -->
    <link rel="stylesheet" href="css/pages/login.css">
  </head>
  
  <body>
 
<!--====================================================
                        PAGE CONTENT
======================================================--> 
      <section class="hero-area">
        <div class="overlay"></div>
        <div class="container">
          <div class="row">
            <div class="col-md-12 ">
                <div class="contact-h-cont">
                  <h3 class="text-center"><img src="img/logo.png" class="img-fluid" alt=""></h3><br>
                  <form action="TeacherServlet" method="post">
                    <div class="form-group">
                      <label for="TeacherID">TeacherID</label>
                      <input type="text" class="form-control" id="TeacherID" name="TeacherID" placeholder="请输入账号"> 
                    </div>  
                    <div class="form-group">
                      <label for="exampleInputEmail1">Password</label>
                      <input class="form-control" type="password" value="123456" id="Password" name="Password"> 
                    </div>   
                    <input type="hidden"  name="mg"  value="login">
                   <button class="btn btn-general btn-blue" role="button"><i fa fa-right-arrow></i>Login</button>
                  <% request.setCharacterEncoding("utf-8"); %>
                   <%=util.convertNull(request.getAttribute("msg"))%>
                  </form>
                </div>
            </div>
          </div>  
        </div>
      </section>
      
    <!--Global Javascript -->
    <script src="js/jquery.min.js"></script>
    <script src="js/tether.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
