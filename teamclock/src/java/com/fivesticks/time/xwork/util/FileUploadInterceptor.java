/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.time.xwork.util;

//FileUploadInterceptor.java


import java.io.File;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.dispatcher.multipart.MultiPartRequestWrapper;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

/**
 * Imported wholesale from WW Wiki.
 */
public class FileUploadInterceptor implements Interceptor {
    protected static final Log log = LogFactory.getLog(FileUploadInterceptor.class);

    protected String allowedTypes;
    protected String disallowedTypes;
    protected Long maximumSize;

    /**
     */
    public FileUploadInterceptor() {
        if (log.isDebugEnabled()) log.debug("new FileUploadInterceptor()");
    }

    /**
     */
    public void init() {
        if (log.isDebugEnabled()) log.debug("init()");
    }

    /**
     */
    public void destroy() {
        if (log.isDebugEnabled()) log.debug("destroy()");
    }

    /**
     * list of allowed mime-types, optional
     */
    public void setAllowedTypes(String allowedTypes) {
        this.allowedTypes = allowedTypes;
    }

    /**
     * list of diallowed mime-types, optional
     */
    public void setDisallowedTypes(String disallowedTypes) {
        this.disallowedTypes = disallowedTypes;
    }

    /**
     * maximum file Size, optional
     */
    public void setMaximumSize(Long maximumSize) {
        this.maximumSize = maximumSize;
    }

    /**
     *
     */
    public String intercept(ActionInvocation invocation) throws Exception {
        if (!(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)) {
            if (log.isDebugEnabled()) log.debug("bypass " + invocation.getProxy().getNamespace() + "/" + invocation.getProxy().getActionName());
            return invocation.invoke();
        }
        MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
        if (multiWrapper.hasErrors()) {
            Collection errors = multiWrapper.getErrors();
            Iterator i = errors.iterator();
            while (i.hasNext()) {
                //how to get to addError() from here?
                log.error((String) i.next());
            }
        }

        Enumeration e = multiWrapper.getFileNames();
        

        //Bind allowed Files
        while (e.hasMoreElements()) {
            // get the value of this input tag
            String inputName = (String) e.nextElement();
            // get the content type
            String contentType = multiWrapper.getContentType(inputName);
            // get the name of the file from the input tag
            String fileName = multiWrapper.getFilesystemName(inputName);
            // Get a File object for the uploaded File
            File file = multiWrapper.getFile(inputName);

            log.info("file " + inputName + " " + contentType + " " + fileName + " " + file);

            // If it's null the upload failed
            if (file == null) {
                log.error("Error uploading: " + fileName);
            } else {
                if (acceptFile(file, contentType, inputName))
                    invocation.getInvocationContext().getParameters().put(inputName, file);
                // Do additional processing/logging...
            }
        }

        //Invoke Action
        /*
         * needs to move from the temp file to the more perm loc
         */
        String result = invocation.invoke();

        //Cleanup
        e = multiWrapper.getFileNames();
        while (e.hasMoreElements()) {
            String inputValue = (String) e.nextElement();
            File file = multiWrapper.getFile(inputValue);
            log.info("removing file " + inputValue + " " + file);
            if (file != null && file.isFile()) file.delete();
        }

        return result;
    }

    //overload this method to modify accept behaviour
    //TODO: addErrors?
    //TODO: i18n!
    protected boolean acceptFile(File file, String contentType, String inputName) {
        if (log.isDebugEnabled()) log.debug("checking" + inputName + " " + file.getName() + " " + file.length() + " " + contentType);
        if (maximumSize != null && maximumSize.longValue() < file.length())
            log.error("file is too long:" + inputName + " " + file.getName() + " " + file.length());
        else if (allowedTypes != null && allowedTypes.indexOf(contentType) < 0)
            log.error("Content-Type not allowed:" + inputName + " " + file.getName() + " " + contentType);
        else if (disallowedTypes != null && disallowedTypes.indexOf(contentType) >= 0)
            log.error("Content-Type disallowed:" + inputName + " " + file.getName() + " " + contentType);
        //somehow we need to set error messages here...
        else {
            if (log.isDebugEnabled()) log.debug("accepted");
            return true;
        }
        if (log.isDebugEnabled()) log.debug("not accepted");
        return false;
    }

}