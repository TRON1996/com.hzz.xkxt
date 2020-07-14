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

import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.factory.AdminFactory;
import com.hzz.xkxt.factory.AdminMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AdminServlet() {
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
		if(mg.equals("login")){
			try {
				login(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mg.equals("exit")){
			Adminexit(request,response);
		}
		if(mg.equals("reset")){
			Adminpwdreset(request,response);
			response.sendRedirect("admin/adminmgr.jsp");
		}
	
		if(mg.equals("in")){
			AdiminmgrInsert(request,response);
			response.sendRedirect("admin/adminmgr.jsp");
		}
		if(mg.equals("up")){
			AdiminmgrUpdate(request,response);
			response.sendRedirect("admin/adminmgr.jsp");
		}
		if(mg.equals("de")){			
			AdiminmgrDelete(request,response);
			response.sendRedirect("admin/adminmgr.jsp");
		}
         if(mg.equals("se")){
        	 try {
        		 AdiminmgrSelect(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}

	

	private void Adminpwdreset(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAdminID(request.getParameter("AdminID"));
		admin.setPassword("123456");
		AdminMgrInterface ami=AdminFactory.getInstance();
		ami.resetpwd(admin);
	}

	private void Adminexit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("user", null);
		response.sendRedirect("admin/login.jsp");
	}

	private void AdiminmgrSelect(HttpServletRequest request,HttpServletResponse response)throws SQLException, IOException  {
		// TODO Auto-generated method stub
		AdminMgrInterface ami=AdminFactory.getInstance();
		List<Admin> alist = ami.select();
		
		JSONArray json=JSONArray.fromObject(alist);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();	
	}

	private void AdiminmgrDelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAdminID(request.getParameter("AdminID"));
		AdminMgrInterface ami=AdminFactory.getInstance();
		response.sendRedirect("utf-8");
		ami.delete(admin);	
	}

	private void AdiminmgrUpdate(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAdminID(request.getParameter("AdminID"));
		admin.setAdminName(request.getParameter("AdminName"));
		admin.setRole(request.getParameter("Role"));
		admin.setRemark(request.getParameter("Remark"));
		
		AdminMgrInterface ami=AdminFactory.getInstance();
		ami.update(admin);
	}

	private void AdiminmgrInsert(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAdminID(request.getParameter("AdminID"));
		admin.setAdminName(request.getParameter("AdminName"));
		admin.setPassword("123456");
		admin.setPhoto(request.getParameter("AdminID"));
		admin.setRole(request.getParameter("Role"));
		admin.setRemark(request.getParameter("Remark"));
		
		AdminMgrInterface ami=AdminFactory.getInstance();
		ami.insert(admin);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException  {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAdminID(request.getParameter("AdminID"));
		admin.setPassword(request.getParameter("Password"));
		
		AdminMgrInterface ami=AdminFactory.getInstance();
		Admin theadmin=ami.login(admin);
		if(theadmin!=null){
			request.getSession().setAttribute("user", theadmin);
			response.sendRedirect("admin/collegemgr.jsp");
		
		}else{
			request.setAttribute("msg", "用户名或密码错误！");
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
	}
}
