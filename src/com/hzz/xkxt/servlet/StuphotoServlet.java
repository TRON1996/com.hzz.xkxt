package com.hzz.xkxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Class01;
import com.hzz.xkxt.bean.Student;
import com.hzz.xkxt.factory.studentFactory;
import com.hzz.xkxt.factory.studentMgrInterface;
import com.hzz.xkxt.util.util;
@WebServlet("/StuphotoServlet")
public class StuphotoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public StuphotoServlet() {
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
    				stuUpdate(request,response);
    				response.sendRedirect("Student/stuperson.jsp");
    			}		
    		}
    		else{
    			stuPhotoUpload(request,response);
    			response.sendRedirect("Student/stuperson.jsp");
    		}
	        
	
	}

	private void stuUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

        Class01 cla=new Class01();
        Student stu=new Student();
        stu.setStudentID(request.getParameter("StudentID"));
        stu.setStudentName(request.getParameter("StudentName"));
        cla.setClassID(request.getParameter("ClassID"));
        stu.setClassmgr(cla);
	    stu.setSex(request.getParameter("Sex"));
		stu.setBirth(request.getParameter("Birth"));	
		stu.setPhoto(request.getParameter("Photo"));
		stu.setRemark(request.getParameter("Remark"));
		studentMgrInterface smi=studentFactory.getInstance();
		smi.upp(stu);
	}

	private void stuPhotoUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			util.fileupload(this, request, "studentphoto", ((Student)(request.getSession().getAttribute("user"))).getStudentID()+".jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
