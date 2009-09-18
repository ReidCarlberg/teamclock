/*
 * Created on Dec 3, 2004
 *
 * 
 */
package com.fivesticks.time.payperiodproductivity.xwork;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.activity.FstxActivity;
import com.fivesticks.time.activity.FstxActivityBDException;
import com.fivesticks.time.activity.FstxActivityBDFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.timeclock.UserShiftCollectionBuilderException;
import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Nick
 * 
 *  
 */
public class PayPeriodProductivityReportBuilder {


    
    private SystemOwner systemOwner;

//    public static PayPeriodProductivityReportBuilder singleton = new PayPeriodProductivityReportBuilder();

    public PayPeriodProductivityReport buildForUser(User user,
            DatePair targetPeriod)
            throws PayPeriodProductivityReportBuilderException {
        PayPeriodProductivityReport report = new PayPeriodProductivityReport();
        return this.buildForUser(user, targetPeriod, report);
    }

    public PayPeriodProductivityReport buildForUser(User user,
            DatePair targetPeriod, PayPeriodProductivityReport report)
            throws PayPeriodProductivityReportBuilderException {
        //  PayPeriodProductivityReport report = new
        // PayPeriodProductivityReport();

        
        /*
         * 2005-11-09 RSC by the time the code gets here
         */
        UserPayPeriodSummary userPayPeriodSummary = null;
        try {
            userPayPeriodSummary = new UserPayPeriodSummaryBuilder()
                    .buildAlreadyResolvedForUser(this.getSystemOwner(),user, targetPeriod);
        } catch (UserShiftCollectionBuilderException e) {
            throw new PayPeriodProductivityReportBuilderException(
                    "Error Building Payperiod Report", e);
        }

        Collection shifts = userPayPeriodSummary.getDisplayableShifts();
        for (Iterator iter = shifts.iterator(); iter.hasNext();) {
            UserShiftDisplayWrapper element = (UserShiftDisplayWrapper) iter
                    .next();
            PayPeriodReportVO vo = new PayPeriodReportVO();
            vo.setDate(element.getCurrent().getShift().getStart());
            vo.setUser(user);
            vo.setUserShiftDisplayWrapper(element);

            DailyActivityVO activity = this.getActivity(user, element
                    .getCurrent().getShift().getStart());
            vo.setDailyActivityVO(activity);

            /*
             * We will just pass the vo to the report and let it organize the
             * data.
             */
            report.addPayPeriodReportVO(vo);
        }

        return report;
    }

    private DailyActivityVO getActivity(User user, Date date)
            throws PayPeriodProductivityReportBuilderException {

        DailyActivityVO vo = new DailyActivityVO();

        Collection activities = null;
        try {
            activities = FstxActivityBDFactory.factory.build(this.getSystemOwner())
                    .getActivityForUserByDate(user, date);
        } catch (FstxActivityBDException e1) {

            throw new PayPeriodProductivityReportBuilderException(e1);
        }

        double elapsedTime = 0.0;
        for (Iterator iter = activities.iterator(); iter.hasNext();) {
            FstxActivity element = (FstxActivity) iter.next();
            if (element.getElapsed() != null) {
                elapsedTime += element.getElapsed().doubleValue();
            }
        }
        vo.setUser(user);
        vo.setDate(date);
        vo.setActivityHours(elapsedTime);
        return vo;
    }

    public PayPeriodProductivityReport buildForAllUsers(
            SystemOwner systemOwner, DatePair targetPeriod)
            throws PayPeriodProductivityReportBuilderException {
        PayPeriodProductivityReport report = new PayPeriodProductivityReport();
        Collection users = null;
        try {
            users = UserServiceDelegateFactory.factory.build(systemOwner)
                    .listUserAndType();
        } catch (UserServiceDelegateException e) {
            throw new PayPeriodProductivityReportBuilderException(e);
        }
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();
            if (!element.getUser().isBooleanInactive()) {
                this.buildForUser(element.getUser(), targetPeriod, report);
            }

        }
        return report;
    }



    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}