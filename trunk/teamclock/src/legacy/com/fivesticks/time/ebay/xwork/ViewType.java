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
public class ViewType extends DescriptiveEnum {

    private static Collection all;

    public static final ViewType STANDARD = new ViewType("STANDARD", "Standard");

    public static final ViewType STANDARD_DUMP = new ViewType("STANDARD_DUMP", "Standard (Dump)");
    
    public static final ViewType AGREEMENT = new ViewType("AGREEMENT",
            "Agreement");

    public static final ViewType STATUS_UPDATE = new ViewType("STATUS_UPDATE",
            "Status Update");

    /**
     * @param shortName
     * @param friendlyName
     */
    public ViewType(String shortName, String friendlyName) {
        super(shortName, friendlyName);
        addToCollection(this);
    }

    private static void addToCollection(ViewType target) {
        if (getAll() == null)
            all = new ArrayList();

        all.add(target);
    }

    public static Collection getAll() {
        return all;
    }

    public static ViewType getByName(String target) {

        if (getAll() == null)
            return null;

        ViewType ret = null;

        for (Iterator iter = getAll().iterator(); iter.hasNext();) {
            ViewType element = (ViewType) iter.next();

            if (element.getName().equals(target)) {
                ret = element;
                break;
            }
        }

        return ret;

    }

}