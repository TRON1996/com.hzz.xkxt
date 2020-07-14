package com.hzz.xkxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.hzz.xkxt.bean.TeachTask;
import com.hzz.xkxt.bean.Course;
import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.TeachTaskFactory;
import com.hzz.xkxt.factory.TeachTaskMgrInterface;
import com.hzz.xkxt.util.util;

import net.sf.json.JSONArray;
@WebServlet("/TeachTaskMgrServlet")
public class TeachTaskMgrServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public TeachTaskMgrServlet() {
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
		if(mg.equals("in")){
			TeachTaskmgrInsert(request,response);
			response.sendRedirect("Teacher/teachtask.jsp");
		}
		if(mg.equals("de")){
			TeachTaskmgrDelete(request,response);
			response.sendRedirect("Teacher/teachtask.jsp");
		}
		if(mg.equals("up")){
			Updata(request,response);
			response.sendRedirect("admin/audit.jsp");
		}
		
		if(mg.equals("se")){
			try {
				TeachTaskmgrSelect(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(mg.equals("Sse")){
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
		String TeacherID=((Teacher)(request.getSession().getAttribute("user"))).getTeacherID();
		TeachTaskMgrInterface ttmi=TeachTaskFactory.getInstance();
	
		List<TeachTask> tlist=ttmi.select(TeacherID);
		 JSONArray json=JSONArray.fromObject(tlist);
		 System.out.println(json);
		response.setCharacterEncoding("utf-8");
	    PrintWriter pw=response.getWriter();
	    pw.write(json.toString());
	    pw.close();
	    pw.flush();
	}


	private void Updata(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		TeachTask tt=new TeachTask();
		tt.setID(request.getParameter("TeachTaskID"));
		tt.setAuditing(new String(request.getParameter("Auditing").getBytes("iso-8859-1"),"UTF-8"));
		TeachTaskMgrInterface ttmi=TeachTaskFactory.getInstance();
		ttmi.updata(tt);
	}


	private void TeachTaskmgrSelect(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String Curricula=request.getParameter("Curricula");
		Curricula="%"+Curricula+"%";
		String TeacherID=((Teacher)(request.getSession().getAttribute("user"))).getTeacherID();
		TeachTaskMgrInterface ttmi=TeachTaskFactory.getInstance();
	
		List<TeachTask> tlist=ttmi.select(Curricula,TeacherID);
		 JSONArray json=JSONArray.fromObject(tlist);
		 System.out.println(json);
		response.setCharacterEncoding("utf-8");
	    PrintWriter pw=response.getWriter();
	    pw.write(json.toString());
	    pw.close();
	    pw.flush();
	}


	private void TeachTaskmgrDelete(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		TeachTask tt=new TeachTask();
		Course co=new Course();

		co.setCourseID(request.getParameter("CourseID"));
		
		
		tt.setTeacherID(((Teacher) request.getSession().getAttribute("user")).getTeacherID());
		tt.setCourse(co);
		TeachTaskMgrInterface ttmi=TeachTaskFactory.getInstance();
		ttmi.delete(tt);
	}


	private void TeachTaskmgrInsert(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String State = new String(request.getParameter("State").getBytes("iso8859-1"),"utf-8");
		if (State.equals("Î´¿ª¿Î")) {
		Teacher tea=((Teacher)request.getSession().getAttribute("user"));
		TeachTask tt=new TeachTask();
		Teacher te=new Teacher();
		Course co=new Course();
		te.setTeacherID(tea.getTeacherID());
		co.setCourseID(request.getParameter("CourseID"));
		tt.setTeacherID(te.getTeacherID());
		tt.setCourse(co);
		tt.setCurricula(util.getNow());
		tt.setAuditing("Î´ÉóºË");
		TeachTaskMgrInterface ttmi=TeachTaskFactory.getInstance();
		ttmi.insert(tt);
	}
	}
}
