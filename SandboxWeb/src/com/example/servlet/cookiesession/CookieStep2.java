package com.example.servlet.cookiesession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieStep2
 */
@WebServlet("/CookieStep2")
public class CookieStep2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        Cookie userData[] = request.getCookies();
        String guestName = userData[0].getValue();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Preview </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Please confirm your information </p>");
        out.println("<p>Guest Name : " + guestName + "</p>");
        out.println("<p>Email Id : " + email + "</p>");
        Cookie emailData = new Cookie("email", email);
        response.addCookie(emailData);
        out.println("<form name='frm' action='CookieSavedData' method='post'>");
        out.println("<p><input type='submit' value='Save Data' name='btnSave' />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
