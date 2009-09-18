/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 * 
 */
public class SystemHealthMonitorFilter implements Filter {

    private static Log log = LogFactory.getLog(SystemHealthMonitorFilter.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {

//         log.info("yeah baby in the filter chain.");

        if (SystemHealthMonitor.singleton.getCurrentIssues().size() > 0) {

            log.info("Found errors.");

            log.info("First: "
                    + SystemHealthMonitor.singleton.getCurrentIssues()
                            .toArray()[0]);

            arg1.setContentType("text/html");

            PrintWriter out = arg1.getWriter();

            out.println("<html><head></head><body>\n");

            out.println("<h2>Startup Failure</h2>\n");

            out.println("<p>fstxTIME failed to start properly.\n");

            out.println("<p><a href=\"error.jsp\">Error List</a>\n");

            out.println("</body></html>\n");

            arg1.flushBuffer();
            
            return;
        }
        
        /*
         * 2005-11-27 RSC refactored this here.
         */
        // log.info("no issues found...");
        try {
//            log.info("filter " + arg2.toString());
            arg2.doFilter(arg0, arg1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }


        
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {

    }

}