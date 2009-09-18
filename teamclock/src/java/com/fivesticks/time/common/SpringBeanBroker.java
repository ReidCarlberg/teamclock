/*
 * Created on May 12, 2004
 *
 */
package com.fivesticks.time.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.fivesticks.time.system.messages.SystemMessageBean;

/**
 * 
 * @author REID
 *  
 */
public class SpringBeanBroker {

    /*
     * Nick 2005- 4-14 final removed to make it more flexable. Since the we use
     * it to init the test system we need to be able to accomodate a system that
     * extends time.
     */
    private static String SPRING_BEAN_FILE = "fstxtime-config-main.xml";

    private static String SPRING_MESSAGE_FILE = "/fstxtime-messages.xml";

    private static BeanFactory factory;

    private static BeanFactory messageFactory;

    private static Log log = LogFactory.getLog(SpringBeanBroker.class);

    public static void changeSpringBeanFile(String fileName) {
        SPRING_BEAN_FILE = fileName;
        /*
         * Reset the factory
         */
        factory = null;
    }

    public static void changeSpringMessageFile(String fileName) {
        SPRING_MESSAGE_FILE = fileName;
        /*
         * Reset the factory
         */
        messageFactory = null;
    }

    public static BeanFactory getBeanFactory() {

        if (factory == null) {

            ClassPathResource res = new ClassPathResource(SPRING_BEAN_FILE);
            //     factory = new XmlBeanFactory(res);
            /*
             * Nick 2005-7-28
             * To take advantage of springs ability to externalize 
             * deployment specific config we need to use an
             * ApplicationContext.
             */
            factory = new ClassPathXmlApplicationContext(SPRING_BEAN_FILE);
        }

        return factory;
    }

    public static BeanFactory getMessageFactory() {
        if (messageFactory == null) {

            log.info("Attempting to initialize Spring messages with ... "
                    + System.getProperty(Settings.KEY_PATH));
            //            messageFactory = new XmlBeanFactory(new FileSystemResource(System
            //                    .getProperty(Settings.KEY_PATH)
            //                    + SPRING_MESSAGE_FILE));

            ClassPathResource res = new ClassPathResource(SPRING_MESSAGE_FILE);
            //    messageFactory = new XmlBeanFactory(res);
            messageFactory = new ClassPathXmlApplicationContext(
                    SPRING_MESSAGE_FILE);
        }

        return messageFactory;
    }

    public static SystemMessageBean getCommonMessage(final String messageName) {
        return (SystemMessageBean) SpringBeanBroker.getMessageFactory()
                .getBean(messageName);
    }

}