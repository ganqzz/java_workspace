package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClearSessionAndCookies
 */
@WebServlet("/clear_session_cookies")
public class ClearSessionAndCookies extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate session 
        request.getSession().invalidate();

        // remove all cookies
        response.setContentType("text/html");
        Cookie[] MyCookies = request.getCookies();
        if (MyCookies != null && MyCookies.length > 0) {
            for (Cookie c : MyCookies) {
                if (c.getName().toLowerCase().contains("credentials")) {
                    // expire the cookie
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        
        response.sendRedirect("JavaBeanSetDemo.jsp");
    }
}
