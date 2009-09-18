/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.util.StringUtils;

import com.fivesticks.time.activity.ActivityFilterVO;
import com.fivesticks.time.activity.util.ActivitySummaryDecorator;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.DatePair;

public class AbstractSummarizeActivityAction extends
        AbstractActivityListContextAwareSupport implements ActivitySummaryContextAware {

    protected ActivityFilterVO params = new ActivityFilterVO();
    
    private ActivitySummaryContext activitySummaryContext;

    private String searchActivities;

    private Date start;

    private Date stop;

    private Collection summary;

    private String username;

    private double totalShiftMinutes;

    private double totalShiftMinutesBreak;

    protected void initializeShiftTotals() throws Exception {

        double totalClockedInMinutes = 0.0;
        double totalOnBreakMinutes = 0.0;

        DatePair startStop = new DatePair(this.params.getStart(), this.params
                .getStop());

        if (StringUtils.hasText(this.params.getUsername())) {
            User user = UserBDFactory.factory.build().getByUsername(
                    params.getUsername());

            UserPayPeriodSummary element = new UserPayPeriodSummaryBuilder()
                    .buildAlreadyResolvedForUser(this.getSystemOwner(), user,
                            startStop);

            totalClockedInMinutes += element.getSummary()
                    .getTotalRoundedMinutes();
            totalOnBreakMinutes = element.getSummary()
                    .getTotalRoundedBreakMinutes();

        } else {

            Collection all = new UserPayPeriodSummaryBuilder()
                    .buildResolvedForAllActiveInternalUsers(this
                            .getSessionContext().getSystemOwner(), startStop);

            for (Iterator iter = all.iterator(); iter.hasNext();) {
                UserPayPeriodSummary element = (UserPayPeriodSummary) iter
                        .next();
                totalClockedInMinutes += element.getSummary()
                        .getTotalRoundedMinutes();
                totalOnBreakMinutes = element.getSummary()
                        .getTotalRoundedBreakMinutes();
            }

        }
        this.setTotalShiftMinutes(totalClockedInMinutes);
        this.setTotalShiftMinutesBreak(totalOnBreakMinutes);
        
        /*
         * percentage of non-break hours.
         */
        new ActivitySummaryDecorator().decorate(this.getSummary(),this.getTotalShiftHours()-this.getTotalShiftHoursBreak());
    }

    /**
     * @return Returns the params.
     */
    public ActivityFilterVO getParams() {
        return params;
    }

    /**
     * @return Returns the searchActivities.
     */
    public String getSearchActivities() {
        return searchActivities;
    }

    /**
     * @return Returns the start.
     */
    public Date getStart() {
        return start;
    }

    /**
     * @return Returns the stop.
     */
    public Date getStop() {
        return stop;
    }

    /**
     * @return Returns the summary.
     */
    public Collection getSummary() {
        return summary;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param params
     *            The params to set.
     */
    public void setParams(ActivityFilterVO params) {
        this.params = params;
    }

    /**
     * @param searchActivities
     *            The searchActivities to set.
     */
    public void setSearchActivities(String searchActivities) {
        this.searchActivities = searchActivities;
    }

    /**
     * @param start
     *            The start to set.
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @param stop
     *            The stop to set.
     */
    public void setStop(Date stop) {
        this.stop = stop;
    }

    /**
     * @param summary
     *            The summary to set.
     */
    public void setSummary(Collection summary) {
        this.summary = summary;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the totalMinutes.
     */
    public double getTotalShiftMinutes() {
        return totalShiftMinutes;
    }

    /**
     * @param totalMinutes
     *            The totalMinutes to set.
     */
    public void setTotalShiftMinutes(double totalMinutes) {
        this.totalShiftMinutes = totalMinutes;
    }

    /**
     * @return Returns the totalMinutesBreak.
     */
    public double getTotalShiftMinutesBreak() {
        return totalShiftMinutesBreak;
    }

    /**
     * @param totalMinutesBreak
     *            The totalMinutesBreak to set.
     */
    public void setTotalShiftMinutesBreak(double totalMinutesBreak) {
        this.totalShiftMinutesBreak = totalMinutesBreak;
    }

    public double getTotalShiftHours() {
        return this.getTotalShiftMinutes() / 60.0;
    }

    public double getTotalShiftHoursBreak() {
        return this.getTotalShiftMinutesBreak() / 60.0;
    }

    public String getTotalShiftHoursFormatted() {
        return hundredthsFormatter.format(this.getTotalShiftHours());
    }

    public String getTotalShiftHoursBreakFormatted() {
        return hundredthsFormatter.format(this.getTotalShiftHoursBreak());
    }

    public String getTotalShiftHoursMinusBreakFormatted() {
        return hundredthsFormatter.format(this.getTotalShiftHours()
                - this.getTotalShiftHoursBreak());
    }

    /**
     * @return Returns the activitySummaryContext.
     */
    public ActivitySummaryContext getActivitySummaryContext() {
        return activitySummaryContext;
    }

    /**
     * @param activitySummaryContext The activitySummaryContext to set.
     */
    public void setActivitySummaryContext(
            ActivitySummaryContext activitySummaryContext) {
        this.activitySummaryContext = activitySummaryContext;
    }

}
