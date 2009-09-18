/*
 * Created on May 10, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

/**
 * @author Reid
 */
public class BoxFilterBuilder {

    public static BoxCriteriaParameters buildActiveByLength() {
        BoxCriteriaParameters filter = new BoxCriteriaParameters();
        filter.setSortByLength(new Boolean(true));
        return filter;
    }
    public static BoxCriteriaParameters buildActiveSortedByLength() {
        BoxCriteriaParameters filter = buildActiveByLength();
        filter.setActiveOnly(new Boolean(true));
        return filter;
    }
    public static BoxCriteriaParameters buildLargerThan(Integer length, Integer width, Integer height) {
        BoxCriteriaParameters filter = new BoxCriteriaParameters();
        filter.setLengthMinimum(length);
        filter.setWidthMinimum(width);
        filter.setHeightMinimum(height);
        filter.setSortByLength(new Boolean(true));
        return filter;
    }
    public static BoxCriteriaParameters buildActiveLargerThan(Integer length, Integer width, Integer height) {
        BoxCriteriaParameters filter = buildLargerThan(length, width, height);
        filter.setActiveOnly(new Boolean(true));
        return filter;
    }
    
    public static BoxCriteriaParameters buildActiveInstockLargerThan(Integer length, Integer width, Integer height) {
        BoxCriteriaParameters filter = buildActiveLargerThan(length, width, height);
        filter.setInstockOnly(new Boolean(true));
        return filter;
    }

}
