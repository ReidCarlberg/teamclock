package com.fstx.stdlib.authen.users;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.useradmin.util.PasswordDigester;
import com.fivesticks.time.useradmin.util.PasswordDigesterAware;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 * 
 * @author Nick
 *  
 */
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO, PasswordDigesterAware {

    private PasswordDigester passwordDigester;
    
    public User authenticateUser(String username, String password)
            throws DAOException {
        
        throw new RuntimeException("ee");
        
//        String[] tokens = { username, username, this.getPasswordDigester().getHash(password) };
//
//        List list = this.getHibernateTemplate().find(
//                UserDAO.SELECT_BY_AUTHENTICATE_HASH, tokens);
//        if (list == null) {
//            //            log.info("list is null");
//            throw new DAOException();
//        }
//        if (list.size() != 1) {
//            //            log.info("list size is not 1 " + list.size());
//            throw new DAOException();
//
//        }
//
//        return (User) list.get(0);

    }

    public User save(User u) throws DAOException {
        
        if (u.getPassword() != null && u.getPassword().length() > 0) {
	        u.setPasswordHash(this.getPasswordDigester().getHash(u.getPassword()));
	        u.setPassword("");
        }
	        
        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public User get(Long id) throws DAOException {
        return (User) this.getHibernateTemplate().get(User.class, id);
    }

    public void delete(User u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void delete(Long id) throws DAOException {
        this.delete(this.get(id));
    }

    public Collection find(final UserFilterParameters params)
            throws DAOException, HibernateException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(User.class);

                if (params.getId() != null) {
                    criteria.add(Expression.eq("id", params.getId()));
                }
                if (params.getUsername() != null) {
                    criteria.add(Expression
                            .eq("username", params.getUsername()));
                }
                /*
                 * can no longer search by password.
                 * 2005-03-12
                 */
//                if (params.getPassword() != null) {
//                    criteria.add(Expression
//                            .eq("password", params.getPassword()));
//                }                
                if (params.getPasswordHash() != null) {
                    criteria.add(Expression
                            .eq("passwordHash", params.getPasswordHash()));
                }
                if (params.getEmail() != null) {
                    criteria.add(Expression.eq("email", params.getEmail()));
                }

                List ret = criteria.list();

                return ret;
            }
        });

    }

    /**
     * Returns a list of <code>User</code> s using the query specified by the
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

    public User get(String email) throws DAOException {
        
        throw new RuntimeException("trying to use the dao to find a user by email.");
//        List u = find(SELECT_BY_EMAIL, email);
//
//        if (u.size() == 0)
//            throw new DAOException("user not found");
//
//        if (u.size() > 1)
//            throw new DAOException(
//                    "Somehow we have more than one user with this email");
//
//        User ret = (User) u.get(0);
//
//        return ret;
    }

    static Log log = LogFactory.getLog(UserDAOImpl.class.getName());
    /**
     * @return Returns the passwordDigester.
     */
    public PasswordDigester getPasswordDigester() {
        return passwordDigester;
    }
    /**
     * @param passwordDigester The passwordDigester to set.
     */
    public void setPasswordDigester(PasswordDigester passwordDigester) {
        this.passwordDigester = passwordDigester;
    }


}