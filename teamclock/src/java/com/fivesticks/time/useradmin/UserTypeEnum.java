/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class UserTypeEnum extends Enum {

    public static final UserTypeEnum EXTERNAL = new UserTypeEnum(
            "USERTYPE_EXTERNAL", "External Customer");

    public static final UserTypeEnum TIMECLOCK = new UserTypeEnum(
            "USERTYPE_TIMECLOCK", "Time Clock Only");

    public static final UserTypeEnum STANDARD = new UserTypeEnum(
            "USERTYPE_STANDARD", "Standard");

    public static final UserTypeEnum PRIVILEGED = new UserTypeEnum(
            "USERTYPE_PRIVILEGED", "Privileged");

    public static final UserTypeEnum OWNERADMIN = new UserTypeEnum(
            "USERTYPE_OWNERADMIN", "Owner/Admin");

    private static Collection types;

    private final String publicName;

    private UserTypeEnum(String privateName, String publicName) {
        super(privateName);
        this.publicName = publicName;
    }

    public static Collection getTypes() {

        if (types == null) {
            types = new ArrayList();
            types.add(UserTypeEnum.TIMECLOCK);
            types.add(UserTypeEnum.STANDARD);
            types.add(UserTypeEnum.PRIVILEGED);
            types.add(UserTypeEnum.OWNERADMIN);
            types.add(UserTypeEnum.EXTERNAL);
        }

        return types;
    }

    public synchronized static UserTypeEnum getByName(String privateName) {
        UserTypeEnum ret = null;

        for (Iterator i = UserTypeEnum.getTypes().iterator(); i.hasNext();) {
            UserTypeEnum current = (UserTypeEnum) i.next();
            if (current.getPrivateName().equals(privateName)) {
                ret = current;
                break;
            }
        }

        return ret;
    }

    public String getPrivateName() {
        return this.getName();
    }

    public String getPublicName() {
        return this.publicName;
    }

}