/*
 * Created on May 17, 2005 by Reid
 */
package com.fstx.stdlib.authen.users;



import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 */
public class UserCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return User.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getCriteria(net.sf.hibernate.Session, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria, CriteriaParameters parameters)
            throws CriteriaBuilderException {

        if (!(parameters instanceof UserCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not UserCriterialParameters");
        }
        
        UserCriteriaParameters params = (UserCriteriaParameters) parameters;
        
        //email
        if (ParamValidator.isSearchable(params.getEmail()))
            criteria.add(Expression.eq("email", params.getEmail()));

        //resetKey
        if (ParamValidator.isSearchable(params.getResetKey()))
            criteria.add(Expression.eq("resetKey", params.getResetKey()));

        //inactive
        if (ParamValidator.isSearchable(params.getInactive()))
            criteria.add(Expression.eq("inactive", params.getInactive()));
        
        //passwordHash
        if (ParamValidator.isSearchable(params.getPasswordHash()))
            criteria.add(Expression.eq("passwordHash", params.getPasswordHash()));
        
        //username
        if (ParamValidator.isSearchable(params.getUsername()))
            criteria.add(Expression.eq("username", params.getUsername()));
        
        //password
        if (ParamValidator.isSearchable(params.getPassword()))
            throw new RuntimeException("password isn't searchable.  Use passwordHash.");
        
        //passwordExpires
        if (ParamValidator.isSearchable(params.getPasswordExpires()))
            throw new RuntimeException("passwordExpires isn't searchable.");
        
    }

}
