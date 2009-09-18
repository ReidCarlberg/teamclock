/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectDisplayWrapper;
import com.fivesticks.time.customer.ProjectFilterVO;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;

/**
 * @author shuji
 */
public class ProjectListAction extends SessionContextAwareSupport  {

    private Collection projectsOriginal;

    private Collection projects;

    public String execute() throws Exception {
        
       
        
        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory.build(this
                .getSessionContext().getSystemOwner());
        ProjectFilterVO filter = new ProjectFilterVO();
        //filter.setOwnerKey(sessionContext.getSystemOwner().getKey());

        // This shold use getAll()!! but not supplied it yet.
        this.setProjectsOriginal(projectBD.searchByFilter(filter));
        this.setProjects(projectsOriginal);

        return SUCCESS;

    }

    private Collection wrappingProject(Collection c) {
        Collection newList = new ArrayList();
        CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory.build(this
                .getSessionContext().getSystemOwner());
        Iterator i = c.iterator();
        while (i.hasNext()) {
            Project fp = (Project) i.next();
            ProjectDisplayWrapper fpdw = new ProjectDisplayWrapper(fp);
            String customerName = null;
            try {
                customerName = customerBD.getFstxCustomer(fp.getCustId())
                        .getName();
            } catch (Exception e) {
                new RuntimeException(e);
            }

            fpdw.setCustomerName(customerName);
            newList.add(fpdw);
        }

        return newList;
    }

    public Collection getProjects() {
        return projects;
    }

    public void setProjects(Collection projects) {
        this.projects = wrappingProject(projects);
    }


    public Collection getProjectsOriginal() {
        return projectsOriginal;
    }

    public void setProjectsOriginal(Collection projectsOriginal) {

        this.projectsOriginal = projectsOriginal;
    }
}