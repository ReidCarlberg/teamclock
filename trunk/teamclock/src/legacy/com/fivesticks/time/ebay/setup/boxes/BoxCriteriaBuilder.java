/*
 * Created on Jun 20, 2005
 *
 */
package com.fivesticks.time.ebay.setup.boxes;



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
public class BoxCriteriaBuilder implements CriteriaDecorator {

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#getObjectClass()
     */
    public Class getObjectClass() {

        return Box.class;
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.common.dao.CriteriaBuilder#decorateCriteria(net.sf.hibernate.Criteria, com.fivesticks.time.common.dao.CriteriaParameters)
     */
    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof BoxCriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not BoxCriteriaParameters");
        }
        
        BoxCriteriaParameters params = (BoxCriteriaParameters) parameters;
        
        //owner key
        if (ParamValidator.isSearchable(params.getOwnerKey())) {
            criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
        }
        //id
        if (ParamValidator.isSearchable(params.getId())) {
            criteria.add(Expression.eq("id", params.getId()));
        }

        //length
        if (ParamValidator.isSearchable(params.getLengthMinimum())) {
            criteria.add(Expression.ge("length", params.getLengthMinimum()));
        } else if (ParamValidator.isSearchable(params.getLength())) {
            criteria.add(Expression.eq("length", params.getLength()));
        }
        
        //width
        if (ParamValidator.isSearchable(params.getWidthMinimum())) {
            criteria.add(Expression.ge("width", params.getWidthMinimum()));
        } else if (ParamValidator.isSearchable(params.getWidth())) {
            criteria.add(Expression.eq("width", params.getWidth()));
        }
                        
        //height
        if (ParamValidator.isSearchable(params.getHeightMinimum())) {
            criteria.add(Expression.ge("height", params.getHeightMinimum()));
        } else if (ParamValidator.isSearchable(params.getHeight())) {
            criteria.add(Expression.eq("height", params.getHeight()));
        }
        
        //active
        if (ParamValidator.isSearchable(params.getActiveOnly())) {
            criteria.add(Expression.eq("active", params.getActiveOnly()));
        }
        
        //instock
        if (ParamValidator.isSearchable(params.getInstockOnly())) {
            criteria.add(Expression.gt("qtyOnHand", new Integer(0)));
        }
        
        //sort by
        if (ParamValidator.isSearchable(params.getSortByLength())) {
            criteria.addOrder(Order.asc("length"));
        } else if (ParamValidator.isSearchable(params.getSortByName())) {
            criteria.addOrder(Order.asc("name"));
        }

        
    }

}
