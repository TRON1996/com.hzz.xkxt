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

import com.hzz.xkxt.bean.Stuxkb;
import com.hzz.xkxt.bean.Student;
import com.hzz.xkxt.factory.StuxkbFactory;
import com.hzz.xkxt.factory.StuxkbMgrInterface;

import net.sf.json.JSONArray;
@WebServlet("/StuxkbServlet")
public class StuxkbServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public StuxkbServlet() {
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
		
         if(mg.equals("se")){
        	 try {
        		 Select(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}

	private void Select(HttpServletRequest request,HttpServletResponse response)throws SQLException, IOException  {
		// TODO Auto-generated method stub
		StuxkbMgrInterface smi=StuxkbFactory.getInstance();
		List<Stuxkb> slist = smi.select(((Student)(request.getSession().getAttribute("user"))).getStudentID());		
		JSONArray json=JSONArray.fromObject(slist);
		System.out.print(json);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(json.toString());
		pw.close();
		pw.flush();	
	}

}
