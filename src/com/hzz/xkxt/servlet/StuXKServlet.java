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

import com.hzz.xkxt.bean.StuXK;
import com.hzz.xkxt.bean.Student;
import com.hzz.xkxt.factory.StuXKFactory;
import com.hzz.xkxt.factory.StuXKMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/StuXKServlet")
public class StuXKServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public StuXKServlet() {
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
			Insert(request,response);
			response.sendRedirect("Student/stuxk.jsp");
		}
		if(mg.equals("de")){
			Delete(request,response);
			response.sendRedirect("Student/stuxk.jsp");
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

	

	private void Delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StuXK sxk=new StuXK();
		sxk.setTeachTaskID(Integer.parseInt(request.getParameter("teachTaskID")));
		StuXKMgrInterface ami=StuXKFactory.getInstance();
		ami.delete(sxk);
	}



	private void Select(HttpServletRequest request,HttpServletResponse response)throws SQLException, IOException  {
		// TODO Auto-generated method stub
		StuXKMgrInterface smi=StuXKFactory.getInstance();
		List<StuXK> slist = smi.select();		
		JSONArray json=JSONArray.fromObject(slist);
		System.out.print(json);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();	
	}


	private void Insert(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
	
	    StuXK sxk=new StuXK();
		sxk.setStudentID(((Student)(request.getSession().getAttribute("user"))).getStudentID());
		sxk.setTeachTaskID(Integer.parseInt(request.getParameter("teachTaskID")));
		sxk.setScoreOne("0");
		sxk.setScoreTwo("0");
		sxk.setScoreThree("0");
		sxk.setTotalScore("0");
		StuXKMgrInterface smi=StuXKFactory.getInstance();
		smi.insert(sxk);
	}
}
