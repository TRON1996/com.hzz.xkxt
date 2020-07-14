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
import com.hzz.xkxt.factory.collegeFactory;
import com.hzz.xkxt.factory.collegeMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/CollegeServlet")
public class CollegeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CollegeServlet() {
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
			collegeInsert(request,response);
			response.sendRedirect("admin/collegemgr.jsp");
		}
		if(mg.equals("de")){
			collegeDelete(request,response);
			response.sendRedirect("admin/collegemgr.jsp");
		}
		if(mg.equals("up")){
			collegeUpdate(request,response);
			response.sendRedirect("admin/collegemgr.jsp");
		}
		if(mg.equals("se")){
			try {
				collegeSelect(request,response);
			} catch (Exception e) {		
				e.printStackTrace();
			}
			
		}
	}

	
	private void collegeSelect(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
	        collegeMgrInterface cmi=collegeFactory.getInstance();
			List<College> clist=cmi.select();
			JSONArray json=JSONArray.fromObject(clist);
			/*System.out.print(json);*/
			
			response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();	
	}

	private void collegeUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		College col=new College();
		col.setCollegeID(request.getParameter("collegeID"));
		col.setCollegeName(request.getParameter("collegeName"));
		col.setRemark(request.getParameter("Remark"));
		collegeMgrInterface cmi=collegeFactory.getInstance();
		cmi.update(col);
	}

	private void collegeDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		College col=new College();
		col.setCollegeID(request.getParameter("collegeID"));
		collegeMgrInterface cmi=collegeFactory.getInstance();
		cmi.delete(col);
	}

	private void collegeInsert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		College col=new College();
		col.setCollegeID(request.getParameter("collegeID"));
		col.setCollegeName(request.getParameter("collegeName"));
		col.setRemark(request.getParameter("Remark"));	
	    collegeMgrInterface cmi=collegeFactory.getInstance();
		cmi.insert(col);
	}

}
