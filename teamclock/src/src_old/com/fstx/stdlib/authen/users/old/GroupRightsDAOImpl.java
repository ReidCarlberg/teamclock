package com.fstx.stdlib.authen.users.old;

import java.util.List;

import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.fstx.stdlib.common.dao.todelete.DAOException;

/**
 * @author Nick
 *  
 */
public class GroupRightsDAOImpl extends HibernateDaoSupport implements
        GroupRightsDAO {

    public GroupRights save(GroupRights u) throws DAOException {
        return (GroupRights) this.getHibernateTemplate().saveOrUpdateCopy(u);
    }

    public GroupRights get(Long id) throws DAOException {
        return (GroupRights) this.getHibernateTemplate().get(GroupRights.class,
                id);
    }

    public void delete(GroupRights u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

    /**
     * Returns a list of <code>GroupRights</code> s using the query specified
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
}