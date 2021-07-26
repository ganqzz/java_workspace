package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/el-scope")
public class ELScopeDemo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("hoge", "HOGE");
        request.getSession().setAttribute("fuga", "FUGA");
        request.getServletContext().setAttribute("piyo", "PIYO");

        request.setAttribute("same", "RequestValue");
        request.getSession().setAttribute("same", "SessionValue");
        request.getServletContext().setAttribute("same", "ApplicationValue");

        request.getRequestDispatcher("el_scope.jsp").forward(request, response);
    }
}
