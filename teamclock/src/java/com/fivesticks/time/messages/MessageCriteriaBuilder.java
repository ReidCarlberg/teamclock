/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.messages;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class MessageCriteriaBuilder extends AbstractCriteriaDecorator {

    public Class getObjectClass() {

        return Message.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof MessageCriteriaParameters)) {
            throw new CriteriaBuilderException(
                    "parameters are not MessageCriteriaParameters");
        }

        MessageCriteriaParameters params = (MessageCriteriaParameters) parameters;

        this.decorateCommon(criteria, params);
        
        /*
         * name
         */
        if (ParamValidator.isSearchable(params.getName())) {
            criteria.add(Expression.eq("name", params.getName()));
        }
        
        /*
         * subject
         */
        if (ParamValidator.isSearchable(params.getSubject())) {
            criteria.add(Expression.eq("subject", params.getSubject()));
        }
        
        /*
         * message
         */
        if (ParamValidator.isSearchable(params.getMessage())) {
            throw new RuntimeException("message isn't searchable");
        }
        
        
    }

}
