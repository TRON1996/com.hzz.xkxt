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

import com.hzz.xkxt.bean.College;
import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.teacherFactory;
import com.hzz.xkxt.factory.teacherMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public TeacherServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
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
         
   		if(mg.equals("login")){
   			try {
   				login(request,response);
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   		}
        if(mg.equals("in")){
   			teachermgrInsert(request,response);
   			response.sendRedirect("admin/teachermgr.jsp");
   		}
   		if(mg.equals("up")){
   			teachermgrUpdate(request,response);
   			response.sendRedirect("admin/teachermgr.jsp");
   		}
   		if(mg.equals("de")){			
   			teachermgrDelete(request,response);
   			response.sendRedirect("admin/teachermgr.jsp");
   		}
            if(mg.equals("se")){
           	 try {
   				teachermgrSelect(request,response);
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	
   	}	
            
         if(mg.equals("exit")){
		teaexit(request,response);
	}
		
	}

	private void teaexit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("user", null);
		response.sendRedirect("Teacher/login.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
	    Teacher tea=new Teacher();
	    tea.setTeacherID(request.getParameter("TeacherID"));
	    tea.setPassword(request.getParameter("Password"));
	    teacherMgrInterface tmi=teacherFactory.getInstance();
	    Teacher thetea=tmi.login(tea);
	    if(thetea!=null){
	    	request.getSession().setAttribute("user", thetea);
	    	response.sendRedirect("Teacher/teaperson.jsp");
	    }else{
	    	request.setAttribute("msg", "用户名或密码错误！");
	    	request.getRequestDispatcher("Teacher/login.jsp").forward(request, response);
	    }
	  }

	private void teachermgrSelect(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException{
		// TODO Auto-generated method stub
		teacherMgrInterface tmi=teacherFactory.getInstance();
		List<Teacher> tlist=tmi.select(request.getParameter("CollegeID"));
	    JSONArray json=JSONArray.fromObject(tlist);
		response.setCharacterEncoding("utf-8");
	    PrintWriter pw=response.getWriter();
	    pw.write(json.toString());
	    pw.close();
	    pw.flush();
	}

	private void teachermgrDelete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Teacher tea=new Teacher();
		tea.setTeacherID(request.getParameter("TeacherID"));
		teacherMgrInterface tmi=teacherFactory.getInstance();
		tmi.delete(tea);
	}

	private void teachermgrUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Teacher tea=new Teacher();
		College col=new College();
		col.setCollegeID(request.getParameter("CollegeID"));
		tea.setCollege(col);
		tea.setTeacherID(request.getParameter("TeacherID"));
		tea.setTeacherName(request.getParameter("TeacherName"));

		tea.setSex(request.getParameter("Sex"));
		tea.setBirthday(request.getParameter("Birthday"));
		tea.setCulture(request.getParameter("Culture"));
		tea.setPassword(request.getParameter("Password"));
		tea.setRemark(request.getParameter("Remark"));
		teacherMgrInterface tmi=teacherFactory.getInstance();
		tmi.updata1(tea);
	}

	private void teachermgrInsert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Teacher tea=new Teacher();
		College col=new College();
		col.setCollegeID(request.getParameter("CollegeID"));
		tea.setCollege(col);
		tea.setTeacherID(request.getParameter("TeacherID"));
		tea.setTeacherName(request.getParameter("TeacherName"));
		
		tea.setSex(request.getParameter("Sex"));
		tea.setBirthday(request.getParameter("Birthday"));
		tea.setCulture(request.getParameter("Culture"));
		tea.setPassword(request.getParameter("Password"));
		tea.setRemark(request.getParameter("Remark"));
	    teacherMgrInterface tmi=teacherFactory.getInstance();
	    tmi.insert(tea);
	}

	
}
