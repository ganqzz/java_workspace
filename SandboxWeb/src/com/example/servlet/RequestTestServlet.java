package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test/*")
public class RequestTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletContext sctx;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// sctx = getServletContext(); // ぬるぽ
		sctx = config.getServletContext();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("--- BEGIN");

		qTest(request, response);

		out.println("--- Headers");
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String str = e.nextElement();
			out.println(str + " : " + request.getHeader(str));
		}

		out.println("--- Attributes");
		e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			String str = e.nextElement();
			out.println(str + " : " + request.getAttribute(str));
		}

		out.println("--- END");
//        response.sendError(499);
        out.println(response.getStatus());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();

		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String line;
		while ((line = reader.readLine()) != null) {
			out.println(line.toUpperCase(Locale.JAPANESE));
		}
	}

	/**
	 * クエリtest
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void qTest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		out.println("--- URL");
		out.println(request.getRequestURL());
		out.println("--- URI");
		out.println(request.getRequestURI());

		// PATH_INFOのパース
		out.println("--- Path Info");
		out.println(request.getPathInfo());
		if (request.getPathInfo() != null) {
			String[] pia = request.getPathInfo().split("/", -1); // PATH_INFOが"/"のときは、length=2
			out.println(pia.length);
			for (int i = 0; i < pia.length; i++) {
				out.println(i + ": " + pia[i]);
			}
		}

		out.println("--- Qeury String");
		out.println(request.getQueryString());
		out.println("--- Parameters");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			out.println(paramName + " => " + paramValue);
		}
	}
}
