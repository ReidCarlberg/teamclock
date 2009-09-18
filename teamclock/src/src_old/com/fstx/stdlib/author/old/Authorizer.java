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
 * Created on Aug 18, 2003
 *
 */
package com.fstx.stdlib.author.old;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.useradmin.FstxTimeSystemRights;
import com.fstx.stdlib.authen.AuthenticatedUser;

/**
 * @author Nick
 *  
 */
public class Authorizer {
    public static final Authorizer singleton = new Authorizer();

    private Authorizer() {
        super();
    }

    public boolean isAuthorized(AuthenticatedUser user,
            FstxTimeSystemRights right) {
        return isAuthorized(user, right.getName());
    }

    public boolean isAuthorizedForAny(AuthenticatedUser user,
            FstxTimeSystemRights[] rights) {
        boolean ret = false;

        for (int i = 0; i < rights.length; i++) {
            if (rights[i] != null && isAuthorized(user, rights[i])) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    public boolean isAuthorized(AuthenticatedUser user, String action) {
        AuthorizationBean ab = null;
        if (user == null || action == null) {
            return false;
        }
        try {
            ab = AuthorizationManager.singleton.getAuthorizationBean(user
                    .getUsername());
            if (ab == null) {
                throw new AuthorizationException();
            }
        } catch (AuthorizationException e) {
            log.error("Unable to retrieve auth list for:  "
                    + user.getUsername());
            return false;
        }
        //Check if user is authorized to use action
        return ab.isAuthorized(action);
    }

    public boolean isAuthorized(String user, FstxTimeSystemRights right) {
        return isAuthorized(user, right.getName());
    }

    // No to be used for security checks.
    public boolean isAuthorized(String user, String action) {
        AuthorizationBean ab = null;
        if (user == null || action == null) {
            return false;
        }
        try {
            ab = AuthorizationManager.singleton.getAuthorizationBean(user);
            if (ab == null) {
                throw new AuthorizationException();
            }
        } catch (AuthorizationException e) {
            log.error("Unable to retrieve auth list for:  " + user);
            return false;
        }
        //Check if user is authorized to use action
        return ab.isAuthorized(action);
    }

    static Log log = LogFactory.getLog(Authorizer.class.getName());
}