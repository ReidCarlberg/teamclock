/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOImpl;

/**
 * @author REID
 *  
 */
public class ToDoDAOFactory {

    public GenericDAO build() {
        
        GenericDAOImpl ret = (GenericDAOImpl) SpringBeanBroker.getBeanFactory().getBean(
                GenericDAO.SPRING_BEAN_NAME);
        
        ret.setCriteriaBuilder(new ToDoCriteriaDecorator());
        
        return ret;
    }
}