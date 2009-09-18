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

import com.fivesticks.time.customer.FstxProject;
import com.fivesticks.time.customer.FstxProjectFilterVO;
import com.fstx.stdlib.common.DAOException;

public class FstxProjectDAOImpl extends HibernateDaoSupport implements
        FstxProjectDAO {

    public FstxProject save(FstxProject u) throws DAOException {
        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public FstxProject get(Long id) throws DAOException {
        return (FstxProject) this.getHibernateTemplate().get(FstxProject.class,
                id);
    }

    public void delete(FstxProject u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

//    /**
//     * Returns a list of <code>FstxProject</code> s using the query specified
//     * by the <code>query</code> key.
//     * 
//     * @param query
//     *            the query key
//     * @param value
//     *            the value to put into the query statement. May be null
//     * @return List
//     * @throws DAOException
//     */
//    public List find(String query, String value) throws DAOException {
//        return this.getHibernateTemplate().find(query, value);
//    }
//
//    public static final String SELECT_ALL = "FstxProject.select.all";
//
//    public static final String SELECT_DISTINCT_PROJECTS = "FstxProject.select.distinct.projects";
//
//    public static final String SELECT_DISTINCT_TASKS = "FstxProject.select.distinct.tasks";

    protected static Log log = LogFactory.getLog(FstxProjectDAOImpl.class
            .getName());

    /**
     * @param filterVO
     * @return
     */
    public List find(final FstxProjectFilterVO filter) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria c = session.createCriteria(FstxProject.class);

                if (filter.getId() != null) {
                    c.add(Expression.eq("id", filter.getId()));
                }
                if (filter.getCustId() != null) {
                    c.add(Expression.eq("custId", filter.getCustId()));
                }

                if (filter.getIsActive() != null) {
                    c.add(Expression.eq("active", filter.getIsActive()));
                }

                if (filter.isPostable() != null) {
                    c.add(Expression.eq("postable", filter.isPostable()));
                }

                if (filter.getLongName() != null
                        && filter.getLongName().length() > 0) {
                    c.add(Expression.eq("longName", filter.getLongName()));
                }

                if (filter.getShortName() != null
                        && filter.getShortName().length() > 0) {
                    c.add(Expression.eq("shortName", filter.getShortName()));
                }

                if (filter.getOwnerKey() != null
                        && filter.getOwnerKey().trim().length() > 0) {
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));
                }

                c.addOrder(Order.asc("shortName"));

                List ret = c.list();

                return ret;
            }
        });

    }

}