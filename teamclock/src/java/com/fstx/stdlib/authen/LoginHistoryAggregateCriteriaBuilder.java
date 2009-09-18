/*
 * Created on Aug 16, 2005
 *
 */
package com.fstx.stdlib.authen;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class LoginHistoryAggregateCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return LoginHistory.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof LoginHistoryFilterParameters)) {
            throw new CriteriaBuilderException(
                    "parameters are not LoginHistoryFilterParameters");
        }

        LoginHistoryFilterParameters params = (LoginHistoryFilterParameters) parameters;

        if (params.getUsername() != null && params.getUsername().length() > 0) {
            criteria.add(Expression.eq("username", params.getUsername()));
        }

        if (params.getOwnerKeyForSuperUser() != null
                && params.getOwnerKeyForSuperUser().length() > 0) {
            criteria.add(Expression.eq("ownerKey", params
                    .getOwnerKeyForSuperUser()));
        } else if (params.getOwnerKey() != null) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }

        if (params.getType() != null) {
            criteria.add(Expression.eq("type", params.getType()));
        }
        if (params.getDateFrom() != null && params.getDateTo() != null) {
            criteria.add(Expression.between("timestamp", params.getDateFrom(),
                    params.getDateTo()));
            // log.info("Using a between!");
        } else {
            // log.info("Not Using a between!");
            if (params.getDateFrom() != null) {
                criteria.add(Expression.ge("timestamp", params.getDateFrom()));
            }
            if (params.getDateTo() != null) {
                criteria.add(Expression.le("timestamp", params.getDateTo()));

            }
        }

        ProjectionList projectionList = Projections.projectionList().add(
                Projections.count("id"), "eventCount");

        if (params.getGroupByOwnerKey() != null
                && params.getGroupByOwnerKey().booleanValue()) {
            projectionList.add(Projections.groupProperty("ownerKey"));
        }
        if (params.getGroupByUser() != null
                && params.getGroupByUser().booleanValue()) {
            projectionList.add(Projections.groupProperty("username"));
        }

        
        criteria.setProjection(projectionList);
        
        criteria.addOrder(Order.desc("eventCount"));
    }
}
