/*
 * Created on Jan 23, 2005 by REID
 */
package com.fivesticks.time.useradmin;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author REID
 */
public class UseradminDeleteCommandFactory {

    public static final String SPRING_BEAN_NAME = "useradminDeleteCommand";
    public static final UseradminDeleteCommandFactory factory = new UseradminDeleteCommandFactory();

    public UseradminDeleteCommand build() {
        return (UseradminDeleteCommand) SpringBeanBroker.getBeanFactory()
                .getBean(UseradminDeleteCommandFactory.SPRING_BEAN_NAME);
    }
}