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

public class CustomerCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return Customer.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof CustomerFilterVO)) {
            throw new CriteriaBuilderException("parameters are not FstxCustomerFilterVO");
        }
        
        CustomerFilterVO params = (CustomerFilterVO) parameters;        

        if (params.getCity() != null && params.getCity().length() > 0) {
            criteria.add(Expression.eq("city", params.getCity()));
        }

        if (params.getName() != null && params.getName().length() > 0) {
            criteria.add(Expression.eq("name", params.getName()));
        }
        
        if (params.getNameLike() != null && params.getNameLike().trim().length() > 0) {
            criteria.add(Expression.like("name", "%" + params.getNameLike() + "%"));
        }

        if (params.getId() != null) {
            criteria.add(Expression.eq("id", params.getId()));
        }

        if (params.getState() != null && params.getState().length() > 0) {
            criteria.add(Expression.eq("state", params.getState()));
        }

        if (params.getStreet1() != null
                && params.getStreet1().length() > 0) {
            criteria.add(Expression.eq("street1", params.getStreet1()));
        }

        if (params.getStreet2() != null
                && params.getStreet2().length() > 0) {
            criteria.add(Expression.eq("street2", params.getStreet2()));
        }

        if (params.getZip() != null && params.getZip().length() > 0) {
            criteria.add(Expression.eq("zip", params.getZip()));
        }
        
        if (params.getStatus() != null && params.getStatus().longValue() > 0) {
            criteria.add(Expression.eq("status", params.getStatus()));
        }
        //          c.addOrder(Order.asc("date"));
        //
        
        /*
         * 2006-05-22 RSC Default behavior is to hide any "hidden" customers.
         */
        if (params.getShowHidden() != null && !params.getShowHidden().booleanValue()) {
            
            criteria.add(Expression.ne("hidden", Boolean.TRUE));
        } else if ((params.getShowHidden() != null && params.getShowHidden().booleanValue())) {
            //don't need to do anything since this should include hidden and non-hidden
        }
        
        criteria.addOrder(Order.asc("name"));

        if (params.getOwnerKey() != null
                && params.getOwnerKey().length() > 0) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }

        if (params.getReturnMaximum() > 0 ) 
            criteria.setMaxResults(params.getReturnMaximum());
    }

}
