/*
 * Created on Aug 21, 2003
 *
 */
package com.fivesticks.time.ui.widgets.legacy;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Needs to take a property and, if it checks out from an authorization point of
 * view, then process the body.
 * 
 * @author Reid
 *  
 */
public class PopUpCalendarPopUpTag extends BodyTagSupport {

    private String name;

    /**
     *  
     */
    public int doStartTag() {

        String ahref = "<a href=\"javascript:"
                + name
                + ".popup();\"><img src=\"/"
                + "fstxtime"
                + "/images/cal.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"Click Here to Pick up the date\"></a>";
        JspWriter out = pageContext.getOut();
        try {
            out.print(ahref);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BodyTagSupport.SKIP_BODY;
    }

    /**
     * all this needs to do is quit.
     */
    public int doEndTag() {

        //log.info("ending the tag...");

        return BodyTagSupport.SKIP_BODY;
    }

    static Log log = LogFactory.getLog(PopUpCalendarPopUpTag.class.getName());

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

}