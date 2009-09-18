/*
 * Created on Sep 29, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.TimeclockFilterParameters;
import com.fivesticks.time.timeclock.TimeclockFilterParametersBuilder;
import com.fivesticks.time.timeclock.TimeclockSearchBD;
import com.fivesticks.time.timeclock.TimeclockSearchBDException;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparator;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparatorFactory;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapperSet;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapperSetBuilder;

/**
 * @author Reid
 */
public class TimeclockAdminShowDetailAction extends SessionContextAwareSupport implements TimeclockAdminContextAware {

    

    private TimeclockAdminContext timeclockAdminContext;

    private Date targetDate;

    private Collection display;

    public String execute() throws Exception {

        if (!this.getTimeclockAdminContext().isValid()) {
            return ERROR;
        }

        if (this.getTargetDate() == null
                && this.getTimeclockAdminContext().getTargetDate() == null) {
            return ERROR;
        }

        if (this.getTargetDate() == null)
            this.setTargetDate(this.getTimeclockAdminContext().getTargetDate());
        else
            this.getTimeclockAdminContext().setTargetDate(this.getTargetDate());

        TimeclockFilterParameters filter = new TimeclockFilterParametersBuilder()
                .buildUserForDate(this.getTimeclockAdminContext().getUser(),
                        this.getTargetDate());

        Collection raw;
        try {
            raw = new TimeclockSearchBD(this.getSystemOwner()).getRawRecords(filter);
        } catch (TimeclockSearchBDException e) {
            throw new RuntimeException(e);
        }

        //do the comparator
        TimeclockDetailComparator comparator = TimeclockDetailComparatorFactory.factory
                .buildDefault();

        //send to displayable builder

        TimeclockDetailDisplayWrapperSet display = new TimeclockDetailDisplayWrapperSetBuilder(this.getSystemOwner())
                .build(raw, comparator);

        this.setDisplay(display);

        return SUCCESS;

    }


    /**
     * @return Returns the timeclockAdminContext.
     */
    public TimeclockAdminContext getTimeclockAdminContext() {
        return timeclockAdminContext;
    }

    /**
     * @param timeclockAdminContext
     *            The timeclockAdminContext to set.
     */
    public void setTimeclockAdminContext(
            TimeclockAdminContext timeclockAdminContext) {
        this.timeclockAdminContext = timeclockAdminContext;
    }

    /**
     * @return Returns the targetDate.
     */
    public Date getTargetDate() {
        return targetDate;
    }

    /**
     * @param targetDate
     *            The targetDate to set.
     */
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * @return Returns the display.
     */
    public Collection getDisplay() {
        return display;
    }

    /**
     * @param display
     *            The display to set.
     */
    public void setDisplay(Collection display) {
        this.display = display;
    }
}