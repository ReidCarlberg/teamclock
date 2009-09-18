/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fivesticks.time.timeclock.UserShiftRecord;
import com.fivesticks.time.timeclock.UserShiftRecordBuilder;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.xwork.UserListBroker;

/**
 * 2006-06-14 updated to JSON.
 * @author Reid
 *  
 */
public class ViewDashboardTimeClockAJAX extends AbstractDashboardJSONAction {

//    private Log log = LogFactory.getLog(ViewDashboardTimeClockAJAX.class);

 
    

    

    public String execute() throws Exception {

        JSONArray result = new JSONArray();
        //timeclock status
            
            UserShiftRecordBuilder shiftBuilder = new UserShiftRecordBuilder(this.getSystemOwner());
            shiftBuilder.setSessionContext(this.getSessionContext());

            for (Iterator iter = this.getActiveUsers().iterator(); iter
                    .hasNext();) {
                UserAndTypeVO element = (UserAndTypeVO) iter.next();

                UserShiftRecord shift = shiftBuilder.buildByResolvedStartDate(element
                        .getUser(), this.getSessionContext().getResolvedNow());
                UserShiftDisplayWrapper c = new UserShiftDisplayWrapper(this.getSystemOwner(),shift);

                JSONObject o = new JSONObject();
                
                o.put("username", c.getCurrent().getUser().getUsername());
                o.put("status", c.getCurrent().getStatus().getFriendlyName());
                o.put("breakHours", c.getRoundedBreakHoursAndHundredths());
                o.put("totalHours", c.getRoundedHoursAndHundredths());
                
                result.put(o);
                
            }
       


        
        this.setJsonDataAsString(result.toString());
        
        return SUCCESS;
    }

   



    public Collection getActiveUsers() throws Exception {
        return UserListBroker.singleton.getActiveUserList(this
                .getSessionContext().getSystemOwner());
    }


   
}