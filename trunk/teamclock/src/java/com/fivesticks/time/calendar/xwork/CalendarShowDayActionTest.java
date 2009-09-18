/*
 * Created on Nov 17, 2005
 *
 */
package com.fivesticks.time.calendar.xwork;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.calendar.CalendarTestFactory;
import com.fivesticks.time.calendar.DailyBinDisplayWrapper;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.settings.broker.MasterSettingsBroker;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class CalendarShowDayActionTest extends AbstractTimeTestCase {

    private Log log = LogFactory.getLog(CalendarShowDayAction.class);
    
    protected void setUp() throws Exception {
        super.setUp();
        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner).updateSetting(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_START, 7);
        SystemSettingsServiceDelegateFactory.factory.build(this.systemOwner).updateSetting(SettingTypeEnum.SETTING_CALENDAR_NORMAL_DAY_ENDS, 20);
        MasterSettingsBroker.singleton.resetBroker();
    }
    
    public void testBasic() throws Exception {
        
        TcCalendar c = CalendarTestFactory.build(this.sessionContext);
        
        assertNotNull(c);
        
        CalendarShowDayAction action = new CalendarShowDayAction();
        action.setSessionContext(this.sessionContext);
        action.setCalendarListContext(new CalendarListContext());
        
        String s = action.execute();
        
        log.info( " Result is " + s);
        
        assertEquals(ActionSupport.SUCCESS, s);
        
        assertNotNull(action.getSchedule());
        
        assertNotNull(action.getSchedule().getBins());
        
        assertNotNull(action.getSchedule().getDisplayableBins());
       
        for (Iterator iter = action.getSchedule().getDisplayableBins().iterator(); iter.hasNext();) {
            DailyBinDisplayWrapper element = (DailyBinDisplayWrapper) iter.next();
            log.info(element.getBinLower());
            
            assertTrue(element.isRegularDay(null));
        }
    }

}
