/*
 * Created on Sep 27, 2004 by Reid
 */
package com.fivesticks.time.payperiodproductivity.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fivesticks.time.timeclock.util.PayPeriodDeterminator;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fivesticks.time.useradmin.xwork.UserListKeyValue;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Reid
 */
public class PayPeriodProductivityReportAction extends SessionContextAwareSupport {

    

//    private String periodType;

    private Date targetDate;

    private String user;

    private String buildReport;

    private PayPeriodProductivityReport report = null;

    public String execute() throws Exception {
        if (this.getBuildReport() == null) {
            return INPUT;
        }

        //   this.setUser("all");
        //  this.setTargetDate(new Date());
        if (this.getUser() == null || this.getUser().length() == 0) {
            this.setUser("all");
        }
        if (this.getTargetDate() == null) {
            /*
             * RSC 2005-11-09 needs a resolved date
             */
//            this.setTargetDate(new Date());
            this.setTargetDate(this.getSessionContext().getResolvedNow());
        }

        PayPeriodDeterminator ppd = new PayPeriodDeterminator();

        PayPeriodTypeEnum ppType = PayPeriodTypeEnum.getByName(this
                .getSessionContext().getSettings().getTimeClockPayPeriodType());

        DatePair targetPeriod = ppd.getPayPeriodByDate(ppType, this
                .getSessionContext().getSettings()
                .getTimeClockPayPeriodRefDate(), this.getTargetDate());

        //          getBySessionTypeAndDate(this
        //                .getSessionContext(), "current", this.getTargetDate());

        if (this.getUser().equals("all")) {
            PayPeriodProductivityReportBuilder builder = new PayPeriodProductivityReportBuilder();
            builder.setSystemOwner(this.getSystemOwner());
            PayPeriodProductivityReport report = builder.buildForAllUsers(this
                    .getSessionContext().getSystemOwner(), targetPeriod);

            this.setReport(report);

        } else {
            User user = UserBDFactory.factory.build().getByUsername(this.getUser());
            PayPeriodProductivityReportBuilder builder = new PayPeriodProductivityReportBuilder();
            builder.setSystemOwner(this.getSystemOwner());
            PayPeriodProductivityReport report = builder.buildForUser(user,
                    targetPeriod);
            this.setReport(report);
        }

        return SUCCESS;
    }


    public Collection getUsers() throws Exception {

        Collection col = UserListBroker.singleton.getUserList(this
                .getSessionContext().getSystemOwner());

        /*
         * We need an option for all in the list
         */
        //        User allUser = new User();
        //        allUser.setUsername("all");
        UserListKeyValue vo = new UserListKeyValue("all", "all");
        //        UserAndTypeVO vo = new UserAndTypeVO();
        //        vo.setUser(allUser);
        col.add(vo);
        return col;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBuildReport() {
        return buildReport;
    }

    public void setBuildReport(String buildReport) {
        this.buildReport = buildReport;
    }

    public PayPeriodProductivityReport getReport() {

        return report;
    }

    public void setReport(PayPeriodProductivityReport report) {
        this.report = report;
    }
}