/*
 * Created on Sep 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.fivesticks.time.activity.xwork;

import java.text.DecimalFormat;
import java.util.Collection;

/**
 * @author shuji
 * 
 * 2006-05-10 RSC This is odd - the totals are here instead of the Display.  Modified the
 * JSP to access only the display collection.
 * 
 */
public class ActivityChildCollection {

    private Collection child;

    private Double total;

    private static DecimalFormat df = new DecimalFormat("#,##0.000;(#)");

    /**
     * @return Returns the total.
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total
     *            The total to set.
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFormattedTotal() {
        return df.format(this.getTotal());
    }

    /**
     * @return Returns the child.
     */
    public Collection getChild() {
        return child;
    }

    /**
     * @param child
     *            The child to set.
     */
    public void setChild(Collection child) {
        this.child = child;
    }
}