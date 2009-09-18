/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.common.dao;

import org.hibernate.Criteria;

/**
 * @author Reid
 */
public interface CriteriaDecorator {

    public Class getObjectClass();
    
    public void decorateCriteria(Criteria criteria, CriteriaParameters parameters) throws CriteriaBuilderException;
    
}
