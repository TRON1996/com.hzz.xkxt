package com.hzz.xkxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzz.xkxt.bean.Admin;
import com.hzz.xkxt.bean.Notice;
import com.hzz.xkxt.factory.NoticeFactory;
import com.hzz.xkxt.factory.NoticeInterface;
import com.hzz.xkxt.util.util;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String pd = request.getParameter("judge");
		if (pd.equals("in")) {
			insert(request, response);
			response.sendRedirect("admin/Admin/gonggaoup.jsp");
		}
		if (pd.equals("se")) {
			select(request, response);
		}
		if (pd.equals("Nse")) {
			NoticeSelect(request, response);
		}
		if (pd.equals("Upload")) {
			ImageUpload(request, response);
		}
		if (pd.equals("de")) {
			delete(request, response);
			response.sendRedirect("admin/Admin/gonggaoInfo.jsp");
		}
	}

	
	void delete(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Notice notice = new Notice();
		notice.setID(Integer.parseInt(request.getParameter("ID")));
		NoticeInterface nInterface = NoticeFactory.NoticeInterFace();
		nInterface.delete(notice);

	}

	// ¹«¸æÏêÇé
	void NoticeSelect(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		NoticeInterface nInterface = NoticeFactory.NoticeInterFace();
		String ID = request.getParameter("ID");
		Notice notice = nInterface.Noselect(ID);
		request.getSession().setAttribute("notice", notice);
		String user = request.getParameter("user");
		if (user.equals("A")) {
			response.sendRedirect("admin/gonggaoShow.jsp");
		}

	}


	void ImageUpload(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String filename = util.fileupload(this, request, "NoticeImage", "");
			
			String ImageContextPath = request.getContextPath() + "/"
					+ "NoticeImage" + "/" + filename;
			response.setContentType("text/html;charset=UTF-8");
			String callback = request.getParameter("CKEditorFuncNum");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + ImageContextPath + "',''" + ")");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void select(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeInterface nInterface = NoticeFactory.NoticeInterFace();
		List<Notice> list = nInterface.select();
		JSONArray json = JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(json.toString());
			pw.close();
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void insert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Notice notice = new Notice();
		Admin admin = (Admin) request.getSession().getAttribute("login");
		notice.setAdmin(admin);
		notice.setNoticeTitle(request.getParameter("NoticeTitle"));
		notice.setNoticeContent(request.getParameter("NoticeContent"));
		notice.setReleaseTime(util.getNowTime());
		NoticeInterface nInterface = NoticeFactory.NoticeInterFace();
		if (nInterface.insert(notice)) {
			request.getSession().setAttribute("AdInsert", "true");
		} else {
			request.getSession().setAttribute("AdInsert", "false");
		}

	}

}
