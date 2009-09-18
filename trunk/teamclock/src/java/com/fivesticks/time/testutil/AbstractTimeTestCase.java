/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.testutil;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2TestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.customer.TaskTestFactory;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

public abstract class AbstractTimeTestCase extends TestCase {

    protected SystemOwner systemOwner;

    protected User user;

    protected Customer customer;

    protected Project project;

    protected Task task;

    protected ContactV2 contact;

    protected SessionContext sessionContext;

    protected String userPassword = "test123";

    protected Log log = LogFactory.getLog(AbstractTimeTestCase.class);

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerTestFactory.getOwner();
        user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        customer = CustomerTestFactory.getPersisted(systemOwner);
        project = ProjectTestFactory.getPersisted(systemOwner, customer);
        task = TaskTestFactory.buildPersisted(systemOwner);
        contact = ContactV2TestFactory.singleton.getPersisted(systemOwner);
        sessionContext = SessionContextTestFactory
                .getContext(systemOwner, user);
        // timezone -- default timezone is CST
        SystemSettingsServiceDelegateFactory.factory.build(systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_TIMEZONE,
                        "America/Chicago");
        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner)
                .updateSetting(
                        SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_START, 7);
        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner)
                .updateSetting(
                        SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_ENDS, 20);
        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner)
                .updateSetting(SettingTypeEnum.SETTING_ACTIVITY_DEFAULT_TASK,
                        task.getId().toString());

        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner).updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE, PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY.getName());
        // always nice to know what we're dealing with
        UserBDFactory.factory.build().changePassword(this.user, userPassword);
    }

}
