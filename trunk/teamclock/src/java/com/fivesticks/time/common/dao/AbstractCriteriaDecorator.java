/*
 * Created on Aug 24, 2005
 *
 */
package com.fivesticks.time.common.dao;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.employee.team.TeamServiceDelegateException;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

public abstract class AbstractCriteriaDecorator implements CriteriaDecorator {

    protected void decorateCommon(Criteria criteria, SystemOwnerKeyAware params) {

        //owner key
        if (ParamValidator.isSearchable(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        
        //id
        if (ParamValidator.isSearchable(params.getId())) {
            criteria.add(Expression.eq("id", params.getId()));
        }

    }
    
    protected void decorateForTeam(String systemOwnerKey, Criteria criteria, String fieldName, String teamShortName) {
        
        if (teamShortName == null || teamShortName.trim().length() == 0)
            return;
        
        
        Collection members;
        try {
            members = TeamServiceDelegateFactory.factory.build(systemOwnerKey).getMembers(teamShortName);
        } catch (TeamServiceDelegateException e) {
            throw new RuntimeException("failed miserably", e);
        }
        
        criteria.add(Expression.in(fieldName, members));
        
    }

    protected void decorateForParam(Criteria criteria, String field, Long parameter) {

        if (ParamValidator.isSearchable(parameter)) {
            criteria.add(Expression.eq(field, parameter));
        }
        
    }

    protected void decorateForParam(Criteria criteria, String field, String parameter) {

        if (ParamValidator.isSearchable(parameter)) {
            criteria.add(Expression.eq(field, parameter));
        }
        
    }
    
    protected void decorateForLike(Criteria criteria, String field, String parameter) {

        if (ParamValidator.isSearchable(parameter)) {
            criteria.add(Expression.like(field, "%" + parameter + "%"));
        }
        
    }

    protected void decorateForParam(Criteria criteria, String field, Boolean parameter) {

        if (ParamValidator.isSearchable(parameter)) {
            criteria.add(Expression.eq(field, parameter));
        }
        
    }

    private void decorateObjectRange(Criteria criteria, String field,
            Object lower, Object upper) {
        if (lower != null && upper != null) {
            criteria.add(Expression.between(field, lower, upper));
        } else if (lower != null) {
            criteria.add(Expression.ge(field, lower));
        } else if (upper != null) {
            criteria.add(Expression.le(field, upper));
        }
    }

    protected void decorateRange(Criteria criteria, String field, Date lower,
            Date upper) {
        decorateObjectRange(criteria, field, lower, upper);
    }

    protected void decorateRange(Criteria criteria, String field,
            Integer lower, Integer upper) {
        decorateObjectRange(criteria, field, lower, upper);
    }
    
}
