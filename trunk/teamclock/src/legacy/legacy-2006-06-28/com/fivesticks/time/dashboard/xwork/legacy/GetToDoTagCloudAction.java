/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork.legacy;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.util.ToDoTagCountVO;
import com.opensymphony.xwork.Action;

public class GetToDoTagCloudAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {

        Collection tags = ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).findDistinctToDoTagsWithCount(
                this.getDashboardContext());
        
        JSONArray ret = new JSONArray();
            
        for (Iterator iter = tags.iterator(); iter.hasNext();) {
            ToDoTagCountVO element = (ToDoTagCountVO) iter.next();
            
            JSONObject o = new JSONObject();
            
            o.put("tag", element.getTag());
            o.put("count", element.getCount());
            
            ret.put(o);
            
            
        }

        this.setJsonResult(ret);
        
        return Action.SUCCESS;
    }

}
