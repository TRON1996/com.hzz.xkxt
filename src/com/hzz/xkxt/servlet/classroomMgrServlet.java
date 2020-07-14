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

import com.hzz.xkxt.bean.Classroom;
import com.hzz.xkxt.factory.classroomFactory;
import com.hzz.xkxt.factory.classroomMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/classroomMgrServlet")
public class classroomMgrServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public classroomMgrServlet() {
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
		if(mg.equals("in")){
			classroomInsert(request,response);
			response.sendRedirect("admin/classroommgr.jsp");
		}
		if(mg.equals("de")){
			classroomDelete(request,response);
			response.sendRedirect("admin/classroommgr.jsp");
		}
		if(mg.equals("up")){
			classroomUpdate(request,response);
			response.sendRedirect("admin/classroommgr.jsp");
		}
		if(mg.equals("se")){
			try {
				classroomSelect(request,response);
			} catch (Exception e) {		
				e.printStackTrace();
			}
			
		}
	}

	private void classroomSelect(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub	   
	            classroomMgrInterface cmi=classroomFactory.getInstance();
				List<Classroom> clist=cmi.select();
				JSONArray json=JSONArray.fromObject(clist);
			
				
				response.setCharacterEncoding("utf-8");
				PrintWriter pw=response.getWriter();
				pw.write(json.toString());
				pw.close();
				pw.flush();	
	}

	private void classroomUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Classroom cla =new Classroom();
		cla.setClassRoomID(request.getParameter("ClassRoomID"));
		cla.setClassRoomName(request.getParameter("ClassRoomName"));
		cla.setClassRoomType(request.getParameter("ClassRoomType"));
		cla.setRemark(request.getParameter("Remark"));
        classroomMgrInterface cmi=classroomFactory.getInstance();
		cmi.update(cla);
		
	}

	private void classroomDelete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Classroom cla=new Classroom();
		cla.setClassRoomID(request.getParameter("ClassRoomID"));
		classroomMgrInterface cmi=classroomFactory.getInstance();
		cmi.delete(cla);
			
	}

	private void classroomInsert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Classroom cla=new Classroom();
		cla.setClassRoomID(request.getParameter("ClassRoomID"));
		cla.setClassRoomName(request.getParameter("ClassRoomName"));
		cla.setClassRoomType(request.getParameter("ClassRoomType"));
		cla.setRemark(request.getParameter("Remark"));
		classroomMgrInterface cmi=classroomFactory.getInstance();
		cmi.insert(cla);
		
	}
		
	}

	

