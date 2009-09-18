/*
 * Created on Nov 14, 2006
 *
 */
package com.fivesticks.time.contactv2;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.system.DatabaseSchemaUpdaterException;

public class ContactV2TestTest extends TestCase {

    public void testBasic() throws Exception {

        LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) SpringBeanBroker
                .getBeanFactory().getBean("&mySessionFactory");

        try {
            lsfb.updateDatabaseSchema();
//            lsfb.createDatabaseSchema();
        } catch (HibernateException e) {
            throw new DatabaseSchemaUpdaterException(e);
        }

    }
}
