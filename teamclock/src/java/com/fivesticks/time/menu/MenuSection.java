/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.menu;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class MenuSection extends Enum {

    public static final MenuSection HOME = new MenuSection("HOME");

    public static final MenuSection TODO = new MenuSection("TODO");

    public static final MenuSection ACTIVITY = new MenuSection("ACTIVITY");

    public static final MenuSection CALENDAR = new MenuSection("CALENDAR");

    public static final MenuSection TIMECLOCK = new MenuSection("TIMECLOCK");

    public static final MenuSection ADMIN = new MenuSection("ADMIN");
    
    public static final MenuSection CUSTOMERS = new MenuSection("CUSTOMERS");
    
    public static final MenuSection CONTACTS = new MenuSection("CONTACTS");

    public static final MenuSection HELP = new MenuSection("HELP");

    public static final MenuSection PASSWORD = new MenuSection("PASSWORD");

    public static final MenuSection SIGNIN = new MenuSection("SIGNIN");

    public static final MenuSection LOGOUT = new MenuSection("LOGOUT");

    public static final MenuSection TIMECLOCK_ONLY = new MenuSection(
            "TIMECLOCK_ONLY");

    /**
     * @param arg0
     */
    protected MenuSection(String arg0) {
        super(arg0);

    }

}