package com.masayukikaburagi.servlet;

import com.masayukikaburagi.controller.LoclogController;
import com.masayukikaburagi.util.Util;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(value = "/test/*", name = "TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ServletContext sctx;
    private LoclogController llc;

    /**
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // sctx = getServletContext(); // ぬるぽ
        sctx = config.getServletContext();
        // llc = new LoclogController();
        llc = new LoclogController(sctx);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("--- URL");
        out.println(request.getRequestURL());
        out.println("--- URI");
        out.println(request.getRequestURI());
        out.println("--- Path Info");
        out.println("--- Qeury String");
        out.println(request.getQueryString());

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

        out.println("--- etc");
        // response.sendError(arg0);
        out.println(response.getStatus());
        out.println(request.getAttribute("userName")); // Case-Sensitive
        out.println(request.getHeader("Authorization")); // Case-Insensitive
        out.println(sctx.getAttribute("DbManager")); // Case-Sensitive
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String json = Util.convertInputStreamToString(request.getInputStream());

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(json);
    }
}
