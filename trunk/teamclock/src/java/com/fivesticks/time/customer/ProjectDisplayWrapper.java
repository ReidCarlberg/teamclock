/*
 * Created on Aug 31, 2004
 *  
 */
package com.fivesticks.time.customer;

public class ProjectDisplayWrapper  {

    /* 
     * rsc 2005-05-20 formerly extended FstxProject.  Caused problems in testing, 
     * so removed it.
     */
    private Project project;

    private String customerName;

    public ProjectDisplayWrapper() {
        super();

    }

    public ProjectDisplayWrapper(Project project) {
        super();
        setProject(project);

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {

        this.project = project;
    }

    public String getPostable() {
        String ret = "false";

        if (project.isPostable() != null && project.isPostable().booleanValue()) {
            ret = "true";
        }

        return ret;
    }

}