/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.todo.ToDo;

/**
 * <p>
 * 2004-08-24 Updated for SystemOwner.
 * 
 * @author Nick
 * @author Shuji
 * @author Reid
 * 
 * 
 */
public class ProjectServiceDelegateImpl extends AbstractServiceDelegate
        implements ProjectServiceDelegate {

    // private FstxProjectDAO dao;

    private SystemOwner systemOwner;

    // private FstxTimeBD fstxTimeBD;

    /**
     * 
     */
    public ProjectServiceDelegateImpl() {
        super();
    }

    // public void setFstxTimeBD(FstxTimeBD timeBD) {
    // fstxTimeBD = timeBD;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#save(com.fstx.time.customer.FstxProject)
     */
    public void save(Project project) throws ProjectBDException,
            ProjectNotUniqueException {

        // log.debug("Saving project2");
        // log.debug(project.getId());
        /*
         * shuji 8/25/2004 here's what I did.
         */

        if (isNew(project)) {
            if (!isUnique(project)) {
                throw new ProjectNotUniqueException();
            }
        }

        // if (project.getId() == null)
        decorate(project);
        // else
        // validate(project);

        this.getDao().save(project);
    }

    /**
     * @param project
     * 
     * If this is a new project it must have a unique name.
     */
    private boolean validate(Project project) throws ProjectBDException {

        if (!project.getOwnerKey().equals(this.getSystemOwner().getKey())) {
            throw new ProjectBDException(
                    "Couldn't validate that the project in question belongs to the current SystemOwner");
        }

        return true;

    }

    /**
     * @param project
     * @return If the project is being saved as a new project it will have a
     *         null Id, else it will attemt to update the existing record.
     */
    private boolean isNew(Project project) {

        if (project.getId() == null) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param project
     * @return
     */
    private boolean isUnique(Project project) throws ProjectBDException {
        /*
         * 8/25/04 Reid also need to do this by short name.
         */
        Project testProject = getFstxProjectByLongName(project
                .getLongName());

        if (testProject != null) {
            return false;
        }

        testProject = getFstxProjectByShortName(project.getShortName());

        if (testProject != null) {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#getAllForCustomer(com.fstx.time.customer.FstxCustomer)
     */
    public Collection getAllForCustomer(Customer customer)
            throws ProjectBDException {

        /*
         * 8/25/04 -- REID 2 choices. validate the customer or decorate the
         * project filter.
         */
        if (!customer.getOwnerKey().equals(this.getSystemOwner().getKey())) {
            throw new ProjectBDException();
        }

        ProjectFilterVO filter = new ProjectFilterVO();
        filter.setCustId(customer.getId());

        Collection allProjects = null;
        allProjects = this.getDao().find(filter);
        return allProjects;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#getFstxProjectByName(java.lang.String)
     */
    public Project getFstxProjectByLongName(String longName)
            throws ProjectBDException {

        ProjectFilterVO filter = new ProjectFilterVO();
        filter.setLongName(longName);
        filter.setOwnerKey(this.getSystemOwner().getKey());
        Collection list = null;
        list = this.getDao().find(filter);
        Project ret;
        if (list != null && list.size() > 0) {
            ret = (Project) list.toArray()[0];
            validate(ret);
            return ret;
        } else {
            return null;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#getFstxProjectByName(java.lang.String)
     */
    public Project getFstxProjectByShortName(String shortName)
            throws ProjectBDException {

        ProjectFilterVO filter = new ProjectFilterVO();
        filter.setShortName(shortName);
        filter.setOwnerKey(this.getSystemOwner().getKey());

        Collection list = null;
        list = this.getDao().find(filter);
        Project ret;
        if (list != null && list.size() > 0) {

            ret = (Project) list.toArray()[0];
            validate(ret);
            return ret;
        } else {
            return null;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#getFstxProject(java.lang.Long)
     */
    public Project getFstxProject(Long id) throws ProjectBDException {

        Project ret = null;
        ret = (Project) this.getDao().get(id);
        validate(ret);

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#searchByFilter(com.fstx.time.customer.FstxProjectFilterVO)
     */
    public Collection searchByFilter(ProjectFilterVO filter)
            throws ProjectBDException {

        /*
         * even the filter needs to be decorated.
         */

        filter.setOwnerKey(this.getSystemOwner().getKey());
        return this.getDao().find(filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxProjectBD#delete(com.fstx.time.customer.FstxProject)
     * 
     * 2006-07-06 reid modified to be transaction safe.
     */
    public void delete(final Project target) throws ProjectBDException {
        validate(target);

        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(
                    this.getTransactionManager());

            transactionTemplate
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    GenericDAO genericDAO = GenericDAOFactory.factory.build();

                    try {
                        // delete todos
                        genericDAO.execute("DELETE  "
                                + ToDo.class.getName()
                                + "  where projectId = " + target.getId());

                        // delete calendar items
                        genericDAO.execute("DELETE  "
                                + TcCalendar.class.getName() 
                                + "  where projectId = " + target.getId());

                        // delete activities
                        genericDAO.execute("DELETE  "
                                + Activity.class.getName() 
                                + "  where projectId = " + target.getId());

                        genericDAO.execute("DELETE  "
                                + Project.class.getName()
                                + "  where id = " + target.getId());
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Transaction failed."
                                + e.getMessage(), e);
                    }
                }

            });

        } catch (RuntimeException e) {
            throw new ProjectBDException(e);
        }

        

    }

    protected static Log log = LogFactory.getLog(ProjectServiceDelegate.class
            .getName());

    // /*
    // * (non-Javadoc)
    // *
    // * @see
    // com.fivesticks.time.customer.FstxProjectDAOAware#setFstxProjectDAO(com.fivesticks.time.customer.FstxProjectDAO)
    // */
    // public void setFstxProjectDAO(FstxProjectDAO fstxProjectDAO) {
    // this.dao = fstxProjectDAO;
    // }
    //
    // /**
    // * @return
    // */
    // public FstxProjectDAO getDao() {
    // return dao;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxProjectBD#getAllActive()
     */
    public Collection getAllActive() throws ProjectBDException {
        ProjectFilterVO filter = new ProjectFilterVO();
        filter.setIsActive(new Boolean(true));

        return this.searchByFilter(filter);

        // filter.setOwnerKey(this.getSystemOwner().getKey());
        // Collection allProjects = null;
        // try {
        // allProjects = this.getFstxProjectDAO().find(filter);
        // } catch (DAOException e) {
        // throw new FstxProjectBDException(e);
        // }
        // return allProjects;
    }

    private void decorate(Project fstxProject) {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("No session context to decorate with.");
        }
        fstxProject.setOwnerKey(this.getSystemOwner().getKey());

    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

}