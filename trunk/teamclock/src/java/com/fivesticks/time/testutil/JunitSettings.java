/*
 * Created on May 25, 2004
 *
 */
package com.fivesticks.time.testutil;

import com.fivesticks.time.common.Settings;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.config.DatabaseInitializer;
import com.fivesticks.time.events.EventChannelInitializer;
import com.fivesticks.time.settings.broker.MasterSettingsBroker;
import com.fivesticks.time.system.messages.MailSettingsBean;

/**
 * @author REID
 *  
 */
public class JunitSettings {

    //public static final String TEST_FILEPATH =
    // "/home/shuji/eclipse3workspace/fstxtime/WEB-INF/src";
//    public static final String TEST_FILEPATH = "c:/java/eclipse3data/fstxtime/WEB-INF/src";
	public static  String TEST_FILEPATH = "";
    public static void initializeTestSystem() throws Exception {
        
        initializeTestSystem(1);
    }

    public static void initializeTestSystem(int quantityOwners)
            throws Exception {
        initializeTestSystemNoDatabase();
        new DatabaseInitializer().initializeTables(quantityOwners);
        /*
         * rsc 2005-05-23 added this--when running multiple tests, this wasn't getting
         * reset and so contained stale data.
         */
        MasterSettingsBroker.singleton.resetBroker();
    }

    public static void initializeTestSystemNoDatabase() {
        
        System.setProperty(Settings.KEY_PATH, TEST_FILEPATH);
        
        
        MailSettingsBean mailSettingsBean = (MailSettingsBean) SpringBeanBroker
                .getBeanFactory().getBean(MailSettingsBean.SPRING_BEAN_NAME);

        //2005-11-27 RSC Refactored to use a default system owner and individual 
        //smtp settings
//        MailHost host = MailHost.factory.build(
//                mailSettingsBean.getSmtpServer(), mailSettingsBean
//                        .getUsername(), mailSettingsBean.getPassword());
//
//        MailHostBroker.singleton.setHost(host);

        /*
         * make sure we have some channels
         */
        new EventChannelInitializer().initialize();
    }

    /**
     * make sure the dump file is on the test_filepath.
     * 
     * @param dumpFileName
     * @throws Exception
     */
    public static void initializeTestSystemWithMySQLDump(
            String dropDumpFileName, String addDumpFileName) throws Exception {

        System.setProperty(Settings.KEY_PATH, TEST_FILEPATH);
        new DatabaseInitializer().initializeFromDumpFile(dropDumpFileName,
                addDumpFileName);

    }
}