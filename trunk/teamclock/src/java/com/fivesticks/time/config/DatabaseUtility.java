package com.fivesticks.time.config;

import org.hibernate.MappingException;
import org.hibernate.cfg.Configuration;



/**
 * @author REID
 *  
 */
class DatabaseUtility {

    public static final DatabaseUtility singleton = new DatabaseUtility();

    public Configuration getTables() throws MappingException {

        return getConfiguration("");

    }

    public Configuration getConfiguration(String prefix)
            throws MappingException {

        Configuration cfg = new Configuration();

        cfg.addFile(prefix
                + "com/fivesticks/time/settings/systemsettings.hbm.xml");

        cfg.addFile(prefix + "com/fstx/stdlib/authen/LoginHistory.hbm.xml");

        cfg.addFile(prefix + "com/fstx/stdlib/authen/users/User.hbm.xml");
        cfg.addFile(prefix + "com/fstx/stdlib/authen/users/UserGroup.hbm.xml");

        //admin & setup tables
        cfg.addFile(prefix + "com/fivesticks/time/hours/FstxTime.hbm.xml");
        cfg.addFile(prefix
                + "com/fivesticks/time/calendar/FstxCalendar.hbm.xml");
        cfg.addFile(prefix
                + "com/fivesticks/time/timeclock/FstxTimeclock.hbm.xml");
        cfg.addFile(prefix
                + "com/fivesticks/time/customer/FstxCustomer.hbm.xml");
        cfg
                .addFile(prefix
                        + "com/fivesticks/time/customer/FstxProject.hbm.xml");
        cfg.addFile(prefix + "com/fivesticks/time/customer/FstxTask.hbm.xml");

        return cfg;

    }
}