/*
 * Created on Mar 11, 2005 by Reid
 */
package com.fivesticks.time.useradmin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class UserPasswordComplexityEnum extends Enum {

    public static final UserPasswordComplexityEnum STANDARD = new UserPasswordComplexityEnum("STANDARD");

    public static final UserPasswordComplexityEnum PARANOID = new UserPasswordComplexityEnum("PARANOID");
    
    private static Collection all;
    /**
     * @param arg0
     */
    protected UserPasswordComplexityEnum(String arg0) {
        super(arg0);
    }
    
    public static Collection getAll() {
        if (all == null) {
            all = new ArrayList();
            all.add(STANDARD);
            all.add(PARANOID);
        }
        return all;
    }
    
    public static UserPasswordComplexityEnum getSingle(String name) {
        UserPasswordComplexityEnum ret = null;
        for (Iterator iter = getAll().iterator(); iter.hasNext();) {
            UserPasswordComplexityEnum element = (UserPasswordComplexityEnum) iter.next();
            if (element.getName().equalsIgnoreCase(name)) {
                ret = element;
                break;
            }
        }
        return ret;
    }

}
