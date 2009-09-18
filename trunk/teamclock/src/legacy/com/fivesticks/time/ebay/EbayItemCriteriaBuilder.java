/*
 * Created on Aug 10, 2005
 *
 */
package com.fivesticks.time.ebay;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *
 */
public class EbayItemCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return EbayItem.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#decorateCriteria(org.hibernate.Criteria, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof EbayItemFilter)) {
            throw new CriteriaBuilderException("parameters are not EbayItemFilter");
        }
        
        EbayItemFilter params = (EbayItemFilter) parameters;

        

        
        //owner key
        if (ParamValidator.isSearchable(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        //id
        if (ParamValidator.isSearchable(params.getId())) {
            criteria.add(Expression.eq("id", params.getId()));
        }

        //project id
        if (ParamValidator.isSearchable(params.getCustId())) {
            criteria.add(Expression.eq("custId", params
                    .getCustId()));
        } 

        //status
        if (ParamValidator.isSearchable(params.getItemStatus())) {
            criteria.add(Expression.eq("itemStatus", params
                    .getItemStatus()));
        }  
        
        //
        if (ParamValidator.isSearchable(params.getDescriptionShortLike())) {
            criteria.add(Expression.like("descriptionShort", "%" + params
                    .getDescriptionShortLike() + "%"));
        }
        
        if (ParamValidator.isSearchable(params.getListingHeadlineLike())) {
            criteria.add(Expression.like("listingHeadline", "%" + params
                    .getListingHeadlineLike() + "%"));
        }        
        
        //excluded -- used for excluded types.
        if (params.getExcluded() != null) {
            criteria.add(Expression.not(Expression.in("itemStatus", params.getExcluded())));
        }
        
        if (params.getReturnMaximum() > 0 ) 
            criteria.setMaxResults(params.getReturnMaximum());
        
        //date added
        if (params.getDateAddedRangeStart() != null && params.getDateAddedRangeStop() != null) {
            criteria.add(Expression.between("dateAdded", SimpleDate.factory.build(
                    params.getDateAddedRangeStart()).getDate(), SimpleDate.factory
                    .build(params.getDateAddedRangeStop()).getDate()));

        }        
        
    }

}
