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

                           
                            
                            
                           <div class="card form" id="form2"style="padding:20px 30px 20px 30px;">
				<div class="row">
					
					<table class="table table-hover" style="width:1300px;" id="coursetab">
						<thead>
                                      <tr class="bg-info text-white">
                                            <th>序号</th>
                                            <th>课程名称</th>
											<th>老师名称</th>
											<th>星期</th>
											<th>节次</th>
											<th>教室</th>
											<th>学分</th>
											<th>课程号</th>
											<th>所属专业</th>										
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
		$(document).ready(loadxk());
		
	
		function loadxk(){
		        $.ajax({
				url:"StuXKServlet?mg=se",
				type:"post",
				dataType:"json",
				success:function(data){
				/* alert(data) */
					$("#coursetab tbody").html("");
				     var trhtml="";
				     for(var i=0;i<data.length;i++){
					    trhtml+="<tr><th scope='row'>";    
						trhtml+=i+1;
						trhtml+="</th><td>";
						trhtml+=data[i].courseName;
						trhtml+="</td><td>";
						trhtml+=data[i].teacherName;					
						trhtml+="</td><td>";
						trhtml+=data[i].weekday;
						trhtml+="</td><td>";
						trhtml+=data[i].classfestival
						trhtml+="</td><td>";
						trhtml+=data[i].classRoomName;
						trhtml+="</td><td>";
						trhtml+=data[i].crediy;
						trhtml+="</td><td>";
						trhtml+=data[i].teachTaskID;
						trhtml+="</td><td>";
						trhtml+=data[i].professionName;
						trhtml+="</td><td><a href='StuXKServlet?mg=in&teachTaskID="+data[i].teachTaskID+"' >选择</a>";
						trhtml+="</td></tr>";
					} 				
					$("#coursetab tbody").append(trhtml);
				},
				error:function(){
					 alert("课程数据请求失败！"); 
				}
			}); 
		}
		</script>
</body>

</html>