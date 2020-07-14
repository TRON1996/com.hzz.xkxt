package com.hzz.xkxt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.teacherFactory;
import com.hzz.xkxt.factory.teacherMgrInterface;
@WebServlet("/TeaupdatapwdServlet")
public class TeaupdatapwdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public TeaupdatapwdServlet() {
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
		if(mg.equals("reset")){
			try {
				Adminpwdreset(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("Teacher/teaperson.jsp");
		}
	}
		private void Adminpwdreset(HttpServletRequest request,HttpServletResponse response) throws SQLException {
			// TODO Auto-generated method stub

			teacherMgrInterface tmi=teacherFactory.getInstance();
			Teacher tea=new Teacher();
			tea.setTeacherID(request.getParameter("TeacherID"));
			tea.setPassword(request.getParameter("oldpwd"));
			
			
			if(tmi.login(tea)==null){
				return;
			}		
			String newpwd=request.getParameter("newpwd");
			String newpwd1=request.getParameter("newpwd1");
		    if(!newpwd1.equals(newpwd)){
		    	return;
		    }
		    tea.setPassword(newpwd);
			tmi.resetpwd(tea);
		}
	}


