/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.object.comments;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ObjectCommentCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return ObjectComment.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof ObjectCommentFilter)) {
            throw new CriteriaBuilderException(
                    "parameters are not ObjectCommentFilter");
        }

        ObjectCommentFilter params = (ObjectCommentFilter) parameters;

        if (params.getObjectType() != null
                && params.getObjectType().trim().length() > 0) {
            criteria.add(Expression.eq("objectType", params
                    .getObjectType()));
        }

        if (params.getObjectId() != null
                && params.getObjectId().longValue() > 0) {
            criteria.add(Expression
                    .eq("objectId", params.getObjectId()));
        }

        if (params.getOwnerKey() != null
                && params.getOwnerKey().trim().length() > 0) {
            criteria.add(Expression
                    .eq("ownerKey", params.getOwnerKey()));
        }

        if (params.getUsername() != null
                && params.getUsername().trim().length() > 0) {
            criteria.add(Expression
                    .eq("username", params.getUsername()));
        }

        if (params.getCommentLike() != null
                && params.getCommentLike().trim().length() > 0) {
            criteria.add(Expression.like("comment", "%"
                    + params.getCommentLike() + "%"));
        }

        criteria.addOrder(Order.desc("timestamp"));


    }

}
