/*
 * Created on Aug 18, 2005
 *
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.Timeclock;
import com.fstx.stdlib.authen.users.UserBDFactory;

public class TimeclockEventModifyPrepareAction extends
        SessionContextAwareSupport implements TimeclockAdminContextAware {

    private TimeclockAdminContext timeclockAdminContext;

    private Long target;

    private String targetUser;

    private Date targetDate;

    private Log log = LogFactory
            .getLog(TimeclockEventModifyPrepareAction.class);

    public String execute() throws Exception {

        // TimeclockAdminContext context = new TimeclockAdminContext();

        if (this.getTarget() != null) {
            Timeclock t = TiimeclockBDFactory.factory.build(
                    this.getSessionContext()).getById(this.getTarget());

            this.getTimeclockAdminContext()
                    .setTargetDate(t.getEventTimestamp());

            this.getTimeclockAdminContext().setUser(
                    UserBDFactory.factory.build()
                            .getByUsername(t.getUsername()));

        } else {

            this.getTimeclockAdminContext()
                    .setTargetDate(this.getTargetDate());

            this.getTimeclockAdminContext().setUser(
                    UserBDFactory.factory.build()
                            .getByUsername(this.getTargetUser()));

        }

        // this.setTimeclockAdminContext(context);

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
     * @return Returns the targetUser.
     */
    public String getTargetUser() {
        return targetUser;
    }

    /**
     * @param targetUser
     *            The targetUser to set.
     */
    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

}
