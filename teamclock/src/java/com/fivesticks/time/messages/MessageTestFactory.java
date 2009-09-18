/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages;

import com.fivesticks.time.systemowner.SystemOwner;

public class MessageTestFactory {

    private static int count = 0;
    
    public static Message getUnpersisted(SystemOwner systemOwner) throws Exception {
        count++;
        
        Message ret = new Message();
        
        ret.setName("name" + count);
        ret.setSubject("subject" + count);
        ret.setMessage("message" + count);
        ret.setOwnerKey(systemOwner.getKey());
        
        return ret;
        
    }
    
    public static Message getPersisted(SystemOwner systemOwner) throws Exception {
        
        Message ret = getUnpersisted(systemOwner);
        
        MessageServiceDelegateFactory.factory.build(systemOwner).save(ret);
        
        return ret;
        
    }
}
