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
package com.fivesticks.time.timeclock;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * <p>
 * There are different types of searches.
 * 
 * <p>
 * First are the raw records.
 * 
 * <p>
 * Second are the timesheet records. Timesheet records are a summary view.
 * 
 * @author REID
 *  
 */
public class TimeclockSearchBD  {

    private final SystemOwner systemOwner;
    
    public TimeclockSearchBD(SystemOwner systemOwner) {
        super();
        this.systemOwner = systemOwner;
    }

    public Collection getRawRecords(TimeclockFilterParameters filter)
            throws TimeclockSearchBDException {

        Collection ret;
        if(this.systemOwner == null)
        {
                throw new TimeclockSearchBDException("Missing system owner");
        }
        try {
            ret = TiimeclockBDFactory.factory.build(this.systemOwner).searchByFilter(
                    filter);
        } catch (TimeclockBDException e) {
            throw new TimeclockSearchBDException(e);
        }

        return ret;
    }

//    /**
//     * <p>
//     * Shiftwise records basically handles a collection of UserShiftRecords for
//     * each user in the date range.
//     * 
//     * @param filter
//     * @return
//     */
//    public Collection getShiftwiseRecords(ShiftwiseSearchParameters filter) {
//        throw new RuntimeException("not impl'ed");
//        //		return null;
//    }

//    private Collection handleShiftwiseRecords(ShiftwiseSearchParameters filter) {
//
//        //users
//        //-- build a collection of applicable users. if there is a single user
//        // in the filter,
//        //then add that to a collection
//
//        //dates for each user, we need to build a collection of shift records
//        // matching each start date.
//
//        return null;
//    }
// 2005-11-12 RSC
//    /**
//     * if we have one in the filter, just return that. otherwise return
//     * everything.
//     * 
//     * @param filter
//     * @return
//     */
//    private Collection buildTargetUserCollection(
//            ShiftwiseSearchParameters filter) throws DAOException,
//            UserBDException {
//        Collection users = null;
//        if (filter.getUsername() != null && filter.getUsername().length() > 0) {
//            users = new ArrayList();
//            users.add(UserBD.factory.build()
//                    .getByUsername(filter.getUsername()));
//        } else {
////            users = UserDAO.factory.build().find(UserDAO.SELECT_ALL);
//            users = UserBD.factory.build().getAll();
//        }
//
//        return users;
//    }


}