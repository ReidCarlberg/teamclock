/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AbstractDashboardJSONAction extends AbstractDashboardContextAware {

    private String jsonDataAsString;

    /**
     * @return Returns the jsonDataAsString.
     */
    public String getJsonDataAsString() throws Exception {
        if (this.jsonDataAsString == null)
            this.setSuccessful();
        
        return jsonDataAsString;
    }

    /**
     * @param jsonDataAsString The jsonDataAsString to set.
     */
    public void setJsonDataAsString(String jsonDataAsString) {
        this.jsonDataAsString = jsonDataAsString;
    }
    
    /*
     * 2006-06-28 reid
     */
    public void setJsonResult(JSONArray result) {
        this.setJsonDataAsString(result.toString());
    }
    
    public void setJsonResult(JSONObject result) {
        this.setJsonDataAsString(result.toString());
    }
    
    public void setSuccessful() throws JSONException {
        
        JSONObject o = new JSONObject();
        o.put("success",true);
        
        this.setJsonDataAsString(o.toString());
    }
}
