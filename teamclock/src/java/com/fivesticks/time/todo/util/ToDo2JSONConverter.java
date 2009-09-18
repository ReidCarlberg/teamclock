/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.todo.util;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.json.AbstractJSONConverter;
import com.fivesticks.time.common.json.ObjectToJSONConverter;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.todo.ToDo;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class ToDo2JSONConverter extends AbstractJSONConverter {

    public ToDo2JSONConverter(SessionContext sessionContext) {
        this.initialize(sessionContext);
    }

    public JSONArray convert(Collection todos) throws Exception {

        JSONArray ret = new JSONArray();

        for (Iterator iter = todos.iterator(); iter.hasNext();) {
            ToDo element = (ToDo) iter.next();

            ret.put(convert(element));
        }

        return ret;

    }

    public JSONObject convert(ToDo target) throws Exception {

        Project project = this.getProjectServiceDelegate().getFstxProject(target.getProjectId());
        
        Customer customer = this.getCustomerServiceDelegate().getFstxCustomer(project.getCustId());
        
        JSONObject ret = new ObjectToJSONConverter().convert(target);
        
        
        /*
         * some additional decoration
         */
        ret.put("projectKey", project.getShortName());
        ret.put("customerName", customer.getName());
        
        /*
         * age is days based on unresolved time
         */
        SimpleDate today = SimpleDate.factory.build();
        SimpleDate created = SimpleDate.factory.build(target.getCreateTimestamp());
        
        int age = created.getDaysBetween(today);
        
        ret.put("age",age);
        
        return ret;
    }

}
