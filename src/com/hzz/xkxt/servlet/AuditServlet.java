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
import com.hzz.xkxt.bean.Audit;
import com.hzz.xkxt.bean.TeachTask;
import com.hzz.xkxt.factory.AuditFactory;
import com.hzz.xkxt.factory.AuditMgrInterface;
import com.hzz.xkxt.factory.TeachTaskFactory;
import com.hzz.xkxt.factory.TeachTaskMgrInterface;
import com.hzz.xkxt.util.util;

import net.sf.json.JSONArray;
@WebServlet("/AuditServlet")
public class AuditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AuditServlet() {
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
			response.sendRedirect("admin/audit.jsp");
		}
		if(mg.equals("up")){
			Updata(request,response);
			response.sendRedirect("admin/audit.jsp");
		}
		if(mg.equals("de")){
			Delete(request,response);
			response.sendRedirect("admin/audit.jsp");
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
		Audit audit=new Audit();
		audit.setTeachTaskID(request.getParameter("TeachTaskID"));
		AuditMgrInterface ami=AuditFactory.getInstance();
		ami.delete(audit);
	}

	private void Updata(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TeachTask tt=new TeachTask();
		tt.setAuditing(request.getParameter("Auditing"));
		TeachTaskMgrInterface ttmi=TeachTaskFactory.getInstance();
		ttmi.updata(tt);
	}

	private void Select(HttpServletRequest request,HttpServletResponse response)throws SQLException, IOException  {
		// TODO Auto-generated method stub
		AuditMgrInterface ami=AuditFactory.getInstance();
		List<Audit> alist = ami.select();		
		JSONArray json=JSONArray.fromObject(alist);
		System.out.print(json);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();	
	}


	private void Insert(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		Admin admin=(Admin)(request.getSession().getAttribute("user"));
		admin.setAdminID(admin.getAdminID());
		Audit aud=new Audit();
		aud.setAdmin(admin);
		aud.setAuditDate(util.getNowTime());
		aud.setTeachTaskID(request.getParameter("TeachTaskID"));
		AuditMgrInterface ami=AuditFactory.getInstance();
		ami.insert(aud);
	}
}
