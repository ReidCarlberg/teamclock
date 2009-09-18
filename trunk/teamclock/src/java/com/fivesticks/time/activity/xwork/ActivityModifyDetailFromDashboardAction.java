/*
 * Created on Jun 14, 2006
 *
 */
package com.fivesticks.time.activity.xwork;

import org.json.JSONObject;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.util.Activity2JSONConverter;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

public class ActivityModifyDetailFromDashboardAction extends AbstractJSONAction {

    private Long target;

    public String execute() throws Exception {

        ActivityBD serviceDelegate = ActivityBDFactory.factory
                .build(this.getSessionContext());

        // activity
        Activity activity = serviceDelegate.get(this.getTarget());
        
        JSONObject activityJson = new Activity2JSONConverter(this
                .getSessionContext()).convert(activity);

        // get the customer
        ProjectServiceDelegate projectServiceDelegate = ProjectServiceDelegateFactory.factory
                .build(this.getSessionContext());
        Project project = projectServiceDelegate.getFstxProject(activity
                .getProjectId());
        CustomerServiceDelegate customerServiceDelegate = CustomerServiceDelegateFactory.factory
                .build(this.getSessionContext());
        Customer customer = customerServiceDelegate.getFstxCustomer(project
                .getCustId());

        // get the customer balance
        /*
         * 2006-07-06 moved to project context.
         */
//        Double currentBalance = CustomerAccountTransactionServiceDelegate.factory
//                .buildTimeAccount(this.getSystemOwner()).getCurrentBalance(
//                        customer);



        JSONObject details = new JSONObject();

        details.put("customerName", customer.getName());
//        details.put("customerBalance", currentBalance);
        details.put("activity", activityJson);





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
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

}
