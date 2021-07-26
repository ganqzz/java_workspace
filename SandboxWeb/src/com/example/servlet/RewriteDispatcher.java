package com.example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetBeansAllPropertiesLoginuser
 */
@WebServlet("/rewrite_params.do")
public class RewriteDispatcher extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String params = String.format("?firstName=%s&lastName=%s"
				, request.getParameter("fn"), request.getParameter("ln"));
		RequestDispatcher rd = request.getRequestDispatcher("setBeanValue.jsp" + params);
		rd.forward(request, response);
	}
}
