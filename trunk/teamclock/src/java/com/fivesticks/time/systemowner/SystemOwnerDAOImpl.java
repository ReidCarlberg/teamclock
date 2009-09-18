/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Reid
 */
public class SystemOwnerDAOImpl extends HibernateDaoSupport implements
        SystemOwnerDAO {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#save(com.fivesticks.time.systemowner.SystemOwner)
     */
    public SystemOwner save(SystemOwner target) {

        this.getHibernateTemplate().saveOrUpdate(target);
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#get(java.lang.Long)
     */
    public SystemOwner get(Long id) {

        return (SystemOwner) this.getHibernateTemplate().get(SystemOwner.class,
                id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#delete(com.fivesticks.time.systemowner.SystemOwner)
     */
    public void delete(SystemOwner target) {
        this.getHibernateTemplate().delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#search(com.fivesticks.time.systemowner.SystemOwnerSearchParameters)
     */
    public List search(final SystemOwnerCriteriaParameters params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        // if (params.getName() != null || params.getAddress1() != null
        // || params.getAddress2() != null) {
        // throw new RuntimeException("not implemented");
        // }

        return hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(SystemOwner.class);

                /*
                 * 2005-12-22 RSC this has just the basics -- that's probably
                 * fine.
                 */
                if (params.getKey() != null
                        && params.getKey().trim().length() > 0) {
                    criteria.add(Expression.eq("key", params.getKey()));
                } else if (params.getKeyLike() != null && params.getKeyLike().length() > 0) {
                    criteria.add(Expression.like("key", "%" + params.getKeyLike() + "%"));
                }

                if (params.getName() != null
                        && params.getName().trim().length() > 0) {
                    criteria.add(Expression.like("name", "%" + params.getName() + "%"));
                }

                if (params.getContactEmail() != null
                        && params.getContactEmail().trim().length() > 0) {
                    criteria.add(Expression.like("contactEmail", "%" + params
                            .getContactEmail() + "%"));
                }
                
                if (params.getContactName() != null
                        && params.getContactName().trim().length() > 0) {
                    criteria.add(Expression.like("contactName", "%" + params
                            .getContactName() + "%"));
                }
                

                if (params.getAccount() != null
                        && params.getAccount().trim().length() > 0) {
                    criteria.add(Expression.eq("account", params.getAccount()));
                }

                if (params.getBillingFrequency() != null
                        && params.getBillingFrequency().trim().length() > 0) {
                    criteria.add(Expression.eq("billingFrequency", params
                            .getBillingFrequency()));
                }
                if (params.getMaxResults() != null
                        && params.getMaxResults().intValue() > 0) {
                    criteria.setMaxResults(params.getMaxResults().intValue());
                }

                if (params.isSortedByContactName()) {
                    criteria.addOrder(Order.asc("contactName"));
                } else if (params.isSortedByKey()) {
                    criteria.addOrder(Order.asc("key"));
                } else {
                    criteria.addOrder(Order.asc("id"));
                }

                List ret = criteria.list();

                return ret;
            }
        });
    }

}