/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.customer.contactxx;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ContactCriteriaBuilder implements CriteriaDecorator {

    public Class getObjectClass() {

        return Contact.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof ContactFilter)) {
            throw new CriteriaBuilderException("parameters are not ContactFilter");
        }
        
        ContactFilter params = (ContactFilter) parameters;        

        if (params.getCustId() != null && params.getCustId().longValue() > 0) {
            criteria.add(Expression.eq("custId", params.getCustId()));
        }
        
        if (params.getNameFirst() != null && params.getNameFirst().length() > 0) {
            criteria.add(Expression.eq("nameFirst", params.getNameFirst()));
        }
        
        if (params.getNameFirstLike() != null && params.getNameFirstLike().length() > 0) {
            criteria.add(Expression.like("nameFirst", "%"+params.getNameFirstLike()+"%"));
        }

        if (params.getNameLast() != null && params.getNameLast().length() > 0) {
            criteria.add(Expression.eq("nameLast", params.getNameLast()));
        }
        
        if (params.getNameLastLike() != null && params.getNameLastLike().length() > 0) {
            criteria.add(Expression.like("nameLast", "%"+params.getNameLastLike()+"%"));
        }

        
        if (params.getOwnerKey() != null
                && params.getOwnerKey().length() > 0) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        
        criteria.addOrder(Order.asc("nameLast"));
        criteria.addOrder(Order.asc("nameFirst"));
    }

}
