/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.tokens;

import java.util.Date;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

public class TokenTestFactory {

    private static int count = 0;

    public static Token getPersisted(SystemOwner systemOwner) throws Exception {

        count++;

        Token ret = getUnpersisted(TokenServiceDelegateImpl.TYPE_SYSTEM,
                systemOwner.getKey(), null, "token" + count);

        GenericDAO dao = (GenericDAO) SpringBeanBroker.getBeanFactory().getBean(GenericDAO.SPRING_BEAN_NAME);
        
        dao.save(ret);
        return ret;

    }
    
    public static Token getPersisted(User user) throws Exception {

        count++;

        Token ret = getUnpersisted(TokenServiceDelegateImpl.TYPE_SYSTEM,
                null, user.getUsername(), "token" + count);

        return ret;

    }    

    public static Token getUnpersisted(String type, String ownerKey,
            String username, String token) {

        Token ret = new Token();

        ret.setActive(true);
        ret.setIssued(new Date());
        ret.setOwnerKey(ownerKey);
        ret.setToken(token);
        ret.setType(type);
        ret.setUsername(username);

        return ret;

    }
}
