/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.util.ToDoProjectVO;
import com.opensymphony.xwork.Action;

public class GetToDoProjectCloudAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {

        Collection projectCloud = ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).findDistinctToDoProjects(
                this.getDashboardContext());
        
        JSONArray ret = new JSONArray();
        
        for (Iterator iter = projectCloud.iterator(); iter.hasNext();) {
            ToDoProjectVO element = (ToDoProjectVO) iter.next();
            
            JSONObject o = new JSONObject();
            o.put("shortName", element.getProjectShortName());
            o.put("projectId", element.getProjectId());
            o.put("count", element.getCount());
            
            ret.put(o);
            
        }

        this.setJsonResult(ret);
        
        return Action.SUCCESS;
    }
}
