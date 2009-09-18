/*
 * Created on Oct 21, 2004 by Reid
 */
package com.fivesticks.time.common.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class RounderTypeEnum extends Enum {

    public static final RounderTypeEnum ROUNDER_RAW = new RounderTypeEnum(
            "raw", "Raw Rounder -- records exact minutes");

    public static final RounderTypeEnum ROUNDER_TENTHS = new RounderTypeEnum(
            "tenths", "Tenths of an Hour Rounder -- 1-2, down, 3-5, up");

    public static final RounderTypeEnum ROUNDER_SIXTHS = new RounderTypeEnum(
            "sixths", "Sixths of an Hour Rounder -- 1-4, down, 5-9, up");

    public static final RounderTypeEnum ROUNDER_QUARTER = new RounderTypeEnum(
            "quarter", "Quarter Hour Rounder -- 1-7, down, 8-14, up");

    private static Collection allRounders;

    private String description;

    /**
     * @param arg0
     */
    protected RounderTypeEnum(String arg0) {
        super(arg0);
    }

    protected RounderTypeEnum(String arg0, String description) {
        this(arg0);
        this.description = description;
    }

    public static Collection getRounders() {

        if (allRounders == null) {
            allRounders = new ArrayList();
            allRounders.add(ROUNDER_RAW);
            allRounders.add(ROUNDER_SIXTHS);
            allRounders.add(ROUNDER_TENTHS);
            allRounders.add(ROUNDER_QUARTER);
        }

        return allRounders;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
}