package com.estsoft.GuestBook2.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.DB.MySQLWebDBConnection;
import com.estsoft.GuestBook.dao.GuestBookDao;
import com.estsoft.GuestBook.vo.GuestBookVo;

/**
 * Servlet implementation class GuestBook2Servlet
 */
@WebServlet("/el")
public class GuestBook2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// utf-8
		response.setCharacterEncoding("UTF-8");

		// 요청분석

		String actionName = request.getParameter("a");

		// add 진행
		if ("add".equals(actionName)) {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String passwd = request.getParameter("password");
			String message = request.getParameter("content");
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setMessage(message);
			vo.setPasswd(passwd);

			GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
			dao.insert(vo);

			// insert를 하는 페이지에서 계속 F5를 누르면 계속 INSERT가 진행된다 -> 302 redirect를 이용해서
			// insert가 추가로 되지않도록
			response.sendRedirect("/GuestBook2/el");

		}
		
		// deleteform (no 갖고왔고, 비번칠거임)
		else if ("deleteform".equals(actionName)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/deleteform.jsp");
			String no = request.getParameter("id");
			
			rd.forward(request, response);
		}
		
		//delete
		else if ("delete".equals(actionName)) {
			//있어도 그만, 없어도 그만?
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/deleteform.jsp");
			
			String passwd = request.getParameter("password");
			String no = request.getParameter("id");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPasswd(passwd);
			GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
			dao.delete(vo);

			response.sendRedirect("/GuestBook2/el");
		}
		
		// index
		else {

			GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
			List<GuestBookVo> list = dao.getList();
			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
			rd.forward(request, response);
		}

	}

}
