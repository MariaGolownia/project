package by.javatr.bicrent.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Wraps user http request in an XSSRequestWrapper object so it will never return unsafe parameters.
 * The suspicious parameters will be modified before returning.
 * It is a anti cross-site scripting (XSS) filter written for Java web applications.
 * It is remove all suspicious strings from request parameters before returning them to the application.
 * It's configured as the first filter in chain (web.xml) and it’s generally a helper
 * to catch every request made to the site.
 * The actual implementation consists of two classes, the actual filter is quite simple,
 * it wraps the HTTP request object in a specialized HttpServletRequestWrapper that will perform filtering.
 */

public class XSSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }

}