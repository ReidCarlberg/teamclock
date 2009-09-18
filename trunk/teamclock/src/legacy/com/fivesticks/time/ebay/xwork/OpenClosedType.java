/*
 * Created on Mar 26, 2005 by Reid
 */
package com.fivesticks.time.ebay.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.util.DescriptiveEnum;

/**
 * @author Reid
 */
public class OpenClosedType extends DescriptiveEnum {

    private static Collection all;

    public static final OpenClosedType OPEN = new OpenClosedType("OPEN",
            "All Open");

    public static final OpenClosedType OPEN_NOT_READY = new OpenClosedType("OPEN_NOT_READY",
    	"Open Not Ready");
    
    public static final OpenClosedType READY_OR_WAITING = new OpenClosedType("READY_OR_WAITING",
	"Ready Or Waiting");    

    public static final OpenClosedType CLOSED = new OpenClosedType("CLOSED",
            "All Closed");

    public static final OpenClosedType ALL = new OpenClosedType("ALL", "All");

    /**
     * @param shortName
     * @param friendlyName
     */
    public OpenClosedType(String shortName, String friendlyName) {
        super(shortName, friendlyName);
        addToCollection(this);
    }

    private static void addToCollection(OpenClosedType target) {
        if (getAll() == null)
            all = new ArrayList();

        all.add(target);
    }

    public static Collection getAll() {
        return all;
    }

    public static OpenClosedType getByName(String target) {

        if (getAll() == null)
            return null;

        OpenClosedType ret = null;

        for (Iterator iter = getAll().iterator(); iter.hasNext();) {
            OpenClosedType element = (OpenClosedType) iter.next();

            if (element.getName().equals(target)) {
                ret = element;
                break;
            }
        }

        return ret;

    }

}