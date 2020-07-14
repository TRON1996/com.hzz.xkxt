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
import com.hzz.xkxt.bean.Department;
import com.hzz.xkxt.factory.departmentFactory;
import com.hzz.xkxt.factory.departmentMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DepartmentServlet() {
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
			departmentInsert(request,response);
			response.sendRedirect("admin/departmentmgr.jsp");
		}
		if(mg.equals("up")){
			departmentUpdate(request,response);
			response.sendRedirect("admin/departmentmgr.jsp");
		}
		if(mg.equals("de")){			
			departmentDelete(request,response);
			response.sendRedirect("admin/departmentmgr.jsp");
		}
		if(mg.equals("se")){			
			try {
				departmentSelect(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	
	private void departmentSelect(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
	        departmentMgrInterface dmi=departmentFactory.getInstance();

			List<Department> dlist=dmi.select(request.getParameter("collegeID"));
			JSONArray json=JSONArray.fromObject(dlist);
			
			
			response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();	
	}

	private void departmentUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		Department dep=new Department();
		College col=new College();
		col.setCollegeID(request.getParameter("collegeID"));
		dep.setCollege(col);
		dep.setDepartmentID(request.getParameter("departmentID"));
		dep.setDepartmentName(request.getParameter("departmentName"));
		dep.setRemark(request.getParameter("Remark"));
		departmentMgrInterface dmi=departmentFactory.getInstance();
		dmi.update(dep);
	}

	private void departmentDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		Department dep=new Department();
		dep.setDepartmentID(request.getParameter("departmentID"));
		departmentMgrInterface dmi=departmentFactory.getInstance();
		dmi.delete(dep);
	}
 
	private void departmentInsert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		Department dep=new Department();
		College col=new College();
		col.setCollegeID(request.getParameter("collegeID"));
		dep.setCollege(col);
		dep.setDepartmentID(request.getParameter("departmentID"));
		dep.setDepartmentName(request.getParameter("departmentName"));
		dep.setRemark(request.getParameter("Remark"));
		departmentMgrInterface dmi=departmentFactory.getInstance();
		dmi.insert(dep);
	}

}
