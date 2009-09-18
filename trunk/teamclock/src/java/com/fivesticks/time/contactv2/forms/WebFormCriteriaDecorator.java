/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.contactv2.forms;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class WebFormCriteriaDecorator extends AbstractCriteriaDecorator {

    public Class getObjectClass() {

        return WebForm.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof WebFormCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not WebFormCriteriaParameters");
        }
        
        WebFormCriteriaParameters params = (WebFormCriteriaParameters) parameters;        

        super.decorateCommon(criteria,params);

        /*
         * 2006-09-03 only does key because that's what we need for right now.
         */
        this.decorateForParam(criteria,"key",params.getKey());
        
        criteria.addOrder(Order.asc("name"));

    }

}
