/*
 * Created on Jun 14, 2004
 *
 */
package com.fivesticks.time.customers;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class FstxTaskDAOFactory {

    public FstxTaskDAO build() {
        return (FstxTaskDAO) SpringBeanBroker.getBeanFactory().getBean(
                FstxTaskDAO.SPRING_BEAN_NAME);
    }
}