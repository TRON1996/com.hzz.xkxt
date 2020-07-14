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

import com.hzz.xkxt.bean.Course;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.courseFactory;
import com.hzz.xkxt.factory.courseMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CourseServlet() {
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
         if(mg.equals("in")){
 			coursemgrInsert(request,response);
 			response.sendRedirect("admin/coursemgr.jsp");
 		}
 		if(mg.equals("up")){
 			coursemgrUpdate(request,response);
 			response.sendRedirect("admin/coursemgr.jsp");
 		}
 		if(mg.equals("de")){			
 			coursemgrDelete(request,response);
 			response.sendRedirect("admin/coursemgr.jsp");
 		}
          if(mg.equals("se")){
         	 try {
 				coursemgrSelect(request,response);
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	
 	}
          if(mg.equals("Cse")){
          	 try {
  				Select(request,response);
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  	
  	}
		
	}

	private void Select(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		courseMgrInterface cmi=courseFactory.getInstance();
	
		List<Course> clist=cmi.select(request.getParameter("ProfessionID"));
	    JSONArray json=JSONArray.fromObject(clist);
		response.setCharacterEncoding("utf-8");
	    PrintWriter pw=response.getWriter();
	    
	    pw.write(json.toString());
	    pw.close();
	    pw.flush();
	    
	}

	private void coursemgrSelect(HttpServletRequest request,HttpServletResponse response)throws SQLException, IOException {
		// TODO Auto-generated method stub
		courseMgrInterface cmi=courseFactory.getInstance();
		Teacher tea=(Teacher)request.getSession().getAttribute("user");
		List<Course> clist=cmi.select(request.getParameter("ProfessionID"),tea.getTeacherID());
	    JSONArray json=JSONArray.fromObject(clist);
		response.setCharacterEncoding("utf-8");
	    PrintWriter pw=response.getWriter();
	    
	    pw.write(json.toString());
	    pw.close();
	    pw.flush();
	}

	private void coursemgrDelete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		cou.setCourseID(request.getParameter("CourseID"));
		courseMgrInterface cmi=courseFactory.getInstance();
		cmi.delete(cou);
	}

	private void coursemgrUpdate(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		Profession pro=new Profession();
		pro.setProfessionID(request.getParameter("ProfessionID"));
		cou.setProfession(pro);
		cou.setCourseID(request.getParameter("CourseID"));
		cou.setCourseName(request.getParameter("CourseName"));
	
		cou.setCurriculumTime(request.getParameter("CurriculumTime"));
		cou.setStudyTime(request.getParameter("StudyTime"));
		cou.setCrediy(request.getParameter("Crediy"));
		cou.setRemark(request.getParameter("Remark"));
		courseMgrInterface cmi=courseFactory.getInstance();
		cmi.update(cou);
		
	}

	private void coursemgrInsert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		Profession pro=new Profession();
		pro.setProfessionID(request.getParameter("ProfessionID"));
		cou.setProfession(pro);
		cou.setCourseID(request.getParameter("CourseID"));
		cou.setCourseName(request.getParameter("CourseName"));
	
		cou.setCurriculumTime(request.getParameter("CurriculumTime"));
		cou.setStudyTime(request.getParameter("StudyTime"));
		cou.setCrediy(request.getParameter("Crediy"));
		cou.setRemark(request.getParameter("Remark"));
		courseMgrInterface cmi=courseFactory.getInstance();
		cmi.insert(cou);
	}
		
	}

	


