/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.CommonPrompts;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectDeleteCommand;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author shuji
 */
public class ProjectModifyAction extends SessionContextAwareSupport implements
        ProjectModifyContextAware {

    private String submitProject;

    private String submitProjectDelete;

    private String cancelProject;

    private Long target;

    private String newLongName;

    private String newShortName;

    private boolean newIsActive;

    // private String stringActive;

    private boolean newIsPostable;

    // private String stringPostable;

    private Long newCustomer;

    private Long projectClassId;

    // private Collection booleanSurrogate;

    private Collection customers;

    private Collection collectionBoolean;

    private Collection projectClassTypes;

    private ProjectModifyContext projectModifyContext;

    public String execute() throws Exception {

        ValidationHelper help = new ValidationHelper();

        if (this.getCancelProject() != null) {
            return SUCCESS;
        }

        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory
                .build(this.getSessionContext());

        if (this.getSubmitProject() == null
                && this.getSubmitProjectDelete() == null) {
            if (this.getTarget() != null) {
                Project projectTarget = projectBD.getFstxProject(this
                        .getTarget());
                if (projectTarget != null) {
                    this.setNewShortName(projectTarget.getShortName());
                    this.setNewLongName(projectTarget.getLongName());
                    this.setNewCustomer(projectTarget.getCustId());
                    this.setNewIsActive(projectTarget.isActive());
                    this.setProjectClassId(projectTarget.getProjectClassId());
                    if (projectTarget.isPostable() != null)
                        this.setNewIsPostable(projectTarget.isPostable()
                                .booleanValue());

                    this.getProjectModifyContext().setTargetProject(
                            projectTarget);
                }
            } else {
                this.getProjectModifyContext().setTargetProject(null);
                this.setNewIsActive(true);
            }
            return INPUT;
        } else if (this.getSubmitProjectDelete() != null) {

            return this.getSessionContext()
                    .getCommonDelete(
                            new ProjectDeleteCommand(this
                                    .getProjectModifyContext()
                                    .getTargetProject(),
                                    "delete-success-project-list"));
        }

        /*
         * check for field validation
         */
        if (!StringUtils.hasText(this.getNewShortName())) {
            this.addFieldError("newShortName", "new Short name is required.");
        } else if (this.getNewShortName().indexOf(" ") > -1) {
            this
                    .addFieldError("newShortName",
                            "Short name can only contain letters, numbers and punctuation.  No spaces.");
        }

        if (!StringUtils.hasText(this.getNewLongName())) {
            this.addFieldError("newLongName", "new Long name is required.");
        }

        if (!help.isNonZero(this.getProjectClassId())) {
            this.addFieldError("projectClassId",
                    "You must select a project class id.");
        }
        /*
         * check to see if we have validation errors, if so, go back to input
         */

        if (this.hasErrors()) {
            return INPUT;
        }

        /*
         * check for uniqueness of short name
         */
        ProjectServiceDelegate psd = ProjectServiceDelegateFactory.factory
                .build(this.getSessionContext());

        Project t = psd.getFstxProjectByLongName(this.getNewLongName());

        if (t != null
                && (this.getProjectModifyContext().getTargetProject() != null && !t
                        .getId().equals(
                                this.getProjectModifyContext()
                                        .getTargetProject().getId()))) {
            this.addFieldError("newLongName", "That project name already exists.");
        }
        
        t = psd.getFstxProjectByShortName(this.getNewShortName());
        
        if (t != null
                && (this.getProjectModifyContext().getTargetProject() != null && !t
                        .getId().equals(
                                this.getProjectModifyContext()
                                        .getTargetProject().getId()))) {
            this.addFieldError("newShortName", "That project name already exists.");
        }
        
        if (this.hasErrors()) {
            return INPUT;
        }
        
        if (this.getProjectModifyContext().getTargetProject() != null) {

            // projectBD.delete(context.getTargetProject());
            // set all fields
            saveProject(this.getProjectModifyContext().getTargetProject());

        } else {

            Project newProject = new Project();
            // newProject.setOwnerKey(this.getSystemOwner().getKey());

            saveProject(newProject);

        }
        if (this.hasErrors()) {
            return INPUT;
        }

        return SUCCESS;
    }

    private void saveProject(Project newProject) {
        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        newProject.setLongName(this.getNewLongName());
        newProject.setShortName(this.getNewShortName());
        newProject.setCustId(this.getNewCustomer());
        newProject.setActive(this.getIsNewActive());
        newProject.setPostable(new Boolean(this.isNewIsPostable()));
        newProject.setProjectClassId(this.getProjectClassId());

        try {
            projectBD.save(newProject);
        } catch (Exception e) {
            this.addFieldError("newLongName", "same name is not allowed.");
        }

    }

    public String getNewLongName() {
        return newLongName;
    }

    public void setNewLongName(String newLongName) {
        this.newLongName = newLongName;
    }

    public String getSubmitProject() {
        return submitProject;
    }

    public void setSubmitProject(String submit) {
        this.submitProject = submit;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getNewShortName() {
        return newShortName;
    }

    public void setNewShortName(String newShortName) {
        this.newShortName = newShortName;
    }

    public Long getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Long newCustomer) {
        this.newCustomer = newCustomer;
    }

    public boolean getIsNewActive() {
        return newIsActive;
    }

    public void setNewIsActive(boolean newIsActive) {
        this.newIsActive = newIsActive;
    }

    public Collection getBooleanSurrogate() {

        return CommonPrompts.singleton.getBooleanSurrogates();

    }

    public Collection getCustomers() {

        try {
            this.customers = CustomerServiceDelegateFactory.factory.build(
                    this.getSessionContext()).getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.customers;
    }

    public void setCustomers(Collection customers) {
        this.customers = customers;
    }

    public String getStringActive() {
        return new Boolean(this.getIsNewActive()).toString();
    }

    public void setStringActive(String stringActive) {
        this.setNewIsActive(new Boolean(stringActive).booleanValue());
    }

    /*
     * 2006-09-01 reid shouldn't need this.
     */
    // public Collection getCollectionBoolean() {
    // collectionBoolean = new LinkedList();
    // collectionBoolean.add(new FstxProjectBoolean(true));
    // collectionBoolean.add(new FstxProjectBoolean(false));
    // return collectionBoolean;
    // }
    //
    // public void setCollectionBoolean(Collection collectionBoolean) {
    // this.collectionBoolean = collectionBoolean;
    // }
    /**
     * @return Returns the stringPostable.
     */
    public String getStringPostable() {
        return new Boolean(this.isNewIsPostable()).toString();
    }

    /**
     * @param stringPostable
     *            The stringPostable to set.
     */
    public void setStringPostable(String stringPostable) {
        this.setNewIsPostable(new Boolean(stringPostable).booleanValue());
    }

    /**
     * @return Returns the newIsPostable.
     */
    public boolean isNewIsPostable() {
        return newIsPostable;
    }

    /**
     * @param newIsPostable
     *            The newIsPostable to set.
     */
    public void setNewIsPostable(boolean newIsPostable) {
        this.newIsPostable = newIsPostable;
    }

    /**
     * @return Returns the cancelProject.
     */
    public String getCancelProject() {
        return cancelProject;
    }

    /**
     * @param cancelProject
     *            The cancelProject to set.
     */
    public void setCancelProject(String cancelProject) {
        this.cancelProject = cancelProject;
    }

    /**
     * @return Returns the projectClassTypes.
     */
    public Collection getProjectClassTypes() throws Exception {
        if (projectClassTypes == null) {
            this.setProjectClassTypes(LookupServiceDelegateFactory.factory
                    .build(this.getSystemOwner()).getAll(
                            LookupType.PROJECT_CLASS));
        }
        return projectClassTypes;
    }

    /**
     * @param projectClassTypes
     *            The projectClassTypes to set.
     */
    public void setProjectClassTypes(Collection projectClassTypes) {
        this.projectClassTypes = projectClassTypes;
    }

    /**
     * @return Returns the projectClassId.
     */
    public Long getProjectClassId() {
        return projectClassId;
    }

    /**
     * @param projectClassId
     *            The projectClassId to set.
     */
    public void setProjectClassId(Long projectClassId) {
        this.projectClassId = projectClassId;
    }

    public String getSubmitProjectDelete() {
        return submitProjectDelete;
    }

    public void setSubmitProjectDelete(String submitProjectDelete) {
        this.submitProjectDelete = submitProjectDelete;
    }

    public ProjectModifyContext getProjectModifyContext() {
        return projectModifyContext;
    }

    public void setProjectModifyContext(
            ProjectModifyContext projectModifyContext) {
        this.projectModifyContext = projectModifyContext;
    }
}