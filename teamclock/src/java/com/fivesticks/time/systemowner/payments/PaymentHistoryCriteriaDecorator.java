/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import org.hibernate.Criteria;

import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class PaymentHistoryCriteriaDecorator extends AbstractCriteriaDecorator {

    public Class getObjectClass() {
        
        return PaymentHistory.class;
    }

    public void decorateCriteria(Criteria criteria, CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof PaymentHistoryCriteriaParameters)) {
            throw new CriteriaBuilderException(
                    "parameters are not PaymentHistoryCriteriaParameters");
        }

        PaymentHistoryCriteriaParameters params = (PaymentHistoryCriteriaParameters) parameters;

        this.decorateCommon(criteria, params);
        
        //2005-12-19 Other Decoration Goes Here.
        
    }

}
