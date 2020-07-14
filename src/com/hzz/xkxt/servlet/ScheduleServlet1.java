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

import com.hzz.xkxt.bean.Schedule;
import com.hzz.xkxt.bean.Teacher;
import com.hzz.xkxt.factory.ScheduleFactory;
import com.hzz.xkxt.factory.ScheduleMgrInterface;

import net.sf.json.JSONArray;


/**
 * Servlet implementation class ScheduleServlet1
 */
@WebServlet("/ScheduleServlet1")
public class ScheduleServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScheduleServlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String mg = request.getParameter("mg");
		if (mg.equals("in")) {
			Insert(request, response);
			response.sendRedirect("Teacher/Schedule1.jsp");
		}
		if (mg.equals("de")) {
			Delete(request, response);
			response.sendRedirect("Teacher/Schedule1.jsp");
		}
		if (mg.equals("se")) {
			try {
				Select(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void Select(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// TODO Auto-generated method stub
		ScheduleMgrInterface sif = ScheduleFactory.getInstance();
		List<Schedule> slist = sif.select(((Teacher) request.getSession()
				.getAttribute("user")).getTeacherID());

		JSONArray json = JSONArray.fromObject(slist);
		System.out.println(json);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();

	}

	private void Delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		ScheduleMgrInterface smi=ScheduleFactory.getInstance();
		Schedule sch=new Schedule();
		sch.setTeachTaskID(request.getParameter("TeachtaskID"));
	  
		smi.delete(sch);
	}

	private void Insert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ScheduleMgrInterface sif =ScheduleFactory.getInstance();
		Schedule sch =new Schedule();
		sch.setClassRoomID(request.getParameter("ClassRoomName"));
		sch.setClassRoomName(request.getParameter("ClassRoomName"));
		sch.setTeachTaskID(request.getParameter("TeachtaskID"));
		sch.setWeekday(request.getParameter("Weekday"));
		sch.setClassfestival(request.getParameter("Classfestival"));
		sch.setClassTime(request.getParameter("ClassTime"));
		sch.setRemark(request.getParameter("Remark"));
		sif.insert(sch);
	}
}
