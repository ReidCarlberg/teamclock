/*
 * Created on Nov 27, 2005
 *
 */
package com.fivesticks.time.common.util;

import com.fivesticks.time.common.SpringBeanBroker;

public class SharedSystemProperties {

    public static final String SPRING_BEAN_NAME = "sharedSystemProperties";
    
    private static SharedSystemProperties singleton;
    
    private String defaultOwnerKey;
    
    

    /**
     * @return Returns the defaultOwnerKey.
     */
    public String getDefaultOwnerKey() {
        return defaultOwnerKey;
    }

    /**
     * @param defaultOwnerKey The defaultOwnerKey to set.
     */
    public void setDefaultOwnerKey(String defaultOwnerKey) {
        this.defaultOwnerKey = defaultOwnerKey;
    }
    
    public static SharedSystemProperties getSingleton() {
        if (singleton == null)
            singleton = (SharedSystemProperties) SpringBeanBroker.getBeanFactory().getBean(SharedSystemProperties.SPRING_BEAN_NAME);
        
        return singleton;
    }
}
