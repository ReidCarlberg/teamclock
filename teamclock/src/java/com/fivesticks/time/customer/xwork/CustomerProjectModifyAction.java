/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectDeleteCommand;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;

public class CustomerProjectModifyAction extends SessionContextAwareSupport
        implements ProjectModifyContextAware, CustomerModifyContextAware {

    private ProjectModifyContext projectModifyContext;

    private CustomerModifyContext customerModifyContext;

    private Long target;

    private String cancelProject;

    private String saveProject;

    private String submitDeleteProject;

    private Project targetProject;

    private Collection projectClassTypes;

    public String execute() throws Exception {

        if (this.getCustomerModifyContext().getTargetCustomer() == null) {
            return ERROR;
        }

        if (this.getCancelProject() != null) {
            return SUCCESS;
        }

        ProjectServiceDelegate bd = ProjectServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        if (this.getTarget() != null) {
            Project pr = bd.getFstxProject(this.getTarget());
            /*
             * validate that it belongs to this customer.
             */
            if (!pr.getCustId()
                    .equals(
                            this.getCustomerModifyContext().getTargetCustomer()
                                    .getId())) {
                return ERROR;
            }
            this.setTargetProject(pr);
            this.getProjectModifyContext().setTargetProject(pr);
            return INPUT;
        }

        if (this.getSubmitDeleteProject() != null) {
            return this.getSessionContext().getCommonDelete(
                    new ProjectDeleteCommand(this.getProjectModifyContext()
                            .getTargetProject(), "delete-success-customer-project"));
        }

        /*
         * adding
         */
        if (this.getSaveProject() == null) {
            this.setTargetProject(new Project());
            this.getProjectModifyContext().setTargetProject(null);
            return INPUT;
        }

        /*
         * validate
         */
        validate(bd);

        if (this.hasErrors()) {
            return INPUT;
        }
        /*
         * saving
         */

        if (this.getTargetProject().getPostable() == null) {
            this.getTargetProject().setPostable(new Boolean(false));
        }

        this.getTargetProject().setCustId(
                this.getCustomerModifyContext().getTargetCustomer().getId());
        if (this.getProjectModifyContext().getTargetProject() != null) {
            this.getTargetProject().setId(
                    this.getProjectModifyContext().getTargetProject().getId());
        }

        bd.save(this.getTargetProject());

        return SUCCESS;
    }

    private void validate(ProjectServiceDelegate bd) throws Exception {

        ValidationHelper help = new ValidationHelper();

        //class
        if (!help.isNonZero(this.getTargetProject().getProjectClassId())) {
            this.addFieldError("targetProject.projectClassId", "You must select a project class.");
        }
        
        // long name
        if (help.isStringEmpty(this.getTargetProject().getLongName())) {
            this.addFieldError("targetProject.longName",
                    "Long name is required.");
        }else {
            // short name must be unique
            Project t = bd.getFstxProjectByLongName(this
                    .getTargetProject().getLongName());
            if (t != null
                    && (this.getProjectModifyContext().getTargetProject() == null || !t
                            .getId().equals(
                                    this.getProjectModifyContext()
                                            .getTargetProject().getId()))) {
                this.addFieldError("targetProject.longName",
                        "Long name must be unique.");
            }
        }

        // short name
        if (help.isStringEmpty(this.getTargetProject().getShortName())) {
            this.addFieldError("targetProject.shortName",
                    "Short name is required.");
        } else if (this.getTargetProject().getShortName().indexOf(" ") > -1) {
            this.addFieldError("targetProject.shortName",
            "Project short name cannot contain a space.");
        } else {
            // short name must be unique
            Project t = bd.getFstxProjectByShortName(this
                    .getTargetProject().getShortName());
            if (t != null
                    && (this.getProjectModifyContext().getTargetProject() == null || !t
                            .getId().equals(
                                    this.getProjectModifyContext()
                                            .getTargetProject().getId()))) {
                this.addFieldError("targetProject.shortName",
                        "Short name must be unique.");
            }
        }
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
     * @return Returns the customerModifyContext.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            The customerModifyContext to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    /**
     * @return Returns the projectModifyContext.
     */
    public ProjectModifyContext getProjectModifyContext() {
        return projectModifyContext;
    }

    /**
     * @param projectModifyContext
     *            The projectModifyContext to set.
     */
    public void setProjectModifyContext(
            ProjectModifyContext projectModifyContext) {
        this.projectModifyContext = projectModifyContext;
    }

    /**
     * @return Returns the saveProject.
     */
    public String getSaveProject() {
        return saveProject;
    }

    /**
     * @param saveProject
     *            The saveProject to set.
     */
    public void setSaveProject(String saveProject) {
        this.saveProject = saveProject;
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

    /**
     * @return Returns the targetProject.
     */
    public Project getTargetProject() {
        return targetProject;
    }

    /**
     * @param targetProject
     *            The targetProject to set.
     */
    public void setTargetProject(Project targetProject) {
        this.targetProject = targetProject;
    }

    public Collection getProjectClassTypes() throws Exception {
        if (projectClassTypes == null) {
            this.setProjectClassTypes(LookupServiceDelegateFactory.factory
                    .build(this.getSystemOwner()).getAll(
                            LookupType.PROJECT_CLASS));
        }
        return projectClassTypes;
    }

    public void setProjectClassTypes(Collection projectClassTypes) {
        this.projectClassTypes = projectClassTypes;
    }

    public String getSubmitDeleteProject() {
        return submitDeleteProject;
    }

    public void setSubmitDeleteProject(String submitDeleteProject) {
        this.submitDeleteProject = submitDeleteProject;
    }
}
