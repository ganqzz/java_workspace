package com.masayukikaburagi.filter;

import com.masayukikaburagi.controller.UserController;
import com.masayukikaburagi.util.Util;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 認証フィルタ
 * Basic認証をするに当たって、DBからユーザ情報を引っ張るため、Filterで実装。
 * <p/>
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(
        filterName = "AuthenticationFilter",
        dispatcherTypes = {DispatcherType.REQUEST},
        servletNames = {"LoclogServlet"})
public class AuthenticationFilter implements Filter {
    ServletContext sctx;

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsreq = (HttpServletRequest) request;
        HttpServletResponse hsres = (HttpServletResponse) response;

        /*
         * エラーチェック
         * 引っ掛かった時点で、即退場
         */
        // Authorizationヘッダ
        String authh = hsreq.getHeader("Authorization");
        if (authh == null) {
            hsres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Base64デコード
        String auth = Util.decodeBase64ToString(authh.replaceFirst("Basic", "").trim());
        if (auth == null) {
            hsres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 分割
        String[] autha = auth.split(":", -1);
        if (autha.length < 2) {
            hsres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // DB照会
        String userName = autha[0];
        String password = Util.encryptPassword(autha[1]); // 暗号化

        UserController uc = new UserController(sctx);
        if (!uc.isLegitimateUser(userName, password)) {
            hsres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // OK
        hsreq.setAttribute("userName", userName); // 属性追加
        hsres.setStatus(HttpServletResponse.SC_OK);

        // サーブレットへ: pass the request along the filter chain
        chain.doFilter(hsreq, hsres);

        // サーブレットから: after the filter chain
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        sctx = fConfig.getServletContext();
    }
}
