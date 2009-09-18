package com.fivesticks.time.activity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.Task;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 *  
 */
public interface ActivityBD  {

    public void delete(Activity target) throws ActivityBDException;

    /**
     * @param timeTemp
     */
    public Activity save(Activity timeTemp)
            throws ActivityBDException;

    /**
     * @return
     */
    public List searchAllDistinctProjects() throws ActivityBDException;

    /**
     * @param filter
     * @return
     */
    public List searchByFilter(ActivityFilterVO filter)
            throws ActivityBDException;



    /**
     * @param long1
     * @return
     */
    public Activity get(Long long1) throws ActivityBDException;

//    public Collection getTodaysActivityForUser(User user)
//            throws FstxActivityBDException;

    public Collection getDaysActivityForUser(String username, Date resolvedTarget)
            throws ActivityBDException;

    public Activity stop(Long long1)
            throws ActivityBDException;

    public Activity stop(Activity fstxActivity)
            throws ActivityBDException;

    public Activity start(Activity fstxActivity)
            throws ActivityBDException;

    public Collection getActivityForUserByDate(User user, Date start)
            throws ActivityBDException;

    public Collection getActivityByProject(Project project) throws ActivityBDException;
    
    public List getTimeTotalsByProjectClass(ActivityFilterVO filter);
    
    public List getTimeTotalsByProject(ActivityFilterVO filter);
    
    public Activity post(User user, Project project, Date localizedStartDate, Date localizedStopDate, Task task, String comments) throws ActivityBDException;
    
    public Collection getUnpostedActivityForPostableProjects(String username);
}