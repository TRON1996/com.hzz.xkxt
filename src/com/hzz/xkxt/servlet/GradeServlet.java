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

import com.hzz.xkxt.bean.Grade;
import com.hzz.xkxt.factory.GradeFactory;
import com.hzz.xkxt.factory.GradeMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/GradeServlet")
public class GradeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GradeServlet() {
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

		if(mg.equals("up")){
			Updata(request,response);
			response.sendRedirect("Teacher/grade.jsp");
		}
         if(mg.equals("se")){
        	 try {
        		 Select(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}

	


	private void Updata(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Grade gr=new Grade();
		gr.setStudentID(request.getParameter("StudentID"));
		gr.setTeachTaskID(request.getParameter("TeachTaskID"));
		gr.setScoreOne(request.getParameter("ScoreOne"));
		gr.setScoreTwo(request.getParameter("ScoreTwo"));
		gr.setScoreThree(request.getParameter("ScoreThree"));
		gr.setTotalScore(String.valueOf((Double.parseDouble(gr.getScoreOne())+
				Double.parseDouble(gr.getScoreTwo())+Double.parseDouble(gr.getScoreThree()))));
		GradeMgrInterface ttmi=GradeFactory.getInstance();
		ttmi.update(gr);
	}

	private void Select(HttpServletRequest request,HttpServletResponse response)throws SQLException, IOException  {
		// TODO Auto-generated method stub

		GradeMgrInterface gmi=GradeFactory.getInstance();
		List<Grade> alist = gmi.select();		
		JSONArray json=JSONArray.fromObject(alist);
		System.out.print(json);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();	
	}


	
}
