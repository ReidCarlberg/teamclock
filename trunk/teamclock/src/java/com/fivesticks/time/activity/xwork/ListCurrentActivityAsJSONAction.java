/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import java.util.Collection;

import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.util.Activity2JSONConverter;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class ListCurrentActivityAsJSONAction extends AbstractJSONAction {

    public String execute() throws Exception {

        Collection c = ActivityBDFactory.factory.build(
                this.getSessionContext()).getActivityForUserByDate(
                this.getSessionContext().getUser().getUser(),
                this.getSessionContext().getResolvedNow());
        
        this.setJsonDataAsString(new Activity2JSONConverter(this.getSessionContext()).convert(c).toString());
        
        return SUCCESS;

    }
}
