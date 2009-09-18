/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class ToDoDisplayWrapper implements Serializable {

    private Customer customer;

    private Project project;

    private final ToDo target;

    private final SystemOwner systemOwner;

    public ToDoDisplayWrapper(ToDo target, SystemOwner systemOwner) {
        this.target = target;
        this.systemOwner = systemOwner;
    }

    /**
     * @return
     */
    public String getAssignedToUser() {
        return target.getAssignedToUser();
    }

    /**
     * @return
     */
    public Date getCreateTimestamp() {
        return target.getCreateTimestamp();
    }

    /**
     * @return
     */
    public Date getDeadlineTimestamp() {
        return target.getDeadlineTimestamp();
    }

    /**
     * @return
     */
    public String getDetail() {
        return target.getDetail();
    }

    /**
     * @return
     */
    public String getEnteredByUser() {
        return target.getEnteredByUser();
    }

    /**
     * @return
     */
    public String getPriority() {
        return target.getPriority();
    }

    public String getCustomerName() {
        String ret = null;

        if (project == null) {
            getProjectName();
        }

        if (customer == null && project != null) {
            try {
                customer = CustomerServiceDelegateFactory.factory.build(
                        this.getSystemOwner()).getFstxCustomer(
                        project.getCustId());
            } catch (CustomerServiceDelegateException e) {
                ret = "[no customer]";
            }
        }

        if (customer != null)
            ret = customer.getName();
        else
            ret = "[no customer]";

        return ret;
    }

    public String getProjectName() {
        String ret = null;

        if (project == null) {
            try {
                project = ProjectServiceDelegateFactory.factory.build(
                        this.getSystemOwner()).getFstxProject(
                        target.getProjectId());
            } catch (ProjectBDException e) {
                ret = "[no project]";
            } catch (RuntimeException e) {
                ret = "[no project]";
            }
        }

        if (project != null)
            ret = project.getShortName();

        return ret;
    }

    /**
     * @return
     */
    public Long getId() {
        return target.getId();
    }

    /**
     * @return
     */
    public boolean isComplete() {
        return target.isComplete();
    }

    public String getDetailShort() {
        String ret = "";

        if (target.getDetail() == null
                || target.getDetail().trim().length() == 0) {
            ret = "[no detail]";
        } else if (target.getDetail().length() > 50) {
            ret = target.getDetail().substring(0, 50) + "...";

        } else {
            ret = target.getDetail();
        }

        return ret;

    }

    public String getDetailFull() {
        String ret = target.getDetail();

        return ret;
    }

    public String getFriendlyDeadline() {
        String ret = null;

        if (target.getDeadlineTimestamp() != null) {
            SimpleDate temp = SimpleDate.factory.build(target
                    .getDeadlineTimestamp());
            ret = temp.getMmddyyyy();
        }

        return ret;

    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @return
     */
    public Integer getSequence() {
        return target.getSequence();
    }

    public String getExternalUser() {
        return target.getExternalUser();
    }

    /**
     * @return
     */
    public String getStatus() {
        return target.getStatus();
    }

    public Collection getSchedules() {
        return target.getSchedules();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDo#getProjectId()
     */
    public Long getProjectId() {
        return target.getProjectId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDo#getTag()
     */
//    public String getTag() {
//        return target.getTag();
//    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.todo.ToDo#getEstimatedRemainingHours()
     */
    public Double getEstimatedRemainingHours() {
        return target.getEstimatedRemainingHours();
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.todo.ToDo#getEstimatedTotalHours()
     */
    public Double getEstimatedTotalHours() {
        return target.getEstimatedTotalHours();
    }

    /**
     * @return Returns the target.
     */
    public ToDo getTarget() {
        return target;
    }

}