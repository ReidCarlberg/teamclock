/*
 * Created on May 10, 2006
 *
 */
package com.fivesticks.time.common.dao;

import com.fivesticks.time.common.AbstractSpringObjectFactory;

public class GenericDAOFactory extends AbstractSpringObjectFactory {

    public static GenericDAOFactory factory = new GenericDAOFactory();
    
    public GenericDAO build() {
        
        return (GenericDAO) super.getBean(GenericDAOImpl.SPRING_BEAN_NAME);
        
    }
}
