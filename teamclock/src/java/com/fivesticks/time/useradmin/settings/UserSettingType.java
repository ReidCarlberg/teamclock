package com.fivesticks.time.useradmin.settings;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.StringUtils;

import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;

/**
 * 2005-10-6
 * 
 * @author Nick This must be coordinated with the UserSetingType.
 */
public class UserSettingType extends ObjectMetricTypeEnum {

    private static Collection all = new ArrayList();

    /*
     * Nick 2005-10-6 Added user settings 2006-05-17 RSC Added default login
     */
    public static final UserSettingType LOGIN_DESTINATION = new UserSettingType(
            "LOGIN_DESTINATION");

    public static final UserSettingType CALENDAR_NOTIFICATION_ON_OR_OFF = new UserSettingType(
            "CALENDAR_NOTIFICATION_ON_OR_OFF");

    public static final UserSettingType CALENDAR_NOTIFICATION_EMAIL_ADDRESS = new UserSettingType(
            "CALENDAR_NOTIFICATION_EMAIL_ADDRESS");

    public static final UserSettingType CALENDAR_DEFAULT_DASHBOARD_VIEW = new UserSettingType(
            "CALENDAR_DEFAULT_DASHBOARD_VIEW");

    public static final UserSettingType CALENDAR_DEFAULT_TAB_VIEW = new UserSettingType(
            "CALENDAR_DEFAULT_TAB_VIEW");

    public static final UserSettingType SHOW_TIME_CLOCK_STATUS = new UserSettingType(
            "SHOW_TIME_CLOCK_STATUS");

    public static final UserSettingType USER_TIME_ZONE = new UserSettingType(
            "USER_TIME_ZONE");

    /**
     * @param arg0
     */
    public UserSettingType(String arg0) {
        super(arg0);
        all.add(this);
    }

    /*
     * This will take the name and parse it to a ognl expression to get and set
     * the corrisponding param of the UserSettingVO.
     */
    public String getOgnl() {
        StringBuffer buffer = new StringBuffer();
        String[] tok = this.getName().split("_");

        for (int i = 0; i < tok.length; i++) {
            String string = tok[i];
            if (i == 0) {
                buffer.append(string.toLowerCase());
            } else {
                buffer.append(StringUtils.capitalize(string.toLowerCase()));
            }
        }
        return buffer.toString();
    }

    public static Collection getAll() {
        return all;
    }

}
