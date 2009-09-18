/*
 * Created on Jan 4, 2005 by REID
 */
package com.fivesticks.time.todo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class ToDoPriorityTypeEnum extends Enum {

    public static ToDoPriorityTypeEnum Q1 = new ToDoPriorityTypeEnum("I",
            "images/icon-todo-priorityI.gif", "images/icon-todo-priorityI-on.gif");

    public static ToDoPriorityTypeEnum Q2 = new ToDoPriorityTypeEnum("II",
            "images/icon-todo-priorityII.gif", "images/icon-todo-priorityII-on.gif");

    public static ToDoPriorityTypeEnum Q3 = new ToDoPriorityTypeEnum("III",
            "images/icon-todo-priorityIII.gif", "images/icon-todo-priorityIII-on.gif");

    public static ToDoPriorityTypeEnum Q4 = new ToDoPriorityTypeEnum("IV",
            "images/icon-todo-priorityIV.gif", "images/icon-todo-priorityIV-on.gif");

    private static Collection all;

    private String icon;

    private String iconOn;

    /**
     * @param arg0
     */
    public ToDoPriorityTypeEnum(String arg0) {
        super(arg0);
    }

    public ToDoPriorityTypeEnum(String arg0, String icon, String iconOn) {
        super(arg0);
        this.icon = icon;
        this.iconOn = iconOn;
    }

    public static Collection getAll() {
        if (all == null) {
            all = new ArrayList();
            all.add(Q1);
            all.add(Q2);
            all.add(Q3);
            all.add(Q4);
        }
        return all;
    }

    public static ToDoPriorityTypeEnum getByName(String name) {
        return (ToDoPriorityTypeEnum) Enum.getEnum(ToDoPriorityTypeEnum.class, name);
    }
    /**
     * @return Returns the icon.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            The icon to set.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return Returns the iconOn.
     */
    public String getIconOn() {
        return iconOn;
    }

    /**
     * @param iconOn
     *            The iconOn to set.
     */
    public void setIconOn(String iconOn) {
        this.iconOn = iconOn;
    }
}