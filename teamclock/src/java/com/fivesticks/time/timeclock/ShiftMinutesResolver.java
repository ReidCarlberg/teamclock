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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author REID
 *  
 */
public class ShiftMinutesResolver {

    public int resolveShiftMinutes(Collection punches)
            throws ShiftMinutesResolverException {

        int ret = 0;

        ret = handleResolve(punches);

        return ret;
    }

    /**
     * @param punches
     * @return
     */
    private int handleResolve(Collection punches)
            throws ShiftMinutesResolverException {

        int ret = 0;

        Collection cleanPunches = this.getCleanPackage(punches);

        Iterator iPunches = cleanPunches.iterator();

        while (iPunches.hasNext()) {
            Timeclock clockIn = (Timeclock) iPunches.next();
            if (!iPunches.hasNext()) {
                break;
            }
            Timeclock clockOut = (Timeclock) iPunches.next();

            if (!clockIn.getEvent().equals(
                    TimeclockEventEnum.CLOCK_IN.getName())) {
                throw new ShiftMinutesResolverException(
                        "clock In is not a clock in record");
            }

            if (!clockOut.getEvent().equals(
                    TimeclockEventEnum.CLOCK_OUT.getName())) {
                throw new ShiftMinutesResolverException(
                        "clock out is not a clock out record");
            }

            int thisPunch = (int) ((clockOut.getEventTimestamp().getTime() - clockIn
                    .getEventTimestamp().getTime()) / 1000) / 60;

            ret += thisPunch;
        }

        return ret;
    }

    private Collection getCleanPackage(Collection punches) {
        Collection ret = new ArrayList();
        Iterator iPunches = punches.iterator();

        while (iPunches.hasNext()) {

            Timeclock current = (Timeclock) iPunches.next();

            if (current.getEvent().equals(
                    TimeclockEventEnum.BREAK_START.getName())
                    || current.getEvent().equals(
                            TimeclockEventEnum.BREAK_STOP.getName())) {

            } else {
                ret.add(current);
            }
        }

        return ret;
    }

}