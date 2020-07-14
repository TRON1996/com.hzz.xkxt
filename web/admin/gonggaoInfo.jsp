
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'AdminReset.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- global stylesheets -->
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
	<%
		try {
			if (request.getSession().getAttribute("user") == null) {
				response.sendRedirect("login.jsp");
			}
	%>
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
	<div class="content-inner chart-cont" style="height: 100%">
		<div class="card form" id="form2" style="padding:10px 20px 20px 40px;">
			<div class="card-header">
				<h4>系统公告</h4>
			</div>
			<br>
			<div class="row" style="padding:10px 20px 20px 40px;">
				<div class="card form" id="form2"
					style="padding:20px 30px 20px 30px;margin-left: 5%;width: 70%">
					<table class="table table-hover" style="width:100%;" id="notice">
						<thead>
							<tr class="bg-info text-white">
								<th style="width: 8%">序号</th>
								<th style="width: 70%;">公告标题</th>
								<th>发布时间</th>
								<th style="width: 10%">操作</th>
							</tr>
						</thead>
						<tbody id="noticetable">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%
		} catch (NullPointerException e) {
			response.sendRedirect("login.jsp");
		}
	%>

</body>
<!--Global Javascript -->
<script src="js/jquery.min.js"></script>
<script src="js/popper/popper.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/chart.min.js"></script>
<script src="js/front.js"></script>

<script src="ckeditor/ckeditor.js"></script>
<script src="admin/js/AdminReleaseInfo.js"></script>
<script>
$(document).ready(function(){
	table();
})
function table(){
	var Sclass = [ "table-warning", "table-success", "table-danger",
	   			"table-info" ];
	$.ajax({
		url:"NoticeServlet?judge=se",
		type:"post",
		dataType:"json",
		success:function(data){
			$("#noticetable").html("");
			var Thtml="";
			for (var i = 0, t = 0; i < data.length; i++) {
				if (i % 2 == 1) {
					Thtml += "<tr class='" + Sclass[t]
							+ "'><th scope='row'>";
					Thtml += i + 1;
					Thtml += "</th><td>";
					Thtml += "<a href='NoticeServlet?judge=Nse&user=A&ID="
					Thtml += data[i].ID;
					Thtml += "'>";
					Thtml += data[i].noticeTitle;
					Thtml += "</a></td><td>";
					Thtml += data[i].releaseTime;
					Thtml += "</td><td><input type='button' value='删除' onclick=\"OffNotice(";
					Thtml += "'"+data[i].ID+"'";//中文传递异常 用''包起来处理
					Thtml += ")\"></td></tr>";
					t++;
					if (t > 3) {
						t = 0;
					}
				} else {
					Thtml += "<tr><th scope='row'>";
					Thtml += i + 1;
					Thtml += "</th><td>";
					Thtml += "<a href='NoticeServlet?judge=Nse&user=A&ID="
					Thtml += data[i].ID;
					Thtml += "'>";
					Thtml += data[i].noticeTitle;
					Thtml += "</a></td><td>";
					Thtml += data[i].releaseTime;
					Thtml += "</td><td><input type='button' value='删除' onclick=\"OffNotice(";
					Thtml += "'"+data[i].ID+"'";//中文传递异常 用''包起来处理
					Thtml += ")\"></td></tr>";
				}
			}
			$("#noticetable").append(Thtml);
		},
		error:function(){
			alert("公告获取失败");
		}
	})
}
function OffNotice(ID){
	if(confirm("确认删除本条公告")){
		location='NoticeServlet?judge=de&ID='+ID;
	}
	
}
</script>

</html>