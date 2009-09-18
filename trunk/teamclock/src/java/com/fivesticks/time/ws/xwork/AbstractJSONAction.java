/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.ws.xwork;

import org.json.JSONException;
import org.json.JSONObject;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public abstract class AbstractJSONAction extends SessionContextAwareSupport {

    private String jsonDataAsString;

    /**
     * @return Returns the jsonDataAsString.
     */
    public String getJsonDataAsString() throws Exception {
        if (jsonDataAsString == null) 
            this.setSuccessful();
        
        return jsonDataAsString;
    }

    /**
     * @param jsonDataAsString The jsonDataAsString to set.
     */
    public void setJsonDataAsString(String jsonDataAsString) {
        this.jsonDataAsString = jsonDataAsString;
    }
    
    public void setSuccessful() throws JSONException {
        
        JSONObject o = new JSONObject();
        o.put("success",true);
        
        this.setJsonDataAsString(o.toString());
    }
    
}
