package com.hzz.xkxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.factory.AdminFactory;
import com.hzz.xkxt.factory.AdminMgrInterface;
import com.hzz.xkxt.util.util;

@WebServlet("/PersonallyInfoServlet")
public class PersonallyInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PersonallyInfoServlet() {
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
		if(mg!=null){
			if(mg.equals("up")){
				AdminUpdate(request,response);
				response.sendRedirect("admin/adminmgr.jsp");
			}		
		}
		else{
			AdminPhotoUpload(request,response);
			response.sendRedirect("admin/personalinfo.jsp");
		}
	}
	

	void AdminUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAdminID(request.getParameter("AdminID"));
		admin.setAdminName(request.getParameter("AdminName"));
		admin.setRole(request.getParameter("Role"));
		admin.setRemark(request.getParameter("Remark"));
		
		AdminMgrInterface ami=AdminFactory.getInstance();
		ami.update(admin);
		
	}
	
	void AdminPhotoUpload(HttpServletRequest request, HttpServletResponse response){
		try {
			util.fileupload(this, request, "adminphoto", ((Admin)(request.getSession().getAttribute("user"))).getAdminID()+".jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
