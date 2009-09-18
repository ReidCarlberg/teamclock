/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;



import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 */
public class LookupsCriteriaBuilder extends AbstractCriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return Lookup.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getCriteria(net.sf.hibernate.Session, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria, CriteriaParameters parameters)
            throws CriteriaBuilderException {

        if (!(parameters instanceof LookupCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not LookupCriterialParameters");
        }
        
        LookupCriteriaParameters params = (LookupCriteriaParameters) parameters;
        
        //owner key
        if (ParamValidator.isSearchable(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        
        //id
        if (ParamValidator.isSearchable(params.getId())) {
            criteria.add(Expression.eq("id", params.getId()));
        }
        
        //type
        if (ParamValidator.isSearchable(params.getType())) {
            criteria.add(Expression.eq("type", params.getType()));
        }
        
       this.decorateForParam(criteria,"shortName",params.getShortName());
        
       this.decorateForParam(criteria,"fullName",params.getFullName());
        
        
        
        
    }

}
