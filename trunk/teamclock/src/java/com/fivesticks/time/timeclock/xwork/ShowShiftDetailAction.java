/*
 * Created on Sep 28, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

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
public class ShowShiftDetailAction extends SessionContextAwareSupport {

    

    private Date targetDate;

    private Collection display;

    public String execute() throws Exception {
        if (this.getTargetDate() == null) {
            return INPUT;
        }

        TimeclockFilterParameters filter = new TimeclockFilterParametersBuilder()
                .buildUserForDate(this.getSessionContext().getUser().getUser(),
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