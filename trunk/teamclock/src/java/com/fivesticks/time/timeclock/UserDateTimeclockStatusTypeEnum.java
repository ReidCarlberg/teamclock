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
 * Created on Nov 22, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 *  
 */
public class UserDateTimeclockStatusTypeEnum extends Enum {

    private static final List list = new ArrayList();

    public static final UserDateTimeclockStatusTypeEnum NO_RECORD = new UserDateTimeclockStatusTypeEnum(
            "NO_RECORD", "No Record");

    public static final UserDateTimeclockStatusTypeEnum CLOCKED_IN = new UserDateTimeclockStatusTypeEnum(
            "CLOCKED_IN", "Clocked In");

    public static final UserDateTimeclockStatusTypeEnum CLOCKED_OUT = new UserDateTimeclockStatusTypeEnum(
            "CLOCKED_OUT", "Clocked Out");

    public static final UserDateTimeclockStatusTypeEnum BREAK = new UserDateTimeclockStatusTypeEnum(
            "ON_BREAK", "On Break");

    public static final UserDateTimeclockStatusTypeEnum INCOMPLETE = new UserDateTimeclockStatusTypeEnum(
            "INCOMPLETE", "Incomplete");

    private String friendlyName;

    /**
     * @param arg0
     */
    protected UserDateTimeclockStatusTypeEnum(String name, String friendlyName) {
        super(name);
        this.friendlyName = friendlyName;
        list.add(this);
    }

    /**
     * @return
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    public static UserDateTimeclockStatusTypeEnum getByName(String name) {
        UserDateTimeclockStatusTypeEnum ret = null;

        Iterator i = getList().iterator();

        while (i.hasNext()) {
            UserDateTimeclockStatusTypeEnum current = (UserDateTimeclockStatusTypeEnum) i
                    .next();
            if (current.getName().equals(name)) {
                ret = current;
                break;
            }
        }

        return ret;
    }

    /**
     * @return
     */
    public static List getList() {
        return list;
    }

}