package com.fivesticks.time.common;

/**
 * Enabler for webwork to use.
 * 
 * @author Shuji
 *  
 */
public interface SessionContextAware {

    public void setSessionContext(SessionContext sessionContext);
}