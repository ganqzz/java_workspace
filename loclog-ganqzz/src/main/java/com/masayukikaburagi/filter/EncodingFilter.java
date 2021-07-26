package com.masayukikaburagi.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Encodingフィルタ
 * Content-Encoding
 * Content-TypeのCharacterEncoding
 */
@WebFilter(
        filterName = "EncodingFilter",
        dispatcherTypes = {DispatcherType.REQUEST},
        urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // CharacterEncoding
        request.setCharacterEncoding("UTF-8");

        // pass the request along the filter chain
        chain.doFilter(request, response);

        // after the filter chain
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
