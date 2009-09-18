/*
 * Created on Jun 14, 2004
 *
 */
package com.fivesticks.time.customers;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;

/**
 * @author Reid
 *  
 */
public class FstxCustomerDAOFactory {

    public GenericDAO build() {
        return (GenericDAO) SpringBeanBroker.getBeanFactory().getBean(
                FstxCustomerDAO.SPRING_BEAN_NAME);
    }
}