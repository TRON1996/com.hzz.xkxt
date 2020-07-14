 package com.hzz.xkxt.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Student;
import com.hzz.xkxt.factory.studentFactory;
import com.hzz.xkxt.factory.studentMgrInterface;

@WebServlet("/StupersonServlet")
public class StupersonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public StupersonServlet() {
		super();
	}

	
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
             
           if(mg.equals("login")){
   			try {
   				login(request,response);
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   		}
   	 if(mg.equals("exit")){
 		stuexit(request,response);
 	}
 		
 	if(mg.equals("reset")){
		try {
			Adminpwdreset(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Student/stuperson.jsp");
	}
 	
	}	 
 	



	private void Adminpwdreset(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
        studentMgrInterface smi=studentFactory.getInstance();
        Student stu=new Student();
        stu.setStudentID(request.getParameter("StudentID"));
        stu.setPassword(request.getParameter("oldpwd"));
		
		if(smi.login(stu)==null){
			return;
		}		
		String newpwd=request.getParameter("newpwd");
		String newpwd1=request.getParameter("newpwd1");
	    if(!newpwd1.equals(newpwd)){
	    	return;
	    }
	    stu.setPassword(newpwd);
		smi.resetpwd(stu);
	}


	

	private void stuexit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("user", null);
		response.sendRedirect("Student/login.jsp");
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException, ServletException {
		// TODO Auto-generated method stub
		Student stu=new Student();
		stu.setStudentID(request.getParameter("StudentID"));
		stu.setPassword(request.getParameter("Password"));
		studentMgrInterface smi=studentFactory.getInstance();
		Student thestu=smi.login(stu);
		
	    if(thestu!=null){
	    	request.getSession().setAttribute("user", thestu);
	    	response.sendRedirect("Student/stuperson.jsp");
	    }else{
	    	request.setAttribute("msg", "用户名或密码错误！");
	    	request.getRequestDispatcher("Student/login.jsp").forward(request, response);
	    }
	}

}
