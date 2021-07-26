package com.masayukikaburagi.servlet;

import com.masayukikaburagi.controller.LoclogController;
import com.masayukikaburagi.util.Util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocationManager
 */
@WebServlet(value = "/loclog/*", name = "LoclogServlet")
public class LoclogServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServletContext sctx;
    private LoclogController llc;

    /**
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // sctx = getServletContext(); // ドキュメント上は、↓の短縮版のはずだが、ぬるぽ
        sctx = config.getServletContext();
        llc = new LoclogController(sctx);
    }

    /**
     * GET
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = (String) request.getAttribute("userName");
        String pi = request.getPathInfo();

        if (pi == null) {
            // GET /page/1 として扱う
            pi = "/page/1";
        }

        String[] pia = pi.split("/", -1); // PATH_INFOが"/"のときは、length=2

        // GET /{logId}
        Integer logId = Util.toInteger(pia[1]);
        if (logId != null) {
            String loclogJson = null;
            if (pia.length >= 3 && pia[2].equals("image")) { // GET /{logId}/image
                loclogJson = llc.getLoclogImage(userName, logId);
            } else {
                loclogJson = llc.getLoclogDetails(userName, logId);
            }

            if (loclogJson == null) {
                // Not found
                setJsonResponse(response, null, HttpServletResponse.SC_NOT_FOUND);
            } else {
                setJsonResponse(response, loclogJson, HttpServletResponse.SC_OK);
            }

            return;
        }

        // GET /{数字以外}
        switch (pia[1]) {
        case "": // fall through
        case "page":
            // GET /page/{page}
            int page = 1;
            if (pia.length == 3) {
                Integer n = Util.toInteger(pia[2]);
                if (n != null && n > 1) page = n;
            }

            // ?per={PER_PAGE} is optional(>=10).
            int per = 10;
            String perParam = request.getParameter("per");
            if (perParam != null) {
                Integer p = Util.toInteger(perParam);
                if (p != null && p > per) per = p;
            }

            setJsonResponse(response, llc.getLoclogsList(userName, page, per),
                            HttpServletResponse.SC_OK);
            return;

        case "total": // GET /total
            setJsonResponse(response, llc.getLoclogsTotal(userName), HttpServletResponse.SC_OK);
            return;

        case "mtime": // GET /mtime
            setJsonResponse(response, llc.getLastMtime(userName), HttpServletResponse.SC_OK);
            return;

        case "search": // GET /search?{QUERY_STRING}
            // {QUERY_STRING} is mandatory.
            String qs = request.getQueryString();
            if (qs != null) {
                String res = llc.searchLoclog(userName, qs);
                setJsonResponse(response, res, HttpServletResponse.SC_OK);
                return;
            }

        case "export": // GET /export
            setJsonResponse(response, llc.export(), HttpServletResponse.SC_OK);
            return;
        }

        // エラー
        setJsonResponse(response, null, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * POST
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = (String) request.getAttribute("userName");
        String errors = null;

        String pi = request.getPathInfo();
        if (pi == null || pi.trim().equals("/")) {
            //
            String json = Util.convertInputStreamToString(request.getInputStream());
            if (json.isEmpty()) {
                errors = "{\"kind\":\"request\",\"message\":\"request body is required\"}";
            } else {
                errors = llc.createLoclog(userName, json);
                if (errors == null) {
                    setJsonResponse(response, null, HttpServletResponse.SC_CREATED);
                    return;
                }
            }
        }

        // エラー
        setJsonResponse(response, errors, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * PUT
     *
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = (String) request.getAttribute("userName");
        String errors = null;

        String pi = request.getPathInfo();
        if (pi == null || pi.trim().equals("/")) {
            //
            String json = Util.convertInputStreamToString(request.getInputStream());
            if (json.isEmpty()) {
                errors = "{\"kind\":\"request\",\"message\":\"request body is required\"}";
            } else {
                errors = llc.updateLoclog(userName, json);
                if (errors == null) {
                    setJsonResponse(response, null, HttpServletResponse.SC_OK);
                    return;
                }
            }
        }

        // エラー
        setJsonResponse(response, errors, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * DELETE
     *
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = (String) request.getAttribute("userName");
        String pi = request.getPathInfo();
        if (pi != null && !pi.trim().isEmpty()) {
            String[] pia = pi.split("/", -1); // PATH_INFOが"/"のときは、length=2

            // DELETE /{logId}
            Integer logId = Util.toInteger(pia[1]);
            if (logId != null && llc.deleteLoclogById(userName, logId)) {
                setJsonResponse(response, null, HttpServletResponse.SC_OK);
                return;
            }
        }

        // エラー
        setJsonResponse(response, null, HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * Set response 共通処理
     *
     * @param response
     * @param json
     * @param sc
     * @throws IOException
     */
    private void setJsonResponse(HttpServletResponse response, String json, int sc) throws
                                                                                    IOException {
        // ステータスコード
        response.setStatus(sc); // body出力の前に実行する必要がある。

        if (json != null) {
            // Content-Type指定
            response.setContentType("application/json; charset=UTF-8");

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        }
    }

    /**
     * Set response 共通処理
     *
     * @param response
     * @param image
     * @param sc
     * @throws IOException
     */
    private void setImageResponse(HttpServletResponse response, byte[] image, int sc) throws
                                                                                      IOException {
        // ステータスコード
        response.setStatus(sc); // body出力の前に実行する必要がある。

        if (image != null) {
            // Content-Type指定
            response.setContentType("image/jpeg");

            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.close();
        }
    }
}
