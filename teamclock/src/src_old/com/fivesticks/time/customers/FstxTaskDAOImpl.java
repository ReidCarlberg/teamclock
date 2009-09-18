package com.fivesticks.time.customers;

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

import com.fivesticks.time.customer.FstxTask;
import com.fivesticks.time.customer.FstxTaskFilterParams;
import com.fstx.stdlib.common.DAOException;

public class FstxTaskDAOImpl extends HibernateDaoSupport implements FstxTaskDAO {

    public FstxTask save(FstxTask u) throws DAOException {
        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public FstxTask get(Long id) throws DAOException {
        return (FstxTask) this.getHibernateTemplate().get(FstxTask.class, id);
    }

    public void delete(FstxTask u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

    /**
     * Returns a list of <code>FstxTask</code> s using the query specified by
     * the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public List searchFstxTasks(String query, String value) throws DAOException {
        return this.getHibernateTemplate().find(query, value);
    }

    public List searchFstxTasks(String query) throws DAOException {
        return this.getHibernateTemplate().find(query);
    }

    public static final String SELECT_ALL = "FstxTask.select.all";

    public static final String SELECT_DISTINCT_PROJECTS = "FstxTask.select.distinct.projects";

    public static final String SELECT_DISTINCT_TASKS = "FstxTask.select.distinct.tasks";

    protected static Log log = LogFactory.getLog(FstxTaskDAOImpl.class
            .getName());

    /**
     * @param filterVO
     * @return
     */
    public List find(final FstxTaskFilterParams filter) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria c = session.createCriteria(FstxTask.class);

                if (filter.getOwnerKey() != null
                        && filter.getOwnerKey().trim().length() > 0)
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));

                if (filter.getId() != null)
                    c.add(Expression.eq("id", filter.getId()));

                if (filter.getNameLong() != null
                        && filter.getNameLong().length() > 0)
                    c.add(Expression.eq("nameLong", filter.getNameLong()));

                if (filter.getNameShort() != null
                        && filter.getNameShort().length() > 0)
                    c.add(Expression.eq("nameShort", filter.getNameShort()));

                c.addOrder(Order.asc("nameShort"));

                List ret = c.list();

                return ret;
            }
        });

    }

}