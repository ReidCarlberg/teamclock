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
public class PopUpCalendarSetupTag extends BodyTagSupport {

    private String name;

    private String formId;

    private String input;

    public int doStartTag() {
        //<!-- // create calendar object(s) just after form tag closed
        // specify form element as the only parameter
        // (document.forms['formname'].elements['inputname']);
        // note: you can have as many calendar objects as you need for your
        // application
        //-->
        //String ahref = "<script language=\"JavaScript\"> var "+ name+" = new
        // calendar2(document.forms['"+form+"'].elements['"+input+"']);"+name+".year_scroll
        // = true;"+name+".time_comp = false;</script> ";
        String ahref = "<script language=\"JavaScript\"> var " + name
                + " = new calendar2(document.forms[" + formId + "].elements['"
                + input + "']);" + name + ".year_scroll = true;" + name
                + ".time_comp = false;</script>  ";

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

    static Log log = LogFactory.getLog(PopUpCalendarSetupTag.class.getName());

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

    /**
     * @return
     */
    public String getFormId() {
        return formId;
    }

    /**
     * @return
     */
    public String getInput() {
        return input;
    }

    /**
     * @param string
     */
    public void setFormId(String string) {
        formId = string;
    }

    /**
     * @param string
     */
    public void setInput(String string) {
        input = string;
    }

}