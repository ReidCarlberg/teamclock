/*
 * Created on Jun 14, 2006
 *
 */
package com.fivesticks.time.timeclock.xwork;

import org.json.JSONObject;

import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class TimeClockHomeAsJSONAction extends AbstractJSONAction {

    public String execute() throws Exception {
        
        TimeClockHomeAction action = new TimeClockHomeAction();
        action.setSessionContext(this.getSessionContext());
        action.execute();
        
        JSONObject o = new JSONObject();
        
        o.put("showPunchIn", action.isShowPunchIn());
        o.put("showPunchOut", action.isShowPunchOut());
        o.put("showBreakStart", action.isShowBreakStart());
        o.put("showBreakStop", action.isShowBreakStop());
        
        o.put("totalToday", action.getTodaySummary().getSummary().getTotalRoundedHours());
        o.put("totalPeriod", action.getUserPayPeriodSummary().getSummary().getTotalRoundedHours());
        
        this.setJsonDataAsString(o.toString());
        
        return SUCCESS;
    }
}
