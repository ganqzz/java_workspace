package com.example.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 * Servlet 3.0
 */
@WebServlet("/UploadServlet")
@MultipartConfig(
        location = "D:/tmp",
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
//        response.getWriter().println("Sorry!... GET method cant handle this request");
        response.sendRedirect("/file_upload.html");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        // セキュリティ確保のため、ファイルタイプによる選別をすること
        for (Part part : request.getParts()) {
            out.println(part.getHeader("Content-Disposition"));
//            String fileName = extractFileName(part);
            String fileName = part.getSubmittedFileName(); // EE7(3.1) null除けは必要
            if (fileName != null) {
                out.println(fileName);
//                part.write(fileName); // ファイルの書き込み
            }
        }
    }

    private String extractFileName(Part part) {
        for (String s : part.getHeader("Content-Disposition").split(";")) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf('=') + 2, s.length() - 1);
            }
        }
        return null; // file以外のtype属性（ex. submit）にname属性が付いている場合
    }
}
