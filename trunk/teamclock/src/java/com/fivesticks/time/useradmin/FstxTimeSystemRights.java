package com.fivesticks.time.useradmin;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 *  
 */
public class FstxTimeSystemRights extends Enum {

    public static final FstxTimeSystemRights TIMECLOCK_ONLY = new FstxTimeSystemRights(
            "TIMECLOCK_ONLY");

    public static final FstxTimeSystemRights HOME = new FstxTimeSystemRights(
            "HOME");

    public static final FstxTimeSystemRights TIME = new FstxTimeSystemRights(
            "TIME");

    public static final FstxTimeSystemRights TIME_MODIFY_ALL = new FstxTimeSystemRights(
            "TIME_MODIFY_ALL");

    public static final FstxTimeSystemRights TIME_PROJECT_MODIFY = new FstxTimeSystemRights(
            "TIME_PROJECT_MODIFY");

    public static final FstxTimeSystemRights TIME_TASK_MODIFY = new FstxTimeSystemRights(
            "TIME_TASK_MODIFY");

    public static final FstxTimeSystemRights TIMECLOCK_MODIFY_SELF = new FstxTimeSystemRights(
            "TIMECLOCK_MODIFY_SELF");

    public static final FstxTimeSystemRights TIMECLOCK_MODIFY_ALL = new FstxTimeSystemRights(
            "TIMECLOCK_MODIFY_ALL");

    public static final FstxTimeSystemRights TIMECLOCK_ADD = new FstxTimeSystemRights(
            "TIMECLOCK_ADD");

    public static final FstxTimeSystemRights TIMECLOCK_LOGIN = new FstxTimeSystemRights(
            "TIMECLOCK_LOGIN");

    public static final FstxTimeSystemRights CALENDAR_VIEW_ALL = new FstxTimeSystemRights(
            "CALENDAR_VIEW_ALL");

    public static final FstxTimeSystemRights CALENDAR_MODIFY_ALL = new FstxTimeSystemRights(
            "CALENDAR_MODIFY_ALL");

    public static final FstxTimeSystemRights TODO_ALL = new FstxTimeSystemRights(
            "TODO_ALL");

    public static final FstxTimeSystemRights GENERAL_ADMINISTRATION = new FstxTimeSystemRights(
            "GENERAL_ADMINISTRATION");

    public static final FstxTimeSystemRights SYSTEMOWNER_ADMINISTRATION = new FstxTimeSystemRights(
            "SYSTEMOWNER_ADMINISTRATION");

    public static int id = 0;

    private int myId;

    /**
     * @param arg0
     */
    protected FstxTimeSystemRights(String name) {
        super(name);
        myId = ++id;
    }

    public static List getRights() {
        return FstxTimeSystemRights.getEnumList(FstxTimeSystemRights.class);
    }

    public static FstxTimeSystemRights getRight(String name) {
        return (FstxTimeSystemRights) FstxTimeSystemRights.getEnum(
                FstxTimeSystemRights.class, name);
    }

    public static FstxTimeSystemRights getRight(int id) {

        FstxTimeSystemRights ret = null;

        List l = getRights();

        Iterator i = l.iterator();

        while (i.hasNext()) {
            FstxTimeSystemRights r = (FstxTimeSystemRights) i.next();
            if (r.getMyId() == id) {
                ret = r;
                break;
            }

        }

        return ret;
    }

    /**
     * @return
     */
    public int getMyId() {
        return myId;
    }

    public String toString() {
        return this.getName();
    }

}