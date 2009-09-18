package com.fivesticks.time.activity;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.Task;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.DAOException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 * 
 */
public class ActivityBDImpl extends AbstractSessionContextAwareServiceDelegate implements
        ActivityBD {

    private ActivityDAO fstxActivityDAO;

    protected Log log = LogFactory.getLog(ActivityBDImpl.class
            .getName());


    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.hours.FstxActivityBD#delete(com.fstx.time.hours.FstxActivity)
     */
    public void delete(Activity target) throws ActivityBDException {
        validate(target);
        try {
            getFstxActivityDAO().delete(target);
        } catch (DAOException e) {
            throw new ActivityBDException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.hours.FstxActivityBD#save(com.fstx.time.hours.FstxActivity)
     */
    public Activity save(Activity time) throws ActivityBDException {

        // if (time.getId() == null)
        // decorate(time);
        // else
        // validate(time);

        decorate(time);

        try {
            validateUser(time.getUsername());
        } catch (Exception e1) {
            throw new ActivityBDException("validateUser on save", e1);
        }

        Activity ret = null;
        try {
            ret = getFstxActivityDAO().save(time);
        } catch (DAOException e) {
            throw new ActivityBDException(e);
        }
        
        try {
            new ToDoEventPublisher().publishToDoWorkedOnEvent(this.getSessionContext(), ret);
        } catch (Exception e) {
            throw new ActivityBDException(e);
        }
        
        return ret;
        //new ToDoEventPublisher().publishToDoWorkedOnEvent()
    }

    /**
     * @param time
     */
    private void validateUser(String username) throws Exception {

        // log.info("searching for username " + username);

        if (username != null && username.trim().length() > 0) {
            SystemUser systemUser = SystemUserServiceDelegateFactory.factory
                    .build().get(username);

            if (!systemUser.getOwnerKey()
                    .equals(this.getSystemOwner().getKey())) {
                throw new RuntimeException(
                        "Attempting to save/search with a user that doesn't belong to this Key.");
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.hours.FstxActivityBD#searchAllDistinctProjects()
     */
    public List searchAllDistinctProjects() throws ActivityBDException {

        try {
            return getFstxActivityDAO().find(
                    ActivityDAOImpl.SELECT_DISTINCT_PROJECTS, null);
        } catch (DAOException e) {
            throw new ActivityBDException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.hours.FstxActivityBD#searchByFilter(com.fstx.time.hours.struts.FstxActivityFilterVO)
     */
    public List searchByFilter(ActivityFilterVO filter)
            throws ActivityBDException {
        // modified by shuji sep. 12

        try {
            validateUser(filter.getUsername());
        } catch (Exception e1) {

            throw new ActivityBDException(e1);
        }

        decorateFilter(filter);

        try {
            return getFstxActivityDAO().find(filter);
        } catch (DAOException e) {
            throw new ActivityBDException(e);
        }
    }
    
    public List getTimeTotalsByProjectClass(ActivityFilterVO filter) {
        
        try {
            this.handleDecorate(filter);
        } catch (AbstractServiceDelegateException e) {
            e.printStackTrace();
        }
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(" SELECT new " + ActivitySummaryByProjectClass.class.getName() + " ( ");
        sb.append("count(act.id), sum(act.chargeableElapsed), sum(act.elapsed), " +
                    "proj.projectClassId )");
        sb.append("from " + Activity.class.getName() + " as act, " + Project.class.getName());
        sb.append(" as proj where act.projectId = proj.id ");
        if (StringUtils.hasText(filter.getUsername())) {
            sb.append(" and act.username = :username ");
        }
        sb.append(" and act.ownerKey = :ownerKey and act.date between :start and :stop ");
        sb.append(" group by proj.projectClassId ");
        
//        log.info(sb);
        
        GenericDAO genericDAO = GenericDAOFactory.factory.build();
        
        List ret = genericDAO.find(sb.toString(), filter);
        
        return ret;
    }
    
    public List getTimeTotalsByProject(ActivityFilterVO filter) {
        
        try {
            this.handleDecorate(filter);
        } catch (AbstractServiceDelegateException e) {
            e.printStackTrace();
        }
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(" SELECT new " + ActivitySummaryByProject.class.getName() + " ( ");
        sb.append("count(act.id), sum(act.chargeableElapsed), sum(act.elapsed), proj.id, proj.shortName, proj.longName )");
        sb.append("from " + Activity.class.getName() + " as act, " + Project.class.getName());
        sb.append(" as proj where act.projectId = proj.id ");
        if (StringUtils.hasText(filter.getUsername())) {
            sb.append(" and act.username = :username ");
        }
        sb.append(" and act.ownerKey = :ownerKey and act.date between :start and :stop ");
        sb.append(" group by proj.id, proj.shortName, proj.longName order by proj.shortName ");
        
//        log.info(sb);
        
        GenericDAO genericDAO = GenericDAOFactory.factory.build();
        
        List ret = genericDAO.find(sb.toString(), filter);
        
        return ret;
    }    



    public Activity get(Long id) throws ActivityBDException {

        try {
            Activity fa = getFstxActivityDAO().get(id);

            /*
             * shuji added validate Sep 2 04
             */

            validate(fa);
            return fa;
        } catch (DAOException e) {
            throw new ActivityBDException();
        }
    }

    /**
     * @return
     */
    public ActivityDAO getFstxActivityDAO() {
        return fstxActivityDAO;
    }

    /**
     * @param timeDAO
     */
    public void setFstxActivityDAO(ActivityDAO timeDAO) {
        fstxActivityDAO = timeDAO;
    }



    public Collection getDaysActivityForUser(String username,
            Date resolvedTarget) throws ActivityBDException {

        ActivityFilterVO params = new ActivityFilterVO();

        SimpleDate today = SimpleDate.factory.buildMidnight(resolvedTarget);
        SimpleDate tomorrow = SimpleDate.factory.buildMidnight(resolvedTarget);
        tomorrow.advanceDay();
        tomorrow.advanceSecond(-1);

        params.setUsername(username);
        params.setStart(today.getDate());
        params.setStop(tomorrow.getDate());

        //2006-06-14 so the dashboard is most recent first.
        params.setSortDescending(Boolean.TRUE);
        
        Collection activities = this.searchByFilter(params);

        return activities;
    }

    public Collection getActivityForUserByDate(User user, Date start)
            throws ActivityBDException {

        return this.getDaysActivityForUser(user.getUsername(), start);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.activity.FstxActivityBD#stop(java.lang.Long)
     */
    public Activity stop(Long long1) throws ActivityBDException {

        Activity vo = null;

        vo = this.get(long1);

        return stop(vo);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.activity.FstxActivityBD#stop(com.fivesticks.time.activity.FstxActivity)
     */
    public Activity stop(Activity fstxActivity)
            throws ActivityBDException {

        /*
         * shuji added validate Sep 2 04, in case!
         */
        validate(fstxActivity);

        if (fstxActivity.getStop() != null) {
            throw new ActivityBDException("already stopped");
        }

        fstxActivity.setStop(this.getRounderUtil().round(
                this.getResolverUtil().resolve(new Date())));

        this.decorateWithElapsed(fstxActivity);

        return this.save(fstxActivity);
    }

    private void decorateWithElapsed(Activity fstxActivity) {

        if (fstxActivity.getStart() != null && fstxActivity.getStop() != null) {

            SimpleDate sStart = SimpleDate.factory.build(fstxActivity
                    .getStart());
            SimpleDate sEnd = SimpleDate.factory.build(fstxActivity.getStop());

            // double el = sStart.getMinutesBetween(sEnd) / 60;
            // fstxActivity.setElapsed(new Double(el));
            fstxActivity.setElapsed(this.getElapsed(sStart.getDate(), sEnd
                    .getDate()));

            fstxActivity.setChargeableElapsed(fstxActivity.getElapsed());
            
        } else {
            fstxActivity.setElapsed(new Double(0.0));
        }

    }

    /**
     * @return Returns the elapsed.
     */
    private Double getElapsed(Date start, Date stop) {

        Double newd = null;

        if (stop != null) {
            long t1 = stop.getTime();
            long t2 = start.getTime();
            double res = (double) t1 - t2;
            double cal = res / 3600000;
            DecimalFormat df = new DecimalFormat("#,##0.000");
            Double d = new Double(cal);
            String f = df.format(d);
            newd = new Double(f);
        }
        return newd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.activity.FstxActivityBD#start(com.fivesticks.time.activity.FstxActivity)
     */
    public Activity start(Activity fstxActivity)
            throws ActivityBDException {
        /*
         * shuji added validate Sep 2 04, in case!
         */

        validate(fstxActivity);

        if (fstxActivity.getId() != null || fstxActivity.getStart() != null)
            throw new ActivityBDException("only for unsaved items.");

        fstxActivity.setStart(this.getRounderUtil().round(
                this.getResolverUtil().resolve(new Date())));

        return this.save(fstxActivity);
    }


    private void validate(Activity fstxActivity)
            throws ActivityBDException {
        if (!fstxActivity.getOwnerKey().equals(this.getSystemOwner().getKey())) {
            throw new ActivityBDException(
                    "Couldn't validate that the activity in question belongs to the current SystemOwner");
        }
    }

    private void decorate(Activity fstxActivity) {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("No system owner to decorate with.");
        }
        fstxActivity.setOwnerKey(this.getSystemOwner().getKey());
    }


    private void decorateFilter(ActivityFilterVO filter) {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("No session context to decorate with.");
        }
        filter.setOwnerKey(this.getSystemOwner().getKey());

    }

    public Activity post(User user, Project project,
            Date localizedUnroundedStartDate, Date localizedUnroundedStopDate,
            Task task, String comments) throws ActivityBDException {

        if (user == null || project == null || localizedUnroundedStartDate == null || localizedUnroundedStopDate == null || task == null || comments == null) {
            throw new ActivityBDException("all parameters must have values");
        }
        
        Activity ret = new Activity();
        ret.setComments(comments);
        ret.setDate(localizedUnroundedStartDate);
        ret.setProject(project.getShortName());
        ret.setProjectId(project.getId());
        ret.setTask(task.getNameShort());
        ret.setTaskId(task.getId());
        ret.setUsername(user.getUsername());

        /*
         * note these should already by localized.
         */
        ret.setStart(this.getRounderUtil().round(
                localizedUnroundedStartDate));

        ret.setStop(this.getRounderUtil().round(
                localizedUnroundedStopDate));

        this.decorateWithElapsed(ret);

        this.save(ret);

        return ret;
    }

    public Collection getActivityByProject(Project project) throws ActivityBDException {
        
        ActivityFilterVO filter = new ActivityFilterVO();
        
        filter.setProjectId(project.getId());
        filter.setMaxResults(new Integer(25));
        filter.setSortDescendingDateId(Boolean.TRUE);
        
        return this.searchByFilter(filter);
    }

    public Collection getUnpostedActivityForPostableProjects(String username) {
        
        String hql = "select act from " +Activity.class.getName() + " as act, " + Project.class.getName() + " as proj where " +
            "act.projectId = proj.id and proj.postable = true and act.acctId is null and act.ownerKey = '" + this.getSystemOwner().getKey() + "' and act.username = '" + 
            username + "' and act.date >= '2004-11-01 00:00:00' and act.chargeableElapsed > 0.0 order by proj.shortName";
        
        Collection c = this.getDao().find(hql);
        
        return c;
    }

}