/*
 * Created on Nov 29, 2005
 *
 */
package com.fivesticks.time.payperiod;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.AbstractTimeObject;

public class PayPeriod extends AbstractTimeObject implements Serializable {

    private Date dateStart;
    private Date dateEnd;
    private boolean locked;
    
    /**
     * @return Returns the dateEnd.
     */
    public Date getDateEnd() {
        return dateEnd;
    }
    /**
     * @param dateEnd The dateEnd to set.
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    /**
     * @return Returns the dateStart.
     */
    public Date getDateStart() {
        return dateStart;
    }
    /**
     * @param dateStart The dateStart to set.
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    /**
     * @return Returns the locked.
     */
    public boolean isLocked() {
        return locked;
    }
    /**
     * @param locked The locked to set.
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
