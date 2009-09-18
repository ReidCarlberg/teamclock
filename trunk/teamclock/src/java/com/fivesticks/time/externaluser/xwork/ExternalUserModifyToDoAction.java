/*
 * Created on Jan 20, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.externaluser.events.ExternalUserEventPublisher;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegateException;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author REID
 */
public class ExternalUserModifyToDoAction extends AbstractExternalCustomerAction {

    

    private Long projectId;

    private Project selectedProject;

    private String details;

    private String submitToDo;

    private String cancelToDo;

    public String execute() throws Exception {

        if (this.getCancelToDo() != null) {
            return SUCCESS;
        }

        if (this.getSubmitToDo() == null) {
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        ToDo r = persist();

        if (this.hasErrors()) {
            return INPUT;
        }

        new ExternalUserEventPublisher().publishToDoAddedEvent(this
                .getSessionContext(), this.getSessionContext()
                .getUser().getUser(), r);

        return SUCCESS;
    }

    public void validate() {
        ValidationHelper helper = new ValidationHelper();

        if (!helper.isNonZero(this.getProjectId())) {
            this.addFieldError("projectId", "You must select a project.");
        }

        if (helper.isStringEmpty(this.getDetails())) {
            this.addFieldError("details", "You must enter some details.");
        }

        if (!this.hasFieldErrors()) {

            Project proj = null;
            try {
                proj = ProjectServiceDelegateFactory.factory.build(this.getSessionContext())
                        .getFstxProject(this.getProjectId());
            } catch (ProjectBDException e) {
                //do nothing
            }
            if (proj == null) {
                this.addFieldError("projectId", "Invalid project.");
            } else {
                this.setSelectedProject(proj);
            }

        }
    }

    private ToDo persist() {

        ToDo todo = new ToDo();

        todo.setComplete(false);
        todo.setCreateTimestamp(new Date());
        todo.setDeadlineTimestamp(new Date());
        todo.setDetail(this.getDetails());
        todo.setEnteredByUser(this.getSessionContext().getSettings()
                .getTodoDefaultEnteredBy());
        todo.setExternalUser(this.getSessionContext().getUser().getUsername());
        todo.setPriority(ToDoPriorityTypeEnum.Q1.getName());
        todo.setProjectId(this.getSelectedProject().getId());
        todo.setAssignedToUser(this.getSessionContext().getSettings()
                .getTodoDefaultAssignedTo());

        try {
            ToDoServiceDelegateFactory.factory.build(
                    this.getSessionContext()).save(todo);
        } catch (ToDoServiceDelegateException e) {
            this.addActionError("Could not add this todo.");
        }

        return todo;

    }

    /**
     * @return Returns the details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     *            The details to set.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return Returns the projectShortName.
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectShortName
     *            The projectShortName to set.
     */
    public void setProjectId(Long projectShortName) {
        this.projectId = projectShortName;
    }


    /**
     * @return Returns the submitToDo.
     */
    public String getSubmitToDo() {
        return submitToDo;
    }

    /**
     * @param submitToDo
     *            The submitToDo to set.
     */
    public void setSubmitToDo(String submitToDo) {
        this.submitToDo = submitToDo;
    }

    /**
     * @return Returns the selectedProject.
     */
    private Project getSelectedProject() {
        return selectedProject;
    }

    /**
     * @param selectedProject
     *            The selectedProject to set.
     */
    private void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public Collection getProjects() {
        Collection ret = null;

        try {
            ret = ProjectServiceDelegateFactory.factory.build(this.getSessionContext())
                    .getAllForCustomer(
                            this.getSessionContext()
                                    .getExternalUserSessionContext()
                                    .getActiveCustomer());
        } catch (ProjectBDException e) {
            //
        }

        return ret;
    }

    /**
     * @return Returns the cancelToDo.
     */
    public String getCancelToDo() {
        return cancelToDo;
    }

    /**
     * @param cancelToDo
     *            The cancelToDo to set.
     */
    public void setCancelToDo(String cancelToDo) {
        this.cancelToDo = cancelToDo;
    }
}