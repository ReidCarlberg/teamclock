/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.customer;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ProjectCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return Project.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof ProjectFilterVO)) {
            throw new CriteriaBuilderException("parameters are not FstxProjectFilterVO");
        }
        
        ProjectFilterVO params = (ProjectFilterVO) parameters;        

        if (params.getId() != null) {
            criteria.add(Expression.eq("id", params.getId()));
        }
        if (params.getCustId() != null) {
            criteria.add(Expression.eq("custId", params.getCustId()));
        }

        if (params.getIsActive() != null) {
            criteria.add(Expression.eq("active", params.getIsActive()));
        }

        if (params.isPostable() != null) {
            criteria.add(Expression.eq("postable", params.isPostable()));
        }

        if (params.getLongName() != null
                && params.getLongName().length() > 0) {
            criteria.add(Expression.eq("longName", params.getLongName()));
        }

        if (params.getShortName() != null
                && params.getShortName().length() > 0) {
            criteria.add(Expression.eq("shortName", params.getShortName()));
        }

        if (params.getOwnerKey() != null
                && params.getOwnerKey().trim().length() > 0) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }

        criteria.addOrder(Order.asc("shortName"));

    }

}
