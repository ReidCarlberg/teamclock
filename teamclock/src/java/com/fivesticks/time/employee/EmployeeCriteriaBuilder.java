/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;



import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 *
 */
public class EmployeeCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return Employee.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#decorateCriteria(net.sf.hibernate.Criteria, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof EmployeeCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not EmployeeCriteriaParameters");
        }
        
        EmployeeCriteriaParameters params = (EmployeeCriteriaParameters) parameters;
        
        //owner key
        if (ParamValidator.isSearchable(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        
        //id
        if (ParamValidator.isSearchable(params.getId())) {
            criteria.add(Expression.eq("id", params.getId()));
        }
        
        //status
        if (ParamValidator.isSearchable(params.getStatus())) {
            criteria.add(Expression.eq("status", params.getStatus()));
        }       
        
        //username
        if (ParamValidator.isSearchable(params.getUsername())) {
            criteria.add(Expression.eq("username", params.getUsername()));
        }     
        
        //code
        if (ParamValidator.isSearchable(params.getCode())) {
            criteria.add(Expression.eq("code", params.getCode()));
        }       
        
        //name first
        if (ParamValidator.isSearchable(params.getNameFirst())) {
            criteria.add(Expression.eq("nameFirst", params.getNameFirst()));
        }       
        
        //name last
        if (ParamValidator.isSearchable(params.getNameLast())) {
            criteria.add(Expression.eq("nameLast", params.getNameLast()));
        }       
    }

}
