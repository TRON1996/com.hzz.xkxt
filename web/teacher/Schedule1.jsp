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
                <jsp:include page="teasidenavbar.jsp" />
              	<div class="content-inner form-cont">
                    <div class="row">
                        <div class="col-md-12">

                            <!--***** 学生信息管理 *****-->
                            <div class="card form" id="form1">
                                <div class="card-header">
                                    <h3><i class="fa fa-user-circle"></i>课程表配置</h3>
                                </div>
                                <br>
						<form action="ScheduleMgrServlet1" method="post">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group row">
								课程：
								<div class="col-9">
									<select class="form-control" id="teaselect" name="TeachtaskID">
									</select>
								</div>
								</div>
									<div class="form-group">
                                              教室名称：<select class="form-control" id="cnameselect" name="ClassRoomName">																					
											</select>
                                      </div>			
                                       <!-- <div class="form-group">
                                              教室类型：<select class="form-control" id="cleixingselect" name="ClassRoomType" onchange="loadcl()">																					
											</select>
                                      </div> -->															
							    <div class="form-group">
                                        <label for="cont-number"></label> 
                                              星期：<select class="form-control" id="Weekday" name="Weekday">																		
												<option value="星期一">星期一</option>
												<option value="星期二">星期二</option>
												<option value="星期三">星期三</option>
												<option value="星期四">星期四</option>
												<option value="星期五">星期五</option>

											</select>
                                        </div>
                                        </div>
								 <div class="col-md-6">
                                             <div class="form-group">
                                                	上课时长：<input type="text" class="form-control" id="ClassTime" name="ClassTime" >
                                            </div>
                                               <div class="form-group">
                                        <label for="cont-number"></label> 
                                              节次：<select class="form-control" id="Classfestival" name="Classfestival">																		
												<option value="第一、二节">第一、二节</option>
												<option value="第三、四节">第三、四节</option>
												<option value="第五、六节">第五、六节</option>
												<option value="第七、八节">第七、八节</option>
												<option value="第九、十节">第九、十节</option>
                                                <option value="第十一、十二节">第十一、十二节</option>
											</select>
                                        </div>
                                        <div class="col-md-6">
							
                                           <div class="form-group">
                                                <label for="cont-number"></label> 
                                                备注：<input type="text" class="form-control" id="Remark" name="Remark" aria-describedby="emailHelp" placeholder="请输入备注！">
                                            </div>
                                            </div>
                                           
                                        </div>
                                         <button type="submit" class="btn btn-general btn-blue mr-2">配置</button>
                                         <button type="reset" class="btn btn-general btn-white">重置</button>
                                         <input type="hidden" value="in" name="mg" id="mg">
                                    </div>
                                   
                                </form>
                            </div>

                            <!--***** 表格 *****-->
                            
                            
                           <div class="card form" id="form2"style="padding:20px 30px 20px 30px;">
				<div class="row">
					
					<table class="table table-hover" style="width:1500px;" id="schtab">
						<thead>
                                        <tr class="bg-info text-white">
                                            <th>序号</th>
											<th>课程名称</th>
											<th>课长</th>
											<th>节次</th>
											<th>教室</th>
											<th>星期</th>
											<th>备注</th>
											<th>操作</th>											
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
	$(document).ready(loadtea());
		function loadtea() {
			$.ajax({
				url:"TeachTaskServlet?mg=Sse",
				type:"post",
				dataType:"json",
			    success:function(data){
				for(var i=0;i<data.length;i++){
					$("#teaselect").append("<option value='"+data[i].ID+"'>"+data[i].course.courseName+"</option>");
				}
				loadcn();
			},
			error:function(){
					alert("课程名称数据请求失败！");
				}
			
		});
	}
	function loadcn(){		
	   
			 $.ajax({
				url:"classroomMgrServlet?mg=se",
				type:"post",
				dataType:"json",
				
				success:function(data){
				 
				$(cnameselect).html("");
					for(var i=0;i<data.length;i++){
					$("#cnameselect").append("<option value='"+data[i].classRoomID+"'>"+data[i].classRoomName+"</option>");
				}
				 loadsch(); 
				},
				error:function(){
					alert("教室名称数据请求失败！");
				}
				});
	}

	function loadsch(){
		$.ajax({
			url:"ScheduleServlet1?mg=se",
			type:"post",
			dataType:"json",
			success:function(data){
				$("#schtab tbody").html("");
				var trhtml="";
				for(var i=0;i<data.length;i++){
					    trhtml+="<tr><th scope='row'>";    
						trhtml+=i+1;
						trhtml+="</th><td>";
						trhtml+=data[i].courseName;
						trhtml+="</td><td>";
						trhtml+=data[i].classTime;
						trhtml+="</td><td>";
						trhtml+=data[i].classfestival;
						trhtml+="</td><td>";
						trhtml+=$("#cnameselect").find("option:selected").text();				
						trhtml+="</td><td>";
						trhtml+=data[i].weekday;
						trhtml+="</td><td>";
						trhtml+=data[i].remark;
						trhtml+="</td><td><a href='ScheduleServlet1?mg=de&TeachtaskID=";
						trhtml+=data[i].teachTaskID;
						trhtml+="'>删除</a></td></tr>";
					}
					$("#schtab tbody").append(trhtml);
			},
			error:function(){
					alert("课程表数据请求失败！");
				}
		});
	}

		</script>
</body>
</html>
