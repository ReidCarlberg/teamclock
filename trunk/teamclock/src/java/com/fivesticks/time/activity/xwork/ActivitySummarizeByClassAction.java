/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.activity.xwork;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.lookups.util.LookupDecorator;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class ActivitySummarizeByClassAction extends
        AbstractSummarizeActivityAction {

    private String monthlySummary;
    
    private String targetYear;
    
    private Collection monthly;
    
    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.ACTIVITY);

        ActivityBD activityBD = ActivityBDFactory.factory.build(this
                .getSessionContext());

        if (this.getSearchActivities() != null) {
            if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
                params.setUsername(this.getSessionContext().getUser().getUsername());
            } 
            
            Collection summary = activityBD.getTimeTotalsByProjectClass(params);

            new LookupDecorator(this.getSystemOwner()).decorate(summary);

            this.setSummary(summary);
            
            this.getActivityListContext().setParams(this.params);
            
            this.initializeShiftTotals();
            
            this.getActivitySummaryContext().setSummary(this.getSummary());
        } else if (this.getMonthlySummary() != null) {
            
            this.getActivityListContext().setParams(this.params);
            
            this.setMonthly(new ArrayList());
            for (int i = 1; i <= 12; i++) {
                
                SimpleDate start = SimpleDate.factory.build(i+"/1/"+this.getTargetYear());
                SimpleDate stop = SimpleDate.factory.build(start.getDate());
                stop.advanceMonth(1);
                stop.advanceDay(-1);
                
                params.setStart(start.getDate());
                params.setStop(stop.getDate());
                
                Collection summary = activityBD.getTimeTotalsByProjectClass(params);

                new LookupDecorator(this.getSystemOwner()).decorate(summary);

                this.setSummary(summary);
                
                this.initializeShiftTotals();
                
                ActivitySummaryVO vo = new ActivitySummaryVO();
                vo.setStart(start.getDate());
                vo.setStop(stop.getDate());
                vo.setSummary(summary);
                vo.setTotalShiftMinutes(this.getTotalShiftMinutes());
                vo.setTotalShiftMinutesBreak(this.getTotalShiftMinutesBreak());
                
                this.getMonthly().add(vo);
            }
            
            this.setSummary(null);
            
        } else {
            if (this.getActivityListContext().getParams() != null) {
                this.params = this.getActivityListContext().getParams();
            }
        }
        
        

        return SUCCESS;
    }

    /**
     * @return the monthlySummary
     */
    public String getMonthlySummary() {
        return monthlySummary;
    }

    /**
     * @param monthlySummary the monthlySummary to set
     */
    public void setMonthlySummary(String monthlySummary) {
        this.monthlySummary = monthlySummary;
    }

    /**
     * @return the targetYear
     */
    public String getTargetYear() {
        return targetYear;
    }

    /**
     * @param targetYear the targetYear to set
     */
    public void setTargetYear(String targetYear) {
        this.targetYear = targetYear;
    }

    /**
     * @return the monthly
     */
    public Collection getMonthly() {
        return monthly;
    }

    /**
     * @param monthly the monthly to set
     */
    public void setMonthly(Collection monthly) {
        this.monthly = monthly;
    }

}