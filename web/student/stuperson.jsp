<%@page import="com.hzz.xkxt.bean.College"%>
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

<title>个人信息维护</title>

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
<link rel="stylesheet" href="css/ui-elements/card.css">
</head>
<body>
	
	<jsp:include page="mainnavbar.jsp" flush="false"></jsp:include>

	<div class="page-content d-flex align-items-stretch">
		<%@ include file="sidenavbar.jsp"%>

		<div class="content-inner chart-cont" style="height: 100%">
			<div class="card form" id="form2"
				style="padding:10px 20px 20px 40px;">
				<div class="card-header">
					<h4>个人信息维护</h4>
				</div>
				<br>
				<%
					Student stu=(Student)(request.getSession().getAttribute("user"));
				%>
				<form action="StuphotoServlet" method="post">
					<div class="row" style="padding:10px 20px 20px 40px;">

						<div class="col-md-6">
							<div class="form-group row">
								<label for="example-text-input" style="width: 85px;"
									class=" col-form-label">个人账号：</label>
									<div class="col-9">
								<input class="form-control" type="text" value="<%=stu.getStudentID() %>" readonly="readonly"
										id="StudentID" name="StudentID">
										</div>
							</div>
							
							<div class="form-group row">
								<label for="example-text-input" style="width: 85px;"
									class=" col-form-label">真实姓名：</label>
								<div class="col-9">
									<input class="form-control" type="text" value="<%=stu.getStudentName() %>"
										id="StudentName" name="StudentName">
								</div>
							</div>
							<div class="form-group row">
								<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">所属班级:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="<%=stu.getClassmgr().getClassID() %>" readonly="readonly"
										id="ClassID" name="ClassID">
								</div>
							</div>
							<div class="form-group row">
							<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">生日:</label>
							<div class="col-9">
							<input class="form-control" type="text" value="<%=stu.getBirth() %>"
										id="Birth" name="Birth">
								</div>
							</div>
							<div class="form-group row">
							<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">性别:</label>
							<div class="col-9">
							<input class="form-control" type="text" value="<%=stu.getSex() %>"
										id="Sex" name="Sex">
								</div>
							</div>
							<div class="form-group row">
								<label for="example-search-input" style="width: 85px;"
									class=" col-form-label">备注:</label>
								<div class="col-9">
									<input class="form-control" type="text" value="<%=stu.getRemark() %>"
										id="Remark" name="Remark">
								</div>
							</div>
							<div class="form-group row">
								<input type="hidden" value="up" name="mg" id="mg">
								<button type="submit" class="btn btn-general btn-white">保存</button>

							</div>

						</div>
				</form>
				
				<form class="col-md-6" action="StuphotoServlet" enctype="multipart/form-data" method="post">
					
					
						<div class="form-group row">
							<label for="example-datetime-local-input" style="width:85px;"
								class=" col-form-label"></label>
							<div class="card hovercard">
								<div class="avatar">
									<img id="pic" alt="" src="studentphoto/<%=stu.getStudentID() %>.jpg" style="width:40mm;height: 52mm;">
								</div>
							</div>
						</div>
						<div class="form-group row">

							<div class="col-9">
								<div class="input-group image-preview">
									<input placeholder="" type="text" id="filename"
										class="form-control image-preview-filename"
										readonly="readonly"> <span class="input-group-btn">
										<div class="btn btn-default image-preview-input">
											<span class="glyphicon glyphicon-folder-open"></span> <span
												class="image-preview-input-title">打开</span> 
												<input type="file" accept=" image/jpg"
												name="picpath" onchange="ImgShowPreview(this)" />

										</div>

									</span> 
									&nbsp;<input type="submit" value="上传照片" />
								</div>
							</div>		
				</form>
			</div>
		</div>

	</div>


    <script src="js/jquery.min.js"></script>
    <script src="js/popper/popper.min.js"></script>
    <script src="js/tether.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/chart.min.js"></script>
    <script src="js/front.js"></script>
	<script type="text/javascript">
		function ImgShowPreview(source) {
			var file = source.files[0];
			if (window.FileReader) {
				var fr = new FileReader();
				console.log(fr);
				var pic = document.getElementById('pic');
				var filename = document.getElementById('filename');
				fr.onloadend = function(e) {
					pic.src = e.target.result;
				};
				fr.readAsDataURL(file);
				pic.style.display = 'block';
				filename.value = file.name;
			}
		}
	</script>
</body>

</html>