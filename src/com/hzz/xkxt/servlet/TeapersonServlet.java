package com.hzz.xkxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.College;
import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.teacherFactory;
import com.hzz.xkxt.factory.teacherMgrInterface;
import com.hzz.xkxt.util.util;
@WebServlet("/TeapersonServlet")
public class TeapersonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public TeapersonServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mg=request.getParameter("mg");
		if(mg!=null){
			if(mg.equals("up")){
				teaUpdate(request,response);
				response.sendRedirect("Teacher/teaperson.jsp");
			}		
		}
		else{
			teaPhotoUpload(request,response);
			response.sendRedirect("Teacher/teaperson.jsp");
		}
	}

	private void teaUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Teacher tea=new Teacher();
		College co=new College();
      
		tea.setTeacherID(request.getParameter("TeacherID"));
		tea.setTeacherName(request.getParameter("TeacherName"));
		co.setCollegeID(request.getParameter("CollegeID"));
	    tea.setCollege(co);
	 		
		tea.setBirthday(request.getParameter("Birthday"));
		tea.setCulture(request.getParameter("Culture"));
		tea.setRemark(request.getParameter("Remark"));
		teacherMgrInterface tmi=teacherFactory.getInstance();
		tmi.update(tea);
	}

	private void teaPhotoUpload(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			util.fileupload(this, request, "teacherphoto", ((Teacher)(request.getSession().getAttribute("user"))).getTeacherID()+".jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
