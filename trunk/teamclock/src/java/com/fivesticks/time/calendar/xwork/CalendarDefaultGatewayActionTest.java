/*
 * Created on Oct 11, 2005
 *
 * 
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.opensymphony.xwork.ActionSupport;

public class CalendarDefaultGatewayActionTest extends AbstractTimeTestCase {

    public void testCalendarDefaultGatewayActionDefault() throws Exception {
        CalendarDefaultGatewayAction action = new CalendarDefaultGatewayAction();
        action.setSessionContext(this.sessionContext);

        action.setUserSettingVO(new UserSettingVO());

        assertEquals(ActionSupport.SUCCESS + "-Day", action.execute());

    }

    public void testCalendarDefaultGatewayActionWeek() throws Exception {
        CalendarDefaultGatewayAction action = new CalendarDefaultGatewayAction();
        action.setSessionContext(this.sessionContext);

        action.setUserSettingVO(new UserSettingVO());
        action.getUserSettingVO().setCalendarDefaultTabView(
                UserSettingVO.WEEK);

        assertEquals(ActionSupport.SUCCESS + "-Week", action.execute());

    }

    public void testCalendarDefaultGatewayActionDay() throws Exception {
        CalendarDefaultGatewayAction action = new CalendarDefaultGatewayAction();
        action.setSessionContext(this.sessionContext);

        action.setUserSettingVO(new UserSettingVO());
        action.getUserSettingVO().setCalendarDefaultTabView(
                UserSettingVO.DAY);

        assertEquals(ActionSupport.SUCCESS + "-Day", action.execute());

    }

    public void testCalendarDefaultGatewayActionMonth() throws Exception {
        CalendarDefaultGatewayAction action = new CalendarDefaultGatewayAction();
        action.setSessionContext(this.sessionContext);

        action.setUserSettingVO(new UserSettingVO());
        action.getUserSettingVO().setCalendarDefaultTabView(
                UserSettingVO.MONTH);

        assertEquals(ActionSupport.SUCCESS + "-Month", action.execute());

    }

}
