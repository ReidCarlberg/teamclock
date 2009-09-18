/*
 * Created on Apr 29, 2004
 *
 */
package com.fivesticks.time.customer;

/**
 * @author Nick
 *  
 */
public class ProjectFactory {

    public static ProjectFactory singleton = new ProjectFactory();

    /**
     *  
     */
    public ProjectFactory() {
        super();
    }

    /**
     * @param name
     * @return
     */
    public Project build(String name) throws ProjectFactoryExecption {
        Customer customer = null;
        try {
            customer = CustomerServiceDelegateFactory.factory.buildEmpty().getUnassignedCustomer();
        } catch (CustomerServiceDelegateException e) {
            throw new ProjectFactoryExecption(e);

        }
        return build(name, customer);
    }

    public Project build(String name, Customer customer) {
        Project project = new Project();
        project.setActive(true);
        project.setCustId(customer.getId());
        project.setLongName(name);
        project.setShortName(name);

        return project;
    }

}