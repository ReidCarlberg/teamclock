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
 * Created on Nov 30, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 *  
 */
public class TimeclockEventEnum extends Enum {

    public static final TimeclockEventEnum CLOCK_IN = new TimeclockEventEnum(
            "CLOCK_IN", "Clock In");

    public static final TimeclockEventEnum CLOCK_OUT = new TimeclockEventEnum(
            "CLOCK_OUT", "Clock Out");

    public static final TimeclockEventEnum BREAK_START = new TimeclockEventEnum(
            "BREAK_START", "Break Start");

    public static final TimeclockEventEnum BREAK_STOP = new TimeclockEventEnum(
            "BREAK_STOP", "Break Stop");

    private String friendlyName;

    /**
     * @param arg0
     */
    public TimeclockEventEnum(String arg0, String friendlyName) {
        super(arg0);
        this.friendlyName = friendlyName;
    }

    public static Collection getCollection() {
        Collection ret = new ArrayList();
        ret.add(CLOCK_IN);
        ret.add(CLOCK_OUT);
        ret.add(BREAK_START);
        ret.add(BREAK_STOP);
        return ret;
    }

    public static TimeclockEventEnum getByName(String name) {
        TimeclockEventEnum ret = null;

        Iterator i = getCollection().iterator();

        while (i.hasNext()) {
            TimeclockEventEnum current = (TimeclockEventEnum) i.next();
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
    public String getFriendlyName() {
        return friendlyName;
    }

}