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

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerFilterVO;
import com.fstx.stdlib.common.DAOException;

public class FstxCustomerDAOImpl extends HibernateDaoSupport implements
        FstxCustomerDAO {

    public FstxCustomer save(FstxCustomer u) throws DAOException {

        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public FstxCustomer get(Long id) throws DAOException {
        return (FstxCustomer) this.getHibernateTemplate().get(
                FstxCustomer.class, id);
    }

    public void delete(FstxCustomer u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        delete(this.get(id));
    }

    /**
     * Returns a list of <code>FstxCustomer</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public List searchFstxCustomers(String query, String value)
            throws DAOException {
        return this.getHibernateTemplate().find(query, value);
    }

//    public static final String SELECT_ALL = "FstxCustomer.select.all";
//
//    public static final String SELECT_DISTINCT_PROJECTS = "FstxCustomer.select.distinct.projects";
//
//    public static final String SELECT_DISTINCT_TASKS = "FstxCustomer.select.distinct.tasks";

    protected static Log log = LogFactory.getLog(FstxCustomerDAOImpl.class
            .getName());

    /**
     * @param filterVO
     * @return
     */
    public List find(final FstxCustomerFilterVO filter) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria c = session.createCriteria(FstxCustomer.class);

                if (filter.getCity() != null && filter.getCity().length() > 0) {
                    c.add(Expression.eq("city", filter.getCity()));
                }

                if (filter.getName() != null && filter.getName().length() > 0) {
                    c.add(Expression.eq("name", filter.getName()));
                }

                if (filter.getId() != null) {
                    c.add(Expression.eq("id", filter.getId()));
                }

                if (filter.getState() != null && filter.getState().length() > 0) {
                    c.add(Expression.eq("state", filter.getState()));
                }

                if (filter.getStreet1() != null
                        && filter.getStreet1().length() > 0) {
                    c.add(Expression.eq("street1", filter.getStreet1()));
                }

                if (filter.getStreet2() != null
                        && filter.getStreet2().length() > 0) {
                    c.add(Expression.eq("street2", filter.getStreet2()));
                }

                if (filter.getZip() != null && filter.getZip().length() > 0) {
                    c.add(Expression.eq("zip", filter.getZip()));
                }
                
                if (filter.getStatus() != null && filter.getStatus().longValue() > 0) {
                    c.add(Expression.eq("status", filter.getStatus()));
                }
                //			c.addOrder(Order.asc("date"));
                //
                c.addOrder(Order.asc("name"));

                if (filter.getOwnerKey() != null
                        && filter.getOwnerKey().length() > 0) {
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));
                }

                List ret = c.list();

                return ret;
            }
        });

    }

}