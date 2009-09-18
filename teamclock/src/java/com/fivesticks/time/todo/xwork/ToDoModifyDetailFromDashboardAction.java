/*
 * Created on Jun 14, 2006
 *
 */
package com.fivesticks.time.todo.xwork;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.util.ToDo2JSONConverter;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class ToDoModifyDetailFromDashboardAction extends
        AbstractJSONAction {

    private Long target;


    public String execute() throws Exception {
        
        ToDoServiceDelegate toDoServiceDelegate = ToDoServiceDelegateFactory.factory.build(this.getSessionContext());
        
        //todo
        ToDo toDo = toDoServiceDelegate.get(this.getTarget());
        JSONObject todoJson = new ToDo2JSONConverter(this.getSessionContext()).convert(toDo);
        
        //get the customer
        ProjectServiceDelegate projectServiceDelegate = ProjectServiceDelegateFactory.factory.build(this.getSessionContext());
        Project project = projectServiceDelegate.getFstxProject(toDo.getProjectId());
        CustomerServiceDelegate customerServiceDelegate = CustomerServiceDelegateFactory.factory.build(this.getSessionContext());
        Customer customer = customerServiceDelegate.getFstxCustomer(project.getCustId());
        
        //get the customer balance
        /*
         * 2006-07-06 moved to the project context.
         */
//        Double currentBalance = CustomerAccountTransactionServiceDelegate.factory.buildTimeAccount(this.getSystemOwner()).getCurrentBalance(customer);
        
        //user list
        Collection activeUsers = this.getUserListProvider().getInternalUsersActiveOnly();
        
        JSONObject details = new JSONObject();
        
        details.put("customerName", customer.getName());
//        details.put("customerBalance", currentBalance);
        details.put("todo", todoJson);
        details.put("status", toDo.getStatus());
        
        JSONArray users = new JSONArray();
        
        for (Iterator iter = activeUsers.iterator(); iter.hasNext();) {
            UserAndTypeVO element = (UserAndTypeVO) iter.next();
            users.put(element.getUser().getUsername());
        }
        
        details.put("userlist", users);
        
        JSONArray priorities = new JSONArray();
        
        for (Iterator iter = ToDoPriorityTypeEnum.getAll().iterator(); iter.hasNext();) {
            ToDoPriorityTypeEnum element = (ToDoPriorityTypeEnum) iter.next();
            priorities.put(element.getName());
        }
        
        details.put("prioritylist", priorities);
        
        this.setJsonDataAsString(details.toString());
        
        return SUCCESS;
        
    }
    
    
    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
    
}
