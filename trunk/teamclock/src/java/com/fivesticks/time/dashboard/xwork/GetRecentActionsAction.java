/*
 * Created on Jun 25, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.dashboard.util.DashboardRecentAction;

public class GetRecentActionsAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {
        
        JSONArray r = new JSONArray();
        
        for (Iterator iter = this.getSessionContext().getRecentActions().iterator(); iter.hasNext();) {
            DashboardRecentAction element = (DashboardRecentAction) iter.next();
            JSONObject o = new JSONObject();
            o.put("typeName", element.getTypeName());
            o.put("description", element.getDescription());
            o.put("id", element.getId());
            
            r.put(o);
        }
        
        this.setJsonDataAsString(r.toString());
        
        
        
        return SUCCESS;
        
    }
}
