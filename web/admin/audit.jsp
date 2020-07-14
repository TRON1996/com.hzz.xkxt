<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hzz.xkxt.bean.*" %>
<%@ page import="com.hzz.xkxt.util.*" %>
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
            <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet" href="css/font-icon-style.css">
            <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">

            <!-- Core stylesheets -->
            <link rel="stylesheet" href="css/form.css">

        </head>

        <body>
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

                <div class="content-inner form-cont">
                    <div class="row">
                        <div class="col-md-12">

                            <!--*****课程信息管理 *****-->
                            <div class="card form" id="form1">
                                <div class="card-header">
                                    <h3><i class="fa fa-user-circle"></i>课程信息管理</h3>
                                
                            
                            
                           <div class="card form" id="form2"style="padding:20px 30px 20px 30px;">
				<div class="row">
					
					<table class="table table-hover" style="width:1300px;" id="coursetab">
						<thead>
                                      <tr class="bg-info text-white">
                                            <th>序号</th>
                                            <th>老师名称</th>
											<th>课程名称</th>
											<th>开课ID</th>
											<th>审核状态</th>
											<th>审核</th>
											<th>审核操作</th>
											<th>保存</th>
                                       </tr>
						</thead>
						<tbody>
							

						</tbody>
					</table>
				</div>

                        </div>
                    </div>
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
		$(document).ready(junge());
	
		
	function tz(TeachTaskID,index){
	var ind="#Auditing"+index;
	var tg=$(ind).val();
	location.href="TeachTaskServlet?mg=up&TeachTaskID="+TeachTaskID+"&Auditing="+tg;
	}
	function junge(){
		$.ajax({
			url:"AuditServlet?mg=se",
			type:"post",
			dataType:"json",
			success:function(data){
			/*     alert(data) */ 
				$("#coursetab tbody").html("");
				var trhtml="";
				for(var i=0;i<data.length;i++){
					    trhtml+="<tr><th scope='row'>";    
						trhtml+=i+1;
						trhtml+="</td><td>";
						trhtml+=data[i].teacherName;
						trhtml+="</td><td>";
						trhtml+=data[i].courseName;
						trhtml+="</td><td>";
						trhtml+=data[i].teachTaskID;
						trhtml+="</td><td>";
						trhtml+=data[i].auditing;
						trhtml+="</td><td>";
						trhtml+="<select class='form-control' id='Auditing"+i+"' name='Auditing'><option value='通过'>通过</option><option value='未通过'>未通过</option></select>"
						/* trhtml+="</td><td><a href='TeachTaskMgrServlet?mg=up&TeachTaskID="+data[i].teachTaskID+")'>审核</a></td>&nbsp;&nbsp;</td><td><a href='AuditMgrServlet?mg=in&TeachTaskID="; */
						trhtml+="</td><td><button type='button' onclick='tz("+data[i].teachTaskID+","+i+")'>审核</button></td>&nbsp;&nbsp;</td><td><a href='AuditServlet?mg=in&TeachTaskID=";
						trhtml+=data[i].teachTaskID;
						trhtml+="'>保存</a></td></tr>";						
					}
					$("#coursetab tbody").append(trhtml);
			},
			error:function(){
					alert("error");
				}
		});
	}
		</script>
</body>

</html>