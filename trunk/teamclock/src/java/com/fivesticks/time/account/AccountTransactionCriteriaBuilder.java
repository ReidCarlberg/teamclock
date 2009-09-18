/*
 * Created on Jul 17, 2005
 *
 */
package com.fivesticks.time.account;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;
import com.fivesticks.time.common.util.ValidationHelper;

/**
 * @author Reid
 *
 */
public class AccountTransactionCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return AccountTransaction.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#decorateCriteria(org.hibernate.Criteria, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof AccountTransactionCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not AccountTransactionCriteriaParameters");
        }
        
        AccountTransactionCriteriaParameters params = (AccountTransactionCriteriaParameters) parameters;

        ValidationHelper help = new ValidationHelper();


        if (!help.isStringEmpty(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        
        if (!help.isStringEmpty(params.getObjectType())) {
            criteria.add(Expression.eq("objectType", params.getObjectType()));
        }
        
        if (help.isNonZero(params.getObjectId())) {
            criteria.add(Expression.eq("objectId", params.getObjectId()));
        }

        if (!help.isStringEmpty(params.getObjectTransactionType())) {
            criteria.add(Expression.eq("objectTransactionType", params.getObjectTransactionType()));
        }

        /*
         * rsc 2005-07-17
         */
        if (!help.isStringEmpty(params.getObjectTransactionCode())) {
            criteria.add(Expression.eq("objectTransactionCode", params.getObjectTransactionCode()));
        }

        /*
         * rsc 2005-06-20 this could be deleted after the main system is updated.
         */
//        if (filter.getCustomerId() != null
//                && filter.getCustomerId().longValue() > 0) {
//            c.add(Expression.eq("customerId", filter.getCustomerId()));
//        }
        
        if (params.getVestigalCustomerId() != null
                && params.getVestigalCustomerId().longValue() > 0) {
            criteria.add(Expression.eq("customerId", params.getVestigalCustomerId()));
        }
        

        if (params.getActivityId() != null
                && params.getActivityId().longValue() > 0) {
            criteria.add(Expression.eq("activityId", params.getActivityId()));
        }

        if (!help.isStringEmpty(params.getType())) {
            criteria.add(Expression.eq("type", params.getType()));
        }

        if (!help.isStringEmpty(params.getComments())) {
            criteria.add(Expression.ilike("comments", "%"
                    + params.getComments() + "%"));
        }

        if (!help.isStringEmpty(params.getEnteredBy()))
            criteria.add(Expression.eq("username", params.getEnteredBy()));

        if (params.getArchived() != null)
            criteria.add(Expression.eq("archived", params.getArchived()));

        if (params.getDateRangeStart() != null
                && params.getDateRangeStop() != null) {
            criteria.add(Expression.ge("date", params.getDateRangeStart()
                    .getDate()));
            criteria.add(Expression.le("date", params.getDateRangeStop()
                    .getDate()));
        } else if (params.getDateRangeStart() != null) {
            criteria.add(Expression.ge("date", params.getDateRangeStart()
                    .getDate()));
        } else if (params.getDateRangeStop() != null) {
            criteria.add(Expression.le("date", params.getDateRangeStop()
                    .getDate()));
        }

        if (params.isSortDateAscending()) {
            criteria.addOrder(Order.asc("date"));
            criteria.addOrder(Order.asc("id"));
        } else {
            criteria.addOrder(Order.desc("date"));
            criteria.addOrder(Order.desc("id"));
        }
        

    }

}
