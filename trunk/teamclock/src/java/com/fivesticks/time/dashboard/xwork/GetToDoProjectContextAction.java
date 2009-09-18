/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;

import org.json.JSONObject;

import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.common.json.ObjectToJSONConverter;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.opensymphony.xwork.Action;

public class GetToDoProjectContextAction extends AbstractDashboardJSONAction {

    public String execute() throws Exception {

        JSONObject ret = new JSONObject();

        if (this.getDashboardContext().getToDoProjectFilter() == null
                || this.getDashboardContext().getToDoProjectFilter()
                        .longValue() == 0) {

        } else {

            /*
             * get the customer
             */
            ProjectServiceDelegate psd = ProjectServiceDelegateFactory.factory
                    .build(this.getSessionContext());

            CustomerServiceDelegate csd = CustomerServiceDelegateFactory.factory
                    .build(this.getSessionContext());

            ContactV2ServiceDelegate contsd = ContactV2ServiceDelegateFactory.factory
                    .build(this.getSessionContext().getSystemOwner());

            /*
             * 2006-07-06 reid this throws a rather ugly exception when the target project
             * has just been deleted.
             */
            Project project = psd.getFstxProject(this.getDashboardContext()
                    .getToDoProjectFilter());

            Customer customer = csd.getFstxCustomer(project.getCustId());

            Collection contacts = contsd.getByCustomer(customer);

            /*
             * get some historical activity
             */
            ActivityBD asd = ActivityBDFactory.factory.build(this
                    .getSessionContext());

            Collection activities = asd.getActivityByProject(project);

            /*
             * build the Json object
             */
            ret.put("project", new ObjectToJSONConverter().convert(project));

            ret.put("customer", new ObjectToJSONConverter().convert(customer));

            ret.put("contacts", new ObjectToJSONConverter()
                    .convertCollection(contacts));

            ret.put("activity", new ObjectToJSONConverter()
                    .convertCollection(activities));

            ret.put("balance",
                    CustomerAccountTransactionServiceDelegate.factory
                            .buildTimeAccount(this.getSystemOwner())
                            .getCurrentBalance(customer));

        }

        this.setJsonResult(ret);

        return Action.SUCCESS;
    }
}
