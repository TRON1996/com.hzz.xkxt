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

import com.hzz.xkxt.bean.Class01;
import com.hzz.xkxt.bean.Profession;
import com.hzz.xkxt.factory.classMgrInterface;
import com.hzz.xkxt.factory.classmgrFactory;

import net.sf.json.JSONArray;
@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ClassServlet() {
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
			classmgrInsert(request,response);
			response.sendRedirect("admin/Class01.jsp");
		}
		if(mg.equals("up")){
			classmgrUpdate(request,response);
			response.sendRedirect("admin/Class01.jsp");
		}
		if(mg.equals("de")){			
			classmgrDelete(request,response);
			response.sendRedirect("admin/Class01.jsp");
		}
         if(mg.equals("se")){
        	 try {
				classmgrSelect(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}


	private void classmgrSelect(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException   {
		// TODO Auto-generated method stub
		 classMgrInterface cmi=classmgrFactory.getInstance();
		 List<Class01> clist=cmi.select(request.getParameter("ProfessionID"));
		 JSONArray json=JSONArray.fromObject(clist);
		 response.setCharacterEncoding("utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();	
	}


	private void classmgrDelete(HttpServletRequest request,HttpServletResponse response)  {
		// TODO Auto-generated method stub
		Class01 cla=new Class01();
		cla.setClassID(request.getParameter("ClassID"));
		classMgrInterface cmi=classmgrFactory.getInstance();
		cmi.delete(cla);
	}


	private void classmgrUpdate(HttpServletRequest request,HttpServletResponse response)  {
		// TODO Auto-generated method stub
		Class01 cla=new Class01();
		Profession pro =new Profession();
		pro.setProfessionID(request.getParameter("ProfessionID"));
		cla.setProfession(pro);
		cla.setClassID(request.getParameter("ClassID"));
		cla.setClassName(request.getParameter("ClassName"));
	
	    cla.setRemark(request.getParameter("Remark"));
	    classMgrInterface cmi=classmgrFactory.getInstance();
	    cmi.update(cla);
	}


	private void classmgrInsert(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Class01 cla=new Class01();
		Profession pro=new Profession();
		pro.setProfessionID(request.getParameter("ProfessionID"));
		cla.setProfession(pro);
		cla.setClassID(request.getParameter("ClassID"));
		cla.setClassName(request.getParameter("ClassName"));
		
		cla.setRemark(request.getParameter("Remark"));
		classMgrInterface cmi=classmgrFactory.getInstance();
		cmi.insert(cla);
	}
}
