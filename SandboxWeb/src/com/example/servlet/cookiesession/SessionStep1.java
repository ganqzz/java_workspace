package com.example.servlet.cookiesession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionStep1
 */
@WebServlet("/SessionStep1")
public class SessionStep1 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String guestName = request.getParameter("guestName");
        HttpSession session = request.getSession();
        session.setAttribute("guestName", guestName);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Greetings </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Welcome " + guestName + "</p>");
        out.println("<form name='frm' action='SessionStep2' method='post'>");
        out.println("<p>Enter Email Id : </p>");
        out.println("<p><input type='email' name='email' /> </p>");
        out.println("<p><input type='submit' value='Show Preview' name='btnPreview' /> </p>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
