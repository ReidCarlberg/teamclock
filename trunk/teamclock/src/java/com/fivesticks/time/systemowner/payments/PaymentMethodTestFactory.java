/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import java.text.SimpleDateFormat;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class PaymentMethodTestFactory {

    static int count = 0;

    public static PaymentMethod buildSampleVisa(SystemOwner systemOwner)
            throws Exception {
        count++;

        return getPersisted(systemOwner, buildUnpersistedSampleVisa());

    }

    public static PaymentMethod buildUnpersistedSampleVisa() throws Exception {
        count++;

        return getUnpersisted("VISA", "4111111111111111", "622");

    }

    public static PaymentMethod getUnpersisted(
            String type, String number, String cvv) throws Exception {

        SimpleDate expires = SimpleDate.factory.build();

        
        expires.advanceMonth(1);

        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
        
        PaymentMethod info = new PaymentMethod();
        info.setNumber(number);
        info.setCvv(cvv);
        info
                .setExpiresMonth(""
                        + sdfMonth.format(expires.getDate()));
        info.setExpiresYear("" + sdfYear.format(expires.getDate()));

        info.setNameOnCard("first" + count);

        info.setCurrent(true);
        info.setStreet("6957 W. North Ave.");
        info.setCity("oak Park");
        info.setState("IL");
        info.setCountry("US");
        info.setZip("60302");
        
        info.setPhone("testphone");

        return info;

    }

    public static PaymentMethod getPersisted(SystemOwner systemOwner,
            PaymentMethod info) throws Exception {

//        PaymentMethod info = getUnpersisted(systemOwner, type, number, cvv);

        PaymentMethodServiceDelegateFactory.factory.build(systemOwner).save(info);

        return info;
    }
}
