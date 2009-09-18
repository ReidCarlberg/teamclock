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
public class PopUpCalendarHeadTag extends BodyTagSupport {

    /**
     * this needs to take the right code, the authenticated user, and check
     * authorization. if not authorized, we skip the body. If authorized, the
     */
    public int doStartTag() {
        //<!-- // create calendar object(s) just after form tag closed
        // specify form element as the only parameter
        // (document.forms['formname'].elements['inputname']);
        // note: you can have as many calendar objects as you need for your
        // application
        //-->
        String ahref = "<script language=\"JavaScript\" src=\"/fstxtime/includes/calendar2.js\"></script>";

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

    static Log log = LogFactory.getLog(PopUpCalendarHeadTag.class.getName());

}