/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 */
public class AccountTransactionDAOImpl extends HibernateDaoSupport implements
        AccountTransactionDAO {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#save(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public AccountTransaction save(AccountTransaction u) throws DAOException {

        this.getHibernateTemplate().saveOrUpdate(u);

        return u;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#get(java.lang.Long)
     */
    public AccountTransaction get(Long id) throws DAOException {

        return (AccountTransaction) this.getHibernateTemplate().get(
                AccountTransaction.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#delete(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public void delete(AccountTransaction u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#delete(java.lang.Long)
     */
    public void delete(Long id) throws DAOException {
        AccountTransaction pre = this.get(id);
        this.delete(pre);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#find(com.fivesticks.time.prepay.PrepayTransactionFilter)
     */
    public List find(final AccountTransactionCriteriaParameters filter) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return  hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                ValidationHelper help = new ValidationHelper();

                Criteria c = session.createCriteria(AccountTransaction.class);

                if (!help.isStringEmpty(filter.getOwnerKey())) {
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));
                }
                
                if (!help.isStringEmpty(filter.getObjectType())) {
                    c.add(Expression.eq("objectType", filter.getObjectType()));
                }
                
                if (help.isNonZero(filter.getObjectId())) {
                    c.add(Expression.eq("objectId", filter.getObjectId()));
                }

                if (!help.isStringEmpty(filter.getObjectTransactionType())) {
                    c.add(Expression.eq("objectTransactionType", filter.getObjectTransactionType()));
                }
                
                /*
                 * rsc 2005-06-20 this could be deleted after the main system is updated.
                 */
//                if (filter.getCustomerId() != null
//                        && filter.getCustomerId().longValue() > 0) {
//                    c.add(Expression.eq("customerId", filter.getCustomerId()));
//                }
                
                if (filter.getVestigalCustomerId() != null
                        && filter.getVestigalCustomerId().longValue() > 0) {
                    c.add(Expression.eq("customerId", filter.getVestigalCustomerId()));
                }
                

                if (filter.getActivityId() != null
                        && filter.getActivityId().longValue() > 0) {
                    c.add(Expression.eq("activityId", filter.getActivityId()));
                }

                if (!help.isStringEmpty(filter.getType())) {
                    c.add(Expression.eq("type", filter.getType()));
                }

                if (!help.isStringEmpty(filter.getComments())) {
                    c.add(Expression.ilike("comments", "%"
                            + filter.getComments() + "%"));
                }

                if (!help.isStringEmpty(filter.getEnteredBy()))
                    c.add(Expression.eq("username", filter.getEnteredBy()));

                if (filter.getArchived() != null)
                    c.add(Expression.eq("archived", filter.getArchived()));

                if (filter.getDateRangeStart() != null
                        && filter.getDateRangeStop() != null) {
                    c.add(Expression.ge("date", filter.getDateRangeStart()
                            .getDate()));
                    c.add(Expression.le("date", filter.getDateRangeStop()
                            .getDate()));
                } else if (filter.getDateRangeStart() != null) {
                    c.add(Expression.ge("date", filter.getDateRangeStart()
                            .getDate()));
                } else if (filter.getDateRangeStop() != null) {
                    c.add(Expression.le("date", filter.getDateRangeStop()
                            .getDate()));
                }

                if (filter.isSortDateAscending())
                    c.addOrder(Order.asc("date"));
                else
                    c.addOrder(Order.desc("date"));

                List ret = c.list();

                return ret;
            }
        });

    }

}