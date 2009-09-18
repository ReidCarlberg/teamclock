/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 *  
 */
public class SystemHealthMonitor {

    private static Log log = LogFactory.getLog(SystemHealthMonitor.class);

    public static final SystemHealthMonitor singleton = new SystemHealthMonitor();

    private ArrayList currentIssues;

    private SystemHealthMonitor() {

    }

    public void initialize() {
        currentIssues = new ArrayList();
    }

    /**
     * @return
     */
    public ArrayList getCurrentIssues() {
        if (currentIssues == null)
            currentIssues = new ArrayList();
        return currentIssues;
    }

    /**
     * @param list
     */
    public void setCurrentIssues(ArrayList list) {
        log.info("ArrayList start");
        currentIssues = list;
        log.info("ArrayList stop");
    }

}