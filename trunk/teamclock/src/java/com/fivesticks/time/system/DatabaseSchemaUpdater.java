/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class DatabaseSchemaUpdater {

//    private static Log log = LogFactory.getLog(DatabaseSchemaUpdater.class);

    /**
     *  
     */
    public void update() throws DatabaseSchemaUpdaterException {
        LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) SpringBeanBroker
                .getBeanFactory().getBean("&mySessionFactory");

        try {
            lsfb.updateDatabaseSchema();
        } catch (HibernateException e) {
            throw new DatabaseSchemaUpdaterException(e);
        }

    }

}