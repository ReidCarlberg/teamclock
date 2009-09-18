package com.fivesticks.time.activity;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.accountactivity.AccountActivityFilter;
import com.fstx.stdlib.common.DAOException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class ActivityDAOImpl extends HibernateDaoSupport implements
        ActivityDAO {

    public Activity save(Activity u) throws DAOException {
        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public Activity get(Long id) throws DAOException {
        return (Activity) this.getHibernateTemplate().get(
                Activity.class, id);
    }

    public void delete(Activity u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

    /**
     * Returns a list of <code>FstxActivity</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public List find(String query, String value) throws DAOException {
        return this.getHibernateTemplate().find(query, value);
    }

    public static final String SELECT_DISTINCT_TASKS_KEY = "fstxtime.select.distinct.tasks";

    public static final String SELECT_ALL = "fstxtime.select.all";

    public static final String SELECT_DISTINCT_PROJECTS = "fstxtime.select.distinct.projects";

    public static final String SELECT_DISTINCT_TASKS = "select distinct fstxtime.task from com.fivesticks.time.activity.FstxActivity fstxtime order by fstxtime.task";

    protected static Log log = LogFactory.getLog(ActivityDAOImpl.class
            .getName());

    /**
     * @param filterVO
     * @return
     */
    public List find(final ActivityFilterVO filter) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria c = session.createCriteria(Activity.class);

                if (filter.getUsername() != null
                        && filter.getUsername().length() > 0)
                    c.add(Expression.eq("username", filter.getUsername()));

                if (filter.getProject() != null
                        && filter.getProject().length() > 0) {
                    c.add(Expression.eq("project", filter.getProject()));

                }

                if (filter.getProjectId() != null) {
                    c.add(Expression.eq("projectId", filter.getProjectId()));

                }
                if (filter.getTaskId() != null) {
                    c.add(Expression.eq("taskId", filter.getTaskId()));

                }

                if (filter.getTask() != null && filter.getTask().length() > 0) {
                    c.add(Expression.eq("task", filter.getTask()));

                }

                if (filter.getOwnerKey() != null
                        && filter.getOwnerKey().length() > 0) {
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));
                }

                if (filter.getStart() != null && filter.getStop() != null) {
                    c.add(Expression.between("date", SimpleDate.factory.build(
                            filter.getStart()).getDate(), SimpleDate.factory
                            .build(filter.getStop()).getDate()));
                } else if (filter.getStart() != null) {
                    c.add(Expression.ge("date", SimpleDate.factory.build(
                            filter.getStart()).getDate()));
                } else if (filter.getStop() != null) {
                    c.add(Expression.le("date", SimpleDate.factory.build(
                            filter.getStop()).getDate()));
                }

                if (filter.getMinimumElapsed() != null
                        && filter.getMaximumElapsed() != null) {
                    c.add(Expression.between("elapsed", filter
                            .getMinimumElapsed(), filter.getMaximumElapsed()));
                } else if (filter.getMinimumElapsed() != null) {
                    c.add(Expression.ge("elapsed", filter.getMinimumElapsed()));
                } else if (filter.getMaximumElapsed() != null) {
                    c.add(Expression.le("elapsed", filter.getMaximumElapsed()));
                }

                if (filter.getMinimumChargeableElapsed() != null
                        && filter.getMaximumChargeableElapsed() != null) {
                    c.add(Expression.between("chargeableElapsed", filter
                            .getMinimumChargeableElapsed(), filter
                            .getMaximumChargeableElapsed()));
                } else if (filter.getMinimumChargeableElapsed() != null) {
                    c.add(Expression.ge("chargeableElapsed", filter
                            .getMinimumChargeableElapsed()));
                } else if (filter.getMaximumChargeableElapsed() != null) {
                    c.add(Expression.le("chargeableElapsed", filter
                            .getMaximumChargeableElapsed()));
                }

                if (filter.getComment() != null) {

                    c.add(Expression.ilike("comments", "%"
                            + filter.getComment() + "%"));

                }

                if (filter.getMaxResults() != null) {
                    c.setMaxResults(filter.getMaxResults().intValue());
                }

                if (filter.getSortDescendingDateId().booleanValue()) {
                    c.addOrder(Order.desc("date"));
                    c.addOrder(Order.desc("id"));
                } else if (filter.getSortDescending() != null
                        && filter.getSortDescending().booleanValue()) {
                    c.addOrder(Order.desc("start"));
                } else {
                    c.addOrder(Order.asc("date"));
                    c.addOrder(Order.asc("start"));
                }

                List ret = c.list();

                return ret;
            }
        });

    }

    public List find(AccountActivityFilter filter) throws DAOException {
        return find((ActivityFilterVO) filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.activity.FstxActivityDAO#find(java.lang.String)
     */
    public List find(String query) throws DAOException {
        return this.getHibernateTemplate().find(query);
    }

}