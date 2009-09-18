/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;



import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 *
 */
public class EbayFormCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return EbayForm.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#decorateCriteria(net.sf.hibernate.Criteria, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof EbayFormCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not EbayFormCriteriaParameters");
        }
        
        EbayFormCriteriaParameters params = (EbayFormCriteriaParameters) parameters;
        
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
        
        //name
        if (ParamValidator.isSearchable(params.getName())) {
            criteria.add(Expression.eq("name",params.getName()));
        }
        
        //form
        if (ParamValidator.isSearchable(params.getForm())) {
            throw new RuntimeException("search by 'form' is not implemented.");
        }

        //linebreak
        if (ParamValidator.isSearchable(params.getLinebreak())) {
            throw new RuntimeException("search by 'linebreak' is not implemented.");
        }
        
        //order
        if (ParamValidator.isSearchable(params.getOrderById())) {
            criteria.addOrder(Order.asc("id"));
        } else {
            criteria.addOrder(Order.asc("name"));
        }

    }

}
