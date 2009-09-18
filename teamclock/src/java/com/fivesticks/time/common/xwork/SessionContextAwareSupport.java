/*
 * Created on Mar 26, 2005 by REID
 */
package com.fivesticks.time.common.xwork;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.customer.providers.CustomerListProvider;
import com.fivesticks.time.customer.providers.CustomerListProviderAware;
import com.fivesticks.time.customer.providers.CustomerListProviderBDImpl;
import com.fivesticks.time.customer.providers.ProjectListProvider;
import com.fivesticks.time.customer.providers.ProjectListProviderAllBDImpl;
import com.fivesticks.time.customer.providers.ProjectListProviderAware;
import com.fivesticks.time.customer.providers.TaskListProvider;
import com.fivesticks.time.customer.providers.TaskListProviderAware;
import com.fivesticks.time.customer.providers.TaskListProviderBDImpl;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.provider.UserListProvider;
import com.fivesticks.time.useradmin.provider.UserListProviderAware;
import com.fivesticks.time.useradmin.provider.UserListProviderBrokerImpl;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegate;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class SessionContextAwareSupport extends ActionSupport implements
        SessionContextAware, CustomerListProviderAware,
        ProjectListProviderAware, TaskListProviderAware, UserListProviderAware {

    public static final String CANCEL = "cancel"; // most of the time we don't
                                                    // need this

    public static final DecimalFormat hundredthsFormatter = new DecimalFormat("#,##0.00");
    
    private SessionContext sessionContext;

    private String confirm;

    private CustomerListProvider customerListProvider;

    private ProjectListProvider projectListProvider;

    private TaskListProvider taskListProvider;

    private UserListProvider userListProvider;

    private Collection maximums;
    private UserSettingServiceDelegate userSettingServiceDelegate;
    private UserSettingVO userSettingVO;

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;

        /*
         * 2005-06-19 reid for the list providers
         */

        CustomerListProviderBDImpl clp = new CustomerListProviderBDImpl();
        clp.setSystemOwner(sessionContext.getSystemOwner());
        this.setCustomerListProvider(clp);

        ProjectListProviderAllBDImpl plp = new ProjectListProviderAllBDImpl();
        plp.setSystemOwner(sessionContext.getSystemOwner());
        this.setProjectListProvider(plp);

        TaskListProviderBDImpl tlp = new TaskListProviderBDImpl();
        tlp.setSystemOwner(sessionContext.getSystemOwner());
        this.setTaskListProvider(tlp);

        /*
         * 2005-06-29 reid for the user list provider
         */
        UserListProviderBrokerImpl ulp = new UserListProviderBrokerImpl();
        ulp.setSystemOwner(sessionContext.getSystemOwner());
        this.setUserListProvider(ulp);

    }

    protected SystemOwner getSystemOwner() {
        SystemOwner ret = null;
        if (this.getSessionContext() != null) {
            ret = this.getSessionContext().getSystemOwner();
        }
        return ret;
    }

    protected String getSuccess() {
        if (this.getSessionContext().getSuccessOverride() != null)
            return this.getSessionContext().getSuccessOverride();
        else
            return SUCCESS;
    }

    protected void clearSuccessOverride() {
        this.getSessionContext().setSuccessOverride(null);
    }

    /**
     * @return Returns the deleteConfirm.
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param deleteConfirm
     *            The deleteConfirm to set.
     */
    public void setConfirm(String deleteConfirm) {
        this.confirm = deleteConfirm;
    }

    /**
     * @return Returns the customerListProvider.
     */
    public CustomerListProvider getCustomerListProvider() {
        return customerListProvider;
    }

    /**
     * @param customerListProvider
     *            The customerListProvider to set.
     */
    public void setCustomerListProvider(
            CustomerListProvider customerListProvider) {
        this.customerListProvider = customerListProvider;
    }

    /**
     * @return Returns the projectListProvider.
     */
    public ProjectListProvider getProjectListProvider() {
        return this.projectListProvider;
    }

    /**
     * @param projectListProvider
     *            The projectListProvider to set.
     */
    public void setProjectListProvider(ProjectListProvider projectListProvider) {
        this.projectListProvider = projectListProvider;
    }

    /**
     * @return Returns the taskListProvider.
     */
    public TaskListProvider getTaskListProvider() {
        return taskListProvider;
    }

    /**
     * @param taskListProvider
     *            The taskListProvider to set.
     */
    public void setTaskListProvider(TaskListProvider taskListProvider) {
        this.taskListProvider = taskListProvider;
    }

    public Collection getCustomers() throws Exception {
        return this.getCustomerListProvider().getCustomers();
    }

    public Collection getProjects() throws Exception {
        return this.getProjectListProvider().getProjects();
    }

    public Collection getTasks() throws Exception {
        return this.getTaskListProvider().getTasks();
    }

    /**
     * @return Returns the userListProvider.
     */
    public UserListProvider getUserListProvider() {
        return userListProvider;
    }

    /**
     * @param userListProvider
     *            The userListProvider to set.
     */
    public void setUserListProvider(UserListProvider userListProvider) {
        this.userListProvider = userListProvider;
    }

    public Collection getInternalUsers() {
        return this.getUserListProvider().getInternalUsersAll();
    }

    public Collection getActiveInternalUsers() {
        return this.getUserListProvider().getInternalUsersActiveOnly();
    }

    public Collection getMaximums() {
        if (maximums == null) {
            maximums = new ArrayList();
//            maximums.add(new IntKeyValueVO(10, 10));
            maximums.add(new IntKeyValueVO(25, 25));
            maximums.add(new IntKeyValueVO(50, 50));
            maximums.add(new IntKeyValueVO(100, 100));
            maximums.add(new IntKeyValueVO(200, 200));
        }
        return maximums;
    }

    public boolean hasText(String test) {
        return test != null && test.trim().length() > 0;
    }

    public UserSettingServiceDelegate getUserSettingServiceDelegate() {
        if (this.userSettingServiceDelegate == null) {
            this.userSettingServiceDelegate = UserSettingServiceDelegateFactory.factory
                    .build(this.getSystemOwner());
        }
    
        return this.userSettingServiceDelegate;
    }

    public UserSettingVO getUserSettingVO()
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException {
        if (this.userSettingVO == null) {
            this.userSettingVO = this.getUserSettingServiceDelegate().find(
                    this.getSessionContext().getUser().getUser());
    
        }
    
        return userSettingVO;
    }

    public void setUserSettingServiceDelegate(
            UserSettingServiceDelegate userSettingServiceDelegate) {
        this.userSettingServiceDelegate = userSettingServiceDelegate;
    }

    public void setUserSettingVO(UserSettingVO userSettingVO) {
        this.userSettingVO = userSettingVO;
    }
    
    public String getSessionRemoteAddress() {

        String ret = "Remote address not available";

        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext().get(WebWorkStatics.HTTP_REQUEST);
        
        if (request != null)
            ret = request.getRemoteAddr();

        return ret;
    }
    


    public void validateFieldHasText(String text, String field, String error) {
        if (!hasText(text)) {
            addFieldError(field, error);
        }
    }
    
    public void validateFieldHasLong(Long longValue, String field, String error) {
        if (longValue == null || longValue.longValue() == 0) {
            addFieldError(field, error);
        }
    }
}
