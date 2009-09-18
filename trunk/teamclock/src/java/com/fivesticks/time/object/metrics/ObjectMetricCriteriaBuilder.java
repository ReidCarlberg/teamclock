/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.object.metrics;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ObjectMetricCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return ObjectMetric.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof ObjectMetricFilter)) {
            throw new CriteriaBuilderException(
                    "parameters are not ObjectMetricFilter");
        }

        ObjectMetricFilter params = (ObjectMetricFilter) parameters;

        if (params.getObjectType() != null
                && params.getObjectType().trim().length() > 0) {
            criteria.add(Expression.eq("objectType", params.getObjectType()));
        }

        if (params.getObjectId() != null
                && params.getObjectId().longValue() > 0) {
            criteria.add(Expression.eq("objectId", params.getObjectId()));
        }
        if (params.getMetricType() != null
                && params.getMetricType().trim().length() > 0) {
            criteria.add(Expression.eq("metricType", params.getMetricType()));
        }

        if (params.getMetricValue() != null
                && params.getMetricValue().trim().length() > 0) {
            criteria.add(Expression.eq("metricValue", params.getMetricValue()));
        }

        if (params.getOwnerKey() != null
                && params.getOwnerKey().trim().length() > 0) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }

        criteria.addOrder(Order.asc("metricType"));

    }

}
