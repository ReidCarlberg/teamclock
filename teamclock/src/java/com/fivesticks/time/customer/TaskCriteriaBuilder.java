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

public class TaskCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return Task.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof TaskCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not FstxTaskFilterParams");
        }
        
        TaskCriteriaParameters params = (TaskCriteriaParameters) parameters;        

        if (params.getOwnerKey() != null
                && params.getOwnerKey().trim().length() > 0)
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));

        if (params.getId() != null)
            criteria.add(Expression.eq("id", params.getId()));

        if (params.getNameLong() != null
                && params.getNameLong().length() > 0)
            criteria.add(Expression.eq("nameLong", params.getNameLong()));

        if (params.getNameShort() != null
                && params.getNameShort().length() > 0)
            criteria.add(Expression.eq("nameShort", params.getNameShort()));

        criteria.addOrder(Order.asc("nameShort"));
    }

}
