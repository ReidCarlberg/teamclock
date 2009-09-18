/*
 * Created on Jan 18, 2006
 *
 */
package com.fivesticks.common.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimerUtil {

    private Log log = LogFactory.getLog(TimerUtil.class);
    
    private Date startTime;

    private Date stopTime;
    
    private String name;

    public static TimerUtil getInstanceAndStart(String name) {
        
        TimerUtil ret = new TimerUtil();
        ret.name = name;
        ret.start();
        return ret;
    }
    
    public void start() {
        log.debug("Starting timer. (" + name + ")");
        startTime = new Date();
    }
    
    public void stop() {
        stopTime = new Date();
        log.info("Stop: " + getMsg(startTime, stopTime) );
    }
    
    public void soFar() {
        log.info("Time so far: " + getMsg(startTime, new Date()));
    }
    
    private String getMsg(Date startTime, Date stopTime) {
        return name + ": " +(stopTime.getTime() - startTime.getTime())/1000 + " seconds";
    }
}
