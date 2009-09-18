/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author Nick
 *  
 */
public interface ProjectServiceDelegate extends  SystemOwnerAware {

    /**
     * @param temp
     */
    public void save(Project temp) throws ProjectBDException,
            ProjectNotUniqueException;

    //    public void setFstxTimeBD(FstxTimeBD timeBD);

    /**
     * @param customer
     * @return
     */
    public Collection getAllForCustomer(Customer customer)
            throws ProjectBDException;

    public Collection getAllActive() throws ProjectBDException;

    /**
     * @param string
     * @return
     */
    Project getFstxProjectByLongName(String longName)
            throws ProjectBDException;

    Project getFstxProjectByShortName(String shortName)
            throws ProjectBDException;

    /**
     * @param long1
     * @return
     */
    Project getFstxProject(Long long1) throws ProjectBDException;

    /**
     * @param filterVO
     * @return
     */
    Collection searchByFilter(ProjectFilterVO filterVO)
            throws ProjectBDException;

    /**
     * @param target
     */
    void delete(Project target) throws ProjectBDException;

}