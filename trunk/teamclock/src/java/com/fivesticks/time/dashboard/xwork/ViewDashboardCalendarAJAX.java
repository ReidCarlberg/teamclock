/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.calendar.CalendarDisplayWrapper;
import com.fivesticks.time.calendar.CalendarFilterDecorator;
import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.DailyBinDisplayWrapper;
import com.fivesticks.time.calendar.Schedule;
import com.fivesticks.time.calendar.ScheduleBuilder;
import com.fivesticks.time.calendar.ScheduleBuilderFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class ViewDashboardCalendarAJAX extends AbstractDashboardJSONAction {

    // private Log log = LogFactory.getLog(ViewDashboardCalendarAJAX.class);

    private static final String TYPE_WEEK="Week";

    public String execute() throws Exception {

        this.prepareDashboardContext();

        CalendarFilterParameters filter = new CalendarFilterParameters();

        filter.setUsername(this.getDashboardContext().getCalendarUsername());
        filter.setOwnerKey(this.getSessionContext().getSystemOwner().getKey());

        ScheduleBuilder builder = null;
        if (this.getDashboardContext().getCalendarDisplayType().equalsIgnoreCase(TYPE_WEEK)) {
//            new FstxCalendarFilterDecorator().decorateForWeeklySimpleDate(
//                    filter, SimpleDate.factory.build(this.getSessionContext().getResolvedNow()));

            /*
             * 2006-07-08 two weeks.
             */
            new CalendarFilterDecorator().decorateForWeeklyTodayPlusTwoWeeks(
                    filter, SimpleDate.factory.build(this.getSessionContext().getResolvedNow()));

            /*
             * RSC 2005-11-23
             * 
             * Since this is a weekly schedule, we need to set normal days
             * that don't exclude anything on the dashboard.
             */
//            builder = ScheduleBuilderFactory.factory.buildWeekly(
//                    this.getSystemOwner(), filter, 24 * 60, -1, 25, this.getSessionContext().getResolvedNow());

            builder = ScheduleBuilderFactory.factory.buildBiweekly(
                    this.getSystemOwner(), filter, 24 * 60, -1, 25, this.getSessionContext().getResolvedNow());
            /*
             * Nick 2005-10-11 See WeeklyBinDisplayWrapper is this changes.
             */

        } else {
            new CalendarFilterDecorator().decorateForDate(filter,this.getSessionContext().getResolvedNow());
            builder = ScheduleBuilderFactory.factory.buildDaily(this.getSystemOwner(),
                    filter, 30, this.getSessionContext().getSettings()
                            .getCalendarNormalDayStart(), this
                            .getSessionContext().getSettings()
                            .getCalendarNormalDayEnds());

        }

        JSONObject ret = new JSONObject();
        
        JSONArray bins = new JSONArray();
        
        Schedule s = builder.build();
        
        for (Iterator iter = s.getDisplayableBins().iterator(); iter.hasNext();) {
            DailyBinDisplayWrapper element = (DailyBinDisplayWrapper) iter.next();
            
            JSONObject b = new JSONObject();
            b.put("label", element.getBinLower());
            
            JSONArray a = new JSONArray();
            for (Iterator iterator = element.getDisplayCalendars().iterator(); iterator.hasNext();) {
                
                CalendarDisplayWrapper displayElement = (CalendarDisplayWrapper) iterator.next();
                
                JSONObject dis = new JSONObject();
                dis.put("id",displayElement.getCalendar().getId());
                dis.put("description",displayElement.getCalendar().getDescription());
                dis.put("projectId",displayElement.getCalendar().getProjectId());
                
                a.put(dis);
                
            }
            b.put("items",a);
            
            bins.put(b);
        } 
        
        ret.put("bins", bins);

        this.setJsonResult(ret);

        return SUCCESS;
    }
    
    private void prepareDashboardContext() throws Exception {
        
        // calendar

        /*
         * Set up default view, in none selected.
         */
        //if (this.getDashboardContext().getCalendarDisplayType() == null) {
            this.getDashboardContext().setCalendarDisplayType(
                    this.getUserSettingVO().getCalendarDefaultDashboardView());
            /*
             * If we don't have a preferance, day sounds good.
             */
            if (this.getDashboardContext().getCalendarDisplayType() == null) {
                this.getDashboardContext().setCalendarDisplayType(
                        UserSettingVO.DAY);
            }
        //}

        if (this.getDashboardContext().getCalendarUsername() == null)
            this.getDashboardContext().setCalendarUsername(
                    this.getSessionContext().getUser().getUsername());
    }
}