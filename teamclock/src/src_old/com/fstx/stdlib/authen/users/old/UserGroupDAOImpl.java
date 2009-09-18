package com.fstx.stdlib.authen.users.old;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public class UserGroupDAOImpl extends HibernateDaoSupport implements
        UserGroupDAO {
    public UserGroup save(UserGroup u) throws DAOException {
        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public UserGroup get(Long id) throws DAOException {
        return (UserGroup) this.getHibernateTemplate().get(UserGroup.class, id);
    }

    public void delete(UserGroup u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

    /**
     * Returns a list of <code>Group</code> s using the query specified by the
     * <code>query</code> key.
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

    public List find(String query) throws DAOException {
        return this.getHibernateTemplate().find(query);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.authen.users.UserGroupDAO#findByName(java.lang.String)
     */
    public UserGroup findByName(final String groupName) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        List data = null;

        data = (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(UserGroup.class);

                criteria.add(Expression.eq("name", groupName));

                List ret = criteria.list();

                return ret;
            }
        });

        UserGroup ret = null;

        if (data != null && data.size() == 1) {
            ret = (UserGroup) data.toArray()[0];
        }
        return ret;
    }
}