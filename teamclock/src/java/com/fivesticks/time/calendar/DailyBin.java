package com.fivesticks.time.calendar;

import java.io.Serializable;
import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class DailyBin extends AbstractBin implements Comparable, Serializable {

    private Date resolvedNow;
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object arg0) {
        if (!(arg0 instanceof DailyBin)) {
            throw new RuntimeException("Invalid object in DailyBin CompareTo"
                    + arg0.getClass());
        }
        DailyBin test = (DailyBin) arg0;

        int ret = this.getLowerBound().compareTo(test.getLowerBound());
        /*
         * If the dates are the same, we don't want to discard the bin.
         */
        if (ret == 0) {
            ret = -1;
        }
        return ret;
    }
    
    /*
     * 2006-06-28 reid needs to be using resolved date.
     */
    public boolean isToday() {

        Date testdate = SimpleDate.factory.buildMidnight(this.getLowerBound())
                .getDate();

        /*
         * 2006-06-28 reid this is where I changed from a new Date() to a new Resolved now. 
         */
        Date today = SimpleDate.factory.buildMidnight(this.getResolvedNow()).getDate();

        if (testdate.equals(today)) {

            return true;

        }
        return false;
    }

    /**
     * @return Returns the resolvedNow.
     */
    public Date getResolvedNow() {
        return resolvedNow;
    }

    /**
     * @param resolvedNow The resolvedNow to set.
     */
    public void setResolvedNow(Date resolvedNow) {
        this.resolvedNow = resolvedNow;
    }

}