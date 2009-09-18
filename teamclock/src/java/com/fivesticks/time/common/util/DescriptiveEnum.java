/*
 * Created on Mar 26, 2005 by Reid
 */
package com.fivesticks.time.common.util;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public abstract class DescriptiveEnum extends Enum {

    private String friendlyName;

    public DescriptiveEnum(String shortName) {
        super(shortName);
        this.friendlyName = shortName;
    }
    /**
     * @param arg0
     */
    public DescriptiveEnum(String shortName, String friendlyName) {
        super(shortName);
        this.friendlyName = friendlyName;
    }

    /**
     * @return Returns the friendlyName.
     */
    public String getFriendlyName() {
        return friendlyName;
    }
    



}
