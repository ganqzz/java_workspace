package com.example.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(filterName = "/AuthenticationFilter", urlPatterns = "/SecuredServlet")
public class AuthenticationFilter implements Filter {

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("init method is called in " + getClass().getName());
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doFilter method is called in " + getClass().getName());

        String userName = request.getParameter("username");
        String passwd = request.getParameter("passwd");

        String ipAddress = request.getRemoteAddr();

        if (userName != null && passwd != null
                    && userName.equals("psuser") && passwd.equals("psguest")) {
            System.out.println("User logged in " + ipAddress + " at " + new Date().toString());
            chain.doFilter(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<h3>Sorry, You are not authorized to access this resource. </h3>");
        }
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
        System.out.println("destroy method is called in " + getClass().getName());
    }
}
