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

            <title>学生信息管理</title>

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

                            <!--***** 学生信息管理 *****-->
                            <div class="card form" id="form1">
                                <div class="card-header">
                                    <h3><i class="fa fa-user-circle"></i>学生信息管理</h3>
                                </div>
                                <br>
                                <form action="StudentServlet" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                	所属分院：<select class="form-control" id="colselect" name="collegeID" onchange="loaddep()">																		
										
											</select>
                                            </div>
                                            <div class="form-group">
                                                	所属专业：<select class="form-control" id="proselect" name="ProfessionID" onchange="loadcla()">																		
										
											</select>
                                            </div>
                                            <div class="form-group">
                                                <label for="cont-number"></label> 
                                                	学号：<input type="text" class="form-control" id="StudentID" name="StudentID" aria-describedby="emailHelp" placeholder="请输入学生编号！">
                                            </div>
                                            <div class="form-group">
                                                <label for="cont-number"></label> 
                                                	性别：<select class="form-control" id="Sex" name="Sex">																		
															<option value="男">男</option>
															<option value="女">女</option>
												</select>
                                            </div>
                                            <div class="form-group">
                                                <label for="cont-number"></label> 
                                                	密码：<input type="text" class="form-control" id="Password" name="Password" aria-describedby="emailHelp" placeholder="请输入密码！">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                         	<div class="form-group">
                                               	 所属系部：<select class="form-control" id="depselect" name="departmentID" onchange="loadpro()">																		
										
											</select>
                                            </div>
                                            <div class="form-group">
                                               	 所属班级：<select class="form-control" id="claselect" name="ClassID" onchange="loadstu()">																		
										
											</select>
                                            </div>
                                             <div class="form-group">
                                                	姓名：<input type="text" class="form-control" id="StudentName" name="StudentName" aria-describedby="emailHelp" placeholder="请输入学生姓名！">
                                            </div>
                                            <div class="form-group">
        										出生日期：<input class="form-control" type="date" value="xxxx/xx/xx" id="Birth">
    										</div>
                                            <div class="form-group">
                                                <label for="cont-number"></label> 
                                                	备注：<input type="text" class="form-control" id="Remark" name="Remark" aria-describedby="emailHelp" placeholder="请输入备注信息！">
                                            </div>
                                           
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-general btn-blue mr-2">保存</button>
                                    <button type="reset" class="btn btn-general btn-white">重置</button>
                                    <input type="hidden" value="in" name="mg" id="mg">
                                </form>
                            </div>

                            <!--***** 表格 *****-->
                            <div class="card-header">
                                <h3>信息列表</h3>
                            </div>
                            <br>
                            <div class="row">
                                <table class="table table-hover" id="stutab">
                                    <thead>
                                        <tr class="bg-info text-white">
                                            <th>学号</th>
                                            <th>姓名</th>
                                            <th>性别</th>
                                            <th>出生日期</th>
                                            <th>所属分院</th>
                                            <th>所属系部</th>
                                            <th>所属专业</th>
                                            <th>所属班级</th>
                                            <th>密码</th>
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

	$(document).ready(loadcol());
		function loadcol() {
				$.ajax({
					url : "CollegeServlet?mg=se",
					type : "post",
					dataType : "json",
					success : function(data) {
						for (var i = 0; i < data.length; i++) {
					$("#colselect").append("<option value='"+data[i].collegeID+"'>"+ data[i].collegeName+ "</option>");
				}
					loaddep();
						},
					error : function() {
					alert("分院数据请求失败！");
						}
				});
						}
		
		function loaddep() {
		var id = $("#colselect").find("option:selected").val();
                 $.ajax({
						url : "DepartmentServlet?mg=se",
						type : "post",
						dataType : "json",
						data : {"collegeID" : id},
						success : function(data) {
						$(depselect).html("");
						$("#depselect option").remove();
							for (var i = 0; i < data.length; i++) {
								$("#depselect").append("<option value='"+data[i].departmentID+"'>"+ data[i].departmentName+ "</option>");
									}
								loadpro();
					},
						error : function() {
							alert("系部数据请求失败！");
										}
						});
						}
		
		function loadpro(){
		var id=$("#depselect").find("option:selected").val();
			$.ajax({
				url:"ProfessionServlet?mg=se",
				type:"post",
				dataType:"json",
				data:{"departmentID":id},
				success:function(data){
					$("#proselect option").remove();
					for(var i=0;i<data.length;i++){
						$("#proselect").append("<option value='"+data[i].professionID+"'>"+data[i].professionName+"</option>");
					}
					loadcla();
				},
				error:function(){
					alert("专业数据请求失败！");
				}
			});
		}
		
		function loadcla(){
		var id=$("#proselect").find("option:selected").val();
			$.ajax({
				url:"ClassServlet?mg=se",
				type:"post",
				dataType:"json",
				data:{"ProfessionID":id},
				success:function(data){
					$("#claselect option").remove();
					for(var i=0;i<data.length;i++){
						$("#claselect").append("<option value='"+data[i].classID+"'>"+data[i].className+"</option>");
					}
					loadstu();
				},
				error:function(){
					alert("班级数据请求失败！");
				}
			});
		}
		
		function loadstu(){
		     var id=$("#claselect").find("option:selected").val();    
			 $.ajax({
				url:"StudentServlet?mg=se",
				type:"post",
				dataType:"json",
				data:{"ClassID":id},
				success:function(data){
					$("#stutab tbody").html("");
				     var trhtml="";
				     for(var i=0;i<data.length;i++){
					    trhtml+="<tr><td>";
						trhtml+=data[i].studentID;
						trhtml+="</td><td>";
						trhtml+=data[i].studentName;
						trhtml+="</td><td>";
						trhtml+=data[i].sex;
						trhtml+="</td><td>";
						trhtml+=data[i].birth;
						trhtml+="</td><td>";
						trhtml+=$("#colselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=$("#depselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=$("#proselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=$("#claselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=data[i].password;
						trhtml+="</td><td>";
						trhtml+=data[i].remark;;
						trhtml+="</td><td><a href='javascript:void(0)' onclick='rowselect("+(i+1)+")'>选择</a>&nbsp;&nbsp;<a href='StudentServlet?mg=de&StudentID="
						trhtml+=data[i].studentID;
						trhtml+="'>删除</a></td></tr>";
					} 					
					$("#stutab tbody").append(trhtml);
				},
				error:function(){
					alert("学生数据加载失败！");
				}
			}); 
		}
		function rowselect(index) {
			var tb = document.getElementById("stutab");				
			$("#StudentID").val(tb.rows[index].cells[0].innerHTML);
			$("#StudentName").val(tb.rows[index].cells[1].innerHTML);
			$("#Sex").val(tb.rows[index].cells[2].innerHTML);
			$("#Birth").val(tb.rows[index].cells[3].innerHTML);
			$("#Password").val(tb.rows[index].cells[8].innerHTML);
			$("#Remark").val(tb.rows[index].cells[9].innerHTML);
			$("#StudentID").attr("readOnly",true);
			$("#mg").val("up");
		}
		function inputreset() {
			$("#StudentID").attr("readOnly", false);
		}
		
		</script>
        </body>

        </html> 