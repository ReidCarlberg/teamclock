/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
/*
 * Created on Dec 2, 2003
 *
 */
package com.fstx.stdlib.authen.users;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * @author REID
 *  
 */
public class UserFactory {

    private static int count = 0;

    public static final UserFactory singleton = new UserFactory();

    public User build() throws Exception {
        count++;
        User ret = new User();
        ret.setUsername("user" + count);
        ret.setPassword("password" + count);
        ret.setEmail("email" + count + "@fivesticks.com");
        ret = UserDAOFactory.factory.build().save(ret);

        return ret;
    }
    
    public User build(String password) throws Exception {
        count++;
        User ret = new User();
        ret.setUsername("user" + count);
        ret.setPassword(password);
        ret.setEmail("email" + count + "@fivesticks.com");
        ret = UserDAOFactory.factory.build().save(ret);

        return ret;        
    }

    public User getPersisted() throws Exception {
        User ret = build();

        UserDAOFactory.factory.build().save(ret);

        return ret;
    }

    public User getPersistedWithSystemOwner(SystemOwner systemOwner)
            throws Exception {
        return getPersistedWithSystemOwner(systemOwner, UserTypeEnum.OWNERADMIN);
    }

    public User getPersistedWithSystemOwner(SystemOwner systemOwner,
            UserTypeEnum userTypeEnum) throws Exception {
        User u = getPersisted();
        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, u,
                userTypeEnum);
        return u;

    }
}