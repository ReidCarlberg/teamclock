/*
 * Created on May 14, 2005 by Reid
 */
package com.fivesticks.time.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Reid
 */
public class GenericDAOImpl extends HibernateDaoSupport implements GenericDAO {

    public static final String SPRING_BEAN_NAME = "genericDao";

    private CriteriaDecorator criteriaBuilder;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.dao.GenericDAO#get(java.lang.Long)
     */
    public Object get(Long id) {

        return this.getHibernateTemplate().get(
                this.getCriteriaBuilder().getObjectClass(), id);
    }

    public Object get(Class objectClazz, Long id) {

        return this.getHibernateTemplate().get(objectClazz, id);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.dao.GenericDAO#save(java.lang.Object)
     */
    public void save(Object target) {

        this.getHibernateTemplate().saveOrUpdate(target);
    }

    public void saveAll(Collection target) {
        this.getHibernateTemplate().saveOrUpdateAll(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.dao.GenericDAO#delete(java.lang.Object)
     */
    public void delete(Object target) {
        this.getHibernateTemplate().delete(target);
    }

    public void deleteAll(Collection target) {
        for (Iterator iter = target.iterator(); iter.hasNext();) {
            Object element = (Object) iter.next();
            this.getHibernateTemplate().delete(target);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.dao.GenericDAO#find(com.fivesticks.time.common.dao.GenericParameters)
     */
    public Collection find(final CriteriaParameters parameters) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        
        final CriteriaDecorator criteriaBuilder = this.getCriteriaBuilder();

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(criteriaBuilder
                        .getObjectClass());

                try {
                    criteriaBuilder.decorateCriteria(criteria, parameters);
                } catch (CriteriaBuilderException e) {

                    /*
                     * If there is a problem building the criteria we don't want
                     * to send anything back.
                     */
                    e.printStackTrace();
                    return new ArrayList();
                }

                List ret = criteria.list();

                return ret;
            }
        });

    }

    public Collection getAll() {
        return this.getHibernateTemplate().loadAll(
                this.getCriteriaBuilder().getObjectClass());
    }

    /**
     * @return Returns the criteriaBuilder.
     */
    public CriteriaDecorator getCriteriaBuilder() {
        return criteriaBuilder;
    }

    /**
     * @param criteriaBuilder
     *            The criteriaBuilder to set.
     */
    public void setCriteriaBuilder(CriteriaDecorator criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public int execute(final String hql, final Object objectWithUpdateFields) {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        Integer ret = (Integer) hibernateTemplate
                .execute(new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        query.setProperties(objectWithUpdateFields);
                        int i = query.executeUpdate();
                        return new Integer(i);
                    }
                });

        return ret.intValue();
    }

    public List find(final String hql) {

        return find(hql, null);
    }

    public List find(final String hql, final Object objectWithUpdateFields) {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        List ret = (List) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                if (objectWithUpdateFields != null)
                    query.setProperties(objectWithUpdateFields);

                return query.list();
            }
        });

        return ret;
    }

    public int execute(final String hql) {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        Integer ret = (Integer) hibernateTemplate
                .execute(new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);

                        int i = query.executeUpdate();
                        return new Integer(i);
                    }
                });

        return ret.intValue();
    }

}