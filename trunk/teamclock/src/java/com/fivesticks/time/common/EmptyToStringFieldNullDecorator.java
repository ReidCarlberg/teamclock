/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.common;

import java.lang.reflect.Method;

import ognl.Ognl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmptyToStringFieldNullDecorator {

    private Log log = LogFactory.getLog(EmptyToStringFieldNullDecorator.class);

    private int depth = 0;

    public void convert(Object conversionTarget) throws Exception {

        Class c = conversionTarget.getClass();

        Method[] publicFields = c.getMethods();
        Object[] argsEmpty = {};
        Object[] argsNull = { null };

        for (int i = 0; i < publicFields.length; i++) {

            if (publicFields[i].getName().startsWith("get")
                    && publicFields[i].getReturnType().getName().equals(
                            String.class.getName())
                    && publicFields[i].getParameterTypes().length == 0) {
                String fieldName = publicFields[i].getName();
                if (!fieldName.equalsIgnoreCase("getClass")
                        && !fieldName.equalsIgnoreCase("getOwnerKey")) {

                    String result = (String) publicFields[i].invoke(
                            conversionTarget, argsEmpty);

                    if (result != null) {
                        if (result.trim().length() == 0) {
                            fieldName = fieldName.substring(3);
                            fieldName = fieldName.substring(0, 1).toLowerCase()
                                    + fieldName.substring(1);
                            // set it to null
                            try {
                                Ognl
                                        .setValue(fieldName, conversionTarget,
                                                null);
                            } catch (Exception e) {
                                log.info("error setting field with OGNL >> "
                                        + fieldName);
                            }
                        }

                    }

                }
            }

        }

        depth--;

    }

}
