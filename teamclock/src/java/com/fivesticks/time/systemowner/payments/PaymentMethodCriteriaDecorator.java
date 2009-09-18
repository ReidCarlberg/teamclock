/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import org.hibernate.Criteria;

import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class PaymentMethodCriteriaDecorator extends AbstractCriteriaDecorator {

    public Class getObjectClass() {
        
        return PaymentMethod.class;
    }

    public void decorateCriteria(Criteria criteria, CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof PaymentMethodCriteriaParameters)) {
            throw new CriteriaBuilderException(
                    "parameters are not PaymentMethodCriteriaParameters");
        }

        PaymentMethodCriteriaParameters params = (PaymentMethodCriteriaParameters) parameters;

        this.decorateCommon(criteria, params);
        
        /*
         * others go heere 2005-12-19
         */
        this.decorateForParam(criteria,"current",params.getCurrentAsObject());
    }

}
