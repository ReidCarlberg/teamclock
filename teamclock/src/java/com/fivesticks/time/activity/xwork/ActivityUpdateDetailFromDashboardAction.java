/*
 * Created on Jun 14, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import java.util.Date;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.common.util.Rounder;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class ActivityUpdateDetailFromDashboardAction extends AbstractJSONAction {

    private Long target;

    private Date date;

    private String startString;

    private String stopString;

    private String comments;

    private Double elapsedChargeable;

    private boolean computeChargeable;

    public String execute() throws Exception {

        if (this.getTarget() == null) {
            return SUCCESS;
        }

        Rounder rounder = DateTimeRounderFactory.factory.build(this
                .getSessionContext().getSettings().getActivityRounderType());

        ActivityBD bd = ActivityBDFactory.factory.build(this
                .getSessionContext());

        Activity activity = bd.get(this.getTarget());

        TimeResolver resolver = new TimeResolver(this.getDate(), this
                .getStartString());

        TimeResolver resolver2 = new TimeResolver(this.getDate(), this
                .getStopString());

        if (this.getDate() != null)
            activity.setDate(this.getDate());

        Date start = rounder.round(resolver.resolve());

        Date stop = null;

        if (this.getStopString().trim().length() > 0) {
            stop = rounder.round(resolver2.resolve());
        }

        /*
         * 2006-06-28 if it looks like stop is before start, just
         * switch the two.
         */
        if (stop != null && stop.compareTo(start) < 0) {
            Date temp = stop;
            stop = start;
            start = temp;
        }
        
        activity.setDate(date);
        activity.setStart(start);
        activity.setStop(stop);
        
        
        activity.setComments(this.getComments());
        
        ActivityUtils.decorateWithElapsed(activity);
        
        if (this.isComputeChargeable()) {
            ActivityUtils.decorateWithElapsedChargeable(activity);
        } else {
            activity.setChargeableElapsed(this.getElapsedChargeable());
        }
        bd.save(activity);
        
        return SUCCESS;

    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Returns the elapsedChargeable.
     */
    public Double getElapsedChargeable() {
        return elapsedChargeable;
    }

    /**
     * @param elapsedChargeable
     *            The elapsedChargeable to set.
     */
    public void setElapsedChargeable(Double elapsedChargeable) {
        this.elapsedChargeable = elapsedChargeable;
    }

    /**
     * @return Returns the start.
     */
    public String getStartString() {
        return startString;
    }

    /**
     * @param start
     *            The start to set.
     */
    public void setStartString(String start) {
        this.startString = start;
    }

    /**
     * @return Returns the stop.
     */
    public String getStopString() {
        return stopString;
    }

    /**
     * @param stop
     *            The stop to set.
     */
    public void setStopString(String stop) {
        this.stopString = stop;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the updateChargeable.
     */
    public boolean isComputeChargeable() {
        return computeChargeable;
    }

    /**
     * @param updateChargeable
     *            The updateChargeable to set.
     */
    public void setComputeChargeable(boolean updateChargeable) {
        this.computeChargeable = updateChargeable;
    }
}
