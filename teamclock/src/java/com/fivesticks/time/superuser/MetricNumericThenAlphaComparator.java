/*
 * Created on Feb 8, 2005 by Reid
 */
package com.fivesticks.time.superuser;

import java.util.Comparator;

import com.fivesticks.time.object.metrics.ObjectMetric;

/**
 * @author Reid
 */
public class MetricNumericThenAlphaComparator implements Comparator {

    private boolean descending;

    public MetricNumericThenAlphaComparator() {
        super();
    }

    public MetricNumericThenAlphaComparator(boolean descending) {
        super();
        this.setDescending(descending);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {

        ObjectMetric om1 = (ObjectMetric) o1;
        ObjectMetric om2 = (ObjectMetric) o2;

        //        System.out.println("Sorting > 1: " + om1.getMetricValue() + ", 2: " +
        // om2.getMetricValue());

        int ret = -1;

        if (Long.parseLong(om1.getMetricValue()) < Long.parseLong(om2
                .getMetricValue())) {
            ret = -1;
        } else if (Long.parseLong(om1.getMetricValue()) > Long.parseLong(om2
                .getMetricValue())) {
            ret = 1;
        } else {
            ret = om1.getOwnerKey().compareTo(om2.getOwnerKey());
        }

        if (this.isDescending())
            ret = ret * -1;

        return ret;
    }

    /**
     * @return Returns the descending.
     */
    public boolean isDescending() {
        return descending;
    }

    /**
     * @param descending
     *            The descending to set.
     */
    public void setDescending(boolean descending) {
        this.descending = descending;
    }
}