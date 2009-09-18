/*
 * Created on Jan 27, 2005 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class TimeclockOnlyPageActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testExternalUserShouldFail() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User extUser1 = SystemUserTestFactory.singleton.buildExternal(owner);
        String pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(extUser1, pw);

        TimeclockOnlyPageAction action = new TimeclockOnlyPageAction();
        action.setSessionContext(new SessionContext());
        action.setTarget(owner.getKey());

        action.setUsername(extUser1.getUsername());
        action.setPassword(pw);

        action.setPunchInButton("login");
        String re = action.execute();
        assertTrue(re.equals(ActionSupport.INPUT));
        assertTrue(action.hasErrors());
    }

    public void testInternalUserLoginShouldSucceed() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User intUser1 = SystemUserTestFactory.singleton.buildOwner(owner);
        String pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(intUser1, pw);
        
        TimeclockOnlyPageAction action = new TimeclockOnlyPageAction();
        action.setSessionContext(new SessionContext());
        action.setTarget(owner.getKey());

        action.setUsername(intUser1.getUsername());
        action.setPassword(pw);

        action.setPunchInButton("login");
        String re = action.execute();

        assertTrue(re.equals(TimeclockOnlyPageAction.SUCCESS_PUNCHIN));
        assertTrue(!action.hasErrors());
    }

    public void testInternalUserLoginLogoutShouldSucceed() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User intUser1 = SystemUserTestFactory.singleton.buildOwner(owner);

        String pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(intUser1, pw);
        
        TimeclockOnlyPageAction action = new TimeclockOnlyPageAction();
        action.setSessionContext(new SessionContext());
        action.setTarget(owner.getKey());

        action.setUsername(intUser1.getUsername());
        action.setPassword(pw);

        action.setPunchInButton("login");
        String re = action.execute();

        assertTrue(re.equals(TimeclockOnlyPageAction.SUCCESS_PUNCHIN));
        assertTrue(!action.hasErrors());

        action.setUsername(intUser1.getUsername());
        action.setPassword(pw);

        action.setPunchInButton(null);
        action.setPunchOutButton("logout");
        String re2 = action.execute();

        assertTrue(re2.equals(TimeclockOnlyPageAction.SUCCESS_PUNCHOUT));
        assertTrue(!action.hasErrors());
    }

    public void testInternalUserShowPayPeriodShouldSucceed() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User intUser1 = SystemUserTestFactory.singleton.buildOwner(owner);
        String pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(intUser1, pw);
        
        SimpleDate refDate = SimpleDate.factory.build();
        refDate.advanceDay(-5);

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(owner);
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE,
                PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY.getName());
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE,
                refDate.getMmddyyyy());

        TimeclockOnlyPageAction action = new TimeclockOnlyPageAction();
        action.setSessionContext(new SessionContext());
        action.setTarget(owner.getKey());
        action.setUsername(intUser1.getUsername());
        action.setPassword(pw);

        action.setShowThisPeriod("current");

        String re = action.execute();

        assertTrue(!action.hasErrors());

        assertTrue(action.getUserPayPeriodSummary() != null);
        assertTrue(action.getUserPayPeriodSummary().getSummary()
                .getDisplayableUserShifts().size() == 14);

    }

    public void testInternalUserShowPayPeriodShouldSucceedNoTarget() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        User intUser1 = SystemUserTestFactory.singleton.buildOwner(owner);
        String pw = new RandomStringBuilder().buildRandomString(10);
        UserBDFactory.factory.build().changePassword(intUser1, pw);
        
        SimpleDate refDate = SimpleDate.factory.build();
        refDate.advanceDay(-5);

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(owner);
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_TYPE,
                PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY.getName());
        sd.updateSetting(SettingTypeEnum.SETTING_TIME_CLOCK_PAY_PERIOD_REFDATE,
                refDate.getMmddyyyy());

        TimeclockOnlyPageAction action = new TimeclockOnlyPageAction();
        action.setSessionContext(new SessionContext());
        /*
         * shouldn't need to worry about this.
         */
//        action.setTarget(owner.getKey());
        action.setUsername(intUser1.getUsername());
        action.setPassword(pw);

        action.setShowThisPeriod("current");

        String re = action.execute();

        assertTrue(!action.hasErrors());

        assertTrue(action.getUserPayPeriodSummary() != null);
        assertTrue(action.getUserPayPeriodSummary().getSummary()
                .getDisplayableUserShifts().size() == 14);

    }
}