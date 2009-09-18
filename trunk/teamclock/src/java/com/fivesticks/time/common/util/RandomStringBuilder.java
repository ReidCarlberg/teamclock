/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.common.util;

import java.util.Date;
import java.util.Random;

/**
 * @author Reid
 */
public class RandomStringBuilder {

    private Random random = new Random(new Date().getTime());

    public String buildRandomString(int length) {

        StringBuffer key = new StringBuffer();

        for (int j = 0; j < length; j++) {

            int i = Math.abs(random.nextInt() % 26);

            i += 65;

            char c = (char) i;

            key.append(c);

        }

        return key.toString();

    }
}