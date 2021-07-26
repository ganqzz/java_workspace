package com.example.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncProgressServlet
 */
@WebServlet(urlPatterns = {"/AsyncProgressServlet"}, asyncSupported = true)
public class AsyncProgressServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Progress Servlet </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Entering doGet(), Thread Name : "
                            + Thread.currentThread().getName() + "</p>");
        out.println("<progress id='progress' max='100'> </progress>");
        AsyncContext aContext = request.startAsync();
        aContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                out.println("<p>onComplete(), Thread Name : "
                                    + Thread.currentThread().getName() + "</p>");
                out.println("</body>");
                out.println("</html>");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
            }
        });
        aContext.start(() -> {
            out.println("<p>Executing Long Running Task using Thread : "
                                + Thread.currentThread().getName() + "</p>");
            int i = 0;
            while (i <= 100) {
                out.println("<script>document.getElementById('progress').value = '"
                                    + i++ + "' ; </script>");
                out.flush();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            out.println("<p>Completed the long running task... </p>");
            aContext.complete();
        });

        out.println("<p>Exiting doGet(), Thread Name : "
                            + Thread.currentThread().getName() + "</p>");
    }
}
