/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.externaluser;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ExternalUserCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return ExternalUser.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof ExternalUserFilter)) {
            throw new CriteriaBuilderException(
                    "parameters are not ExternalUserFilter");
        }

        ExternalUserFilter params = (ExternalUserFilter) parameters;

        if (params.getUsername() != null
                && params.getUsername().trim().length() > 0) {
            criteria.add(Expression.eq("username", params.getUsername()));
        }

        if (params.getOwnerKey() != null
                && params.getOwnerKey().trim().length() > 0) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }

        if (params.getCustomerId() != null
                && params.getCustomerId().longValue() > 0) {
            criteria.add(Expression.eq("customerId", params.getCustomerId()));
        }

        criteria.addOrder(Order.asc("username"));

    }

}
