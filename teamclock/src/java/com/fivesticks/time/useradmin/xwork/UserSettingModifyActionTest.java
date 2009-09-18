/*
 * Created on 2005-10-7 Nick
 */
package com.fivesticks.time.useradmin.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegate;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.opensymphony.xwork.ActionSupport;

public class UserSettingModifyActionTest extends AbstractTimeTestCase {

    public void testUserSettings() throws Exception {

        UserSettingModifyAction action = new UserSettingModifyAction();
        action.setUserModifyContext(new UserModifyContext());
        action.setSessionContext(this.sessionContext);
        UserAndTypeVO userAndTypeVO = new UserAndTypeVO();
        userAndTypeVO.setUser(this.user);
        userAndTypeVO.setUserTypeEnum(UserTypeEnum.PRIVILEGED);
        action.getUserModifyContext().setTarget(userAndTypeVO);

        assertEquals(ActionSupport.INPUT, action.execute());

        UserSettingVO vo = action.getUserModifyContext().getUserSettingVO();
        assertNotNull(vo);

        assertNull(vo.getCalendarDefaultDashboardView());
        assertNull(vo.getCalendarNotificationEmailAddress());

        vo.setCalendarNotificationEmailAddress("test@test.com");
        vo.setCalendarDefaultDashboardView("weekly");

        action.setUserSettingSubmit("Submit");
        assertEquals(ActionSupport.SUCCESS, action.execute());

        UserSettingServiceDelegate cssd = UserSettingServiceDelegateFactory.factory
                .build(this.systemOwner);
        // UserSettingVO vo = cssd.find(this.user);
        // assertNull(vo.getAccountBalanceNotifyEmailAddress());
        // assertNull(vo.getAccountBalanceNotifyFrequency());

        UserSettingVO voReload = cssd.find(this.user);

        assertEquals("test@test.com", voReload
                .getCalendarNotificationEmailAddress());
        assertEquals("weekly", voReload.getCalendarDefaultDashboardView());
    }

}
