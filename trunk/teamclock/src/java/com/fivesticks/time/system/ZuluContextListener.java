/*
 * Created on Jul 17, 2003
 *
 */
package com.fivesticks.time.system;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid S Carlberg
 * @version 1.0.0
 */
public class ZuluContextListener implements ServletContextListener {

    private Log log = LogFactory.getLog(this.getClass());

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {

        /*
         * Nick 2005-10-6 This will make all new Date() relative to GMT 0. It
         * sees the local of the server and the local time of the server and
         * figures out GMT 0. We adjust then for users.
         */
        TimeZone.setDefault(TimeZone.getTimeZone("Zulu"));

//        MailSettingsBean mailSettingsBean = (MailSettingsBean) SpringBeanBroker
//                .getBeanFactory().getBean(MailSettingsBean.SPRING_BEAN_NAME);
//
//        try {
//            new StartupValidator().validate();
//        } catch (Exception e) {
//
//        }
//
//        //
//        // /*
//        // * events
//        // */
//        new EventChannelInitializer().initialize();

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {

    }

}