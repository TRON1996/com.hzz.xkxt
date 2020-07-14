<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hzz.xkxt.bean.*" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<%
		try {
			if (request.getSession().getAttribute("user") == null) {
				response.sendRedirect("login.jsp");
			}
			;
	%>
	
<jsp:include page="mainnavbar.jsp"></jsp:include>

	  <div class="page-content d-flex align-items-stretch">

                <!--***** SIDE NAVBAR *****-->
                <jsp:include page="sidenavbar.jsp" />
              	<div class="content-inner form-cont">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card form" id="form1">
                                <div class="card-header">
                                    <h3><i class="fa fa-user-circle"></i>课程表</h3>
                                </div>
					<br>
					<div class="page-content d-flex align-items-stretch">
						<div class="content-inner chart-cont" style="margin-left: 5%">

							<!--***** CONTENT *****-->
							<div class="row">
								<table class="table table-hover" id="TimeTables">
									<thead>
										<tr class="bg-info text-white">
											<th style="width: 10%">节次</th>
											<th style="width: 18%">星期一</th>
											<th style="width: 18%">星期二</th>
											<th style="width: 18%">星期三</th>
											<th style="width: 18%">星期四</th>
											<th style="width: 18%">星期五</th>
										</tr>
									</thead>
									<tbody id="TimeTablesTable">
										<%
											String[] Classfestival = { "第一、二节", "第三、四节", "第五、六节", "第七、八节",
														"第九、十节", "第十一、十二节" };
												for (int i = 0; i < 6; i++) {
										%>
										<tr>
											<th><%=Classfestival[i] %></th>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
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
	
		<!--Global Javascript -->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper/popper.min.js"></script>
	<script src="js/tether.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/front.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	table();
});

function table() {
	$.ajax({
		url : "StuxkbServlet?mg=se",
		type : "post",
		dataType : "json",
		success : function(data) {
			var Thtml = "";
			for (var i = 0, t = 0; i < data.length; i++) {
				var Classfestival,ClassTime=0;
				switch (data[i].weekday){
				case '星期一': Weekday=0;break;
				case '星期二': Weekday=1;break;
				case '星期三': Weekday=2;break;
				case '星期四': Weekday=3;break;
				case '星期五': Weekday=4;break;
				}
				switch (data[i].classfestival){
				case '第一、二节': Classfestival=0;break;
				case '第三、四节': Classfestival=1;break;
				case '第五、六节': Classfestival=2;break;
				case '第七、八节': Classfestival=3;break;
				case '第九、十节': Classfestival=4;break;
				case '第十一、十二节': Classfestival=5;break;
				}
				var Thtml=$("#TimeTablesTable tr").eq(Classfestival).find("td").eq(Weekday).html();
				if(Thtml!=''){
				$("#TimeTablesTable tr").eq(Classfestival).find("td").eq(Weekday).html(Thtml+'</br>'+data[i].courseName+" "+data[i].classRoomName); 
			}else{
				$("#TimeTablesTable tr").eq(Classfestival).find("td").eq(Weekday).html(data[i].courseName+" "+data[i].classRoomName); 
			}}
			
		},

		error : function() {
			alert("课程表获取失败！");
		}
	})
}
	</script>
</body>

</html>
