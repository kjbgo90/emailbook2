package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmailBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.EmailBookVo;

@WebServlet("/eb")
public class EmailBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("emailbookServlet 실행");
		
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("action");
		
//		if("list".equals(actionName)) {
//			System.out.println("list요청");
//			
//			EmailBookDao dao = new EmailBookDao();
//			List<EmailBookVo> list = dao.getList();
//			
//			System.out.println(list.toString());
//			
//			request.setAttribute("emailList", list);
//			
//			WebUtil.forword(request, response, "/WEB-INF/list.jsp");
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			*/
//		} 
		
		if ("wform".equals(actionName)) {
			System.out.println("wform 요청");
			WebUtil.forword(request, response, "/WEB-INF/writeForm.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			*/
		} 
		
		else if("insert".equals(actionName)) {
			System.out.println("insert요청");
			
			String lastName = request.getParameter("ln");
			String firstName = request.getParameter("fn");
			String email = request.getParameter("email");
			
			EmailBookVo vo = new EmailBookVo();
			vo.setLastName(lastName);
			vo.setFirstName(firstName);
			vo.setEmail(email);
			
			EmailBookDao dao = new EmailBookDao();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "/emailbook2/eb?action=list");
//			response.sendRedirect("/emailbook2/eb?action=list");
		}
		else {
			System.out.println("list요청");
			
			EmailBookDao dao = new EmailBookDao();
			List<EmailBookVo> list = dao.getList();
			
			System.out.println(list.toString());
			
			request.setAttribute("emailList", list);
			
			WebUtil.forword(request, response, "/WEB-INF/list.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
