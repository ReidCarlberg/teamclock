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

package com.fivesticks.time.timeclock;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;

/**
 * @author Nick
 *  
 */
public class TimeclockDeleteCommand extends DeleteCommand {

    //private static final String XWORK_SUCCESS = "delete-success-timeclock-event";

    private String xWorkSuccess = "delete-success-timeclock-event";
    
    private Timeclock target;

    /**
     * @param target
     */
    public TimeclockDeleteCommand(Timeclock target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#handleDelete()
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        try {

            TiimeclockBDFactory.factory.build(sessionContext).delete(target);
        } catch (Exception e) {
            throw new DeleteCommandFailedException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {
        StringBuffer ret = new StringBuffer();

        ret.append("Entry for: " + target.getUsername() + ", ");

        ret.append(target.getEvent() + ", ");

        ret.append(target.getEventTimestamp());

        return ret.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {
        return "This will permanently remove this time entry.";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
//    public String getXWorkSuccess() throws DeleteCommandFailedException {
//
//        return XWORK_SUCCESS;
//    }

    /**
     * @return Returns the xworkSuccess.
     */
    public String getXWorkSuccess() {
        return xWorkSuccess;
    }

    /**
     * @param xworkSuccess The xworkSuccess to set.
     */
    public void setXWorkSuccess(String xworkSuccess) {
        this.xWorkSuccess = xworkSuccess;
    }
}