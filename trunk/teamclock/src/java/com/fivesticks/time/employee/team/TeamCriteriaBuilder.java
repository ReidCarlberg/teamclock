/*
 * Created on Aug 21, 2005
 *
 */
package com.fivesticks.time.employee.team;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class TeamCriteriaBuilder extends AbstractCriteriaDecorator {

    public Class getObjectClass() {

        return Team.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof TeamCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not TeamCriteriaParameters");
        }
        
        TeamCriteriaParameters params = (TeamCriteriaParameters) parameters;
        
        
        
        this.decorateCommon(criteria, params);
        
        //status
        if (ParamValidator.isSearchable(params.getStatus())) {
            criteria.add(Expression.eq("status", params.getStatus()));
        }       
        
        //name
        if (ParamValidator.isSearchable(params.getName())) {
            criteria.add(Expression.eq("name", params.getName()));
        }       
        
        //nameShort
        if (ParamValidator.isSearchable(params.getNameShort())) {
            criteria.add(Expression.eq("nameShort", params.getNameShort()));
        }       
        
    }

}
