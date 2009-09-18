/*
 * Created on Jun 7, 2005 by Reid
 */
package com.fivesticks.time.ebay.util;


/**
 * @author Reid
 */
public class DoubleRounder {

    public static final DoubleRounder singleton = new DoubleRounder();
    
    public double get6(double target) {
        return round(target,6);
    }

    public double get3(double target) {
        return round(target,3);
    }

    public double get2(double target) {
        return round(target,2);
    }

    public double round(double val, int places) {
        long factor = (long) Math.pow(10, places);

        // Shift the decimal the correct number of places
        // to the right.
        val = val * factor;

        // Round to the nearest integer.
        long tmp = Math.round(val);

        // Shift the decimal the correct number of places
        // back to the left.
        return (double) tmp / factor;
    }

}