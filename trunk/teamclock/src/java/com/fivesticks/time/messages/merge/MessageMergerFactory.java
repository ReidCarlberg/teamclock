/*
 * Created on Jun 6, 2005 by Reid
 */
package com.fivesticks.time.messages.merge;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class MessageMergerFactory {

    public static MessageDataMerger build(SystemOwner systemOwner,
            Customer fstxCustomer, Message message) {

        MessageDataMerger ret = new CustomerMessageDataMerger(systemOwner,
                fstxCustomer, message);

        return ret;

    }
    
    public static MessageDataMerger build(SystemOwner systemOwner,
            ContactV2 contactV2, Message message) {

        MessageDataMerger ret = new ContactMessageDataMerger(systemOwner,
                contactV2, message);

        return ret;

    }

    public static MessageDataMerger build(SystemOwner systemOwner, SystemOwner systemOwnerTarget,
            Message message) {

        MessageDataMerger ret = new SystemOwnerMessageDataMerger(systemOwner,systemOwnerTarget,
                message);

        return ret;

    }

}
