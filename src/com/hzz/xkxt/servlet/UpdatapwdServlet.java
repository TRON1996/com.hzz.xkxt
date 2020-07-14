package com.hzz.xkxt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.factory.AdminFactory;
import com.hzz.xkxt.factory.AdminMgrInterface;
@WebServlet("/UpdatapwdServlet")
public class UpdatapwdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdatapwdServlet() {
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
		if(mg.equals("reset")){
			try {
				Adminpwdreset(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("admin/adminmgr.jsp");
		}
	}
		private void Adminpwdreset(HttpServletRequest request,HttpServletResponse response) throws SQLException {
			// TODO Auto-generated method stub
			AdminMgrInterface ami=AdminFactory.getInstance();
			Admin admin=new Admin();	
			admin.setAdminID(request.getParameter("AdminID"));
			admin.setPassword(request.getParameter("oldpwd"));
			
			if(ami.login(admin)==null){
				return;
			}		
			String newpwd=request.getParameter("newpwd");
			String newpwd1=request.getParameter("newpwd1");
		    if(!newpwd1.equals(newpwd)){
		    	return;
		    }
		    admin.setPassword(newpwd);
			ami.resetpwd(admin);
		}
	}


