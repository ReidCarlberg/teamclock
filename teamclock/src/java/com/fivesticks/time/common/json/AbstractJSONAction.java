/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.common.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public abstract class AbstractJSONAction extends SessionContextAwareSupport {

    private final int DEBUG_INDENT_FACTOR = 3;
    
    private JSONArray jsonArrayResult;
    
    private JSONObject jsonObjectResult;
    
    private String jsonDataAsString;

    private boolean debug;
    
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
        
        this.setJsonResult(o);
    }
    
    protected void setJsonResult(JSONObject jsonObject) throws JSONException {
        
        this.setJsonObjectResult(jsonObject);
        
        if (this.isDebug()) {
            this.setJsonDataAsString(jsonObject.toString(DEBUG_INDENT_FACTOR));
        } else {
            this.setJsonDataAsString(jsonObject.toString());
        }
    }
    
    protected void setJsonResult(JSONArray jsonArray) throws JSONException {
        
        this.setJsonArrayResult(jsonArray);
        
        if (this.isDebug()) {
            this.setJsonDataAsString(jsonArray.toString(DEBUG_INDENT_FACTOR));
        } else {
            this.setJsonDataAsString(jsonArray.toString());
        }
        
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public JSONArray getJsonArrayResult() {
        return jsonArrayResult;
    }

    public void setJsonArrayResult(JSONArray jsonArrayResult) {
        this.jsonArrayResult = jsonArrayResult;
    }

    public JSONObject getJsonObjectResult() {
        return jsonObjectResult;
    }

    public void setJsonObjectResult(JSONObject jsonObjectResult) {
        this.jsonObjectResult = jsonObjectResult;
    }
}
