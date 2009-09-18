/*
 * Created on May 22, 2006
 *
 */
package com.fivesticks.time.register.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.util.RounderTypeEnum;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.settings.event.SettingsEventPublisher;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fstx.stdlib.authen.users.UserBDFactory;

public class InitialSetupAction extends SessionContextAwareSupport {

    private String password1;

    private String password2;

    private String timeClockPayPeriodType;

    private Date payPeriodRefDate;

    private String timeClockRounderType;

    private String submitInitial;

    public String execute() throws Exception {

        if (!hasText(this.getSubmitInitial())) {
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        // update password
        UserBDFactory.factory.build().changePassword(
                this.getSessionContext().getUser().getUser(),
                this.getPassword1());

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.getSystemOwner());
        
        // update payroll frequency
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE, this.getTimeClockPayPeriodType());
        
        // update pay period start date
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE, this.getPayPeriodRefDate());

        // update time clock punch
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_ROUNDER_TYPE, this.getTimeClockRounderType());

        // update "initial settings complete flag."
        sd.updateSetting(SettingTypeEnum.INITIAL_TIMECLOCK_COMPLETE, true);

        // publish settings modified event
        new SettingsEventPublisher().publishSettingsModifiedEvent(this.getSystemOwner());

        return SUCCESS;
    }

    /**
     * @return Returns the password1.
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * @param password1
     *            The password1 to set.
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * @return Returns the password2.
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2
     *            The password2 to set.
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * @return Returns the payPeriodRefDate.
     */
    public Date getPayPeriodRefDate() {
        return payPeriodRefDate;
    }

    /**
     * @param payPeriodRefDate
     *            The payPeriodRefDate to set.
     */
    public void setPayPeriodRefDate(Date payPeriodRefDate) {
        this.payPeriodRefDate = payPeriodRefDate;
    }

    /**
     * @return Returns the submitInitial.
     */
    public String getSubmitInitial() {
        return submitInitial;
    }

    /**
     * @param submitInitial
     *            The submitInitial to set.
     */
    public void setSubmitInitial(String submitInitial) {
        this.submitInitial = submitInitial;
    }

    /**
     * @return Returns the timeClockPayPeriodType.
     */
    public String getTimeClockPayPeriodType() {
        return timeClockPayPeriodType;
    }

    /**
     * @param timeClockPayPeriodType
     *            The timeClockPayPeriodType to set.
     */
    public void setTimeClockPayPeriodType(String timeClockPayPeriodType) {
        this.timeClockPayPeriodType = timeClockPayPeriodType;
    }

    /**
     * @return Returns the timeClockRounderType.
     */
    public String getTimeClockRounderType() {
        return timeClockRounderType;
    }

    /**
     * @param timeClockRounderType
     *            The timeClockRounderType to set.
     */
    public void setTimeClockRounderType(String timeClockRounderType) {
        this.timeClockRounderType = timeClockRounderType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.ActionSupport#validate()
     */
    public void validate() {

        super.validate();

        if (!hasText(this.getPassword1())) {
            this.addFieldError("password1", "Please enter a password.");
        }

        if (!hasText(this.getPassword2())) {
            this.addFieldError("password2", "Please confirm your password.");
        }

        if (!hasText(this.getTimeClockPayPeriodType())) {
            this.addFieldError("timeClockPayPeriodType",
                    "Please select a payperiod type.");
        }

        if (!hasText(this.getTimeClockRounderType())) {
            this.addFieldError("timeClockRounderType",
                    "Please select a rounder type.");
        }

        if (this.getPayPeriodRefDate() == null) {
            this.addFieldError("payPeriodRefDate",
                    "Please enter recent pay period start date.");
        }

        if (!this.hasErrors()) {
            if (!this.getPassword1().equals(this.getPassword2())) {
                this.addFieldError("password1", "Passwords do not match.");
            }
        }

        if (!this.hasErrors()) {
            ValidationHelper vh = new ValidationHelper();
            if (!vh.isValidPassword(this.getPassword1())) {
                this
                        .addFieldError(
                                "password1",
                                "Password isn't valid.  It should be 5-20 characters and should not include spaces.");
            }
        }

    }

    public Collection getRounderTypes() {
        return RounderTypeEnum.getRounders();
    }

    public Collection getPayPeriodTypes() {
        return PayPeriodTypeEnum.getTypes();
    }

}
