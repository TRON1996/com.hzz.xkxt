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
                <jsp:include page="sidenavbar.jsp" />

                <div class="content-inner form-cont">
                    <div class="row">
                        <div class="col-md-12">

                            <!--***** 学生信息管理 *****-->
                            <div class="card form" id="form1">
                                <div class="card-header">
                                    <h3><i class="fa fa-user-circle"></i>教师信息管理</h3>
                                </div>
                                <br>
                                <form action="TeacherServlet" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                	所属分院：<select class="form-control" id="colselect" name="CollegeID" onchange="loadtea()">																		
										
											</select>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="cont-number"></label> 
                                                	教职工号：<input type="text" class="form-control" id="TeacherID" name="TeacherID" aria-describedby="emailHelp" placeholder="请输入教职工号！">
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
                                                	姓名：<input type="text" class="form-control" id="TeacherName" name="TeacherName" aria-describedby="emailHelp" placeholder="请输入教师姓名！">
                                            </div>
                                            <div class="form-group">
        										出生日期：<input class="form-control" type="date" value="xxxx/xx/xx" id="Birthday" name="Birthday">
    										</div>
    										 <div class="form-group">
                                                <label for="cont-number"></label> 
                                                文化程度：<input type="text" class="form-control" id="Culture" name="Culture" aria-describedby="emailHelp" placeholder="请输入文化信息！">
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
                                    </div>
                                   
                                </form>
                            </div>

                            <!--***** 表格 *****-->
                            
                            
                           <div class="card form" id="form2"style="padding:20px 30px 20px 30px;">
				<div class="row">
					
					<table class="table table-hover" style="width:1300px;" id="teatab">
						<thead>
                                        <tr class="bg-info text-white">
                                            <th style="width: 10%">教职工号</th>
                                            <th style="width: 10%">姓名</th>
                                            <th style="width: 5%">性别</th>
                                            <th style="width: 15%">出生日期</th>
                                            <th style="width: 15%">文化程度</th>
                                            <th style="width: 10%">所属分院</th>
                                            <th style="width: 10%">密码</th>
                                            <th style="width: 15%">备注</th>
                                            <th style="width: 15%">操作</th>
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
					loadtea();
						},
					error : function() {
					alert("分院数据请求失败！");
						}
				});
						}
		
		function loadtea(){
		     var id=$("#colselect").find("option:selected").val();   
		    
			 $.ajax({
				url:"TeacherServlet?mg=se",
				type:"post",
				dataType:"json",
				data:{"CollegeID":id},
				success:function(data){
					$("#teatab tbody").html("");
				     var trhtml="";
				     for(var i=0;i<data.length;i++){
					    trhtml+="<tr><td>";
						trhtml+=data[i].teacherID;
						trhtml+="</td><td>";
						trhtml+=data[i].teacherName;
						trhtml+="</td><td>";
						trhtml+=data[i].sex;
						trhtml+="</td><td>";
						trhtml+=data[i].birthday;
						trhtml+="</td><td>";
						trhtml+=data[i].culture;
						trhtml+="</td><td>";
						trhtml+=$("#colselect").find("option:selected").text();
						trhtml+="</td><td>";
						trhtml+=data[i].password;
						trhtml+="</td><td>";
						trhtml+=data[i].remark;
						trhtml+="</td><td><a href='javascript:void(0)' onclick='rowselect("+(i+1)+")'>选择</a>&nbsp;&nbsp;<a href='TeacherServlet?mg=de&TeacherID="
						trhtml+=data[i].teacherID;
						trhtml+="'>删除</a></td></tr>";
					} 					
					$("#teatab tbody").append(trhtml);
				},
				error:function(){
					alert("教师数据加载失败！");
				}
			}); 
		}
		function rowselect(index) {
			var tb = document.getElementById("teatab");				
			$("#TeacherID").val(tb.rows[index].cells[0].innerHTML);
			$("#TeacherName").val(tb.rows[index].cells[1].innerHTML);
			$("#Sex").val(tb.rows[index].cells[2].innerHTML);
			$("#Birthday").val(tb.rows[index].cells[3].innerHTML);
			$("#Culture").val(tb.rows[index].cells[4].innereHTML);
			$("#Password").val(tb.rows[index].cells[6].innerHTML);
			$("#Remark").val(tb.rows[index].cells[7].innerHTML);
			$("#TeacherID").attr("readOnly",true);
			$("#mg").val("up");
		}
		function inputreset() {
			$("#TeacherID").attr("readOnly", false);
		}
		
		</script>
        </body>

        </html> 