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
public class SystemUserDAOImpl extends HibernateDaoSupport implements
        SystemUserDAO {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#save(com.fivesticks.time.systemowner.SystemOwner)
     */
    public SystemUser save(SystemUser target) {

        this.getHibernateTemplate()
                .saveOrUpdate(target);
        
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#get(java.lang.Long)
     */
    public SystemUser get(Long id) {

        return (SystemUser) this.getHibernateTemplate().get(SystemUser.class,
                id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#delete(com.fivesticks.time.systemowner.SystemOwner)
     */
    public void delete(SystemUser target) {
        this.getHibernateTemplate().delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.systemowner.SystemOwnerDAO#search(com.fivesticks.time.systemowner.SystemOwnerSearchParameters)
     */
    public List search(final SystemUserSearchParameters params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(SystemUser.class);

                if (params.getUsername() != null
                        && params.getUsername().trim().length() > 0) {
                    criteria.add(Expression
                            .eq("username", params.getUsername()));
                }

                if (params.getOwnerKey() != null
                        && params.getOwnerKey().trim().length() > 0) {
                    criteria.add(Expression
                            .eq("ownerKey", params.getOwnerKey()));
                }

                if (params.getUserType() != null
                        && params.getUserType().trim().length() > 0) {
                    criteria.add(Expression.eq("role", params.getUserType()));
                }

                criteria.addOrder(Order.asc("username"));

                List ret = criteria.list();

                return ret;
            }
        });
    }

}