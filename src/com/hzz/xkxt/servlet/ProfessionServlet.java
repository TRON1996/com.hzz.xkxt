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

import com.hzz.xkxt.bean.Department;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.factory.professionFactory;
import com.hzz.xkxt.factory.professionMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/ProfessionServlet")
public class ProfessionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ProfessionServlet() {
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
			professionInsert(request,response);
			response.sendRedirect("admin/professionmgr.jsp");
		}
		if(mg.equals("up")){
			professionUpdate(request,response);
			response.sendRedirect("admin/professionmgr.jsp");
		}
		if(mg.equals("de")){			
			profssionDelete(request,response);
			response.sendRedirect("admin/professionmgr.jsp");
		}
        if(mg.equals("se")){
        	 try {
        		 professionSelect(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }		
	}

	private void professionSelect(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		 professionMgrInterface pmi=professionFactory.getInstance();
		 List<Profession> plist=pmi.select(request.getParameter("departmentID"));
		 JSONArray json=JSONArray.fromObject(plist);
		
		 response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();	
	}

	private void profssionDelete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Profession pro=new Profession();
		pro.setProfessionID(request.getParameter("ProfessionID"));
	    professionMgrInterface pmi=professionFactory.getInstance();
        pmi.delete(pro);    
	}

	private void professionUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Profession pro=new Profession();
		Department dep=new Department();
		dep.setDepartmentID(request.getParameter("departmentID"));
		pro.setDepartment(dep);
		pro.setProfessionID(request.getParameter("ProfessionID"));
		pro.setProfessionName(request.getParameter("ProfessionName"));
		pro.setRemark(request.getParameter("Remark"));
		professionMgrInterface pmi=professionFactory.getInstance();
		pmi.update(pro);
	}

	private void professionInsert(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Profession pro =new Profession();
		Department dep=new Department();
		dep.setDepartmentID(request.getParameter("departmentID"));
		pro.setDepartment(dep);
		pro.setProfessionID(request.getParameter("ProfessionID"));
		pro.setProfessionName(request.getParameter("ProfessionName"));
		pro.setRemark(request.getParameter("Remark"));
		professionMgrInterface pmi=professionFactory.getInstance();
		pmi.insert(pro);
	}

}
