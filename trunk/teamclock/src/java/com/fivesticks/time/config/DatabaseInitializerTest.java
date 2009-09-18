package com.fivesticks.time.config;

import junit.framework.TestCase;

import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.fivesticks.time.common.Settings;
import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class DatabaseInitializerTest extends TestCase {

    /**
     * Constructor for DatabaseInitializerTest.
     * 
     * @param arg0
     */
    public DatabaseInitializerTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    //    public void testInitializeTables() throws Exception {
    //        TestSettings.initializeTestSystem();
    //    }

    public void testSchemaCreateFromSpring() throws Exception {

        System.setProperty(Settings.KEY_PATH, "c:/java/eclipse3Data/fstxtime/WEB-INF/src");

        LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) SpringBeanBroker
                .getBeanFactory().getBean("&mySessionFactory");

        lsfb.dropDatabaseSchema();

        lsfb.createDatabaseSchema();

    }

}