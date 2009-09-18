/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;

/**
 * @author Reid
 */
public class AccountTransactionDAOFactory {

    public GenericDAO build() {
        return (GenericDAO) SpringBeanBroker.getBeanFactory()
                .getBean(AccountTransactionDAO.SPRING_BEAN_NAME);
    }

}