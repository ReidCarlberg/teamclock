/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;



import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 *
 */
public class SimpleCommissionCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {
        
        return SimpleCommission.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#decorateCriteria(net.sf.hibernate.Criteria, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria, CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof SimpleCommissionCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not SimpleCommissionCriteriaParameters");
        }
        
        SimpleCommissionCriteriaParameters params = (SimpleCommissionCriteriaParameters) parameters;
        
        //owner key
        if (ParamValidator.isSearchable(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        
        //id
        if (ParamValidator.isSearchable(params.getId())) {
            criteria.add(Expression.eq("id", params.getId()));
        }        
        
    }

}
