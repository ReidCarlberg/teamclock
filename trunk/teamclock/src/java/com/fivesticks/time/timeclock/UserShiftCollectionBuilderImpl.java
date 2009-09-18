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
 * Created on Dec 26, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.DatePair;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 *  
 */
class UserShiftCollectionBuilderImpl implements UserShiftCollectionBuilder {

    private final SystemOwner systemOwner;
    
    private static Log log = LogFactory.getLog(UserShiftCollectionBuilderImpl.class);
    
    UserShiftCollectionBuilderImpl(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.timeclock.UserShiftCollectionBuilder#build(com.fstx.stdlib.authen.users.User,
     *      com.fstx.stdlib.common.simpledate.DatePair)
     */
    public Collection buildAlreadyResolved(User user, DatePair range)
            throws UserShiftCollectionBuilderException {
        return handleBuild(user, range);
    }

    /**
     * @param user
     * @param range
     * @return
     */
    private Collection handleBuild(User user, DatePair range)
            throws UserShiftCollectionBuilderException {
        Collection ret = new ArrayList();

        SimpleDate start = SimpleDate.factory.buildMidnight(range.getStart());
        SimpleDate end = SimpleDate.factory.buildMidnight(range.getEnd());

//        log.info("range from  " + start.getMmddyyyy() + " to " + end.getMmddyyyy());
        
        while (start.getDate().compareTo(end.getDate()) < 1) {

            UserShiftRecord current;
            try {
                current = new UserShiftRecordBuilder(this.systemOwner).buildByResolvedStartDate(user,
                        start.getDate());
            } catch (UserShiftRecordBuilderException e) {
                throw new UserShiftCollectionBuilderException(e);
            }
            ret.add(current);
            start.advanceDay();
        }

        return ret;
    }

}