/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class SettingFeatureSetTypeEnum extends Enum {

    public static final SettingFeatureSetTypeEnum SUPERUSER = new SettingFeatureSetTypeEnum(
            "SUPERUSER");

    public static final SettingFeatureSetTypeEnum COMPLETE = new SettingFeatureSetTypeEnum(
            "COMPLETE");

    public static final SettingFeatureSetTypeEnum GENERAL = new SettingFeatureSetTypeEnum(
            "GENERAL");

    public static final SettingFeatureSetTypeEnum BASIC = new SettingFeatureSetTypeEnum(
            "BASIC");

    private static Collection allTypes;

    /**
     * @param arg0
     */
    public SettingFeatureSetTypeEnum(String arg0) {
        super(arg0);

    }

    public static Collection getAllTypes() {
        if (allTypes == null) {
            allTypes = new ArrayList();
            allTypes.add(SUPERUSER);
            allTypes.add(COMPLETE);
            allTypes.add(GENERAL);
            allTypes.add(BASIC);
        }

        return allTypes;
    }

    public static SettingFeatureSetTypeEnum getByName(String name) {
        SettingFeatureSetTypeEnum ret = null;

        for (Iterator iter = SettingFeatureSetTypeEnum.getAllTypes().iterator(); iter
                .hasNext();) {
            SettingFeatureSetTypeEnum element = (SettingFeatureSetTypeEnum) iter
                    .next();
            if (element.getName().equals(name)) {
                ret = element;
                break;
            }
        }

        return ret;
    }

}