package com.fstx.stdlib.authen.users.old;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public class GroupMembershipDAOImpl extends HibernateDaoSupport implements
        GroupMembershipDAO {
    public GroupMembership save(GroupMembership u) throws DAOException {
        this.getHibernateTemplate()
                .saveOrUpdate(u);
        return u;
    }

    public GroupMembership get(Long id) throws DAOException {
        return (GroupMembership) this.getHibernateTemplate().get(
                GroupMembership.class, id);
    }

    public void delete(GroupMembership u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

    /**
     * Returns a list of <code>GroupMembership</code> s using the query
     * specified by the <code>query</code> key.
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

    public List find(String query, String[] values) throws DAOException {
        return this.getHibernateTemplate().find(query, values);
    }

    public List find(User user) throws DAOException {
        return this.getHibernateTemplate().find(SELECT_GROUPMEMBERSHIP_BY_USER,
                user.getId().toString());
    }

    public List find(UserGroup target) throws DAOException {
        return this.getHibernateTemplate().find(
                SELECT_GROUPMEMBERSHIP_BY_GROUP, target.getId().toString());
    }
}