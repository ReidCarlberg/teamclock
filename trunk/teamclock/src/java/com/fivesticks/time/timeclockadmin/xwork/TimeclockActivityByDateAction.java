/*
 * Created on Aug 18, 2005
 *
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fivesticks.time.timeclock.UserShiftRecord;
import com.fivesticks.time.timeclock.UserShiftRecordBuilder;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.xwork.UserCollectionBuilder;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class TimeclockActivityByDateAction extends SessionContextAwareSupport
        implements TimeclockActivityByDateContextAware {

    private Collection shifts;

    private TimeclockActivityByDateContext timeclockActivityByDateContext;
    
    
    
    private SimpleDate targetDateSimple;

    public String execute() throws Exception {

        /*
         * resolvedDate starts here. 2005-11-09 RSC
         */
        if (this.getTimeclockActivityByDateContext().getTargetDate() == null) {
            Date timeZoneAdjusted = this.getSessionContext().getResolvedNow();
            this.getTimeclockActivityByDateContext().setTargetDate(
                    timeZoneAdjusted);
        }
        Collection activeUsers = new UserCollectionBuilder()
                .buildInternalActiveOnlyAsUserAndTypeVO(this.getSystemOwner());

        targetDateSimple = SimpleDate.factory.buildMidnight(this
                .getTimeclockActivityByDateContext().getTargetDate());
        
        if (this.getTargetDate() != null) {
            targetDateSimple = SimpleDate.factory.buildMidnight(this.getTargetDate());
        } else {
            this.setTargetDate(targetDateSimple.getDate());
        }

        shifts = new ArrayList();

        for (Iterator iter = activeUsers.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            UserShiftRecord record = new UserShiftRecordBuilder(this
                    .getSystemOwner()).buildByResolvedStartDate(element.getUser(),
                            targetDateSimple.getDate());

            UserShiftDisplayWrapper display = new UserShiftDisplayWrapper(this
                    .getSystemOwner(), record);

            shifts.add(display);

        }

        return SUCCESS;
    }

    /**
     * @return Returns the shifts.
     */
    public Collection getShifts() {
        return shifts;
    }

    /**
     * @param shifts
     *            The shifts to set.
     */
    public void setShifts(Collection shifts) {
        this.shifts = shifts;
    }

    // /**
    // * @return Returns the targetDate.
    // */
    // public Date getTargetDate() {
    // return targetDate;
    // }
    //
    // /**
    // * @param targetDate The targetDate to set.
    // */
    // public void setTargetDate(Date targetDate) {
    // this.targetDate = targetDate;
    // }

    /**
     * @return Returns the timeclockActivityByDateContext.
     */
    public TimeclockActivityByDateContext getTimeclockActivityByDateContext() {
        return timeclockActivityByDateContext;
    }

    /**
     * @param timeclockActivityByDateContext
     *            The timeclockActivityByDateContext to set.
     */
    public void setTimeclockActivityByDateContext(
            TimeclockActivityByDateContext timeclockActivityByDateContext) {
        this.timeclockActivityByDateContext = timeclockActivityByDateContext;
    }

    /**
     * @return Returns the targetDate.
     */
    public Date getTargetDate() {
        return this.getTimeclockActivityByDateContext().getTargetDate();
    }

    /**
     * @param targetDate The targetDate to set.
     */
    public void setTargetDate(Date targetDate) {
        this.getTimeclockActivityByDateContext().setTargetDate(targetDate);
    }

    /**
     * @return Returns the targetDateSimple.
     */
    public SimpleDate getTargetDateSimple() {
        return targetDateSimple;
    }

    /**
     * @param targetDateSimple The targetDateSimple to set.
     */
    public void setTargetDateSimple(SimpleDate targetDateSimple) {
        this.targetDateSimple = targetDateSimple;
    }
}
