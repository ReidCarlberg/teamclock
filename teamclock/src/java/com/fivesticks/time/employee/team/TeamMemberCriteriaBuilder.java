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

public class TeamMemberCriteriaBuilder extends AbstractCriteriaDecorator {

    public Class getObjectClass() {

        return TeamMember.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof TeamMemberCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not TeamMemberCriteriaParameters");
        }
        
        TeamMemberCriteriaParameters params = (TeamMemberCriteriaParameters) parameters;
        

        this.decorateCommon(criteria, params);
        
        //team id
        if (ParamValidator.isSearchable(params.getTeamId())) {
            criteria.add(Expression.eq("teamId", params.getTeamId()));
        }
        
        //username
        if (ParamValidator.isSearchable(params.getUsername())) {
            criteria.add(Expression.eq("username", params.getUsername()));
        }
                
        
        //role
        if (ParamValidator.isSearchable(params.getRole())) {
            criteria.add(Expression.eq("role", params.getRole()));
        }
        
    }

}
