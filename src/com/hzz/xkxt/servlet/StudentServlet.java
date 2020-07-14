package com.hzz.xkxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Class01;
import com.hzz.xkxt.bean.Student;
import com.hzz.xkxt.factory.studentFactory;
import com.hzz.xkxt.factory.studentMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public StudentServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mg=request.getParameter("mg");
		if(mg.equals("in")){
			studentmgrInsert(request,response);
			response.sendRedirect("admin/studentmgr.jsp");
		}
		if(mg.equals("up")){
			studentmgrUpdate(request,response);
			response.sendRedirect("admin/studentmgr.jsp");
		}
		if(mg.equals("de")){			
			studentmgrDelete(request,response);
			response.sendRedirect("admin/studentmgr.jsp");
		}
         if(mg.equals("se")){
        	 try {
				studentmgrSelect(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

}



	private void studentmgrSelect(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException    {
		// TODO Auto-generated method stub
		 studentMgrInterface smi=studentFactory.getInstance();
		 List<Student> slist=smi.select(request.getParameter("ClassID"));
         JSONArray json=JSONArray.fromObject(slist);
         response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();	
	}


	private void studentmgrDelete(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub;
		Student stu=new Student();
		stu.setStudentID(request.getParameter("StudentID"));
		studentMgrInterface smi=studentFactory.getInstance();
		smi.delete(stu);
	}


	private void studentmgrUpdate(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
	    Student stu=new Student();
	    Class01 cla=new Class01();
	    cla.setClassID(request.getParameter("ClassID"));
	    stu.setClassmgr(cla);
	    stu.setStudentID(request.getParameter("StudentID"));
	    stu.setStudentName(request.getParameter("StudentName"));
	    stu.setSex(request.getParameter("Sex"));
	    stu.setBirth(request.getParameter("Birth"));
        stu.setPhoto(request.getParameter("Photo"));
	    stu.setPassword(request.getParameter("Password"));
	    stu.setRemark(request.getParameter("Remark"));
	    studentMgrInterface smi=studentFactory.getInstance();
	    smi.update(stu);
	}


	private void studentmgrInsert(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Student stu=new Student();
		Class01 cla=new Class01();
		cla.setClassID(request.getParameter("ClassID"));
		stu.setClassmgr(cla);
		stu.setStudentID(request.getParameter("StudentID"));
		stu.setStudentName(request.getParameter("StudentName"));
		stu.setSex(request.getParameter("Sex"));
		stu.setBirth(request.getParameter("Birth"));

		stu.setPassword(request.getParameter("Password"));
		stu.setRemark(request.getParameter("Remark"));
		studentMgrInterface smi=studentFactory.getInstance();
		smi.insert(stu);
	}
}